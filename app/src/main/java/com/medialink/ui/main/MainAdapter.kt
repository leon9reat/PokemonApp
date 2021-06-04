package com.medialink.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.medialink.pokemonapp.R
import com.medialink.pokemonapp.databinding.ListItemBinding
import com.medialink.pokemonapp.model.Result

class MainAdapter(private val mCallback: IMainActivity) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private val mListPokemon: ArrayList<Result> = arrayListOf()
    private val mListImage: ArrayList<String> = arrayListOf()

    fun setDataPokemon(listData: List<Result>) {
        this.mListPokemon.clear()
        this.mListPokemon.addAll(listData)
    }

    fun setDataImage(listImage: List<String>) {
        this.mListImage.clear()
        this.mListImage.addAll(listImage)
    }

    inner class MyViewHolder(private val mBinding: ListItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(detail: Result, image: String) {


            with(mBinding) {
                tvName.text = detail.name

                Glide.with(itemView.context)
                    .load(image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_cloud_download_24))
                    .into(ivPokemon)

                itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        val data = mListPokemon[position]
        val image = mListImage[position]
        holder.bind(data, image)
    }

    override fun getItemCount(): Int = mListPokemon.size

}