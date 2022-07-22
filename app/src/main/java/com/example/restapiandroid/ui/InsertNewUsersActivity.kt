package com.example.restapiandroid.ui


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restapiandroid.databinding.ActivityInsertNewUsersBinding
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.presenter.insert.AddDataPresenter
import com.example.restapiandroid.presenter.insert.AddDataView

class InsertNewUsersActivity : AppCompatActivity(), AddDataView {
    private lateinit var binding: ActivityInsertNewUsersBinding
    private lateinit var presenter: AddDataPresenter
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertNewUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewBackAdd.setOnClickListener {
            startActivity(Intent(this@InsertNewUsersActivity,MainActivity::class.java))
        }


        presenter = AddDataPresenter(this)
        binding.buttonSimpanAdd.setOnClickListener {
            val nama = binding.editTextNamaAdd.text.toString()
            val alamat = binding.editTextAlamatAdd.text.toString()
            val email = binding.editTextEmailAdd.text.toString()
            val phone = binding.editTextPhoneAdd.text.toString()
            val pendidikan = binding.editTextPendidikanAdd.text.toString()
            val ttl = binding.editTextTtlAdd.text.toString()
            Log.v(TAG, "Nama = $nama, Alamat = $alamat, Email = $email, Phone = $phone, Pendidikan = $pendidikan, TTL = $ttl")
            if (TextUtils.isEmpty(nama))
            {
                Toast.makeText(applicationContext,"Nama Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(alamat))
            {
                Toast.makeText(applicationContext,"Alamat Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(email))
            {
                Toast.makeText(applicationContext,"Email Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(phone))
            {
                Toast.makeText(applicationContext,"Phone Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(pendidikan))
            {
                Toast.makeText(applicationContext,"Pendidikan Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(ttl))
            {
                Toast.makeText(applicationContext,"Tempat Tanggal Lahir Wajib Di Isi",Toast.LENGTH_LONG).show()
            }else{
                presenter.getTambahData(nama,alamat,phone, ttl, pendidikan, email)
                val mainPage = Intent(this@InsertNewUsersActivity, MainActivity::class.java)
                startActivity(mainPage)
            }

        }
    }


    override fun onSuccess(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressCircularadd.visibility = View.VISIBLE
        binding.lineadd.visibility = View.GONE
    }

    override fun onHideLoading() {
        binding.progressCircularadd.visibility = View.GONE
        binding.lineadd.visibility = View.VISIBLE
    }
}