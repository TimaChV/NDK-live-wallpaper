package com.android.gl2jni;


import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MyActivity extends Activity implements OnClickListener {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.wellcome);

		View applyButton = findViewById(R.id.button_lwp);
		applyButton.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.button_lwp:
				if(Build.VERSION.SDK_INT >= 16){
					Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
					intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, MyService.class));
					startActivity(intent);
				}else{
					Intent applyIntent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
					startActivity(applyIntent);
				}
				finish();
				break;


		}
	}



}
