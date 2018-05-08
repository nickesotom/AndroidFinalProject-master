package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.List;

public class FoodScreenActivity extends AppCompatActivity {

	Toolbar mToolbar;
	ListView mListView;


	int[] recipeNames = {R.string.recipe1,
			R.string.recipe2,
			R.string.recipe3,
			R.string.recipe4,
			R.string.recipe5,
			R.string.recipe6,
			R.string.recipe7,
			R.string.recipe8,
			R.string.recipe9,
			R.string.recipe10,
			R.string.recipe11};
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

	int[] recipeItems = {R.string.recipeItems1,
			R.string.recipeItems2,
			R.string.recipeItems3,
			R.string.recipeItems4,
			R.string.recipeItems5,
			R.string.recipeItems6,
			R.string.recipeItems7,
			R.string.recipeItems8,
			R.string.recipeItems9,
			R.string.recipeItems10,
			R.string.recipeItems11};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_screen);

		//mToolbar = (Toolbar) findViewById(R.id.toolbar);
		
		mListView = (ListView) findViewById(R.id.recipes);
		RecipeAdapter mAdapter = new RecipeAdapter(FoodScreenActivity.this, recipeNames, recipePhotos);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//	FetchJsonData process = new FetchJsonData();
			//	process.execute();
				Intent mIntent = new Intent(FoodScreenActivity.this, RecipeDetailActivity.class);
				mIntent.putExtra("recipeItems", recipeItems[position]);
				startActivity(mIntent);
			}
		});
	}
}
