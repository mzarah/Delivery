package com.hfad.delivery

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.hfad.delivery.ui.CustomerInfoActivity
import com.hfad.delivery.ui.LogInActivity
import com.hfad.delivery.ui.PromocodesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Delivery)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.closed)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)   //for displaying burger and backbutton


        val sharedPreference: SharedPreference = SharedPreference(this)
        if(sharedPreference.getValueString("token")!=null)
        {
            showMenuItems()
        }
        else {
            hideMenuItems()
        }


        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.miLogin -> startNewActivity(LogInActivity())
                R.id.miPromocodes -> startNewActivity(PromocodesActivity())
                R.id.miProfile -> startNewActivity(CustomerInfoActivity())
                R.id.miLogout -> {
                    sharedPreference.removeValue("token")
                    navView.menu.findItem(R.id.miLogin).isVisible = true
                    hideMenuItems()
                }
            }
            onOptionsItemSelected(it)
            true
        }
    }

    // for burger and backbutton working by clicking
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startNewActivity (activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.drawerLayout, fragment)
            commit()
        }

    fun showMenuItems() {
        navView.menu.findItem(R.id.miProfile).isVisible = true
        navView.menu.findItem(R.id.miPayments).isVisible = true
        navView.menu.findItem(R.id.miPromocodes).isVisible = true
        navView.menu.findItem(R.id.miAdresses).isVisible = true
        navView.menu.findItem(R.id.miOrders).isVisible = true
        navView.menu.findItem(R.id.miLogout).isVisible = true
        navView.menu.findItem(R.id.miLogin).isVisible = false
    }

    fun hideMenuItems() {
        navView.menu.findItem(R.id.miProfile).isVisible = false
        navView.menu.findItem(R.id.miPayments).isVisible = false
        navView.menu.findItem(R.id.miPromocodes).isVisible = false
        navView.menu.findItem(R.id.miAdresses).isVisible = false
        navView.menu.findItem(R.id.miOrders).isVisible = false
        navView.menu.findItem(R.id.miLogout).isVisible = false
        navView.menu.findItem(R.id.miLogin).isVisible = true
    }

}