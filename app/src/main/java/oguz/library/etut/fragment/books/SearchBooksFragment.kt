package oguz.library.etut.fragment.books

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.adapter.MinimumsAdapter
import oguz.library.etut.model.MinimumsModel
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class SearchBooksFragment:Fragment() {
    private lateinit var backIconSearch : ImageView
    private lateinit var toolbarSearchBook : LinearLayout
    private lateinit var searchContainer : LinearLayout
    private lateinit var searchViewBooks : SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksAdapter: MinimumsAdapter
    private var bookList = ArrayList<MinimumsModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUISearchContainer()
        setSearchData()

        searchViewBooks.setOnQueryTextListener(object :

            SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                filterList(newText)
                return true
            }

        })

        return  searchContainer
    }
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<MinimumsModel>()
            for (i in bookList) {

                if (i.title?.lowercase(Locale.ROOT)!!.contains(query)) {
                    filteredList.add(i)

                }
                else if (i.subtitle?.lowercase(Locale.ROOT)!!.contains(query)){

                    filteredList.add(i)
                }

                if (filteredList.isEmpty()) {
                    booksAdapter.setFilteredList(filteredList)




                } else {
                    booksAdapter.setFilteredList(filteredList)

                }


            }
        }

    }
    private fun setSearchData() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bookList = readJSONFromFile(requireContext()) as ArrayList<MinimumsModel>
        booksAdapter =  MinimumsAdapter(bookList, requireContext())
        recyclerView.adapter = booksAdapter

    }
    @SuppressLint("SuspiciousIndentation")
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

            val data = MinimumsModel(imageName, title, key_id, subtitle,category,pdf)

                bookList.add(data)


        }

        return bookList
    }

    private fun setUISearchContainer() {

       toolBarSearch()
        recyclerSearchView()
        searchContainer = LinearLayout(requireContext())
        searchContainer.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        searchContainer.orientation = LinearLayout.VERTICAL
        searchContainer.setBackgroundColor(resources.getColor(R.color.background))
        searchContainer.isClickable = true
        searchContainer.isFocusable = true
        searchContainer.addView(toolbarSearchBook)
        searchContainer.addView(recyclerView)
    }
    private fun toolBarSearch() {
        backIconSearchMin()
        searchBooks()
        toolbarSearchBook = LinearLayout(requireContext())
        toolbarSearchBook.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        toolbarSearchBook.orientation = LinearLayout.HORIZONTAL
        toolbarSearchBook.addView(backIconSearch)
        toolbarSearchBook.addView(searchViewBooks)
    }

    private fun searchBooks() {
        searchViewBooks = SearchView(requireContext())
        val searchViewMinParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        )
        searchViewBooks.layoutParams = searchViewMinParams
        searchViewBooks.background = resources.getDrawable(R.drawable.edit_shape)
        searchViewBooks.isIconified = false
        searchViewBooks.queryHint = getString(R.string.gozle)
        searchViewMinParams.setMargins(dpToPx(resources,10))

    }

    private fun backIconSearchMin() {
        backIconSearch = ImageView(requireContext())
        val backIconParams = LinearLayout.LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        backIconSearch.layoutParams = backIconParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIconSearch.foreground= ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        backIconParams.gravity = Gravity.CENTER
        backIconParams.marginStart = dpToPx(resources, 10)
        backIconSearch.setImageResource(R.drawable.backstack)
        backIconSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun recyclerSearchView() {
        recyclerView = RecyclerView(requireContext())
        recyclerView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

    }

  /*  private fun searchViewBook() {
        searchViewBooks= SearchView(requireContext())
       val  searchViewBooksParams =LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        searchViewBooks.layoutParams = searchViewBooksParams
        searchViewBooksParams.setMargins(dpToPx(resources,10))
        searchViewBooks.queryHint = getString(R.string.gozle)
        searchViewBooks.isIconifiedByDefault = false
        val searchFrame = FrameBackground(requireContext(), resources.getColor(R.color.white))
        searchViewBooks.background = searchFrame

    }*/


}