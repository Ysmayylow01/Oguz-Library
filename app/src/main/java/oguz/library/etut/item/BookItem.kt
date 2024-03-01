package oguz.library.etut.item

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.setMargins
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.StaticMethods.Companion.dpToPx

class BookItem(context: Context) : RelativeLayout(context) {
    private val relativeLayout = RelativeLayout(context)
    private val linearLayout = LinearLayout(context)
    private val lineText = LinearLayout(context)
     val cardViewBookItem = MaterialCardView(context)
    var imageBookItem = ShapeableImageView(context)
    var titleBooksItem = TextView(context)
    var subtitleBooksItem = TextView(context)

    init {
        setUIBookItem()
    }

    private fun setUIBookItem() {
        setContainer()
        cardItemBooks()
        linearCardItem()
        imageBooks()
        linearTextVertical()
        titleBooks()
        subtitleBooks()
    }



    @SuppressLint("ResourceAsColor")
    private fun subtitleBooks() {
        val subtitleTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        subtitleBooksItem.layoutParams = subtitleTextParams
        subtitleBooksItem.text = "sadasd"
        subtitleBooksItem.textSize = 12f
        subtitleBooksItem.setTextColor(R.color.subtitle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            subtitleBooksItem.typeface = resources.getFont(R.font.mitr_light)
        }
       StaticMethods.setBoldTypeface(subtitleBooksItem)

    }

    private fun linearTextVertical() {
        val linearTextHrParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.MATCH_PARENT, 1f
        )
        lineText.layoutParams = linearTextHrParams
        lineText.orientation = LinearLayout.VERTICAL
        linearTextHrParams.setMargins(dpToPx(resources, 10))
        lineText.addView(subtitleBooksItem)
        lineText.addView(titleBooksItem)
    }

    @SuppressLint("ResourceAsColor", "NewApi")
    private fun titleBooks() {
        val titleTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        titleBooksItem.layoutParams = titleTextParams
        titleBooksItem.text = "sadasd"
        titleBooksItem.textSize = 16f
        titleBooksItem.setTextColor(R.color.textBlue)
        titleBooksItem.typeface = resources.getFont(R.font.aclonia_regular)


    }

    private fun imageBooks() {
        val imageBookItemParams = LinearLayout.LayoutParams(
            dpToPx(resources, 70),
            dpToPx(resources, 70)
        )
        imageBookItem.layoutParams = imageBookItemParams
        imageBookItem.scaleType = ImageView.ScaleType.CENTER_CROP
        imageBookItemParams.gravity = Gravity.CENTER
        imageBookItemParams.setMargins(dpToPx(resources,8))
        imageBookItem.setImageResource(R.drawable.sozlukicon)


    }

    private fun linearCardItem() {
        linearLayout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.addView(imageBookItem)
        linearLayout.addView(lineText)
    }

    @SuppressLint("NewApi")
    private fun cardItemBooks() {
        cardViewBookItem.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        cardViewBookItem.cardElevation = dpToPx(resources, 6).toFloat()
        cardViewBookItem.radius = dpToPx(resources, 10).toFloat()
        cardViewBookItem.useCompatPadding = true
        cardViewBookItem.backgroundTintList =ContextCompat.getColorStateList(context, R.color.white)
        cardViewBookItem.addView(linearLayout)
        addView(cardViewBookItem)
    }
    @SuppressLint("ResourceAsColor")
    private fun setContainer() {
        relativeLayout.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        setBackgroundColor(R.color.background)
    }
}