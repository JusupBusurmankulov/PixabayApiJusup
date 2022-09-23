package com.example.pixabayapijusup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabayapijusup.Retrofit.ImageModel
import com.example.pixabayapijusup.databinding.ItemImageBinding

class ImageAdapter(private val list: List<ImageModel>):RecyclerView.Adapter<ImageAdapter.ImageViewHolder>( ) {
    class ImageViewHolder(private val binding: ItemImageBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.itemImage.load(model.largeImageURL)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
        notifyDataSetChanged()
    }


}