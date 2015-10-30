package com.jeffreydiaz.doctube.util;

import com.jeffreydiaz.doctube.model.Listing;

import retrofit.Call;
import retrofit.http.GET;

public interface RedditEndPoints {

	@GET("/r/documentaries/top.json?limit=75")
	Call<Listing> getTopDocsToday();

	@GET("/r/documentaries/hot.json?limit=75")
	Call<Listing> getHotDocsToday();

	@GET("/r/documentaries/new.json?limit=75")
	Call<Listing> getNewDocsToday();

	@GET("/r/documentaries/top.json?limit=75&t=week")
	Call<Listing> getTopDocsThisWeek();

	@GET("/r/documentaries/top.json?limit=75&t=month")
	Call<Listing> getTopDocsThisMonth();

	@GET("/r/documentaries/top.json?limit=75&t=year")
	Call<Listing> getTopDocsThisYear();

	@GET("/r/documentaries/top.json?limit=75&t=all")
	Call<Listing> getTopDocsAllTime();
}