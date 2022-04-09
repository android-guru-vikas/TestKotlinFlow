package com.vikas.groceryapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vikas.groceryapp.adapters.GardenPlantingAdapter
import com.vikas.groceryapp.databinding.ActivityMainBinding
import com.vikas.groceryapp.utilities.NetworkResult
import com.vikas.groceryapp.viewmodels.GardenPlantingListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GardenPlantingListViewModel by viewModels()
    private var searchJob: Job? = null
    private val adapter = GardenPlantingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.groceryItemsRv.adapter = adapter

        search("")
    }

    private fun search(query: String) {
        binding.pbDog.visibility = View.VISIBLE
        viewModel.fetchDogResponse()
        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {

                    }
                    binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    Log.d("TAG","Inside onError : ${response.message}")
                    binding.pbDog.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    binding.pbDog.visibility = View.VISIBLE
                }
            }
        }

    }
}