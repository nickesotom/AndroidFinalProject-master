package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

import org.w3c.dom.Text;

public class SleepScreenActivity extends AppCompatActivity {

	Button mShowDialog;
	private String[] arraySpinnerHours = new String [12];
	private String[] arraySpinnerMinutes = new String [60];
	TextView targetBedTime;
	Spinner mHourSpinner;
	Spinner mMinSpinner;
	Spinner mDayNightSpinner;
	Button showBedtime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep_screen);
		CustomAnalogClock customAnalogClock = (CustomAnalogClock) findViewById(R.id.analog_clock);
		customAnalogClock.setAutoUpdate(true);

		int humanCounterHours = 0;
		int humanCounterMin = 0;
		for(int hours = 0; hours < 12; hours++) {
			humanCounterHours++;
			this.arraySpinnerHours[hours] = "" +humanCounterHours;
		}
		for(int min = 0; min < 60; min++) {
			humanCounterMin++;
			if(humanCounterMin <= 9) {
				this.arraySpinnerMinutes[min] = "0"+min;
			}
			else {
				this.arraySpinnerMinutes[min] = ""+min;
			}

		}
		showBedtime = (Button) findViewById(R.id.display_bed_time);
		targetBedTime = (TextView) findViewById(R.id.text_bedtime);
		mShowDialog = (Button) findViewById(R.id.bedtime_button);
		mShowDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new AlertDialog.Builder(SleepScreenActivity.this);
				final View mView = getLayoutInflater().inflate(R.layout.dialog_bedtime_spinner, null);
				mBuilder.setTitle("Set a targeted bedtime");

				mHourSpinner = (Spinner) mView.findViewById(R.id.hours_spinner);
				mMinSpinner = (Spinner) mView.findViewById(R.id.minutes_spinner);
				mDayNightSpinner = (Spinner) mView.findViewById(R.id.daynight_spinner);

				ArrayAdapter<String> mHourAdapter = new ArrayAdapter<String>(SleepScreenActivity.this, android.R.layout.simple_spinner_item,
						arraySpinnerHours);
				ArrayAdapter<String> mMinAdapter = new ArrayAdapter<String>(SleepScreenActivity.this, android.R.layout.simple_spinner_item,
						arraySpinnerMinutes);
				ArrayAdapter<String> mDayNightAdapter = new ArrayAdapter<String>(SleepScreenActivity.this, android.R.layout.simple_spinner_item,
						getResources().getStringArray(R.array.AMPM));

				mHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mMinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mDayNightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				mHourSpinner.setAdapter(mHourAdapter);
				mMinSpinner.setAdapter(mMinAdapter);
				mDayNightSpinner.setAdapter(mDayNightAdapter);


				mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(SleepScreenActivity.this, mHourSpinner.getSelectedItem().toString() + ":" + mMinSpinner.getSelectedItem().toString() +
								mDayNightSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

						//targetBedTime.setText(mHourSpinner.getSelectedItem().toString()+":"+mMinSpinner.getSelectedItem().toString()+ " " + mDayNightSpinner.getSelectedItem().toString());
						saveBedtime(mView);
						dialog.dismiss();
					}
				});
				mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				mBuilder.setView(mView);
				AlertDialog dialog = mBuilder.create();
				dialog.show();
			}

		});
		showBedtime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				displayData(v);
			}
		});

	}
	public void saveBedtime(View view) {
		String hourSelectedPosition = mHourSpinner.getSelectedItem().toString();
		String minSelectionPosition = mMinSpinner.getSelectedItem().toString();
		String ampmSelectedPosition = mDayNightSpinner.getSelectedItem().toString();
		SharedPreferences shareBedtime = getSharedPreferences("userBedtime", Context.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = shareBedtime.edit();
		mEditor.putString("bedtimeHours", hourSelectedPosition);
		mEditor.putString("bedtimeMinute", minSelectionPosition);
		mEditor.putString("bedtimeAMPM", ampmSelectedPosition);
		mEditor.apply();

		Toast.makeText(this, "saved your bedtime!", Toast.LENGTH_SHORT).show();

	}
	public void displayData(View view) {
		SharedPreferences sharedPref = getSharedPreferences("userBedtime", Context.MODE_PRIVATE);


		String hoursBed = sharedPref.getString("bedtimeHours", "");
		String minBed = sharedPref.getString("bedtimeMinute", "");
		String dayNight = sharedPref.getString("bedtimeAMPM", "");

		targetBedTime.setText(hoursBed + ":" + minBed + " " + dayNight);


	}
	public void onClickChecklist(View view) {
		Intent toChecklist = new Intent(this, ChecklistActivity.class);
		startActivity(toChecklist);
	}
}

