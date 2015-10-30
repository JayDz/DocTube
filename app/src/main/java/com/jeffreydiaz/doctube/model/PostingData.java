package com.jeffreydiaz.doctube.model;

import com.google.gson.annotations.SerializedName;

public class PostingData {

	@SerializedName("is_self")   public boolean isSelf;
	@SerializedName("thumbnail") public String thumbnailUrl;
	@SerializedName("url")       public String documentaryUrl;
	@SerializedName("title")     public String documentaryTitle;
	@SerializedName("domain")    public String documentaryHostDomain;
}