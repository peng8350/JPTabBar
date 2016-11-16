package com.jpeng.jptabbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jpeng.jptabbar.animate.*;
import com.jpeng.jptabbar.badgeview.*;

import static android.R.attr.width;

/**
 * Created by jpeng on 16-11-13.
 */
class JPTabItem extends RelativeLayout {

    //Alpha动画
    private static final int ALPHA_TYPE = 0;
    //ROTATE3D动画
    private static final int ROTATE3D_TYPE = 1;
    //旋转动画
    private static final int ROTATE_TYPE = 2;
    //缩放动画
    private static final int SCALE_TYPE = 3;
    //跳跃动画
    private static final int JUMP_TYPE = 4;

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
    private BadgeImageView mIconView;


    /**
     * Badge的显示模式
     */
    private BadgeMode mBadgeMode;

    /**
     * Badge是否隐藏
     */
    private boolean mBadgeHide;

    /**
     * Badge是否可以拖动
     */
    private boolean mDragable;

    /**
     * Badge的字体大小
     */
    private int mBadgeTextSize;

    /**
     * 徽章Margin
     */
    private int mBadgeMargin;


    /**
     * Badge的背景颜色
     */
    private int mBadgeBackground;

    /**
     * 动画的实现类
     */
    private Animatable mAnimater;

    /**
     * 是否被选中
     */
    private boolean mSelected = true;

    /**
     * badge的间距
     */
    private int mBadgePadding;

    /**
     * 徽章被用户拖出去的回调事件
     */
    private BadgeDismissListener mDismissListener;


    public JPTabItem(Context context, int width) {

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
        setBackgroundDrawable(new ColorDrawable(0xffffffff));
        initPaint();
        initImageView();
    }

    /**
     * 初始化徽章
     */
    public void initBadge() {
        mIconView.getBadgeViewHelper().setBadgeGravity(BadgeViewHelper.BadgeGravity.RightTop);
        mIconView.getBadgeViewHelper().setDragable(mDragable);
        mIconView.getBadgeViewHelper().setBadgeBgColorInt(mBadgeBackground);
        mIconView.getBadgeViewHelper().setBadgeTextSizeSp(mBadgeTextSize);
        mIconView.getBadgeViewHelper().setBadgePaddingDp(mBadgePadding);
        mIconView.getBadgeViewHelper().setBadgeVerticalMarginDp(mBadgeMargin);
        mIconView.getBadgeViewHelper().setBadgeHorizontalMarginDp(mBadgeMargin);
        mIconView.getBadgeViewHelper().setDragDismissDelegage(new DragDismissDelegate() {
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
        mIconView = new BadgeImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                mIconSize + mMargin * 2, mIconSize + mMargin);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mIconView.setScaleType(ImageView.ScaleType.FIT_XY);
        mIconView.setPadding(mMargin, mMargin, mMargin, 0);
        mIconView.setLayoutParams(params);
        //设置图标
        if (mSelectIcon == null) {
            mIconView.setImageDrawable(mNormalIcon);

        } else {
            mCompundIcon = new LayerDrawable(new Drawable[]{mNormalIcon, mSelectIcon});
            mIconView.setImageDrawable(mCompundIcon);
        }
        //添加进去主布局
        addView(mIconView);


        //初始化BadgeView设置回调和属性
        initBadge();
    }

    @Override
    protected void onDraw(Canvas canvas) {
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

    public boolean isSelected() {
        return mSelected;
    }


    public void setAnimater(int type) {
        if (type == ALPHA_TYPE) {
            mAnimater = new AlphaAnimater();
        } else if (type == SCALE_TYPE) {
            mAnimater = new ScaleAnimater();
        } else if (type == ROTATE_TYPE) {
            mAnimater = new RotateAnimater();
        } else if (type == ROTATE3D_TYPE) {
            mAnimater = new FlipAnimater();
        } else if (type == JUMP_TYPE) {
            mAnimater = new JumpAnimater();
        }
    }

    /**
     * 给Item设置代理
     */
    public void setDismissDelegate(BadgeDismissListener listener) {
        this.mDismissListener = listener;
    }

    /**
     * 设置TabItem选中的状态
     *
     * @param selected
     * @param animated
     */
    public void setSelect(boolean selected, boolean animated) {

        if (mCompundIcon != null) {
            if (selected) {
                changeAlpha(1f);
            } else {
                changeAlpha(0f);
            }
        } else {
            changeColorIfneed(selected);
        }
        if (mSelected != selected) {
            mSelected = selected;

            //播放动画
            if (selected && animated) {
                mAnimater.playAnimate(mIconView, mDuration);
            }


            postInvalidate();

        }
    }


    /**
     * 假如开发者没有提供selected Icon的时候,改变图标颜色
     * 而且要接受过滤
     *
     * @param selected
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
    public void changeAlpha(float offset) {
        if (mCompundIcon != null) {

            mNormalIcon.setAlpha((int) (255 * ((float) (1 - offset))));
            mSelectIcon.setAlpha((int) (offset * 255));
        }
    }


    /**
     * 设置徽章是否显示
     */
    public void setBadgeVisibility(boolean show) {
        if (mBadgeMode == BadgeMode.OVAL) {
            if (show) {
                mIconView.showCirclePointBadge();
            } else {
                mIconView.hiddenBadge();
            }
        }
    }

    /**
     * 设置徽章的数字
     */
    public void setBadgeNumber(int count) {
        //假如是数字模式
        if (mBadgeMode == BadgeMode.NUMBER) {
            if (count == 0) {
                mIconView.hiddenBadge();
            } else if (count > 99) {
                mIconView.showTextBadge(99 + "+");
            } else {
                mIconView.showTextBadge(count + "");
            }
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

        private BadgeMode mode;

        private int badgeBackground;

        private int badgeMarin;

        private boolean dragable;

        private int badgeTextSize;

        private int badgepadding;

        private String title;

        private Context context;

        private int animateType;

        private int index;

        private int duration;

        private boolean iconfilter;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setNormalColor(int normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        public Builder setIconSize(int size) {
            this.iconSize = size;
            return this;
        }

        public Builder setIndex(int index) {
            this.index = index;
            return this;
        }

        public Builder setSelectedColor(int selectColor) {
            this.selectColor = selectColor;
            return this;
        }


        public Builder setBadgeMode(BadgeMode mode) {
            this.mode = mode;
            return this;
        }

        public Builder setDurtion(int durtion) {
            this.duration = durtion;
            return this;
        }

        public Builder setMargin(int margin){
            this.margin = margin;
            return this;
        }

        public Builder setIconFilte(boolean acceptFilte) {
            this.iconfilter = acceptFilte;
            return this;
        }

        public Builder setBadgeMargin(int margin) {
            this.badgeMarin = margin;
            return this;
        }

        public Builder setBadgeTextSize(int size) {
            this.badgeTextSize = size;
            return this;
        }

        public Builder setBadgeColor(int color) {
            this.badgeBackground = color;
            return this;
        }

        public Builder setBadgeDrable(boolean drag) {
            this.dragable = drag;
            return this;
        }

        public Builder setBadgePadding(int padding) {
            this.badgepadding = padding;
            return this;
        }


        public Builder setNormalIcon(@DrawableRes int icon) {
            this.normalIcon = icon;
            return this;
        }

        public Builder setSelectIcon(@DrawableRes int icon) {
            this.selectIcon = icon;
            return this;
        }

        public Builder setTextSize(int size) {
            this.textSize = size;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public Builder setAnimateType(int type) {
            this.animateType = type;
            return this;
        }

        public JPTabItem build() {
            JPTabItem item = new JPTabItem(context, width);
            item.setAnimater(animateType);
            item.mTextSize = textSize
                    ;
            item.mTitle = title;
            item.mNormalColor = normalColor;
            item.mSelectColor = selectColor;
            item.mBadgeMode = mode;
            item.mBadgeTextSize = badgeTextSize;
            item.mNormalIcon = context.getResources().getDrawable(normalIcon);
            if (selectIcon != 0)
                item.mSelectIcon = context.getResources().getDrawable(selectIcon);
            item.mBadgePadding = badgepadding;
            item.mBadgeBackground = badgeBackground;
            item.mDragable = dragable;
            item.mIndex = index;
            item.mBadgeMargin = badgeMarin;
            item.mIconSize = iconSize;
            item.mDuration = duration;
            item.mMargin = margin;
            item.mAcceptFilter = iconfilter;

            item.init(context);
            return item;
        }


    }

}
