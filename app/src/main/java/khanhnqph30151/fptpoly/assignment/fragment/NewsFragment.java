package khanhnqph30151.fptpoly.assignment.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.newspaper.TinTuc;
import khanhnqph30151.fptpoly.assignment.newspaper.TinTucAdapter;
import khanhnqph30151.fptpoly.assignment.newspaper.TinTucLoader;
import khanhnqph30151.fptpoly.assignment.newspaper.tablayout.NewsViewPager2Adapter;

public class NewsFragment extends Fragment {
    NewsViewPager2Adapter newsViewPager2Adapter;


    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.news_tablayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.news_viewpager);
        newsViewPager2Adapter = new NewsViewPager2Adapter(NewsFragment.this);
        viewPager2.setAdapter(newsViewPager2Adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Thời Sự");
                        break;
                    case 1:
                        tab.setText("Số Hoá");
                        break;
                    case 2:
                        tab.setText("Thế Giới");
                        break;
                }
            }
        }).attach();




    }

}