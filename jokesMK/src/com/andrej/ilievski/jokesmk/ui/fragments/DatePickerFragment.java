package com.andrej.ilievski.jokesmk.ui.fragments;

import java.util.Calendar;

import com.andrej.ilievski.jokesmk.R;
import com.andrej.ilievski.jokesmk.R.id;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
	Activity a = getActivity();
	
	Button dateButton = (Button) a.findViewById(R.id.btn_date_picker);
	String sYear = Integer.toString(year).substring(0, 4);
    String sMonth = Integer.toString(month + 1);
    String sDay = Integer.toString(day);
    dateButton.setText(sDay + "/" + sMonth + "/" + sYear);
	}

}
