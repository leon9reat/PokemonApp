package com.medialink.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medialink.pokemonapp.databinding.AbilityItemBinding
import com.medialink.pokemonapp.model.Ability

class AbilitiesAdapter(private val mCallback: IDetailActivity):
    RecyclerView.Adapter<AbilitiesAdapter.AbilityViewHolder>() {

    private val mListAbility: ArrayList<Ability> = arrayListOf()

    fun setDataAbility(listData: List<Ability>) {
        this.mListAbility.clear()
        this.mListAbility.addAll(listData)
    }

    inner class AbilityViewHolder(private val mBinding: AbilityItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(ability: Ability) {
            with(mBinding) {
                tvAbilityName.text = ability.ability?.name
                tvAbilityHidden.text = ability.isHidden.toString()
                tvAbilitySlot.text = ability.slot.toString()

                /*itemView.setOnClickListener {
                    mCallback.onItemClick(detail)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbilitiesAdapter.AbilityViewHolder {
        val binding = AbilityItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AbilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilitiesAdapter.AbilityViewHolder, position: Int) {
        val data = mListAbility[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mListAbility.size

}