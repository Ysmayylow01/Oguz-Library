package oguz.library.etut.item

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import oguz.library.etut.R
import oguz.library.etut.StaticMethods

class LoadingProgress(context: Context) : RelativeLayout(context) {
    private val relativeLoading = RelativeLayout(context)
    private val loadingProgres = ProgressBar(context)

    init {
        setUILoading()
        loadingProgress()
    }

    private fun loadingProgress() {

        val progressBarParams = LayoutParams(
            StaticMethods.dpToPx(resources, 30),
            StaticMethods.dpToPx(resources, 30)
        )
        loadingProgres.layoutParams = progressBarParams
        loadingProgres.indeterminateTintList =
            ContextCompat.getColorStateList(context, R.color.textBlue)
        progressBarParams.addRule(CENTER_IN_PARENT)

    }

    private fun setUILoading() {
        relativeLoading.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        relativeLoading.setBackgroundColor(resources.getColor(R.color.background))
        addView(relativeLoading)
        relativeLoading.addView(loadingProgres)
    }
}