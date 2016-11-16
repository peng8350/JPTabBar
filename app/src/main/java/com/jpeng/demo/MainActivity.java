package com.jpeng.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.BadgeModes;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import com.jpeng.jptabbar.badgeview.BadgeMode;

import java.util.ArrayList;
import java.util.List;

import static com.jpeng.demo.R.id.tabbar;


public class MainActivity extends FragmentActivity implements BadgeDismissListener, OnTabSelectListener {

    private List<Fragment> list=new ArrayList<>();
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};

    @BadgeModes
    private static final BadgeMode[] mBadgeModes = {BadgeMode.OVAL,BadgeMode.NUMBER,BadgeMode.OVAL,BadgeMode.NUMBER};


    private ViewPager mPager;

    private JPTabBar mTabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.view_pager);

        mTabbar = (JPTabBar) findViewById(tabbar);

        list.add(new tab1());
        list.add(new tab2());
        list.add(new tab3());
        list.add(new tab4());

        mPager.setAdapter(new com.jpeng.demo.Adapter(getSupportFragmentManager(),list));
        //显示圆点模式的徽章
        mTabbar.ShowOvalBadge(0);
        mTabbar.ShowOvalBadge(2);
        //显示数字模式的徽章
        mTabbar.setBadgeCount(1,100);
        mTabbar.setBadgeCount(3,3);
        //设置容器
        mTabbar.setContainer(mPager);
        //切换页面
//        mTabbar.setSelectTab(3,false);
        //设置Badge消失的代理
        mTabbar.setDismissListener(this);
        mTabbar.setTabListener(this);
    }

    @Override
    public void onDismiss(int position){
        Toast.makeText(this,"Badge消失位置是 ："+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabSelect(int index) {
    }

    @Override
    public void onClickMiddle() {
        Toast.makeText(this,"中间的按钮被点击了",Toast.LENGTH_SHORT).show();
    }
}
