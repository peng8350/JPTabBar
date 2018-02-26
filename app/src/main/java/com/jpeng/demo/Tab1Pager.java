package com.jpeng.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.jpeng.jptabbar.JPTabBar;

/**
 * Created by jpeng on 16-11-14.
 */
public class Tab1Pager extends Fragment implements View.OnClickListener, TextWatcher {

    private EditText mNumberEt;
    private ImageButton mMinusIb, mPlusIb;
    private Button mShowTextBtn,mHideBtn,mShowCircleBtn;
    private JPTabBar mTabBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab1, null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mTabBar = ((MainActivity)getActivity()).getTabbar();
        mShowTextBtn = (Button) layout.findViewById(R.id.button1);
        mShowCircleBtn = (Button) layout.findViewById(R.id.button2);
        mHideBtn = (Button) layout.findViewById(R.id.button3);
        mNumberEt = (EditText) layout.findViewById(R.id.et_count);
        mMinusIb = (ImageButton) layout.findViewById(R.id.imageButton1);
        mPlusIb = (ImageButton) layout.findViewById(R.id.imageButton2);
        mShowTextBtn.setOnClickListener(this);
        mShowCircleBtn.setOnClickListener(this);
        mHideBtn.setOnClickListener(this);
        mNumberEt.addTextChangedListener(this);
        mPlusIb.setOnClickListener(this);
        mMinusIb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int count = Integer.parseInt(mNumberEt.getText().toString());
        if (v == mMinusIb) {
            count--;
            mNumberEt.setText(count + "");
        } else if(v==mPlusIb) {
            count++;
            mNumberEt.setText(count + "");
        }
        else if(v==mShowTextBtn){
            mTabBar.showBadge(0,"文字",true);
        }
        else if(v==mShowCircleBtn){
            mTabBar.showCircleBadge(0,true);
        }
        else{
            mTabBar.hideBadge(0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s!=null&&s.toString().equals("0")){
            mTabBar.showBadge(0, ""+0,true);
            mTabBar.hideBadge(0);
            return;
        }
        if (s.toString().equals("")) {
            mTabBar.showBadge(0, ""+0,true);
            return;
        }
        int count = Integer.parseInt(s.toString());
        if(mTabBar!=null)
            mTabBar.showBadge(0, count+"",true);
    }

    public void clearCount() {
        //当徽章拖拽爆炸后,一旦View被销毁,不判断就会空指针异常
        if(mNumberEt!=null)
            mNumberEt.setText("0");
    }
}
