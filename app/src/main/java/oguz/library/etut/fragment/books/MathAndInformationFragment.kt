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

class MathAndInformationFragment : Fragment() {
    private lateinit var mathLinearContainer: RelativeLayout
    private lateinit var mathRecyclerView: RecyclerView
    private lateinit var minimumsAdapter: MinimumsAdapter
    private var themeModels = ArrayList<MinimumsModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUPLinearContainer()

        setUpBooks()
        return mathLinearContainer
    }

    private fun setUpBooks() {
        mathRecyclerView.setHasFixedSize(true)
        mathRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        themeModels = readJSONFromFile(requireContext()) as ArrayList<MinimumsModel>
        minimumsAdapter = MinimumsAdapter(themeModels, requireContext())
        mathRecyclerView.adapter = minimumsAdapter
    }
    private fun readJSONFromFile(context: Context): List<MinimumsModel> {
        themeModels = ArrayList()
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
            if (category=="math"){
                themeModels.add(data)
            }

        }

        return themeModels
    }


    private fun setUPLinearContainer() {
        setUpRecyclerViewMath()
        mathLinearContainer = RelativeLayout(requireContext())
        mathLinearContainer.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mathLinearContainer.setBackgroundColor(resources.getColor(R.color.background))
        mathLinearContainer.addView(mathRecyclerView)

    }

    private fun setUpRecyclerViewMath() {
        mathRecyclerView = RecyclerView(requireContext())
        val mathRecyclerViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mathRecyclerView.layoutParams = mathRecyclerViewParams
        mathRecyclerView.id = View.generateViewId()
    }


}