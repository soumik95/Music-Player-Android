package com.example.project_music_player;







import java.io.File;








import java.util.Random;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MusicApp extends Activity implements OnClickListener,OnSeekBarChangeListener {
	static MediaPlayer p1;
	ImageView v1,v2;
	TextView t1,t2;
	SeekBar sb1;
	ImageButton b1,b2,b3,b4,b5;
	int posi,shuffle=0;
	String song,songpath;
	BGTask bt=new BGTask();
	boolean flagchanged = false;
	boolean autoloop = false;
	SongsRetrieval ob = new SongsRetrieval(this);
	MediaMetadataRetriever md = new MediaMetadataRetriever();
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_app);
		t1=(TextView)findViewById(R.id.ma_tv2);
		t2=(TextView)findViewById(R.id.ma_tv1);
		v1=(ImageView)findViewById(R.id.ma_iv1);
		v2=(ImageView)findViewById(R.id.ma_iv2);
		b1=(ImageButton)findViewById(R.id.ma_play);
		b2=(ImageButton)findViewById(R.id.ma_next);
		b3=(ImageButton)findViewById(R.id.ma_prev);
		b4=(ImageButton)findViewById(R.id.ma_shuffle);
		b5=(ImageButton)findViewById(R.id.ma_times);
		//b3.setOnClickListener(this);
		//b2.setOnClickListener(this);
		b1.setOnClickListener(this);
		sb1=(SeekBar)findViewById(R.id.ma_sb);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		sb1.setOnSeekBarChangeListener(this);
		
		 Intent ri= getIntent();
		 Bundle b=ri.getExtras();
		 song=b.getString("k1");
		 songpath=b.getString("k2");
		 posi=b.getInt("k3");
		 md.setDataSource(songpath);
     	Bitmap bmp;
     	byte[] data = md.getEmbeddedPicture();
     	if(data!=null)
     	{
     		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
     	}
     	else
     	{
     		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.play);
     	}
     	t2.setText(song);
     	v2.setImageBitmap(bmp);
     	//v1.setImageBitmap(bmp);
			try
			{
				
				if(p1!=null)
				{
					p1.reset();
					p1=new MediaPlayer();
				}
				if(p1==null)
					p1=new MediaPlayer();
				p1.setDataSource(songpath);
				if(autoloop)
					p1.setLooping(true);
				p1.prepare();
				p1.start();
				sb1.setMax(p1.getDuration());
				flagchanged=false;
				bt=new BGTask();
				bt.start();
			}catch(Exception e){
				//Toast.makeText(this, "FILE IS CORRUPTED", 500).show();
				
			}
			/*p1.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer p1) {
					
					try{
						 p1.reset();
						 p1=new MediaPlayer();
						 ob.getAudioList();
						
						 	if((posi+1)>ob.songs.length)
						 		posi=0;
						    songpath=ob.mAudioPath[++posi];
						    song=ob.songs[posi];
						    md.setDataSource(songpath);
					     	Bitmap bmp;
					     	byte[] data = md.getEmbeddedPicture();
					     	if(data!=null)
					     	{
					     		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
					     	}
					     	else
					     	{
					     		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.play);
					     	}
					     	v2.setImageBitmap(bmp);
							p1=new MediaPlayer();
							p1.setDataSource(songpath);
							if(autoloop)
								p1.setLooping(true);
							p1.prepare();
							p1.start();
							t2.setText(song);
							sb1.setMax(p1.getDuration());
							flagchanged=false;
							bt=new BGTask();
							bt.start();
							
					     
						}catch (Exception e)
						{
							//Toast.makeText(this, "MUSIC FILE CORRUPTED", 300).show();
						}
					
				}
			});*/
			
			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int mid = item.getItemId();
		if(mid==R.id.omi1){
			//Intent i = new Intent(this,Equalizer.class);
			//startActivity(i);
			Intent intent = new Intent();
	        intent.setAction("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
	        startActivity(intent);
		}
		if(mid==R.id.omi2){
	            if (songpath != null) {      // file.exists

	                ContentValues values = new ContentValues();
	                values.put(MediaStore.MediaColumns.DATA, songpath);
	                values.put(MediaStore.MediaColumns.TITLE, "ring");
	                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
	                values.put(MediaStore.MediaColumns.SIZE, songpath.length());
	                values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
	                values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
	                values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
	                values.put(MediaStore.Audio.Media.IS_ALARM, true);
	                values.put(MediaStore.Audio.Media.IS_MUSIC, false);

	                Uri uri = MediaStore.Audio.Media.getContentUriForPath(songpath);
	                getContentResolver().delete(
	                    uri,
	                    MediaStore.MediaColumns.DATA + "=\""
	                            + songpath + "\"", null);
	                Uri newUri = getContentResolver().insert(uri, values);

	                try {
	                    RingtoneManager.setActualDefaultRingtoneUri(
	                        this, RingtoneManager.TYPE_RINGTONE,
	                        newUri);
	                } catch (Throwable t) {

	                }
	            }
	        }
	    
			
		if(mid==R.id.omi3){
			Intent i = new Intent(this,AboutUs.class);
			startActivity(i);
		}
		if(mid==R.id.omi4){
			p1.stop();
			finishAffinity();
		}
		
		return super.onOptionsItemSelected(item);
	}
		


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id==R.id.ma_play){
		boolean flag=p1.isPlaying();
		if(flag==false){
			p1.start();
			/*//long d =p1.getDuration(); // gives total duration
			long cd= p1.getCurrentPosition(); // current postion of playing
			long s = cd/1000;
			String dtd = String.format("DURATION - %02d:%02d",s/60 ,s%60);
			t1.setText(dtd);*/
			b1.setImageResource(android.R.drawable.ic_media_pause);}
			else {
				
				p1.pause();
				b1.setImageResource(android.R.drawable.ic_media_play);
				
			}
		}
		else if(id==R.id.ma_next)
		{
			try{
			if(shuffle==0){
			 p1.reset();
			 p1=new MediaPlayer();
			 ob.getAudioList();
			
			 	if((posi+1)>ob.songs.length)
			 		if(autoloop)
			 		{
			 			posi=0;
			 		}
			 		else
			 			throw new Exception();
			    songpath=ob.mAudioPath[++posi];
			    song=ob.songs[posi];
			    md.setDataSource(songpath);
		     	Bitmap bmp;
		     	byte[] data = md.getEmbeddedPicture();
		     	if(data!=null)
		     	{
		     		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
		     	}
		     	else
		     	{
		     		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.play);
		     	}
		     	v2.setImageBitmap(bmp);
				p1=new MediaPlayer();
				p1.setDataSource(songpath);
				if(autoloop)
					p1.setLooping(true);
				p1.prepare();
				p1.start();
				t2.setText(song);
				sb1.setMax(p1.getDuration());
				flagchanged=false;
				bt=new BGTask();
				bt.start();
			}
			else
			{
				p1.reset();
				 p1=new MediaPlayer();
				 ob.getAudioList();
				 Random rand = new Random();

				 posi = rand.nextInt(ob.songs.length) + 0;
				 songpath=ob.mAudioPath[posi];
				    song=ob.songs[posi];
				    md.setDataSource(songpath);
			     	Bitmap bmp;
			     	byte[] data = md.getEmbeddedPicture();
			     	if(data!=null)
			     	{
			     		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			     	}
			     	else
			     	{
			     		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.play);
			     	}
			     	v2.setImageBitmap(bmp);
					p1=new MediaPlayer();
					p1.setDataSource(songpath);
					if(autoloop)
						p1.setLooping(true);
					p1.prepare();
					p1.start();
					t2.setText(song);
					sb1.setMax(p1.getDuration());
					flagchanged=false;
					bt=new BGTask();
					bt.start();
			}
				
		     
			}catch (Exception e)
			{
				//Toast.makeText(this, "MUSIC FILE CORRUPTED", 300).show();
				//Toast.makeText(this, "AUTO LOOP IS OFF", 300).show();
			}
			
		}
		else if(id==R.id.ma_prev)
		{
			try{
			 p1.reset();
			 p1=new MediaPlayer();
			 if((posi-1)<0)
			 		posi=ob.songs.length-1;
			 
			 ob.getAudioList();
				
			 
			    songpath=ob.mAudioPath[--posi];
			    song=ob.songs[posi];
			    md.setDataSource(songpath);
		     	Bitmap bmp;
		     	byte[] data = md.getEmbeddedPicture();
		     	if(data!=null)
		     	{
		     		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
		     	}
		     	else
		     	{
		     		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.play);
		     	}
		     	
		     	v2.setImageBitmap(bmp);
				p1=new MediaPlayer();
				p1.setDataSource(songpath);
				if(autoloop)
					p1.setLooping(true);
				p1.prepare();
				p1.start();
				t2.setText(song);
				sb1.setMax(p1.getDuration());
				flagchanged=false;
				bt=new BGTask();
				bt.start();
		     
			}catch (Exception e)
			{
				//Toast.makeText(this, "MUSIC FILE CORRUPTED", 300).show();
			}
		}
		else
		if(id==R.id.ma_shuffle)
		{
			if(shuffle==1)
			{
				b4.setBackgroundColor(Color.WHITE);
				//Toast.makeText(this, "SHUFFLE TURNED OFF", 50).show();
				shuffle=0;
			}
			else
			{
				b4.setBackgroundColor(Color.BLACK);
				//Toast.makeText(this, "SHUFFLE TURNED ON", 50).show();
				shuffle=1;
			}
		}
		else
		if(id==R.id.ma_times)
		{
			if(autoloop==false)
			{
				b5.setBackgroundColor(Color.BLACK);
				//b5.setImageResource(android.R.drawable.stat_notify_sync);
				autoloop=true;
			}
			else
			{
				b5.setBackgroundColor(Color.WHITE);
				//b5.setImageResource(android.R.drawable.ic_menu_rotate);
				autoloop=false;
			}
		}

		
		
	}
		

	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		long cd= p1.getCurrentPosition(); // current postion of playing
		long s = cd/1000;
		String dtd = String.format("DURATION - %02d:%02d",s/60 ,s%60);
		t1.setText(dtd);
		 if(p1 != null && fromUser){
             p1.seekTo(progress);
         }
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	class BGTask extends Thread
	{
		public void run()
		{
			flagchanged=true;
			while(true)
			{
				if(!flagchanged)
					return;
				i=p1.getCurrentPosition();
				sb1.setProgress(i);
				if(i>p1.getDuration())
					break;
			}
			
		}
	}
	
	
}


