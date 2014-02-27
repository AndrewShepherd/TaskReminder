package com.example.taskreminder;

import android.database.sqlite.SQLiteDatabase;

public class RemindersDbAdapter {
	private static final String DATABASE_NAME = "data";
	private static final String DATABASE_TABLE = "reminders";
	private static final int DATABASE_VERSION = 1;
	
	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_DATE_TIME = "reminder_date_time";
	public static final String KEY_ROWID = "_id";
	
	//private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

}
