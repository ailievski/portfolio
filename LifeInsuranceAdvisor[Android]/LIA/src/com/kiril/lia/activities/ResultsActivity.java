package com.kiril.lia.activities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kiril.lia.R;
import com.kiril.lia.domain.CompanyResultModel;
import com.kiril.lia.domain.InsuranceModel;
import com.kiril.lia.fragments.ContactFragment;
import com.kiril.lia.parsers.JsonParser;
import com.kiril.lia.parsers.XMLParser;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ResultsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_results);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.results, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private static final String EMPTYOFFER = "nema ponuda";
		Spinner mSpChangePremium;
		Spinner mSpChangeDuration;
		ImageButton mBtnContactCroatia;
		ImageButton mBtnContactUniqa;
		ImageButton mBtnContactWinner;
		ImageButton mBtnContactGrawe;
		private TextView croText;
		private TextView uniqaText;
		private TextView winnerText;
		private TextView graweText;
		private Button searchButton;
		ProgressBar pb;

		private InsuranceModel model;

		ArrayList<String> durationList;
		private List<CompanyResultModel> results;
		ArrayAdapter<String> durationAdapter;

		public PlaceholderFragment() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);

			model = getActivity().getIntent().getParcelableExtra(
					"modelparcable");

			requestData(model.getGender(), model.getYears(),
					model.getPremium(), model.getPeriod());
			String[] durations = getResources().getStringArray(
					R.array.durations);
			durationList = new ArrayList<String>(Arrays.asList(durations));

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_results,
					container, false);
			pb = (ProgressBar) rootView.findViewById(R.id.progressBar1);
			pb.setVisibility(View.VISIBLE);

			searchButton = (Button) rootView
					.findViewById(R.id.btn_result_search);
			croText = (TextView) rootView.findViewById(R.id.croatia_textview);
			uniqaText = (TextView) rootView.findViewById(R.id.uniqa_textview);
			winnerText = (TextView) rootView.findViewById(R.id.winner_textview);
			graweText = (TextView) rootView.findViewById(R.id.grawe_textview);

			searchButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					requestData(model.getGender(), model.getYears(),
							model.getPremium(), model.getPeriod());
					pb.setVisibility(View.VISIBLE);

				}
			});

			mSpChangePremium = (Spinner) rootView.findViewById(R.id.spinner1);
			String[] premiums = getResources().getStringArray(R.array.premiums);
			ArrayAdapter<String> premiumAdapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_spinner_item,
					premiums);
			premiumAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpChangePremium.setAdapter(premiumAdapter);

			mSpChangePremium
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

			mSpChangeDuration = (Spinner) rootView.findViewById(R.id.spinner2);

			durationAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item, durationList);

			durationAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpChangeDuration.setAdapter(durationAdapter);

			if (model.getYears() >= 51) {
				int size = durationList.size() + 10;

				for (int i = 76 - model.getYears(); i < size; i++) {

					durationList.remove(String.valueOf(i));

				}

				durationAdapter.notifyDataSetChanged();
			}
			mSpChangeDuration
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int pos, long arg3) {

							int period = Integer.parseInt(parent
									.getItemAtPosition(pos).toString());
							model.setPeriod(period);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			mBtnContactCroatia = (ImageButton) rootView
					.findViewById(R.id.contact_croatia);

			mBtnContactCroatia.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String[] TO = { "contact@cro.mk" };

					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setData(Uri.parse("mailto:"));
					emailIntent.setType("text/plain");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
					try {

						startActivity(Intent.createChooser(emailIntent,
								"Choose an email client from..."));

					} catch (android.content.ActivityNotFoundException ex) {

						Toast.makeText(getActivity(),
								"No email client installed.",

								Toast.LENGTH_LONG).show();

					}

					// getActivity().getSupportFragmentManager()
					// .beginTransaction()
					// .replace(R.id.container, new ContactFragment())
					// .commit();
				}
			});

			mBtnContactUniqa = (ImageButton) rootView
					.findViewById(R.id.contact_uniqa);

			mBtnContactUniqa.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					String[] TO = { "info@uniqa.mk" };

					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setData(Uri.parse("mailto:"));
					emailIntent.setType("text/plain");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
					try {

						startActivity(Intent.createChooser(emailIntent,
								"Choose an email client from..."));

					} catch (android.content.ActivityNotFoundException ex) {

						Toast.makeText(getActivity(),
								"No email client installed.",

								Toast.LENGTH_LONG).show();

					}

					// getActivity().getSupportFragmentManager()
					// .beginTransaction()
					// .replace(R.id.container, new ContactFragment())
					// .commit();
				}
			});

			mBtnContactWinner = (ImageButton) rootView
					.findViewById(R.id.contact_winner);

			mBtnContactWinner.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					String[] TO = { "winner@winner.mk" };

					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setData(Uri.parse("mailto:"));
					emailIntent.setType("text/plain");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
					try {

						startActivity(Intent.createChooser(emailIntent,
								"Choose an email client from..."));

					} catch (android.content.ActivityNotFoundException ex) {

						Toast.makeText(getActivity(),
								"No email client installed.",

								Toast.LENGTH_LONG).show();

					}
					// getActivity().getSupportFragmentManager()
					// .beginTransaction()
					// .replace(R.id.container, new ContactFragment())
					// .commit();
				}
			});

			mBtnContactGrawe = (ImageButton) rootView
					.findViewById(R.id.contact_grawe);

			mBtnContactGrawe.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String[] TO = { "office.skopje@grawe.at" };

					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setData(Uri.parse("mailto:"));
					emailIntent.setType("text/plain");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
					try {

						startActivity(Intent.createChooser(emailIntent,
								"Choose an email client from..."));

					} catch (android.content.ActivityNotFoundException ex) {

						Toast.makeText(getActivity(),
								"No email client installed.",

								Toast.LENGTH_LONG).show();

					}
					// getActivity().getSupportFragmentManager()
					// .beginTransaction()
					// .replace(R.id.container, new ContactFragment())
					// .commit();
				}
			});

			return rootView;
		}

		private void requestData(String modelGender, int modelYears,
				int modelPremium, int modelPeriod) {
			String encodedString = "";
			String gender;
			if (modelGender.equals("male")) {
				gender = getResources().getString(R.string.mashki);

				try {
					encodedString = URLEncoder.encode(gender, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				gender = getResources().getString(R.string.zenski);

				try {
					encodedString = URLEncoder.encode(gender, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			String uri = String
					.format("http://webmedia.mk/api-z-osiguruvanje/api/presmetka?gender=%1s&vozrast=%2d&period=%d&premija=%d",
							encodedString, modelYears, modelPeriod,
							modelPremium);

			StringRequest request = new StringRequest(uri,
					new Response.Listener<String>() {

						@Override
						public void onResponse(String response) {

							results = JsonParser.parseJson(response);

							// 1 grave
							// 2 croacia
							// 3 uniqa
							// 4 winner

							pb.setVisibility(View.INVISIBLE);

							croText.setText(String.valueOf(results.get(1)
									.getOsigurenaSuma()) + " €");

							uniqaText.setText(String.valueOf(results.get(2)
									.getOsigurenaSuma()) + " €");

							winnerText.setText(String.valueOf(results.get(3)
									.getOsigurenaSuma()) + " €");

							graweText.setText(String.valueOf(results.get(0)
									.getOsigurenaSuma()) + " €");

						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError ex) {
							pb.setVisibility(View.INVISIBLE);
							Toast.makeText(getActivity(), "Nema Rezultati",
									Toast.LENGTH_SHORT).show();
						}
					});
			RequestQueue queue = Volley.newRequestQueue(getActivity());
			queue.add(request);

		}
	}

}
