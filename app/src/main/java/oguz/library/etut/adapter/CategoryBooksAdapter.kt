package oguz.library.etut.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import oguz.library.etut.fragment.minimum.MinimumsFragment
import oguz.library.etut.fragment.books.ArtificialFragment
import oguz.library.etut.fragment.books.CSITFragment
import oguz.library.etut.fragment.books.MathAndInformationFragment

class CategoryBooksAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MathAndInformationFragment()
            1 -> CSITFragment()
            2 -> ArtificialFragment()
            else -> MinimumsFragment()

        }
    }
}
