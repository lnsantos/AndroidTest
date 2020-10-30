package com.lnsantos.stefaniniandroidtest.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lnsantos.stefaniniandroidtest.R
import com.lnsantos.stefaniniandroidtest.databinding.ActivityMainBinding
import com.lnsantos.stefaniniandroidtest.main.adapter.ImageAdapter
import com.lnsantos.stefaniniandroidtest.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(processContentView())

        setupView()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchImages()
    }

    private fun setupViewModel() {
        viewModel.liveDataImages.observe(this){

            if (it.isEmpty()){
                Toast.makeText(this, resources.getString(R.string.images_not_found), Toast.LENGTH_LONG).show()
                return@observe
            }

            adapter.updateItems(it)
            binding.numberIndicator.text = "${it.size}"
        }

        viewModel.liveDataLoading.observe(this){
            val visibility = if (it){
                View.VISIBLE
            }else{
                View.GONE
            }

            with(binding){
                progressCircular.visibility = visibility
                progressView.visibility = visibility
            }
        }

    }

    private fun setupView(){
        adapter = ImageAdapter()

        binding.recycler.layoutManager = GridLayoutManager(this,  4, RecyclerView.VERTICAL, false)
        binding.recycler.adapter = adapter
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if (!recyclerView.canScrollVertically(1)){
                    viewModel.fetchImages()
                }
            }

        })
    }

    private fun processContentView() = ActivityMainBinding
        .inflate(layoutInflater).run {
            binding = this
            root
    }

}