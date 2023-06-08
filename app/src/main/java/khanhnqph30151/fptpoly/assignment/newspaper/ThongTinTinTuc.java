package khanhnqph30151.fptpoly.assignment.newspaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.fragment.NewsFragment;

public class ThongTinTinTuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tin_tuc);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        WebView myWebview = findViewById(R.id.webview);
        myWebview.loadUrl(link);
        myWebview.setWebViewClient(new WebViewClient());
    }
}