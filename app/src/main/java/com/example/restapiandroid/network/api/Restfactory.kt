package com.example.restapiandroid.network.api

import android.util.Base64
import com.example.restapiandroid.constants.Constant
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Restfactory {
   companion object
   {
       private val booleanSerializerDeserializer = BooleanSerializerDeserializer()
       private val httpClient = OkHttpClient.Builder()

       // Create a trust manager that does not validate certificate chains
       val unsafeOkHttpClient: OkHttpClient

       // Install the all-trusting trust manager

           // Create an ssl socket factory with our all-trusting manager
           get() = try {
               // Create a trust manager that does not validate certificate chains
               val trustAllCerts =
                   arrayOf<TrustManager>(
                       object : X509TrustManager {
                           @Throws(CertificateException::class)
                           override fun checkClientTrusted(
                               chain: Array<X509Certificate>,
                               authType: String
                           ) {
                           }

                           @Throws(CertificateException::class)
                           override fun checkServerTrusted(
                               chain: Array<X509Certificate>,
                               authType: String
                           ) {
                           }

                           override fun getAcceptedIssuers(): Array<X509Certificate> {
                               return arrayOf()
                           }
                       }
                   )

               // Install the all-trusting trust manager
               val sslContext =
                   SSLContext.getInstance("SSL")
               sslContext.init(null, trustAllCerts, SecureRandom())

               // Create an ssl socket factory with our all-trusting manager
               val sslSocketFactory = sslContext.socketFactory
               httpClient.sslSocketFactory(
                   sslSocketFactory,
                   trustAllCerts[0] as X509TrustManager
               )
               httpClient.hostnameVerifier { hostname, session -> true }
               httpClient.build()
           } catch (e: Exception) {
               throw RuntimeException(e)
           }
       var gson = GsonBuilder()
           .setDateFormat("yyyy-MM-dd HH:mm:ss")
           .serializeNulls()
           .registerTypeAdapter(Boolean::class.java, booleanSerializerDeserializer)
           .registerTypeAdapter(Boolean::class.javaPrimitiveType, booleanSerializerDeserializer)
           .create()
       private val builder: Retrofit.Builder = Retrofit.Builder()
           .baseUrl(Constant.CONNECTION)
           .client(unsafeOkHttpClient)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create(gson))

       fun <S> createService(serviceClass: Class<S>?): S {
           return createService(serviceClass, null, null)
       }

       fun <S> createService(serviceClass: Class<S>?, username: String?, password: String?): S {
           if (!httpClient.interceptors().isEmpty()) {
               httpClient.interceptors().clear()
           }
           if (username != null && password != null) {
               val credentials = "$username:$password"
               val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
               httpClient.addInterceptor { chain ->
                   val original = chain.request()
                   val requestBuilder = original.newBuilder()
                       .header("Authorization", basic)
                       .header("Accept", "application/json")
                       .method(original.method(), original.body())
                   val request = requestBuilder.build()
                   chain.proceed(request)
               }
           }
           if (Log.LOG) {
               val logging = HttpLoggingInterceptor()
               logging.level = HttpLoggingInterceptor.Level.BODY
               httpClient.addInterceptor(logging)
           }
           val client = httpClient
               .connectTimeout(120, TimeUnit.SECONDS)
               .readTimeout(60, TimeUnit.SECONDS)
               .writeTimeout(60, TimeUnit.SECONDS)
               .build()
           val retrofit = builder.client(client).build()
           return retrofit.create(serviceClass)
       }
   }
}