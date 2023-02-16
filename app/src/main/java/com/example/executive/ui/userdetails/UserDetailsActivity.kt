package com.example.executive.ui.userdetails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.executive.data.dao.CountryDao
import com.example.executive.data.db.ExecutiveDatabase
import com.example.executive.data.model.Country
import com.example.executive.databinding.UserDetailsActivityBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserDetailsActivity : AppCompatActivity() {
    private lateinit var userList: Map<String, Country>
    var region: String = ""
    private lateinit var countryDao: CountryDao

    @Inject
    lateinit var adapter: UserAdapter
    private lateinit var binding: UserDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailsActivityBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        region = intent.getStringExtra("Region").toString()

        setContentView(binding.root)
        setUI()
        setupObserver()
    }

    private fun setUI() {
        adapter = UserAdapter(this, HashMap(),false)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        GlobalScope.launch {
            val db = ExecutiveDatabase.getInstance(applicationContext)
            countryDao = db.countryDao()
            val data = countryDao.getAllData()
            userList = data.data
            val list: Map<String, Country> = userList.filter { (_, value) ->
                value.region == region
            }
            adapter.addData(list)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}