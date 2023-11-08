package com.example.smartfertigation.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfertigation.databinding.FragmentHomeBinding
import com.example.smartfertigation.model.nutrients

@UnstableApi class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private var homeList= mutableListOf<nutrients>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = homeBinding.root

        homeViewModel.loadNutrients()

        homeViewModel.errorMsg.observe(viewLifecycleOwner){ msg ->
            showErroMsg(msg)
        }

        homeViewModel.homeList.observe(viewLifecycleOwner){ homeList ->
            homeAdapter.appendItems(homeList)
        }

        homeAdapter = HomeAdapter(homeList, onItemClicked = { onHomeItemClicked(it) })

        homeBinding.homeRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = homeAdapter
            setHasFixedSize(false)
        }

        homeBinding.newCalculationButton.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNewCalculationFragment())
        }

        return view
    }

    private fun showErroMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onHomeItemClicked(home: nutrients) {

    }

}
