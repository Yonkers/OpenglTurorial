package com.yon.opengl.render;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import com.yon.opengl.shape.Cube;
import com.yon.opengl.shape.FlatColoredSquare;
import com.yon.opengl.shape.Mesh;
import com.yon.opengl.shape.Square;

public class TutorialRender implements Renderer {

	Square square;
	Cube cube ;
	
	float[] colors = {
			 1f, 0f, 0f, 1f, // vertex 0 red
			 0f, 1f, 0f, 1f, // vertex 1 green
			 0f, 0f, 1f, 1f, // vertex 2 blue
			 1f, 0f, 1f, 1f, // vertex 3 magenta
			};
	
	private float angle = 0;

	public void onDrawFrame(GL10 gl) {
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | // OpenGL docs.
				GL10.GL_DEPTH_BUFFER_BIT);
		if (null == square) {
//			square = new Square();
			square = new FlatColoredSquare();
		}
		
		if(null == cube){
			cube = new Cube(2f,2f,2f);
			
		}

		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();

		// Translates 4 units into the screen.
		gl.glTranslatef(0, 0, -10);

//		square.draw(gl);
		// SQUARE A
		 // Save the current matrix.
		 gl.glPushMatrix();
		 // Rotate square A counter-clockwise.
		 gl.glRotatef(angle , 10, 0, -10);
		 // Draw square A.
//		 square.draw(gl);
		 cube.draw(gl);
		 // Restore the last matrix.
		 gl.glPopMatrix();

		 // SQUARE B
		 // Save the current matrix
		 gl.glPushMatrix();
		 // Rotate square B before moving it,
		 //making it rotate around A.
		 gl.glRotatef(-angle, 0, 0, 10);
		 // Move square B.
		 gl.glTranslatef(2, 0, 0);
		 // Scale it to 50% of square A
		 gl.glScalef(.5f, .5f, .5f);
		 // Draw square B.
		 square.draw(gl);

		 // SQUARE C
		 // Save the current matrix
		 gl.glPushMatrix();
		 // Make the rotation around B
		 gl.glRotatef(-angle, 0, 0, 1);
		 gl.glTranslatef(2, 0, 0);
		 // Scale it to 50% of square B
		 gl.glScalef(.5f, .5f, .5f);
		 // Rotate around it's own center.
		 gl.glRotatef(angle*10, 0, 0, 1);
		 // Draw square C.
		 square.draw(gl);

		 // Restore to the matrix as it was before C.
		 gl.glPopMatrix();
		 // Restore to the matrix as it was before B.
		 gl.glPopMatrix();

		 // Increse the angle.
		 angle += 1;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);// OpenGL docs.
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
		// Reset the projection matrix
		gl.glLoadIdentity();// OpenGL docs.
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
		// Reset the modelview matrix
		gl.glLoadIdentity();// OpenGL docs.

	}

	@Override
	public void onSurfaceCreated(GL10 gl,
			javax.microedition.khronos.egl.EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.5f, 0.0f, 0.0f, 0.5f); // OpenGL docs.
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);// OpenGL docs.
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);// OpenGL docs.
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.
				GL10.GL_NICEST);

	}
}
