package oguz.library.etut.fragment.formulas

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import com.github.chrisbanes.photoview.PhotoView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.item.Toolbar

class FormulaViewFragment : Fragment() {
    private lateinit var toolbarFormView : Toolbar
    private lateinit var linearFormView: RelativeLayout
    private lateinit var formImage: PhotoView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        containerForm()

        return linearFormView
    }

    private fun containerForm() {
        val title = arguments?.getString("title")
        val imageForm = arguments?.getInt("formula")
        linearFormView = RelativeLayout(requireContext())
        linearFormView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        linearFormView.setBackgroundColor(resources.getColor(R.color.background))
        linearFormView.isClickable = true
        linearFormView.isFocusable = true
        toolbarFormView = Toolbar(requireContext())
        toolbarFormView.titleTextToolbar.text = title
        toolbarFormView.titleTextToolbar.maxLines = 1
        toolbarFormView.titleTextToolbar.textSize = 17f
        toolbarFormView.titleTextToolbar.ellipsize = TextUtils.TruncateAt.END
        toolbarFormView.titleTextToolbar.setPadding(dpToPx(resources, 5),dpToPx(resources, 0),dpToPx(resources, 5),dpToPx(resources, 0))
        toolbarFormView.backIcon.setImageResource(R.drawable.backstack)
        toolbarFormView.id = View.generateViewId()
        toolbarFormView.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        formImage = PhotoView(requireContext())
        val formImageParams= RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        formImage.layoutParams = formImageParams
        formImage.scaleType = ImageView.ScaleType.FIT_CENTER
        formImageParams.addRule(RelativeLayout.BELOW, toolbarFormView.id)
        formImage.setImageResource(imageForm!!)
        linearFormView.addView(toolbarFormView)
        linearFormView.addView(formImage)



    }
}