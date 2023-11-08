package com.example.smartfertigation.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.smartfertigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = homeBinding.root

        homeBinding.newCalculationButton.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNewCalculationFragment())
        }

        return view
    }

}
