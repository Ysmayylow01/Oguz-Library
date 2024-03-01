package oguz.library.etut.adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import oguz.library.etut.R
import oguz.library.etut.StaticMethods
import oguz.library.etut.fragment.books.BooksFavoriteFragment
import oguz.library.etut.fragment.books.PdfBooksFragment
import oguz.library.etut.model.Books

class BooksAdapter(
    private val context: Context,
    private var bookList: ArrayList<Books>,
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("FavoriteBooks", Context.MODE_PRIVATE)
    }
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val booksTitle: TextView = itemView.findViewById(R.id.titleBooks)
        val booksSubTitle: TextView = itemView.findViewById(R.id.subtitleBooks)
        val booksImage: ImageView = itemView.findViewById(R.id.imageBooks)
        val booksCardView: CardView = itemView.findViewById(R.id.cardViewk)
        val favoriteImageView: ImageView = itemView.findViewById(R.id.favItemBooks)
    }
    fun  setFilteredList(booksList: ArrayList<Books>){
        this.bookList= booksList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val booksItem = bookList[position]
        holder.booksTitle.text = booksItem.title
        holder.booksSubTitle.text = booksItem.Subtitle
        holder.booksImage.setImageResource(booksItem.imageName)
        val isFavorite = getFavoriteStatus(booksItem.title)
        holder.favoriteImageView.setImageResource(if (getFavoriteStatus(booksItem.title)) R.drawable.like_solid else R.drawable.like_border)
        holder.booksCardView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.booksCardView.context,
                R.anim.slide_in
            )
        )
        holder.favoriteImageView.setOnClickListener {
val newFavouriteStatus = !isFavorite

            holder.favoriteImageView.setImageResource(if (newFavouriteStatus)
                R.drawable.like_solid
             else
                R.drawable.like_border
           )
            updateFavoriteStatus(booksItem.title, newFavouriteStatus)

            bookList[position] = booksItem
            notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener {
            val fragment = PdfBooksFragment()
            val bundle = Bundle()
            bundle.putString("title", booksItem.title)
            bundle.putString("subtitle", booksItem.Subtitle)
            bundle.putInt("image", booksItem.imageName)
            bundle.putString("pdf", booksItem.pdf)
            fragment.arguments = bundle
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_main,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }

    }
     fun updateFavoriteStatus(title: String, isFavorite: Boolean) {
        sharedPreferences.edit().putBoolean(title, isFavorite).apply()
    }

    fun getFavoriteStatus(title: String): Boolean {
        val sharedPreferences = context.getSharedPreferences("FavoriteBooks", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(title, false)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
