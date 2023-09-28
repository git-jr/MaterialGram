package com.paradoxo.materialgram.presentation.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.paradoxo.materialgram.R
import com.paradoxo.materialgram.domain.model.Image


class ImageAdapter(
    private var context: Context,
    private var imageArrayList: List<Image>
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(imageArrayList[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return imageArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.carousel_image_view)
        }
    }
}