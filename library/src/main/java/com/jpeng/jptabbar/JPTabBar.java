package com.jpeng.jptabbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.jpeng.jptabbar.animate.Animatable;
import com.jpeng.jptabbar.anno.BadgeModes;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import com.jpeng.jptabbar.badgeview.BadgeMode;

import java.lang.reflect.Field;

/**
 * Created by jpeng on 16-11-13.
 */
public class JPTabBar extends LinearLayout implements ViewPager.OnPageChangeListener {
    //默认的图标大小
    private static final int DEFAULT_ICONSIZE = 24;
    //字体默认大小
    private static final int DEFAULT_TEXTSIZE = 14;
    //默认的没有选中的文字和图标的颜色
    private static final int DEFAULT_NORMAL_COLOR = 0xffAEAEAE;

    //默认的上下背景间隔
    private static final int DEFAULT_MARGIN=8;
    //默认的选中颜色
    private static final int DEFAULT_SELECT_COLOR = 0xff59D9B9;

    //默认的徽章背景颜色
    private static final int DEFAULT_BADGE_COLOR = 0xffff0000;

    //默认的徽章字体大小
    private static final int DEFAULT_BADGE_TEXTSIZE = 11;

    //默认的动画类型是3D旋转
    private static final int DEFAULT_ANIMATE_TYPE = 1;
    //默认的徽章距离中心点距离
    private static final int DEFAULT_PADDING = 8;
    //默认的动画时间
    private static final int DEFAULT_DURATION = 500;
    private static final boolean DEFAULT_ACEEPTFILTER = true;
    private static final int DEFAULT_BADGEMARGIN = 3;

    private Context mContext;

    private TypedArray mAttribute;

    private int mHeight;
    /**
     * 选中的当前Tab的位置
     */
    private int mSelectIndex;

    /**
     * 所有Tabitem
     */
    private JPTabItem[] mJPTabItems;

    /**
     * 中间按钮
     */
    private JPMiddleTabItem mMiddleItem;

    /**
     * 监听点击Tab回调的观察者
     */
    private OnTabSelectListener mTabSelectLis;

    /**
     * 判断是否需要动画,解决Viewpager回调onpageChange冲突事件
     */
    private boolean mNeedAnimate;

    /**
     * Tab对应的ViewPager
     */
    private ViewPager mTabPager;


    public JPTabBar(Context context) {
        super(context);
        init(context, null);
    }

    public JPTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public JPTabBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * 初始化TabBar
     *
     * @param context 上下文
     * @param set     XML结点集合
     */
    private void init(Context context, AttributeSet set) {

        mContext = context;

        mAttribute = context.obtainStyledAttributes(set, R.styleable.JPTabBar);
        setClipChildren(false);
        initFromAttribute();

    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        getLayoutParams().height = mHeight;
    }


    public void initFromAttribute() {

        String[] titles = null;
        //普通图标
        int[] normalIcon = null;
        //选中的图标
        int[] selectedIcon = null;
        //Badge的模式
        BadgeMode[] modes = null;

        /**
         * 获取所有节点属性
         */
        mHeight = mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_TabHeight, DensityUtils.dp2px(mContext, 48));
        int normalColor = mAttribute.getColor(R.styleable.JPTabBar_TabNormalColor, DEFAULT_NORMAL_COLOR);
        int selectColor = mAttribute.getColor(R.styleable.JPTabBar_TabSelectColor, DEFAULT_SELECT_COLOR);
        int textSize = DensityUtils.px2sp(mContext, mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_TabTextSize, DensityUtils.sp2px(mContext, DEFAULT_TEXTSIZE)));
        //这里
        int iconSize = mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_TabIconSize, DensityUtils.dp2px(mContext, DEFAULT_ICONSIZE));
        int margin = mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_TabMargin,DensityUtils.dp2px(mContext,DEFAULT_MARGIN));
        int AnimateType = mAttribute.getInt(R.styleable.JPTabBar_TabAnimate, DEFAULT_ANIMATE_TYPE);
        int BadgeColor = mAttribute.getColor(R.styleable.JPTabBar_BadgeColor, DEFAULT_BADGE_COLOR);
        int BadgetextSize = DensityUtils.px2sp(mContext, mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_BadgeTextSize, DensityUtils.sp2px(mContext, DEFAULT_BADGE_TEXTSIZE)));
        boolean dragable = mAttribute.getBoolean(R.styleable.JPTabBar_BadgeDraggable, false);
        int badgePadding = DensityUtils.px2dp(mContext,mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_BadgePadding, DensityUtils.dp2px(mContext,DEFAULT_PADDING)));
        int duration = mAttribute.getInteger(R.styleable.JPTabBar_TabDuration, DEFAULT_DURATION);
        int badgeMargin = DensityUtils.px2dp(mContext,mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_BadgeMargin, DensityUtils.dp2px(mContext,DEFAULT_BADGEMARGIN)));
        boolean acceptFilter = mAttribute.getBoolean(R.styleable.JPTabBar_TabIconFilter, DEFAULT_ACEEPTFILTER);
        //反射注解
        Field[] fields = mContext.getClass().getDeclaredFields();

        //遍历所有字段,寻找标记
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Titles.class)) {
                //标题
                try {
                    titles = (String[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(NorIcons.class)) {
                try {
                    normalIcon = (int[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(SeleIcons.class)) {
                try {
                    selectedIcon = (int[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(BadgeModes.class)) {
                try {
                    modes = (BadgeMode[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }


        CheckIfAssertError(titles, normalIcon, selectedIcon, modes);


        //计算Tab的宽度
        int MiddleIconId = mAttribute.getResourceId(R.styleable.JPTabBar_TabMiddleIcon, 0);
        mJPTabItems = new JPTabItem[titles.length];
        //实例化TabItem添加进去
        for (int i = 0; i < titles.length; i++) {
            final int temp = i;

            mJPTabItems[i] = new JPTabItem.Builder(mContext).setTitle(titles[i]).setIndex(temp).setTextSize(textSize)
                    .setNormalColor(normalColor).setAnimateType(AnimateType)
                    .setBadgeMode(modes == null ? BadgeMode.NUMBER : modes[temp]).setBadgeTextSize(BadgetextSize)
                    .setSelectedColor(selectColor).setNormalIcon(normalIcon[i]).setBadgeDrable(dragable).setBadgeColor(BadgeColor)
                    .setBadgePadding(badgePadding).setIconSize(iconSize).setIconFilte(acceptFilter)
                    .setDurtion(duration).setBadgeMargin(badgeMargin).setMargin(margin)
                    .setSelectIcon(selectedIcon == null ? 0 : selectedIcon[i]).build();
            mJPTabItems[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectTab(temp);
                }
            });
            addView(mJPTabItems[i]);

            //判断是不是准备到中间的tab,假如设置了中间图标就添加进去
            if (i == (titles.length / 2 - 1)) {

                mMiddleItem = BuildMiddleBtn(mHeight, MiddleIconId);

            }

        }


        setSelectTab(0, false);

        mAttribute.recycle();
    }

    /**
     * 判断有没有声明变量的错误
     *
     * @param titles       标题
     * @param normalIcon   没有选中的图标
     * @param selectedIcon 选中的图标
     * @param modes        Badge显示的模式
     */
    private void CheckIfAssertError(String[] titles, int[] normalIcon, int[] selectedIcon, BadgeMode[] modes) {
        if (titles == null || normalIcon == null) {
            throw new TabException("you must set the Titles Annotation and NormalIcon Annotation for the JPTabbar!!!");
        }
        int originlen = titles.length;
        //判断注解里面的数组长度是否一样
        if ((originlen != normalIcon.length)
                || (selectedIcon != null && originlen != selectedIcon.length) || (modes != null && originlen != modes.length)) {
            throw new TabException("the Annotation Array length is not equal,Please Check Your Annotation in your Activity,Ensure Every Array length is equals!");
        }
    }

    private JPMiddleTabItem BuildMiddleBtn(int height, int icon_res) {
        if (icon_res == 0) return null;
        JPMiddleTabItem middleBtn = new JPMiddleTabItem(mContext, height);

        middleBtn.setIcon(mContext.getResources().getDrawable(icon_res));

        middleBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTabSelectLis != null)
                    mTabSelectLis.onClickMiddle();
            }
        });

        addView(middleBtn);
        return middleBtn;
    }

    /****-------提供给开发者调用的方法---------****/


    /**
     * 切换Tab页面
     */
    public void setSelectTab(int index) {
        setSelectTab(index,true);
    }

    /**
     * 切换Tab页面,是否带动画
     */
    public void setSelectTab(int index, boolean animated) {
        mNeedAnimate= animated;
        mSelectIndex = index;
        //把全部tab selected设置为false
        for (int i = 0; i < mJPTabItems.length; i++) {
            if (i == index) {
                continue;
            }
            mJPTabItems[i].setSelect(false, false);
        }
        if (mTabPager != null) {
            mTabPager.setCurrentItem(index, false);
        }
        mJPTabItems[index].setSelect(true, animated);

        if (mTabSelectLis != null) {
            mTabSelectLis.onTabSelect(index);
        }
    }

    /**
     * 设置容器和TabBar联系在一起
     */
    public void setContainer(ViewPager pager) {
        if (pager != null) {
            mTabPager = pager;
            mTabPager.setOnPageChangeListener(this);
        }
    }

    /**
     * 显示OVAL原点
     */
    public void ShowOvalBadge(int position) {
        mJPTabItems[position].setBadgeVisibility(true);
    }

    /**
     * 隐藏OVAL圆点
     */
    public void HideOvalBadge(int position) {
        mJPTabItems[position].setBadgeVisibility(false);
    }

    /**
     * 获得选中的位置
     */
    public int getSelectPosition() {
        return mSelectIndex;
    }

    /**
     * 获取徽章是否在显示
     */
    public boolean isBadgeShow(int index){
        return mJPTabItems[index].isBadgeShow();
    }

    /**
     * 设置徽章消息数量
     *
     * @param position Tab的位置
     * @param   count    消息的数量
     */
    public void setBadgeCount(int position, int count) {

        mJPTabItems[position].setBadgeNumber(count);
    }

    /**
     * 设置自定义动画
     */
    public void setCustomAnimate(Animatable customAnimate){
        for(JPTabItem item:mJPTabItems){
            item.setAnimater(customAnimate);
        }
    }

    /**
     * 设置点击TabBar事件的观察者
     */
    public void setTabListener(OnTabSelectListener listener) {
        mTabSelectLis = listener;
    }

    /**
     * 设置badgeView消失的回调事件
     */
    public void setDismissListener(BadgeDismissListener listener) {
        for (JPTabItem item : mJPTabItems) {
            item.setDismissDelegate(listener);
        }
    }

    /****---------------****/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            mJPTabItems[position].changeAlpha(1 - positionOffset);
            mJPTabItems[position + 1].changeAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int position) {
        if(mNeedAnimate)
        setSelectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state==1){
            mNeedAnimate = true;
        }
    }


}
