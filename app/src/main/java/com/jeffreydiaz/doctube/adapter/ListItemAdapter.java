package com.jeffreydiaz.doctube.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jeffreydiaz.doctube.R;
import com.jeffreydiaz.doctube.model.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListItemAdapter extends ArrayAdapter<ListItem> {

	private List<ListItem> items;

	public ListItemAdapter(Context context, List<ListItem> items)
	{
		super(context, R.layout.listview_row, R.id.doc_title, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = super.getView(position, convertView, parent);
		ItemViewHolder holder = (ItemViewHolder) row.getTag();

		if (holder == null) {
			holder = new ItemViewHolder(row);
			row.setTag(holder);
		}

		Picasso.with(getContext())
			.load(items.get(position).getThumbnailUrl())
			.into(holder.docThumbnail);
		holder.docTitle.setText(items.get(position).getDocumentaryTitle());

		return row;
	}
}