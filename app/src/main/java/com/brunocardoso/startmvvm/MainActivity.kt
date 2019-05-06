package com.brunocardoso.startmvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.brunocardoso.startmvvm.adapters.RecyclerAdapter
import com.brunocardoso.startmvvm.models.NicePlace
import com.brunocardoso.startmvvm.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var mFab: FloatingActionButton
    private lateinit var mAdapter: RecyclerAdapter

    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // ViewModel fornece para a view
        // dados observaveis
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mViewModel.init()

        mViewModel.getNicePlaces()?.let {
            it.observe(this, Observer {
                mAdapter.notifyDataSetChanged()
            })
        }

        mViewModel.getUpdating()?.let {
            it.observe(this, Observer {

                if (it == true){
                    showProgressBar()
                }else{
                    hideProgressBar()
                    recyclerView.smoothScrollToPosition( (mViewModel.getNicePlaces()?.value?.size ?: 0) - 1)
                }

            })
        }

        fab.setOnClickListener {
            mViewModel.addNewNicePlace(
                NicePlace("Teste", "")
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = RecyclerAdapter(this, mViewModel.getNicePlaces()?.value!! )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun showProgressBar(){ progress_bar.visibility = View.VISIBLE }

    private fun hideProgressBar(){ progress_bar.visibility = View.GONE }


}

