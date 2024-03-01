package oguz.library.etut.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.db.DatabaseHelperK
import oguz.library.etut.fragment.books.PdfBooksFragment
import oguz.library.etut.fragment.minimum.PdfMinimumsFragment
import oguz.library.etut.model.Books
import oguz.library.etut.model.MinimumsModel

class MinimumsAdapter(
    private var themesModels: ArrayList<MinimumsModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<MinimumsAdapter.viewHolder>() {
    private var favDB: DatabaseHelperK? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinimumsAdapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        favDB = DatabaseHelperK(context)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val themeModelFav = themesModels[position]
        val cursor = favDB!!.readAllData33()
        if (cursor!!.count != 0) {
            while (cursor.moveToNext()) {
                val ids = ArrayList<String>()
                ids.add(cursor.getString(0))
                if (ids.contains(themeModelFav.key_id)) {
                    holder.favBtn.setImageResource(R.drawable.like_solid)
                }
            }
        }
        val title = themeModelFav.title
        val image = themeModelFav.image
        val id = themeModelFav.key_id
        val subtitle = themeModelFav.subtitle
        val pdf = themeModelFav.pdf
        holder.imageView.setImageResource(themeModelFav.image)
        holder.titleTextView.text = themeModelFav.title
        holder.subtitleText.text = themeModelFav.subtitle
        holder.imageView.tag = position
        holder.favBtn.setOnClickListener { checkFavorite(title!!, image, holder.favBtn,  id!!, subtitle!!,  pdf!!) }

        holder.itemView.setOnClickListener {
            val fragment = PdfMinimumsFragment()
            val bundle = Bundle()
            bundle.putInt("image", themeModelFav.image)
            bundle.putString("title", themeModelFav.title)
            bundle.putString("key_id", themeModelFav.key_id)
            bundle.putString("subtitle", themeModelFav.subtitle)
            bundle.putString("category", themeModelFav.category)
            bundle.putString("pdf", themeModelFav.pdf)
            fragment.arguments = bundle
            (holder.itemView.context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_main,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.cardView.context, R.anim.slide_in))
    }

    override fun getItemCount(): Int {
        return themesModels.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var titleTextView: TextView
        var subtitleText : TextView
        var favBtn: ImageView
        var cardView: CardView

        init {
            imageView = itemView.findViewById(R.id.imageBooks)
            titleTextView = itemView.findViewById(R.id.titleBooks)
            subtitleText = itemView.findViewById(R.id.subtitleBooks)
            favBtn = itemView.findViewById(R.id.favItemBooks)
            cardView = itemView.findViewById(R.id.cardViewk)
        }
    }

    private fun checkFavorite(title: String, image: Int, favorite: ImageView,  unique_id: String,subtitle:String,pdf:String) {
        val cursor = favDB!!.readAllData33()
        if (cursor!!.count != 0) {
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals(unique_id, ignoreCase = true)) {
                    favDB!!.deleteItem33(unique_id)
                    favorite.setImageResource(R.drawable.like_border)
                    return
                }
            }
        }
        favDB!!.addScanRecord3(unique_id, title, image.toString(), subtitle, pdf)
        favorite.setImageResource(R.drawable.like_solid)
    }

    fun  setFilteredList(themesModels: ArrayList<MinimumsModel>){
        this.themesModels = themesModels
        notifyDataSetChanged()
    }
}