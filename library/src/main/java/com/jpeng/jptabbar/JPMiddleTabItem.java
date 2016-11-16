package com.jpeng.jptabbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by jpeng on 16-11-14.
 */
public class JPMiddleTabItem extends ImageView {


    /**
     * 图标drawable
     */
    private Drawable mIcon;


    /**
     * @param context 上下文
     * @param radius  这个为TabBar的高度,在这里为背景的半径
     */
    public JPMiddleTabItem(Context context, int radius) {
        super(context);
        initProtery(radius);
    }



    private void initProtery(int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height,height);
        params.setMargins(0,0,0, (int) (height*0.33));
        params.gravity=Gravity.BOTTOM;
        setLayoutParams(params);
        setScaleType(ScaleType.CENTER_INSIDE);

    }




    public void setIcon(Drawable mIcon) {
        this.mIcon= mIcon;

        setImageDrawable(mIcon);
    }


}
