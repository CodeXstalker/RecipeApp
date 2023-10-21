package com.stalker.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.stalker.recipesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList : ArrayList<Recipe_Modal>
    private lateinit var adapter : PopularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        searchRecipeAction()
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopularRecipe.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var db = Room.databaseBuilder(this@MainActivity, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        val recipes = daoObject.getAll()
        for(i in recipes!!.indices){

            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            adapter = PopularAdapter(dataList, this)
            binding.rvPopularRecipe.adapter = adapter
        }
    }


    private fun searchRecipeAction() {
        binding.etSearchRecipe.setOnClickListener {
            intentToSearchActivity()
        }
    }

    private fun intentToSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}