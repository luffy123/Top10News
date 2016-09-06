package com.top10News.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.tablayout.CommonTabLayout;
import com.tablayout.listener.CustomTabEntity;
import com.tablayout.listener.OnTabSelectListener;
import com.tablayout.utils.UnreadMsgUtils;
import com.tablayout.widget.MsgView;
import com.top10News.fragment.TabFragment;
import com.top10News.R;
import com.top10News.entity.TabEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout mTabLayout_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mFragments.add(TabFragment.newInstance(0));
            mFragments.add(TabFragment.newInstance(1));
            mFragments.add(TabFragment.newInstance(1));
            mFragments.add(TabFragment.newInstance(2));

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        /** with Fragments */
        mTabLayout_1 = (CommonTabLayout)findViewById(R.id.tl_1);
        mTabLayout_1.setTabData(mTabEntities, this, R.id.fragment_change, mFragments);
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //显示未读红点
        mTabLayout_1.showDot(1);

        //两位数
        mTabLayout_1.showMsg(0, 55);
        mTabLayout_1.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout_1.showMsg(1, 100);
        mTabLayout_1.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout_1.showDot(2);
        MsgView rtv_2_2 = mTabLayout_1.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout_1.showMsg(3, 5);
        mTabLayout_1.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout_1.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
