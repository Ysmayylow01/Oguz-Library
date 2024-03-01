package oguz.library.etut.item

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx

class Toolbar(context: Context) : LinearLayout(context) {
    private val linearToolbar = LinearLayout(context)
    private val relativeLayout = LinearLayout(context)
    var backIcon = ImageView(context)
    var titleTextToolbar = TextView(context)
    var otherIcon = ImageView(context)

    init {
        setUIToolbar()
    }

    private fun setUIToolbar() {
        setContainerToolbar()
        setRelativeToolbar()
        setBackIcon()
        setTextToolbar()
        setOtherIcon()
    }

    private fun setOtherIcon() {
        val otherIconParams = LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        otherIcon.layoutParams = otherIconParams
        otherIconParams.gravity = Gravity.CENTER
    }

    private fun setTextToolbar() {
        val setTextParams = LayoutParams(
           0,
            LayoutParams.WRAP_CONTENT, 1f
        )
        setTextParams.gravity = Gravity.CENTER
        titleTextToolbar.textAlignment = TEXT_ALIGNMENT_CENTER
        titleTextToolbar.layoutParams = setTextParams
        titleTextToolbar.textSize = 22f
        titleTextToolbar.setTextColor(resources.getColor(R.color.textBlue))


    }

    private fun setBackIcon() {
        val backIconParams = LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        backIcon.layoutParams = backIconParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIcon.foreground = ContextCompat.getDrawable(context, R.drawable.ripple_effect)
        }
        backIconParams.gravity = Gravity.CENTER

    }

    private fun setRelativeToolbar() {
        val relativeParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        relativeLayout.layoutParams = relativeParams
        relativeLayout.orientation = HORIZONTAL
        relativeLayout.setPadding(dpToPx(resources, 10))
        relativeLayout.setBackgroundColor(resources.getColor(R.color.backgroundCard))
        relativeLayout.addView(backIcon)
        relativeLayout.addView(titleTextToolbar)
        relativeLayout.addView(otherIcon)
       addView(relativeLayout)
    }

    private fun setContainerToolbar() {
        linearToolbar.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        linearToolbar.orientation = LinearLayout.VERTICAL
    }


}