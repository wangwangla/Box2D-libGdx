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

### ApplyForce

ApplyForce方法会在刚体上施加力

F = ma   使得物体存在加速度

### ApplyMpulse

不会产生力，直接影响速度   会使得速度叠加，产生新速度

### setLineVelocity

它会将速度变为当前速度。调用之前需要wakeup()


```
//给一个力     f = ma
if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
    body.applyForce(new Vector2(100,0), body.getWorldCenter(),true);
}

//叠加
if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    body.applyLinearImpulse(new Vector2(-100,0), body.getWorldCenter(),true);
}

//设置
if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    body.setLinearVelocity(new Vector2(-100,0));
}
```

## 多边形

- 组合  通过多个组合出一个完整的

- 原生  

```
PolygonShape shape = new PolygonShape();
Vector2 [] vertices = new Vector2[4];
vertices[0] = new Vector2();
vertices[0].set(50, 50);
vertices[1] = new Vector2();
vertices[1].set(200, 50);
vertices[2] = new Vector2();
vertices[2].set(100, 200);
vertices[3] = new Vector2();
vertices[3].set(200, 200);
shape.set(vertices);
```

对于凹多边形会出现错误。

b2Separator但是java框架並麽有找到。 ……

## 存储数据

```
BodyDef bodyDef1 = new BodyDef();
bodyDef1.position.set(230, 130);//记得米和像素的转换关系
//2.Box2D世界工厂更具需求创建createBody()生产刚体
body=Constant.world.createBody(bodyDef1);

FixtureDef shapeRequest1 = new FixtureDef();
shapeRequest1.density = 3;
shapeRequest1.friction = 0.3F;
shapeRequest1.restitution = 0.2F;

PolygonShape shape1 = new PolygonShape();
shape1.setAsBox(50,50);

FixtureDef bgDef1 = new FixtureDef();
bgDef1.shape = shape1;
body.createFixture(bgDef1);

Image image = new Image(new Texture("1.png"));
image.setSize(100,100);

body.setUserData(image);
```

将数据存储在body里面，需要属于到时候在将值取出来。


## 移動鼠標

MouseJointDef def = new MouseJointDef();
def.bodyA :一个
def.bodyB :另一个
def.maxForce : 限制鼠标关节上的最大的力 这里通常需要乘以刚体的
质量multiplier * mass * gravity

### 鼠标是不是划过物体





## 自定义重力

重力是一个向下的力，但是热气球可以在空中。

实现方法有两种：一、加减法抵消重力；二、重置重力。下面我们来详细一下这两种方法。

有方向  有大小的力  重力 = 质量 * 重力加速度

得到身体的总质量。 * @返回质量，通常以千克（kg）为单位。
body.getMass();


//获取身体的质量数据。 * @返回包含身体质量，惯性和中心的结构
body.getMassData();

```
public class MassData {
	/** The mass of the shape, usually in kilograms. **/
	public float mass;

	/** The position of the shape's centroid relative to the shape's origin. **/
	public final Vector2 center = new Vector2();

	/** The rotational inertia of the shape about the local origin. **/
	public float I;
}

```

给重力一个反作用力

```
body.ApplyForce(new b2Vec2(0, -10*body.GetMass()),body.GetWorldCenter());
```


Box2D中的Dynamics包下有一个b2Island类，类中有一个Solve方法，这方法通过gravity形参对刚体进行
重力模拟，代码如下：


能不能解释下getWorldCenter与getWorldPoint的区别?????





























## 监听撞击

```
分类 和 掩码

分类： 分类我是谁
掩码： 我撞击谁

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

存储数据

``` 
body.setUserData(object);
```

获取数据

```
Object userData = body.getUserData();
```

```
float density = -1;     密度
float friction = -1;    摩擦力
float restitution = -1; 反馈
```

## Fixture

```
body.createFixture(shape, 1.0f);
body.createFixture (FixtureDef def)
```


## 认识

Box2d是通过b2AABB表示的
Box2D中的大小是通过设置两个对角顶点upperBound和lowerBound实现的
对于静止不动的就设置睡着。
```
创建世界
```

## （二）

Box 2D计量单位米    

模拟的时长     碰撞后检测的运行次数
```
Constant.world.step(1/60f, 6, 2);
Constant.renderer.render(Constant.world,Constant.combined);
```

刚体创建可以看作是一个工厂，创建步骤：
- 创建刚体，需要提供位置   角度
- 定义刚体形状
    a) density：质量
    b) friction：表面摩擦力
    c) restitution：表面张力，这个值越大，刚体越硬
    d) SetAsBox：设置刚体为矩形
    
---------------
步骤 ：

- 创建世界

那条线可以可以标示圆形刚体的角度，灰色表示刚体静止，不参与Box2D模拟计算，红色表示刚体
为运动的，要进行物理模拟计算，专业分类可以节省CPU开支。






## 关节

创建实例

```
Body body1 = createBody(600);
Body body2 = createBody(0);

//关节
WeldJointDef def = new WeldJointDef();
def.collideConnected = false;
def.initialize(body1,body2,new Vector2(0,0));
Constant.world.createJoint(def);
```

必须要是他的子类

```
public Joint createJoint (JointDef def) {
long jointAddr = createProperJoint(def);
Joint joint = null;
if (def.type == JointType.DistanceJoint) joint = new DistanceJoint(this, jointAddr);
if (def.type == JointType.FrictionJoint) joint = new FrictionJoint(this, jointAddr);
if (def.type == JointType.GearJoint) joint = new GearJoint(this, jointAddr, ((GearJointDef) def).joint1, ((GearJointDef) def).joint2);
if (def.type == JointType.MotorJoint) joint = new MotorJoint(this, jointAddr);
if (def.type == JointType.MouseJoint) joint = new MouseJoint(this, jointAddr);
if (def.type == JointType.PrismaticJoint) joint = new PrismaticJoint(this, jointAddr);
if (def.type == JointType.PulleyJoint) joint = new PulleyJoint(this, jointAddr);
if (def.type == JointType.RevoluteJoint) joint = new RevoluteJoint(this, jointAddr);
if (def.type == JointType.RopeJoint) joint = new RopeJoint(this, jointAddr);
if (def.type == JointType.WeldJoint) joint = new WeldJoint(this, jointAddr);
if (def.type == JointType.WheelJoint) joint = new WheelJoint(this, jointAddr);
if (joint == null) throw new GdxRuntimeException("Unknown joint type: " + def.type); 
```

两个刚体保持一致,做某种运动   


### DistanceJointDef

```
Body body1 = createBody(800);
Body body2 = createBody(188);
DistanceJointDef  jointDef = new DistanceJointDef();
jointDef.bodyA = body1;
jointDef.bodyB = body2;
jointDef.initialize(body1,body2,new Vector2(Constant.width/2-100,500),new Vector2(Constant.width/2,500));
```

这个可以用自行车的前后轮胎进行类比


## 马达关节





