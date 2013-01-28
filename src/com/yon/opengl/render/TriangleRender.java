package com.yon.opengl.render;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import com.yon.opengl.util.BufferUtil;

public class TriangleRender extends BaseRender {
	 
	private int one = 0x10000;   
	
    private int[] trigger = {0, one, 0, -one, -one, 0, one, -one, 0};  
     
    private int[] quarter = {one, one, 0, -one, one, 0, one, -one, 0,    
             -one, -one, 0};    
    
    //准备三角各顶点    
    private IntBuffer triggerBuffer = BufferUtil.iBuffer(trigger);    
    
    //准备四边顶点    
    private IntBuffer quarterBuffer = BufferUtil.iBuffer(quarter);
    
	@Override
	public void onDrawFrame(GL10 gl) {
		super.onDrawFrame(gl);
		//清楚屏幕和深度缓存    
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);    
        gl.glLoadIdentity();    
        //现将屏幕向左移动，用来画三角形    
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);    
        //允许设置顶点    
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);    
        //设置三角形    
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);    
        //绘制三角形    
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);    
        //重置    
        gl.glLoadIdentity();    
        //现将屏幕向左移动，用来画四边形    
        gl.glTranslatef(1.5f, 0.0f, -6.0f);    
        //设置四边形    
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, quarterBuffer);    
        //绘制四边形    
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);    
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); 
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		super.onSurfaceChanged(gl, width, height);
		 //设置OpenGL场景大小    
        float ratio = (float) width / height;    
        gl.glViewport(0, 0, width, height);    
        gl.glMatrixMode(GL10.GL_PROJECTION);//设置为投影矩阵模式    
        gl.glLoadIdentity();//重置    
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);//设置视角    
        gl.glMatrixMode(GL10.GL_MODELVIEW);    
        gl.glLoadIdentity();   
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		 //告诉系统对透视进行修正    
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);    
        //设置清除屏幕时所用的颜色，参数依次为红、绿、蓝、Alpha值    
        gl.glClearColorx(0, 0, 0, 0);    
        //启用阴影平滑    
        gl.glShadeModel(GL10.GL_SMOOTH);    
        //以下是关于深度缓存的设置，非常重要    
        gl.glClearDepthf(1.0f);//设置深度缓存    
        gl.glEnable(GL10.GL_DEPTH_TEST);//启用深度测试    
        gl.glDepthFunc(GL10.GL_LEQUAL);//所做深度测试的类型    
	}

}
