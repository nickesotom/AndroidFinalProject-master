package edu.lewisu.cs.nsoto.androidfinalproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class RecipeDetailActivity extends AppCompatActivity {
	public static TextView data;
	TextView recipeItem;

	Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);
		//data = (TextView) findViewById(R.id.textView2);
		recipeItem = (TextView) findViewById(R.id.textView2);
		Bundle mBundle = getIntent().getExtras();

		if(mBundle != null) {
			recipeItem.setText(mBundle.getInt("recipeItems"));
		}

	}
}
