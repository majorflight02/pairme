package com.echolabstech.pairme;

import com.echolabstech.db.BeerPairingsDb;
import com.echolabstech.search.SearchBeerEntriesFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements BeerEntryFragment.BeerEntryFragmentCommunication
{
	public static final String TAG = "MainActivity-";
	public static final boolean DEBUG = true;
	
	public static final String BEER_ENTRY_FRAGMENT = "BEER_ENTRY_FRAGMENT";
	public static final String FOOD_ENTRY_FRAGMENT = "FOOD_ENTRY_FRAGMENT";
	public static final String SEARCH_BEERENTRY_FRAGMENT = "SEARCH_BEERENTRY_FRAGMENT";
	
	private final FragmentManager mFragmentManager = getFragmentManager();
	private FragmentTransaction mFragmentTransaction;
	private int mTransactions_id = 0;
	public static BeerPairingsDb mBeerPairingsDb;
	
	@Override
	public void messageFromGameWonFragment(String messageType, String message) 
	{
		if (DEBUG)
			Log.v(messageType, messageType+":"+message);
		
		if (messageType.contains(BeerEntryFragment.BeerEntryFragmentCommunication.MESSAGETYPE_MENU_SEARCH))
			loadFragment(SEARCH_BEERENTRY_FRAGMENT, message);
	}//messageFromGameWonFragment
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBeerPairingsDb = new BeerPairingsDb(this);
		BeerEntryFragment.addListeners(this);
	}//oncreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		loadFragment(BEER_ENTRY_FRAGMENT, null);
	}//onResume
	
	private void loadFragment(String choice, Object message)
	{
		final String LOCALTAG = TAG+"buildFragments";
		
		try
		{
			if (choice.contains(BEER_ENTRY_FRAGMENT))
				loadBeerEntryFragment();
			else if (choice.contains(FOOD_ENTRY_FRAGMENT))
				loadFoodEntryFragment();
			else if (choice.contains(SEARCH_BEERENTRY_FRAGMENT))
				loadSearchBeerentryFragment();
		}catch (Exception e) { Log.v(LOCALTAG,e.toString()); e.printStackTrace(); }
	}//buildFragments
	
	private void loadBeerEntryFragment()
	{		
		BeerEntryFragment fragment = new BeerEntryFragment();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.activity_main_fragmentcontainer_layout, fragment);
		//mFragmentTransaction.add(R.id.activity_main_fragmentcontainer_layout, fragment);
		mFragmentTransaction.addToBackStack(BEER_ENTRY_FRAGMENT);
		mTransactions_id = mFragmentTransaction.commit();
	}//loadPuzzlePackMenuFragment
	
	private void loadFoodEntryFragment()
	{		
		FoodEntryFragment fragment = new FoodEntryFragment();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.activity_main_fragmentcontainer_layout, fragment);
		//mFragmentTransaction.add(R.id.activity_main_fragmentcontainer_layout, fragment);
		mFragmentTransaction.addToBackStack(FOOD_ENTRY_FRAGMENT);
		mTransactions_id = mFragmentTransaction.commit();
	}//loadPuzzlePackMenuFragment
	
	private void loadSearchBeerentryFragment()
	{		
		SearchBeerEntriesFragment fragment = new SearchBeerEntriesFragment();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.activity_main_fragmentcontainer_layout, fragment);
		//mFragmentTransaction.add(R.id.activity_main_fragmentcontainer_layout, fragment);
		mFragmentTransaction.addToBackStack(SEARCH_BEERENTRY_FRAGMENT);
		mTransactions_id = mFragmentTransaction.commit();
	}//loadPuzzlePackMenuFragment
}//mainactivity
