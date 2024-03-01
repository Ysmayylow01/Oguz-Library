package oguz.library.etut

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import oguz.library.etut.fragment.HomeFragment

class StaticMethods {

     companion object {
           fun dpToPx(resources: Resources,dp:Int): Int {
               val scale = resources.displayMetrics.density
               return (dp * scale + 0.5f).toInt()
          }
         fun navigateBack(fragmentManager: FragmentManager, activity: FragmentActivity?) {
             if (fragmentManager.backStackEntryCount > 0) {
                 fragmentManager.popBackStack()
             } else {
                 activity?.onBackPressed()
             }
         }
         fun setBoldTypeface(textView: TextView) {
             textView.setTypeface(null, Typeface.BOLD)
         }
         fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int) {
             fragmentManager.beginTransaction()
                 .setCustomAnimations(
                 R.anim.enter_anim,
                 0,
                 0,
                 R.anim.pop_exit_anim
             )
                 .add(containerId, fragment)
                 .addToBackStack(null)
                 .commit()
         }
         fun replaceFragmentMain(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int) {
             fragmentManager.beginTransaction()
                 .add(containerId, fragment)
                 .commit()
         }

         fun replaceFragmentFormulas(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int) {
             fragmentManager.beginTransaction()
                 .setCustomAnimations(
                     R.anim.enter_anim,
                     0,
                     0,
                     R.anim.pop_exit_anim
                 )
                 .replace(containerId, fragment)
                 .commit()
         }
         fun intentActivity(context: Context, activity : Activity){
             val intent=Intent(context, activity::class.java)
             context.startActivity(intent)
         }

     }
}
