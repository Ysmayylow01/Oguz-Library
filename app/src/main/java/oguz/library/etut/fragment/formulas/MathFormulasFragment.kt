package oguz.library.etut.fragment.formulas


import android.os.Build
import android.os.Bundle
import android.view.Display.Mode
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.StaticMethods.Companion.navigateBack
import oguz.library.etut.adapter.FormulasAdapter
import oguz.library.etut.item.Toolbar
import oguz.library.etut.model.Model_formula
import java.util.*
import kotlin.collections.ArrayList


class MathFormulasFragment : Fragment() {

    private lateinit var recyclerViewMathFormulas: RecyclerView
    private var matemList = ArrayList<Model_formula>()
    private lateinit var containerMathFormulas: LinearLayout
    private lateinit var toolbarMath: LinearLayout
    private lateinit var adapter: FormulasAdapter
    private lateinit var backIconMathToolbar : ImageView
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        containerMathFormulas()
        addDataToList()
        onclick()
        searchData()
        return containerMathFormulas
    }

    private fun searchData() {
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }




    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Model_formula>()
            for (i in matemList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
                else  {

                }
            }

            if (filteredList.isEmpty()) {
                adapter.setFilteredList(filteredList)
                Toast.makeText(requireContext(), getString(R.string.tapylmady) , Toast.LENGTH_SHORT).show()

            }
            else {
                adapter.setFilteredList(filteredList)
            }

        }
    }

    private fun onclick() {
        backIconMathToolbar.setOnClickListener {
            navigateBack(parentFragmentManager, activity)
        }
    }

    private fun containerMathFormulas() {

        containerMathFormulas = LinearLayout(requireContext())
        containerMathFormulas.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerMathFormulas.orientation = LinearLayout.VERTICAL
        containerMathFormulas.setBackgroundColor(resources.getColor(R.color.background))
        toolbarMath = LinearLayout(requireContext())
        toolbarMath.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        toolbarMath.orientation = LinearLayout.HORIZONTAL
        toolbarMath.setPadding(dpToPx(resources, 8))
        toolbarMath.setBackgroundColor(resources.getColor(R.color.background))
        backIconMathToolbar = ImageView(requireContext())
        val backIconParams = LinearLayout.LayoutParams(
            StaticMethods.dpToPx(resources, 50),
            StaticMethods.dpToPx(resources, 50)
        )
        backIconMathToolbar.layoutParams = backIconParams
        backIconMathToolbar.setImageResource(R.drawable.backstack)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIconMathToolbar.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
        }
        backIconParams.gravity = Gravity.CENTER

        searchView = SearchView(requireContext())
        val  searchViewParams = LinearLayout.LayoutParams(
            0 , LinearLayout.LayoutParams.WRAP_CONTENT , 1f
        )
        searchView.layoutParams = searchViewParams
        searchView.background = resources.getDrawable(R.drawable.edit_shape)
        searchView.isIconifiedByDefault = false
        searchView.queryHint = getString(R.string.gozle)


        toolbarMath.addView(backIconMathToolbar)
        toolbarMath.addView(searchView)

        recyclerViewMathFormulas = RecyclerView(requireContext())
        recyclerViewMathFormulas.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerViewMathFormulas.setBackgroundColor(resources.getColor(R.color.background))
        recyclerViewMathFormulas.setHasFixedSize(true)
        recyclerViewMathFormulas.layoutManager = LinearLayoutManager(requireContext())
        containerMathFormulas.addView(toolbarMath)
        containerMathFormulas.addView(recyclerViewMathFormulas)
    }

    private fun addDataToList() {
        matemList = ArrayList()
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Esasy formula",
                "Algebra",
                R.drawable.esasyformulalar
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Görkezijili derejäniň häsiýetleri",
                "Algebra",
                R.drawable.derejegorkeziji
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Derejäniň häsiýetleri",
                "Algebra",
                R.drawable.derejehasiyetleri
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Gysga köpeltmek formulalar",
                "Algebra",
                R.drawable.gysgakopeltmek
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Binomyň teoremasy",
                "Algebra",
                R.drawable.binomynteoremasy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Ady droplar",
                "Algebra",
                R.drawable.adydroplar
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Deňlikler",
                "Algebra",
                R.drawable.denlikler
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Kompleks sanlar",
                "Algebra",
                R.drawable.komsanlar
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Kompleks sanlaryň häsiýetleri",
                "Algebra",
                R.drawable.komplsanhas
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Kompleks sanlaryň ýazgysy",
                "Algebra",
                R.drawable.kompsanynyazgysy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Kwadrat deňlemäniň formulasy",
                "Algebra",
                R.drawable.kwdenlekoklertapfor
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Logarifmiň häsiýetleri",
                "Algebra",
                R.drawable.logarifmnhasi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.basical,
                "Jemleme teoremalary",
                "Algebra",
                R.drawable.jemlemeteo
            )
        )

        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Köpburçluklar",
                "Geometriýa",
                R.drawable.kopbur
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Tegelek",
                "Geometriýa",
                R.drawable.tegelek
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Üçburçlugyň meýdany we perimetri",
                "Geometriýa",
                R.drawable.ucburclukperimetr
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Dörtburçluk",
                "Geometriýa",
                R.drawable.dortbur
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Geometrik jisimleriň göwrümi",
                "Geometriýa",
                R.drawable.geometjisigowr
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Iki nokadyň aralygy",
                "Geometriýa",
                R.drawable.ikinokadynar
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Göni çyzygyň deňlemesi",
                "Geometriýa",
                R.drawable.gonicyzygyndenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Göni çyzygyň burç koefsiýenti",
                "Geometriýa",
                R.drawable.gonicyzburkof
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Iki nokadyň arasyndaky orta nokat",
                "Geometriýa",
                R.drawable.ikinokadaraskyortanok
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Töweregiň deňlemesi",
                "Geometriýa",
                R.drawable.toweregindenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Ellips deňlemesi",
                "Geometriýa",
                R.drawable.ellipsdenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Ellips merkezi",
                "Geometriýa",
                R.drawable.ellipsmerkezi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Parabolanyň umumy deňlemesi",
                "Geometriýa",
                R.drawable.parabolanynumumdenle
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.geometri,
                "Giperbola deňlemesi",
                "Geometriýa",
                R.drawable.giperboladenle
            )
        )


        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Pifagoryň teoremasy",
                "Trigonometriýa",
                R.drawable.pifagorteor
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Esasy trigonomertik formulalar",
                "Trigonometriýa",
                R.drawable.esasytrigonomform
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Trigonometrik funksiýalar",
                "Trigonometriýa",
                R.drawable.trigonfunks
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Napiýeriň teoremasy",
                "Trigonometriýa",
                R.drawable.napierteorema
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Burçlaryň trigonometrik funksiýalary",
                "Trigonometriýa",
                R.drawable.umumburtrigfunk
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Delambre Gausyň analogiýasy",
                "Trigonometriýa",
                R.drawable.delamgausynanalogi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Burçlar üçin kosinuslar usuly",
                "Trigonometriýa",
                R.drawable.burcucinkosinusul
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.trigon,
                "Ýarym burç",
                "Trigonometriýa",
                R.drawable.yarymburc
            )
        )

        matemList.add(
            Model_formula(
                R.drawable.linearalgebra,
                "Matrisaň häsiýetleri",
                "Çyzykly algebra",
                R.drawable.matrisahasiyet
            )
        )

        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Integralyň esasy häsiýetleri",
                "Integral",
                R.drawable.integesasyhasiyet
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Rasional funksiýanyň integraly - 1",
                "Integral",
                R.drawable.rasionalfunkintegraly1
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Rasional funksiýanyň integraly - 2",
                "Integral",
                R.drawable.rasionalfunkintegraly2
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Trigonometik funksiýany integrirlemek - 1",
                "Integral",
                R.drawable.trigonfunkintegrir1
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Trigonometik funksiýany integrirlemek - 2",
                "Integral",
                R.drawable.trigonfunkintegrir2
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Trigonometik funksiýany integrirlemek - 3",
                "Integral",
                R.drawable.trigonfunkintegrir3
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Dekart koordinatalar",
                "Integral",
                R.drawable.dekartkoor
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Derejeli we logarifmik funksiýanyň integraly",
                "Integral",
                R.drawable.derejeliwelogarffunkintegraly
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Differensial operator üýtgeýjisi",
                "Integral",
                R.drawable.differensialoperatoruytgey
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Fubiniň teoremasy",
                "Integral",
                R.drawable.fubininteoremasy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Gausyň teoremasy",
                "Integral",
                R.drawable.gausteorema
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Giperbolik funksiýanyň integraly",
                "Integral",
                R.drawable.giperbolikfunkinteg
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Skalýar meýdany",
                "Integral",
                R.drawable.skalyarmeydany
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.integral,
                "Ugrukdyryjy önümler",
                "Integral",
                R.drawable.ugrukdyryjyonumler
            )
        )


        adapter = FormulasAdapter(matemList)
        recyclerViewMathFormulas.adapter = adapter


    }



}










