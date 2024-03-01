package oguz.library.etut.adapter

import android.annotation.SuppressLint
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
import oguz.library.etut.fragment.formulas.FormulaViewFragment
import oguz.library.etut.fragment.minimum.PdfMinimumsFragment
import oguz.library.etut.model.Model_formula


class FormulasAdapter(private var matemList:ArrayList<Model_formula>)
    : RecyclerView.Adapter<FormulasAdapter.matemViewHolder>(){

    var onItemClick : ((Model_formula) -> Unit)? = null

    class matemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageForm)
        val textView: TextView = itemView.findViewById(R.id.formTitle)
        val textView2: TextView=itemView.findViewById(R.id.formSubtitle)
        val cardView :FrameLayout = itemView.findViewById(R.id.frameForm)
    }

    fun  setFilteredList(matemList: ArrayList<Model_formula>){
        this.matemList= matemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): matemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_item, parent , false)
        return matemViewHolder(view)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: matemViewHolder, position: Int) {
        val matem = matemList[position]
        holder.imageView.setImageResource(matem.image)
        holder.textView.text = matem.name
        holder.textView2.text = matem.at

        holder.itemView.setOnClickListener{
            val fragment = FormulaViewFragment()
            val bundle = Bundle()
            bundle.putInt("formula", matem.form)
            bundle.putString("title", matem.name)
            fragment.arguments = bundle
            (holder.itemView.context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_formula,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.cardView.context, R.anim.slide_in))
    }

    override fun getItemCount(): Int {
        return matemList.size
    }

}

