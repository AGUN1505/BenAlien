package com.dicoding.mybenalien

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val KEY_ALIEN = "key_alien"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail Alien"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btn_Share: Button = findViewById(R.id.btshare)
        btn_Share.setOnClickListener(this)

        val tvDetaiName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)

        val dataAlien = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Alien>(KEY_ALIEN, Alien::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Alien>(KEY_ALIEN)
        }
        if (dataAlien != null) {
            tvDetaiName.text = dataAlien.name
            tvDetailDescription.text = dataAlien.description
            ivDetailPhoto.setImageResource(dataAlien.photo)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btshare -> {
                val tvDetailNama: TextView = findViewById(R.id.tv_detail_name)
                val judul = tvDetailNama.text.toString()
                val shareJudulIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, judul)
                }
                startActivity(Intent.createChooser(shareJudulIntent, "Bagikan dengan:"))
            }
        }
    }
}


