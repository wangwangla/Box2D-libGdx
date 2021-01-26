#Box 2d 

## git 

git init 
git add ./
git commit -m "提交"
git push origin master

## box2d

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



- 角度转换

```
float degrees = (float) Math.toDegrees(body.getAngle());
```


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




























