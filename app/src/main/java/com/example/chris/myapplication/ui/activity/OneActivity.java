package com.example.chris.myapplication.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.ui.fragment.TabContentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:00
 */

public class OneActivity extends BaseActivity {


    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    @Bind(R.id.vp_content)
    ViewPager vpContent;
    @Bind(R.id.tl_tab)
    TabLayout tlTab;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected int initUI() {
        return R.layout.activity_one;
    }


    @Override
    protected void initData() {
        initContent();
        initTab();

    }

    @Override
    protected void initListener() {
    }


    int i=0;

    private void initTab() {

        tlTab.setTabMode(TabLayout.MODE_FIXED);
        tlTab.setTabGravity(TabLayout.GRAVITY_FILL);

        tlTab.setSelectedTabIndicatorColor(Color.BLUE);
//        ViewCompat.setElevation(tlTab, 10);
        tlTab.setupWithViewPager(vpContent);

        Observable.from(tabIndicators)
                .map(new Func1<String, TabLayout.Tab>() {
                    @Override
                    public TabLayout.Tab call(String s) {
                        return tlTab.getTabAt(tabIndicators.indexOf(s));
                    }
                })
                .filter(new Func1<TabLayout.Tab, Boolean>() {
                    @Override
                    public Boolean call(TabLayout.Tab itemTab) {
                        return itemTab != null;
                    }
                })
                .subscribe(new Action1<TabLayout.Tab>() {
                    @Override
                    public void call(TabLayout.Tab itemTab) {
                        itemTab.setCustomView(R.layout.item_tab_layout_custom);

                        TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                        itemTv.setText(tabIndicators.get(i++));
                    }
                });

    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabFragments = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            tabIndicators.add("Tab " + i);
        }

        for (String s : tabIndicators) {
            tabFragments.add(TabContentFragment.newInstance(s));
        }
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }


}
