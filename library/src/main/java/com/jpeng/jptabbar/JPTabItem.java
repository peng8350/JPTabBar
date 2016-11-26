package com.jpeng.jptabbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jpeng.jptabbar.animate.Animatable;
import com.jpeng.jptabbar.badgeview.BadgeRelativeLayout;
import com.jpeng.jptabbar.badgeview.BadgeViewHelper;
import com.jpeng.jptabbar.badgeview.Badgeable;
import com.jpeng.jptabbar.badgeview.DragDismissDelegate;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by jpeng on 16-11-13.
 * TabBarItem类
 */
public class JPTabItem extends BadgeRelativeLayout {


    //颜色渐变时间
    private static final int FILTER_DURATION = 400;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 标题
     */
    private String mTitle;

    /**
     * 索引值
     */
    private int mIndex;

    /**
     * 对应图标的大小
     */
    private int mIconSize;

    /**
     * Tab的上下编剧
     */
    private int mMargin;

    /**
     * Tab的没有选中图标
     */
    private Drawable mNormalIcon;

    /**
     * Tab选中的图标
     */
    private Drawable mSelectIcon;

    /**
     * 选中颜色(包括底部文字和图标)
     */
    private int mSelectColor;

    /**
     * 没有选中的颜色(包括底部文字和图标)
     */
    private int mNormalColor;

    /**
     * 选中的颜色
     */
    private Drawable mSelectBg;

    /**
     * Tab字体大小
     */
    private int mTextSize;
    /**
     * Tab字体的画笔
     */
    private Paint mTextPaint;

    /**
     * 允许图标颜色随着字体颜色变化而变化
     */
    private boolean mAcceptFilter;

    /**
     * 动画的长度
     */
    private int mDuration;

    /**
     * 图标的图层
     */
    private LayerDrawable mCompundIcon;


    /**
     * 图标ImageView
     */
    private ImageView mIconView;

    /**
     * Badge是否可以拖动
     */
    private boolean mDraggable;

    /**
     * Badge的字体大小
     */
    private int mBadgeTextSize;

    /**
     * 徽章垂直Margin
     */
    private int mBadgeVerMargin;

    /**
     * 徽章水平Margin
     */
    private int mBadgeHorMargin;

    /**
     * Badge的背景颜色
     */
    private int mBadgeBackground;



    /**
     * 是否被选中
     */
    private boolean mSelected;

    /**
     * badge的间距
     */
    private int mBadgePadding;

    /**
     * 徽章被用户拖出去的回调事件
     */
    private BadgeDismissListener mDismissListener;

    public JPTabItem(Context context) {
        super(context);
    }

    /**
     * 初始化布局和数据
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 3;
        setLayoutParams(params);
        initPaint();
        initImageView();
        setBackgroundResource(android.R.color.transparent);
    }

    /**
     * 初始化徽章
     */
    public void initBadge() {
        getBadgeViewHelper().setBadgeGravity(BadgeViewHelper.BadgeGravity.RightTop);
        getBadgeViewHelper().setDragable(mDraggable);
        getBadgeViewHelper().setBadgeBgColorInt(mBadgeBackground);
        getBadgeViewHelper().setBadgeTextSizeSp(mBadgeTextSize);
        getBadgeViewHelper().setBadgePaddingDp(mBadgePadding);
        getBadgeViewHelper().setBadgeVerticalMarginDp(mBadgeVerMargin);
        getBadgeViewHelper().setBadgeHorizontalMarginDp(mBadgeHorMargin);
        getBadgeViewHelper().setDragDismissDelegage(new DragDismissDelegate() {
            @Override
            public void onDismiss(Badgeable badgeable) {
                if (mDismissListener != null)
                    mDismissListener.onDismiss(mIndex);
            }
        });
    }


    /**
     * 初始化所有画笔
     */
    private void initPaint() {
        //底部画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(DensityUtils.sp2px(mContext, mTextSize));

    }

    /**
     * 初始化图标ImageView
     */
    private void initImageView() {
        //设置ImageView布局属性
        mIconView = new ImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                mIconSize , mIconSize );
        params.addRule(mTitle==null?RelativeLayout.CENTER_IN_PARENT:RelativeLayout.CENTER_HORIZONTAL);
        if(mTitle!=null)
        params.topMargin = mMargin;
        mIconView.setScaleType(ImageView.ScaleType.FIT_XY);
        mIconView.setLayoutParams(params);
        //设置图标
        if (mSelectIcon == null) {
            mIconView.setImageDrawable(mNormalIcon);

        } else {
            mCompundIcon = new LayerDrawable(new Drawable[]{mNormalIcon, mSelectIcon});
            mNormalIcon.setAlpha(255);
            mSelectIcon.setAlpha(0);
            mIconView.setImageDrawable(mCompundIcon);
        }

        //添加进去主布局
        addView(mIconView);
        //初始化BadgeView设置回调和属性
        initBadge();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(mTitle!=null)
        DrawText(canvas);
    }


    /**
     * 画底部文字
     */
    private void DrawText(Canvas canvas) {

        Rect textBound = new Rect();
        mTextPaint.getTextBounds(mTitle, 0, mTitle.length(), textBound);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();

        if (mSelected) {
            //选中
            mTextPaint.setColor(mSelectColor);
        } else {
            mTextPaint.setColor(mNormalColor);
        }

        float x = getMeasuredWidth() / 2f;
        float y = getTextY(textBound, fontMetrics);
        canvas.drawText(mTitle, x, y, mTextPaint);
    }

    /**
     * 得到字体的X轴坐标
     */
    private float getTextY(Rect textBound, Paint.FontMetrics fontMetrics) {
        return (getMeasuredHeight() - mMargin - textBound.height() / 2f - fontMetrics.descent + (fontMetrics.descent - fontMetrics.ascent) / 2);
    }

    public boolean isBadgeShow() {
        return isShowBadge();
    }

    public ImageView getIconView() {
        return mIconView;
    }

    /**
     * 给Item设置代理
     */
    void setDismissDelegate(BadgeDismissListener listener) {
        this.mDismissListener = listener;
    }

    /**
     * 设置TabItem选中的状态
     *
     * @param selected
     * @param animated
     */
    void setSelect(Animatable animatable,boolean selected, boolean animated) {
        if (selected&&mSelectBg!=null){
            setBackgroundDrawable(mSelectBg);
        }
        else{
            setBackgroundResource(android.R.color.transparent);
        }
        if (mSelected != selected) {
            mSelected = selected;
            if (mCompundIcon != null) {
                if (selected) {
                    if(!animated) {
                        changeAlpha(1f);
                    }
                    else {
                        ObjectAnimator.ofInt(mSelectIcon, "alpha", 0, 255).setDuration(FILTER_DURATION).start();
                        ObjectAnimator.ofInt(mNormalIcon, "alpha", 255, 0).setDuration(FILTER_DURATION).start();
                    }
                } else {
                    if(!animated) {
                        changeAlpha(0f);
                    }
                    else {
                        ObjectAnimator.ofInt(mNormalIcon, "alpha", 0, 255).setDuration(FILTER_DURATION).start();
                        ObjectAnimator.ofInt(mSelectIcon, "alpha", 255, 0).setDuration(FILTER_DURATION).start();
                    }
                }
            } else {
                changeColorIfneed(selected);
            }
            //播放动画
            if ( animated) {
                if (animatable != null) {
                    animatable.playAnimate(mIconView,mSelected);
                }
            }

            postInvalidate();

        }
    }

    boolean isSelect(){
        return mSelected;
    }
    /**
     * 假如开发者没有提供selected Icon的时候,改变图标颜色
     * 而且要接受过滤
     *
     */
    private void changeColorIfneed(boolean selected) {
        if (mAcceptFilter && mSelectIcon == null
                ) {
            if (selected) {
                mIconView.setColorFilter(mSelectColor);

            } else {
                mIconView.setColorFilter(mNormalColor);
            }
        }
    }

    /**
     * 这个方法用来改变图标的颜色
     * 在滑动Viewpager回调onScroll方法
     * 传入1时,selectedicon将会显示
     * 传入0时,NormalIcon将会显示
     *
     * @param offset
     */
    void changeAlpha(float offset) {
        if (mCompundIcon != null) {
            mNormalIcon.setAlpha((int) (255 * ((float) (1 - offset))));
            mSelectIcon.setAlpha((int) (offset * 255));
        }
    }


    /**
     * 构造者
     */
    static class Builder {

        private int iconSize;

        private int margin;

        private int selectColor;

        private int normalColor;

        private int textSize;

        private int normalIcon;

        private int selectIcon;

        private int badgeBackground;

        private int badgeVerMargin;

        private int badgeHorMargin;

        private boolean dragable;

        private int badgeTextSize;

        private int badgepadding;

        private Drawable selectbg;

        private String title;

        private Context context;

        private int index;

        private int duration;

        private boolean iconfilter;



        public Builder(Context context) {
            this.context = context;
        }

        Builder setNormalColor(int normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        Builder setIconSize(int size) {
            this.iconSize = size;
            return this;
        }

        Builder setIndex(int index) {
            this.index = index;
            return this;
        }

        Builder setSelectedColor(int selectColor) {
            this.selectColor = selectColor;
            return this;
        }


        Builder setMargin(int margin) {
            this.margin = margin;
            return this;
        }

        Builder setIconFilte(boolean acceptFilte) {
            this.iconfilter = acceptFilte;
            return this;
        }

        Builder setBadgeVerMargin(int margin) {
            this.badgeVerMargin = margin;
            return this;
        }

        Builder setBadgeHorMargin(int margin) {
            this.badgeHorMargin = margin;
            return this;
        }

        Builder setBadgeTextSize(int size) {
            this.badgeTextSize = size;
            return this;
        }

        Builder setBadgeColor(int color) {
            this.badgeBackground = color;
            return this;
        }

        Builder setSelectBg(Drawable res){
            this.selectbg = res;
            return this;
        }

        Builder setBadgeDrable(boolean drag) {
            this.dragable = drag;
            return this;
        }

        Builder setBadgePadding(int padding) {
            this.badgepadding = padding;
            return this;
        }


        Builder setNormalIcon(@DrawableRes int icon) {
            this.normalIcon = icon;
            return this;
        }

        Builder setSelectIcon(@DrawableRes int icon) {
            this.selectIcon = icon;
            return this;
        }

        Builder setTextSize(int size) {
            this.textSize = size;
            return this;
        }

        Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        JPTabItem build() {
            JPTabItem item = new JPTabItem(context);
            item.mTextSize = textSize
            ;
            item.mTitle = title;
            item.mNormalColor = normalColor;
            item.mSelectColor = selectColor;
            item.mBadgeTextSize = badgeTextSize;
            item.mNormalIcon = context.getResources().getDrawable(normalIcon).mutate();
            if (selectIcon != 0)
                item.mSelectIcon = context.getResources().getDrawable(selectIcon).mutate();
            item.mBadgePadding = badgepadding;
            item.mBadgeBackground = badgeBackground;
            item.mDraggable = dragable;
            item.mIndex = index;
            item.mBadgeHorMargin = badgeHorMargin;
            item.mBadgeVerMargin = badgeVerMargin;
            item.mIconSize = iconSize;
            item.mMargin = margin;
            item.mAcceptFilter = iconfilter;
            item.mSelectBg = selectbg;
            item.init(context);
            return item;
        }


    }

}
