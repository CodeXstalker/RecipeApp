package com.stalker.recipesapp.adapter

import android.annotation.SuppressLint
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

class SearchRecipeAdapter(var dataList: ArrayList<Recipe_Modal>, var context: Context) :
    RecyclerView.Adapter<SearchRecipeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(context)
        var view: View = layoutInflater.inflate(R.layout.serch_recipe_rv, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchTitle.text = dataList.get(position).tittle
        val imgUrl = dataList.get(position).img
        if (!imgUrl.isNullOrEmpty()) {
            Glide.with(context).load(imgUrl).into(holder.searchImage)
        }
    }

        @SuppressLint("NotifyDataSetChanged")
        fun filterSearch(filterList: ArrayList<Recipe_Modal>){
            dataList = filterList
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val searchImage: ImageView = itemView.findViewById(R.id.iv_srechImage)
        val searchTitle: TextView = itemView.findViewById(R.id.tv_searchRecipe)
    }

}