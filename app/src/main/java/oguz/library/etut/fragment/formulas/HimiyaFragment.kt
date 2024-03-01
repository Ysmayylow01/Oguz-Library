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



class HimiyaFragment : Fragment() {
    private lateinit var containerChemistryLinear : LinearLayout
    private lateinit var recyclerViewChemistry: RecyclerView
    private var matemList = ArrayList<Model_formula>()
    private lateinit var adapter: FormulasAdapter
    private lateinit var toolbarMath: LinearLayout
    private lateinit var backIconMathToolbar : ImageView
    private lateinit var searchView: SearchView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        containerChemistry()
        addDataToList()
        onclick()
        return containerChemistryLinear

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
    private fun containerChemistry() {

        containerChemistryLinear = LinearLayout(requireContext())
        containerChemistryLinear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        containerChemistryLinear.orientation = LinearLayout.VERTICAL
        containerChemistryLinear.setBackgroundColor(resources.getColor(R.color.background))
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

        recyclerViewChemistry = RecyclerView(requireContext())
        recyclerViewChemistry.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerViewChemistry.setBackgroundColor(resources.getColor(R.color.background))
        recyclerViewChemistry.setHasFixedSize(true)
        recyclerViewChemistry.layoutManager = LinearLayoutManager(requireContext())
        containerChemistryLinear.addView(toolbarMath)
        containerChemistryLinear.addView(recyclerViewChemistry)
    }

    private fun addDataToList() {
    matemList = ArrayList()
    matemList.add(Model_formula(R.drawable.stoiometri, "Himiki reaksiýalaryň görnüşi","Stohiometriýa",R.drawable.himikireaksgornus))
    matemList.add(Model_formula(R.drawable.stoiometri,"Esasy stohiometriýa", "Stohiometriýa",R.drawable.esasystohiometriya))
    matemList.add(Model_formula(R.drawable.stoiometri,"Stohimetriýanyň hasaplamalary", "Stohiometriýa",R.drawable.stohiometriyanynhasap))
    matemList.add(Model_formula(R.drawable.ergin,"Erginiň konsentrasiýasy", "Erginler",R.drawable.ergininkonsentr))
    matemList.add(Model_formula(R.drawable.ergin,"Molarity, molality we Molar fraksiýasy", "Erginler",R.drawable.molaritywemolar))
    matemList.add(Model_formula(R.drawable.ergin,"Suwuklyk", "Erginler",R.drawable.suwuklyk))
    matemList.add(Model_formula(R.drawable.ergin,"pH terezisi", "Erginler",R.drawable.phterezi))
    matemList.add(Model_formula(R.drawable.elektrohimiya,"Elektro himiýa", "Elektro himiýa",R.drawable.elektro_himiya))
    matemList.add(Model_formula(R.drawable.termohimiya,"Kalorimetriýa", "Termo-himiýa",R.drawable.kalorimetr))
    matemList.add(Model_formula(R.drawable.termohimiya,"Entalpiýa we entropiýa", "Termo-himiýa",R.drawable.entalpiyaweentropiya))
    matemList.add(Model_formula(R.drawable.gaz,"Ideal gazyň kanuny", "Gaz",R.drawable.idealgazynkanuny))
    matemList.add(Model_formula(R.drawable.gaz,"Daltonyň kanuny we bölegi", "Gaz",R.drawable.daltonkanuny))
    matemList.add(Model_formula(R.drawable.gaz,"Grahamyň kanuny", "Gaz",R.drawable.grahamynkanuny))
    matemList.add(Model_formula(R.drawable.gaz,"Kök ortaça kwadrat tizligi", "Gaz",R.drawable.kokkwadrattizanladyar))
    matemList.add(Model_formula(R.drawable.gaz,"Van Der Waals deňlemesi", "Gaz",R.drawable.wanderwalsdenleme))
    matemList.add(Model_formula(R.drawable.gaz,"Gysyş faktory", "Gaz",R.drawable.gysysfaktor))



    adapter = FormulasAdapter(matemList)
    recyclerViewChemistry.adapter = adapter



}}










