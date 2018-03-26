package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WaterScreenActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_screen);
	}

	public void onClickAddWater(View view) {
		Intent toAddWaterPage = new Intent(this, AddWaterScreenActivity.class);
		startActivity(toAddWaterPage);

	}
}
