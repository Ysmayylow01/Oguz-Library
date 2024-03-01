package oguz.library.etut.fragment.minimum

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
import oguz.library.etut.adapter.BooksAdapter
import oguz.library.etut.adapter.MinAdapter
import oguz.library.etut.adapter.MinimumsAdapter
import oguz.library.etut.model.Books
import oguz.library.etut.model.Minimums
import org.json.JSONArray

class MathMinimumsFragment : Fragment() {
    private lateinit var containerMathMin: LinearLayout
    private lateinit var recyclerMathMin: RecyclerView
    private var minimumsItems = ArrayList<Minimums>()
    private lateinit var minimumsAdapter: MinAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContainerMathMin()
        readData()
        return containerMathMin

    }
    private fun readData() {
       recyclerMathMin.setHasFixedSize(true)
        recyclerMathMin.layoutManager = LinearLayoutManager(requireContext())
        val minItems = readJSONFromFile(requireContext())
        minimumsAdapter= MinAdapter(requireContext() , minItems as ArrayList<Minimums>)
        recyclerMathMin.adapter = minimumsAdapter
    }

    private fun readJSONFromFile(context: Context): List<Minimums> {
        val jsonFile = context.resources.openRawResource(R.raw.minimums)
        val jsonData = jsonFile.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonData)
        val dataList = mutableListOf<Minimums>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val title = jsonObject.getString("title")
            val image = jsonObject.getString("image")
            val imageName = context.resources.getIdentifier(image, "drawable", context.packageName)
            val category = jsonObject.getString("category")
            val pdf = jsonObject.getString("pdf")

            val data = Minimums(id, title, imageName,category,pdf)
            if (category=="math"){
                dataList.add(data)
            }

        }

        return dataList
    }

    private fun setContainerMathMin() {
        setUpRecyclerViewMathMin()
        containerMathMin = LinearLayout(requireContext())
        containerMathMin.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerMathMin.setBackgroundColor(resources.getColor(R.color.background))
        containerMathMin.addView(recyclerMathMin)


    }
    private fun setUpRecyclerViewMathMin() {
       recyclerMathMin = RecyclerView(requireContext())
        val aiRecyclerViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        recyclerMathMin.layoutParams = aiRecyclerViewParams
        recyclerMathMin.id = View.generateViewId()
    }


}