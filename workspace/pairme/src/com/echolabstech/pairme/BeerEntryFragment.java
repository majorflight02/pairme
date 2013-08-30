package com.echolabstech.pairme;

import java.util.ArrayList;

import com.echolabstech.db.Beer;
import com.echolabstech.db.BeerPairingsDb;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class BeerEntryFragment extends Fragment implements OnClickListener, TextWatcher  
{
	public static final String TAG = "BeerEntryFragment-";
	public static final boolean DEBUG = true;
	
	private View mLayout;
	private static final ArrayList<BeerEntryFragmentCommunication> mListeners = new ArrayList<BeerEntryFragmentCommunication>();
	private EditText mBeerName;
	private EditText mType;
	private EditText mHead;
	private EditText mAroma;
	private EditText mAttack;
	private EditText mPrimary;
	private EditText mSecondary;
	private EditText mTertiary;
	private EditText mFinal;
	private EditText mAftertaste;
	private EditText mBody;
	private Button mSubmit;
	private Button mSearch;
	
	public interface BeerEntryFragmentCommunication
	{
		public static final String MESSAGETYPE_MENU_SEARCH = "MESSAGETYPE_MENU_SEARCH";
		
		public void messageFromGameWonFragment(String messageType, String message);
	}//PuzzlePackMenuFragmentCommunication
	
	public static void addListeners(BeerEntryFragmentCommunication listener)
	{
		mListeners.add(listener);
	}//addListener
	
	public static void notifyListeners(String messageType, String message)
	{
		for (BeerEntryFragmentCommunication listener : mListeners)
			listener.messageFromGameWonFragment(messageType, message);
	}//notifyListeners
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		final String LOCALTAG = TAG+"onCreate";
		
		if (DEBUG)
			Log.v(LOCALTAG, "onCreate");
	}//onCreate

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mLayout = inflater.inflate(R.layout.fragment_beer_entry, container, false);	
		
		mBeerName = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_beername_edittext);
		mType = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_type_edittext);
		mHead = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_head_edittext);
		mAroma = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_aroma_edittext);
		mAttack = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_attack_edittext);
		mPrimary = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_primary_edittext);
		mSecondary = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_secondary_edittext);
		mTertiary = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_tertiary_edittext);
		mFinal = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_final_edittext);
		mAftertaste = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_aftertaste_edittext);
		mBody = (EditText) mLayout.findViewById(R.id.fragment_beer_entry_body_edittext);
		mSubmit = (Button) mLayout.findViewById(R.id.fragment_beer_entry_submit_btn);
		mSearch = (Button) mLayout.findViewById(R.id.fragment_beer_entry_search_btn);
		
		setListeners();
		
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
	public void onPause()
	{
		super.onPause();
		final String LOCALTAG = TAG+"onPause";
		
		saveEntry();
	}//onPause
	
	private long saveEntry()
	{
		final String LOCALTAG = TAG+"saveEntry";
		
		Beer beer = new Beer();
	
		beer.mName = (mBeerName.getText().toString());
		beer.mType = (mType.getText().toString());
		beer.mHead = (mHead.getText().toString());
		beer.mAroma = (mAroma.getText().toString());
		beer.mAttack = (mAttack.getText().toString());
		beer.mPrimary = (mPrimary.getText().toString());
		beer.mSecondary = (mSecondary.getText().toString());
		beer.mTertiary = (mTertiary.getText().toString());
		beer.mFinal = (mFinal.getText().toString());
		beer.mAfterTaste = (mAftertaste.getText().toString());
		beer.mBody = (mBody.getText().toString());
		
		return MainActivity.mBeerPairingsDb.writeBeerRecordByName(beer);
	}//saveEntry
	
	private void setListeners()
	{
		final String LOCALTAG = TAG+"setListeners";
		
		mSubmit.setOnClickListener(this);
		mSearch.setOnClickListener(this);
		
		mBeerName.addTextChangedListener(this);
	}//setListeners
	
	@Override
	public void onClick(View v)
	{
		final String LOCALTAG = TAG+"onClick";
		
		if (DEBUG)
			Log.v(LOCALTAG, "click submit");
		
		if (v.getId() == mSubmit.getId())
		{
			long id = saveEntry();
			
			if (DEBUG)
			{
				Log.v(LOCALTAG, "saveEntry id:"+id);
				Beer beer = MainActivity.mBeerPairingsDb.getBeerRecordById(id);
				Log.v(LOCALTAG, "name:"+beer.mName+", row:"+beer.mId);
			}//if debug
		}//if submit button pressed
		else if (v.getId() == mSearch.getId())
		{
			notifyListeners(BeerEntryFragmentCommunication.MESSAGETYPE_MENU_SEARCH, getSearchString());
			/*
			ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
			records = MainActivity.mBeerPairingsDb.getBeerRecordBySearchString(getSearchString());
			Log.v(LOCALTAG, "number of results:"+records.size());
			*/
		}//else if search
	}//oncClick

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

	private String getSearchString()
	{
		return mBeerName.getText().toString();
	}//getSearchString
}//BeerEntryFragment

