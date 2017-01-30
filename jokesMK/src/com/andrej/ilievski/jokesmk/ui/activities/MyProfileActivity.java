package com.andrej.ilievski.jokesmk.ui.activities;

import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.id;
import com.andrej.ilievski.jokesmk.R.layout;
import com.andrej.ilievski.jokesmk.ui.fragments.DatePickerFragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MyProfileActivity extends Activity {
	
	private ImageView imgProfilePicture;
	private EditText edittxtUserName;
	private EditText edittxtCity;
	private Button btnDate;
	private Button btnChooseImage;
	private Button btnSave;
	private RadioButton rbtnMale;
	private RadioButton rbtnFemale;
	
	private static int RESULT_LOAD_IMAGE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		
		imgProfilePicture = (ImageView) findViewById(R.id.img_profile_picture);
		
		edittxtUserName = (EditText) findViewById(R.id.edittxt_user_name);
		edittxtCity = (EditText) findViewById(R.id.edittxt_user_city);
		
		btnDate = (Button) findViewById(R.id.btn_date_picker);
		btnDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
			    newFragment.show(getFragmentManager(), "datePicker");				
			}
		});
		
		btnChooseImage = (Button) findViewById(R.id.btn_choose_image);
		btnChooseImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	             startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
		
		btnSave = (Button) findViewById(R.id.btn_save_profile);
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveData();
				Toast.makeText(MyProfileActivity.this, "Вашата промена е зачувана", Toast.LENGTH_SHORT).show();
			}
		});
		
		rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
		rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
		rbtnMale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (rbtnMale.isChecked()) {
					rbtnFemale.setChecked(false);
				}
			}
		});
		rbtnFemale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (rbtnFemale.isChecked()) {
					rbtnMale.setChecked(false);
				}
			}
		});
		
		loadSavedPreferences();
	}
	
	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		edittxtUserName.setText(sharedPreferences.getString("string_edittxtUserName", ""));
		edittxtCity.setText(sharedPreferences.getString("string_edittxtCity", ""));
		rbtnMale.setChecked(sharedPreferences.getBoolean("checkbox_1", false));
		rbtnFemale.setChecked(sharedPreferences.getBoolean("checkbox_2", false));
		btnDate.setText(sharedPreferences.getString("string_btnDate", ""));
	}

	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	private void savePreferences1(String key, boolean value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor shEditor = sharedPreferences.edit();
		shEditor.putBoolean(key,value);
		shEditor.commit();
	}
	
	public void saveData() {
		savePreferences("string_edittxtUserName", edittxtUserName.getText().toString());
		savePreferences("string_edittxtCity",edittxtCity.getText().toString());
		savePreferences1("checkbox_1", rbtnMale.isChecked());
		savePreferences1("checkbox_2", rbtnFemale.isChecked());
		savePreferences("string_btnDate", btnDate.getText().toString());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            int width=100;
            int height=100;
            Bitmap bmp = BitmapFactory.decodeFile(picturePath);
            bmp=Bitmap.createScaledBitmap(bmp, width,height, true);
            imgProfilePicture.setImageBitmap(bmp);
        }
	}
	
	
}
