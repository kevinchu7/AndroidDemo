package com.example.activity;

import com.example.contacts.R;
import com.example.entity.Person;
import com.example.util.MessageDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * 
 * @author zhukai
 *
 */
public class AddActivity extends Activity {
    MessageDAO md;
	ImageView save,reback;
	Intent intent;
	EditText et_name,et_num,et_family,et_office,et_addr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		et_name=(EditText)findViewById(R.id.username);
		et_num=(EditText)findViewById(R.id.mobilephone);
		et_family=(EditText)findViewById(R.id.familyphone);
		et_office=(EditText)findViewById(R.id.officephone);
		et_addr=(EditText)findViewById(R.id.address);
		
		intent=getIntent();
		et_num.setText(intent.getStringExtra("contact"));
		
		save=(ImageView)findViewById(R.id.save);
	    reback=(ImageView)findViewById(R.id.img_return);
		md=new MessageDAO(this);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String name = et_name.getText().toString();//将et_name读取的文本转化为字符串
				String num=et_num.getText().toString();
				Spinner group=(Spinner)findViewById(R.id.group1);
				
				if(name.trim().equals("")) {//如果读取的字符串为空   
					Toast.makeText(AddActivity.this, "姓名不能为空", Toast.LENGTH_LONG).show();  
					return;
				}   //从表单上获取数据   
				else if(num.trim().equals("")) {//如果读取的字符串为空   
					Toast.makeText(AddActivity.this, "号码不能为空", Toast.LENGTH_LONG).show();  
					return;
				}   //从表单上获取数据   
				else if(num.length()!=7&&num.length()!=11){
					Toast.makeText(AddActivity.this, "号码格式不符", Toast.LENGTH_LONG).show();  
					return;
				}
					
				Person user = new Person();//在user类中定义了一个user函数  
				if(md.exists(num)){
					Toast.makeText(AddActivity.this, "此号码已存在", Toast.LENGTH_LONG).show();
					return;
				}
				user.setName(name);
				user.setMobilephone(num);
				user.setFamilyphone(et_family.getText().toString());
				user.setAddress(et_addr.getText().toString());
				user.setGroup(group.getSelectedItem().toString());
				user.setOfficephone(et_office.getText().toString());
				if(md.Insert(user))
					Toast.makeText(AddActivity.this,"联系人已添加", Toast.LENGTH_LONG).show();
				
			}
		});
		reback.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				Intent i=new Intent(AddActivity.this,MainActivity.class);
	    		startActivity(i);
	    		finish();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
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
