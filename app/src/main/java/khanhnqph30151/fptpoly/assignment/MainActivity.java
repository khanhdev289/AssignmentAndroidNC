package khanhnqph30151.fptpoly.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import khanhnqph30151.fptpoly.assignment.fragment.FavoriteFragment;
import khanhnqph30151.fptpoly.assignment.fragment.MusicFragment;
import khanhnqph30151.fptpoly.assignment.fragment.NewsFragment;
import khanhnqph30151.fptpoly.assignment.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView view = findViewById(R.id.bottom_navi);
        Bundle check = getIntent().getExtras();
        boolean checkdn = check.getBoolean("khach");
        if(checkdn == true){
            view.getMenu().clear();
            view.inflateMenu(R.menu.menu_khach);
        }
        else if(checkdn == false) {
            view.getMenu().clear();
            view.inflateMenu(R.menu.menu_bottom_navi);
        }
        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_music) {
                    replaceFragment(new MusicFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_favorite) {
                    replaceFragment(new FavoriteFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_newsp) {
                    replaceFragment(new NewsFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_user) {
                    replaceFragment(new UserFragment());
                    return true;
                }else {
                    return false;
                }
            }
        });

        replaceFragment(new MusicFragment());
    }
    public void replaceFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}