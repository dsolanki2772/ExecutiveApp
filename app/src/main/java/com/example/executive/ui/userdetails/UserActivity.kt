package com.example.executive.ui.userdetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.executive.MVVMApplication
import com.example.executive.data.dao.CountryDao
import com.example.executive.data.db.ExecutiveDatabase
import com.example.executive.data.model.Country
import com.example.executive.databinding.ActivityUserBinding
import com.example.executive.di.component.DaggerActivityComponent
import com.example.executive.di.module.ActivityModule
import com.example.executive.ui.base.UiState
import com.example.executive.utils.Commons
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @Inject
    lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityUserBinding
    private lateinit var countryDao: CountryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val db = ExecutiveDatabase.getInstance(applicationContext)
        countryDao = db.countryDao()
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data.data)
                            GlobalScope.launch { countryDao.insertAll(it.data) }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@UserActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(userList: Map<String, Country>) {
        val distList = userList.toList().distinctBy { it.second.region }.toMap()
        adapter.addData(distList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}