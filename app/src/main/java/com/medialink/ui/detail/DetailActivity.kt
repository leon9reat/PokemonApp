package com.medialink.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.medialink.pokemonapp.R
import com.medialink.pokemonapp.databinding.ActivityDetailBinding
import com.medialink.pokemonapp.utils.JsonHelper
import com.medialink.pokemonapp.utils.ViewModelFactory

class DetailActivity : AppCompatActivity(), IDetailActivity {

    private lateinit var mBinding: ActivityDetailBinding
    private lateinit var mViewModel: DetailViewModel
    private val mAbilitiesAdapter = AbilitiesAdapter(this)
    private val mFormAdapter = FormsAdapter(this)
    private val mHeldAdapter = HeldItemsAdapter(this)
    private val mMoveAdapter = MovesAdapter(this)
    private val mStatAdapter = StatsAdapter(this)
    private val mTypeAdapter = TypesAdapter(this)

    companion object {
        const val PARCEL_DETAIL = "PARCEL_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupViewModel()
        setupUI()
        setupObserver()

        val item: com.medialink.pokemonapp.model.Result? = intent.getParcelableExtra(PARCEL_DETAIL)

        if (item != null) {
            item.name?.let { mViewModel.fetchPokemon(it) }
        }

        supportActionBar?.hide()
    }

    private fun setupObserver() {
        mViewModel.pokemon.observe(this, {
            if (it != null) {
                mBinding.tvName.text = it.name
                mBinding.tvWeight.text = it.weight.toString()
                mBinding.tvHeight.text = it.height.toString()
                mBinding.tvBaseExperience.text = it.baseExperience.toString()
                mBinding.tvSpecies.text = it.species?.name

                Glide.with(this)
                    .load(it.sprites?.other?.officialArtwork?.frontDefault)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_cloud_download_24))
                    .into(mBinding.ivPoster)

                Glide.with(this)
                    .load(it.sprites?.backDefault)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivBackDefault)
                Glide.with(this)
                    .load(it.sprites?.backFemale)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivBackFemale)
                Glide.with(this)
                    .load(it.sprites?.backShiny)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivBackShiny)
                Glide.with(this)
                    .load(it.sprites?.backShinyFemale)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivBackShinyFemale)

                Glide.with(this)
                    .load(it.sprites?.frontDefault)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivFrontDefault)
                Glide.with(this)
                    .load(it.sprites?.frontFemale)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivFrontFemale)
                Glide.with(this)
                    .load(it.sprites?.frontShiny)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivFrontShiny)
                Glide.with(this)
                    .load(it.sprites?.frontShinyFemale)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_no_image))
                    .into(mBinding.ivFrontShinyFemale)


                it.abilities?.let { listAbilitiy ->
                    mAbilitiesAdapter.setDataAbility(listAbilitiy)
                    mAbilitiesAdapter.notifyDataSetChanged()
                }
                it.forms?.let { listForm ->
                    mFormAdapter.setDataForm(listForm)
                    mFormAdapter.notifyDataSetChanged()
                }
                it.heldItems?.let { listHeld ->
                    mHeldAdapter.setDataHeld(listHeld)
                    mHeldAdapter.notifyDataSetChanged()
                }
                it.moves?.let { listMove ->
                    mMoveAdapter.setDataMove(listMove)
                    mMoveAdapter.notifyDataSetChanged()
                }
                it.stats?.let { listStat ->
                    mStatAdapter.setDataStat(listStat)
                    mStatAdapter.notifyDataSetChanged()
                }
                it.types?.let { listType ->
                    mTypeAdapter.setDataType(listType)
                    mTypeAdapter.notifyDataSetChanged()
                }
            }
        })
        mViewModel.error.observe(this, {
            if (it != null) {
                Snackbar.make(mBinding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupUI() {
        with(mBinding.rvAbilities) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mAbilitiesAdapter
        }

        with(mBinding.rvForms) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mFormAdapter
        }

        with(mBinding.rvHeldItems) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mHeldAdapter
        }

        with(mBinding.rvMoves) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mMoveAdapter
        }

        with(mBinding.rvStats) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mStatAdapter
        }

        with(mBinding.rvTypes) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mTypeAdapter
        }


    }

    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this, ViewModelFactory(JsonHelper(this)))
            .get(DetailViewModel::class.java)
    }
}