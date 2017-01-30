package com.kiril.lia.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.kiril.lia.R;
import com.kiril.lia.domain.InsuranceModel;

public class Main extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment implements
			OnDateSetListener {

		CheckBox mCheckMale;
		CheckBox mCheckFemale;
		Button mBtnSearch;
		Button mBtnDateofBirth;
		Spinner mSpDuration;
		Spinner mSpProfession;
		Spinner mSpPremium;

		ArrayAdapter<String> durationAdapter;
		ArrayList<String> durationList;
		ArrayList<String> durationListTmp;

		private InsuranceModel model;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);

			model = new InsuranceModel();
			durationListTmp = new ArrayList<String>();

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			mSpDuration = (Spinner) rootView
					.findViewById(R.id.spinner_duration);
			String[] durations = getResources().getStringArray(
					R.array.durations);
			durationList = new ArrayList<String>(Arrays.asList(durations));

			durationAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item, durationList);
			durationAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpDuration.setAdapter(durationAdapter);
			mSpDuration.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View arg1,
						int pos, long arg3) {

					int period = Integer.parseInt(parent.getItemAtPosition(pos)
							.toString());
					model.setPeriod(period);

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

			mSpPremium = (Spinner) rootView.findViewById(R.id.spinner_premium);
			String[] premiums = getResources().getStringArray(R.array.premiums);
			ArrayAdapter<String> premiumAdapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_spinner_item,
					premiums);
			premiumAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			mSpPremium.setAdapter(premiumAdapter);
			mSpPremium
					.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int pos, long arg3) {

							int premium = Integer.parseInt(parent
									.getItemAtPosition(pos).toString());
							model.setPremium(premium);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			// mSpProfession = (Spinner) rootView
			// .findViewById(R.id.spinner_profession);
			// String[] professions = getResources().getStringArray(
			// R.array.professions);
			// ArrayAdapter<String> professionAdapter = new
			// ArrayAdapter<String>(
			// getActivity(), android.R.layout.simple_spinner_item,
			// professions);
			// professionAdapter
			// .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// mSpProfession.setAdapter(professionAdapter);

			mBtnDateofBirth = (Button) rootView.findViewById(R.id.dateOfBirth);
			mBtnDateofBirth.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					DialogFragment newFragment = new DatePickerFragment();
					newFragment.show(getFragmentManager(), "datePicker");

				}
			});

			mBtnSearch = (Button) rootView.findViewById(R.id.btn_search);
			mBtnSearch.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (isOnline()) {

						if (isEverithingFilled()) {

							Intent i = new Intent(getActivity(),
									ResultsActivity.class);
							i.putExtra("modelparcable", model);
							startActivity(i);
						} else {
							Toast.makeText(getActivity(),
									"Vnesete gi site podatoci",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(getActivity(),
								"Network isn't available", Toast.LENGTH_LONG)
								.show();
					}

				}
			});

			mCheckMale = (CheckBox) rootView.findViewById(R.id.check_male);
			mCheckMale.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					mCheckFemale.setChecked(false);
					model.setGender("male");

				}
			});

			mCheckFemale = (CheckBox) rootView.findViewById(R.id.check_female);
			mCheckFemale.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					mCheckMale.setChecked(false);
					model.setGender("female");

				}
			});
			return rootView;
		}

		private boolean isEverithingFilled() {

			if (!isChecked() || (model.getYears() == 0)) {
				return false;

			} else {
				return true;
			}

		}

		public boolean isChecked() {
			if ((mCheckFemale.isChecked()) || (mCheckMale.isChecked())) {
				return true;
			} else {
				return false;
			}

		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			String sYear = Integer.toString(year).substring(0, 4);
			String sMonth = Integer.toString(monthOfYear + 1);
			String sDay = Integer.toString(dayOfMonth);

			mBtnDateofBirth.setText(sDay + "  |  " + sMonth + "  |  " + sYear);
			int years = calculateAgeWithJoda(Integer.valueOf(sYear),
					Integer.valueOf(sMonth), Integer.valueOf(sDay));
			model.setYears(years);

			if (model.getYears() >= 51) {

				if (!durationListTmp.isEmpty())
					for (String s : durationListTmp)
						durationList.add(s);

				durationListTmp.clear();

				int size = durationList.size() + 10;

				for (int i = 76 - model.getYears(); i < size; i++) {

					durationList.remove(String.valueOf(i));
					durationListTmp.add(String.valueOf(i));
				}

				durationAdapter.notifyDataSetChanged();

			} else {
				if (!durationListTmp.isEmpty()) {
					for (String s : durationListTmp) {
						durationList.add(s);

					}
					durationAdapter.notifyDataSetChanged();
					durationListTmp.clear();
				}
			}

			Toast.makeText(getActivity(), "Vozrast " + years + " godini",
					Toast.LENGTH_SHORT).show();

		}

		public class DatePickerFragment extends DialogFragment {

			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);

				Date curentDate = new Date();

				DatePickerDialog d = new DatePickerDialog(getActivity(),
						PlaceholderFragment.this, year, month, day);
				long min15 = (long) 4.734e+11;
				long max65 = (long) 2.083e+12 - (long) 2.592e+8;
				d.getDatePicker().setMaxDate(curentDate.getTime() - min15);
				d.getDatePicker().setMinDate(curentDate.getTime() - max65);
				return d;
			}

		}

		private int calculateAgeWithJoda(int birthYear, int birthMounth,
				int birthDay) {

			LocalDate birthdate = new LocalDate(birthYear, birthMounth,
					birthDay);
			LocalDate now = new LocalDate();
			Period period = new Period(birthdate, now);

			int nov = period.getYears();

			return nov;
		}

		protected boolean isOnline() {
			ConnectivityManager cm = (ConnectivityManager) getActivity()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnectedOrConnecting()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
