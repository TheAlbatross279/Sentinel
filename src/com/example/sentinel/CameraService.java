package com.example.sentinel;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.google.android.glass.media.CameraManager;
import com.parse.Parse;

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
		Parse.initialize(this, "dgTfm5RHJiyBa9cYNI2pwLDSlzBlLICr2Xp8iYei", "lOFsyBAvypZoPOWxECHh00jzxEJXK2bnkhUMMye6");
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
