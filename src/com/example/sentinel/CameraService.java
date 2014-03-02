package com.example.sentinel;

import com.google.android.glass.media.CameraManager;

import android.app.Service;
import android.content.Intent;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;
public class CameraService extends Service{
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		//String path = intent.getStringExtra(
        //        CameraManager.EXTRA_PICTURE_FILE_PATH);
		String path = "/mnt/sdcard/DCIM/Camera/";
		Log.d("CameraObserver", path);
		FileObserver observer = new CameraObserver(path);
		observer.startWatching(); // start the observer
		
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		//TODO for communication return IBinder implementation
		return null;
	}
	
}
