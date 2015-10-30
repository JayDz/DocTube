package com.jeffreydiaz.doctube.ui;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeffreydiaz.doctube.R;
import com.jeffreydiaz.doctube.adapter.TabsAdapter;

public class DocTubeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doc_tube);

		setUpTabs();
	}

	private void setUpTabs()
	{
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		TabsAdapter adapter = new TabsAdapter(getApplicationContext(), getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
		tabLayout.setupWithViewPager(viewPager);
	}
}