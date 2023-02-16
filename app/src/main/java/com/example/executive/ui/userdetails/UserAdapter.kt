package com.example.executive.ui.userdetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.executive.data.model.Country
import com.example.executive.databinding.UserItemLayoutBinding
import com.google.gson.Gson

class UserAdapter(
    private val context: Context,
    private var userList: Map<String, Country>,
    var flag: Boolean
) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            context: Context,
            userDetails: Country,
            userList: Map<String, Country>,
            flag: Boolean
        ) {
            if (flag)
                binding.tvCountry.text = userDetails.region
            else
                binding.tvCountry.text = userDetails.country
            itemView.setOnClickListener {
                if (flag) {
                    val list: HashMap<String, Country> = HashMap()
                    list.putAll(userList)
                    val intent = Intent(context, UserDetailsActivity::class.java)
                    intent.putExtra("Region", userDetails.region)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(context, userList[userList.keys.elementAt(position)]!!, userList, flag)
    }

    fun addData(list: Map<String, Country>) {
        userList = list
    }

}