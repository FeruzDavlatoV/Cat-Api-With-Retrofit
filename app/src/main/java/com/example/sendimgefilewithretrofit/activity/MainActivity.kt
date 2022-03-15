package com.example.sendimgefilewithretrofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sendimgefilewithretrofit.R
import com.example.sendimgefilewithretrofit.fragments.SearchFragment
import com.example.sendimgefilewithretrofit.fragments.UploadFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchFragment: SearchFragment
    private lateinit var uploadFragment: UploadFragment
    private lateinit var fab_btn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        searchFragment = SearchFragment()
        uploadFragment = UploadFragment()
        bottomNavigationView.background = null
        fab_btn = findViewById(R.id.fab)

        fab_btn.setOnClickListener{
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView.menu.getItem(0).isCheckable = true
        loadFragment(searchFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {

                R.id.searchFragment -> {
                    loadFragment(searchFragment)
                    true
                }
                R.id.uploadFragment -> {
                    loadFragment(uploadFragment)
                    true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment)
            .commit()
    }
}