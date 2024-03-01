package oguz.library.etut


import android.os.Bundle
import android.widget.FrameLayout
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import oguz.library.etut.fragment.HomeFragment

class MainActivity : LocalizationActivity() {

    private lateinit var mainContainer: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUIMain()
        StaticMethods.replaceFragmentMain(supportFragmentManager,HomeFragment(), R.id.frame_main)
        setContentView(mainContainer)
    }

    private fun setUIMain() {
        mainContainer = FrameLayout(this)
        mainContainer.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
           mainContainer.id = R.id.frame_main
    }
}