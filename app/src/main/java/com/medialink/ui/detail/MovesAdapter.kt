package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.MoveItemBinding
import com.medialink.pokemonapp.model.Move

class MovesAdapter(private val mCallback: IDetailActivity) :
    RecyclerView.Adapter<MovesAdapter.MoveViewHolder>() {

    private val mListMove: ArrayList<Move> = arrayListOf()

    fun setDataMove(listData: List<Move>) {
        this.mListMove.clear()
        this.mListMove.addAll(listData)
    }

    inner class MoveViewHolder(private val mBinding: MoveItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(move: Move) {
            with(mBinding) {
                tvMove.text = move.move?.name
                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val binding = MoveItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val data = mListMove[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return mListMove.size
    }
}