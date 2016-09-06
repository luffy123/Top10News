package com.top10News.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tablayout.SlidingTabLayout;
import com.tablayout.listener.OnTabSelectListener;
import com.tablayout.widget.MsgView;
import com.top10News.adapter.DemoAdapter;
import com.top10News.R;

import java.util.ArrayList;

/**
 *
 */
public class TabFragment extends Fragment implements OnTabSelectListener {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;

	private Context mContext = getActivity();
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private final String[] mTitles = {
			"热门", "iOS", "Android", "前端"
			, "后端", "设计", "工具资源"
	};

	/**
	 * Create a new instance of the fragment
	 */
	public static TabFragment newInstance(int index) {
		TabFragment fragment = new TabFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (getArguments().getInt("index", 0) == 0) {
			View view = inflater.inflate(R.layout.fragment_demo_tab, container, false);
			initDemoTab(view);
			return view;
		} else if (getArguments().getInt("index", 0) == 1){
			View view = inflater.inflate(R.layout.fragment_demo_list, container, false);
			initDemoList(view);
			return view;
		} else {
			View view = inflater.inflate(R.layout.fragment_demo_list, container, false);
			initDemoList(view);
			return view;
		}
	}

	/**
	 * Init the fragment
	 */
	private void initDemoList(View view) {

		fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
		recyclerView = (RecyclerView) view.findViewById(R.id.fragment_demo_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		ArrayList<String> itemsData = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
		}

		DemoAdapter adapter = new DemoAdapter(itemsData);
		recyclerView.setAdapter(adapter);
	}

	/**
	 * Refresh
	 */
	public void refresh() {
		if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
			recyclerView.smoothScrollToPosition(0);
		}
	}

	private void initDemoTab(View parentView) {
		for (String title : mTitles) {
			mFragments.add(GetMoreListViewFragment.getInstance());
		}

		ViewPager vp = (ViewPager)parentView.findViewById(R.id.vp);
		vp.setAdapter(new MyPagerAdapter(getChildFragmentManager() , mFragments));
		/**自定义部分属性*/
		SlidingTabLayout tabLayout_2 = (SlidingTabLayout)parentView.findViewById(R.id.tl_2);
		tabLayout_2.setViewPager(vp);
		vp.setCurrentItem(4);
		tabLayout_2.showMsg(3, 5);
		tabLayout_2.setMsgMargin(3, 0, 10);
		MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
		if (rtv_2_3 != null) {
			rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
		}

		tabLayout_2.showMsg(5, 5);
		tabLayout_2.setMsgMargin(5, 0, 10);
	}

	@Override
	public void onTabSelect(int position) {
		Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTabReselect(int position) {
		Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragmentsList;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyPagerAdapter(FragmentManager fm , ArrayList<Fragment> fragmentsList) {
			super(fm);
			this.fragmentsList = fragmentsList;
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}

}
