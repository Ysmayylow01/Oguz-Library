package oguz.library.etut.fragment.minimum

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.adapter.BooksAdapter
import oguz.library.etut.adapter.MinAdapter
import oguz.library.etut.model.Books
import oguz.library.etut.model.Minimums
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class SearchMinimumsFragment:Fragment() {
    private lateinit var containerSearchMinLinear : LinearLayout
    private lateinit var toolbarSearchMin : LinearLayout
    private lateinit var backIconSearch : ImageView
    private lateinit var searchViewMin : SearchView
    private lateinit var recyclerSearchMin : RecyclerView
    private lateinit var minAdapter : MinAdapter
    private var minList = ArrayList<Minimums>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setContainerSearchMin()
        setSearchData()

        searchViewMin.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })



        return containerSearchMinLinear

    }
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Minimums>()
            for (i in minList) {

                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
                else {
                }

                if (filteredList.isEmpty()) {
                    minAdapter.setFilteredList(filteredList)



                } else {
                    minAdapter.setFilteredList(filteredList)
                }


            }
        }

    }
    private fun setSearchData() {
        recyclerSearchMin.setHasFixedSize(true)
        recyclerSearchMin.layoutManager = LinearLayoutManager(requireContext())
        minList = readJSONFromFile(requireContext()) as ArrayList<Minimums>
        minAdapter = MinAdapter(requireContext(), minList )
        recyclerSearchMin.adapter = minAdapter

    }
    @SuppressLint("SuspiciousIndentation")
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

            dataList.add(data)


        }

        return dataList
    }

    private fun setContainerSearchMin() {
        toolBarSearch()
        recyclerViewMinimums()
        containerSearchMinLinear = LinearLayout(requireContext())
        containerSearchMinLinear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        containerSearchMinLinear.setBackgroundColor(resources.getColor(R.color.background))
        containerSearchMinLinear.orientation = LinearLayout.VERTICAL
        containerSearchMinLinear.setPadding(dpToPx(resources, 5))
        containerSearchMinLinear.isClickable = true
        containerSearchMinLinear.isClickable = true
        containerSearchMinLinear.addView(toolbarSearchMin)
        containerSearchMinLinear.addView(recyclerSearchMin)
    }

    private fun recyclerViewMinimums() {
        recyclerSearchMin = RecyclerView(requireContext())
        recyclerSearchMin.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerSearchMin.setBackgroundColor(resources.getColor(R.color.background))
    }

    private fun toolBarSearch() {
        backIconSearchMin()
        searchViewMinimums()
        toolbarSearchMin = LinearLayout(requireContext())
        toolbarSearchMin.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        toolbarSearchMin.orientation = LinearLayout.HORIZONTAL
        toolbarSearchMin.addView(backIconSearch)
        toolbarSearchMin.addView(searchViewMin)
    }

    private fun searchViewMinimums() {
        searchViewMin = SearchView(requireContext())
        val searchViewMinParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        )
        searchViewMin.layoutParams = searchViewMinParams
        searchViewMin.background = resources.getDrawable(R.drawable.edit_shape)
        searchViewMin.isIconified = false
        searchViewMin.queryHint = getString(R.string.gozle)
        searchViewMinParams.setMargins(dpToPx(resources,10))


    }

    private fun backIconSearchMin() {
        backIconSearch = ImageView(requireContext())
        val backIconParams = LinearLayout.LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        backIconSearch.layoutParams = backIconParams
        backIconParams.gravity = Gravity.CENTER
        backIconSearch.setImageResource(R.drawable.backstack)
        backIconSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}