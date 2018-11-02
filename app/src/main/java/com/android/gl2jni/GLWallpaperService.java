package com.android.gl2jni;


import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;



public abstract class GLWallpaperService extends WallpaperService {
	private static final String TAG = "lwp_service";

	public class GLEngine extends Engine {
		class WallpaperGLSurfaceView extends GLSurfaceView {

			WallpaperGLSurfaceView(Context context) {
				super(context);
				//Log.d(TAG, "WallpaperGLSurfaceView(" + context + ")");
			}

			@Override
			public SurfaceHolder getHolder() {
				//Log.d(TAG, "getHolder(): -> " + getSurfaceHolder());
				return getSurfaceHolder();
			}

			public void onDestroy() {
				//Log.d(TAG, "onDestroy()");
				super.onDetachedFromWindow();
			}
		}


		private WallpaperGLSurfaceView glSurfaceView;
		private boolean rendererHasBeenSet;

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			//Log.d(TAG, "onCreate(" + surfaceHolder + ")");
			super.onCreate(surfaceHolder);
			glSurfaceView = new WallpaperGLSurfaceView(GLWallpaperService.this);

			// Check if the system supports OpenGL ES 2.0.
			final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
			final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

			if (supportsEs2)
			{
				// Request an OpenGL ES 2.0 compatible context.
				setEGLContextClientVersion(2);
				// On Honeycomb+ devices, this improves the performance when
				// leaving and resuming the live wallpaper.
				setPreserveEGLContextOnPause(true);
				// Set the renderer to our user-defined renderer.
				//Log.d("tima", "context = "+getApplicationContext());
				setRenderer(getNewRenderer());
			}
			else
			{
				// This is where you could create an OpenGL ES 1.x compatible
				// renderer if you wanted to support both ES 1 and ES 2.
				return;
			}
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			//Log.d(TAG, "onVisibilityChanged(" + visible + ")");
			super.onVisibilityChanged(visible);
			if (rendererHasBeenSet) {
				if (visible) {
					glSurfaceView.onResume();
				} else {
					glSurfaceView.onPause();
				}
			}
		}

		@Override
		public void onDestroy() {
			//Log.d(TAG, "onDestroy()");
			super.onDestroy();
			glSurfaceView.onDestroy();
		}

		protected void setRenderer(Renderer renderer) {
			//Log.d(TAG, "setRenderer(" + renderer + ")");
			glSurfaceView.setRenderer(renderer);
			rendererHasBeenSet = true;
		}

		protected void setPreserveEGLContextOnPause(boolean preserve) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				//Log.d(TAG, "setPreserveEGLContextOnPause(" + preserve + ")");
				glSurfaceView.setPreserveEGLContextOnPause(preserve);
			}
		}

		protected void setEGLContextClientVersion(int version) {
			//Log.d(TAG, "setEGLContextClientVersion(" + version + ")");
			glSurfaceView.setEGLContextClientVersion(version);
		}

		protected WallpaperGLSurfaceView getGlSurfaceView(){
			return glSurfaceView;
		}
	}

	abstract GLSurfaceView.Renderer getNewRenderer();
}
