package com.example.project_music_player;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AlbulmSongs extends ListActivity {
	TextView tv1;
	int pos;
	String [] mi;
	String [] mipath;
	//Bitmap[] mbit;
	String[] m = {"AS","add"};
	SongsRetrieval ob;
	int j=0;
	int ct=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_albulm_songs);
		ob= new SongsRetrieval(this);
		ob.getAudioList();
		tv1=(TextView)findViewById(R.id.als_tv1);
		Intent ri = getIntent();
		Bundle b = ri.getExtras();
		pos = b.getInt("k1");
		String a=ob.albulm[pos].toString();
		
		for(int i=0;i<ob.albulm.length;i++)
		{
			if(ob.albulm[i].equalsIgnoreCase(a))
			{
				ct++;
			}
		}
		
		mi=new String[ct];
		mipath=new String[ct];
		//mbit=new Bitmap[ct];
		for(int i=0;i<ob.albulm.length;i++)
		{
			if(ob.albulm[i].equalsIgnoreCase(a))
			{
				mipath[j]=ob.mAudioPath[i].toString();
				mi[j++]=ob.songs[i].toString();
				//mbit[j++]=ob.bit[i];
			}
		}
		//getSongs();
		  //a=a+" POS "+pos+"...."+ct+",,,";
		tv1.setText(a);
		int d = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,d,mi); 
		setListAdapter(ad);
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this,MusicApp.class);
		i.putExtra("k1", mi[position]);
		i.putExtra("k2", mipath[position]);
		i.putExtra("k3", pos);
		startActivity(i);
	}
}
