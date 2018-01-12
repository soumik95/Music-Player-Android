package com.example.project_music_player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SongsFragment extends android.support.v4.app.ListFragment{
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		SongsRetrieval ob=new SongsRetrieval(getActivity());
		 ob.getAudioList();
		View rootView=inflater.inflate(R.layout.songs,container,false);
		
		ListAdapter ad=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ob.songs); 
		setListAdapter(ad);
		return rootView; 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		SongsRetrieval ob=new SongsRetrieval(getActivity());
		 ob.getAudioList();
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(getActivity(),MusicApp.class);
		i.putExtra("k1", ob.songs[position]);
		i.putExtra("k2", ob.mAudioPath[position]);
		i.putExtra("k3", position);
		//i.putExtra("k3", mbit[position]);
		startActivity(i);
	}
	/*private void playSong(String path) throws IllegalArgumentException,
    IllegalStateException, IOException {

    Log.d("ringtone", "playSong :: " + path);

    mMediaPlayer.reset();
    mMediaPlayer.setDataSource(path);       
//mMediaPlayer.setLooping(true);
    mMediaPlayer.prepare();
    mMediaPlayer.start();
}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try {
			playSong(mAudioPath[position]);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getActivity(), artist[position], 100).show();
	}*/

}
