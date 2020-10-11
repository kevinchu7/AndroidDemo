package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.contacts.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends Activity {

	TextView text;
	EditText et;
	Button bt;
	
	 private mServiceReceiver mReceiver01, mReceiver02;
	 private static String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
	 private static String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		bt=(Button)findViewById(R.id.send_msg);
		text=(TextView)findViewById(R.id.title1);
		et=(EditText)findViewById(R.id.edit_msg);
		final Intent intent=getIntent();
		text.setText(intent.getStringExtra("contact"));
		String data=getResources().getString(R.string.msg);
      
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("TAG","响应成功 ");
				 String phone = intent.getStringExtra("number"); 
		         String context = et.getText().toString();  
		         
					SmsManager manager = SmsManager.getDefault();  
					Intent itSend = new Intent(SMS_SEND_ACTIOIN);
			          Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);
			          
			          /* sentIntent参数为传送后接受的广播信息PendingIntent */
			          PendingIntent mSendPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itSend, 0);
			          
			          /* deliveryIntent参数为送达后接受的广播信息PendingIntent */
			          PendingIntent mDeliverPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itDeliver, 0);
			          
		            ArrayList<String> list = manager.divideMessage(context);  //因为一条短信有字数限制，因此要将长短信拆分  
		            for(String text:list){  
		                manager.sendTextMessage(phone, null, text, mSendPI, mDeliverPI);  
		            }  
		            //Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show(); 
		            
		            refresh();
		        }  
			     
			     private void refresh() {
						
						onCreate(null);
					}
				
			});		
	}
	
	 public class mServiceReceiver extends BroadcastReceiver
	  {
	    @Override
	    public void onReceive(Context context, Intent intent)
	    {
	      
	      //mTextView01.setText(intent.getAction().toString());
	      if (intent.getAction().equals(SMS_SEND_ACTIOIN))
	      {
	        try
	        {
	          /* android.content.BroadcastReceiver.getResultCode()方法 */
	          //Retrieve the current result code, as set by the previous receiver.
	          switch(getResultCode())
	          {
	            case Activity.RESULT_OK:
	              /* 发送短信成功 */
	              //mTextView01.setText(R.string.str_sms_sent_success);
	              mMakeTextToast
	              (
	                "发送短信成功",
	                true
	              );
	              break;
	            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	              /* 发送短信失败 */
	              //mTextView01.setText(R.string.str_sms_sent_failed);
	              mMakeTextToast
	              (
	                "发送短信失败",
	                true
	              );
	              break;
	            case SmsManager.RESULT_ERROR_RADIO_OFF:
	              break;
	            case SmsManager.RESULT_ERROR_NULL_PDU:
	              break;
	          }        
	        }
	        catch(Exception e)
	        {
	          e.getStackTrace();
	        }
	      }
	      else if(intent.getAction().equals(SMS_DELIVERED_ACTION))
	      {
	        try
	        {
	          /* android.content.BroadcastReceiver.getResultCode()方法 */
	          switch(getResultCode())
	          {
	            case Activity.RESULT_OK:
	              /* 短信 */
	              //mTextView01.setText(R.string.str_sms_sent_success);
	              mMakeTextToast
	              (
	                "对方成功接收短信",
	                true
	              );
	              break;
	            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	              /* 短信未送达 */
	              //mTextView01.setText(R.string.str_sms_sent_failed);
	              mMakeTextToast
	              (
	                "对方未接收短信",
	                true
	              );
	              break;
	            case SmsManager.RESULT_ERROR_RADIO_OFF:
	              break;
	            case SmsManager.RESULT_ERROR_NULL_PDU:
	              break;
	          }        
	        }
	        catch(Exception e)
	        {
	          e.getStackTrace();
	        }
	      }      
	    }
	  }
	  
	  public void mMakeTextToast(String str, boolean isLong)
	  {
	    if(isLong==true)
	    {
	      Toast.makeText(SmsActivity.this, str, Toast.LENGTH_LONG).show();
	    }
	    else
	    {
	      Toast.makeText(SmsActivity.this, str, Toast.LENGTH_SHORT).show();
	    }
	  }
	  //这是重载Activity中的函数
	  @Override
	  protected void onResume()
	  {
	   
	    
	    /* 自定义IntentFilter为SENT_SMS_ACTIOIN Receiver */
	    IntentFilter mFilter01;
	    mFilter01 = new IntentFilter(SMS_SEND_ACTIOIN);
	    mReceiver01 = new mServiceReceiver();
	    registerReceiver(mReceiver01, mFilter01);
	    
	    /* 自定义IntentFilter为DELIVERED_SMS_ACTION Receiver */
	    mFilter01 = new IntentFilter(SMS_DELIVERED_ACTION);
	    mReceiver02 = new mServiceReceiver();
	    registerReceiver(mReceiver02, mFilter01);
	    
	    super.onResume();
	  }
	  
	  @Override
	  protected void onPause()
	  {
	    
	    /* 取消注册自定义Receiver */
	    unregisterReceiver(mReceiver01);
	    unregisterReceiver(mReceiver02);
	    
	    super.onPause();
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms, menu);
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

