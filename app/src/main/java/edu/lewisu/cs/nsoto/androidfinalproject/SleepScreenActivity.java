package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

public class SleepScreenActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep_screen);
		CustomAnalogClock customAnalogClock = (CustomAnalogClock) findViewById(R.id.analog_clock);
		customAnalogClock.setAutoUpdate(true);



	}
}

