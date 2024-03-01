package oguz.library.etut.fragment.formulas

import android.os.Build
import android.os.Bundle
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
import oguz.library.etut.adapter.FormulasAdapter
import oguz.library.etut.model.Model_formula
import java.util.*
import kotlin.collections.ArrayList

class FizikaFragment : Fragment() {
    private lateinit var containerPhysicsLinear: LinearLayout
    private lateinit var recyclerPhysics: RecyclerView
    private var matemList = ArrayList<Model_formula>()
    private lateinit var adapter: FormulasAdapter
    private lateinit var toolbarMath: LinearLayout
    private lateinit var backIconMathToolbar : ImageView
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        containerPhysics()

        addDataToList()
        onclick()



        return containerPhysicsLinear
    }
    private fun onclick() {
        backIconMathToolbar.setOnClickListener {
            StaticMethods.navigateBack(parentFragmentManager, activity)
        }
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
    private fun containerPhysics() {

        containerPhysicsLinear = LinearLayout(requireContext())
        containerPhysicsLinear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerPhysicsLinear.orientation = LinearLayout.VERTICAL
        containerPhysicsLinear.setBackgroundColor(resources.getColor(R.color.background))

        toolbarMath = LinearLayout(requireContext())
        toolbarMath.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        toolbarMath.orientation = LinearLayout.HORIZONTAL
        toolbarMath.setPadding(StaticMethods.dpToPx(resources, 8))
        toolbarMath.setBackgroundColor(resources.getColor(R.color.background))
        backIconMathToolbar = ImageView(requireContext())
        val backIconParams = LinearLayout.LayoutParams(
            StaticMethods.dpToPx(resources, 50),
            StaticMethods.dpToPx(resources, 50)
        )
        backIconMathToolbar.layoutParams = backIconParams
        backIconMathToolbar.setImageResource(R.drawable.backstack)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backIconMathToolbar.foreground =  ContextCompat.getDrawable(requireContext(), R.drawable.ripple_effect)
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

        recyclerPhysics = RecyclerView(requireContext())
        recyclerPhysics.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerPhysics.setBackgroundColor(resources.getColor(R.color.background))
        recyclerPhysics.setHasFixedSize(true)
        recyclerPhysics.layoutManager = LinearLayoutManager(requireContext())
        containerPhysicsLinear.addView(toolbarMath)
        containerPhysicsLinear.addView(recyclerPhysics)
    }


    private fun addDataToList() {
        matemList = ArrayList()
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Wektor komponentler",
                "Mehanika",
                R.drawable.wektorkomponentler
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Hemişelik tizlik",
                "Mehanika",
                R.drawable.hemiseliktizlik
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Hemişelik tizlenme",
                "Mehanika",
                R.drawable.hemiseliktizlenme
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Erkin gaçma",
                "Mehanika",
                R.drawable.mugtyykylmak
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Dik hereket",
                "Mehanika",
                R.drawable.dikhereket
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Proýeksiýa hereket",
                "Mehanika",
                R.drawable.proyeksiyahereket
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Birmeňzeş tegelek hereket",
                "Mehanika",
                R.drawable.birmenzestegelekhereket
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Yzygiderli tizlenmäniň tegelek hereketi",
                "Mehanika",
                R.drawable.yzygidertizteghereketi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Nýutonyň kanunlary",
                "Mehanika",
                R.drawable.nutonkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Iş energiýa we güýç",
                "Mehanika",
                R.drawable.isenergiyaweguyc
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Çyzykly tizlik häsiýetleri",
                "Mehanika",
                R.drawable.cyzyklytizlikhasiyetleri
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Aýlanma dinamikasy",
                "Mehanika",
                R.drawable.aylanmadinamikasy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.mehan,
                "Nýutonyň agyrlyk kanuny",
                "Mehanika",
                R.drawable.nutonynagyrlykkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Dykyzlyk, Belli agyrlyk güýji",
                "Suwuk mehanika",
                R.drawable.belliagyrlykguyji
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Bellibir göwrüm",
                "Suwuk mehanika",
                R.drawable.bellibirgowrum
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Basyş, Paskalyň kanuny",
                "Suwuk mehanika",
                R.drawable.basys
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Arhimediň ýörelgesi",
                "Suwuk mehanika",
                R.drawable.arhimedyorelge
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Akyş tizligi",
                "Suwuk mehanika",
                R.drawable.akystizligi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Üznüksizlik deňlemesi",
                "Suwuk mehanika",
                R.drawable.uznuksizlikdenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Bernolliniň deňlemesi",
                "Suwuk mehanika",
                R.drawable.bernollinindenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Nasos, Hereketlendirijiniň güýji",
                "Suwuk mehanika",
                R.drawable.nasoshereketlendirijiguyc
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.suwuk,
                "Umumy energiýa deňlemesi",
                "Suwuk mehanika",
                R.drawable.umumyenergiyadenlemesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Periodik, Burç ýygylygy",
                "Tolkunlar",
                R.drawable.periodikburcyygylygy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Garmoniki hereket",
                "Tolkunlar",
                R.drawable.garmonikihereket
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Burç garmoniki hereket",
                "Tolkunlar",
                R.drawable.burcgarmonikihereketi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Ýönekeý maýatnik",
                "Tolkunlar",
                R.drawable.yonekeymayatnik
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Fiziki maýatnik",
                "Tolkunlar",
                R.drawable.fizikimayatnik
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Sinusoidal tolkunlary",
                "Tolkunlar",
                R.drawable.sinusoidaltolkun
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.tolkun,
                "Ses aýratynlyklary",
                "Tolkunlar",
                R.drawable.sesayratynlyklary
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Temperatura häsiýetleri",
                "Termodinamika",
                R.drawable.temperaturahasiyetleri
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Termiki stres",
                "Termodinamika",
                R.drawable.termikistres
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Ýylylyk fazasynyň üýtgemegi",
                "Termodinamika",
                R.drawable.yylylykfazasynynuytgem
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Ýylylyk geçiriji",
                "Termodinamika",
                R.drawable.yylylykgeciriji
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Ýylylyk giňelmesi",
                "Termodinamika",
                R.drawable.yylylykginelmesi
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Ýylylyk mukdary",
                "Termodinamika",
                R.drawable.yylylykmukdary
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.termofizika,
                "Ideal gaz kanuny",
                "Termodinamika",
                R.drawable.idealgazkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Amperiň kanuny",
                "Elektromagnetizm",
                R.drawable.amperinkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Elektrik meýdany",
                "Elektromagnetizm",
                R.drawable.elektrikmeydany
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Elektrik potensial energiýasy",
                "Elektromagnetizm",
                R.drawable.elektrikpotensialenergiyasy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Elektrik potensialy",
                "Elektromagnetizm",
                R.drawable.elektrikpotensialy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Faradeýiň kanuny",
                "Elektromagnetizm",
                R.drawable.faradaykanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Gausyň kanuny",
                "Elektromagnetizm",
                R.drawable.gausynkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Kirçoftyň düzgüni",
                "Elektromagnetizm",
                R.drawable.kircoftynduzguni
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Kolombyň kanuny",
                "Elektromagnetizm",
                R.drawable.kolombkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Lorentyň kanuny",
                "Elektromagnetizm",
                R.drawable.lorentynkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Magnit akymynyň dykyzlygy",
                "Elektromagnetizm",
                R.drawable.magnitakymynyndykyzlygy
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Omuň kanuny",
                "Elektromagnetizm",
                R.drawable.omunkanuny
            )
        )
        matemList.add(
            Model_formula(
                R.drawable.elektromagnetizm,
                "Yzygiderli aýlaw",
                "Elektromagnetizm",
                R.drawable.yzygiderliaylaw
            )
        )







        adapter = FormulasAdapter(matemList)
        recyclerPhysics.adapter = adapter


    }
}










