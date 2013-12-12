package kr.mint.kdiary;

import java.util.*;

import kr.mint.kdiary.bean.*;
import kr.mint.kdiary.db.*;
import android.app.*;
import android.app.AlertDialog.Builder;
import android.content.*;
import android.content.DialogInterface.OnClickListener;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class KDiaryActivity extends Activity
{
	private MemoDBController controller;
	private ArrayList<String> titleList;
	private ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_list);
		list = (ListView) findViewById(R.id.memo_list);
		ArrayAdapter<String> adapter;
		controller = new MemoDBController(KDiaryActivity.this);
		titleList = new ArrayList<String>();
		
		if (controller.getMemoList().isEmpty())
		{
			Intent intent = new Intent(KDiaryActivity.this, KDiaryEditActivity.class);
			startActivity(intent);
			finish();
			return;
		}
		for (DataBean dataBean : controller.getMemoList())
		{
			titleList.add(dataBean.getTitle());
		}
		adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, titleList);
		
		list.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
			{
				Builder dialog = new AlertDialog.Builder(KDiaryActivity.this);
				dialog.setTitle(R.string.question)
				.setMessage(R.string.question_del)
				.setNegativeButton(R.string.del, new OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						controller.deleteMemo(position);
					}
				})
				.show();
				return false;
			}
		});
		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				
			}
		});
		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, 0, Menu.NONE, R.string.add).setIcon(android.R.drawable.ic_menu_add);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = new Intent(KDiaryActivity.this, KDiaryEditActivity.class);
		startActivity(intent);
		finish();
		return super.onOptionsItemSelected(item);
	}
}
	
