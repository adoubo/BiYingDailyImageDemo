package com.adoubo.biyingdailyimagedemo;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private BingFragment bingFragment;
    private IcibaFragment icibaFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private int[] titles = {R.string.tab_bing, R.string.tab_iciba};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("cwx", "onCreate");
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        bingFragment = new BingFragment();
        icibaFragment = new IcibaFragment();
        fragments.add(bingFragment);
        fragments.add(icibaFragment);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tabLayout.getSelectedTabPosition();
                Log.i("cwx", "index: " + tabIndex + " fragment: " + fragments.get(tabIndex));
                getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fragments.get(tabIndex)).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_bing), true);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_iciba));
    }
}
