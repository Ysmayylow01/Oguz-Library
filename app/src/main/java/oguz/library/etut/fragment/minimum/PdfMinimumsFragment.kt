package oguz.library.etut.fragment.minimum

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.EllipsizeCallback
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.item.Toolbar

class PdfMinimumsFragment:Fragment(){
    private lateinit var pdfContainer: RelativeLayout
    private lateinit var pdfLayout: PDFView
    private lateinit var toolbarPdfMin : Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setPdfRerlative()
        return pdfContainer
    }

    private fun setPdfRerlative() {
        pdfToolbar()
        pdfView(requireContext(), null)
        pdfContainer = RelativeLayout(requireContext())
        pdfContainer.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        pdfContainer.setBackgroundColor(resources.getColor(R.color.white))
        pdfContainer.isFocusable = true
        pdfContainer.isClickable = true
        pdfContainer.addView(toolbarPdfMin)
        pdfContainer.addView(pdfLayout)

    }


    private fun pdfToolbar() {
      val title = arguments?.getString("title")
        toolbarPdfMin = Toolbar(requireContext())
        toolbarPdfMin.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        toolbarPdfMin.backIcon.setImageResource(R.drawable.backstack)
        toolbarPdfMin.titleTextToolbar.text = title
        toolbarPdfMin.titleTextToolbar.maxLines = 1
        toolbarPdfMin.titleTextToolbar.ellipsize = TextUtils.TruncateAt.END
        toolbarPdfMin.titleTextToolbar.textSize = 18f
        toolbarPdfMin.id = View.generateViewId()
        toolbarPdfMin.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun pdfView(context: Context, attrs: AttributeSet?) {
        pdfLayout = PDFView(context, attrs)
        val pdfLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        pdfLayout.layoutParams = pdfLayoutParams
        pdfLayoutParams.addRule(RelativeLayout.BELOW, toolbarPdfMin.id)
        val pdf = arguments?.getString("pdf")
        pdfLayout.fromAsset(pdf).load()
    }
}
