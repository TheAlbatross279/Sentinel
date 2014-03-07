package com.example.sentinel;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.FileObserver;
import android.util.Log;


public class CameraObserver extends FileObserver {
	public final String TAG = "CameraObserver";
	private String path;
	
	public CameraObserver(String path) {
		super(path);
		Log.d(TAG, "I GOT MADE!!!");
		this.path = path;
	}

	@Override
    public void onEvent(int event, String file) {
        if(event == FileObserver.CREATE && !file.equals(".probe")){ 
        	// check if its a "create" and not equal to .probe because thats created every time camera is launched
            Log.d(TAG, "File created [" + this.path + file + "]");
            //get file
           File picture = new File(this.path, file);
           
           Bitmap bitmap = BitmapFactory.decodeFile(picture.getPath());
           ParseFile pic;
            ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream); 
			byte[] bitmapdata = stream.toByteArray();
			//FileInputStream input = new FileInputStream(picture);
			//int size = 1000;
			//long size = picture.length();
			//byte[] b = new byte[(int) size];
			//input.read(b);
			//FileOutputStream f = new FileOutputStream(new File("/mnt/sdcard/Pictures/", file));
			//input.close();
          
			
			pic = new ParseFile(picture.getName(), bitmapdata);
			try {
				pic.save();
				Log.d(TAG, "Upload Success!");
			} catch (ParseException e) {
				Log.d(TAG, "Upload Failure.");
				e.printStackTrace();
			}
            /* while (input.read(b) != -1) {
				//f.write(b);
			}*/
			
			ParseObject photo = new ParseObject("Photo");
			photo.put("fileFull", pic);
			photo.saveInBackground();
            
            /*boolean isDeleted = picture.delete();
            if (isDeleted) {
            	Log.d(TAG, "I deleted it! [" + this.path + file + "]");
            }*/
        }
    }

	public void close(){
		super.finalize();
	}

}
