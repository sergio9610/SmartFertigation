package com.example.smartfertigation.ui.register


import com.example.smartfertigation.R
import com.example.smartfertigation.databinding.ActivityRegisterBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smartfertigation.ui.main.MainActivity


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        val view = registerBinding.root
        setContentView(view)

        registerViewModel.errorMessage.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        registerBinding.registerRegistrateButton.setOnClickListener {
            val name = registerBinding.registerNombreTextInputEditText.text.toString()
            val email = registerBinding.registerEmailTextInputEditText.text.toString()
            val password = registerBinding.registerPasswordTextInputEditText.text.toString()
            val repeatPassword = registerBinding.registerConfirmarPasswordTextInputEditText.text.toString()
            val cel = registerBinding.registerCelTextInputEditText.text.toString()
        }

    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
