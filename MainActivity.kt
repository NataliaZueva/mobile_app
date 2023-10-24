package com.example.a2410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val numA = findViewById<EditText>(R.id.numA).text.toString()
        val numB = findViewById<EditText>(R.id.numB).text.toString()
        if (TextUtils.isEmpty(numA) || TextUtils.isEmpty(numB)) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
        } else {
            val sum = numA.toDouble() + numB.toDouble()
            val tvSum = findViewById<TextView>(R.id.sum)
            tvSum.text = sum.toString()
        }
    }
}
