package com.yon.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.yon.opengl.render.RotateRender;

public class MainList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		GLSurfaceView view = new GLSurfaceView(this);
		
//		view.setRenderer(new TutorialRender());
//		view.setRenderer(new TriangleRender());
//		view.setRenderer(new ColorTriangleRender());
		view.setRenderer(new RotateRender());
		
		setContentView(view);
	}

}
