package edu.lewisu.cs.nsoto.androidfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class HomePageActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

	}

	public void onClickWater(View view) {
		Intent toWaterPage = new Intent(this, WaterScreenActivity.class);
		startActivity(toWaterPage);
	}

	public void OnClickSleep(View view) {
		Intent toSleepPage = new Intent(this, SleepScreenActivity.class);
		startActivity(toSleepPage);
	}

	public void OnClickFood(View view) {
		Intent toFoodPage = new Intent(this, FoodScreenActivity.class);
		startActivity(toFoodPage);
	}
}