package com.calcs.currencyconverter.ui.currencyRates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calcs.currencyconverter.databinding.CurrencyRateRowBinding
import com.calcs.currencyconverter.domain.model.viewdatamodel.CurrencyRate

class CurrencyRatesAdapter(
    private val currencyRatesList: List<CurrencyRate>,
    private val onItemClick: (CurrencyRate) -> Unit
) : RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            CurrencyRateRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = currencyRatesList.size

    private fun getItem(position: Int): CurrencyRate = currencyRatesList[position]

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.currencyRow.currencyRate = getItem(position)
    }

    inner class CurrencyViewHolder(val currencyRow: CurrencyRateRowBinding) :
        RecyclerView.ViewHolder(currencyRow.root) {

        init {
            currencyRow.root.setOnClickListener {
                onItemClick(getItem(bindingAdapterPosition))
            }
        }

    }
}