package com.jeffreydiaz.doctube.ui;

public class TopListingsFragment extends DocTubeFragment {

	private static final int TOP_TODAY_LISTINGS = 2;

	@Override
	protected int getTopic()
	{
		return TOP_TODAY_LISTINGS;
	}
}