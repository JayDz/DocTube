package com.jeffreydiaz.doctube.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jeffreydiaz.doctube.R;
import com.jeffreydiaz.doctube.ui.HotListingsFragment;
import com.jeffreydiaz.doctube.ui.NewListingsFragment;
import com.jeffreydiaz.doctube.ui.TopAllTimeListingsFragment;
import com.jeffreydiaz.doctube.ui.TopListingsFragment;
import com.jeffreydiaz.doctube.ui.TopMonthListingsFragment;
import com.jeffreydiaz.doctube.ui.TopWeekListingsFragment;
import com.jeffreydiaz.doctube.ui.TopYearListingsFragment;

public class TabsAdapter extends FragmentPagerAdapter {

	private static final int FRAGMENT_COUNT = 7;
	private static final int HOT_LISTINGS = 0;
	private static final int NEW_LISTINGS = 1;
	private static final int TOP_TODAY_LISTINGS = 2;
	private static final int TOP_WEEK_LISTINGS = 3;
	private static final int TOP_MONTH_LISTINGS = 4;
	private static final int TOP_YEAR_LISTINGS = 5;
	private static final int TOP_ALLTIME_LISTINGS = 6;

	private Context context;

	public TabsAdapter(Context context, FragmentManager fragmentManager)
	{
		super(fragmentManager);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position)
	{
		switch (position) {
		case HOT_LISTINGS:
			return new HotListingsFragment();
		case NEW_LISTINGS:
			return new NewListingsFragment();
		case TOP_TODAY_LISTINGS:
			return new TopListingsFragment();
		case TOP_WEEK_LISTINGS:
			return new TopWeekListingsFragment();
		case TOP_MONTH_LISTINGS:
			return new TopMonthListingsFragment();
		case TOP_YEAR_LISTINGS:
			return new TopYearListingsFragment();
		case TOP_ALLTIME_LISTINGS:
			return new TopAllTimeListingsFragment();
		default:
			throw new IllegalStateException("Invalid tab position");
		}
	}

	@Override
	public int getCount()
	{
		return FRAGMENT_COUNT;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		String[] titles = context.getResources().getStringArray(R.array.tab_names);
		return titles[position];
	}
}