package com.yon.opengl.render;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.yon.opengl.util.BufferUtil;

public class RotateRender extends BaseRender {
	 private int one = 0x10000;      
     private int[] trigger = {0, one, 0, -one, -one, 0, one, -one, 0};      
     private int[] quarter = {one, one, 0, -one, one, 0, one, -one, 0,      
             -one, -one, 0};      
     private int[] color = {one, 0, 0, one,    
                             0, one, 0, one,    
                             0, 0, one, one};    
     //准备三角各顶点      
     private IntBuffer triggerBuffer = BufferUtil.iBuffer(trigger);      
     //准备四边顶点      
     private IntBuffer quarterBuffer = BufferUtil.iBuffer(quarter);      
     //准备三角形颜色    
     private IntBuffer colorBuffer = BufferUtil.iBuffer(color);    
         
     private float rotateTri; //用于三角形的旋转；    
     private float rotateQua; //用于四边形的旋转；
     
   //咳，咳现在开始画图了      
     @Override      
     public void onDrawFrame(GL10 gl) {      
         // TODO Auto-generated method stub      
         //清楚屏幕和深度缓存      
         gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);      
         gl.glLoadIdentity();      
         //现将屏幕向左移动，用来画三角形      
         gl.glTranslatef(-1.5f, 0.0f, -6.0f);     
         //旋转三角形    
         gl.glRotatef(rotateTri, 0.0f, 1.0f, 1.0f);    
         //允许设置顶点      
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);     
         //开启颜色渲染功能    
         gl.glEnableClientState(GL10.GL_COLOR_ARRAY);    
         //设置三角形      
         gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);     
         //给三角形的每一顶点着色    
         gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);    
         //绘制三角形      
         gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);     
         //关闭颜色渲染功能    
         gl.glDisableClientState(GL10.GL_COLOR_ARRAY);    
         //绘制三角形结束    
         gl.glFinish();    
             
         //重置      
         gl.glLoadIdentity();      
         //现将屏幕向左移动，用来画四边形      
         gl.glTranslatef(1.5f, 0.0f, -6.0f);     
         /**  
          * 旋转四边形  
          * 参数：   
          * 第一个参数是旋转角度   
          * 之后是x、y、z共同决定旋转轴方向的参数   
          * 例如:1,0,0 表示经过x坐标的一个单位向右旋转   
          *      -1,0,0 表示.................向左旋转   
          * */    
         gl.glRotatef(rotateQua, 1.0f, 0.0f, 0.0f);    
         //设置四边形      
         gl.glVertexPointer(3, GL10.GL_FIXED, 0, quarterBuffer);      
         //绘制四边形      
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);      
         gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);    
         //绘制正方形结束    
         gl.glFinish();    
         gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);      
             
             
         //改变旋转的角度    
         rotateTri += 0.5f;    
         rotateQua -= 0.5f;    
     }      
   
     //当窗口改变时，调用，至少在创建窗口时调用一次，这边设置下场景大小      
     @Override      
     public void onSurfaceChanged(GL10 gl, int width, int height) {      
         // TODO Auto-generated method stub      
         //设置OpenGL场景大小      
         float ratio = (float) width / height;      
         gl.glViewport(0, 0, width, height);      
         gl.glMatrixMode(GL10.GL_PROJECTION);//设置为投影矩阵模式      
         gl.glLoadIdentity();//重置      
         gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);//设置视角      
         gl.glMatrixMode(GL10.GL_MODELVIEW);      
         gl.glLoadIdentity();      
     }      
   
     //当窗口被创建时我们可以做些初始化工作      
     @Override      
     public void onSurfaceCreated(GL10 gl, EGLConfig config) {      
         // TODO Auto-generated method stub      
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
