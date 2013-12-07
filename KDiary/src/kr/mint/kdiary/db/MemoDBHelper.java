package kr.mint.kdiary.db;

import android.content.*;
import android.database.sqlite.*;

public class MemoDBHelper extends SQLiteOpenHelper
{
	SQLiteDatabase db;
	private static final String DB_NAME = "kmemo.db";
	
	
	public MemoDBHelper(Context context)
	{
		super(context, DB_NAME, null, 1);
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE memo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, date TEXT);");
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS mome");
		onCreate(db);
	}
}
