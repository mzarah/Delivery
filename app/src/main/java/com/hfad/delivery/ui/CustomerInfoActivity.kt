package com.hfad.delivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hfad.delivery.R
import com.hfad.delivery.SharedPreference
import com.hfad.delivery.repository.Repository
import com.hfad.delivery.viewmodel.UserTokenVMFactory
import com.hfad.delivery.viewmodel.UserTokenViewModel
import kotlinx.android.synthetic.main.activity_customer_info.*
import kotlinx.android.synthetic.main.activity_promocodes.*

class CustomerInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Delivery)
        setContentView(R.layout.activity_customer_info)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar!!.title = "Profile"

        val repository = Repository()
        val viewModelFactory = UserTokenVMFactory(repository)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(UserTokenViewModel::class.java)

        val headers: MutableMap<String, String> = HashMap()
        val sharedPreference: SharedPreference = SharedPreference(this)
        val token: String? = sharedPreference.getValueString("token")
        Log.d("token", token)
        headers["Authorization"] = "Bearer $token"

        viewModel.getCustomerInfo(headers)
        viewModel.customerInfoResponse.observe(this, {
            if (it.isSuccessful) {
                fullname.setText(it?.body()?.fullName)
                email.setText(it?.body()?.email)
                phonenumber.setText(it?.body()?.phoneNumber)
               // gender.sele
                bdate.setText(it?.body()?.birthDate)
            } else {
                Log.d("ERROR", it.errorBody().toString())
                Toast.makeText(this, it.errorBody()?.string(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}