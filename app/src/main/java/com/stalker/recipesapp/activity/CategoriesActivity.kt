package com.stalker.recipesapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.stalker.recipesapp.adapter.CategoryAdapter
import com.stalker.recipesapp.database.AppDatabase
import com.stalker.recipesapp.databinding.ActivityCategoriesBinding
import com.stalker.recipesapp.modal.Recipe_Modal

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var dataList : ArrayList<Recipe_Modal>
    private lateinit var adapter : CategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater) // Corrected inflater code
        setContentView(binding.root)
        title = intent.getStringExtra("TITTLE")
        setUpRecyclerView()

        binding.imageView4.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }

    }



    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val db = Room.databaseBuilder(this@CategoriesActivity, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        val recipes = daoObject.getAll()
        for(i in recipes!!.indices){

            if(recipes[i].category.contains(intent.getStringExtra("CATEGORIES")!!)){
                dataList.add(recipes[i])
            }
            adapter = CategoryAdapter(dataList, this)
            binding.rvItem.adapter = adapter
        }
    }

}