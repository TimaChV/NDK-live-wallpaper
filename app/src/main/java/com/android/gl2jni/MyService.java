package com.android.gl2jni;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyService extends GLWallpaperService {
    private MyRenderer renderer;
    private static final String TAG = "lwp";

    @Override
    GLSurfaceView.Renderer getNewRenderer() {
        renderer = new MyRenderer(MyService.this);
        return renderer;
    }

    @Override
    public Engine onCreateEngine() {
        MyGLEngine myGLEngine = new MyGLEngine();
        Log.d(TAG, "MyService.onCreateEngine() -> "+ myGLEngine);
        return myGLEngine;
    }


    @Override
    public void onDestroy() { // service onDestroy()
        super.onDestroy();

        Log.d(TAG, "MyService.onDestroy()");
    }


    class MyGLEngine extends GLWallpaperService.GLEngine {

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            Log.d(TAG, "    MyGLEngine.onVisibilityChanged(" + visible + ")");
            if (visible) {
                GL2JNILib.onResume();
            } else {
                GL2JNILib.onPause();
            }
            super.onVisibilityChanged(visible);
        }


        @Override
        public void onDestroy() { // engine onDestroy()
            Log.d(TAG, "    MyGLEngine.onDestroy()");

            getGlSurfaceView().queueEvent(new Runnable() {
                @Override
                public void run() {
                    //GL2JNILib.dispose();
                    if(renderer != null) renderer.dispose();
                }
            });

            renderer = null;
            super.onDestroy();
        }

    }// end engine

}// end service
