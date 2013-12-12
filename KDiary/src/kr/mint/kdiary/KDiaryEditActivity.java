package kr.mint.kdiary;

import java.text.*;
import java.util.*;

import kr.mint.kdiary.db.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class KDiaryEditActivity extends Activity
{
	private EditText title, content;
	private Button addBtn;
	private MemoDBController controller;
	private String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_edit);
		
		title = (EditText) findViewById(R.id.edit_title);
		content = (EditText) findViewById(R.id.edit_content);
		addBtn = (Button) findViewById(R.id.add_btn);
		controller = new MemoDBController(KDiaryEditActivity.this);
		
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		date = dateFormat.toString();
		
		
		addBtn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				controller.insertMemo(title.getText().toString(), content.getText().toString(),date);
				Intent intent = new Intent(KDiaryEditActivity.this, KDiaryActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
}
