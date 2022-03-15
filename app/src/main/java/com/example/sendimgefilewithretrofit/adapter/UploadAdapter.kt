package com.example.sendimgefilewithretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sendimgefilewithretrofit.R
import com.example.sendimgefilewithretrofit.networking.model.Cat
import com.google.android.material.imageview.ShapeableImageView

class UploadAdapter(var context: Context, var list: ArrayList<Cat>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_upload, parent, false)
        return UploadViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var items = list[position]
        if (holder is UploadViewHolder){
            var iv_upload = holder.iv_upload
            var cardView = holder.card_view

            Glide.with(holder.itemView.context)
                .load(items.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_upload)
        }
    }

    override fun getItemCount(): Int = list.size

    class UploadViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_upload = view.findViewById<ShapeableImageView>(R.id.item_images_upload)
        var card_view = view.findViewById<CardView>(R.id.card_view1)
    }

}