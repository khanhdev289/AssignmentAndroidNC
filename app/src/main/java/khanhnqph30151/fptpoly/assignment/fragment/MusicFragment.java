package khanhnqph30151.fptpoly.assignment.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.adapter.MusicAdapter;
import khanhnqph30151.fptpoly.assignment.data.MusicDAO;
import khanhnqph30151.fptpoly.assignment.model.Music;
import khanhnqph30151.fptpoly.assignment.service.Service;

public class MusicFragment extends Fragment {

    MusicDAO musicDAO;
    MusicAdapter adapterMusic;
    ArrayList<Music> listmusic;
    RecyclerView recyclerView;
    Button btn_them;
    TextView tenbaihat;
    Button start, pause, stop, add;
    boolean check = true;

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance(int position, ContentResolver contentResolver) {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_music, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyMusic);
        tenbaihat = view.findViewById(R.id.ed_tenbaihat);
        btn_them = view.findViewById(R.id.btn_music_add);
        start = view.findViewById(R.id.btn_start);
        pause = view.findViewById(R.id.btn_pause);
        stop = view.findViewById(R.id.btn_stop);

        realoaddata();
        btn_them.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                ThemMusic();
            }
        });
        Intent intentsong = new Intent(getActivity(), Service.class);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == false){
                    getActivity().startService(intentsong);
                    check = true;
                }

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == true) {
                    getActivity().startService(intentsong);
                    check = false;
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(intentsong);
                check = true;
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
    public void realoaddata() {
        musicDAO = new MusicDAO(getContext());
        listmusic = musicDAO.GetDSS();
        adapterMusic = new MusicAdapter(listmusic, getContext(), musicDAO,tenbaihat);
        recyclerView.setAdapter(adapterMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void ThemMusic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themnhac, null, false);
        EditText themtenmusic = view.findViewById(R.id.ed_dialog_music_tennhac);
        EditText themlinknhac = view.findViewById(R.id.ed_dialog_music_link);
        Button themnhac = view.findViewById(R.id.dialog_btnthemnhac);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        themnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtenmusic.getText().toString();
                String themlink = themlinknhac.getText().toString();
                Music themmusic = new Music();
                if (themten.trim().equals("") && themlink.trim().equals("")) {
                    Toast.makeText(getContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
                } else {
                    themmusic.setTenMusic(themten);
                    if (musicDAO.Themsong(themmusic) > 0) {
                        realoaddata();
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}