package com.example.admin.something;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private static final int PERMISSION_CODE = 1000;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.ViewPager);

        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        adapter.setTabLayoutIcon(tabLayout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for(int result : grantResults) {
            if(result == PackageManager.PERMISSION_DENIED) {
                System.exit(0);
            }
        }
    }

    public void init () {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        requestPermissions(permissions, PERMISSION_CODE);

        DBHandler.init(this);
        startService(new Intent(this, MyService.class));
        context = this;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private ViewPageFragment[] fragments;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

            fragments = new ViewPageFragment[3];
            fragments[0] = new ListFragment();
            fragments[1] = new CalendarFragment();
            fragments[2] = new OtherFragment();
        }

        public void setTabLayoutIcon(TabLayout tabLayout) {
            for(int i = 0;i < fragments.length;i++) {
                tabLayout.getTabAt(i).setIcon(fragments[i].icon);
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
