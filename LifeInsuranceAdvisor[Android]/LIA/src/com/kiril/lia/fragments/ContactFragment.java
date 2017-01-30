package com.kiril.lia.fragments;


import com.kiril.lia.R;
import com.kiril.lia.activities.ResultsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ContactFragment extends Fragment {
	
	Button mBtnBack;
	Button mBtnSend;
	EditText mName;
	EditText mMail;
	EditText mTelNumber;
	EditText mMsg;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_kontakt, container,false);
		mBtnBack = (Button)view.findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ResultsActivity.class);
				startActivity(intent);
			}
		});
		
		mBtnSend = (Button)view.findViewById(R.id.btn_send);
		
		mName= (EditText)view.findViewById(R.id.edit_name);
		mMail= (EditText)view.findViewById(R.id.edit_mail);
		mTelNumber= (EditText)view.findViewById(R.id.edit_tel_number);
		mMsg= (EditText)view.findViewById(R.id.edit_msg);
		
		return view;
		
	}

}
