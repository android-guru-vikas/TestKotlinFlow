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

package com.vikas.groceryapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.groceryapp.data.Record
import com.vikas.groceryapp.databinding.LayoutGroceryItemsBinding

class GardenPlantingAdapter :
    PagingDataAdapter<Record, GardenPlantingAdapter.GalleryViewHolder>(GardenPlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            LayoutGroceryItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Log.d("TAG", "Inside onBindViewHolder : $position")
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class GalleryViewHolder(
        private val binding: LayoutGroceryItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Record) {
            Log.d("TAG", "Inside bind : ${item.commodity}")
            binding.apply {
                grocery = item
                priceTv.text = grocery?.modal_price
                executePendingBindings()
            }
        }
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<Record>() {

    override fun areItemsTheSame(
        oldItem: Record,
        newItem: Record
    ): Boolean {
        return oldItem.commodity == newItem.commodity
    }

    override fun areContentsTheSame(
        oldItem: Record,
        newItem: Record
    ): Boolean {
        return oldItem == newItem
    }
}
