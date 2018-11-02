package com.android.gl2jni;

import android.content.Context;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.util.Log;


public class MyRenderer implements GLSurfaceView.Renderer {
    private Context context;
    private static final String TAG = "lwp";
    private boolean rend_ready = false;
    //GL10 _gl;// save gl context
    //static public Object lock = new Object();


    public MyRenderer(Context context_){
        context = context_;
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig eglConfig) {
        Log.d(TAG, "    MyRender.onSurfaceCreated()");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "    MyRender.onSurfaceChanged()");
        rend_ready = false;
        GL2JNILib.resize(width, height);
        rend_ready = true;
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        if (rend_ready) {
            GL2JNILib.step();
        }

    }



    public void dispose(){
        Log.d(TAG, "    MyRender.dispose()");
        rend_ready = false;
        GL2JNILib.dispose();
    }


}
