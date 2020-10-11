package com.example.activity;

import com.example.contacts.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RingActivity extends Activity {
	static String number;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ring);
		number="";
		tv=(TextView)findViewById(R.id.call_num);
	}
	public void OnClick(View v)
	{
		switch(v.getId())
		{
		case R.id.one:
			if(number.length()<14)
			number=number+"1";
			tv.setText(number);
			//Toast.makeText(RingActivity.this,number.substring(0, number.length()-1), Toast.LENGTH_LONG).show();
			break;
		case R.id.two:
			if(number.length()<14)
			number=number+"2";
			tv.setText(number);
			break;
		case R.id.three:
			if(number.length()<14)
			number=number+"3";
			tv.setText(number);
			break;
		case R.id.four:
			if(number.length()<14)
			number=number+"4";
			tv.setText(number);
			break;
		case R.id.five:
			if(number.length()<14)
			number=number+"5";
			tv.setText(number);
			break;
		case R.id.six:
			if(number.length()<14)
			number=number+"6";
			tv.setText(number);
			break;
		case R.id.seven:
			if(number.length()<14)
			number=number+"7";
			tv.setText(number);
			break;
		case R.id.eight:
			if(number.length()<14)
			number=number+"8";
			tv.setText(number);
			break;
		case R.id.nine:
			if(number.length()<14)
			number=number+"9";
			tv.setText(number);
			break;
		case R.id.zero:
			if(number.length()<14)
			number=number+"0";
			tv.setText(number);
			break;
		case R.id.star:
			if(number.length()<14)
			number=number+"*";
			tv.setText(number);
			break;
		case R.id.jing:
			if(number.length()<14)
			number=number+"#";
			tv.setText(number);
			break;
		case R.id.ring_call:
			Intent phoneintent=new Intent("android.intent.action.CALL",
					Uri.parse("tel:"+number));
			startActivity(phoneintent);
			break;
		case R.id.ring_add:
			Intent intent=new Intent(RingActivity.this,AddActivity.class);
			intent.putExtra("contact", number);
    		startActivity(intent);
			break;
		case R.id.del:
			int end=number.length();
			if(end>0){
			  number=number.substring(0,end-1);
			  tv.setText(number);
			  break;
			}
			else
				break;
		default:
			Toast.makeText(RingActivity.this,"³öÏÖ´íÎó", Toast.LENGTH_LONG).show();
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ring, menu);
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
