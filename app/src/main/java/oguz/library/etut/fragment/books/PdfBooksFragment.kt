package oguz.library.etut.fragment.books

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView

class PdfBooksFragment : Fragment() {

    private lateinit var pdfContainer: RelativeLayout
    private lateinit var pdfLayout: PDFView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val title = arguments?.getString("title")
        val subtitle = arguments?.getString("subtitle")
        val imageResourceId = arguments?.getInt("image")

        setPdfRerlative()
        return pdfContainer
    }

    private fun setPdfRerlative() {
        pdfView(requireContext(), null)
        pdfContainer = RelativeLayout(requireContext())
        pdfContainer.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        pdfContainer.addView(pdfLayout)
    }

    private fun pdfView(context: Context, attrs: AttributeSet?) {
        pdfLayout = PDFView(context, attrs)
        pdfLayout.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val pdf = arguments?.getString("pdf")
        pdfLayout.fromAsset(pdf).load()

    }
}