# :blush:JPTabBar:blush:
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/peng83508440/maven/JPTabBar)
# ScreenShots:
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br>
    ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/1.gif)<br>
     ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/2.gif) <br>
       ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/3.gif)

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
        compile 'com.jpeng:JPTabBar:1.0.3'
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

    @BadgeModes
    private static final BadgeMode[] mBadgeModes = {BadgeMode.OVAL,BadgeMode.NUMBER,BadgeMode.OVAL,BadgeMode.NUMBER};

```
4.After above, the layout of the TabBar basically has been built. If you want to achieve Wechat kind of gradual change as there are automatically ViewPager to change the function of the page, only in the oncreate Activity method, adding a line of code:
```JAVA
    //The parameters must be extends ViewPager
    mTabbar.setContainer(mPager);
```
# Method and node description:
#### The Main Method Of JPTabBar:
```JAVA
    /**
     * Set custom Tab toggle animation
     */
    public void setCustomAnimate(Animatable customAnimate);

    /**
     * Set The Number of badges
     * Apply in digital mode
     * @param position The position of Tab
     * @param count    Number of Message
     */
    public void setBadgeCount(int position, int count);

     /**
     * Show the OVAL Badge, used in the OVAL Mode
     */
    public void ShowOvalBadge(int position);

    /**
     * Hide the OVAL Badge, used in the OVAL Mode
     */
    public void HideOvalBadge(int position);

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
| TabHeight      |TabBar height, will cover the settings of layout_height |dimension| 48dp |
| TabNormalColor      |Font and icon of the normal color   |color   |   0xffAEAEAE(Gray) |
| TabSelectColor |Font and icon of the selected color     |color  | 0xff59D9B9(Cyan) |
| TabTextSize |the textsize of the bottom text     |dimension | 14sp |
| TabIconSize |the icon size of the tab       |dimension| 24dp |
| TabIconFilter |   Set the icon  change by the font color|boolean | true |
| TabMargin |Set the icon distance above and below the distance from the text      |dimension | 8dp |
| TabDuration |The animate time of the Tab Switch     |Integer  | 500 |
| TabAnimate |The animate type of the Tab Switch      |enum | Flip |
| TabMiddleIcon |The middle Icon of the tab      |drawable | 无 |
| BadgeColor |The background of the badgeView      |color | #f00(RED) |
| BadgeDraggable |Can drag on the badge touched by user     |boolean  | false |
| BadgePadding |The background expansion distance of the badge      |dimension | 8dp |
| BadgeTextSize |The textSize of the Badge      |dimension | 11dp |
| BadgeMargin | The badge margin in the TabBar      |dimension | 3dp |
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

