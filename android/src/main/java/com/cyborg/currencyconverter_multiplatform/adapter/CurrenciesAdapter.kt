package com.cyborg.currencyconverter_multiplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cyborg.common.data.model.CurrencyModel
import com.cyborg.currencyconverter_multiplatform.R
import kotlinx.android.synthetic.main.layout_currency_item.view.*
import java.util.*

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
        holder.currencyDesc.text = Currency.getInstance(currency.currencyName).displayName

        val drawable = holder.itemView.context.resources.getIdentifier(
            "flag_" + currency.currencyName.toLowerCase(Locale.ENGLISH),
            "drawable", holder.itemView.context.packageName
        )

        Glide.with(holder.itemView.context).load(drawable)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.currencyIcon)
    }

    class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val currencyName: TextView = itemView.findViewById(R.id.currencyName)
        val currencyValue: TextView = itemView.findViewById(R.id.currencyValue)
        val currencyDesc: TextView = itemView.findViewById(R.id.currencyDesc)
        val currencyIcon: AppCompatImageView = itemView.findViewById(R.id.currencyIcon)
    }
}