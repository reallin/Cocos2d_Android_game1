package com.lxj.cocosandroid_game2;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	/*private CCDirector mcCcDirector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CCGLSurfaceView surfaceView = new CCGLSurfaceView(this);
		setContentView(surfaceView);
		mcCcDirector = mcCcDirector.sharedDirector();
		// 核心方法一
		mcCcDirector.attachInView(surfaceView);// 开启绘制线程在surfaceView完成绘制操作
		mcCcDirector.setDisplayFPS(true);
		mcCcDirector.setDeviceOrientation(mcCcDirector.kCCDeviceOrientationLandscapeLeft);//横屏
		mcCcDirector.setScreenSize(480, 320); 
		
		CCScene root = CCScene.node();
		FirstLayout firstLayout = new FirstLayout();
		root.addChild(firstLayout);
		mcCcDirector.runWithScene(root);
	}

	@Override
	protected void onDestroy() {
		mcCcDirector.end();
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mcCcDirector.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mcCcDirector.onPause();
		super.onPause();
	}
	*/
	private CCDirector director;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CCGLSurfaceView surfaceView = new CCGLSurfaceView(this);
		setContentView(surfaceView);

		director = CCDirector.sharedDirector();
		// 核心方法一
		director.attachInView(surfaceView);// 开启绘制线程在surfaceView完成绘制操作

		// 屏幕方向的设置
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		// 设置屏幕的大小
		director.setScreenSize(480, 320);

		// 帧率信息
		director.setDisplayFPS(true);

		CCScene scene = createScene();
		// 核心方法二
		director.runWithScene(scene);

	}

	private CCScene createScene() {
		CCScene root = CCScene.node();

		FirstLayout firstLayout = new FirstLayout();
		

		root.addChild(firstLayout);

		return root;
	}

	@Override
	protected void onResume() {
		director.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		director.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		director.end();
		super.onDestroy();
	}
}
