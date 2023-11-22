package com.example.smartfertigation.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.R
import com.example.smartfertigation.databinding.ActivityLoginBinding
import com.example.smartfertigation.ui.main.ui.MainActivity
import com.example.smartfertigation.ui.register.RegisterActivity

@UnstableApi class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_SmartFertigation)
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val view = loginBinding.root
        setContentView(view)
        //setupViewModelObservers()

        loginViewModel.errorMsg.observe(this){msg ->
            showErrorMsg(msg)
        }

        loginViewModel.registerSuccess.observe(this){
            val intent = Intent(this,MainActivity::class.java)
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

        // Obtener SharedPreferences
        /*sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Verificar si el usuario ya ha iniciado sesión previamente
        val isUserLoggedIn = sharedPreferences.getBoolean("isUserLoggedIn", false)
        if (isUserLoggedIn) {
            // Si el usuario ya ha iniciado sesión, ir directamente a la MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/

    }

    /*private fun setupViewModelObservers() {
        loginViewModel.errorMsg.observe(this) { msg ->
            showErrorMsg(msg)
        }

        loginViewModel.registerSuccess.observe(this) {
            // Guardar el estado de inicio de sesión cuando el usuario inicia sesión exitosamente
            sharedPreferences.edit().putBoolean("isUserLoggedIn", true).apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }*/
    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
