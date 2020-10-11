package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ContectAdapter;
import com.example.contacts.R;
import com.example.entity.Person;
import com.example.util.MessageDAO;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class GroupActivity extends Activity {

	ListView demo_list;
	TextView gt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		gt=(TextView)findViewById(R.id.group_title);
        demo_list=(ListView)findViewById(R.id.group_list);
        Intent intent=getIntent();
		String str=intent.getStringExtra("grouptitle");
		gt.setText(str);
		str="groups='"+str+"'";
		ContectAdapter con=new ContectAdapter(SearchPerson(str));
		demo_list.setAdapter(con);
		demo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
				
				MessageDAO md=new MessageDAO(GroupActivity.this);
				Cursor cur=md.Query();
				cur.moveToPosition(position);
				Intent intent=new Intent(GroupActivity.this,ShowActivity.class);
				intent.putExtra("name", cur.getString(1));
				intent.putExtra("mobile_num",cur.getString(2));
				intent.putExtra("family_num",cur.getString(3));
				intent.putExtra("office_num",cur.getString(4));
				intent.putExtra("groups",cur.getString(5));
				intent.putExtra("address",cur.getString(7));
				startActivity(intent);
				//Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
			}
		});
	}
	public List<Person> SearchPerson(String s){
		List<Person> datalist=new ArrayList<>();
		MessageDAO md=new MessageDAO(GroupActivity.this);
		Cursor cur=md.ConditionQuery(s);
		while(cur.moveToNext())
		{
			Person p=new Person();
			p.setName(cur.getString(1));
			p.setMobilephone(cur.getString(2));
		    datalist.add(p);
		   // Toast.makeText(ShowtableActivity.this, p.name+p.mobilephone, Toast.LENGTH_LONG).show();
		}
		return datalist;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
