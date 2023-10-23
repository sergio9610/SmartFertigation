package com.example.smartfertigation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smartfertigation.R
import com.example.smartfertigation.databinding.ActivityLoginBinding
import com.example.smartfertigation.ui.main.MainActivity
import com.example.smartfertigation.ui.register.RegisterActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        //loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val view = loginBinding.root
        setContentView(view)


        loginViewModel.errorMessage.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        loginBinding.loginIniciarSesionButton.setOnClickListener {
            val email = loginBinding.loginEmailTextInputEditText.text.toString()
            val password = loginBinding.loginPasswordTextInputEditText.text.toString()
            val flag = loginViewModel.validateFields(email, password)
            if(flag){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        loginBinding.loginRegistrarseButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
