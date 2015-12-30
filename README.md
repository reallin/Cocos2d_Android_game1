# Cocos2d_Android_game1
用cocos2d_android做的一个demo，显示各种动作，包括移动，跳跃，改变颜色等。先看下效果图：
![](https://github.com/reallin/Cocos2d_Android_game1/blob/master/8.png)
## 使用到的技术
* cocos2d_android
* 动画
## 使用的动作
### 移动
移动是游戏中最常见的动作之一，如植物大战僵尸中的僵尸，子弹等。而Cocos2d中设置移动也很简单，看以下代码：
```java
CGPoint point = CGPoint.ccp(300, 150);
     CCSprite sprite = CCSprite.sprite("bg.img");
     CCMoveTo moveTo = CCMoveTo. action(1, point);
     sprite.runAction(moveTo);
```

### 旋转
旋转的实现也很简单，与Move类似，看下代码：
```java
CCRotateBy scaleBy = CCRotateBy.action(0.2f, 360);//100是顺时针100度
	sprite.runAction(scaleBy);
```
第一个参数依然是时间，第二个参数是度数。旋转是顺时针的。可以看demo的第二棵树。
这里By与To又有点不同，To比较懒，如果是大小180度，它会逆时间转，它的原则是尽量少转。By就比较老实，写多少度，它就转多少。
### 缩放
就是缩小放大这个动作，看我demo中的每一棵树，这树的动作并不是一次性的，而是像个心脏似的一起跳，这里就有两个问题，如何让放大缩小两个动作串联起来？如何使动作循环执行？先不急，后面会系统讲解，先带着这问题继续往下。缩放的代码：
```java
CCScaleBy scaleBy = CCScaleBy.action(0.2f, 1.5f);
     sprite.runAction(scaleBy);
```
### 跳跃
玩过超级玛丽的都知道，小玛丽跳的高度和大玛丽跳的不一样，有些牛X的还能二连跳。其实这个实现起来也很方便，先看一连跳：
```java
CGPoint pos = CGPoint.ccp (300, 150);
            // 跳跃：启动；时间；目标点；高度：实际跳跃的高度（最高点）；次数
           CCJumpBy jumpBy = CCJumpBy. action(2, pos, 100, 1);
           CCSprite sprite = getSprite();
           sprite.runAction(jumpBy);
 ```
pos指定最终跳到的点，CCJumpBy.action第一个参数是时间，第二个参数是终点，第三个参数是跳到的高度（最高点）,最后一个参数是指跳的次数，由此可见，二连跳就很简单了，只要把1修改为2就可以了。
### 贝叶斯曲线
这个听起来很高端，其实就是抛物线状。植物大战僵尸里的花吐出的阳光就走了一条抛物线。实现如下：
```java
CCBezierConfig c = new CCBezierConfig();
           c. controlPoint_1 = CGPoint. ccp(0, 0);
           c. controlPoint_2 = CGPoint. ccp(150, 200);
           c. endPosition = CGPoint. ccp(300, 0);
           CCBezierBy bezierBy = CCBezierBy. action(2, c);
           getSprite().runAction(bezierBy);
```
### 渐快渐慢动作
这个经常用在打斗游戏中，一个怪快速跳到另一个怪身边，杀杀，然后再跳回来，就是一个渐快到渐慢的过程。看下它的代码：
```java
CGPoint pos = CGPoint.ccp (300, 200);
           CCMoveBy moveBy = CCMoveBy. action(2, pos);
           CCEaseIn easeIn = CCEaseIn. action(moveBy, 10);// 渐快：加速运动（加速度恒定）
           CCEaseOut easeOut = (CCEaseOut) easeIn.reverse();
           getSprite().runAction(
                      CCRepeatForever.action(CCSequence.actions(easeIn,
                                CCDelayTime. action(1), easeOut)));
```
这个是我学习cocos2d的敲门砖。还没完善，回头还会继续完善。接下来我也会陆续做几个cocos2d的游戏。做这个纯粹是有兴趣。

