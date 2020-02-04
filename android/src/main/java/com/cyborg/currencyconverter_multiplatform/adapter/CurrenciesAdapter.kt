package com.cyborg.currencyconverter_multiplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.common.data.model.CurrencyModel
import com.cyborg.currencyconverter_multiplatform.R

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {


    private var mCurrenciesList = mutableListOf<CurrencyModel>()

    fun setCurrencyList(list: List<CurrencyModel>) {

        mCurrenciesList.clear()
        mCurrenciesList.addAll(list)

        mCurrenciesList.sortWith(compareBy {
            it.currencyName
        })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_currency_item, parent, false
            )
        )

    override fun getItemCount(): Int = mCurrenciesList.size

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {

        val currency = mCurrenciesList[position]

        holder.currencyName.text = currency.currencyName
        holder.currencyValue.text = currency.currencyValue.toString()
    }

    class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val currencyName: TextView = itemView.findViewById(R.id.currencyName)
        val currencyValue: TextView = itemView.findViewById(R.id.currencyValue)
    }
}