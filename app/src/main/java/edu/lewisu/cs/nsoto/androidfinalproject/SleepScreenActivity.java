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

public class SleepScreenActivity extends AppCompatActivity {
	Time mTime;

	Handler mHandler;
	Runnable mRunnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep_screen);
		mTime = new Time();
		mRunnable = new Runnable() {
			@Override
			public void run() {
				mTime.setToNow();
				DrawingView dv = new DrawingView(SleepScreenActivity.this, mTime.hour,mTime.minute, mTime.second,mTime.weekDay,mTime.monthDay,getBatteryLevel());
				setContentView(dv);
				mHandler.postDelayed(mRunnable, 1000);
			}
		};
		mHandler = new Handler();
		mHandler.postDelayed(mRunnable, 1000);

	}
	public float getBatteryLevel() {
		Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		if(level == -1 || scale == -1) {
			return 50.0f;
		}
		return ((float) level / (float)scale*100f);
	}

	public class DrawingView extends View {
		Paint mBackgroundPaint, mTextPaint, mTextPaintBack;
		Typeface tf;
		int hours, minutes, seconds, weekday, date;
		float battery;

		public DrawingView(Context context, int hours, int minutes, int seconds, int weekday, int date, float battery) {
			super(context);
			tf = Typeface.createFromAsset(getAssets(),"digital-7.ttf");
			mBackgroundPaint = new Paint();
			mBackgroundPaint.setColor(000000);

			mTextPaint = new Paint();
			mTextPaint.setColor(000000);
			mTextPaint.setAntiAlias(true);
			mTextPaint.setTextAlign(Paint.Align.CENTER);
			mTextPaint.setTextSize(48);
			mTextPaint.setTypeface(tf);

			mTextPaintBack = new Paint();

			mTextPaintBack.setColor(000000);
			mTextPaintBack.setAntiAlias(true);
			mTextPaintBack.setTextAlign(Paint.Align.CENTER);
			mTextPaintBack.setTextSize(30);
			mTextPaintBack.setTypeface(tf);

			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
			this.weekday = weekday;
			this.date = date;
			this.battery = battery;





		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			float width = canvas.getWidth();
			float height = canvas.getHeight();
			canvas.drawRect(0,0, width, height, mBackgroundPaint);

			float centerX = width / 2f;
			float centerY = height /2f;

			int cur_hour = hours;
			String cur_ampm = "AM";
			if(cur_hour == 0) {
				cur_hour = 12;
			}
			if(cur_hour > 12) {
				cur_hour = cur_hour - 12;
				cur_ampm = "PM";
			}
			String text = String.format("%02d:%02d:02d", cur_hour, minutes, seconds);
			String dayOfWeek = "";
			if(weekday == 1) {
				dayOfWeek = "Monday";
			}
			else if(weekday == 2) {
				dayOfWeek = "Tuesday";
			}
			else if(weekday == 3) {
				dayOfWeek = "Wednesday";
			}
			else if(weekday == 4) {
				dayOfWeek = "Thursday";
			}
			else if(weekday == 5) {
				dayOfWeek = "Friday";
			}
			else if(weekday == 6) {
				dayOfWeek = "Saturday";
			}
			else if(weekday == 7) {
				dayOfWeek = "Sunday";
			}

			String text2 = String.format("Date: %s %d", dayOfWeek, date);
			String batteryLevel = "Battery: " + (int)battery + "%";
			canvas.drawText("00 00 00", centerX, centerY, mTextPaintBack);

			mTextPaint.setColor(getResources().getColor(R.color.black));
			mTextPaint.setTextSize(50);
			canvas.drawText(batteryLevel+ " " + text2, centerX, centerY+30, mTextPaint);





		}

	}
}
