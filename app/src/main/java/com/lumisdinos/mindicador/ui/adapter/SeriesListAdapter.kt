package com.lumisdinos.mindicador.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lumisdinos.mindicador.common.util.convertDateFormat
import com.lumisdinos.mindicador.common.util.isClickedShort
import com.lumisdinos.mindicador.common.util.numbToStr
import com.lumisdinos.mindicador.databinding.ItemSerieBinding
import com.lumisdinos.mindicador.ui.model.SerieView

class SeriesListAdapter(private val itemClickListener: OnSerieClickListener) :
    ListAdapter<SerieView, SeriesListAdapter.ViewHolder>(SerieDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemSerieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SerieView, clickListener: OnSerieClickListener) {
            binding.dateTv.text = convertDateFormat(item.fecha)
            binding.priceTv.text = numbToStr(item.valor)

            binding.root.setOnClickListener {
                if (isClickedShort()) return@setOnClickListener
                clickListener.onItemClicked(item.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSerieBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


interface OnSerieClickListener {

    fun onItemClicked(id: Int?)

}


class SerieDiffCallback : DiffUtil.ItemCallback<SerieView>() {
    override fun areItemsTheSame(oldItem: SerieView, newItem: SerieView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SerieView, newItem: SerieView): Boolean {
        return oldItem == newItem
    }
}