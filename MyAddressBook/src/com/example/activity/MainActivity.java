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
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView demo_list;
	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		demo_list=(ListView)findViewById(R.id.listview);
		
		ContectAdapter con=new ContectAdapter(DatatoPerson());
		demo_list.setAdapter(con);
		
		demo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
				// TODO Auto-generated method stub
				MessageDAO md=new MessageDAO(MainActivity.this);
				Cursor cur=md.Query();
				cur.moveToPosition(position);
				Intent intent=new Intent(MainActivity.this,ShowActivity.class);
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
		demo_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			
				
				
				showActionMenu(arg1,position);
                
				return true;
			}
		});
		et=(EditText)findViewById(R.id.et_search);
		TextWatcher watcher=new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				
				String str=" name like '%"+s.toString()+"%'";
				ContectAdapter con;
				if(str==null)
				    con=new ContectAdapter(DatatoPerson());
				else
					con=new ContectAdapter(SearchPerson(str));
				demo_list.setAdapter(con);
				//Toast.makeText(MainActivity.this,s.toString(), Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			
		};
		et.addTextChangedListener(watcher);
	}

	 public void onClick(View v)
	    { 
	    	switch(v.getId()){
	    	case R.id.ring:
	    		Intent intent=new Intent(MainActivity.this,RingActivity.class);
	    		startActivity(intent);
	    	    break;
	    	case R.id.mygroup:
	    		showGroupMenu((Button)findViewById(R.id.mygroup));
	    		break;
	    	}
	    	
	    }
	 private void showGroupMenu(View view) {
		 // View当前PopupMenu显示的相对View的位置
		 PopupMenu popupMenu = new PopupMenu(this, view);
		 // menu布局
		 popupMenu.getMenuInflater().inflate(R.menu.group, popupMenu.getMenu());
		 // menu的item点击事件
		 popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		  @Override
		  public boolean onMenuItemClick(MenuItem item) {
		  Intent intent=new Intent(MainActivity.this,GroupActivity.class);
		  intent.putExtra("grouptitle", item.getTitle());
		  startActivity(intent);
		   return true;
		  }
		 });
		 popupMenu.show();
		 }
	   private void showActionMenu(View view,int position) {
		 // View当前PopupMenu显示的相对View的位置
		 PopupMenu popupMenu = new PopupMenu(this, view);
		 // menu布局
	     final int pos=position;
		 popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
		 // menu的item点击事件
		 popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		  @Override
		  public boolean onMenuItemClick(MenuItem item) {
			    MessageDAO md;
			    Cursor cur;
			    Uri uri;
				switch(item.getItemId())
				{
				case R.id.action_call:
					//隐藏该对话框
					md=new MessageDAO(MainActivity.this);
				    cur=md.Query();
					cur.moveToPosition(pos);
					String str=cur.getString(2);
					Intent phoneintent=new Intent("android.intent.action.CALL",
							Uri.parse("tel:"+str));
					startActivity(phoneintent);
					break;
				case R.id.action_sms:
					md=new MessageDAO(MainActivity.this);
				    cur=md.Query();
					cur.moveToPosition(pos);
					String num=cur.getString(2);
					uri=Uri.parse("smsto:"+num);
		    		Intent mesintent=new Intent(Intent.ACTION_SENDTO,uri);
		    		startActivity(mesintent);
		    		break;
				case R.id.action_email:
					uri=Uri.parse("mailto:2748375177@qq.com");
					Intent mailintent = new Intent(Intent.ACTION_SENDTO, uri);
					startActivity(mailintent);
					break;
				case R.id.action_edit:
					md=new MessageDAO(MainActivity.this);
					cur=md.Query();
					cur.moveToPosition(pos);
				    Intent intent=new Intent(MainActivity.this,ShowActivity.class);
					intent.putExtra("name", cur.getString(1));
					intent.putExtra("mobile_num",cur.getString(2));
					intent.putExtra("family_num",cur.getString(3));
					intent.putExtra("office_num",cur.getString(4));
					intent.putExtra("groups",cur.getString(5));
					intent.putExtra("address",cur.getString(7));
					startActivity(intent);
					break;
				case R.id.action_add:
					Intent addintent=new Intent(MainActivity.this,AddActivity.class);
					addintent.putExtra("contact", "");
		    		startActivity(addintent);
		    		break;
				default:
					//使用Toast显示用户单击的菜单项
					Toast.makeText(MainActivity.this, "您单击了【"+item.getTitle()+"】菜单项",Toast.LENGTH_SHORT).show();
				    break;
				}
				
				return false;
			}
		 });
		 popupMenu.show();
		 }

	 public List<Person> DatatoPerson(){
			List<Person> datalist=new ArrayList<>();
			MessageDAO md=new MessageDAO(MainActivity.this);
			Cursor cur=md.Query();
			while(cur.moveToNext())
			{
				Person p=new Person();
				p.setName(cur.getString(1));
				p.setMobilephone(cur.getString(2));
			    datalist.add(p);
			   
			}
			return datalist;
		}
		public List<Person> SearchPerson(String s){
			List<Person> datalist=new ArrayList<>();
			MessageDAO md=new MessageDAO(MainActivity.this);
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
		getMenuInflater().inflate(R.menu.main, menu);
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
