package com.jeffreydiaz.doctube.model;

public class ListItem {

	private String thumbnailUrl;
	private String documentaryUrl;
	private String documentaryTitle;

	public ListItem(String thumbnailUrl, String documentaryUrl, String documentaryTitle)
	{
		this.thumbnailUrl = thumbnailUrl;
		this.documentaryUrl = documentaryUrl;
		this.documentaryTitle = documentaryTitle;
	}

	public String getThumbnailUrl() { return thumbnailUrl; }
	public String getDocumentaryUrl() { return documentaryUrl; }
	public String getDocumentaryTitle() { return documentaryTitle; }
}