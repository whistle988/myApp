package com.example.quwiclient.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quwiclient.*
import com.example.quwiclient.model.ApiClient
import com.example.quwiclient.model.LoginRequest
import com.example.quwiclient.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        viewInitializations()

        login.setOnClickListener {
            Toast.makeText(this, "Click event works.", Toast.LENGTH_SHORT).show();
            val email: String = et_email.text.toString()
            val password: String = et_password.text.toString()

            if (validateInput(email, password)) {
                signin(email, password)
            }

            signin(email, password)
        }
    }

    fun signin(email: String, password: String) {

        apiClient.getApiService(this).signin(LoginRequest(email, password))
            .enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()

                    if (loginResponse?.token != null) {
                        sessionManager.saveAuthToken(loginResponse.token)
                        Log.d("TAG", "Token = ${loginResponse.token}");
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed#1!", Toast.LENGTH_SHORT).show()
                    }

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)

                    startActivity(intent)
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed#2!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun viewInitializations() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    fun validateInput(email: String?, password: String?): Boolean {
        if (email == null || email.trim { it <= ' ' }.length == 0) {
            et_email.error = "Please Enter Email"
            return false
        }
        if (password == null || password.trim { it <= ' ' }.length == 0) {
            et_password.error = "Please Enter Password"
            return false
        }

        if (!isEmailValid(et_email.text.toString())) {
            et_email.error = "Please Enter Valid Email"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}