package oguz.library.etut.fragment.books

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.adapter.MinimumsAdapter
import oguz.library.etut.model.MinimumsModel
import org.json.JSONArray

class CSITFragment: Fragment() {
    private lateinit var csitLinearContainer: RelativeLayout
    private lateinit var csitRecyclerView: RecyclerView
    private lateinit var booksAdapter: MinimumsAdapter
    private var booksItem =ArrayList<MinimumsModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUPLinearContainer()

        setUpBooks()
        return csitLinearContainer
    }

    private fun setUpBooks() {
        csitRecyclerView.setHasFixedSize(true)
        csitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
         booksItem = readJSONFromFile(requireContext()) as ArrayList<MinimumsModel>
        booksAdapter = MinimumsAdapter(booksItem, requireContext())
        csitRecyclerView.adapter = booksAdapter
    }
    private fun readJSONFromFile(context: Context): List<MinimumsModel> {
        val jsonFile = context.resources.openRawResource(R.raw.book)
        val jsonData = jsonFile.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonData)


        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val image = jsonObject.getString("image")
            val imageName = context.resources.getIdentifier(image, "drawable", context.packageName)
            val title = jsonObject.getString("title")
            val key_id = jsonObject.getString("key_id")
            val subtitle = jsonObject.getString("subtitle")
            val category = jsonObject.getString("category")
            val pdf = jsonObject.getString("pdf")
            val data = MinimumsModel(imageName, title, key_id,subtitle, category,pdf)
            if (category=="information"){
                booksItem.add(data)
            }
        }

        return booksItem
    }


    private fun setUPLinearContainer() {
        setUpRecyclerViewMath()
        csitLinearContainer = RelativeLayout(requireContext())
        csitLinearContainer.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        csitLinearContainer.setBackgroundColor(resources.getColor(R.color.background))
        csitLinearContainer.addView(csitRecyclerView)

    }

    private fun setUpRecyclerViewMath() {
        csitRecyclerView = RecyclerView(requireContext())
        val csitRecyclerViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        csitRecyclerView.layoutParams = csitRecyclerViewParams
    }

}