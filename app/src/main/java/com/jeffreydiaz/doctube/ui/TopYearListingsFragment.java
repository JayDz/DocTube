package com.jeffreydiaz.doctube.ui;

public class TopYearListingsFragment extends DocTubeFragment {

	private static final int TOP_YEAR_LISTINGS = 5;

	@Override
	protected int getTopic()
	{
		return TOP_YEAR_LISTINGS;
	}
}