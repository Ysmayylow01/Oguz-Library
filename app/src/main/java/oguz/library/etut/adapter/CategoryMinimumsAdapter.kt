package oguz.library.etut.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import oguz.library.etut.fragment.books.ArtificialFragment
import oguz.library.etut.fragment.books.CSITFragment
import oguz.library.etut.fragment.books.MathAndInformationFragment
import oguz.library.etut.fragment.minimum.AIMinimumsFragment
import oguz.library.etut.fragment.minimum.CSITMinimumsFragment
import oguz.library.etut.fragment.minimum.MathMinimumsFragment
import oguz.library.etut.fragment.minimum.MinimumsFragment

class CategoryMinimumsAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MathMinimumsFragment()
            1 -> CSITMinimumsFragment()
            2 -> AIMinimumsFragment()
            else -> MinimumsFragment()

        }
    }
}
