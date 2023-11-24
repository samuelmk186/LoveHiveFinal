// MyAdapterActivity.java
package com.example.lovehive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class MyAdapterActivity extends FragmentPagerAdapter {
    ArrayList<String> arrayList = new ArrayList<>();

    public MyAdapterActivity(@NonNull FragmentManager fragmentManager){
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 2) {
            return new FragmentProfileActivity();
        } else {
            Fragment myFragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("TEXT_ID", arrayList.get(position));
            myFragment.setArguments(bundle);
            return myFragment;
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public void addData() {
        arrayList.add("CHATS");
        arrayList.add("STATUS");
        arrayList.add("Profile");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
