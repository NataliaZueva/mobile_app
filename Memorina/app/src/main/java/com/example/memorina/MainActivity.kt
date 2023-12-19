package com.example.memorina


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import kotlinx.coroutines.*

@SuppressLint("StaticFieldLeak")
var img_i_1: ImageView? = null
@SuppressLint("StaticFieldLeak")
var img_i_2: ImageView? = null

val img_sprites = arrayOf(R.drawable.img_1,R.drawable.img_2,
    R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,
    R.drawable.img_6,R.drawable.img_7,R.drawable.img_8)

var found_imgs_num = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

//        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
//        params.weight = 1.toFloat() // единичный вес

        val img_sprite_indexes = (0..15).toList().toMutableList()

        // Создание массива с нужными индексами
        var max_i = 16
        val indexes = (0..15).toList().toMutableList()

        while (max_i > 0){
            val spriteI = (0..7).random()
            println("SPRITE INDEX = $spriteI")
            img_sprite_indexes[indexes[0]] = spriteI
            println(indexes[0])
            indexes[0] = indexes[max_i-1]
            max_i--
            print("$indexes -> ")

            val i = (0 until max_i).random()
            img_sprite_indexes[indexes[i]] = spriteI
            println(indexes[i])
            indexes[i] = indexes[max_i-1]
            max_i--
            println(indexes)
        }
        println(img_sprite_indexes)
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1.toFloat() // единичный вес
        params.height = 580

        val imgViews = ArrayList<ImageView>()
        for (i in 0..15) {
            imgViews.add( // вызываем конструктор для создания нового ImageView
                ImageView(applicationContext).apply {
                    val t = img_sprite_indexes[i]
                    println("$i = $t")
                    setImageResource(R.drawable.img_rub)
                    layoutParams = params
                    tag = "img_$t"
                    setOnClickListener(colorListener)
                })
        }

        val rows = Array(4, { LinearLayout(applicationContext) })

        var count = 0
        for (view in imgViews) {
            val row: Int = count / 4
            rows[row].addView(view)
            count ++
        }
        for (row in rows) {
            layout.addView(row)
        }
        setContentView(layout)

    }

    @SuppressLint("ShowToast")
    suspend fun setBackgroundWithDelay(v: ImageView) {
        println(v.tag)
        var ind = v.tag.toString().filter { it.isDigit() }.toInt()
        println(ind)
        v.setImageResource(img_sprites[ind])
        delay(500)
        if (img_i_1 == null)
        {
            img_i_1 = v
        }
        else if (img_i_2 == null)
        {
            img_i_2 = v
        }
        if (img_i_1 != null && img_i_2 != null){
            if (img_i_1!!.tag == img_i_2!!.tag){
                img_i_1!!.visibility = View.INVISIBLE
                img_i_2!!.visibility = View.INVISIBLE

                img_i_1 = null
                img_i_2 = null

                found_imgs_num += 2
                if (found_imgs_num == 16){
                    val toast = Toast.makeText(
                        applicationContext,
                        "Вы нашли все пары!",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
            else{
                img_i_1!!.setImageResource(R.drawable.img_rub)
                img_i_2!!.setImageResource(R.drawable.img_rub)

                img_i_1 = null
                img_i_2 = null
            }
        }
    }

    // обработчик нажатия на кнопку
    @OptIn(DelicateCoroutinesApi::class)
    val colorListener = View.OnClickListener() {
        // запуск функции в фоновом потоке
        GlobalScope.launch (Dispatchers.Main)
        {
            setBackgroundWithDelay( it as ImageView ) }
    }
}
