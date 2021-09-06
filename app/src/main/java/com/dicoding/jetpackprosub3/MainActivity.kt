package com.dicoding.jetpackprosub3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dicoding.jetpackprosub3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private val binding get() = activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupBottomNav()

    }

    private fun setupBottomNav() {
        val bottomNavigationView = binding?.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_main) as NavHostFragment
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.navController
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}