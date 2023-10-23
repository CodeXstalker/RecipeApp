package com.stalker.recipesapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.stalker.recipesapp.adapter.SearchRecipeAdapter
import com.stalker.recipesapp.database.AppDatabase
import com.stalker.recipesapp.databinding.ActivitySearchBinding
import com.stalker.recipesapp.modal.Recipe_Modal

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    private lateinit var dataList: ArrayList<Recipe_Modal>
    private lateinit var adapter: SearchRecipeAdapter
    lateinit var recipes: List<Recipe_Modal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestingFocusOnSearchRecipeEditText()

        settingUpRoomDb()
        setUpRecyclerView()

        settingUpTextWatcher()

        binding.ivBackButton.setOnClickListener {
            onClickBackButtton()
        }
    }

    private fun settingUpRoomDb() {
        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries().fallbackToDestructiveMigration().createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        recipes = daoObject.getAll()!!
    }

    private fun settingUpTextWatcher() {


        binding.etSearchRecipe.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != "") {
                    filterData(p0.toString())
                }else{
                    setUpRecyclerView()
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    private fun filterData(filterText: String) {
        var filterData = ArrayList<Recipe_Modal>()
        for (i in recipes.indices) {
            if (recipes[i].tittle.lowercase().contains(filterText.lowercase())) {
                filterData.add(recipes[i])
            }
            adapter.filterSearch(filterData)
        }
    }

    private fun requestingFocusOnSearchRecipeEditText() {

        binding.etSearchRecipe.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun onClickBackButtton() {
        intentToMainActivity()
    }

    private fun intentToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setUpRecyclerView() {
        /**
         * Here we initialized the dataList
         */
        dataList = ArrayList()
        binding.rvSearchRecipe.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        for (i in recipes.indices) {

            if (recipes[i].category.contains("Popular")) {
                dataList.add(recipes[i])
            }
            adapter = SearchRecipeAdapter(dataList, this)
            binding.rvSearchRecipe.adapter = adapter
        }

    }
}