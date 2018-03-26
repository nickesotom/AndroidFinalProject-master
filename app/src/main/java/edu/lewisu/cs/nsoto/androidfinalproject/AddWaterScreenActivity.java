package edu.lewisu.cs.nsoto.androidfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddWaterScreenActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_water_screen);
	}

	public void OnClickReturnToWaterScreen(View view) {
		Intent returnToWaterPage = new Intent(this, WaterScreenActivity.class);
		this.finish();
	}
}
