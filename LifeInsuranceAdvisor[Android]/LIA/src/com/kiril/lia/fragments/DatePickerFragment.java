package com.kiril.lia.fragments;

import java.util.Calendar;

import com.kiril.lia.R;
import com.kiril.lia.activities.Main;
import com.kiril.lia.activities.Main.PlaceholderFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment {

	private Fragment mFragment;

	public DatePickerFragment(OnClickListener onClickListener) {
		mFragment = (Fragment) onClickListener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(),
				(OnDateSetListener) mFragment, year, month, day);
	}

}
