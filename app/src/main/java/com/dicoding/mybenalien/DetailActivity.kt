package com.dicoding.mybenalien

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    val tvDetaiName = findViewById<TextView>(R.id.tv_detail_name)
    val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)
    val ivDetailPhoto = findViewById<ImageView>(R.id.iv_detail_photo)

    tvDetaiName.text = dataAlien.name
    tvDetailDescription.text = dataAlien.description
    ivDetailPhoto.setImageResource(dataAlien.photo)
}

    val dataAlien = if (build.VERSION.SDK_INT >= 33) {
        intent.getParcelableExtra<Alien>("key_alien", Alien::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getParcelableExtra<Alien>("key_alien")
    }
}