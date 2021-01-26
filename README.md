#Box 2d 

## git 

git init 
git add ./
git commit -m "提交"
git push origin master

## box2d

- 积分步

Box2D是由一些代码构成的积分器，积分器在离散点上做模拟物理运算，它将与游戏动画循环一同运行（时间步的作用）
物理的计算需要60帧

约束器，用于求解器解决模拟中的约束，一次一个，的那个约束会被求解，但是在求解一个的时候会耽误另一个。
处理办法：迭代多次   box2D建议是10次
少的迭代会增加性能降低精度      一个时间步遍历10次约束。

- Api

公差保证其工作  Box2D可以处理0.1到10米之间的移动物体

- body创建过程

```
BodyDef bodyDef = new BodyDef();
bodyDef.type = this.type;
bodyDef.position.set(position.x,position.y);
Body body = Constant.world.createBody(bodyDef);
if (shape == null){
    PolygonShape polygonShape = new PolygonShape();
    polygonShape.setAsBox(width, height);
    shape = polygonShape;
}
FixtureDef fixtureDef = new FixtureDef();
fixtureDef.shape = shape;
if (categoryBits != -1){
    fixtureDef.filter.categoryBits = categoryBits;
}
if (maskBits != -1) {
    fixtureDef.filter.maskBits = maskBits;
}
body.createFixture(fixtureDef);
shape.dispose();
```

- 世界刷新

```
Constant.world.step(1/60f, 6, 2);
Constant.renderer.render(Constant.world,Constant.combined);
```

- 角度转换

```
float degrees = (float) Math.toDegrees(body.getAngle());
```

如果和精灵绑定，如果需要旋转，需要设置旋转的位置中心点。

## 力



## 监听撞击

```
public class WorldContactListener implements ContactListener{
   @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if (fixtureA.getFilterData().categoryBits == Constant.PILL_BIT ||
                fixtureB.getFilterData().categoryBits == Constant.PILL_BIT) {
            // pill
            if (fixtureA.getFilterData().categoryBits == Constant.PLAYER_BIT) {
            }else{
            }
        }
    }
    @Override
    public void endContact(Contact contact) {
    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
```




























