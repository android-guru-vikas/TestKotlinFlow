/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vikas.groceryapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vikas.groceryapp.data.GroceryRepository
import com.vikas.groceryapp.data.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GroceryListViewModel @Inject internal constructor(
    private val repository: GroceryRepository
) : ViewModel() {
    private var currentQueryValue: String? = null

    suspend fun getGroceries(queryString: String): Flow<PagingData<Record>> {
        var model: String? = ""
        Log.d("TAG", "Inside getGrocery : " + repository.getGroceryItems(queryString).toString())
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Record>> =
            repository.getGroceryItems(queryString).cachedIn(viewModelScope)

            Log.d("TAG", "Inside list view model 1 ${newResult.first().map { 
              model =  it.commodity
            }}")

        Log.d("TAG", "Inside model : " + model)

        return newResult
    }

}
