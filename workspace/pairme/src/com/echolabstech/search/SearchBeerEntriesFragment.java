package com.echolabstech.search;

import java.util.ArrayList;

import com.echolabstech.db.Beer;
import com.echolabstech.pairme.MainActivity;
import com.echolabstech.pairme.R;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchBeerEntriesFragment extends Fragment implements OnClickListener, TextWatcher
{
	public static final String TAG = "SearchBeerEntriesFragment-";
	public static final boolean DEBUG = true;
	
	private View mLayout;
	private static final ArrayList<GameWonFragmentCommunication> mListeners = new ArrayList<GameWonFragmentCommunication>();
	private EditText mSearchEditText;
	private Button mSearchButton;
	
	public interface GameWonFragmentCommunication
	{
		public static final String MESSAGETYPE_GAME_WON = "MESSAGETYPE_GAME_WON";
		public static final String MESSAGE_PUZZLE = "MESSAGE_PUZZLE";
		
		public void messageFromGameWonFragment(String messageType, String message);
	}//PuzzlePackMenuFragmentCommunication
	
	public static void addListeners(GameWonFragmentCommunication listener)
	{
		mListeners.add(listener);
	}//addListener
	
	public static void notifyListeners(String configuration, String message)
	{
		for (GameWonFragmentCommunication listener : mListeners)
			listener.messageFromGameWonFragment(configuration, message);
	}//notifyListeners
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		final String LOCALTAG = TAG+"onCreate";
		
		if (DEBUG)
			Log.v(LOCALTAG, "start");
	}//onCreate

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final String LOCALTAG = TAG+"onCreateView";
		
		mLayout = inflater.inflate(R.layout.fragment_search_beer, container, false);	
		
		mSearchEditText = (EditText) mLayout.findViewById(R.id.fragment_search_beer_search_edittext);
		mSearchEditText.addTextChangedListener(this);
		
		mSearchButton = (Button) mLayout.findViewById(R.id.fragment_search_beer_search_button);
		mSearchButton.setOnClickListener(this);
		
		return mLayout;		
	}//onCreateView
	
	@Override
	public void onStart()
	{
		super.onStart();
		final String LOCALTAG = TAG+"onStart";
		
		if (DEBUG)
			Log.v(LOCALTAG, "start");
	}//onResume	
	
	@Override
	public void onResume()
	{
		super.onResume();
		final String LOCALTAG = TAG+"onResume";
		
		if (DEBUG)
			Log.v(LOCALTAG, "resume");
	}//onResume	
	
	@Override
	public void onClick(View v) 
	{
		searchBeerByName(mSearchEditText.getText().toString());
	}//onClick
	
	@Override
	public void afterTextChanged(Editable s) 
	{
		final String LOCALTAG = TAG+"afterTextChanged";
		
		if (DEBUG)
			Log.v(LOCALTAG, s.toString());
	}//afterTextChanged

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) 
	{
		final String LOCALTAG = TAG+"beforeTextChanged";
		
		if (DEBUG)
			Log.v(LOCALTAG, s.toString());
	}//beforeTextChanged

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) 
	{
		final String LOCALTAG = TAG+"onTextChanged";
		
		if (DEBUG)
			Log.v(LOCALTAG, s.toString());
	}//onTextChanged
	
	private void searchBeerByName(String search)
	{
		final String LOCALTAG = TAG+"onStart";
		
		SparseArray<Beer> beers = new SparseArray<Beer>();
		beers = MainActivity.mBeerPairingsDb.getBeerRecordBySearchString(search);
		Log.v(LOCALTAG, "number of results:"+beers.size());
	}//searchBeerByName
	
}//FoodEntryFragmet
