package com.jpeng.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import java.util.ArrayList;
import java.util.List;

import static com.jpeng.demo.R.id.tabbar;


public class MainActivity extends FragmentActivity implements BadgeDismissListener, OnTabSelectListener {

    private List<Fragment> list=new ArrayList<>();
    @Titles
    private static final int[] mTitles = {R.string.tab1,R.string.tab2,R.string.tab3,R.string.tab4};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};

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
        list.add(new tab4());
        list.add(new tab4());
        mPager.setAdapter(new com.jpeng.demo.Adapter(getSupportFragmentManager(),list));
        //显示圆点模式的徽章
        //设置容器
        mTabbar.ShowBadge(0,999);
        mTabbar.setContainer(mPager);
        //设置Badge消失的代理
        mTabbar.setDismissListener(this);
        mTabbar.setTabListener(this);

    }

    @Override
    public void onDismiss(int position) {

    }

    @Override
    public void onTabSelect(int index) {

    }

    @Override
    public void onClickMiddle(View middleBtn){
        Toast.makeText(this,"点击中间",0).show();
    }
}
