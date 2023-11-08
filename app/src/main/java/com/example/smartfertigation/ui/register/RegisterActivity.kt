package com.example.smartfertigation.ui.register

import com.example.smartfertigation.databinding.ActivityRegisterBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi

@UnstableApi class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        val view = registerBinding.root
        setContentView(view)

       registerViewModel.errorMsg.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        registerViewModel.registerSuccess.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }

        registerBinding.registerRegistrateButton.setOnClickListener {
            val name = registerBinding.registerNombreTextInputEditText.text.toString()
            val email: String = registerBinding.registerEmailTextInputEditText.text.toString()
            val password = registerBinding.registerPasswordTextInputEditText.text.toString()
            val repeatPassword = registerBinding.registerConfirmarPasswordTextInputEditText.text.toString()
            val cel = registerBinding.registerCelTextInputEditText.text.toString()
            val company = registerBinding.registerEmpresaTextInputEditText.text.toString()
            val city = registerBinding.registerCiudadTextInputEditText.text.toString()

            registerViewModel.validateFields(name, email, password, repeatPassword, cel, company, city)

        }

    }
    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
