package khanhnqph30151.fptpoly.assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.data.FavoriteDAO;
import khanhnqph30151.fptpoly.assignment.model.Favorite;


public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    private Context context;
    private ArrayList<Favorite> listmfav;
    private FavoriteDAO favDAO;

    public AdapterFavorite(Context context, ArrayList<Favorite> listmfav, FavoriteDAO favDAO) {
        this.context = context;
        this.listmfav = listmfav;
        this.favDAO = favDAO;
    }
    public void setData(ArrayList<Favorite> list ){
        this.listmfav = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenfav;
        ImageView xoafav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenfav = itemView.findViewById(R.id.item_fav_ten);
            xoafav = itemView.findViewById(R.id.iv_item_fav_heart);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenfav.setText(listmfav.get(position).getTenMusic());
        holder.xoafav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idfav = listmfav.get(position).getIdMusic();
                if (favDAO.XoaFav(idfav) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    listmfav.clear();
                    favDAO = new FavoriteDAO(context);
                    listmfav = favDAO.GetDSS();
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listmfav.size();
    }


}
