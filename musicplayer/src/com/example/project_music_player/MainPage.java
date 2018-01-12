package com.example.project_music_player;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.LauncherActivity.ListItem;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainPage extends FragmentActivity implements OnClickListener,TabListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		final ActionBar ab=getActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ab.addTab(ab.newTab().setText("Songs").setTabListener(this));
		ab.addTab(ab.newTab().setText("Artists").setTabListener(this));
		ab.addTab(ab.newTab().setText("Albulms").setTabListener(this));
		//ab.addTab(ab.newTab().setText("Playlist").setTabListener(this));
	}


	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}*/
	@Override
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getPosition()==0)
		{
			SongsFragment s= new SongsFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, s).commit();
		}
		else
		if(tab.getPosition()==1)
		{
			ArtistsFragment s= new ArtistsFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, s).commit();
		}
		else
		if(tab.getPosition()==2)
		{
			AlbulmFragment s= new AlbulmFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, s).commit();
		}
		else
		{
			PlaylistFragment s= new PlaylistFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, s).commit();
		}
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
