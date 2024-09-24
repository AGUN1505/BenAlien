package com.dicoding.mybenalien

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAlien: RecyclerView
    private val list = ArrayList<Alien>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Alien"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvAlien = findViewById(R.id.rv_alien)
        rvAlien.setHasFixedSize(true)

        list.addAll(getListAlien())
        showRecyclerList()
    }

    private fun getListAlien(): ArrayList<Alien> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAlien = ArrayList<Alien>()
        for (i in dataName.indices) {
            val alien = Alien(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listAlien.add(alien)
        }
        return listAlien
    }

    private fun showRecyclerList() {
        rvAlien.layoutManager = LinearLayoutManager(this)
        val listAlienAdapter = ListAlienAdapter(list)
        rvAlien.adapter = listAlienAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAlien.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvAlien.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}