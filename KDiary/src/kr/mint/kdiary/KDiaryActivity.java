package kr.mint.kdiary;

import java.util.*;

import kr.mint.kdiary.bean.*;
import kr.mint.kdiary.db.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

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
		list.setAdapter(adapter);
	}
}
