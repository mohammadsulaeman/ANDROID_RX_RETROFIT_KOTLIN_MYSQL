package com.example.restapiandroid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiandroid.R
import com.example.restapiandroid.network.model.Users
import com.example.restapiandroid.ui.UpdateUsersActivity

class UsersAdapter(var context: Context,var datalist : List<Users>) : RecyclerView.Adapter<UsersAdapter.ItemViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val users = datalist.get(position)
        holder.namaAnda.text = users.nama
        holder.alamatAnda.text = users.alamat
        holder.emailAnda.text = users.email
        holder.phoneAnda.text = users.phone
        holder.ttlAnda.text = users.ttl
        holder.pendidikanAnda.text = users.pendidikan

        holder.linkCLikc.setOnClickListener {
            val updateData = Intent(context, UpdateUsersActivity::class.java)
            updateData.putExtra("id", users.id)
            updateData.putExtra("nama",users.nama)
            updateData.putExtra("alamat", users.alamat)
            updateData.putExtra("email", users.email)
            updateData.putExtra("pendidikan", users.pendidikan)
            updateData.putExtra("phone",users.phone)
            updateData.putExtra("ttl",users.ttl)
            context.startActivity(updateData)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val namaAnda : TextView = itemView.findViewById(R.id.textViewNamaItem)

        val alamatAnda : TextView = itemView.findViewById(R.id.textViewAlamatItem)

        val emailAnda : TextView = itemView.findViewById(R.id.textViewEmailItem)

        val phoneAnda : TextView = itemView.findViewById(R.id.textViewPhoneItem)

        val ttlAnda : TextView = itemView.findViewById(R.id.textViewTTLItem)

        val pendidikanAnda : TextView = itemView.findViewById(R.id.textViewPendidikanItem)

        val linkCLikc : LinearLayout = itemView.findViewById(R.id.lineClickItem)

    }


}