package com.example.colortiles123

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.activity.ComponentActivity
import kotlin.random.Random


// тип для координат
data class Coord(val x: Int, val y: Int)

class MainActivity : ComponentActivity() {

    private lateinit var tiles: Array<Array<View>>

    private val brightColor by lazy { resources.getColor(R.color.bright, theme) }
    private val darkColor by lazy { resources.getColor(R.color.dark, theme) }



    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiles = Array(4) { row ->
            Array(4) { col ->
                findViewById(resources.getIdentifier("t$row$col", "id", packageName))
            }
        }
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (Random.nextBoolean()) {
                    tiles[i][j].setBackgroundColor(brightColor)
                } else {
                    tiles[i][j].setBackgroundColor(darkColor)
                }

            }
        }
    }

    private fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright, theme)
        val darkColor = resources.getColor(R.color.dark, theme)
        val drawable = view.background as ColorDrawable
        if (drawable.color ==brightColor ) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    private fun getCoordFromString(s: String): Coord {
        return if (s.length == 2) {
            val x = Character.getNumericValue(s[0])
            val y = Character.getNumericValue(s[1])
            Coord(x, y)
        } else {

            Coord(0, 0)
        }
    }

    fun onClick(v: View) {
        val tag = v.tag as String
        val coord = getCoordFromString(tag)
        changeColor(v)

        for (i in 0 until 4) {
            if (i != coord.x) {
                changeColor(tiles[i][coord.y])
            }
            if (i != coord.y) {
                changeColor(tiles[coord.x][i])
            }
        }
        checkVictory()
    }

    private fun checkVictory() {
        val refColor = (tiles[0][0].background as ColorDrawable).color

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val currColor = (tiles[i][j].background as ColorDrawable).color
                if (currColor != refColor) {
                    return
                }
            }
        }
        Toast.makeText(this, "It seems to be a victory!", Toast.LENGTH_SHORT).show()
    }
}

