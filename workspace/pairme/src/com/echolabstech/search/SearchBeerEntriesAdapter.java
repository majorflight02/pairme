package com.echolabstech.search;

import com.echolabstech.db.Beer;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SearchBeerEntriesAdapter extends BaseAdapter 
{
	public static final String TAG = "SearchBeerEntriesAdapter-";
	public static final boolean DEBUG = true;
	
	private SparseArray<Beer> mBeers;
	private ViewHolder mHolders;
	
	public class ViewHolder
	{
		
	}//ViewHolder
	
	public SearchBeerEntriesAdapter(SparseArray<Beer> beers)
	{
		mBeers = beers;
	}//SearchBeerEntriesAdapter
	
	@Override
	public int getCount() 
	{
		return mBeers.size();
	}//getCount

	@Override
	public Object getItem(int position) 
	{
		return mBeers.get(position);
	}//getItem

	@Override
	public long getItemId(int position) 
	{
		return 0;
	}//getItemId

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		return null;
	}//getView
}//SearchBeerEntriesAdapter
