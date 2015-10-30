package com.jeffreydiaz.doctube.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeffreydiaz.doctube.R;

public class ItemViewHolder {

	ImageView docThumbnail;
	TextView docTitle;

	public ItemViewHolder(View view)
	{
		docThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
		docTitle = (TextView) view.findViewById(R.id.doc_title);
	}
}