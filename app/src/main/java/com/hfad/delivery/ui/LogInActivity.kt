package com.hfad.delivery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hfad.delivery.R
import com.hfad.delivery.model.phoneNumber
import com.hfad.delivery.repository.Repository
import com.hfad.delivery.viewmodel.UserTokenVMFactory
import com.hfad.delivery.viewmodel.UserTokenViewModel
import io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_log_in.nextBtn
import kotlinx.android.synthetic.main.activity_log_in.phoneNumber
import kotlinx.android.synthetic.main.activity_log_in2.*

class LogInActivity : AppCompatActivity() {

    private lateinit var viewModel: UserTokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Delivery)
        setContentView(R.layout.activity_log_in)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar!!.title = "Log in"

        ccp.setCountryForPhoneCode(994)

        val repository = Repository()
        val viewModelFactory = UserTokenVMFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserTokenViewModel::class.java)

        nextBtn.setOnClickListener {
            val num = ccp.selectedCountryCodeWithPlus + phoneNumber.text.toString()

            viewModel.getOtp(phoneNumber(num))
            val intent = Intent(this@LogInActivity, LogIn2Activity::class.java)
            intent.putExtra("phoneNumber", num)
            startActivity(intent);


            viewModel.emptyResponse.observe(this, Observer {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Succesfully blabla", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("ERROR", it.errorBody().toString ())
                    Toast.makeText(this, it.errorBody()?.string(), Toast.LENGTH_SHORT).show()
                }
            })
          }
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}