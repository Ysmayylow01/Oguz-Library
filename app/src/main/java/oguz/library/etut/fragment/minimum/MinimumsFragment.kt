package oguz.library.etut.fragment.minimum


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
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.adapter.CategoryMinimumsAdapter
import oguz.library.etut.fragment.quiz.QuizFragment
import oguz.library.etut.item.BookItem


class MinimumsFragment : Fragment() {
    private lateinit var linearContainerMinimums: LinearLayout
    private lateinit var toolbarLineHorizontal: LinearLayout
    private lateinit var backIconMinimums: ImageView
    private lateinit var likeIconMinimums: ImageView
    private lateinit var searchIcon: ImageView
    private lateinit var searchViewMinimums: LinearLayout
    private lateinit var tabLayoutMinimums: TabLayout
    private lateinit var viewPagerCatMinimums: ViewPager2
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
        onClick()

        setUpViewPagerAndTabs()



        return linearContainerMinimums
    }

    private fun setUpViewPagerAndTabs() {
        viewPagerCatMinimums.adapter = CategoryMinimumsAdapter(this)
        TabLayoutMediator(tabLayoutMinimums, viewPagerCatMinimums) { tab, position ->
            tab.setText(tabTitles[position])
        }.attach()
    }

    private fun onClick() {
        backIconMinimums.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        likeIconMinimums.setOnClickListener {
            StaticMethods.replaceFragment(parentFragmentManager, QuizFragment(), R.id.frame_main)
        }

        searchViewMinimums.setOnClickListener {
            StaticMethods.replaceFragment(
                parentFragmentManager,
                SearchMinimumsFragment(),
                R.id.frame_main
            )
        }


    }


    private fun setConBooks() {
        toolbarBooks()
        tabCatBooks()
        viewPagerCat()


        linearContainerMinimums= LinearLayout(requireContext())
        linearContainerMinimums.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearContainerMinimums.orientation = LinearLayout.VERTICAL
        linearContainerMinimums.setBackgroundColor(resources.getColor(R.color.background))
        linearContainerMinimums.addView(toolbarLineHorizontal)
        val book = BookItem(requireContext())
        book.imageBookItem.setImageResource(R.drawable.oguzlib1)
        linearContainerMinimums.addView(tabLayoutMinimums)
        linearContainerMinimums.addView(viewPagerCatMinimums)


    }

    private fun viewPagerCat() {
        viewPagerCatMinimums = ViewPager2(requireContext())
        viewPagerCatMinimums.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )


    }

    private fun tabCatBooks() {
        tabLayoutMinimums = TabLayout(requireContext())
        val tabLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tabLayoutMinimums.layoutParams = tabLayoutParams
        tabLayoutMinimums.setBackgroundColor(resources.getColor(R.color.background))
        tabLayoutMinimums.tabIndicatorAnimationMode = TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC
        tabLayoutMinimums.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayoutMinimums.setTabTextColors(
            ContextCompat.getColor(requireContext(), R.color.unselected),
            ContextCompat.getColor(requireContext(), R.color.textBlue)
        )
        tabLayoutMinimums.tabRippleColor = ContextCompat.getColorStateList(
            requireContext(),
            R.color.shimmertext
        )
        tabLayoutMinimums.setSelectedTabIndicatorColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.textBlue
            )
        )


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
        toolbarLineHorizontal.addView(backIconMinimums)
        toolbarLineHorizontal.addView(searchViewMinimums)
        toolbarLineHorizontal.addView(likeIconMinimums)

    }

    private fun booksLikeIcon() {
        likeIconMinimums = ImageView(requireContext())
        val likeIconBooksParams = LinearLayout.LayoutParams(
            StaticMethods.dpToPx(resources, 50),
            StaticMethods.dpToPx(resources, 50)
        )
        likeIconMinimums.layoutParams = likeIconBooksParams
        likeIconMinimums.setImageResource(R.drawable.ic_quiz)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            likeIconMinimums.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        likeIconBooksParams.gravity = Gravity.CENTER
    }

    private fun booksSearchView() {
        searchIcon()
        searchHint()
        searchViewMinimums = LinearLayout(requireContext())
        val searchViewBooksParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        )
        searchViewMinimums.layoutParams = searchViewBooksParams
        searchViewBooksParams.gravity = Gravity.CENTER
        searchViewBooksParams.setMargins(
            StaticMethods.dpToPx(resources, 5),
            StaticMethods.dpToPx(resources, 0),
            StaticMethods.dpToPx(resources, 5),
            StaticMethods.dpToPx(resources, 0)
        )
        searchViewMinimums.orientation = LinearLayout.HORIZONTAL
        searchViewMinimums.setPadding(StaticMethods.dpToPx(resources, 6))
        searchViewMinimums.background = resources.getDrawable(R.drawable.edit_shape)
        searchViewMinimums.addView(searchIcon)
        searchViewMinimums.addView(searchHint)
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
        searchHintParams.marginStart = StaticMethods.dpToPx(resources, 10)
        searchHint.setTextColor(resources.getColor(R.color.unselected))
    }

    private fun searchIcon() {
        searchIcon = ImageView(requireContext())
        val searchIconParams = LinearLayout.LayoutParams(
            StaticMethods.dpToPx(resources, 30),
            StaticMethods.dpToPx(resources, 30)
        )
        searchIcon.layoutParams = searchIconParams
        searchIcon.setImageResource(R.drawable.search64)
        searchIcon.imageTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.unselected)

    }

    private fun booksBackIcon() {
        backIconMinimums = ImageView(requireContext())
        val backIconBookParams = LinearLayout.LayoutParams(
            StaticMethods.dpToPx(resources, 50),
            StaticMethods.dpToPx(resources, 50)
        )
        backIconMinimums.layoutParams = backIconBookParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIconMinimums.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        backIconMinimums.setImageResource(R.drawable.backstack)
        backIconBookParams.gravity = Gravity.CENTER


    }
}