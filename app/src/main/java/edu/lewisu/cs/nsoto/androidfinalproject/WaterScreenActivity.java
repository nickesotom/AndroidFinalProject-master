package edu.lewisu.cs.nsoto.androidfinalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class WaterScreenActivity extends AppCompatActivity {
	Button mAddWater;
	Button mResetWater;
	TextView waterAmount;
	ProgressBar mProgressBar;
	Spinner mSpinner;

	private WaterModel water = new WaterModel(0, 64);;
	private WaterModel DBWater;

	final DBHandler db = new DBHandler(this);

	SharedPreferences prefs;

	//private PendingIntent pendingIntent;
	//private AlarmManager manager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_screen);

		prefs = getSharedPreferences("edu.lewisu.cs.nsoto.androidfinalproject", MODE_PRIVATE);

		if (prefs.getBoolean("firstrun", true)) {
			db.addWater(water);
			//startAlarm();
			prefs.edit().putBoolean("firstrun", false).commit();
		}

		/*Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

		registerReceiver(broadcastReceiver, new IntentFilter("midnightIntent"));*/

		mAddWater = (Button) findViewById(R.id.fill_water_button);
		mResetWater = (Button) findViewById(R.id.reset_water_button);
		waterAmount = (TextView) findViewById(R.id.water_consumed);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

		DBWater = db.getWaterAmount(1);


		mProgressBar.setMax((int)DBWater.getMaxWater());
		mProgressBar.setProgress((int)DBWater.getCurrentWater());
		waterAmount.setText(String.valueOf(DBWater.getCurrentWater()) + "/" + String.valueOf(DBWater.getMaxWater()));


		mAddWater.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new AlertDialog.Builder(WaterScreenActivity.this);
				View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

				mBuilder.setTitle("Choose an amount of water");

				mSpinner = (Spinner) mView.findViewById(R.id.spinner);
				ArrayAdapter<String> waterAmounts = new ArrayAdapter<String>(WaterScreenActivity.this,
						android.R.layout.simple_spinner_item,
						getResources().getStringArray(R.array.waterValues));

				waterAmounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				mSpinner.setAdapter(waterAmounts);

				mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("Enter a water amount...")) {
							dialog.dismiss();
						} else {
							//Toast.makeText(WaterScreenActivity.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
							addingWater(db);
							dialog.dismiss();
						}
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

		mResetWater.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v){
				db.updateWater(water);
				waterAmount.setText(String.valueOf(DBWater.getCurrentWater()) + "/" + String.valueOf(DBWater.getMaxWater()));
			}
		});
	}



	/*public void startAlarm() {
		manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);

		manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
	}

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			db.updateWater(water);
		}
	};*/



	public void addingWater(DBHandler db) {

		int spinnerPosition = mSpinner.getSelectedItemPosition();
		String[] waterNums = getResources().getStringArray(R.array.waterNums);
		float amount = Float.parseFloat(waterNums[spinnerPosition]);


		DBWater.addWater(amount);
		db.updateWater(DBWater);

		DBWater = db.getWaterAmount(1);

		mProgressBar.incrementProgressBy((int)amount);
		waterAmount.setText(String.valueOf(DBWater.getCurrentWater()) + "/" + String.valueOf(DBWater.getMaxWater()));


		if (DBWater.getCurrentWater() == DBWater.getMaxWater()) {
			Toast.makeText(this, "You have reached your water limit for the day. Good job!", Toast.LENGTH_SHORT).show();
		}
		if (DBWater.getCurrentWater() > DBWater.getMaxWater()) {
			Toast.makeText(this, "You've already reached your limit, slow down!", Toast.LENGTH_SHORT).show();
		}

	}


}
