package com.example.smartfertigation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.R
import com.example.smartfertigation.databinding.ActivityLoginBinding
import com.example.smartfertigation.ui.main.ui.MainActivity
import com.example.smartfertigation.ui.register.RegisterActivity

@UnstableApi class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_SmartFertigation)
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val view = loginBinding.root
        setContentView(view)

        loginViewModel.errorMsg.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        loginViewModel.registerSuccess.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            //intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            // TODO cambiar finish por flag en intent
            startActivity(intent)
            finish()
        }

        loginBinding.loginRegistrarseButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBinding.loginIniciarSesionButton.setOnClickListener {
            val email = loginBinding.loginEmailTextInputEditText.text.toString()
            val password = loginBinding.loginPasswordTextInputEditText.text.toString()
            loginViewModel.validateFields(email, password)

        }

    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
