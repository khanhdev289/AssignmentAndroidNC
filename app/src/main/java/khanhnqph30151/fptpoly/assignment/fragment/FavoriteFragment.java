package khanhnqph30151.fptpoly.assignment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.adapter.AdapterFavorite;
import khanhnqph30151.fptpoly.assignment.data.FavoriteDAO;
import khanhnqph30151.fptpoly.assignment.model.Favorite;


public class FavoriteFragment extends Fragment {
    RecyclerView recyFav;
    FavoriteDAO favDAO;
    ArrayList<Favorite> list;
    AdapterFavorite adapterFavorite;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(int position) {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyFav = view.findViewById(R.id.recyFavo);
        reloadData();
    }
    private void reloadData(){
        favDAO = new FavoriteDAO(getContext());
        list = favDAO.GetDSS();
        adapterFavorite = new AdapterFavorite(getContext(),list,favDAO);
        adapterFavorite.setData(list);
        recyFav.setAdapter(adapterFavorite);
        recyFav.setLayoutManager(new GridLayoutManager(getContext(), 1));

    }
}