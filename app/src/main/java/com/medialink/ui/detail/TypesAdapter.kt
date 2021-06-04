package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.TypeItemBinding
import com.medialink.pokemonapp.model.Type

class TypesAdapter(private val mCallback: IDetailActivity) :
    RecyclerView.Adapter<TypesAdapter.TypeViewHolder>() {

    private val mListType: ArrayList<Type> = arrayListOf()

    fun setDataType(listData: List<Type>) {
        this.mListType.clear()
        this.mListType.addAll(listData)
    }

    inner class TypeViewHolder(private val mBinding: TypeItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(type: Type) {
            with(mBinding) {
                tvTypeName.text = type.type?.name
                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val binding = TypeItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val data = mListType[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return mListType.size
    }

}