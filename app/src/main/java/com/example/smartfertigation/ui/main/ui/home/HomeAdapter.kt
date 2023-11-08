package com.example.smartfertigation.ui.main.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfertigation.R
import com.example.smartfertigation.databinding.CardViewNutrientesItemBinding
import com.example.smartfertigation.model.nutrients

class HomeAdapter(
    private val homeList :MutableList<nutrients>,
    private val onItemClicked: (nutrients) -> Unit,
    //private val onItemLongClicked:
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_nutrientes_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int = homeList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val nutriente = homeList[position]
        holder.bind(nutriente)
        holder.itemView.setOnClickListener{onItemClicked(nutriente)}
    }

    fun appendItems(newList: MutableList<nutrients>){
        homeList.clear()
        homeList.addAll(newList)
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewNutrientesItemBinding.bind(itemView)

        fun bind(nutrients: nutrients){
            with(binding){
                nNo3TextView.text = nutrients.n_no3.toString()
                nNh4TextView.text = nutrients.n_nh4.toString()
                pTextView.text = nutrients.p.toString()
                kTextView.text = nutrients.k.toString()
                caTextView.text = nutrients.ca.toString()
                mgTextView.text = nutrients.mg.toString()
                sTextView.text = nutrients.s.toString()
            }
        }

    }


}