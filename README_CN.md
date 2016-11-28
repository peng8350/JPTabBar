# :blush:JPTabBar:blush:
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/bintray/jcenter/JPTabBar)
<br>[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JPTabBar-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4685)
### 

# 效果图
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br>
   
             
# 主要功能以及特色:
   - [x] 多种Tab切换的动画效果

   - [x] 实现底部导航中间按钮凸出的效果

   - [x] 实现类似Wechat图标渐变，并且带动画

   - [x] 实现TabBar上的红色标记,并且可以拖动

   - [x] 提供监听Tab的点击事件,中间点击以及badge被拖拉消失的接口

   - [x] 引用注解方式,免去自己手动构造TabBarItem

# 用法:
  1.引入Gradle依赖
```Java
    repositories {
        jcenter()
    }

    dependencies{
        compile 'com.jpeng:JPTabBar:1.1.7'
    }

```
2.添加JPTabBar到你的主界面布局
```JAVA


    <com.jpeng.jptabbar.JPTabBar
        android:id="@+id/tabbar"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="#fff"
         jp:TabTextSize="12sp"
        />
```
3.在你的主界面使用注解声明数组变量,内部通过反射来生成TabItem,注意的是:NorIcons是必须的,每个数组长度要保持一致

```JAVA
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};


```
或者，你可以在oncreate方法里面初始化导航的item
```JAVA
        mTabbar.setTitles(R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4)
                .setNormalIcons(R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal)
                .setSelectedIcons(R.mipmap.tab1_selected, R.mipmap.tab2_selected, R.mipmap.tab3_selected, R.mipmap.tab4_selected)
                .generate();
```
4.经过上面,TabBar的布局基本上已经搭建好了。如果要实现Wechat那种渐变还有自动让ViewPager改变页面的功能的话,只需要在Activity oncreate方法里面,添加一行代码:(当然如果你不使用ViewPager的话，不调用这个方法)
```JAVA
    //传入一定要集成继承ViewPager
    mTabbar.setContainer(mPager);
```



# 方法和节点说明:
#### JPTabBar主要方法:
```JAVA
    /**
     * 设置自定义Tab切换动画
     */
    public void setCustomAnimate(Animatable customAnimate);


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
     * 设置图标和标题的滑动渐变以及点击渐变是否使用
     */
    public JPTabBar setUseFilter(boolean filter);
    
    /**
     * 设置是否需要页面滚动动画
     */
    public JPTabBar setUseScrollAnimate(boolean scrollAnimate);
    
    /**
    *显示圆点徽章
    */ 
    public void showCircleBadge(int pos);
        
    /**
     * 设置徽章消息数量限制数
     * 如果你使用这个方法 ShowBadge(int position,int count)
     * 如果第二个参数 > limit , Badge将会显示 "limit+"
     * 可以看下参考图
     */
    public void setCountLimit(int limit);

    /**
     * 隐藏BadgeView
     */
    public void hideBadge(int position);

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
```

### 结点说明:
| 结点名字        |     结点说明     | 参数类型 | 默认值  |
|-------------|:-------------|:----------:|:-----:|
| TabNormalColor      |字体和图标的未选中颜色   |color   |   0xffAEAEAE(灰色) |
| TabSelectColor |字体和图标的选中的颜色     |color  | 0xff59D9B9(青色) |
| TabTextSize |Tab底部文件大小      |dimension | 14sp |
| TabIconSize |Tab图标的大小       |dimension| 24dp |
| TabIconFilter |   设置图标是否随着字体颜色而改变|boolean | true |
| TabMargin |设置图标距离上面和文字距离下面的距离      |dimension | 8dp |
| TabSelectBg |设置TabBarItem选中的背景颜色     |color | 透明 |
| TabAnimate |Tab切换的动画类型      |enum | Scale |
| TabMiddleIcon |Tab中间的图标      |drawable | 无 |
| BadgeColor |徽章的背景颜色      |color | #f00(红色) |
| BadgePadding |徽章的背景扩展距离      |dimension | 4dp |
| BadgeTextSize |徽章显示的字体大小      |dimension | 11dp |
| BadgeVerticalMargin | 徽章垂直间距     |dimension | 3dp |
| BadgeHorticalMargin | 徽章水平间距     |dimension | 20dp |

# 注意事项
1.假如你已经给TabBar setContainer,不要setOnPageChangeListener给ViewPager
```JAVA
  /**
    *如果你前面已经给TabBar设置了容器,然后调用这个方法的话,类似WeChat那种拖动渐变效果以及自动切换页面将会失效
    *假如你要监听页面改变事件,可以使用TabListener
   */
  mPager.setOnPageChangeListener(this);
  
  
```

2.假如你要实现中间凸出的按钮,必须要在主界面最外围的父结点设置  android:clipChildren="false",否则会遮盖
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
3.如果你想让ViewPager禁止滑动,你可以使用我demo中的NoScrollViewPager

# 更新日志
### V1.1.0
   - 解决XML布局中渲染报错问题
   - 修复点击Tab回调两次的BUG
   - 新增切换对动画的回调接口方法,提高TabBar动画灵活性

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
  
# 希望
</p>如果你觉得这个项目快速和有用,有帮助,别忘记点一下右上角的星星,因为我要在下下年挑战BAT校招。
<br><br>
</p>世界上再强的程序员写代码过程中难免会出现BUG,如果使用者发现BUG,可联系我或者直接issue,谢谢！

# 关于我
一名在校大学生,目前还在专研学习各种技术中...<br>
邮箱:83508440@qq.com

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
