package com.tpsmedia.mysoejasch.additional

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CircularProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress = 0
    private var maxProgress = 100
    private val paint = Paint()
    private val rect = RectF()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.isAntiAlias = true
        paint.color = Color.parseColor("#000000") // Ubah warna sesuai keinginan
    }

    fun setProgress(progress: Int) {
        this.progress = progress.coerceIn(0, maxProgress)
        invalidate() // Memanggil ulang onDraw()
    }

    fun setMaxProgress(max: Int) {
        maxProgress = max
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val diameter = Math.min(width, height)

        rect.set(10f, 10f, diameter - 10f, diameter - 10f)

        // Menggambar lingkaran latar belakang
        paint.color = Color.LTGRAY
        canvas.drawArc(rect, 0f, 360f, false, paint)

        // Menggambar progress
        paint.color = Color.BLUE
        val sweepAngle = 360f * progress / maxProgress
        canvas.drawArc(rect, -90f, sweepAngle, false, paint)
    }
}