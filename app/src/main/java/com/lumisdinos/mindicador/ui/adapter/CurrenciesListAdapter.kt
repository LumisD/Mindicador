package com.lumisdinos.mindicador.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lumisdinos.mindicador.common.util.isClickedShort
import com.lumisdinos.mindicador.common.util.numbToStr
import com.lumisdinos.mindicador.databinding.ItemCurrencyBinding
import com.lumisdinos.mindicador.ui.model.CurrencyView
import java.util.*

class CurrenciesListAdapter(private val itemClickListener: OnCurrencyClickListener) :
    ListAdapter<CurrencyView, CurrenciesListAdapter.ViewHolder>(CurrencyDiffCallback()) {

    private var unfilteredlist = listOf<CurrencyView>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyView, clickListener: OnCurrencyClickListener) {
            binding.nameTv.text = item.nombre
            binding.unitTv.text = item.unidadMedida
            binding.priceTv.text = numbToStr(item.valor)

            binding.root.setOnClickListener {
                if (isClickedShort()) return@setOnClickListener
                clickListener.onItemClicked(item.codigo)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun modifyList(list : List<CurrencyView>) {
        unfilteredlist = list
        submitList(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<CurrencyView>()

        if(!query.isNullOrEmpty()) {
            list.addAll(unfilteredlist.filter {
                it.codigo?.toLowerCase(Locale.getDefault())?.contains(query.toString().toLowerCase(Locale.getDefault())) ?: false ||
                        it.nombre?.toLowerCase(Locale.getDefault())?.contains(query.toString().toLowerCase(Locale.getDefault())) ?: false
            })
        } else {
            list.addAll(unfilteredlist)
        }

        submitList(list)
    }
}


interface OnCurrencyClickListener {

    fun onItemClicked(codigo: String?)

}


class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyView>() {
    override fun areItemsTheSame(oldItem: CurrencyView, newItem: CurrencyView): Boolean {
        return oldItem.codigo == newItem.codigo
    }

    override fun areContentsTheSame(oldItem: CurrencyView, newItem: CurrencyView): Boolean {
        return oldItem == newItem
    }
}