package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Nick on 5/5/2018.
 */

public class RecipeAdapter extends ArrayAdapter<String> {

	private String[] names;
	private int[] recipes;
	private Context mContext;

	public RecipeAdapter(@NonNull Context context, String[] recipeNames, int[] recipePics) {
		super(context, R.layout.listview_item);
		this.names = recipeNames;
		this.recipes = recipePics;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return names.length;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		ViewHolder mViewHolder = new ViewHolder();
		if(convertView == null) {

			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.listview_item, parent, false);
			mViewHolder.mRecipe = (ImageView) convertView.findViewById(R.id.recipePhoto);
			mViewHolder.mName = (TextView) convertView.findViewById(R.id.recipeName);
			convertView.setTag(mViewHolder);
		}
		else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		mViewHolder.mRecipe.setImageResource(recipes[position]);
		mViewHolder.mName.setText(names[position]);
		return convertView;
	}
	static class ViewHolder {
		ImageView mRecipe;
		TextView mName;
	}
}
