package khanhnqph30151.fptpoly.assignment.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.data.MusicDAO;
import khanhnqph30151.fptpoly.assignment.model.Music;
import khanhnqph30151.fptpoly.assignment.service.Service;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    ArrayList<Music> list;
    Context context;
    MusicDAO DAO;
    TextView title;

    public MusicAdapter(ArrayList<Music> listsong, Context context, MusicDAO DAO, TextView title) {
        this.list = listsong;
        this.context = context;
        this.DAO = DAO;
        this.title = title;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_tennhac;
        CardView itemnhac;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tennhac = itemView.findViewById(R.id.item_tennhac);
            itemnhac = itemView.findViewById(R.id.itemnhac);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhac, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Music music = list.get(position);
        holder.item_tennhac.setText(list.get(position).getTenMusic());
//        holder.itemnhac.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Showdata(context, listsong.get(position));
//                return false;
//            }
//        });
        holder.itemnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = list.get(position).getTenMusic();
                title.setText(ten);

                Intent i = new Intent(context, Service.class);
                i.putExtra("link",list.get(position).getLink());
                context.startService(i);
            }
        });
        holder.itemnhac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete(music.getIdMusic());
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setData(ArrayList<Music> lst){
        this.list = lst;
        notifyDataSetChanged();
    }

    //    public void Showdata(Context context, Music music) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Thông Tin Bài Nhạc");
//        builder.setMessage("Tên Bài Nhạc:" + music.getTen() + "\n Link Nhạc:" + music.getLink());
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                alertDialog.dismiss();
//            }
//        });
//    }
    public void delete(int id){
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("Bạn có muốn xoá không ?");
        dialogDL.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogDL.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (DAO.delete(id) > 0) {
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list = DAO.GetDSS();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xoa k thanh cong", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }


}
