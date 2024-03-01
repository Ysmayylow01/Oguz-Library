package oguz.library.etut.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.fragment.books.PdfBooksFragment
import oguz.library.etut.fragment.minimum.PdfMinimumsFragment
import oguz.library.etut.model.Minimums


class MinAdapter (private val context: Context,
private var minimumList: ArrayList<Minimums>,
) : RecyclerView.Adapter<MinAdapter.BookViewHolder>() {


    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val minTitle: TextView = itemView.findViewById(R.id.minTitle)
        val minImage: ImageView = itemView.findViewById(R.id.imageMin)
        val minFrame: FrameLayout = itemView.findViewById(R.id.frameMin)
    }
    fun  setFilteredList(minimumList: ArrayList<Minimums>){
        this.minimumList= minimumList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.min_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val minItem = minimumList[position]
        holder.minTitle.text = minItem.title
        holder.minImage.setImageResource(minItem.imageName)
        holder.minFrame.startAnimation(
            AnimationUtils.loadAnimation(
                holder.minFrame.context,
                R.anim.slide_in
            )
        )
        holder.itemView.setOnClickListener {
            val fragment = PdfMinimumsFragment()
            val bundle = Bundle()
            bundle.putString("title", minItem.title)
            bundle.putString("pdf", minItem.pdf)
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

    }



    override fun getItemCount(): Int {
        return minimumList.size
    }
}
