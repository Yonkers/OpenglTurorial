package com.yon.opengl;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.yon.opengl.render.TutorialRender;
import com.yon.opengl.shape.SimplePlane;

public class TutorialPartI extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		GLSurfaceView view = new GLSurfaceView(this);
		 view.setRenderer(new TutorialRender());
//		TextureRender renderer = new TextureRender();
//		view.setRenderer(renderer);

		setContentView(view);

		/*// Create a new plane.
		SimplePlane plane = new SimplePlane(1, 1);

		// Move and rotate the plane.
		plane.z = 1.7f;
		plane.rx = -65;

		// Load the texture.
		plane.loadBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher));

		// Add the plane to the renderer.
		renderer.addMesh(plane);*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tutorial_part_i, menu);
		return true;
	}

}
