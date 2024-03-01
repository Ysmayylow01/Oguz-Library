package oguz.library.etut.item

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.google.android.material.card.MaterialCardView
import com.romainpiel.shimmer.Shimmer
import oguz.library.etut.R
import oguz.library.etut.SplashScreen
import oguz.library.etut.StaticMethods.Companion.dpToPx

class MainCardItem(context: Context) : LinearLayout(context) {
    private val linearLayout = LinearLayout(context)
    private val cardItemMain = MaterialCardView(context)
    private val cardIconMain = MaterialCardView(context)
    private val linearHorizontal = LinearLayout(context)
    var textCardItem = com.romainpiel.shimmer.ShimmerTextView(context)
    var imageCardItem = ImageView(context)
    private  val shimmerText = Shimmer().setDuration(3000)

    init {
        setUICardItem()
    }

    private fun setUICardItem() {
        linearLayoutCardItemMain()
        cardViewItem()

    }

    @SuppressLint("NewApi")
    private fun cardIcon() {
        imageCardItems()
        val cardIconMainParams = LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        cardIconMain.layoutParams = cardIconMainParams
        cardIconMainParams.gravity = Gravity.CENTER
        cardIconMain.radius = dpToPx(resources, 100).toFloat()
        cardIconMain.cardElevation = dpToPx(resources, 10).toFloat()
        cardIconMainParams.leftMargin = dpToPx(resources, 10)
        cardIconMain.backgroundTintList = ContextCompat.getColorStateList(context, R.color.cardlogo)
        cardIconMain.addView(imageCardItem)
    }

    private fun imageCardItems() {
        val imageCardItemParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        imageCardItem.layoutParams = imageCardItemParams
        imageCardItem.scaleType = ImageView.ScaleType.CENTER_CROP
        imageCardItemParams.setMargins(dpToPx(resources, 5))
    }

    private fun linearLayoutCardItemMain() {

        linearLayout.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    @SuppressLint( "ResourceAsColor")
    private fun textCardItems() {
        val cardTextItemsParams = LayoutParams(
            0, LayoutParams.WRAP_CONTENT, 1f
        )
        textCardItem.layoutParams = cardTextItemsParams
        cardTextItemsParams.gravity = Gravity.CENTER
        textCardItem.setTextColor(resources.getColor(R.color.textBlue))
        cardTextItemsParams.marginStart = dpToPx(resources, 20)
        textCardItem.textAlignment = TEXT_ALIGNMENT_TEXT_START
        textCardItem.textSize = 20f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textCardItem.typeface = resources.getFont(R.font.aclonia_regular)
        }
        textCardItem.reflectionColor =resources.getColor(R.color.shimmertext)
    }

    @SuppressLint("NewApi")
    private fun cardViewItem() {
        lineHorizontal()
        val cardItemMainParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            dpToPx(resources, 70)
        )
        cardItemMain.layoutParams = cardItemMainParams
        cardItemMainParams.setMargins(dpToPx(resources, 10), dpToPx(resources, 0), dpToPx(resources, 10), dpToPx(resources, 0))
        cardItemMain.useCompatPadding = true
        cardItemMain.elevation = dpToPx(resources, 2).toFloat()
        cardItemMain.backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
        cardItemMain.radius = dpToPx(resources, 15).toFloat()
        cardItemMain.addView(linearHorizontal)
        addView(cardItemMain)
    }

    private fun lineHorizontal() {
        cardIcon()
        textCardItems()
        linearHorizontal.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        linearHorizontal.orientation = HORIZONTAL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            linearHorizontal.foreground = ContextCompat.getDrawable(context, R.drawable.ripple_effect)
        }
        linearHorizontal.addView(cardIconMain)
        linearHorizontal.addView(textCardItem)
    }
}
