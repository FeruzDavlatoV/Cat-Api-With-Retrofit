package com.example.sendimgefilewithretrofit.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sendimgefilewithretrofit.R
import com.example.sendimgefilewithretrofit.networking.model.BreedItem
import com.example.sendimgefilewithretrofit.networking.model.Cat
import com.google.android.material.imageview.ShapeableImageView

class SearchAdapter(var context: Context, var list: ArrayList<BreedItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var itemCLick: ((Cat) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var items = list[position]

        if (holder is SearchViewHolder) {
            val iv_search = holder.iv_search
            val card_view = holder.card_view

            Glide.with(holder.itemView.context)
                .load(items.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_search)
        }
    }

    override fun getItemCount(): Int = list.size

    class SearchViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var iv_search = view.findViewById<ShapeableImageView>(R.id.item_images1)
        var card_view = view.findViewById<CardView>(R.id.card_view1)
    }
}