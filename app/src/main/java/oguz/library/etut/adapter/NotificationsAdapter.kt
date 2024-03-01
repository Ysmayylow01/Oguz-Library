package oguz.library.etut.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale


import com.google.firebase.firestore.FirebaseFirestore
import oguz.library.etut.R
import oguz.library.etut.model.Notifications
import java.time.Month

class NotificationsAdapter(val requireContext: Context, val notificationsList: ArrayList<Notifications>) :
    RecyclerView.Adapter<NotificationsAdapter.bomViewHolder>()  {


    inner class  bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageview = itemView.findViewById<TextView>(R.id.bildirisTitle)
        val desc = itemView.findViewById<TextView>(R.id.bildirisDes)
        val data = itemView.findViewById<TextView>(R.id.tvData)
        val author = itemView.findViewById<TextView>(R.id.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return  bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.notifications_item, parent,false )
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
              val notifList = notificationsList[position]
        holder.imageview.text = notifList.title
        holder.desc.text = notifList.desc
        val timestamp = notifList.date
        val date = timestamp.toDate()
        holder.data.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
        holder.author.text = notifList.author


    }

    override fun getItemCount()  = notificationsList.size
}