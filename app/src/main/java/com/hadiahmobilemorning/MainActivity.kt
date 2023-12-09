package com.hadiahmobilemorning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.hadiahmobilemorning.databinding.ActivityMainBinding
import com.hadiahmobilemorning.fragment.BookFragment
import com.hadiahmobilemorning.fragment.FeedbackFragment
import com.hadiahmobilemorning.fragment.HomeFragment
import com.hadiahmobilemorning.fragment.LogoutFragment
import com.hadiahmobilemorning.fragment.ProfileFragment
import com.hadiahmobilemorning.fragment.SettingsFragment
import com.hadiahmobilemorning.fragment.ShareFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_bottomNavbar -> replaceFragment(HomeFragment())
                R.id.book_bottomNavbar -> replaceFragment(BookFragment())
                R.id.profile_bottomNavbar -> replaceFragment(ProfileFragment())

                else -> {
                }
            }
            true
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: NavigationView = binding.navView

        var drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation item clicks in the drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_settings -> replaceFragment(SettingsFragment())
                R.id.nav_share -> replaceFragment(ShareFragment())
                R.id.nav_feedback -> replaceFragment(FeedbackFragment())
                R.id.nav_logout -> replaceFragment(LogoutFragment())
            }
            // Close the drawer after handling item click
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        // Enable the ActionBarDrawerToggle to open/close the drawer
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon_24) // Set your burger icon here

        toggle.setToolbarNavigationClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
