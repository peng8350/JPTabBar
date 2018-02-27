# :blush:JPTabBar:blush:
[![Build Status](https://travis-ci.org/peng8350/JPTabBar.svg?branch=master)](https://travis-ci.org/peng8350/JPTabBar)
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/bintray/jcenter/JPTabBar)
<br>[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JPTabBar-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4685)
 [ ![Download](https://api.bintray.com/packages/peng83508440/maven/JPTabBar/images/download.svg) ](https://bintray.com/peng83508440/maven/JPTabBar/_latestVersion)
 [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/peng8350/JPTabBar/blob/master/LICENSE)
 
### 

# 效果图
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br> 
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/animation.gif)<br> 
             
# 主要功能以及特色:
   - [x] 多种Tab切换的动画效果

   - [x] 实现底部导航中间按钮凸出的效果

   - [x] 实现类似Wechat图标渐变，并且带动画

   - [x] 实现TabBar上的红色标记,并且可以拖动

   - [x] 提供监听Tab的点击事件,中间点击以及badge被拖拉消失的接口

   - [x] 引用注解方式,免去自己手动构造TabBarItem

# 依赖
[BGABadgeView-Android](https://github.com/bingoogolapple/BGABadgeView-Android)

# 用法:
  1.引入Gradle依赖
```
    repositories {
        jcenter()
    }

    dependencies{
        compile 'com.jpeng:JPTabBar:1.4.0'
    }

```
2.添加JPTabBar到你的主界面布局
```

    <com.jpeng.jptabbar.JPTabBar
        android:id="@+id/tabbar"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="#fff"
         jp:TabTextSize="12sp"
        />
     
```
3.在你的主界面使用注解声明数组变量,内部通过反射来生成TabItem,注意的是:NorIcons是必须的,每个数组长度要保持一致

```
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};


```
或者，你可以在oncreate方法里面初始化导航的item
```
        mTabbar.setTitles(R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4)
                .setNormalIcons(R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal)
                .setSelectedIcons(R.mipmap.tab1_selected, R.mipmap.tab2_selected, R.mipmap.tab3_selected, R.mipmap.tab4_selected)
                .generate();
```
经过上面,TabBar的布局基本上已经搭建好了。如果要实现Wechat那种渐变还有自动让ViewPager改变页面的功能的话,只需要在Activity oncreate方法里面,添加一行代码:(当然如果你不使用ViewPager的话，不调用这个方法)
```
    //传入一定要集成继承ViewPager
    mTabbar.setContainer(mPager);
```

# 凸出按钮:
1.如果想要达到凸出按钮的效果,首先需要在XML的tabbar控件追加以下结点属性,这个layout代表的就是你自定义凸出按钮布局
```
    jp:TabMiddleView="@layout/..."
```
2.在里可以通过getMiddleView方法获得布局对象,并且可以随意给布局里面控件设置监听
```
    View middleView = mJPTabBar.getMiddleView();
```
3.确保tabbar父控件使用RelativeLayout或者FrameLayout作为根结点,因为凸出按钮是依靠父layout添加进去的
```
  <?xml version="1.0" encoding="utf-8"?>
  <!--Use RelativeLayout or FrameLayout --!>
  <RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:jp="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <!--TabBar --!>
    
    
   </RelativeLayout>

```

# 方法和节点说明:
#### JPTabBar主要方法:
```

     /**
     * 显示BadgeView ,传入字符串
     * 当然还有一个重载方法,第二个参数为int,设置消息数量
     * 传入""字符串显示圆点
     * 默认为false,不可拖动
     */
    public void showBadge(int position,String text);
    
    /**
     * 与上面方法是一样的,唯一不同就是,传入true的时候,这个徽章可以拖动
     */
   public void showBadge(int position,String text,boolean draggable);
    
    /**
    *显示圆点徽章
    */ 
    public void showCircleBadge(int pos);

    /**
     * 隐藏BadgeView
     */
    public void hideBadge(int position);
    
     /**
       * 获取徽章是否显示
       */
    public boolean isBadgeShow(int index) 

    /**
     * 切换Tab页面
     */
    public void setSelectTab(int index);

    /**
     * 设置点击TabBar事件的观察者
     */
    public void setTabListener(OnTabSelectListener listener);

    /**
     * 设置badgeView消失的回调事件
     */
    public void setDismissListener(BadgeDismissListener listener);
    
    /**
     * 这个方法用来获得中间TabItem的View对象(即你在XML设置的"TabMiddleView")
     */
    public View getMiddleView();
    
     /**
       * 设置某个TabItem的标题
       */
    public void setTitle(int pos, String title)
    
    /**
      * 获取TabItem
      */
    public JPTabItem getTabAtPosition(int pos) 
```

### 结点说明:
| 结点名字        |     结点说明     | 参数类型 | 默认值  |
|-------------|:-------------|:----------:|:-----:|
| TabNormalColor      |字体和图标的未选中颜色   |color   |   0xffAEAEAE(灰色) |
| TabSelectColor |字体和图标的选中的颜色     |color  | 0xff59D9B9(青色) |
| TabTextSize |Tab底部文件大小      |dimension | 14sp |
| TabIconSize |Tab图标的大小       |dimension| 24dp |
| TabIconFilter |   设置图标是否随着字体颜色而改变|boolean | true |
| TabTypeface |  设置所有TabItem的字体类型| string | null |
| TabMargin |设置图标距离上面和文字距离下面的距离      |dimension | 8dp |
| TabSelectBg |设置TabBarItem选中的背景颜色     |color | 透明 |
| TabAnimate |Tab切换的动画类型(None,Scale,Jump....)      |enum | NULL |
| TabMiddleView |Tab中间自定义View     |layout | 无 |
| TabMiddleBottomDis | 中间图标底部距离 |dimension | 20dp |
| TabMiddleHMargin | 中间图标的左右间距 |dimension | 24dp |
| TabPressAnimateEnable | 允许按住TabItem的动画效果 |boolean | true |
| TabPageAnimateEnable | 允许页面滑动效果 |boolean | false |
| TabGradientEnable | 允许图标颜色随着页面滑动而改变 |boolean | false |
| BadgeColor |徽章的背景颜色      |color | #f00(红色) |
| BadgePadding |徽章的背景扩展距离      |dimension | 4dp |
| BadgeTextSize |徽章显示的字体大小      |dimension | 11dp |
| BadgeVerticalMargin | 徽章垂直间距     |dimension | 3dp |
| BadgeHorticalMargin | 徽章水平间距     |dimension | 20dp |

# 注意事项
1.假如你已经给TabBar setContainer,不要setOnPageChangeListener给ViewPager
```
  /**
    *如果你前面已经给TabBar设置了容器,然后调用这个方法的话,类似WeChat那种拖动渐变效果以及自动切换页面将会失效
    *假如你要监听页面改变事件,可以使用TabListener
   */
  mPager.setOnPageChangeListener(this);
  
  
```
2.如果你想让ViewPager禁止滑动,你可以使用我demo中的NoScrollViewPager

3.OnTabSelectListener里的onInterruptSelect的回调方法只中断点击TabItem的情况,不考虑页面滑动过去后选中

# 存在的问题:
1.关于徽章的功能无法拖动,拖动消失等问题,这个问题发生在一部分小米手机机型上,原作者是
通过悬浮窗口实现徽章的爆炸效果,这类手机默认没有打开悬浮窗的权限<br>
2.Flip(翻转)动画失效问题,由于华为部分7.0手机不支持setRotationY和setRotationX,
Flip动画正是调用了setRotationY

# 更新日志
### V1.1.2
   - 给动画添加了弹性
   - 修复当pager没有数据时无法点击问题

### V1.1.4
   - 修复这个BUG当页面数量大于或者小于TabItem数量的问题
   - 添加点击ITEM颜色渐变(有selectedicon)的效果
   - 增加另一套初始化TabBarItems的方法
   - 解决因为drawable共用内存,每次finish后,没有图标的问题。
   
### V1.1.5
  - 移除标题必须设置的限制,可以没有标题
  - 再次修复徽章位置问题
  - 添加了一些调用方法和修改了一些方法命名规范
   
### V1.1.6
  - 添加之前忘记的标题渐变效果
  - 删除BadgeDraggable结点,用ShowBadge方法设置是否可以拖动
  - 添加几个方法，减少TabBar的限制性
  
### V1.1.7
  - 修复高度问题,还有移除TabHeight结点(粗心导致)
  - 修复这个BUG,当用户每次运行APP图标渐变问题

### V1.1.9
  - 修正BUG,当setUseScrollAnimate(false),滑动TAB动画未有还原
  - 改变滚动动画和渐变默认关闭
  - 改变Badge的位置为水平居上方,以适应屏幕适配
  
### V1.2.0
  - 修正之前一直没有解决的中间图标点击外面没有响应问题
  - 添加一些动态的方法
  - 添加两个结点设置中间图标的属性
  
### V1.2.3
  - 修改一些存在问题和BUG
  - 优化了旋转动画弹性
  - 中间TabItem 使用自定义View代替只可以一个图标
  
### V1.2.5
  - 添加另外一种方法去获取状态栏的高度
  - 修正中间View布局参数的BUG

### V1.3.0
  - 添加另外一种缩放动画
  - 添加一种新的特性:tabitem可以展示动画,当用户按下或者在View外面范围松开
  - 调整了一些动画的效果
  
### V1.3.2
  - 修正不使用动画时按下奔溃问题
  - 修改默认动画为没有动画
  
### V1.3.5
  - 新增动态修改结点的方法  
  - 修正触摸事件逻辑判断问题(换到别的Tab不能滑动徽章)
  - 修复在attrs里TabAnimate值的错误
  - 暴露TabItem接口,可以直接操作TabItem

### V1.4.0
  - 添加设置TabItem的字体类型的功能
  - 添加onInterruptSelect回调方法来决定是否中断点击事件
  - 移除部分作用不大的方法和属性,比如:消息限制格式
  - 增加可以控制按住动画效果开关
  - 增加TabBar部分方法,比如:修改某个tab标题,图标等
  - 重命名setUseScrollAnimate setUseFilter这两个方法名,并且可通过结点设置
  
# 关于我
一名在校大学生,目前还在专研学习各种技术中...<br>
邮箱:peng8350@gmail.com

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
