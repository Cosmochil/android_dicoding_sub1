package com.example.dicodinggitprofile.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodinggitprofile.databinding.ActivityMainBinding
import com.example.dicodinggitprofile.model.GitResponse
import com.example.dicodinggitprofile.model.Users
import com.example.dicodinggitprofile.services.GitInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)

        binding.apply {
            rv_git_users.layoutManager = LinearLayoutManager(this@MainActivity)
            rv_git_users.setHasFixedSize(true)
            rv_git_users.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()

            }
            editQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false

            }

        }
        viewModel.getSearchUser().observe(this, {
            if( it != null) {
                adapter.setList(it)
                showLoading(false)

            }
        })
    }

    private fun searchUser() {
        binding.apply {
            val query = editQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUser(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}