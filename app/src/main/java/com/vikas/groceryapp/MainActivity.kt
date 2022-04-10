package com.vikas.groceryapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vikas.groceryapp.adapters.GroceryListAdapter
import com.vikas.groceryapp.databinding.ActivityMainBinding
import com.vikas.groceryapp.viewmodels.GroceryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GroceryListViewModel by viewModels()
    private var searchJob: Job? = null
    private val adapter = GroceryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.groceryItemsRv.adapter = adapter

        search("")
    }

    private fun search(query: String) {
        searchJob?.cancel()
        Log.d("TAG", "Inside search")

        searchJob = lifecycleScope.launch {
            viewModel.getGroceries(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}