package com.example.lovehive;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MyAdapterActivity myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        myAdapter = new MyAdapterActivity(getSupportFragmentManager());
        myAdapter.addData();
        viewPager.setAdapter(myAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
