package com.wangkeke.mdsimple;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.fabBtn)
    FloatingActionButton fabBtn;
    @Bind(R.id.root_coordina_layout)
    CoordinatorLayout rootCoordinaLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
//    @Bind(R.id.drawer)
//    DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        //给左上角图标加上返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.hello_world, R.string.hello_world);
//        drawer.setDrawerListener(drawerToggle);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFragment(), "首页");
        adapter.addFragment(new MyFragment(), "消息");
        adapter.addFragment(new MyFragment(), "我的");

        viewPager.setAdapter(adapter);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootCoordinaLayout, "floating", Snackbar.LENGTH_LONG).show();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("one"));
        tabLayout.addTab(tabLayout.newTab().setText("two"));
        tabLayout.addTab(tabLayout.newTab().setText("three"));
        tabLayout.addTab(tabLayout.newTab().setText("three"));

        tabLayout.setupWithViewPager(viewPager);

        collapsingToolbarLayout.setTitle("酒色");

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        break;
                    case R.id.navItem2:
                        break;
                    case R.id.navItem3:
                        break;
                }
                return false;
            }
        });
    }

   /* @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (drawerToggle.onOptionsItemSelected(item))
//            return true;

        int id = item.getItemId();
        if (id == R.id.setting) {
            Snackbar.make(toolbar, "设置", Snackbar.LENGTH_LONG)
                    .setAction("todo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();
            return true;
        } else if (id == R.id.message) {
            Snackbar.make(toolbar, "消息", Snackbar.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.about) {
            Snackbar.make(toolbar, "关于", Snackbar.LENGTH_LONG).show();
            return true;
        } /*else if (id == android.R.id.home) {
            Snackbar.make(toolbar, "首页", Snackbar.LENGTH_LONG).show();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //这里返回的标题就是TabLayout的标题
            return fragmentTitles.get(position);
        }
    }
}
