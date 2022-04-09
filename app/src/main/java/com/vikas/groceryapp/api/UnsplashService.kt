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

package com.vikas.groceryapp.api

import com.vikas.groceryapp.data.GroceryModelResponse
import com.vikas.groceryapp.utilities.API_KEY
import com.vikas.groceryapp.utilities.RESOURCE_INDEX
import com.vikas.groceryapp.utilities.RESPONSE_TYPE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Used to connect to the Unsplash API to fetch photos
 */
interface UnsplashService {

    @GET("resource/{resValue}")
    suspend fun getDog(
        @Path("resValue") resValue: String = RESOURCE_INDEX,
        @Query("filters") filters: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("api-key") query: String = API_KEY,
        @Query("format") format: String = RESPONSE_TYPE
    ): Response<GroceryModelResponse>
}
