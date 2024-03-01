package oguz.library.etut.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.RelativeLayout.LayoutParams
import androidx.fragment.app.Fragment
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.romainpiel.shimmer.Shimmer
import oguz.library.etut.FormulasActivity
import oguz.library.etut.MainActivity
import oguz.library.etut.item.MainCardItem
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.StaticMethods.Companion.dpToPx
import oguz.library.etut.StaticMethods.Companion.replaceFragment
import oguz.library.etut.fragment.books.BooksFragment
import oguz.library.etut.fragment.minimum.MinimumsFragment
import oguz.library.etut.fragment.notifications.NotificationsFragment
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var scrollView: ScrollView
    private lateinit var relativeLayoutMain: LinearLayout
    private lateinit var viewFlipperLogo: ViewFlipper
    private lateinit var logo1: ImageView
    private lateinit var logo2: ImageView
    private lateinit var categoryLinearLayout: LinearLayout
    private lateinit var imageLogo: ImageView
    private lateinit var booksCard: MainCardItem
    private lateinit var minCard: MainCardItem
    private lateinit var formulaCard: MainCardItem
    private lateinit var dictionaryCard: MainCardItem
    private lateinit var notificationCard: MainCardItem
    private lateinit var aboutCard: MainCardItem
    private lateinit var tm : TextView
    private lateinit var en : TextView

    private val shimmerText = Shimmer().setDuration(3000)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        imageLogo()
        scrollViewCon()


        return scrollView
    }

    @SuppressLint("ResourceAsColor")
    private fun scrollViewCon() {
        mainRelativeLayout()
        scrollView = ScrollView(requireContext())
        scrollView.layoutParams = ViewGroup.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
        )
        scrollView.setBackgroundColor(resources.getColor(R.color.background))
        scrollView.addView(relativeLayoutMain)
    }

    private fun onClickHome() {
        booksCard.setOnClickListener {
                replaceFragment(parentFragmentManager, BooksFragment(), R.id.frame_main)
        }
        minCard.setOnClickListener {

                replaceFragment(parentFragmentManager, MinimumsFragment(), R.id.frame_main)
        }
        formulaCard.setOnClickListener {

            StaticMethods.intentActivity(requireContext(), FormulasActivity())

        }
        dictionaryCard.setOnClickListener {

        }
        notificationCard.setOnClickListener {

                replaceFragment(parentFragmentManager, NotificationsFragment(), R.id.frame_main)

        }
        aboutCard.setOnClickListener {

                replaceFragment(parentFragmentManager, AboutFragment(), R.id.frame_main)
     
        }

    }

    private fun mainRelativeLayout() {
        viewFlipperLg()
        catLineLayout()
        lanuage()
        relativeLayoutMain = LinearLayout(requireContext())
        relativeLayoutMain.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        relativeLayoutMain.setBackgroundColor(resources.getColor(R.color.background))
        relativeLayoutMain.orientation = LinearLayout.VERTICAL
        relativeLayoutMain.addView(viewFlipperLogo)
        relativeLayoutMain.addView(categoryLinearLayout)
        relativeLayoutMain.addView(tm)
        relativeLayoutMain.addView(imageLogo)
        categoryLinearLayout.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.slide__in
            )
        )
    }

    private fun lanuage() {
        tm = TextView(requireContext())
        val tmParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tm.layoutParams = tmParams
        tm.setText(getString(R.string.dilsayla))
        tm.setTextColor(resources.getColor(R.color.subtitle))
        tm.textSize = 14f
        tm.id = View.generateViewId()
        tmParams.gravity = Gravity.CENTER
        tm.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), tm)
            popupMenu.menuInflater.inflate(R.menu.menu_language, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.tm -> {
                        val localeLan = Locale("en")
                        setLanguage(requireContext(), localeLan)
                        requireActivity().recreate()
                        true
                    }
                    R.id.en -> {
                        val localeLan = Locale("bg")
                        setLanguage(requireContext(), localeLan)
                        requireActivity().recreate()
                        true
                    }
                    else -> false
                }
            }

            try {
                val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldPopup.isAccessible = true
                val popup = fieldPopup.get(popupMenu)
                popup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(popup, true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
           popupMenu.show()
        }
    }

    private fun imageLogo() {
        imageLogo = ImageView(requireContext())
        val imageLogoParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(resources, 130)
        )
        imageLogo.layoutParams = imageLogoParams
        imageLogo.id = View.generateViewId()
        imageLogoParams.bottomMargin = dpToPx(resources, 8)
        imageLogo.setImageResource(R.drawable.oguzlib1)
    }

    private fun catLineLayout() {
        categoryLinearLayout = LinearLayout(requireContext())
        val catLineParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        categoryLinearLayout.layoutParams = catLineParams
        categoryLinearLayout.id = View.generateViewId()
        categoryLinearLayout.gravity = Gravity.CENTER
        categoryLinearLayout.orientation = LinearLayout.VERTICAL
        catLineParams.setMargins(
            dpToPx(resources, 0),
            dpToPx(resources, 15),
            dpToPx(resources, 0),
            dpToPx(resources, 15)
        )
        booksCard = MainCardItem(requireContext())
        minCard = MainCardItem(requireContext())
        formulaCard = MainCardItem(requireContext())
        dictionaryCard = MainCardItem(requireContext())
        notificationCard = MainCardItem(requireContext())
        aboutCard = MainCardItem(requireContext())
        booksCard.imageCardItem.setImageResource(R.drawable.booksicon)
        booksCard.textCardItem.text = getString(R.string.kitap)
        shimmerText.start(booksCard.textCardItem)
        shimmerText.start(minCard.textCardItem)
        shimmerText.start(formulaCard.textCardItem)
        shimmerText.start(dictionaryCard.textCardItem)
        shimmerText.start(notificationCard.textCardItem)
        shimmerText.start(aboutCard.textCardItem)

        onClickHome()
        minCard.textCardItem.text = getString(R.string.minText)
        formulaCard.textCardItem.text = getString(R.string.formula)
        minCard.imageCardItem.setImageResource(R.drawable.minimum)
        minCard.imageCardItem.setPadding(
            dpToPx(resources, 5),
            dpToPx(resources, 5),
            dpToPx(resources, 5),
            dpToPx(resources, 5)
        )
        notificationCard.imageCardItem.setImageResource(R.drawable.notif)
        formulaCard.imageCardItem.setImageResource(R.drawable.formulaicon)
        aboutCard.imageCardItem.setImageResource(R.drawable.infoicon)
        aboutCard.imageCardItem.setPadding(
            dpToPx(resources, 5),
            dpToPx(resources, 5),
            dpToPx(resources, 5),
            dpToPx(resources, 5)
        )
        dictionaryCard.imageCardItem.setImageResource(R.drawable.sozlukicon)
        notificationCard.textCardItem.text = getString(R.string.notification)
        aboutCard.textCardItem.text = getString(R.string.barada)
        dictionaryCard.textCardItem.text = getString(R.string.sozluk)
        categoryLinearLayout.addView(booksCard)
        categoryLinearLayout.addView(minCard)
        categoryLinearLayout.addView(formulaCard)
        categoryLinearLayout.addView(dictionaryCard)
        categoryLinearLayout.addView(notificationCard)
        categoryLinearLayout.addView(aboutCard)
    }

    private fun viewFlipperLg() {
        viewLogo1()
        viewLogo2()
        viewFlipperLogo = ViewFlipper(requireContext())
        val viewFlipperLogoParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        viewFlipperLogo.layoutParams = viewFlipperLogoParams
        viewFlipperLogo.id = View.generateViewId()
        viewFlipperLogoParams.topMargin = dpToPx(resources, 10)
        viewFlipperLogo.addView(logo1)
        viewFlipperLogo.addView(logo2)
        viewFlipperLogo.flipInterval = 2000
        viewFlipperLogo.isAutoStart = true
        viewFlipperLogo.setInAnimation(requireContext(), android.R.anim.slide_in_left)
        viewFlipperLogo.setOutAnimation(requireContext(), android.R.anim.slide_out_right)
    }

    private fun viewLogo2() {
        logo1 = ImageView(requireContext())
        logo1.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            dpToPx(resources, 197)
        )
        logo1.setImageResource(R.drawable.oguz)
        logo1.id = View.generateViewId()
    }

    private fun viewLogo1() {
        logo2 = ImageView(requireContext())
        logo2.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            dpToPx(resources, 197)
        )
        logo2.setImageResource(R.drawable.logo)
        logo2.id = View.generateViewId()




    }



}