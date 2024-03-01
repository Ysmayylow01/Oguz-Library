package oguz.library.etut

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.romainpiel.shimmer.Shimmer
import oguz.library.etut.StaticMethods.Companion.dpToPx
import org.w3c.dom.Text


class SplashScreen : AppCompatActivity() {

    private lateinit var splashContainer: RelativeLayout
    private lateinit var splashLinear: LinearLayout
    private lateinit var splashImageView: ImageView
    private lateinit var splashTxView: com.romainpiel.shimmer.ShimmerTextView
    private lateinit var splashProgressBar : ProgressBar
    private  val shimmerText = Shimmer().setDuration(3000)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Oguz_Library)
        setUi()
        Handler().postDelayed( {
            StaticMethods.intentActivity(this,MainActivity())
            overridePendingTransition(R.anim.fade_in, R.anim.fadein)
            finish()
        }, 2500)
        setContentView(splashContainer)

    }

    private fun setUi() {
        setSplashContainer()
    }



    @SuppressLint("NewApi")
    private fun splashTextView() {
        splashTxView = com.romainpiel.shimmer.ShimmerTextView(this)
        val splashTextViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        splashTxView.layoutParams = splashTextViewParams
        splashTxView.text = getString(R.string.app_name)
        splashTxView.setTextColor(getColor(R.color.textBlue))
        splashTxView.textSize = 30f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            splashTxView.typeface = resources.getFont(R.font.aclonia_regular)
        }
        splashTxView.reflectionColor = getColor(R.color.shimmertext)
        splashTextViewParams.gravity = Gravity.CENTER_HORIZONTAL
        shimmerText.start(splashTxView)


    }

    private fun splashImage() {
        splashImageView = ImageView(this)
        val splashImageViewParams = LinearLayout.LayoutParams(
            dpToPx(resources, 200),
            dpToPx(resources, 200)
        )
        splashImageView.layoutParams = splashImageViewParams
        splashImageView.id = View.generateViewId()
        splashImageViewParams.gravity = Gravity.CENTER_HORIZONTAL
        splashImageView.setImageResource(R.drawable.logo)
        splashImageViewParams.bottomMargin = dpToPx(resources, 20)
        val objectAnimator = ObjectAnimator.ofFloat(
            splashImageView,
            "rotationY",
            0f,
            360f
        )
        objectAnimator.duration = 2500
        objectAnimator.repeatCount = ObjectAnimator.INFINITE

        objectAnimator.start()
    }

    @SuppressLint("ResourceType")
    private fun splashLoading() {
        splashProgressBar = ProgressBar(this)
          val  splashProgressBarParams= LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
        splashProgressBar.layoutParams = splashProgressBarParams
        splashProgressBarParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        splashProgressBarParams.addRule(RelativeLayout.BELOW, splashLinear.id)
        splashProgressBarParams.topMargin = dpToPx(resources, 20)
        splashProgressBar.indeterminateTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.textBlue))
    }


    private fun splashLinearLay() {
        splashImage()
        splashTextView()
        splashLinear = LinearLayout(this)
        val splashlinearParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        splashLinear.layoutParams = splashlinearParams
        splashLinear.id = View.generateViewId()
        splashlinearParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        splashLinear.orientation = LinearLayout.VERTICAL
        splashLinear.setBackgroundColor(getColor(R.color.background))
        splashLinear.addView(splashImageView)
        splashLinear.addView(splashTxView)
    }

    @SuppressLint("NewApi")
    private fun setSplashContainer() {
        splashLinearLay()
        splashLoading()
        splashContainer = RelativeLayout(this)
        splashContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        splashContainer.setBackgroundColor(getColor(R.color.background))
        splashContainer.addView(splashLinear)
        splashContainer.addView(splashProgressBar)

    }


}