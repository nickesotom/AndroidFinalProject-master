package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class WaterScreenActivity extends AppCompatActivity {
	Button mAddWater;
	private double currentWater = 0.0;
	private double maxWater = 64.0;
	private String[] waterLevels = new String [4];
	WaterHelper mWater = new WaterHelper(currentWater, maxWater);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_screen);

		mAddWater = (Button) findViewById(R.id.fill_water_button);
		mAddWater.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new AlertDialog.Builder(WaterScreenActivity.this);
				int addingToArray = 0;

				View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

				mBuilder.setTitle("Specify an amount of water");


				for(int i = 0; i < waterLevels.length; i++) {
					addingToArray++;
					waterLevels[i] = "" + addingToArray;
				}
				final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
				//final int selection = Integer.parseInt(mSpinner.getSelectedItem().toString());
				ArrayAdapter<String> waterAmounts = new ArrayAdapter<String>(WaterScreenActivity.this,
						android.R.layout.simple_spinner_item,
						getResources().getStringArray(R.array.waterValues));

				//waterAmounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				mSpinner.setAdapter(waterAmounts);



				mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Water Amount In FL OZ…")) {
							Toast.makeText(WaterScreenActivity.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
							Log.d("app", "value:" +waterLevels);
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


	/**
	 * Created by Nick on 4/13/2018.
	 */

	public static class WaterHelper {
		double currentWater = 0;
		double maxWater = 0;

		public WaterHelper(double currentWater, double maxWater) {
			this.currentWater = currentWater;
			this.maxWater = maxWater;
		}

		public double getCurrentWater() {
			return currentWater;
		}

		public void setCurrentWater(double currentWater) {
			this.currentWater = currentWater;
		}

		public double getMaxWater() {
			return maxWater;
		}

		public void setMaxWater(double maxWater) {
			this.maxWater = maxWater;
		}
	}
}
