package Project0;
/**
 * Body
 */
public class Body {

    /**
     * Its current x position
     */
    public double xxPos;

    /**
     * Its current y position
     */
    public double yyPos;

    /**
     * Its current velocity in the x direction
     */
    public double xxVel;

    /**
     * Its current velocity in the y direction
     */
    public double yyVel;

    /**
     * Its mass
     */
    public double mass;

    /**
     * The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)
     * 两个构造方法，生成两个body
     */
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    /**
     * calcDistance 计算两个Body之间的距离（和所提供的参照Body相比）
     * 返回参数r
     *
     */
    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);//返回所有参数的平方和的平方根
        return r;
    }

    /**
     * calcForceExertedBy 该calcForceExertedBy方法采用Body，然后返回一个双精度值，
     * 以描述给定物体施加在该物体上的力。您应该calcDistance在此方法内部调用该方法。
     * 例如， samh.calcForceExertedBy(rocinante)对于“仔细检查您的理解”中的数字，返回1.334 ⋅10− 9。
     *
     * 完成后calcForceExertedBy，重新编译并运行下一个单元测试。
     * 就是要你算两个物体之间的力，那这个力应该是
     */
    public double calcForceExertedBy(Body b){
        double G = 6.67e-11;//
        double r1=calcDistance(b);
        double F=G*this.mass*b.mass/(r1*r1);
        return F;
    }
    /**
     * calcNetForceExertedByX和CalcNetForceExertedByY
     *
     * 写入方法calcNetForceExertedByX和calcNetForceExertedByY
     * 每个收进的数组BodyS和计算的净X和所有机构在目前的身体数组中施加净ÿ力。例如，考虑以下代码片段：
     *
     *
     * Body[] allBodys = {samh, rocinante, aegir};
     * samh.calcNetForceExertedByX(allBodys);
     * samh.calcNetForceExertedByY(allBodys);
     *
     *
     * 这里的两个调用将返回“仔细检查您的理解”中给出的值。
     *
     * 当您实现这些方法时，请记住Bodys不能对自己施加重力！
     * 您能想到为什么会这样吗（提示：宇宙可能会自身崩溃，摧毁包括您在内的所有事物）？
     * 为避免此问题，请忽略数组中与当前主体相同的任何主体。要比较两个主体，请使用.equals方法代替==：
     * （ samh.equals(samh)将返回true）。在第二周，我们将说明差异。
     *
     *
     */

    /**
     * 给出FX和FY
     * Fx=F*dx/r
     * Fy=F*dy/r
     */
    public double calcForceExertedByX(Body b) {
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }

    /**
     * Calcualte the net force in x direction and y direction
     * 多个星体在x和y方向的力的总和
     * 思路：列一个数组，把所有星体在X方向（y方向）的力加起来
     */
    public double calcNetForceExertedByX(Body[] bs) {
        double FxNet = 0;
        for (Body c : bs) {
            if (!this.equals(c)) {
                //自己不能给自己施加重力
                FxNet += this.calcForceExertedByX(c);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Body[] bs) {
        double FyNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                //自己不能给自己施加重力
                FyNet += this.calcForceExertedByY(b);
            }
        }
        return FyNet;
    }

    /**
     * 接下来，您将添加一种方法，该方法确定施加在身体上的力将使该身体加速多少，并确定在短时间内身体的速度和位置发生的变化 dŤ
     * 也就是说算出加速度、速度和位置的变化
     *
     * 需要遵循的步骤：
     * 1.使用提供的计算加速度 X-和 ÿ-势力。
     * 2.通过使用加速度和当前速度来计算新速度。回想一下，加速度描述了单位时间内速度的变化，因此新的速度为（vX+ dŤ ⋅一个X，vÿ+ dŤ ⋅一个ÿ）。
     * 3.通过使用在步骤2中计算的速度和当前位置来计算新位置。新职位是（pX+ dŤ ⋅vX，pÿ+ dŤ ⋅vÿ）
     *
     *
     *
     */
    public void update(double dt,double fx,double fy){
        double Ax=fx/this.mass;
        double Ay=fy/this.mass;
        this.xxVel += Ax * dt;
        this.yyVel += Ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;//这些属性都是更新的
    }



    /**
     * Draw the picture of the Body according to its position
     * 用draw.picture方法
     */
    public void draw() {

        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }


}