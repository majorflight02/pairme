package com.echolabstech.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.util.SparseArray;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BeerPairingsDb extends SQLiteAssetHelper 
{
	public static final String TAG = "BeerPairingsDb-";
	public static final boolean DEBUG = true;
	
	private static final String DATABASE_NAME = "beerpairingsdb";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TBL_BEERENTRIES = "beer_entries";
	public static final String COL_ID = "id";
	public static final String COL_BEERNAME = "name";
	public static final String COL_TYPE = "type";
	public static final String COL_HEAD = "head";
	public static final String COL_AROMA = "aroma";
	public static final String COL_ATTACK = "taste_attack";
	public static final String COL_PRIMARY = "taste_primary";
	public static final String COL_SECONDARY = "taste_secondary";
	public static final String COL_TERTIARY = "taste_tertiary";
	public static final String COL_FINAL = "taste_final";
	public static final String COL_AFTERTASTE = "taste_aftertaste";
	public static final String COL_BODY = "body";
	
	public BeerPairingsDb(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// you can use an alternate constructor to specify a database location 
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
	}//BeerPairingsDb

	public SparseArray<Beer> getBeerRecordById(long id)
	{
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {COL_ID, COL_BEERNAME, COL_TYPE, COL_HEAD, COL_AROMA, COL_ATTACK, COL_PRIMARY, COL_SECONDARY,
				COL_TERTIARY, COL_FINAL, COL_AFTERTASTE, COL_BODY}; 
		String sqlTables = TBL_BEERENTRIES;
		String selection = COL_ID+" = "+id;
		SparseArray<Beer> beers = new SparseArray<Beer>();
		
		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, selection, null, null, null, null);

		c.moveToFirst();
		while (beers.size() < c.getColumnCount())
		{
			Beer beer = new Beer();
			beer.mId = c.getLong(c.getColumnIndex(COL_ID));
			beer.mName = c.getString(c.getColumnIndex(COL_BEERNAME));
			beer.mType = c.getString(c.getColumnIndex(COL_PRIMARY));
			beer.mHead = c.getString(c.getColumnIndex(COL_HEAD));
			beer.mAroma = c.getString(c.getColumnIndex(COL_AROMA));
			beer.mAttack = c.getString(c.getColumnIndex(COL_ATTACK));
			beer.mPrimary = c.getString(c.getColumnIndex(COL_PRIMARY));
			beer.mSecondary = c.getString(c.getColumnIndex(COL_SECONDARY));
			beer.mTertiary = c.getString(c.getColumnIndex(COL_TERTIARY));
			beer.mFinal = c.getString(c.getColumnIndex(COL_FINAL));
			beer.mAfterTaste = c.getString(c.getColumnIndex(COL_AFTERTASTE));
			beer.mBody = c.getString(c.getColumnIndex(COL_BODY));
			
			beers.append(beers.size(), beer);
		}//while all columns have not be accounted for
		
		return beers;
	}//getBeerRecordByName
	/*
	public ArrayList<ArrayList<String>> getBeerRecordBySearchString(String search)
	{
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String [] sqlSelect = {COL_ID, COL_BEERNAME, COL_TYPE, COL_HEAD, COL_AROMA, COL_ATTACK, COL_PRIMARY, COL_SECONDARY,
				COL_TERTIARY, COL_FINAL, COL_AFTERTASTE, COL_BODY}; 
		String sqlTables = TBL_BEERENTRIES;
		String selection = COL_BEERNAME+" LIKE '%"+search+"%'";
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
		ArrayList<String> array;
		
		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, selection, null, null, null, null);
		c.moveToFirst();
		
		do
		{
			array = new ArrayList<String>();
			
			array.add(c.getString(c.getColumnIndex(COL_ID)));
			array.add(c.getString(c.getColumnIndex(COL_BEERNAME)));
			array.add(c.getString(c.getColumnIndex(COL_TYPE)));
			array.add(c.getString(c.getColumnIndex(COL_HEAD)));
			array.add(c.getString(c.getColumnIndex(COL_AROMA)));
			array.add(c.getString(c.getColumnIndex(COL_ATTACK)));
			array.add(c.getString(c.getColumnIndex(COL_PRIMARY)));
			array.add(c.getString(c.getColumnIndex(COL_SECONDARY)));
			array.add(c.getString(c.getColumnIndex(COL_TERTIARY)));
			array.add(c.getString(c.getColumnIndex(COL_FINAL)));
			array.add(c.getString(c.getColumnIndex(COL_AFTERTASTE)));
			array.add(c.getString(c.getColumnIndex(COL_BODY)));
			
			records.add(array);
		}//while all columns have not be accounted for
		while (c.moveToNext());
		
		return records;
	}//getBeerRecordByName
	*/
	public long writeBeerRecordByName(Beer beer)
	{
		final String LOCALTAG = TAG+"writeBeerRecordByName";
		
		SQLiteDatabase db = getWritableDatabase();
		String sqlTables = TBL_BEERENTRIES;
		ContentValues cv = new ContentValues();
		String whereClause = COL_BEERNAME +" = '"+beer.mName+"'";
		long id = 0;
		
		id = beer.mId;
		cv.put(COL_BEERNAME, beer.mName);
		cv.put(COL_TYPE,beer.mType );
		cv.put(COL_HEAD, beer.mHead);
		cv.put(COL_AROMA, beer.mAroma);
		cv.put(COL_ATTACK, beer.mAttack);
		cv.put(COL_PRIMARY, beer.mPrimary);
		cv.put(COL_SECONDARY, beer.mSecondary);
		cv.put(COL_TERTIARY, beer.mTertiary);
		cv.put(COL_FINAL, beer.mFinal);
		cv.put(COL_AFTERTASTE, beer.mAfterTaste);
		cv.put(COL_BODY, beer.mBody);
		
		if (beer.mId > 0)
			db.update(sqlTables, cv, whereClause, null);
		else
			id = db.insert(sqlTables, null, cv);
		
		return id;
	}//writePuzzle
}//BeerPairingsDb
