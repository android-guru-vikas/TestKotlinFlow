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
import com.vikas.groceryapp.api.UnsplashService
import com.vikas.groceryapp.utilities.RESOURCE_INDEX
import javax.inject.Inject

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource  @Inject constructor(private val dogService: UnsplashService) {

    suspend fun getDog() = dogService.getDog(RESOURCE_INDEX, "", 10, 10)

}
