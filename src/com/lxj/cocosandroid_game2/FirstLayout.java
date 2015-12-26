package com.lxj.cocosandroid_game2;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.ccColor3B;

import android.view.MotionEvent;

public class FirstLayout extends CCLayer{
	private static final int TAG_X = 10;
	private static final int TAG_X1 = 11;

	private CCSprite ccSprite= null;
	private CCSprite leaf;
	
public FirstLayout(){
	//CCSprite sprite = CCSprite.sprite("bg.png");
	// ����layer���Դ���touch�Ŀ���
			this.setIsTouchEnabled(true);// ��һ���������е�layer�У����ҽ���һ��layer�ܹ������û���touch
			// ͼƬ�����ꡢ���Լ��ķ���;
			
	fliX();
}
public CCSprite fliX1(){
	if(leaf==null){
	leaf = CCSprite.sprite("bg.png");// ͼƬ��Դassets�ļ�����

	// this.addChild(leaf);
	this.addChild(ccSprite,1,TAG_X1);

	//Log.i(TAG, "x:" + leaf.getPosition().x + "y:" + leaf.getPosition().y);

	// Ϊʲô�����½�չʾ������ԭ�����½�
	// ͼƬΪʲô��ʾ��һ�ǣ�1/4����ê��
	leaf.setAnchorPoint(0, 0);
	}
	return leaf;
}
public CCSprite fliX(){
	if(ccSprite==null){
		ccSprite = CCSprite.sprite("bg.png");
	
	ccSprite.setFlipX(true);
	ccSprite.setAnchorPoint(0,0);
	ccSprite.setPosition(100,0);
	this.addChild(ccSprite,0,TAG_X);
	}
	return ccSprite;
	}
@Override
public boolean ccTouchesBegan(MotionEvent event) {
	// TODO Auto-generated method stub
	CCSprite sprite =  (CCSprite)this.getChildByTag(TAG_X);
	CCSprite sprite1 =  (CCSprite)this.getChildByTag(TAG_X1);

	//ת��opengl�µ������
	CGPoint cgPoint = this.convertTouchToNodeSpace(event); 
	boolean flag = CGRect.containsPoint(sprite.getBoundingBox(), cgPoint);
	boolean flag1 = CGRect.containsPoint(sprite1.getBoundingBox(), cgPoint);

	if(flag){
		//����͸����
		//sprite.setOpacity(100);
		/*//�����Ƿ�ɼ�
		sprite.setVisible(false);
		//ɾ���Լ�
		sprite.removeSelf();*/
		move();
	}
	if(flag1){
		scale();	
	}
	return super.ccTouchesBegan(event);
}
	
private void scale(){
	CCScaleBy scaleBy = CCScaleBy.action(0.2f, 1.5f);
	CCSequence ccSequence = CCSequence.actions(scaleBy, scaleBy.reverse());
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
	fliX1().runAction(ccRepeatForever);
}

private void roate(){
	CCRotateBy scaleBy = CCRotateBy.action(0.2f, 100);//100��˳ʱ��100��
	fliX1().runAction(scaleBy);
}

private void changeColor(){
	//�ı���ɫ����
	ccColor3B c = ccColor3B.ccc3(0,-100,0);
	CCTintBy tintBy = CCTintBy.action(1, c);
	CCLabel cLabel = CCLabel.makeLabel("�ı�����", "", 20);//�������壬�м����Ϊttf�ļ���20Ϊ�����С
	cLabel.setColor(ccColor3B.ccc3(255,228, 0));//��ʼֵ
	CCSequence ccSequence = CCSequence.actions(tintBy, tintBy.reverse());
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
	this.addChild(cLabel);
	cLabel.runAction(ccRepeatForever);
}
private void jump1(){
	CGPoint pos = CGPoint.ccp (300, 150);
    // ��Ծ��������ʱ�䣻Ŀ��㣻�߶ȣ�ʵ����Ծ�ĸ߶ȣ���ߵ㣩������
   CCJumpBy jumpBy = CCJumpBy. action(2, pos, 100, 2);
	CCRotateBy scaleBy = CCRotateBy.action(0.2f, 100);//100��˳ʱ��100��
	//����ִ�ж���
	CCSpawn ccSpawn = CCSpawn.actions(jumpBy, scaleBy);
	//ÿѭ��һ��ͣһ��
	CCSequence ccSequence = CCSequence.actions(ccSpawn, ccSpawn.reverse(),CCDelayTime.action(1));
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
   CCSprite sprite = fliX1();
   sprite.setAnchorPoint(0.5F, 0.5F);
   
   sprite.runAction(ccRepeatForever);
}


private void move(){
	//�����յ�
	CGPoint point = CGPoint.ccp(300, 150);
	CCSprite sprite = fliX();
	CCMoveBy moveBy = CCMoveBy.action(1, point);
	// ����˳��ִ��
			// ��������������
	CCSequence ccSequence = CCSequence.actions(moveBy, moveBy.reverse());

	// ������ִ��һ�鶯��
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
	sprite.runAction(ccRepeatForever);

}
private void jump(){
	CGPoint pos = CGPoint.ccp (300, 150);
    // ��Ծ��������ʱ�䣻Ŀ��㣻�߶ȣ�ʵ����Ծ�ĸ߶ȣ���ߵ㣩������
   CCJumpBy jumpBy = CCJumpBy. action(2, pos, 100, 2);

   CCSprite sprite = fliX1();
   sprite.runAction(jumpBy);

}

}
