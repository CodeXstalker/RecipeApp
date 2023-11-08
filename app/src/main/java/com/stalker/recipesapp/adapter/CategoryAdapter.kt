package com.stalker.recipesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stalker.recipesapp.R
import com.stalker.recipesapp.modal.Recipe_Modal

class CategoryAdapter(var dataList: ArrayList<Recipe_Modal>, var context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.caterogry_layout, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(dataList[position].img).into(holder.popularImage)
        holder.popularTitle.text = dataList[position].tittle
        var time = dataList.get(position).ing.split("\n".toString()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        holder.popularTime.text = time.get(0)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val popularImage: ImageView = itemView.findViewById(R.id.iv_srechImage)
        val popularTitle: TextView = itemView.findViewById(R.id.tv_searchRecipe)
        val popularTime: TextView = itemView.findViewById(R.id.tv_time)
    }
}
