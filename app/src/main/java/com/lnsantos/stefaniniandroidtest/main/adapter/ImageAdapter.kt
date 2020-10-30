package com.lnsantos.stefaniniandroidtest.main.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lnsantos.stefaniniandroidtest.R
import com.lnsantos.stefaniniandroidtest.core.model.Image
import com.lnsantos.stefaniniandroidtest.databinding.ItemCardImageAndVideoBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){
    private val _items: ArrayList<Image> = arrayListOf()


    fun updateItems(items: List<Image>){
        _items.clear()
        _items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCardImageAndVideoBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(_items[position])
    }

    override fun getItemCount(): Int = _items.size

    inner class ImageViewHolder(private val binding : ItemCardImageAndVideoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(item: Image){
            binding.contentImage.visibility = View.GONE
            binding.contentVideo.visibility = View.GONE

            if (item.urlMp4 != null){
                binding.contentVideo.visibility = View.VISIBLE

                val uri = Uri.parse(item.urlMp4)
                binding.contentVideo.setOnPreparedListener {
                    it.setVolume(0F,0F)
                }

                binding.contentVideo.setVideoURI(uri)
                binding.contentVideo.start()
            } else{
                binding.contentImage.visibility = View.VISIBLE
                Glide
                    .with(binding.root.context)
                    .load(item.urlImage)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.background_white)
                    .into(binding.contentImage)
            }

        }

    }

}