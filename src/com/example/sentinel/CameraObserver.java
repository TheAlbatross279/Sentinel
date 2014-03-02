package com.example.sentinel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
          
            try {
            	FileInputStream input = new FileInputStream(picture);
                int size = 1000;
                byte[] b = new byte[size];
                FileOutputStream f = new FileOutputStream(new File("/mnt/sdcard/Pictures/", file));
                while (input.read(b) != -1) {
                	f.write(b);
                }

                input.close();
            	f.close();
            } catch (IOException e) {
            	Log.d(TAG, "I died opening" + this.path + file + "]");
            }
            
            boolean isDeleted = picture.delete();
            if (isDeleted) {
            	Log.d(TAG, "I deleted it! [" + this.path + file + "]");
            }
        }
    }

	public void close(){
		super.finalize();
	}

}
