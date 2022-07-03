package com.example.dicodinggitprofile.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dicodinggitprofile.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_LOGIN)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserInfoViewModel::class.java)

        viewModel.setUserInfo(username)

        viewModel.getUserInfo().observe(this, {
            if (it != null) {
                binding.apply {
                    infoName.text = it.name
                    infoLogin.text = it.login
                    infoFollowers.text = "${ it.followers } Followers"
                    infoFollowing.text = "${ it.following } Following"
                    Glide.with(this@UserInfoActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(infoAvatar)
                }
            }
        })

        val followPageAdapter = FollowPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = followPageAdapter
            followTabs.setupWithViewPager(viewPager)
        }
    }
}