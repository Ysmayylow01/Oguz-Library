package oguz.library.etut.fragment.books

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import oguz.library.etut.R
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.StaticMethods.Companion.replaceFragment
import oguz.library.etut.adapter.CategoryBooksAdapter
import oguz.library.etut.item.BookItem

class BooksFragment : Fragment() {
    private lateinit var linearContainerBooks: LinearLayout
    private lateinit var toolbarLineHorizontal: LinearLayout
    private lateinit var backIconBooks: ImageView
    private lateinit var likeIconBook: ImageView
    private lateinit var searchIcon: ImageView
    private lateinit var searchViewBooks: LinearLayout
    private lateinit var tabLayoutBooks: TabLayout
    private lateinit var viewPagerCatBooks: ViewPager2
    private lateinit var searchHint: TextView
    private val tabTitles = arrayListOf(
        R.string.amaly_matematika,
        R.string.informatika_we_hasaplayys,
        R.string.emeli_an
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setConBooks()


        setUpViewPagerAndTabs()



        return linearContainerBooks
    }


    private fun setUpViewPagerAndTabs() {
        viewPagerCatBooks.adapter = CategoryBooksAdapter(this)
        TabLayoutMediator(tabLayoutBooks, viewPagerCatBooks) { tab, position ->
            tab.setText(tabTitles[position])
        }.attach()
    }

    private fun onClick() {
        backIconBooks.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        likeIconBook.setOnClickListener {
            replaceFragment(parentFragmentManager, BooksFavoriteFragment(), R.id.frame_main)

        }
        searchViewBooks.setOnClickListener {
            replaceFragment(parentFragmentManager, SearchBooksFragment(), R.id.frame_main)
        }


    }


    private fun setConBooks() {
        toolbarBooks()
        tabCatBooks()
        viewPagerCat()
        onClick()
        linearContainerBooks = LinearLayout(requireContext())
        linearContainerBooks.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearContainerBooks.orientation = LinearLayout.VERTICAL
        linearContainerBooks.setBackgroundColor(resources.getColor(R.color.background))
        linearContainerBooks.isClickable = true
        linearContainerBooks.isFocusable = true
        linearContainerBooks.addView(toolbarLineHorizontal)
        linearContainerBooks.addView(tabLayoutBooks)
        linearContainerBooks.addView(viewPagerCatBooks)
    }

    private fun viewPagerCat() {
        viewPagerCatBooks = ViewPager2(requireContext())
        viewPagerCatBooks.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )


    }

    private fun tabCatBooks() {
        tabLayoutBooks = TabLayout(requireContext())
        val tabLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tabLayoutBooks.layoutParams = tabLayoutParams
        tabLayoutBooks.setBackgroundColor(resources.getColor(R.color.background))
        tabLayoutBooks.tabIndicatorAnimationMode = TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC
        tabLayoutBooks.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayoutBooks.setTabTextColors(
            getColor(requireContext(), R.color.unselected),
            getColor(requireContext(), R.color.textBlue)
        )
        tabLayoutBooks.tabRippleColor = ContextCompat.getColorStateList(
            requireContext(),
            R.color.shimmertext
        )
        tabLayoutBooks.setSelectedTabIndicatorColor(getColor(requireContext(), R.color.textBlue))


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun toolbarBooks() {
        booksBackIcon()
        booksSearchView()
        booksLikeIcon()
        toolbarLineHorizontal = LinearLayout(requireContext())
        val toolbarLineHorizontalParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        toolbarLineHorizontal.layoutParams = toolbarLineHorizontalParams
        toolbarLineHorizontal.orientation = LinearLayout.HORIZONTAL
        toolbarLineHorizontalParams.setMargins(10)
        toolbarLineHorizontal.setBackgroundColor(resources.getColor(R.color.background))
        toolbarLineHorizontal.addView(backIconBooks)
        toolbarLineHorizontal.addView(searchViewBooks)
        toolbarLineHorizontal.addView(likeIconBook)
    }

    private fun booksLikeIcon() {
        likeIconBook = ImageView(requireContext())
        val likeIconBooksParams = LinearLayout.LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        likeIconBook.layoutParams = likeIconBooksParams

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            likeIconBook.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        likeIconBook.setImageResource(R.drawable.like_border)
        likeIconBooksParams.gravity = Gravity.CENTER
    }

    private fun booksSearchView() {
        searchIcon()
        searchHint()
        searchViewBooks = LinearLayout(requireContext())
        val searchViewBooksParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        )
        searchViewBooks.layoutParams = searchViewBooksParams
        searchViewBooksParams.gravity = Gravity.CENTER
        searchViewBooksParams.setMargins(
            dpToPx(resources, 5),
            dpToPx(resources, 0),
            dpToPx(resources, 5),
            dpToPx(resources, 0)
        )
        searchViewBooks.orientation = LinearLayout.HORIZONTAL
        searchViewBooks.setPadding(dpToPx(resources, 6))
        searchViewBooks.background = resources.getDrawable(R.drawable.edit_shape)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            searchViewBooks.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        searchViewBooks.addView(searchIcon)
        searchViewBooks.addView(searchHint)
    }

    private fun searchHint() {
        searchHint = TextView(requireContext())
        val searchHintParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        searchHint.layoutParams = searchHintParams
        searchHint.text = getString(R.string.gozle)
        searchHint.textSize = 16f
        searchHintParams.gravity = Gravity.CENTER
        searchHintParams.marginStart = dpToPx(resources, 10)
        searchHint.setTextColor(resources.getColor(R.color.unselected))
    }

    private fun searchIcon() {
        searchIcon = ImageView(requireContext())
        val searchIconParams = LinearLayout.LayoutParams(
            dpToPx(resources, 30),
            dpToPx(resources, 30)
        )
        searchIcon.layoutParams = searchIconParams
        searchIcon.setImageResource(R.drawable.search64)
        searchIcon.imageTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.unselected)

    }

    private fun booksBackIcon() {
        backIconBooks = ImageView(requireContext())
        val backIconBookParams = LinearLayout.LayoutParams(
            dpToPx(resources, 50),
            dpToPx(resources, 50)
        )
        backIconBooks.layoutParams = backIconBookParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIconBooks.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        backIconBooks.setImageResource(R.drawable.backstack)
        backIconBookParams.gravity = Gravity.CENTER


    }
}