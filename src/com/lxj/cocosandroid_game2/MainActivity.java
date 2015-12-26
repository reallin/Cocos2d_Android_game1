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
		// ���ķ���һ
		mcCcDirector.attachInView(surfaceView);// ���������߳���surfaceView��ɻ��Ʋ���
		mcCcDirector.setDisplayFPS(true);
		mcCcDirector.setDeviceOrientation(mcCcDirector.kCCDeviceOrientationLandscapeLeft);//����
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
		// ���ķ���һ
		director.attachInView(surfaceView);// ���������߳���surfaceView��ɻ��Ʋ���

		// ��Ļ���������
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		// ������Ļ�Ĵ�С
		director.setScreenSize(480, 320);

		// ֡����Ϣ
		director.setDisplayFPS(true);

		CCScene scene = createScene();
		// ���ķ�����
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
