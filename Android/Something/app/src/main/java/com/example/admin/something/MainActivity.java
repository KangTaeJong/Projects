package com.example.admin.something;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private static final int PERMISSION_REQUEST_CODE = 1000;
    public static Context context;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.ViewPager);

        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ((ViewPagerAdapter)viewPager.getAdapter()).setTabLayoutIcon(tabLayout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for(int result : grantResults) {
            if(result == PackageManager.PERMISSION_DENIED) {
                System.exit(0);
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void init() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        requestPermissions(permissions, PERMISSION_REQUEST_CODE);

        context = this;
        Database.init(this);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<ViewPageFragment> fragments;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            fragments = new ArrayList<>();
            fragments.add(new ListFragment());
            fragments.add(new CalendarFragment());
            fragments.add(new OtherFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void setTabLayoutIcon(TabLayout tabLayout) {
            for(int i = 0;i < fragments.size();i++) {
                tabLayout.getTabAt(i).setIcon(fragments.get(i).getIcon());
            }
        }
    }
}
