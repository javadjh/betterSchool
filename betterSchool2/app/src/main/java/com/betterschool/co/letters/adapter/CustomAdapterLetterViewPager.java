package com.betterschool.co.letters.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.betterschool.co.letters.lettersFragment;

import org.jetbrains.annotations.NotNull;

public class CustomAdapterLetterViewPager extends FragmentStatePagerAdapter {
    String department;

    public CustomAdapterLetterViewPager(@NonNull @NotNull FragmentManager fm, String department) {
        super(fm);
        this.department = department;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (department) {
            case "headmaster":
                if (position == 0) {
                    return new lettersFragment("send");
                }
                break;
            case "teacher":
                switch (position) {
                    case 0:
                        return new lettersFragment("public");
                    case 1:
                        return new lettersFragment("send");
                    case 2:
                        return new lettersFragment("private");
                }
                break;
            case "student":
                switch (position) {
                    case 0:
                        return new lettersFragment("public");
                    case 1:
                        return new lettersFragment("private");
                }
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (department) {
            case "headmaster":
                if (position == 0) {
                    return "ارسال شده";
                }
                break;
            case "teacher":
                switch (position) {
                    case 0:
                        return "عمومی";
                    case 1:
                        return "ارسال شده";
                    case 2:
                        return "خصوصی";
                }
                break;
            case "student":
                switch (position) {
                    case 0:
                        return "عمومی";
                    case 1:
                        return "خصوصی";
                }
                break;
        }

        return super.getPageTitle(position);
    }


    @Override
    public int getCount() {
        switch (department){
            case "headmaster":
                return 1;
            case "teacher":
                return 3;
            case "student":
                return 2;
            default:return 0;
        }
    }
}


