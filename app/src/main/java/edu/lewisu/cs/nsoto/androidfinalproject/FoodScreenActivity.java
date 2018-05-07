package edu.lewisu.cs.nsoto.androidfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.List;

public class FoodScreenActivity extends AppCompatActivity {

	Toolbar mToolbar;
	ListView mListView;


	String[] recipeNames = {"Vegetarian Casserole", "Spicy Arrabiata Penne", "Jerk chicken with rice & peas", "Chicken Marengo", "Peach & Blueberry Grunt",
			"Chilli prawn linguine", "Chocolate Avocado Mousse", "New York cheesecake", "Vegan Chocolate Cake", "Vegan Lasagna", "Fettucine alfredo" };
	int[] recipePhotos = {R.drawable.cass,
			R.drawable.penne,
			R.drawable.jerkchicken,
			R.drawable.chickenmarengo,
			R.drawable.grunt,
			R.drawable.linguine,
			R.drawable.mousse,
			R.drawable.cheescake,
			R.drawable.vegancake,
			R.drawable.veganlasagna,
			R.drawable.alfredosmall};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_screen);

		//mToolbar = (Toolbar) findViewById(R.id.toolbar);
		
		mListView = (ListView) findViewById(R.id.recipes);
		RecipeAdapter mAdapter = new RecipeAdapter(FoodScreenActivity.this, recipeNames, recipePhotos);
		mListView.setAdapter(mAdapter);
	}
}
