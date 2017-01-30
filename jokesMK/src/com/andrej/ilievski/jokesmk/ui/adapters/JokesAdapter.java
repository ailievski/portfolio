package com.andrej.ilievski.jokesmk.ui.adapters;

import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.id;
import com.andrej.ilievski.jokesmk.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class JokesAdapter extends ArrayAdapter<String> {
	
	private LayoutInflater inflater;

	public JokesAdapter(Context context, int resource, String[] objects) {
		super(context, R.layout.joke_item, objects);
		
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null) {
			convertView = inflater.inflate(R.layout.joke_item, null);
		}
		
		TextView jokeContent = (TextView) convertView.findViewById(R.id.txt_joke_content);
		TextView userTitle = (TextView) convertView.findViewById(R.id.txt_user_title);
		TextView jokeRating = (TextView) convertView.findViewById(R.id.txt_joke_rating);
		
		String content = getItem(position);
		jokeContent.setText(content.toString());
		
		return convertView;
	}
	
}
