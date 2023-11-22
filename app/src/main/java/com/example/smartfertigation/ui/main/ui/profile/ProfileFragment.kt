package com.example.smartfertigation.ui.main.ui.profile

import ProfileViewModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.databinding.FragmentProfileBinding
import com.example.smartfertigation.ui.login.LoginActivity

@UnstableApi class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        profileViewModel.errorMsg.observe(viewLifecycleOwner){msg ->
            showErrorMsg(msg)
        }

        profileViewModel.userLoggedOut.observe(viewLifecycleOwner){
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // Configurar un clic en el botón para cerrar sesión
        binding.buttonCerrarSesion.setOnClickListener {
            profileViewModel.singOut()
        }
        return root
    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}