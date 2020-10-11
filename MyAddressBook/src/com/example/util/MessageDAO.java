package com.example.util;

import com.example.entity.Person;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * 
 * @author zhukai
 *
 */
public class MessageDAO {
	private SQLiteDatabase db;
	public MessageDAO(Context context)
	{
		MyOpenHelper myopenhelper=new MyOpenHelper(context);
		db=myopenhelper.getReadableDatabase();
		
	}
	public boolean Insert(Person p){
		
		try{
		
			String sql="insert into person values(null,'"+p.getName()+"','"+p.getMobilephone()+"','"+
			                                        p.getFamilyphone()+"','"+p.getOfficephone()+"','"+p.getGroup()+"',"+"0,'"+p.getAddress()+ "');";
			db.execSQL(sql);
			return true;
		}
		catch(Exception e)
		{
		 Log.i("TAG","数据库错误： "+e.toString());
		 return false;
		}
		
	}
   public boolean Delete(String s){
		
		try{
		
			String sql="delete from person where "+s;
			db.execSQL(sql);
			return true;
		}
		catch(Exception e)
		{
		 Log.i("TAG","数据库错误： "+e.toString());
		 return false;
		}
		
	}
    public boolean update(String s1,String s2){
		
		try{
		
			String sql="update person "+s2+" where name='"+s1+"'";
			db.execSQL(sql);
			return true;
		}
		catch(Exception e)
		{
		 Log.i("TAG","数据库错误： "+e.toString());
		 return false;
		}
		
	}
	public Cursor Query(){
		String sql="select * from person";
		Cursor cur=db.rawQuery(sql, null);
		return cur;
	}
	public Cursor ConditionQuery(String s){
		String sql="select * from person where "+s;
		Cursor cur=db.rawQuery(sql, null);
		return cur;
	}
	public boolean exists(String s)
	{
		String sql="select * from person where mobilephone='"+s+"'";
		Cursor cur=db.rawQuery(sql, null);
		if(cur.getCount()==0)
			return false;
		else
			return true;
	}
	
}
