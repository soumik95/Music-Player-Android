package com.example.project_music_player;
import com.example.project_music_player.SongsRetrieval;

import android.app.ListFragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ArtistsFragment extends android.support.v4.app.ListFragment {
	

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 SongsRetrieval ob=new SongsRetrieval(getActivity());
		 ob.getAudioList();
		View rootView=inflater.inflate(R.layout.songs,container,false);
		ListAdapter ad=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ob.artist); 
		setListAdapter(ad);
		return rootView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(getActivity(),ArtistSongs.class);
		i.putExtra("k1", position);
		startActivity(i);
		//Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(),100).show();
	}

}
