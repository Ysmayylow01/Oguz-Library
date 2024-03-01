package oguz.library.etut.fragment.quiz

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.StaticMethods.Companion.replaceFragment
import oguz.library.etut.item.QuizItem
import oguz.library.etut.item.Toolbar

class QuizFragment : Fragment() {

    private lateinit var containerQuiz: LinearLayout
    private lateinit var toolbarQuiz: Toolbar
    private lateinit var quizLogo : ImageView
    private lateinit var mathQuiz: QuizItem
    private lateinit var aiQuiz : QuizItem
    private lateinit var csitQuiz : QuizItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContainerQuiz()
        onClickQuizFragment()
        return containerQuiz
    }

    private fun onClickQuizFragment() {
        toolbarQuiz.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }


    private fun setContainerQuiz() {
        containerQuiz = LinearLayout(requireContext())
        containerQuiz.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerQuiz.setBackgroundColor(resources.getColor(R.color.background))
        containerQuiz.orientation = LinearLayout.VERTICAL
        containerQuiz.isFocusable = true
        containerQuiz.isClickable = true

        toolbarQuiz = Toolbar(requireContext())
        toolbarQuiz.titleTextToolbar.setText(getString(R.string.zehin))
        toolbarQuiz.backIcon.setImageResource(R.drawable.backstack)

        mathQuiz = QuizItem(requireContext())
        mathQuiz.imageQuiz.setImageResource(R.drawable.ic_quiz_math)
        mathQuiz.tvQuiz.setText(R.string.amaly_matematika)
        mathQuiz.setOnClickListener {
            replaceFragment(parentFragmentManager, MathQuizFragment(), R.id.frame_main)
        }

        csitQuiz = QuizItem(requireContext())
        csitQuiz.imageQuiz.setImageResource(R.drawable.ic_quiz_csit)
        csitQuiz.tvQuiz.setText(R.string.informatika_we_hasaplayys)
        csitQuiz.setOnClickListener {
            replaceFragment(parentFragmentManager, CSITQuizFragment(), R.id.frame_main)
        }


        aiQuiz = QuizItem(requireContext())
        aiQuiz.imageQuiz.setImageResource(R.drawable.ic_quiz_ai)
        aiQuiz.tvQuiz.setText(R.string.emeli_an)
        aiQuiz.setOnClickListener {
            replaceFragment(parentFragmentManager, AIQuizFragment(), R.id.frame_main)
        }

        quizLogo = ImageView(requireContext())
        val quizLogoParams = LinearLayout.LayoutParams(
         dpToPx(resources, 330),
            dpToPx(resources, 140)
        )
        quizLogo.layoutParams = quizLogoParams
        quizLogoParams.topMargin = dpToPx(resources, 15)
        quizLogoParams.gravity = Gravity.CENTER
        quizLogoParams.bottomMargin = dpToPx(resources, 15)
        quizLogo.setImageResource(R.drawable.logo_zehin)
        val objectAnimator = ObjectAnimator.ofFloat(
            quizLogo,
            "rotationX",
            0f,
            360f
        )
        objectAnimator.duration = 3000
        objectAnimator.repeatCount = ObjectAnimator.INFINITE

        objectAnimator.start()

        containerQuiz.addView(toolbarQuiz)
        containerQuiz.addView(quizLogo)
        containerQuiz.addView(mathQuiz)
        containerQuiz.addView(csitQuiz)
        containerQuiz.addView(aiQuiz)
    }
}