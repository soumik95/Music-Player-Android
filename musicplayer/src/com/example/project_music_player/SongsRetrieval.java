	package com.example.project_music_player;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ListFragment;

public class SongsRetrieval{
	
	public String[] songs;
	public String[] mAudioPath;
	public String[] artist;
	public String[] albulm;
	public long[] albulmid;
	MediaMetadataRetriever md = new MediaMetadataRetriever();
	//public String[] albulmart;
	public Bitmap[] bit;
	//static public MediaPlayer mMediaPlayer =new MediaPlayer();
	
	private Context ctx;

    public SongsRetrieval( Context ctx ) 
    {
        super();
        this.ctx = ctx;
    }
	public void getAudioList()
	{
		final Cursor mCursor = ctx.getContentResolver().query(
	            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
	            new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ALBUM_ID}, null, null,
	            "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");

	    int count = mCursor.getCount();

	    songs = new String[count];
	    mAudioPath = new String[count];
	    artist=new String[count];
	    albulm=new String[count];
	    albulmid=new long[count];
	    bit=new Bitmap[count];
	    
	    
	    //albulmart=new String[count];
	    //bit=new Bitmap[count];
	    int i = 0;
	    if (mCursor.moveToFirst()) {
	        do {
	        	albulmid[i] = mCursor.getLong(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
	            songs[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
	            mAudioPath[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
	            artist[i]= mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
	            albulm[i]= mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
	            //albulmart[i]= mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART));
	            /*final Uri ART_CONTENT_URI = Uri.parse("content://media/external/audio/albumart");
	            Uri albumArtUri = ContentUris.withAppendedId(ART_CONTENT_URI, albulmid[i]);
	            try {
	                bit[i] = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), albumArtUri);
	            } catch (Exception exception) {
	                // log error
	            }*/
	            	/*md.setDataSource(mAudioPath[i]);
	            	Bitmap bmp;
	            	byte[] data = md.getEmbeddedPicture();
	            	if(data!=null)
	            	{
	            		bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
	            	}
	            	else
	            	{
	            		bmp = BitmapFactory.decodeResource(ctx.getResources(), android.R.drawable.btn_radio);
	            	}*/
	            	//InputStream is = new ByteArrayInputStream(data);
	                //Bitmap bm = BitmapFactory.decodeStream(is);
	            	//Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
	            	//bit[i]=bm;
	            
	            i++;
	        } while(mCursor.moveToNext());
	    }   

	    mCursor.close();
	}

}
