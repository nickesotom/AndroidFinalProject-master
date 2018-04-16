package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WaterScreenActivity extends AppCompatActivity {
	Button mAddWater;
	TextView waterAmount;
	ProgressBar mProgressBar;

	private int consumedWater = 0;
	final int PROGRESS_BAR_INCREMENT = consumedWater;
	private int currentWater = 0;
	private int maxWater = 70;
	private int waterScore = 0;
	Spinner mSpinner;

	WaterHelper mWater = new WaterHelper(currentWater, maxWater);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_screen);

		mAddWater = (Button) findViewById(R.id.fill_water_button);
		waterAmount = (TextView) findViewById(R.id.water_consumed);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

		mAddWater.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new AlertDialog.Builder(WaterScreenActivity.this);

				View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

				mBuilder.setTitle("Specify an amount of water");
				mSpinner = (Spinner) mView.findViewById(R.id.spinner);
				ArrayAdapter<String> waterAmounts = new ArrayAdapter<String>(WaterScreenActivity.this,
						android.R.layout.simple_spinner_item,
						getResources().getStringArray(R.array.waterValues));

				waterAmounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				mSpinner.setAdapter(waterAmounts);



				mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Enter a water amount…")) {
							Toast.makeText(WaterScreenActivity.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
							addingWater();
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
	}
	private void addingWater() {
		if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("8 fl oz.")) {
			consumedWater = consumedWater + 8;
			mProgressBar.incrementProgressBy(consumedWater);
			waterScore = waterScore + consumedWater;
			waterAmount.setText(waterScore + "/"+ maxWater);
			consumedWater = 0;
			Log.d("consumed", "consumed value:" +consumedWater);
		}
		else if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("12 fl oz.")) {
			consumedWater = consumedWater + 12;
			mProgressBar.incrementProgressBy(consumedWater);
			waterScore = waterScore + consumedWater;
			waterAmount.setText(waterScore + "/"+ maxWater);
			consumedWater = 0;
			Log.d("consumed", "consumed value:" +consumedWater);
		}
		else if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("18 fl oz.")) {
			consumedWater = consumedWater + 18;
			mProgressBar.incrementProgressBy(consumedWater);
			waterScore = waterScore + consumedWater;
			waterAmount.setText(waterScore + "/"+ maxWater);
			consumedWater = 0;
			Log.d("consumed", "consumed value:" +consumedWater);
		}
		else if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("24 fl oz.")) {
			consumedWater = consumedWater + 24;
			mProgressBar.incrementProgressBy(consumedWater);
			waterScore = waterScore + consumedWater;
			waterAmount.setText(waterScore + "/"+ maxWater);
			consumedWater = 0;
			Log.d("consumed", "consumed value:" +consumedWater);
		}
	}


	/**
	 * Created by Nick on 4/13/2018.
	 */

	public static class WaterHelper {
		int currentWater = 0;
		int maxWater = 0;

		public WaterHelper(int currentWater, int maxWater) {
			this.currentWater = currentWater;
			this.maxWater = maxWater;
		}

		public int getCurrentWater() {
			return currentWater;
		}

		public void setCurrentWater(int currentWater) {
			this.currentWater = currentWater;
		}

		public int getMaxWater() {
			return maxWater;
		}

		public void setMaxWater(int maxWater) {
			this.maxWater = maxWater;
		}
	}
}
