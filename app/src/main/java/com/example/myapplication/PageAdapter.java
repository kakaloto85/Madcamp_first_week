package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.transition.FragmentTransitionSupport;

import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int numberoftabs;
    public PageAdapter(@NonNull FragmentManager fm, int numberoftabs) {
        super(fm);
        this.numberoftabs = numberoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //탭을
        switch (position) {
            case 0:

                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return numberoftabs;

    }
}
