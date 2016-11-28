package com.jpeng.jptabbar.badgeview;

import android.content.Context;
import android.graphics.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jpeng.jptabbar.DensityUtils;

/**
 * Created by jpeng on 16-11-16.
 */
public class BadgeViewHelper {
    private Bitmap mBitmap;
    private Badgeable mBadgeable;
    private Paint mBadgePaint;
    /**
     * 徽章背景色
     */
    private int mBadgeBgColor;
    /**
     * 徽章文本的颜色
     */
    private int mBadgeTextColor;
    /**
     * 徽章文本字体大小
     */
    private int mBadgeTextSize;
    /**
     * 徽章背景与宿主控件上下边缘间距离
     */
    private int mBadgeVerticalMargin;
    /**
     * 徽章背景与宿主控件左右边缘间距离
     */
    private int mBadgeHorizontalMargin;
    /***
     * 徽章文本边缘与徽章背景边缘间的距离
     */
    private int mBadgePadding;
    /**
     * 徽章文本
     */
    private String mBadgeText;
    /**
     * 徽章文本所占区域大小
     */
    private Rect mBadgeNumberRect;
    /**
     * 是否显示Badge
     */
    private boolean mIsShowBadge;
    /**
     * 徽章在宿主控件中的位置
     */
    private BadgeGravity mBadgeGravity;
    /**
     * 整个徽章所占区域
     */
    private RectF mBadgeRectF;
    /**
     * 是否可拖动
     */
    private boolean mDragable;
    /**
     * 拖拽徽章超出轨迹范围后，再次放回到轨迹范围时，是否恢复轨迹
     */
    private boolean mIsResumeTravel;
    /***
     * 徽章描边宽度
     */
    private int mBadgeBorderWidth;
    /***
     * 徽章描边颜色
     */
    private int mBadgeBorderColor;
    /**
     * 触发开始拖拽徽章事件的扩展触摸距离
     */
    private int mDragExtra;
    /**
     * 整个徽章加上其触发开始拖拽区域所占区域
     */
    private RectF mBadgeDragExtraRectF;
    /**
     * 拖动时的徽章控件
     */
    private DragBadgeView mDropBadgeView;
    /**
     * 是否正在拖动
     */
    private boolean mIsDraging;
    /**
     * 拖动大于BGABadgeViewHelper.mMoveHiddenThreshold后抬起手指徽章消失的代理
     */
    private DragDismissDelegate mDelegage;
    private boolean mIsShowDrawable = false;

    public BadgeViewHelper(Badgeable badgeable, Context context, AttributeSet attrs, BadgeGravity defaultBadgeGravity) {
        mBadgeable = badgeable;
        initDefaultAttrs(context, defaultBadgeGravity);
        afterInitDefaultAndCustomAttrs();
        mDropBadgeView = new DragBadgeView(context, this);
    }

    private void initDefaultAttrs(Context context, BadgeGravity defaultBadgeGravity) {
        mBadgeNumberRect = new Rect();
        mBadgeRectF = new RectF();
        mBadgeBgColor = Color.RED;
        mBadgeTextColor = Color.WHITE;
        mBadgeTextSize = DensityUtils.sp2px(context, 10);

        mBadgePaint = new Paint();
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        // 设置mBadgeText居中，保证mBadgeText长度为1时，文本也能居中
        mBadgePaint.setTextAlign(Paint.Align.CENTER);

        mBadgePadding = DensityUtils.dp2px(context, 4);
        mBadgeVerticalMargin = DensityUtils.dp2px(context, 0);

        mBadgeHorizontalMargin = DensityUtils.dp2px(context, 0);

        mBadgeGravity = defaultBadgeGravity;
        mIsShowBadge = false;

        mBadgeText = null;

        mBitmap = null;

        mIsDraging = false;

        mDragable = false;

        mBadgeBorderColor = Color.WHITE;

        mDragExtra = DensityUtils.dp2px(context, 4);
        mBadgeDragExtraRectF = new RectF();
    }



    private void afterInitDefaultAndCustomAttrs() {
        mBadgePaint.setTextSize(mBadgeTextSize);
    }

    public void setBadgeBgColorInt(int badgeBgColor) {
        mBadgeBgColor = badgeBgColor;
        mBadgeable.postInvalidate();
    }

    public void setBadgeTextColorInt(int badgeTextColor) {
        mBadgeTextColor = badgeTextColor;
        mBadgeable.postInvalidate();
    }

    public void setBadgeTextSizeSp(int badgetextSize) {
        if (badgetextSize >= 0) {
            mBadgeTextSize = DensityUtils.sp2px(mBadgeable.getContext(), badgetextSize);
            mBadgePaint.setTextSize(mBadgeTextSize);
            mBadgeable.postInvalidate();
        }
    }

    public void setBadgeVerticalMarginDp(int badgeVerticalMargin) {
        if (badgeVerticalMargin >= 0) {
            mBadgeVerticalMargin = DensityUtils.dp2px(mBadgeable.getContext(), badgeVerticalMargin);
            mBadgeable.postInvalidate();
        }
    }

    public void setBadgeHorizontalMarginDp(int badgeHorizontalMargin) {
            mBadgeHorizontalMargin = DensityUtils.dp2px(mBadgeable.getContext(), badgeHorizontalMargin);
            mBadgeable.postInvalidate();
    }

    public void setBadgePaddingDp(int badgePadding) {
        if (badgePadding >= 0) {
            mBadgePadding = DensityUtils.dp2px(mBadgeable.getContext(), badgePadding);
            mBadgeable.postInvalidate();
        }
    }

    public void setBadgeGravity(BadgeGravity badgeGravity) {
        if (badgeGravity != null) {
            mBadgeGravity = badgeGravity;
            mBadgeable.postInvalidate();
        }
    }

    public void setDragable(boolean dragable) {
        mDragable = dragable;
        mBadgeable.postInvalidate();
    }

    public void setIsResumeTravel(boolean isResumeTravel) {
        mIsResumeTravel = isResumeTravel;
        mBadgeable.postInvalidate();
    }

    public void setBadgeBorderWidthDp(int badgeBorderWidthDp) {
        if (badgeBorderWidthDp >= 0) {
            mBadgeBorderWidth = DensityUtils.dp2px(mBadgeable.getContext(), badgeBorderWidthDp);
            mBadgeable.postInvalidate();
        }
    }

    public void setBadgeBorderColorInt(int badgeBorderColor) {
        mBadgeBorderColor = badgeBorderColor;
        mBadgeable.postInvalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mBadgeDragExtraRectF.left = mBadgeRectF.left - mDragExtra;
                mBadgeDragExtraRectF.top = mBadgeRectF.top - mDragExtra;
                mBadgeDragExtraRectF.right = mBadgeRectF.right + mDragExtra;
                mBadgeDragExtraRectF.bottom = mBadgeRectF.bottom + mDragExtra;

                if ((mBadgeBorderWidth == 0 || mIsShowDrawable) && mDragable && mIsShowBadge && mBadgeDragExtraRectF.contains(event.getX(), event.getY())) {
                    mIsDraging = true;
                    mBadgeable.getParent().requestDisallowInterceptTouchEvent(true);

                    Rect badgeableRect = new Rect();
                    mBadgeable.getGlobalVisibleRect(badgeableRect);
                    mDropBadgeView.setStickCenter(badgeableRect.left + mBadgeRectF.left + mBadgeRectF.width() / 2, badgeableRect.top + mBadgeRectF.top + mBadgeRectF.height() / 2);

                    mDropBadgeView.onTouchEvent(event);
                    mBadgeable.postInvalidate();
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsDraging) {
                    mDropBadgeView.onTouchEvent(event);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIsDraging) {
                    mDropBadgeView.onTouchEvent(event);
                    mIsDraging = false;
                    return true;
                }
                break;
            default:
                break;
        }
        return mBadgeable.callSuperOnTouchEvent(event);
    }

    public void endDragWithDismiss() {
        hiddenBadge();
        if (mDelegage != null) {
            mDelegage.onDismiss(mBadgeable);
        }
    }

    public void endDragWithoutDismiss() {
        mBadgeable.postInvalidate();
    }

    public void drawBadge(Canvas canvas) {
        if (mIsShowBadge && !mIsDraging) {
            if (mIsShowDrawable) {
                drawDrawableBadge(canvas);
            } else {
                drawTextBadge(canvas);
            }
        }
    }

    /**
     * 绘制图像徽章
     *
     * @param canvas
     */
    private void drawDrawableBadge(Canvas canvas) {
        mBadgeRectF.left = mBadgeable.getWidth() - mBadgeHorizontalMargin - mBitmap.getWidth();
        mBadgeRectF.top = mBadgeVerticalMargin;
        switch (mBadgeGravity) {
            case RightTop:
                mBadgeRectF.top = mBadgeVerticalMargin;
                break;
            case RightCenter:
                mBadgeRectF.top = (mBadgeable.getHeight() - mBitmap.getHeight()) / 2;
                break;
            case RightBottom:
                mBadgeRectF.top = mBadgeable.getHeight() - mBitmap.getHeight() - mBadgeVerticalMargin;
                break;
            default:
                break;
        }
        canvas.drawBitmap(mBitmap, mBadgeRectF.left, mBadgeRectF.top, mBadgePaint);
        mBadgeRectF.right = mBadgeRectF.left + mBitmap.getWidth();
        mBadgeRectF.bottom = mBadgeRectF.top + mBitmap.getHeight();
    }

    /**
     * 绘制文字徽章
     *
     * @param canvas
     */
    private void drawTextBadge(Canvas canvas) {
        String badgeText = "";
        if (!TextUtils.isEmpty(mBadgeText)) {
            badgeText = mBadgeText;
        }
        // 获取文本宽所占宽高
        mBadgePaint.getTextBounds(badgeText, 0, badgeText.length(), mBadgeNumberRect);
        // 计算徽章背景的宽高
        int badgeHeight = mBadgeNumberRect.height() + mBadgePadding * 2;
        int badgeWidth;
        // 当mBadgeText的长度为1或0时，计算出来的高度会比宽度大，此时设置宽度等于高度
        if (badgeText.length() == 1 || badgeText.length() == 0) {
            badgeWidth = badgeHeight;
        } else {
            badgeWidth = mBadgeNumberRect.width() + mBadgePadding * 2;
        }

        // 计算徽章背景上下的值
        mBadgeRectF.top = mBadgeVerticalMargin;
        mBadgeRectF.bottom = mBadgeable.getHeight() - mBadgeVerticalMargin;
        switch (mBadgeGravity) {
            case RightTop:
                mBadgeRectF.bottom = mBadgeRectF.top + badgeHeight;
                break;
            case RightCenter:
                mBadgeRectF.top = (mBadgeable.getHeight() - badgeHeight) / 2;
                mBadgeRectF.bottom = mBadgeRectF.top + badgeHeight;
                break;
            case RightBottom:
                mBadgeRectF.top = mBadgeRectF.bottom - badgeHeight;
                break;
            default:
                break;
        }

        // 计算徽章背景左右的值
        mBadgeRectF.right = mBadgeable.getWidth() - mBadgeHorizontalMargin;
        mBadgeRectF.left = mBadgeRectF.right - badgeWidth;

        if (mBadgeBorderWidth > 0) {
            // 设置徽章边框景色
            mBadgePaint.setColor(mBadgeBorderColor);
            // 绘制徽章边框背景
            canvas.drawRoundRect(mBadgeRectF, badgeHeight / 2, badgeHeight / 2, mBadgePaint);

            // 设置徽章背景色
            mBadgePaint.setColor(mBadgeBgColor);
            // 绘制徽章背景
            canvas.drawRoundRect(new RectF(mBadgeRectF.left + mBadgeBorderWidth, mBadgeRectF.top + mBadgeBorderWidth, mBadgeRectF.right - mBadgeBorderWidth, mBadgeRectF.bottom - mBadgeBorderWidth), (badgeHeight - 2 * mBadgeBorderWidth) / 2, (badgeHeight - 2 * mBadgeBorderWidth) / 2, mBadgePaint);
        } else {
            // 设置徽章背景色
            mBadgePaint.setColor(mBadgeBgColor);
            // 绘制徽章背景
            canvas.drawRoundRect(mBadgeRectF, badgeHeight / 2, badgeHeight / 2, mBadgePaint);
        }


        if (!TextUtils.isEmpty(mBadgeText)) {
            // 设置徽章文本颜色
            mBadgePaint.setColor(mBadgeTextColor);
            // initDefaultAttrs方法中设置了mBadgeText居中，此处的x为徽章背景的中心点y
            float x = mBadgeRectF.left + badgeWidth / 2;
            // 注意：绘制文本时的y是指文本底部，而不是文本的中间
            float y = mBadgeRectF.bottom - mBadgePadding;
            // 绘制徽章文本
            canvas.drawText(badgeText, x, y, mBadgePaint);
        }
    }

    public void showCirclePointBadge() {
        showTextBadge(null);
    }

    public void showTextBadge(String badgeText) {
        mIsShowDrawable = false;
        mBadgeText = badgeText;
        mIsShowBadge = true;
        mBadgeable.postInvalidate();
    }

    public void hiddenBadge() {
        mIsShowBadge = false;
        mBadgeable.postInvalidate();
    }

    public boolean isShowBadge() {
        return mIsShowBadge;
    }

    public void showDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mIsShowDrawable = true;
        mIsShowBadge = true;
        mBadgeable.postInvalidate();
    }

    public boolean isShowDrawable() {
        return mIsShowDrawable;
    }

    public RectF getBadgeRectF() {
        return mBadgeRectF;
    }

    public int getBadgePadding() {
        return mBadgePadding;
    }

    public String getBadgeText() {
        return mBadgeText;
    }

    public int getBadgeBgColor() {
        return mBadgeBgColor;
    }

    public int getBadgeTextColor() {
        return mBadgeTextColor;
    }

    public int getBadgeTextSize() {
        return mBadgeTextSize;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setDragDismissDelegage(DragDismissDelegate delegage) {
        mDelegage = delegage;
    }

    public View getRootView() {
        return mBadgeable.getRootView();
    }

    public boolean isResumeTravel() {
        return mIsResumeTravel;
    }

    public enum BadgeGravity {
        RightTop,
        RightCenter,
        RightBottom
    }
}