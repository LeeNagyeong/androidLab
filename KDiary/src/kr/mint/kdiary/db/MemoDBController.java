package kr.mint.kdiary.db;

import java.util.*;

import kr.mint.kdiary.bean.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class MemoDBController
{
	private SQLiteDatabase db;
	private MemoDBHelper dbHelper;
	
	
	public MemoDBController(Context context)
	{
		dbHelper = new MemoDBHelper(context);
	}
	
	
	public void insertMemo(String title, String content, String date)
	{
		ContentValues row;
		db = dbHelper.getWritableDatabase();
		row = new ContentValues();
		row.put("title", title);
		row.put("content", content);
		row.put("date", date);
		db.insert("memo", null, row);
		dbHelper.close();
	}
	
	
	public void updateMemo(int id, String title, String content, String date)
	{
		String[] whereArgs = { String.valueOf(id) };
		ContentValues row;
		db = dbHelper.getWritableDatabase();
		row = new ContentValues();
		row.put("title", title);
		row.put("content", content);
		row.put("date", date);
		db.update("memo", row, "_id=?", whereArgs);
		dbHelper.close();
	}
	
	
	public void deleteMemo(int id)
	{
		String[] whereArgs = { String.valueOf(id) };
		db = dbHelper.getWritableDatabase();
		db.delete("memo", "_id=?", whereArgs);
		dbHelper.close();
	}
	
	
	public ArrayList<DataBean> getMemoList()
	{
		db = dbHelper.getReadableDatabase();
		Cursor cursor;
		cursor = db.rawQuery("SELECT * FROM memo", null);
		cursor.moveToFirst();
		
		ArrayList<DataBean> results = new ArrayList<DataBean>();
		while (!cursor.isAfterLast())
		{
			DataBean dataBean = new DataBean();
			dataBean.setId(cursor.getString(cursor.getColumnIndex("_id")));
			dataBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			dataBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
			dataBean.setDate(cursor.getString(cursor.getColumnIndex("date")));
			results.add(dataBean);
			cursor.moveToNext();
		}
		
		cursor.close();
		dbHelper.close();
		return results;
	}
}
