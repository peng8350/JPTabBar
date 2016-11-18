# :blush:JPTabBar:blush:
[![Download](https://img.shields.io/crates/d/rustc-serialize.svg)](https://bintray.com/peng83508440/maven/JPTabBar)

### 阅读英文文档 [请点击这里](https://github.com/peng8350/JPTabBar/blob/master/README_EN.md)

# 效果图
   ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/main.gif)<br>
    ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/1.gif)<br>
     ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/2.gif) <br>
       ![](https://github.com/peng8350/JPTabBar/blob/master/screenshots/3.gif)

# 主要功能以及特色:
   - [x] 多种Tab切换的动画效果

   - [x] 实现底部导航中间按钮凸出的效果

   - [x] 实现WeChat那种滑动导航的底部渐变效果,随着滑动的距离变化而变化

   - [x] 实现TabBar上的红色标记,并且可以拖动

   - [x] 强大的BadgeView功能,智能判断数字隐藏与越界显示,两种显示模式。

   - [x] 提供监听Tab的点击事件,中间点击以及badge被拖拉消失的接口

   - [x] 引用注解方式,免去自己手动构造TabBarItem

# 用法:
  1.引入Gradle依赖
```Java
    repositories {
        jcenter()
    }

    dependencies{
        compile 'com.jpeng:JPTabBar:1.0.5'
    }

```
2.添加JPTabBar到你的主界面布局
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
3.在你的主界面使用注解声明数组变量,内部通过反射来生成TabItem,注意的是:NorIcons和Titles是必须的,每个数组长度要保持一致

```JAVA
    @Titles
    private static final String[] mTitles = {"页面一","页面二","页面三","页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.tab1_normal, R.mipmap.tab2_normal, R.mipmap.tab3_normal, R.mipmap.tab4_normal};


```
4.经过上面,TabBar的布局基本上已经搭建好了。如果要实现Wechat那种渐变还有自动让ViewPager改变页面的功能的话,只需要在Activity oncreate方法里面,添加一行代码:
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
     */
    public void ShowBadge(int position,String text);

    /**
     * 隐藏BadgeView
     */
    public void HideBadge(int position);

    /**
     * 切换Tab页面,是否带动画
     */
    public void setSelectTab(int index, boolean animated);

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
| TabHeight      |TabBar的高度,将会覆盖layout_height的设置 |dimension| 56dp |
| TabNormalColor      |字体和图标的未选中颜色   |color   |   0xffAEAEAE(灰色) |
| TabSelectColor |字体和图标的选中的颜色     |color  | 0xff59D9B9(青色) |
| TabTextSize |Tab底部文件大小      |dimension | 14sp |
| TabIconSize |Tab图标的大小       |dimension| 24dp |
| TabIconFilter |   设置图标是否随着字体颜色而改变|boolean | true |
| TabMargin |设置图标距离上面和文字距离下面的距离      |dimension | 8dp |
| TabSelectBg |设置TabBarItem选中的背景颜色     |color | 透明 |
| TabDuration |Tab切换的动画时间     |Integer  | 500 |
| TabAnimate |Tab切换的动画类型      |enum | Flip |
| TabMiddleIcon |Tab中间的图标      |drawable | 无 |
| BadgeColor |徽章的背景颜色      |color | #f00(红色) |
| BadgeDraggable |徽章是否可以拖动     |boolean  | false |
| BadgePadding |徽章的背景扩展距离      |dimension | 4dp |
| BadgeTextSize |徽章显示的字体大小      |dimension | 11dp |
| BadgeMargin | 徽章距离右边边缘的间隔      |dimension | 9dp |
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
# 更新日志
### V1.0.0
   - 发布,添加README,上传GIF截图
   
### V1.0.3
   - 添加额外一些方法
   - 修正代码风格

### V1.0.4
   - 修复BadgeView显示位置随着字体位置偏差问题
   - 修复背景被TabItem背景覆盖问题
   - 修改高度默认56dp,徽章Margin为9dp

### V1.0.5
   - 解决徽章BadgeMargin不能往左上角移动
   - Titles注解可支持int[]数组
   - 干掉徽章BadgeModes注解,提高徽章使用的灵活性,修改了TabBar调用徽章的方法
   - 添加TabSelectBg结点,用来设置选中的背景
   
# 希望
</p>如果你觉得这个项目快速和有用,有帮助,别忘记点一下右上角的星星,因为我要在下下年挑战BAT校招。
<br><br>
</p>世界上再强的程序员写代码过程中难免会出现BUG,如果使用者发现BUG,可联系我或者直接issue,谢谢！

# 关于我
一名在校大学生,目前还在专研学习各种技术中...<br>
QQ:83508440<br>
邮箱:83508440@qq.com

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
