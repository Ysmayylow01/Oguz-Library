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

class ArtificialFragment : Fragment() {
    private lateinit var aiLinearContainer: RelativeLayout
    private lateinit var aiRecyclerView: RecyclerView
    private var booksItems = ArrayList<MinimumsModel>()
    private lateinit var booksAdapter: MinimumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUPLinearContainer()

    readData()
        return aiLinearContainer
    }

    private fun readData() {
        aiRecyclerView.setHasFixedSize(true)
        aiRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        booksItems = readJSONFromFile(requireContext()) as ArrayList<MinimumsModel>
        booksAdapter = MinimumsAdapter(booksItems, requireContext())
        aiRecyclerView.adapter = booksAdapter
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

            val data = MinimumsModel(imageName, title,key_id,subtitle,category,pdf)
            if (category=="ai"){
                booksItems.add(data)
            }

        }

        return booksItems
    }


    private fun setUPLinearContainer() {
        setUpRecyclerViewAI()
        aiLinearContainer = RelativeLayout(requireContext())
        aiLinearContainer.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        aiLinearContainer.setBackgroundColor(resources.getColor(R.color.background))
        aiLinearContainer.addView(aiRecyclerView)

    }

    private fun setUpRecyclerViewAI() {
        aiRecyclerView = RecyclerView(requireContext())
        val aiRecyclerViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        aiRecyclerView.layoutParams = aiRecyclerViewParams
        aiRecyclerView.id = View.generateViewId()
    }
}
