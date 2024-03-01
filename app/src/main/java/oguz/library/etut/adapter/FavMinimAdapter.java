package oguz.library.etut.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import oguz.library.etut.R;
import oguz.library.etut.db.DatabaseHelper;
import oguz.library.etut.db.DatabaseHelperK;
import oguz.library.etut.fragment.books.PdfBooksFragment;
import oguz.library.etut.model.FavModel;
import oguz.library.etut.model.FavoriteModel;

public class FavMinimAdapter extends RecyclerView.Adapter<FavMinimAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FavModel> favArrayList;
    private DatabaseHelperK favDB;
    View.OnClickListener onClickListener;
    View.OnClickListener onClickListenerDelete;

    public FavMinimAdapter(Context context, ArrayList<FavModel> favArrayList, View.OnClickListener onClickListenerDelete, View.OnClickListener onClickListener) {
        this.context = context;
        this.favArrayList = favArrayList;
        this.onClickListenerDelete = onClickListenerDelete;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        favDB = new DatabaseHelperK(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavModel themesModel = favArrayList.get(position);
        holder.favimageView.setImageResource(themesModel.getImage());
        holder.title_textView.setText(themesModel.getTitle());
        holder.subtitleTextView.setText(themesModel.getSubtitle());
        holder.favimageView.setTag(position);
        holder.favBtn.setImageResource(R.drawable.like_solid);
        holder.favBtn.setTag(position);
        holder.favBtn.setOnClickListener(onClickListenerDelete);
        holder.itemView.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return favArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favBtn;
        ImageView favimageView;
        TextView title_textView;
        TextView subtitleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favBtn = itemView.findViewById(R.id.favItemBooks);
            favimageView = itemView.findViewById(R.id.imageBooks);
            title_textView = itemView.findViewById(R.id.titleBooks);
            subtitleTextView= itemView.findViewById(R.id.subtitleBooks);

        }

    }
    public void setItems(ArrayList<FavModel> favArrayList) {
        this.favArrayList = favArrayList;

    }


}
