package com.example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper{

	public MyOpenHelper(Context context) {
		super(context, "contect.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.i("TAG","onCreate方法被执行");
	    String sql="create table if not exists person("+
		            "_id integer primary key autoincrement,"+
		            "name  varchar(8) not null,"+
		            "mobilephone varchar(11) not null,"+ 
		            "familyphone varchar(10),"+
		            "officephone varchar(10),"+       
		            "groups varchar(10),"+ 
		            "remark integer,"+
		            "address varchar(10));";  
		db.execSQL(sql);                        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.i("TAG","onUpgr方法被执行");
	}

}
