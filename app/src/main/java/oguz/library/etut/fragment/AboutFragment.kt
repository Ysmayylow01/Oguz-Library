package oguz.library.etut.fragment

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.xw.repo.widget.BounceScrollView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.adapter.ImageSliderAdapter
import oguz.library.etut.item.Toolbar
import org.w3c.dom.Text


class AboutFragment : Fragment() {
    private val imageList = listOf(
        R.drawable.oguzlib1,
        R.drawable.oguzlib1,
        R.drawable.oguzlib1
    )
    private lateinit var scrollViewAbout: BounceScrollView
    private lateinit var linearAbout: LinearLayout
    private lateinit var aboutLinearCon: LinearLayout
    private lateinit var toolbarAbout: Toolbar
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var aboutframeView: MaterialCardView
    private lateinit var linearAboutCard: LinearLayout
    private lateinit var titleAbout: TextView
    private lateinit var descAbout: TextView
    private lateinit var versionApp : MaterialCardView
    private lateinit var linearVersionApp : LinearLayout
    private lateinit var relativeVersion : RelativeLayout
    private lateinit var relativeVersion2 : RelativeLayout
    private lateinit var logo1 : ImageView
    private lateinit var logo2 : ImageView
    private lateinit var appName : TextView
    private lateinit var appVersion : TextView
    private lateinit var developerTitle : TextView
    private lateinit var tituTitle : TextView
    private lateinit var tituLink : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setContainerAbout()
        setUISlider()
        return aboutLinearCon
    }

    private fun setUISlider() {
        imageSliderAdapter = ImageSliderAdapter(imageList)
        viewPager2.adapter = imageSliderAdapter
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            @RequiresApi(Build.VERSION_CODES.P)
            override fun run() {
                viewPager2.currentItem = (viewPager2.currentItem + 1) % imageList.size
                handler.postDelayed(this, 3000)

            }
        }

        handler.postDelayed(runnable, 3000)
        setUpIndicator()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicator(position)

            }
        })
    }

    private fun setUpIndicator() {
        for (i in imageList.indices) {
            val indicator = ImageView(requireContext())
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(8, 0, 8, 0)
            indicator.layoutParams = layoutParams
            indicator.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (i == 0) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
                )
            )
            indicatorLayout.addView(indicator)
        }
    }

    private fun updateIndicator(position: Int) {
        if (!isAdded) return
        for (i in 0 until indicatorLayout.childCount) {
            val indicator = indicatorLayout.getChildAt(i) as ImageView
            indicator.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (i == position) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
                )
            )
        }
    }

    private fun indicatorsLayout() {
        indicatorLayout = LinearLayout(requireContext())
        val indicatorLayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        indicatorLayout.layoutParams = indicatorLayoutParams
        indicatorLayout.orientation = LinearLayout.HORIZONTAL
        indicatorLayout.gravity = Gravity.CENTER
        indicatorLayoutParams.topMargin = dpToPx(resources, 7)
        indicatorLayout.id = View.generateViewId()

    }


    private fun imageSlider() {
        viewPager2 = ViewPager2(requireContext())
        val viewPagerParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            dpToPx(resources, 200)
        )
        viewPager2.layoutParams = viewPagerParams
        viewPager2.id = View.generateViewId()
    }

    private fun scrollContainerAbout() {
        aboutLinear()
        scrollViewAbout = BounceScrollView(requireContext())
        scrollViewAbout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        scrollViewAbout.damping = 1.2f
        scrollViewAbout.setBackgroundColor(resources.getColor(R.color.background))
        scrollViewAbout.addView(linearAbout)
    }

    private fun aboutLinear() {
        imageSlider()
        indicatorsLayout()
        frameViewAboutApps()
        versionCardView()
        developedTitle()
        linearAbout = LinearLayout(requireContext())
        linearAbout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        linearAbout.orientation = VERTICAL
        linearAbout.setBackgroundColor(resources.getColor(R.color.background))
        linearAbout.addView(viewPager2)
        linearAbout.addView(indicatorLayout)
        linearAbout.addView(aboutframeView)
        linearAbout.addView(versionApp)
        linearAbout.addView(developerTitle)

    }

    private fun developedTitle() {
        developerTitle = TextView(requireContext())
       val developerTitleParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        developerTitle.layoutParams = developerTitleParams
        developerTitleParams.gravity =Gravity.CENTER
        developerTitleParams.topMargin = dpToPx(resources, 10)
        developerTitle.text = getString(R.string.copirihgt)
        developerTitle.textSize = 15f
        developerTitle.setTextColor(resources.getColor(R.color.textBlue))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            developerTitle.typeface = resources.getFont(R.font.mitr_regular)
        }

    }

    private fun versionCardView() {
        linearVersion()
        versionApp = MaterialCardView(requireContext())
        val versionAppViewParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        versionApp.layoutParams = versionAppViewParams
        versionApp.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.backgroundCard)
        versionApp.useCompatPadding = true
        versionApp.elevation = dpToPx(resources, 5).toFloat()
        versionApp.radius = dpToPx(resources, 10).toFloat()
        versionApp.setPadding(dpToPx(resources, 10))
        versionAppViewParams.setMargins(dpToPx(resources, 10))
        versionApp.addView(linearVersionApp)

    }

    private fun linearVersion() {
        relativeVers()
        relativeVers2()
        linearVersionApp = LinearLayout(requireContext())
        linearVersionApp.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        linearVersionApp.orientation = VERTICAL
        linearVersionApp.setPadding(dpToPx(resources, 10))
        linearVersionApp.addView(relativeVersion)
        linearVersionApp.addView(relativeVersion2)
    }

    private fun relativeVers2() {
        logo2V()
        titu()
        linkTitu()
        relativeVersion2 = RelativeLayout(requireContext())
        relativeVersion2.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        relativeVersion2.addView(logo2)
        relativeVersion2.addView(tituTitle)
        relativeVersion2.addView(tituLink)

    }

    private fun linkTitu() {

        tituLink = TextView(requireContext())
        val tituLinkParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        tituLink.layoutParams = tituLinkParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tituLink.typeface = resources.getFont(R.font.mitr_light)
        }
        tituLink.setTextColor(resources.getColor(R.color.link))
        tituLink.text = getString(R.string.tituLink)
        tituLink.textSize = 14f
        tituLinkParams.bottomMargin = dpToPx(resources, 10)
        tituLinkParams.addRule(RelativeLayout.END_OF, logo2.id)
        tituLinkParams.addRule(RelativeLayout.BELOW, tituTitle.id)
        tituLink.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
    }

    private fun titu() {
        tituTitle = TextView(requireContext())
        val tituTitleParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        tituTitle.layoutParams = tituTitleParams
        tituTitle.text = getString(R.string.oguzhan)
        tituTitle.textSize = 14f
        tituTitle.id = View.generateViewId()
        tituTitleParams.setMargins(dpToPx(resources, 10))
        tituTitle.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tituTitle.typeface = resources.getFont(R.font.mitr_regular)
        }
        tituTitle.setTextColor(resources.getColor(R.color.textBlue))
        tituTitleParams.addRule(RelativeLayout.END_OF, logo2.id)
    }

    private fun logo2V() {
        logo2 = ImageView(requireContext())
        val logo2Params = RelativeLayout.LayoutParams(
            dpToPx(resources, 100),
            dpToPx(resources, 100)
        )
        logo2.layoutParams = logo2Params
        logo2.setImageResource(R.drawable.oguz)
        logo2Params.addRule(RelativeLayout.CENTER_VERTICAL)
        logo2.id = View.generateViewId()
        logo2Params.addRule(RelativeLayout.ALIGN_PARENT_START)
        val objectAnimator = ObjectAnimator.ofFloat(
            logo2,
            "rotationY",
            0f,
            360f
        )
        objectAnimator.duration = 2500
        objectAnimator.repeatCount = ObjectAnimator.INFINITE

        objectAnimator.start()
    }

    private fun relativeVers() {
        logo1app()
        appName()
        appversion()
        relativeVersion = RelativeLayout(requireContext())
        relativeVersion.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        relativeVersion.addView(logo1)
        relativeVersion.addView(appName)
        relativeVersion.addView(appVersion)
    }

    private fun appversion() {
        appVersion = TextView(requireContext())
        val appVersionParams =  RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        appVersion.layoutParams = appVersionParams
        appVersion.text = getString(R.string.versia)
        appVersionParams.topMargin = dpToPx(resources, 10)
        appVersion.textSize = 14f
        appVersion.setTextColor(resources.getColor(R.color.unselected))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            appVersion.typeface = resources.getFont(R.font.mitr_light)
        }
        appVersionParams.addRule(RelativeLayout.END_OF, logo1.id)

        appVersion.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
    }

    private fun appName() {
        appName = TextView(requireContext())
        val appNameParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        appName.layoutParams = appNameParams
        appName.text = getString(R.string.app_name)
        appName.id = View.generateViewId()
        appNameParams.bottomMargin = dpToPx(resources, 10)
        appName.textSize = 22f
        appName.setTextColor(resources.getColor(R.color.textBlue))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            appName.typeface = resources.getFont(R.font.mitr_regular)
        }
        appName.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        appNameParams.addRule(RelativeLayout.END_OF, logo1.id)
        appNameParams.addRule(RelativeLayout.CENTER_VERTICAL)
    }

    private fun logo1app() {
        logo1 = ImageView(requireContext())
        val logo1Params = RelativeLayout.LayoutParams(
            dpToPx(resources, 100),
             dpToPx(resources, 100)
        )
        logo1.layoutParams = logo1Params
        logo1.setImageResource(R.drawable.logo)
        logo1.id = View.generateViewId()
        logo1Params.addRule(RelativeLayout.ALIGN_PARENT_START)
        val objectAnimator = ObjectAnimator.ofFloat(
            logo1,
            "rotationY",
            0f,
            360f
        )
        objectAnimator.duration = 2500
        objectAnimator.repeatCount = ObjectAnimator.INFINITE
        objectAnimator.start()
    }

    private fun frameViewAboutApps() {

        aboutLinearTitle()
        aboutframeView = MaterialCardView(requireContext())
        val aboutframeViewParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        aboutframeView.layoutParams = aboutframeViewParams
        aboutframeView.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.backgroundCard)
        aboutframeView.useCompatPadding = true
        aboutframeView.elevation = dpToPx(resources, 5).toFloat()
        aboutframeView.radius = dpToPx(resources, 10).toFloat()
        aboutframeView.setPadding(dpToPx(resources, 10))
        aboutframeViewParams.setMargins(dpToPx(resources, 10))
        aboutframeView.addView(linearAboutCard)
    }

    private fun aboutLinearTitle() {
        aboutTitle()
        aboutDescriptions()
        linearAboutCard = LinearLayout(requireContext())
        linearAboutCard.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        linearAboutCard.orientation = VERTICAL
        linearAboutCard.setPadding(dpToPx(resources, 10))
        linearAboutCard.addView(titleAbout)
        linearAboutCard.addView(descAbout)
    }

    private fun aboutDescriptions() {
        descAbout = TextView(requireContext())
        val descAboutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        descAbout.layoutParams = descAboutParams
        descAbout.setTextColor(resources.getColor(R.color.unselected))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            descAbout.typeface = resources.getFont(R.font.mitr_regular)
        }
        descAbout.textSize = 14f
        descAboutParams.bottomMargin = dpToPx(resources, 10)
        descAboutParams.marginStart = dpToPx(resources, 10)
        descAbout.text = getString(R.string.aboutFull)
    }

    private fun aboutTitle() {
        titleAbout = TextView(requireContext())
        val titleAboutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        titleAbout.layoutParams = titleAboutParams
        titleAbout.textSize = 22f
        titleAboutParams.gravity = Gravity.CENTER
        titleAbout.setTextColor(resources.getColor(R.color.textBlue))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            titleAbout.typeface = resources.getFont(R.font.mitr_regular)
        }
        titleAbout.text = getString(R.string.Aboutapp)
    }

    private fun onClickAbout() {
        toolbarAbout.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        tituLink.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data= Uri.parse("https://etut.edu.tm")
            startActivity(intent)
        }
    }

    private fun setContainerAbout() {
        scrollContainerAbout()
        toolbarInfo()
        onClickAbout()

        aboutLinearCon = LinearLayout(requireContext())
        aboutLinearCon.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        aboutLinearCon.setBackgroundColor(resources.getColor(R.color.background))
        aboutLinearCon.orientation = LinearLayout.VERTICAL
        aboutLinearCon.addView(toolbarAbout)
        aboutLinearCon.addView(scrollViewAbout)

    }

    private fun toolbarInfo() {
        toolbarAbout = Toolbar(requireContext())
        toolbarAbout.backIcon.setImageResource(R.drawable.backstack)
        toolbarAbout.titleTextToolbar.setText(R.string.barada)
    }


}