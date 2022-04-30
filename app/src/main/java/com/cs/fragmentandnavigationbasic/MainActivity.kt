package com.cs.fragmentandnavigationbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.cs.fragmentandnavigationbasic.databinding.ActivityMainBinding
import com.cs.fragmentandnavigationbasic.fragments.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

         drawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
       drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_dashboard -> replaceFragment(DashboardFragment(), it.title.toString())
                R.id.nav_sync -> replaceFragment(SyncFragment(), it.title.toString())
                R.id.nav_message -> replaceFragment(MessageFragment(), it.title.toString())
                R.id.nav_trash -> replaceFragment(TrashFragment(), it.title.toString())
                R.id.nav_login -> replaceFragment(LoginFragment(), it.title.toString())
                R.id.nav_share -> replaceFragment(ShareFragment(), it.title.toString())
                R.id.nav_rate_us -> replaceFragment(RateUsFragment(), it.title.toString())

            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}