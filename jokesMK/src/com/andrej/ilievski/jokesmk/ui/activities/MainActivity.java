package com.andrej.ilievski.jokesmk.ui.activities;



import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.array;
import com.andrej.ilievski.jokesmk.R.id;
import com.andrej.ilievski.jokesmk.R.layout;
import com.andrej.ilievski.jokesmk.R.menu;
import com.andrej.ilievski.jokesmk.ui.adapters.CategoriesAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView listView;
	private Button btnNewest;
	private Button btnMostPopular;
	private Button btnMyProfile;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		listView = (ListView) findViewById(R.id.jokes_list);
		
		btnNewest = (Button) findViewById(R.id.btn_newest);
		
		btnMostPopular = (Button) findViewById(R.id.btn_most_popular);
		
		btnMyProfile = (Button) findViewById(R.id.btn_my_profile);
		btnMyProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, MyProfileActivity.class);
				startActivity(myIntent);
			}
		});
		
		setDefaultCategoriesAdapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void setDefaultCategoriesAdapter() {
		String[] categoriesArray = getResources().getStringArray(R.array.array_categories);
		CategoriesAdapter sampleAdapter = new CategoriesAdapter(this, R.layout.categories_item, categoriesArray);
		listView.setAdapter(sampleAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Object title = arg0.getAdapter().getItem(arg2); 
				String titleName = title.toString();
				
				Intent intent = new Intent(MainActivity.this, JokesListActivity.class);
				intent.putExtra("key", titleName);
				startActivity(intent);	
			}
		});
		
	}
    
}
