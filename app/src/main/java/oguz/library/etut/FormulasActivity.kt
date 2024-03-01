package oguz.library.etut

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import oguz.library.etut.StaticMethods.Companion.replaceFragmentFormulas
import oguz.library.etut.fragment.formulas.FizikaFragment
import oguz.library.etut.fragment.formulas.HimiyaFragment
import oguz.library.etut.fragment.formulas.MathFormulasFragment

class FormulasActivity : AppCompatActivity() {
    private lateinit var containerFormulasActivity: RelativeLayout
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var frameFormulas: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Oguz_Library)
        setContainerFormulas()
        replaceFragmentFormulas(supportFragmentManager, MathFormulasFragment(), R.id.frame_formula)
        setContentView(containerFormulasActivity)
    }

    private fun setContainerFormulas() {
        bottomNav()
        frameForm()
        containerFormulasActivity = RelativeLayout(this)
        containerFormulasActivity.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        containerFormulasActivity.setBackgroundColor(resources.getColor(R.color.background))
        containerFormulasActivity.addView(bottomNavView)
        containerFormulasActivity.addView(frameFormulas)
    }

    private fun frameForm() {
        frameFormulas = FrameLayout(this)
        val frameParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        frameFormulas.layoutParams = frameParams
        frameParams.addRule(RelativeLayout.ABOVE, bottomNavView.id)
        frameFormulas.id = R.id.frame_formula
    }

    @SuppressLint("ResourceType")
    private fun bottomNav() {
        bottomNavView = BottomNavigationView(this)
        val bottomNavParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        bottomNavView.layoutParams = bottomNavParams
        bottomNavView.id = View.generateViewId()
        bottomNavParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        bottomNavView.itemRippleColor = null
        bottomNavView.itemTextColor =
            ContextCompat.getColorStateList(this, R.menu.bottom_nav_sel_text)
        bottomNavView.itemIconTintList =
            ContextCompat.getColorStateList(this, R.menu.bottom_nav_selector)
        bottomNavView.setBackgroundColor(resources.getColor(R.color.background))
        bottomNavView.inflateMenu(R.menu.bottom_nav)
        bottomNavView.selectedItemId = R.id.math
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.math -> {
                    replaceFragmentFormulas(supportFragmentManager, MathFormulasFragment(), R.id.frame_formula)
                }
                R.id.physics -> {
                    replaceFragmentFormulas(supportFragmentManager, FizikaFragment(), R.id.frame_formula)
                }
                R.id.chemistry -> {
                    replaceFragmentFormulas(supportFragmentManager, HimiyaFragment(), R.id.frame_formula)
                }

            }
            return@setOnNavigationItemSelectedListener true

        }
    }

}