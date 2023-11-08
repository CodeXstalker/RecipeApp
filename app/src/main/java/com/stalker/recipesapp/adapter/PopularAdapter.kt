package com.stalker.recipesapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stalker.recipesapp.R
import com.stalker.recipesapp.modal.Recipe_Modal

class PopularAdapter(var dataList: ArrayList<Recipe_Modal>, var context: Context) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.popular_recipe_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.popularTitle.text = dataList.get(position).tittle
        /**
         * Here we are formatting the data to get the time
         */
        var time = dataList.get(position).ing.split("\n".toString()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        holder.popularTime.text = time.get(0)
        /**
         * here we are loading images from the link in RoomDB
         */
        val imgUrl = dataList.get(position).img
        if (!imgUrl.isNullOrEmpty()) {
            Glide.with(context).load(imgUrl).into(holder.popularImage)
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val popularImage: ImageView = itemView.findViewById(R.id.iv_popularImage)
        val popularTitle: TextView = itemView.findViewById(R.id.tv_popularTitle)
        val popularTime: TextView = itemView.findViewById(R.id.tv_popularTime)
    }


}