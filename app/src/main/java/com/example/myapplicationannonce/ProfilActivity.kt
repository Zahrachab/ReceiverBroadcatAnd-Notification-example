package com.example.myapplicationannonce

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        val name = intent.extras.getString("nom")
        val bibliographie = intent.extras.getString("bibliographie")
        val bitmap = intent.getParcelableExtra("photo") as Bitmap

       textName.setText(name)
       textBiblio.setText(bibliographie!!)
       image.setImageBitmap(bitmap!!)

    }
}
