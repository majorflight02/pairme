package com.echolabstech.search;

import java.util.ArrayList;
import com.echolabstech.pairme.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchBeerEntriesFragment extends Fragment
{
	public static final String TAG = "SearchBeerEntriesFragment-";
	public static final boolean DEBUG = true;
	
	private View mLayout;
	private static final ArrayList<GameWonFragmentCommunication> mListeners = new ArrayList<GameWonFragmentCommunication>();
	
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
		mLayout = inflater.inflate(R.layout.fragment_search_beer, container, false);	
		
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
}//FoodEntryFragmet
