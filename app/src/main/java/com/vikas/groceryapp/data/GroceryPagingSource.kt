/*
 * Copyright 2020 Google LLC
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

package com.vikas.groceryapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vikas.groceryapp.api.APIService
import com.vikas.groceryapp.utilities.GROCERY_STARTING_PAGE_INDEX
import com.vikas.groceryapp.utilities.RESOURCE_INDEX
import com.vikas.groceryapp.utilities.RESPONSE_ITEM_LIMIT

class GroceryPagingSource(
    private val service: APIService,
    private val query: String
) : PagingSource<Int, Record>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Record> {
        val page = params.key ?: GROCERY_STARTING_PAGE_INDEX
        return try {
            val response = service.getGrocery(RESOURCE_INDEX, query, page, RESPONSE_ITEM_LIMIT)
            val grocery = response.records
            Log.d("TAG", "Inside load completed : ${grocery.toString()}")

            LoadResult.Page(
                data = grocery,
                prevKey = null,
                nextKey = page + 10
            )
        } catch (exception: Exception) {
            Log.d("TAG", "Inside load error : $exception")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Record>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
