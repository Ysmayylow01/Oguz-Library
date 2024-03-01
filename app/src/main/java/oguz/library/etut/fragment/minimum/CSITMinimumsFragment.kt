package oguz.library.etut.fragment.minimum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R

class CSITMinimumsFragment:Fragment() {
    private lateinit var linearArtificialMinimums : LinearLayout
    private lateinit var recyclerArtificialMin : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContainerArtificialMin()
        return linearArtificialMinimums
    }

    private fun setContainerArtificialMin() {
        artificialMinRecycler()
        linearArtificialMinimums = LinearLayout(requireContext())
        linearArtificialMinimums.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearArtificialMinimums.orientation = LinearLayout.VERTICAL
        linearArtificialMinimums.setBackgroundColor(resources.getColor(R.color.background))
        linearArtificialMinimums.addView(recyclerArtificialMin)
    }

    private fun artificialMinRecycler() {
        recyclerArtificialMin = RecyclerView(requireContext())
        recyclerArtificialMin.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerArtificialMin.setHasFixedSize(true)
        recyclerArtificialMin.layoutManager = LinearLayoutManager(requireContext())

    }
}