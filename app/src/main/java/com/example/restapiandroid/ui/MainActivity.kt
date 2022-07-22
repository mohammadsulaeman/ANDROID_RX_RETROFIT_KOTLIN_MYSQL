package com.example.restapiandroid.ui

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiandroid.adapter.UsersAdapter
import com.example.restapiandroid.databinding.ActivityMainBinding
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.network.model.GetDataUsersResponseJson
import com.example.restapiandroid.presenter.read.GetDataUsersPresenter
import com.example.restapiandroid.presenter.read.GetDataViewInterface

class MainActivity : AppCompatActivity() , GetDataViewInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UsersAdapter
    lateinit var presenter: GetDataUsersPresenter
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAdd.setOnClickListener {
            val intentAdd = Intent(this@MainActivity, InsertNewUsersActivity::class.java)
            startActivity(intentAdd)
        }

        setUpMVP()
        setUpView()
        getUsersList()
    }

    fun getUsersList()
    {
        presenter.getUsers()
    }
    fun setUpMVP(){
        presenter = GetDataUsersPresenter(this)
    }
    fun setUpView()
    {
        binding.recyclerviewAdd.setHasFixedSize(true)
        binding.recyclerviewAdd.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
    override fun showLoading() {
        binding.progressCircularMain.visibility = View.VISIBLE
        binding.recyclerviewAdd.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.progressCircularMain.visibility = View.GONE
        binding.recyclerviewAdd.visibility = View.VISIBLE
        binding.fabAdd.visibility = View.VISIBLE
    }

    override fun onSuccess(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onGetDataUsersAll(request: GetDataUsersResponseJson) {
       if (request != null)
       {
           Log.v(TAG, "OnGetDataUsers = ${request.biodata}")
           adapter = UsersAdapter(this@MainActivity, request.biodata)
           binding.recyclerviewAdd.adapter = adapter
       }
    }
}