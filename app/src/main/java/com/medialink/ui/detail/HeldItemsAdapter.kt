package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.HeldItemBinding
import com.medialink.pokemonapp.model.HeldItem

class HeldItemsAdapter(private val mCallback: IDetailActivity):
    RecyclerView.Adapter<HeldItemsAdapter.HeldViewHolder>() {

    private val mListHeld: ArrayList<HeldItem> = arrayListOf()

    fun setDataHeld(listData: List<HeldItem>) {
        this.mListHeld.clear()
        this.mListHeld.addAll(listData)
    }

    inner class HeldViewHolder(private val mBinding: HeldItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(held: HeldItem) {
            with(mBinding) {
                tvHeldItem.text = held.item?.name
                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeldItemsAdapter.HeldViewHolder {
        val binding = HeldItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HeldViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeldItemsAdapter.HeldViewHolder, position: Int) {
        val data = mListHeld[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mListHeld.size
}