package com.hfad.delivery.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hfad.delivery.R
import com.hfad.delivery.SharedPreference
import com.hfad.delivery.repository.Repository
import com.hfad.delivery.viewmodel.UserTokenVMFactory
import com.hfad.delivery.viewmodel.UserTokenViewModel
import kotlinx.android.synthetic.main.activity_promocodes.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PromocodesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Delivery)
        setContentView(R.layout.activity_promocodes)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar!!.title = "Promo codes"


        val repository = Repository()
        val viewModelFactory = UserTokenVMFactory(repository)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(UserTokenViewModel::class.java)

        val headers: MutableMap<String, String> = HashMap()
        val sharedPreference: SharedPreference = SharedPreference(this)
        val token: String? = sharedPreference.getValueString("token")
        Log.d("token", token)
        headers["Authorization"] = "Bearer $token"
        getPromoCode(viewModel, headers)

        share.setOnClickListener {
            val message: String = "https://189delivery.az/promocode?code=" + shareable_promo.text.toString()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(shareIntent, "Share promo-code via:"))
        }

        addBtn.setOnClickListener {
            val code = new_promo.text.toString()
            Log.d("promo", new_promo.text.toString())

            viewModel.addPromoCode(code, headers)
            getPromoCode(viewModel, headers)
        }


    }

    fun getPromoCode(viewModel: UserTokenViewModel, headers: MutableMap<String, String>) {
        viewModel.getCustomerInfo(headers)

        viewModel.customerInfoResponse.observe(this, {
            if (it.isSuccessful) {
                shareable_promo.text = it.body()?.generatedPromoCode?.code
                if (it.body()?.currentPromoCode != null) {
                    your_promo.text = it.body()?.currentPromoCode?.code
                    your_promo.visibility = View.VISIBLE
                    textView6.visibility = View.VISIBLE
                }
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