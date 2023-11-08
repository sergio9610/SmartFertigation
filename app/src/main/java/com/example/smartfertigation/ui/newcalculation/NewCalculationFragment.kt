package com.example.smartfertigation.ui.newcalculation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartfertigation.databinding.FragmentNewCalculationBinding

class
NewCalculationFragment : Fragment() {

    private lateinit var newCalculationViewModel: NewCalculationViewModel
    private lateinit var newCalculationBinding: FragmentNewCalculationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        newCalculationBinding = FragmentNewCalculationBinding.inflate(inflater, container, false)
        newCalculationViewModel = ViewModelProvider(this)[NewCalculationViewModel::class.java]
        val view = newCalculationBinding.root

        newCalculationViewModel.errorMsg.observe(viewLifecycleOwner){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        with(newCalculationBinding){
            calcularNutrientesButton.setOnClickListener {
                newCalculationViewModel.validateFields(
                    nNo3NutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    nNh4NutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    pNutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    kNutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    caNutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    mgNutrientesEditTextNumberDecimal.text.toString().toFloat(),
                    sNutrientesEditTextNumberDecimal.text.toString().toFloat()
                )
            }
        }

        return view
    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

}