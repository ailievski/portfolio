package com.andrej.ilievski.jokesmk.ui.adapters;

import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.drawable;
import com.andrej.ilievski.jokesmk.R.id;
import com.andrej.ilievski.jokesmk.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoriesAdapter extends ArrayAdapter<String> {
	
	public Integer[] imageId = {
		      R.drawable.informaticki,
		      R.drawable.obrazovanie,
		      R.drawable.policajski,
		      R.drawable.plavushi,
		      R.drawable.rasisticki,
		      R.drawable.politicki,
		      R.drawable.crnhumor,
		      R.drawable.erotski,
		      R.drawable.zivotni,
		      R.drawable.razno,
		      R.drawable.cak_noris,
		      R.drawable.zlatna_riba,
		      R.drawable.perica,
		      R.drawable.mujo,
		      R.drawable.cigo,
		      R.drawable.kumanovski_muabeti,
		      R.drawable.berovski_biseri,
		      R.drawable.glupi
	};
	
	private LayoutInflater inflater;

	public CategoriesAdapter(Context context, int resource, String[] objects) {
		super(context, R.layout.categories_item, objects);

		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.categories_item, null);
		}
		
		TextView txtCategory = (TextView) convertView.findViewById(R.id.txt_category_item);
		ImageView imgCategoryPicture  = (ImageView) convertView.findViewById(R.id.img_category_picture);
        
		String category = getItem(position);
		
		txtCategory.setText(category.toString());
		
		imgCategoryPicture.setImageResource(imageId[position]);
		
		return convertView;
	}
	
}
