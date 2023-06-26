package khanhnqph30151.fptpoly.assignment.newspaper.tablayout;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.model.TinTuc;
import khanhnqph30151.fptpoly.assignment.adapter.TinTucAdapter;
import khanhnqph30151.fptpoly.assignment.newspaper.TinTucLoader;


public class ThoiSuFragment extends Fragment {
    RecyclerView recyThoiSu;

    public ThoiSuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThoiSuFragment newInstance(String param1, String param2) {
        ThoiSuFragment fragment = new ThoiSuFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_thoi_su, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String urlRss = "https://vnexpress.net/rss/thoi-su.rss";
        ThoiSuFragment.DownloadTinTuc dowload = new DownloadTinTuc(this);
        dowload.execute(urlRss);

        recyThoiSu = view.findViewById(R.id.recy_thoisu);
    }
    public class DownloadTinTuc extends AsyncTask<String, Void, List<TinTuc>> {
        ThoiSuFragment activity = null;


        public DownloadTinTuc(ThoiSuFragment activity) {
            this.activity = activity;

        }

        @Override
        protected ArrayList<TinTuc> doInBackground(String... strings) {

            TinTucLoader loader = new TinTucLoader();
            ArrayList<TinTuc> list = new ArrayList<TinTuc>();


            // tạo url Connection để tải RSS
            String urlRss = strings[0];

            try {
                URL url = new URL(urlRss);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    // kết nối thành công thì mới lấy luồng dữ liệu
                    InputStream inputStream = urlConnection.getInputStream();
                    list = (ArrayList<TinTuc>) loader.getTinTucList(inputStream);

                }
                urlConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return list;
        }

        @Override
        protected void onPostExecute(List<TinTuc> tinTucs) {
            super.onPostExecute(tinTucs);
//        Log.d("zzzzz", "onPostExecute: số lượng bài viết = " + tinTucs.size());
//        for(int i = 0; i< tinTucs.size(); i++){
//            Log.d("zzzzz", "Tiêu đề bài viết:  " + tinTucs.get(i).getTitle()  );
//        }

            TinTucAdapter adapter = new TinTucAdapter((ArrayList<TinTuc>) tinTucs, requireContext());
            recyThoiSu.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyThoiSu.setAdapter(adapter);

        }
    }
}