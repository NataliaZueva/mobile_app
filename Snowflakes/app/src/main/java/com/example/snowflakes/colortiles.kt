package com.example.snowflakes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

data class Snowflake(var x: Float, var y: Float, var velocity: Float, val radius: Float, val color: Int)
lateinit var snow: Array<Snowflake>
val paint = Paint()
var h = 1000; var w = 1000

class Snowflakes(ctx: Context) : View(ctx) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // redraw the canvas
        canvas.drawColor(Color.BLUE)
        for (s in snow) {
            paint.color = s.color
            canvas.drawCircle(s.x, s.y, s.radius, paint)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top; w = right - left
        val r = Random(0)
        r.nextFloat() // генерирует число от 0 до 1
        snow = Array(10) {
            Snowflake(
                x = r.nextFloat() * w,
                y= r.nextFloat() * h,
                velocity = 15 + 10 * r.nextFloat(),
                radius = 30 + 20 * r.nextFloat(),
                color = Color.rgb(
                    r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256)))}
        Log.d("mytag", "snow: " + snow.contentToString())
    }

    private fun MoveSnowflakes() {
        for (s in snow) {
            s.y += s.velocity

            if (s.y > h - 400F) {
                s.velocity = s.velocity * 0.95F
            }
            if (s.y.toInt() >= h - 50F) {
                s.velocity = 0F
            }

            if (s.y > h) {
                s.y -= h
            }
        }
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        MoveSnowflakes()
        invalidate() // redraw the View
        return true
    }
}
