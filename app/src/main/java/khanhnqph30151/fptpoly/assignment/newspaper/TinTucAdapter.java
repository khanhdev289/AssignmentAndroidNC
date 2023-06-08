package khanhnqph30151.fptpoly.assignment.newspaper;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.fragment.NewsFragment;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ViewHolder> {
    private ArrayList<TinTuc> list;
    private Context context;

    public TinTucAdapter(ArrayList<TinTuc> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setData(ArrayList<TinTuc> lst){
        this.list = lst;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintuc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv1.setText(list.get(position).getTitle());
        holder.tv2.setText(list.get(position).getDescription());
//        holder.tv3.setText(list.get(position).getPubDate());
//        holder.tv4.setText(list.get(position).getLink());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ThongTinTinTuc.class);
                i.putExtra("link", list.get(position).getLink());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4;


        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.item_title);
            tv2 = itemView.findViewById(R.id.item_des);
            tv3 = itemView.findViewById(R.id.item_pubData);
            tv4 = itemView.findViewById(R.id.item_link);
        }
    }
}

