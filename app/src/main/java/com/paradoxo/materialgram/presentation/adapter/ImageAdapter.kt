package com.paradoxo.materialgram.presentation.adapter


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.paradoxo.materialgram.R
import com.paradoxo.materialgram.databinding.ImageItemBinding
import com.paradoxo.materialgram.domain.model.Media


class ImageAdapter(
    private var context: Context,
    private var mediaArrayList: List<Media>
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(mediaArrayList[position].url, imageLoaderGif())
    }

    private fun imageLoaderGif(): ImageLoader {
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .placeholder(R.drawable.shimmer_animation)
            .error(R.drawable.ic_error)
            .build()
        return imageLoader
    }

    override fun getItemCount(): Int {
        return mediaArrayList.size
    }

    class ViewHolder(itemView: ImageItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var imageView: ImageView

        init {
            imageView = itemView.carouselImageView
        }
    }
}