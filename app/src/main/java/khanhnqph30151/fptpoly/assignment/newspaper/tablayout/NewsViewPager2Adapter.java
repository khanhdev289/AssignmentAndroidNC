package khanhnqph30151.fptpoly.assignment.newspaper.tablayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NewsViewPager2Adapter extends FragmentStateAdapter {
    public NewsViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ThoiSuFragment();
            case 1:
                return new SoHoaFragment();
            case 2:
                return new TheGioiFragment();
            default:
                return new ThoiSuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
