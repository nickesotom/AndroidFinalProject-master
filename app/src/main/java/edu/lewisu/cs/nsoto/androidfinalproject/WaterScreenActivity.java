package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.DialogInterface;
//import android.graphics.PorterDuff;
import android.database.Cursor;
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

public class WaterScreenActivity extends AppCompatActivity {
	Button mAddWater;
	TextView waterAmount;
	ProgressBar mProgressBar;
	Spinner mSpinner;
	Button resetButton;
	private WaterModel water;
	private WaterModel DBWater;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_screen);

		final DBHandler db = new DBHandler(this);

		//db.deleteWater();


		water = new WaterModel(0, 64);
		db.addWater(water);
		resetButton = (Button) findViewById(R.id.reset_button);
		mAddWater = (Button) findViewById(R.id.fill_water_button);
		waterAmount = (TextView) findViewById(R.id.water_consumed);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

		//mAddWater.getBackground().setColorFilter(0x003182, PorterDuff.Mode.MULTIPLY);

		DBWater = db.getWaterAmount(1);


		mProgressBar.setMax((int)DBWater.getMaxWater());
		mProgressBar.setProgress((int)DBWater.getCurrentWater());
		waterAmount.setText(String.valueOf(DBWater.getCurrentWater()) + "/" + String.valueOf(DBWater.getMaxWater()));

		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
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
	}

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
