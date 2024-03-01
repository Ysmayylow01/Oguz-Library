package oguz.library.etut.fragment.books
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.adapter.FavMinimAdapter
import oguz.library.etut.db.DatabaseHelperK
import oguz.library.etut.fragment.minimum.PdfMinimumsFragment
import oguz.library.etut.item.Nofavorite
import oguz.library.etut.item.Toolbar
import oguz.library.etut.model.FavModel

class BooksFavoriteFragment : Fragment() {
    private lateinit var recyclerViewFav: RecyclerView
    private lateinit var containerFavBooks : LinearLayout
    private lateinit var noFavContainer : Nofavorite
    private lateinit var toolbarFavorite : Toolbar
    private lateinit var favDB: DatabaseHelperK
    private lateinit var favAdapter: FavMinimAdapter
    private var arrayListFav = ArrayList<FavModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        setContainerBooksFavorite()
        recyclerViewFav.setHasFixedSize(true)
        recyclerViewFav.layoutManager = LinearLayoutManager(requireContext())
        favDB = DatabaseHelperK(requireContext())
        getAllFavorite()
        return containerFavBooks
    }

    private fun setContainerBooksFavorite() {
        toolbarFav()
        noFavorite()
        recyclerViewFavBooks()
        containerFavBooks = LinearLayout(requireContext())
        containerFavBooks.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerFavBooks.orientation = LinearLayout.VERTICAL
        containerFavBooks.isFocusable = true
        containerFavBooks.isClickable = true
        containerFavBooks.setBackgroundColor(resources.getColor(R.color.background))
        containerFavBooks.addView(toolbarFavorite)
        containerFavBooks.addView(noFavContainer)
        containerFavBooks.addView(recyclerViewFav)

    }

    private fun noFavorite() {
        noFavContainer = Nofavorite(requireContext())

    }

    private fun toolbarFav() {
        toolbarFavorite = Toolbar(requireContext())
        toolbarFavorite.backIcon.setImageResource(R.drawable.backstack)
        toolbarFavorite.titleTextToolbar.text = getString(R.string.favorite)
        toolbarFavorite.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun recyclerViewFavBooks() {
        recyclerViewFav = RecyclerView(requireContext())
        recyclerViewFav.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerViewFav.setBackgroundColor(resources.getColor(R.color.background))
    }

    private fun getAllFavorite() {
        arrayListFav = ArrayList()
        val cursor: Cursor? = favDB.readAllData33()
        if (cursor?.count == 0) {
            noFavContainer.visibility = View.VISIBLE
        } else {
            while (cursor?.moveToNext() == true) {
                val themeModel = FavModel()
                themeModel.key_id = cursor?.getString(0)
                themeModel.title = cursor.getString(1)
                themeModel.image = cursor.getInt(3)
                themeModel.subtitle = cursor.getString(4)
                themeModel.pdf = cursor.getString(5)
                arrayListFav.add(themeModel)
                noFavContainer.visibility = View.GONE
            }

            favAdapter = FavMinimAdapter(requireContext(), arrayListFav, onClickListenerDelete, onClickListener)
            recyclerViewFav.adapter = favAdapter
            favAdapter.notifyDataSetChanged()
        }
    }

    private val onClickListenerDelete = View.OnClickListener { view ->
        val pos = view.tag as Int
        favDB.deleteItem33(arrayListFav[pos].key_id!!)
        arrayListFav.removeAt(pos)
        favAdapter.setItems(arrayListFav)
        favAdapter.notifyDataSetChanged()
    }

    private val onClickListener = View.OnClickListener { view ->
        val pos = recyclerViewFav.getChildAdapterPosition(view)
        if (pos != RecyclerView.NO_POSITION) {
            val favModel = arrayListFav[pos]
            val fragment = PdfMinimumsFragment()
            val bundle = Bundle()
            bundle.putString("title", favModel.title)
            bundle.putString("subtitle", favModel.subtitle)
            bundle.putString("pdf", favModel.pdf)
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_main, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        getAllFavorite()
    }
}
