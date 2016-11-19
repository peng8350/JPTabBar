# :blush:JPTabBar:blush:
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/peng83508440/maven/JPTabBar)
<br>

阅读中文文档 [请点击这里](https://github.com/peng8350/JPTabBar/blob/master/README_CN.md)

# ScreenShots:
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br>
    ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/1.gif)
     ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/2.gif) <br>
       ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/3.gif)
         ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/4.gif)<br>
           ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/5.gif)
             ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/6.gif)<br>
             
# Main functions and features:
   - [x] More Animation effects of multiple Tab switching

   - [x] Implements the effect of the middle button of the bottom navigation

   - [x] Implements the kind of WeChat sliding navigation of the bottom gradient effect, with the change of the sliding distance

   - [x] Implements the red mark on the TabBar, and can drag

   - [x] The powerful BadgeView function, intelligent judgment digital hiding and cross-border display, two display modes。

   - [x] Provide listening to the click event, middle click and badge is dragged away the interface

   - [x] Reference annotation method, construction TabBarItem

# Usage:
  1.Introducing Gradle dependency
```Java
    repositories {
        jcenter()
    }

    dependencies{
        compile 'com.jpeng:JPTabBar:1.1.0'
    }

```
2.Add JPTabBar to your main interface layout
```JAVA


    <com.jpeng.jptabbar.JPTabBar
        android:id="@+id/tabbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#fff"
        jp:TabHeight="56dp"
        jp:BadgeDraggable="true"
        jp:TabAnimate="Jump"
        jp:BadgePadding="4dp"
        jp:BadgeMargin="5dp"
        jp:BadgeTextSize="10dp"
        />
```
3.In your main interface using an array of variables to declare an array of variables, the internal reflection to generate TabItem, attention is: Titles and NorIcons are required, the length of each array should be consistent

```JAVA
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};


```
4.After above, the layout of the TabBar basically has been built. If you want to achieve Wechat kind of gradual change as there are automatically ViewPager to change the function of the page, only in the oncreate Activity method, adding a line of code:
```JAVA
    //The parameters must be extends ViewPager
    mTabbar.setContainer(mPager);
```
5.The project has provided many animation,If you want to Custom your Animation,You can setCustomAnimate,Duclipte of examples:
```
            mTabbar.setCustomAnimate(new Animatable() {
                /**
                 * When you Tab Pager,The method will be called
                 * @param target IconView in the iconview
                 * @param Duration your animation time
                 */
                @Override
                public void playAnimate(View target, int Duration) {
                    ViewHelper.setPivotX(target,target.getLayoutParams().width/2);
                    ViewHelper.setPivotY(target,target.getLayoutParams().height/2);
    
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(
                            ObjectAnimator.ofFloat(target,"scaleX",0.2f,1f).setDuration(Duration),
                            ObjectAnimator.ofFloat(target,"scaleY",0.2f,1f).setDuration(Duration),
                            ObjectAnimator.ofFloat(target,"alpha",0.3f,1f).setDuration(Duration)
                    );
    
                    set.start();
                }
    
                /**
                 * The explain of the Method
                 * When you touch in the ViewPager by User,The method will be called back
                 * @param target The same in top
                 * @param offset Range value 0f-1f
                 */
                @Override
                public void onPageAnimate(View target, float offset) {
                    ViewHelper.setScaleX(target,1+offset*0.2f);
                    ViewHelper.setScaleY(target,1+offset*0.2f);
                }
    
                /**
                 * return true can make onPageAnimate method called
                 * @return
                 */
                @Override
                public boolean isNeedPageAnimate() {
                    return true;
                }
            });
```

# Method and node description:
#### The Main Method Of JPTabBar:
```JAVA
    /**
     * Set custom Tab toggle animation
     */
    public void setCustomAnimate(Animatable customAnimate);


     /**
     * Show the BadgeView With Text
     */
    public void ShowBadge(int position,String text);

    /**
     * Hide the OVAL Badge
     */
    public void HideBadge(int position);

    /**
     * Switch Tab page, whether with animation
     */
    public void setSelectTab(int index, boolean animated);

    /**
     * Set the Observer of the Click Tab Event
     */
    public void setTabListener(OnTabSelectListener listener);

    /**
     * set the CallBack of the Badge Dragging Dismiss
     */
    public void setDismissListener(BadgeDismissListener listener);
```
### Attribute Explain:
| Attribute Name        |     Attribute Explain     | Parameter Type | Default Value  |
|-------------|:-------------|:----------:|:-----:|
| TabHeight      |TabBar height, will cover the settings of layout_height |dimension| 56dp |
| TabNormalColor      |Font and icon of the normal color   |color   |   0xffAEAEAE(Gray) |
| TabSelectColor |Font and icon of the selected color     |color  | 0xff59D9B9(Cyan) |
| TabTextSize |the textsize of the bottom text     |dimension | 14sp |
| TabIconSize |the icon size of the tab       |dimension| 24dp |
| TabIconFilter |   Set the icon  change by the font color|boolean | true |
| TabMargin |Set the icon distance above and below the distance from the text      |dimension | 8dp |
| TabSelectBg |Set the TabItem Selected bg    |color | transparent |
| TabDuration |The animate time of the Tab Switch     |Integer  | 500 |
| TabAnimate |The animate type of the Tab Switch      |enum | Scale |
| TabMiddleIcon |The middle Icon of the tab      |drawable | 无 |
| BadgeColor |The background of the badgeView      |color | #f00(RED) |
| BadgeDraggable |Can drag on the badge touched by user     |boolean  | false |
| BadgePadding |The background expansion distance of the badge      |dimension | 4dp |
| BadgeTextSize |The textSize of the Badge      |dimension | 11dp |
| BadgeMargin | The badge right margin in the TabBar      |dimension | 9dp |
# Matters needing attention
1.If you have given setContainer TabBar, do not setOnPageChangeListener to ViewPager
```JAVA
  /**
    *If you already have the TabBar set up the container, 
    *and then call this method,
    *the kind of WeChat that drag the gradient effect and automatically switch the page will be invalid
    *If you want to listen to the page to change the event, you can use the TabListener
   */
  mPager.setOnPageChangeListener(this);
  
  
```

2.If you want to achieve the middle of the button, you must set the android:clipChildren= "false" to the parent node at the top of the main interface, otherwise it will be covered.
```JAVA
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:jp="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clipChildren="false"
    android:gravity="bottom"
    android:orientation="vertical"
    >

```
# Update Log
### V1.0.0
   - Publish,Add README,Upload GIF
   
### V1.0.3
   - Add some extra Methods
   - Reverse the Code Style

### V1.0.4
   - Reverse the BadgePosition show error
   - Reverse the background covered by the TabItem Bg
   - Reverse the Default value error

### V1.0.5
   - Reverse the BadgeView cannot move to LeftTop
   - Titles annotaion support int
   - Remove BadgeModes annotation,Enhance the flexibility of the use of badges,Update the TabBar method
   - Add the TabSelectBg attribute,used to set the selected item bg
  
### V1.1.0
   - Reverse the Rending problem in the XML
   - Reverse the BUG of CLick Tab Event CallBack twice.
   - Add the OnPageAnimate Method in interface,Enhance the flexibility of animation
   
# Hope
</p>If you think this project is fast and useful, help, don't forget to click on the upper right corner of the star, because I want to challenge the BAT school recruit in the next year。
<br><br>
</p>The world's strong programmer code process will inevitably appear BUG, if the user found BUG, you can contact me or directly issue, thank you！

# About Me
A college student, is still in the study of various techniques...<br>
QQ:83508440<br>
E-mail:83508440@qq.com

# License
```
Copyright 2016 [JPeng]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

