# :blush:JPTabBar:blush:
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/bintray/jcenter/JPTabBar)
<br>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JPTabBar-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4685)

阅读中文文档 [请点击这里](https://github.com/peng8350/JPTabBar/blob/master/README_CN.md)

# ScreenShots:
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br>
    
             
# Main functions and features:
   - [x] More Animation effects of multiple Tab switching

   - [x] Implements the effect of the middle button of the bottom navigation

   - [x] Implements like Wechat icon filter and provide animation change.

   - [x] Implements the red mark on the TabBar, and can drag

   - [x] Provide listening to the click event, middle click and badge is dragged away the interface

   - [x] Reference annotation method, construction TabBarItem

# Usage:
  1.Introducing Gradle dependency
```Java
    repositories {
        jcenter()
    }

    dependencies{
        compile 'com.jpeng:JPTabBar:1.1.7'
    }

```
2.Add JPTabBar to your main interface layout
```JAVA


    <com.jpeng.jptabbar.JPTabBar
        android:id="@+id/tabbar"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="#fff"
         jp:TabTextSize="12sp"
        />
```
3.In your main interface using an array of variables to declare an array of variables, the internal reflection to generate TabItem, attention is:NorIcons are required, the length of each array should be consistent

```JAVA
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};


```
Or, you can init in the oncreate
```JAVA
        mTabbar.setTitles(R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4)
                .setNormalIcons(R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal)
                .setSelectedIcons(R.mipmap.tab1_selected, R.mipmap.tab2_selected, R.mipmap.tab3_selected, R.mipmap.tab4_selected)
                .generate();
```
4.After above, the layout of the TabBar basically has been built. If you want to achieve Wechat kind of gradual change as there are automatically ViewPager to change the function of the page, only in the oncreate Activity method, adding a line of code:(Of curse,If you don't use ViewPager,You needn't use this method)
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
     * Show the BadgeView With Text
     * default is false ,cannot drag
     */
    public void showBadge(int position,String text);
    
    /**
     * It is the same with the up method,But the different is,
     * The Badge Can draggable when you use true.
     */
    public void showBadge(int position,String text,boolean draggable);
        
    /**
     * set the icon and title filter when scroll page and click the tab
     */
    public JPTabBar setUseFilter(boolean filter);
        
    /**
     * Set the boolean If Need the PageAnimate
     */
    public JPTabBar setUseScrollAnimate(boolean scrollAnimate);
    
    /**
      *Show the Circle point
      */ 
    public void showCircleBadge(int pos);
    
    /**
      * Set the Badge Message Count Limit
      * If you use ShowBadge(int position,int count)
      * If the Second parameters > limit , it will show "limit+"
      * you can see the screenshots
      */
    public void setCountLimit(int limit);

    /**
     * Hide the OVAL Badge
     */
    public void hideBadge(int position);

    /**
     * Switch Tab page
     */
    public void setSelectTab(int index);

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
| TabNormalColor      |Font and icon of the normal color   |color   |   0xffAEAEAE(Gray) |
| TabSelectColor |Font and icon of the selected color     |color  | 0xff59D9B9(Cyan) |
| TabTextSize |the textsize of the bottom text     |dimension | 14sp |
| TabIconSize |the icon size of the tab       |dimension| 24dp |
| TabIconFilter |   Set the icon  change by the font color|boolean | true |
| TabMargin |Set the icon distance above and below the distance from the text      |dimension | 8dp |
| TabSelectBg |Set the TabItem Selected bg    |color | transparent |
| TabAnimate |The animate type of the Tab Switch      |enum | Scale |
| TabMiddleIcon |The middle Icon of the tab      |drawable | 无 |
| BadgeColor |The background of the badgeView      |color | #f00(RED) |
| BadgePadding |The background expansion distance of the badge      |dimension | 4dp |
| BadgeTextSize |The textSize of the Badge      |dimension | 10dp |
| BadgeVerticalMargin | The badge vertical margin     |dimension | 3dp |
| BadgeHorticalMargin | The badge hortical margin     |dimension | 20dp |

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
3.If you want to Disable the scroll of ViewPager,you can use NoScrollViewPager in my demo

# Update Log
### V1.1.0
   - Reverse the Rending problem in the XML
   - Reverse the BUG of CLick Tab Event CallBack twice.
   - Add the OnPageAnimate Method in interface,Enhance the flexibility of animation
   
### V1.1.2
   - Add the Bouncing of the Animation
   - Reverse don't click when have no Pager in the Adapter
   
### V1.1.4
   - Reverse the Bug of When the Pagers count of ViewPager less than or more than the count of tab
   - Add the Color FIlter to the Tab Icon When user Switch Tab
   - Add the Another init item method
   - solve the drawable in the same memory problem,Every time finish the activity,have no Icon show.
  
### V1.1.5
  - Remove the limit of the titles,You can set without titles
  - Fix the position of the badge again
  - Add some methods and Update some method's name
  
### V1.1.6
  - Add the title Filter
  - Remove the BadgeDraggable Attribute,and replace with the ShowBadge method
  - Add several methods to reduce the TabBar limit 
  
### V1.1.7
  - Fix the problem of height and remove the TabHeight attribute
  - Fix BUG, when the app run gradient problem
  
# Hope
</p>If you think this project is fast and useful, help, don't forget to click on the upper right corner of the star, because I want to challenge the BAT school recruit in the next year。
<br><br>
</p>The world's strong programmer code process will inevitably appear BUG, if the user found BUG, you can contact me or directly issue, thank you！

# About Me
A college student, is still in the study of various techniques...<br>
E-mail:83508440@qq.com

# License
```
Copyright 2016 JPeng

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

