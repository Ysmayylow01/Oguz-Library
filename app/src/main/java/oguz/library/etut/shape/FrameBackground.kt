package oguz.library.etut.shape

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable


@SuppressLint("ResourceAsColor")
class FrameBackground(context: Context, attrs: Int) : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val cornerRadius = dpToPx(10)

    init {
        paint.color = attrs
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        path.reset()
        path.addRoundRect(RectF(bounds), cornerRadius, cornerRadius, Path.Direction.CW)
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: android.graphics.ColorFilter?) {
        paint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return paint.alpha
    }

    private fun dpToPx(dp:Int): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }
}
