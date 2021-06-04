package com.medialink.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medialink.pokemonapp.R
import com.medialink.pokemonapp.databinding.ActivityMainBinding
import com.medialink.pokemonapp.model.Result
import com.medialink.pokemonapp.utils.JsonHelper
import com.medialink.pokemonapp.utils.ViewModelFactory
import com.medialink.ui.about.AboutActivity
import com.medialink.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private val mAdapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupViewModel()
        setupUI()
        setupObserver()

        mViewModel.fetchListPokemon()
    }

    private fun setupObserver() {
        mViewModel.loading.observe(this, {
            mBinding.progressMain.visibility = if (it) View.VISIBLE else View.GONE
        })
        mViewModel.listPokemon.observe(this, {
            if (it.results != null) {
              mAdapter.setDataPokemon(it.results)
              mAdapter.notifyDataSetChanged()
            }
        })
        mViewModel.listImage.observe(this, {
            if (it != null) {
                mAdapter.setDataImage(it)
                mAdapter.notifyDataSetChanged()
            }
        })
        mViewModel.error.observe(this, {
            if (it != null) {
                Snackbar.make(mBinding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupUI() {
        with(mBinding.myRecycler) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this, ViewModelFactory(JsonHelper(this)))
            .get(MainViewModel::class.java)
    }

    override fun onItemClick(result: Result) {
        Log.d("debug", "onItemClick :$result")
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra(DetailActivity.PARCEL_DETAIL, result)
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about_me -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}