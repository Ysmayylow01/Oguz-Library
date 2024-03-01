package oguz.library.etut.item

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.setPadding
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx

class Nofavorite(context : Context):RelativeLayout(context) {
    private  val relativeNoFav =RelativeLayout(context)
    private val linearNoFav = LinearLayout(context)
          var noFavIcon = ImageView(context)
     var noFavText = TextView(context)

    init {
        setUINoFav()
    }

    private fun setUINoFav() {
        containerNoFav()
        linearNoFavorite()
        noFavImage()
        noTextFav()
    }

    private fun noTextFav() {
        val noFavTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        noFavText.layoutParams = noFavTextParams
        noFavText.setText(resources.getString(R.string.nofavorite))
        noFavText.textSize = 20f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            noFavText.typeface = resources.getFont(R.font.mitr_regular)
        }
        noFavText.setTextColor(resources.getColor(R.color.textBlue))
        noFavText.setPadding(dpToPx(resources,10))
        noFavTextParams.gravity = Gravity.CENTER
        noFavText.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
    }

    private fun linearNoFavorite() {
        val linearNoFavParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        linearNoFav.layoutParams = linearNoFavParams
        linearNoFav.orientation = LinearLayout.VERTICAL
        linearNoFavParams.addRule(CENTER_IN_PARENT)
        linearNoFav.addView(noFavIcon)
        linearNoFav.addView(noFavText)

    }

    private fun noFavImage() {
        val noFavIconParams = LinearLayout.LayoutParams(
            dpToPx(resources, 150),
            dpToPx(resources, 150)
        )
        noFavIcon.layoutParams = noFavIconParams
        noFavIconParams.gravity = Gravity.CENTER
        noFavIcon.setPadding(dpToPx(resources, 10))
        noFavIconParams.bottomMargin = dpToPx(resources,10)
        noFavIcon.setImageResource(R.drawable.nofavorite)
    }

    private fun containerNoFav() {
        relativeNoFav.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        relativeNoFav.setBackgroundColor(resources.getColor(R.color.background))
        addView(relativeNoFav)
        relativeNoFav.addView(linearNoFav)

    }
}