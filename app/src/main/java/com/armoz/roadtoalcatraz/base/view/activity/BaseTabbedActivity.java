package com.armoz.roadtoalcatraz.base.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;

public abstract class BaseTabbedActivity extends BaseDrawerActivity {

    FragmentPagerAdapter fragmentPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(R.id.frame_layout);
        getLayoutInflater().inflate(R.layout.activity_base_tabbed, view);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        for (int i = 0; i < getTabsNumber(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(getTabTitle(i)));
        }

        viewPager = (ViewPager) findViewById(R.id.pager);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return getTab(position);
            }

            @Override
            public int getCount() {
                return getTabsNumber();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getTabTitle(position);
            }

        };

        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });

        tabLayout.setupWithViewPager(viewPager);

    }

    protected abstract int getTabsNumber();

    protected abstract Fragment getTab(int position);

    protected abstract CharSequence getTabTitle(int position);


}
