package com.betterschool.co.letters.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.betterschool.co.letters.lettersFragment;

public class CustomAdapterLetterViewPager extends FragmentStatePagerAdapter {
    public CustomAdapterLetterViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //Active
                return new lettersFragment("send");
            case 1:
                //deActive
                return new lettersFragment("receive");
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ارسال شده";
            case 1:
                return "دریافت شده";
        }

        return super.getPageTitle(position);
    }


    @Override
    public int getCount() {
        return 2;
    }
}


