
package com.android.gl2jni;

// Wrapper for native library

public class GL2JNILib {

     static {
         System.loadLibrary("native-lib");
     }


    public static native void resize(int width, int height);

    public static native void step();

    public static native void onPause();

    public static native void onResume();

    public static native void dispose();

}
