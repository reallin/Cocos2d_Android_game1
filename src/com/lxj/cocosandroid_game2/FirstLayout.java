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
	// 设置layer可以处理touch的开关
			this.setIsTouchEnabled(true);// 在一个场景所有的layer中，有且仅有一个layer能够处理用户的touch
			// 图片、坐标、画自己的方法;
			
	fliX();
}
public CCSprite fliX1(){
	if(leaf==null){
	leaf = CCSprite.sprite("bg.png");// 图片资源assets文件夹下

	// this.addChild(leaf);
	this.addChild(ccSprite,1,TAG_X1);

	//Log.i(TAG, "x:" + leaf.getPosition().x + "y:" + leaf.getPosition().y);

	// 为什么在左下角展示：坐标原点左下角
	// 图片为什么显示了一角（1/4）：锚点
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

	//转成opengl下的坐标点
	CGPoint cgPoint = this.convertTouchToNodeSpace(event); 
	boolean flag = CGRect.containsPoint(sprite.getBoundingBox(), cgPoint);
	boolean flag1 = CGRect.containsPoint(sprite1.getBoundingBox(), cgPoint);

	if(flag){
		//设置透明度
		//sprite.setOpacity(100);
		/*//设置是否可见
		sprite.setVisible(false);
		//删除自己
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
	CCRotateBy scaleBy = CCRotateBy.action(0.2f, 100);//100是顺时针100度
	fliX1().runAction(scaleBy);
}

private void changeColor(){
	//改变颜色幅度
	ccColor3B c = ccColor3B.ccc3(0,-100,0);
	CCTintBy tintBy = CCTintBy.action(1, c);
	CCLabel cLabel = CCLabel.makeLabel("改变字体", "", 20);//创建字体，中间参数为ttf文件，20为字体大小
	cLabel.setColor(ccColor3B.ccc3(255,228, 0));//初始值
	CCSequence ccSequence = CCSequence.actions(tintBy, tintBy.reverse());
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
	this.addChild(cLabel);
	cLabel.runAction(ccRepeatForever);
}
private void jump1(){
	CGPoint pos = CGPoint.ccp (300, 150);
    // 跳跃：启动；时间；目标点；高度：实际跳跃的高度（最高点）；次数
   CCJumpBy jumpBy = CCJumpBy. action(2, pos, 100, 2);
	CCRotateBy scaleBy = CCRotateBy.action(0.2f, 100);//100是顺时针100度
	//并行执行动画
	CCSpawn ccSpawn = CCSpawn.actions(jumpBy, scaleBy);
	//每循环一次停一秒
	CCSequence ccSequence = CCSequence.actions(ccSpawn, ccSpawn.reverse(),CCDelayTime.action(1));
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
   CCSprite sprite = fliX1();
   sprite.setAnchorPoint(0.5F, 0.5F);
   
   sprite.runAction(ccRepeatForever);
}


private void move(){
	//设置终点
	CGPoint point = CGPoint.ccp(300, 150);
	CCSprite sprite = fliX();
	CCMoveBy moveBy = CCMoveBy.action(1, point);
	// 按照顺序执行
			// 将动作串联起来
	CCSequence ccSequence = CCSequence.actions(moveBy, moveBy.reverse());

	// 反复的执行一组动作
	CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccSequence);
	sprite.runAction(ccRepeatForever);

}
private void jump(){
	CGPoint pos = CGPoint.ccp (300, 150);
    // 跳跃：启动；时间；目标点；高度：实际跳跃的高度（最高点）；次数
   CCJumpBy jumpBy = CCJumpBy. action(2, pos, 100, 2);

   CCSprite sprite = fliX1();
   sprite.runAction(jumpBy);

}

}
