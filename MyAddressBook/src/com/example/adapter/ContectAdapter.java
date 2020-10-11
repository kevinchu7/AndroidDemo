package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.contacts.R;
import com.example.entity.Person;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContectAdapter extends BaseAdapter{

	List<Person> datalist=new ArrayList<>();
	public ContectAdapter(List<Person> data)
	{
		this.datalist=data;
		
	}
	public int getCount() {
		return datalist.size();
	}

	@Override
	public Object getItem(int position) {
		return datalist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rootView=LayoutInflater.from(parent.getContext()).inflate(R.layout.base_layout, null);
		TextView name=(TextView)rootView.findViewById(R.id.t_name);
		TextView num=(TextView)rootView.findViewById(R.id.t_phone);
		name.setText(datalist.get(position).getName());
		num.setText(datalist.get(position).getMobilephone());
		return rootView;
	}

}

