package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.FormItemBinding
import com.medialink.pokemonapp.model.Form

class FormsAdapter(private val mCallback: IDetailActivity):
    RecyclerView.Adapter<FormsAdapter.FormViewHolder>() {

    private val mListForm: ArrayList<Form> = arrayListOf()

    fun setDataForm(listData: List<Form>) {
        this.mListForm.clear()
        this.mListForm.addAll(listData)
    }

    inner class FormViewHolder(private val mBinding: FormItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(form: Form) {
            with(mBinding) {
                tvForm.text = form.name
                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormsAdapter.FormViewHolder {
        val binding = FormItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FormViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FormsAdapter.FormViewHolder, position: Int) {
        val data = mListForm[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mListForm.size
}