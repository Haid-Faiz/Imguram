package com.example.imguram

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        //------------------- action Bar code-------------------------
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            // These are fragments ids in mobile navigation nav graph
//                R.id.navigation_hot, R.id.navigation_trending))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        //----------------Action Bar code------------------------------

        navView.setupWithNavController(navController)
    }
}