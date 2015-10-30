package com.jeffreydiaz.doctube.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jeffreydiaz.doctube.R;
import com.jeffreydiaz.doctube.adapter.ListItemAdapter;
import com.jeffreydiaz.doctube.model.Children;
import com.jeffreydiaz.doctube.model.ListItem;
import com.jeffreydiaz.doctube.model.Listing;
import com.jeffreydiaz.doctube.util.RedditEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public abstract class DocTubeFragment extends Fragment {

	private static final String BASE = "https://www.reddit.com";
	private static final int HOT_LISTINGS = 0;
	private static final int NEW_LISTINGS = 1;
	private static final int TOP_TODAY_LISTINGS = 2;
	private static final int TOP_WEEK_LISTINGS = 3;
	private static final int TOP_MONTH_LISTINGS = 4;
	private static final int TOP_YEAR_LISTINGS = 5;
	private static final int TOP_ALLTIME_LISTINGS = 6;

	private ListView listView;
	private SwipeRefreshLayout swipeRefreshLayout;
	private Retrofit retrofit;
	private List<ListItem> data;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_doctube, container, false);

		listView = (ListView) view.findViewById(R.id.listings);
		listView.setDivider(null);
		listView.setDividerHeight(0);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
			{
				if (data == null) return;
				launchVideo(data.get(position).getDocumentaryUrl());
			}
		});

		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh()
			{
				fetchData();
			}
		});
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
			android.R.color.holo_green_light,
			android.R.color.holo_orange_light,
			android.R.color.holo_red_light);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		fetchData();
	}

	public void launchVideo(String url)
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}

	private void fetchData()
	{
		isRefreshing(true);
		initRetrofit();
		RedditEndPoints redditService = retrofit.create(RedditEndPoints.class);
		Call<Listing> request = findRequestForPosition(redditService, getTopic());
		request.enqueue(new Callback<Listing>() {
			@Override
			public void onResponse(Response<Listing> response, Retrofit retrofit)
			{
				ListItemAdapter adapter = extractResponse(response);
				listView.setAdapter(adapter);
				isRefreshing(false);
			}

			@Override
			public void onFailure(Throwable t)
			{
				isRefreshing(false);
				Toast.makeText(getContext(), "Network Failure", Toast.LENGTH_LONG).show();
			}
		});
	}

	private Call<Listing> findRequestForPosition(RedditEndPoints redditService, int pos)
	{
		switch (pos) {
		case TOP_TODAY_LISTINGS:
			return redditService.getTopDocsToday();
		case HOT_LISTINGS:
			return redditService.getHotDocsToday();
		case NEW_LISTINGS:
			return redditService.getNewDocsToday();
		case TOP_WEEK_LISTINGS:
			return redditService.getTopDocsThisWeek();
		case TOP_MONTH_LISTINGS:
			return redditService.getTopDocsThisMonth();
		case TOP_YEAR_LISTINGS:
			return redditService.getTopDocsThisYear();
		case TOP_ALLTIME_LISTINGS:
			return redditService.getTopDocsAllTime();
		default:
			throw new IllegalStateException("No URL found.");
		}
	}

	private ListItemAdapter extractResponse(Response<Listing> response)
	{
		List<Children> children = response.body().data.children;
		data = new ArrayList<>(children.size());
		for (Children child : children) {
			if (!child.data.isSelf && isHostedOnYoutube(child)) {
				String docThumbnailUrl = child.data.thumbnailUrl;
				String docUrl = child.data.documentaryUrl;
				String docTitle = child.data.documentaryTitle;

				data.add(new ListItem(docThumbnailUrl, docUrl, docTitle));
			}
		}

		if (getActivity() != null) {
			return new ListItemAdapter(getActivity(), data);
		}
		return null;
	}

	private boolean isHostedOnYoutube(Children child)
	{
		return child.data.documentaryHostDomain.equals("youtu.be") ||
			child.data.documentaryHostDomain.equals("youtube.com");
	}

	private void isRefreshing(boolean isRefreshing)
	{
		swipeRefreshLayout.setRefreshing(isRefreshing);
	}

	private void initRetrofit()
	{
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
				.baseUrl(BASE)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
		}
	}

	abstract protected int getTopic();
}