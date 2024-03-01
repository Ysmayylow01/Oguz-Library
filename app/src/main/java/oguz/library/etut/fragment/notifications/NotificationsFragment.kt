package oguz.library.etut.fragment.notifications

import android.content.ContentValues.TAG
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.adapter.NotificationsAdapter
import oguz.library.etut.item.LoadingProgress
import oguz.library.etut.model.Notifications

class NotificationsFragment : Fragment() {
    private lateinit var frameNotifications: LinearLayout
    private lateinit var recyclerViewNot: RecyclerView
    private lateinit var db: FirebaseFirestore
    private lateinit var toolbarNotification: oguz.library.etut.item.Toolbar
    private lateinit var notifAdapter: NotificationsAdapter
    private lateinit var progressBar: LoadingProgress

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setLinearLayoutNotif()
        db = FirebaseFirestore.getInstance()

        onClickNotification()
        setUpDb()
        return frameNotifications
    }

    private fun onClickNotification() {
        toolbarNotification.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setLinearLayoutNotif() {
        toolbarNotification()
        recyclerNotif()
        loadingProgress()
        frameNotifications = LinearLayout(requireContext())
        frameNotifications.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        frameNotifications.orientation = LinearLayout.VERTICAL
        frameNotifications.setBackgroundColor(resources.getColor(R.color.background))
        frameNotifications.addView(toolbarNotification)
        frameNotifications.addView(progressBar)
        frameNotifications.addView(recyclerViewNot)

    }

    private fun loadingProgress() {
        progressBar = LoadingProgress(requireContext())
        progressBar.visibility = View.VISIBLE
    }

    private fun toolbarNotification() {
        toolbarNotification = oguz.library.etut.item.Toolbar(requireContext())
        toolbarNotification.titleTextToolbar.setText(R.string.notification)
        toolbarNotification.backIcon.setImageResource(R.drawable.backstack)

    }

    private fun recyclerNotif() {

        recyclerViewNot = RecyclerView(requireContext())
        recyclerViewNot.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        recyclerViewNot.visibility = View.GONE
        recyclerViewNot.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpDb() {
        db.collection("notifications").get().addOnSuccessListener { result ->
            recyclerViewNot.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            val notifList = arrayListOf<Notifications>()
            val data = result.toObjects(Notifications::class.java)

            notifList.addAll(data!!)
            notifList.sortByDescending { it.date }
            notifAdapter = NotificationsAdapter(requireContext(), notifList)
            recyclerViewNot.adapter = notifAdapter
            notifAdapter.notifyDataSetChanged()
        }
            .addOnFailureListener {
                progressBar.visibility = View.VISIBLE
            }


    }

}