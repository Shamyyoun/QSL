package com.mahmoudelshamy.qsl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import views.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private String[] mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    /**
     * method, used to initialize components
     */
    private void initComponents() {
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabs = getResources().getStringArray(R.array.main_tabs);

        // customize view pager
        mViewPager.setOffscreenPageLimit(mTabs.length);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        // customize tab sliding layout
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(android.R.color.white);
            }
        });
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    /**
     * --Tabs Adapter--
     */
    public class TabsAdapter extends FragmentStatePagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    fragment = new StandingsFragment();
                    break;

                case 1:
                    fragment = new ScheduleFragment();
                    break;

                case 2:
                    fragment = new ScorersFragment();
                    break;

                case 3:
                    fragment = new AssistsFragment();
                    break;

                case 4:
                    fragment = new WinnerFragment();
                    break;
            }
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs[position];
        }

        @Override
        public int getCount() {
            return mTabs.length;
        }
    }
}
