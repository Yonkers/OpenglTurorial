package com.yon.opengl.render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class BaseRender implements Renderer {

	protected static String TAG = "BaseRender";
	
	@Override
	public void onDrawFrame(GL10 gl) {
		//Log.d(TAG, "---onDrawFrame---");

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		Log.d(TAG, "---onSurfaceChanged---");

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.d(TAG, "---onSurfaceCreated---");

	}

}
