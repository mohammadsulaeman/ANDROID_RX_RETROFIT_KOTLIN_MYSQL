package com.example.restapiandroid.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.restapiandroid.R
import com.example.restapiandroid.databinding.ActivityUpdateUsersBinding
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.presenter.editdel.EditDeleteDataUsersPresenter
import com.example.restapiandroid.presenter.editdel.EditDeleteView

class UpdateUsersActivity : AppCompatActivity(), EditDeleteView  {
    private lateinit var binding: ActivityUpdateUsersBinding
    private lateinit var presenter: EditDeleteDataUsersPresenter
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tampilkan data
        val intent = intent
        val id = intent.getStringExtra("id")
        Log.d(TAG, "ID = $id")
        binding.editTextNamaEditDel.setText(intent.getStringExtra("nama"))
        binding.editTextAlamatEditDel.setText(intent.getStringExtra("alamat"))
        binding.editTextPhoneEditDel.setText(intent.getStringExtra("phone"))
        binding.editTextEmailEditDel.setText(intent.getStringExtra("email"))
        binding.editTextPendidikanEditDel.setText(intent.getStringExtra("pendidikan"))
        binding.editTextTtlEditDel.setText(intent.getStringExtra("ttl"))
        presenter = EditDeleteDataUsersPresenter(this)
        binding.imageViewBackEditDel.setOnClickListener {
            startActivity(Intent(this@UpdateUsersActivity, MainActivity::class.java))
        }
        binding.buttonUpdate.setOnClickListener {
            val nama = binding.editTextNamaEditDel.text.toString()
            val alamat = binding.editTextAlamatEditDel.text.toString()
            val email = binding.editTextEmailEditDel.text.toString()
            val phone = binding.editTextPhoneEditDel.text.toString()
            val pendidikan = binding.editTextPendidikanEditDel.text.toString()
            val ttl = binding.editTextTtlEditDel.text.toString()
            Log.v(TAG, "Nama = $nama, Alamat = $alamat, Email = $email, Phone = $phone, Pendidikan = $pendidikan, TTL = $ttl")
            if (TextUtils.isEmpty(nama))
            {
                Toast.makeText(applicationContext,"Nama Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(alamat))
            {
                Toast.makeText(applicationContext,"Alamat Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(email))
            {
                Toast.makeText(applicationContext,"Email Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(phone))
            {
                Toast.makeText(applicationContext,"Phone Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(pendidikan))
            {
                Toast.makeText(applicationContext,"Pendidikan Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(ttl))
            {
                Toast.makeText(applicationContext,"Tempat Tanggal Lahir Wajib Di Isi", Toast.LENGTH_LONG).show()
            }else{
                if (id != null) {
                    presenter.getEditData(id, nama, alamat, phone, ttl, pendidikan, email)
                startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Toast.makeText(applicationContext,"Data Gagal Di Update",Toast.LENGTH_LONG).show()
                }

            }
        }

        binding.buttonDelete.setOnClickListener {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@UpdateUsersActivity)
            alertDialog.setMessage("Apa Anda Yakin Menghapus Data Anda ?")
            alertDialog.setPositiveButton("YAKIN!", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (id != null) {
                        presenter.getDeleteData(id)
                        startActivity(Intent(this@UpdateUsersActivity, MainActivity::class.java))
                    }else{
                        Toast.makeText(applicationContext,"Data Gagal Di Delete",Toast.LENGTH_LONG).show()
                    }

                }

            }).setNegativeButton("TIDAK!", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }

            }).create().show()
        }
    }

    companion object {
        const val EXTRA_ID = "com.example.restapiandroid.ui.EXTRA_ID"
        const val EXTRA_NAME = "com.example.restapiandroid.ui.EXTRA_NAME"
        const val EXTRA_ALAMAT = "com.example.restapiandroid.ui.EXTRA_ALAMAT"
        const val EXTRA_PHONE = "com.example.restapiandroid.ui.EXTRA_PHONE"
        const val EXTRA_EMAIL = "com.example.restapiandroid.ui.EXTRA_EMAIL"
        const val EXTRA_TTL = "com.example.restapiandroid.ui.EXTRA_TTL"
        const val EXTRA_PENDIDIKAN = "com.example.restapiandroid.ui.EXTRA_PENDIDIKAN"
    }

    override fun showLoading() {
        binding.progressCirculareditDel.visibility = View.VISIBLE
        binding.lineEditDel.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.progressCirculareditDel.visibility = View.GONE
        binding.lineEditDel.visibility = View.VISIBLE
    }

    override fun onSuccess(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }
}