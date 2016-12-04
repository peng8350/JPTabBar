package com.jpeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import static com.jpeng.demo.R.id.tabbar;


public class MainActivity extends AppCompatActivity implements BadgeDismissListener, OnTabSelectListener {

    private List<Fragment> list = new ArrayList<>();
    //    @Titles
//    private int[] titles = {R.string.Tab1Pager,R.string.Tab2Pager,R.string.Tab3Pager,R.string.Tab4Pager};
//    @NorIcons
//    private int[] mNormalIcons = {R.mipmap.tab1_normal,R.mipmap.tab2_normal,R.mipmap.tab3_normal,R.mipmap.tab4_normal};
//    @SeleIcons
//    private int[] mSelectedIcons =  {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};
    private NoScrollViewPager mPager;

    private JPTabBar mTabbar;

    private Tab1Pager mTab1;

    private Tab2Pager mTab2;

    private Tab3Pager mTab3;

    private Tab4Pager mTab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (NoScrollViewPager) findViewById(R.id.view_pager);
        mTabbar = (JPTabBar) findViewById(tabbar);
        mTabbar.setTitles(R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4)
                .setNormalIcons(R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal)
                .setSelectedIcons(R.mipmap.tab1_selected, R.mipmap.tab2_selected, R.mipmap.tab3_selected, R.mipmap.tab4_selected)
                .generate();
        mPager.setNoScroll(false);
        mTab1 = new Tab1Pager();
        mTab2 = new Tab2Pager();
        list.add(mTab1);
        list.add(mTab2);
        list.add(new Tab3Pager());
        list.add(new Tab4Pager());
//        list.add(new Tab4Pager());
//        list.add(new Tab4Pager());
        mPager.setAdapter(new com.jpeng.demo.Adapter(getSupportFragmentManager(), list));
        //显示圆点模式的徽章
        //设置容器
        mTabbar.showBadge(0, 50);
        mTabbar.setContainer(mPager);
        //设置Badge消失的代理
        mTabbar.setDismissListener(this);
        mTabbar.setTabListener(this);
    }

    @Override
    public void onDismiss(int position) {
        if (position == 0) {
            mTab1.clearCount();
        }
    }


    @Override
    public void onTabSelect(int index) {

    }

    @Override
    public void onClickMiddle(View middleBtn) {
        Intent intent = new Intent(this, SayActivity.class);
        startActivity(intent);
    }

    public JPTabBar getTabbar() {
        return mTabbar;
    }

}
