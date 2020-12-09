package com.hfad.delivery.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.delivery.MainActivity
import com.hfad.delivery.R
import com.hfad.delivery.SharedPreference
import com.hfad.delivery.model.PhoneNOtp
import com.hfad.delivery.repository.Repository
import com.hfad.delivery.viewmodel.UserTokenVMFactory
import com.hfad.delivery.viewmodel.UserTokenViewModel
import kotlinx.android.synthetic.main.activity_log_in.nextBtn
import kotlinx.android.synthetic.main.activity_log_in2.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LogIn2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Delivery)
        setContentView(R.layout.activity_log_in2)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar!!.title = "Log in"

        val sharedPreference: SharedPreference = SharedPreference(this)

        var intent1: Intent = getIntent()
        val phone = intent1.getStringExtra("phoneNumber")
        phoneNumber_txt.setText(phone)

        val repository = Repository()
        val viewModelFactory = UserTokenVMFactory(repository)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(UserTokenViewModel::class.java)


        nextBtn.setOnClickListener {
            var numbernotp = PhoneNOtp(phone, otp.text.toString())
            viewModel.authenticate(numbernotp)

            viewModel.userTokenResponse.observe(this, Observer {
                if (it.isSuccessful) {
                    sharedPreference.save("token", it.body()!!.id_token.toString())
                    if (it.body()!!.registered) {
                        val intent = Intent(this@LogIn2Activity, MainActivity::class.java)
                        startActivity(intent);
                    } else {
                        // TODO: 29-Nov-20  go to sign up screen
                    }
                } else {
                    Log.d("ERROR", it.errorBody().toString())
                    Toast.makeText(this, it.errorBody().toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}