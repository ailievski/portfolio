package com.andrej.ilievski.jokesmk.ui.activities;

import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.array;
import com.andrej.ilievski.jokesmk.R.id;
import com.andrej.ilievski.jokesmk.R.layout;
import com.andrej.ilievski.jokesmk.R.menu;
import com.andrej.ilievski.jokesmk.ui.adapters.JokesAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class JokesListActivity extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jokes_list);
		
		listView = (ListView) findViewById(R.id.jokes_list);
		
		 Intent intent = getIntent();
		    String key = "key";
		    String title = intent.getStringExtra(key);
		    setTitle(title);
		    
		    setDefaultJokesAdapter();
	}
	
	private void setDefaultJokesAdapter() {
		String[] jokesArray = getResources().getStringArray(R.array.array_jokes_informaticki);
		JokesAdapter sampleAdapter = new JokesAdapter(this, R.layout.joke_item, jokesArray);
		listView.setAdapter(sampleAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(JokesListActivity.this);
		inflater.inflate(R.menu.jokes_list_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
