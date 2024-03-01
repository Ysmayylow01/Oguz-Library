package oguz.library.etut.item

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.core.View
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx

class QuizItem(context: Context): LinearLayout(context) {
    private val linearLayout = LinearLayout(context)
    var cardView = FrameLayout(context)
    private val relativeLayout = LinearLayout(context)
    var imageQuiz = ImageView(context)
    var tvQuiz = TextView(context)

    init {
        setUiQuizItem()
    }

    private fun setUiQuizItem() {
        linear()
        card()
        frameLayout()
        imageView()
       textViewQuiz()
    }

    private fun textViewQuiz() {
        val tvQuizParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        tvQuiz.layoutParams = tvQuizParams
        tvQuiz.setText(resources.getString(R.string.amaly_matematika))
        tvQuiz.setTextColor(resources.getColor(R.color.black))
        tvQuiz.textSize = 16f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvQuiz.typeface = resources.getFont(R.font.mitr_medium)
        }
        tvQuizParams.setMargins(dpToPx(resources, 10))
       tvQuizParams.gravity = Gravity.CENTER
    }

    private fun card() {
       val  cardViewParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        cardView.layoutParams = cardViewParams
        cardView.background = resources.getDrawable(R.drawable.edit_shape)
        cardView.foreground = ContextCompat.getDrawable(context, R.drawable.ripple_effect)
        cardViewParams.setMargins(dpToPx(resources, 10))
        cardView.elevation = 5f
        cardView.addView(relativeLayout)
        addView(cardView)
    }

    private fun frameLayout() {
        relativeLayout.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        relativeLayout.orientation = HORIZONTAL
        relativeLayout.addView(imageQuiz)
        relativeLayout.addView(tvQuiz)

    }

    private fun imageView() {
         val imageViewParams = LayoutParams(
             dpToPx(resources, 80),
             dpToPx(resources, 80)
         )
        imageQuiz.layoutParams = imageViewParams
        imageQuiz.setImageResource(R.drawable.ic_quiz_math)
        imageViewParams.gravity = Gravity.CENTER
        imageViewParams.setMargins(dpToPx(resources, 10))
    }

    private fun linear() {
        linearLayout.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

    }


}