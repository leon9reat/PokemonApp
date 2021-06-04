package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.StatItemBinding
import com.medialink.pokemonapp.model.Stat

class StatsAdapter(private val mCallback: IDetailActivity) :
    RecyclerView.Adapter<StatsAdapter.StatViewHolder>() {

    private val mListStat: ArrayList<Stat> = arrayListOf()

    fun setDataStat(listData: List<Stat>) {
        this.mListStat.clear()
        this.mListStat.addAll(listData)
    }

    inner class StatViewHolder(private val mBinding: StatItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(stat: Stat) {
            with(mBinding) {
                tvStatName.text = stat.stat?.name
                tvBaseStat.text = stat.baseStat.toString()
                tvStatEffort.text = stat.effort.toString()
                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = StatItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val data = mListStat[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return mListStat.size
    }
}