package demo;
 
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;

/** Sample 4 - how to trigger repeating actions from the main update loop.
 * In this example, we make the player character rotate. */
public class HelloLoop extends SimpleApplication {
    long prevTime = -1;
 
    public static void main(String[] args){
        HelloLoop app = new HelloLoop();
        app.start();
    }
    
    public static final float maxAngle = 30.0f/180.0f*3.141592653f;
 
    protected Node body;
    protected Node leftUpLeg;
    protected Node rightUpLeg;
    protected Node leftDownLeg;
    protected Node rightDownLeg;
    protected Node leftHip;
    protected Node rightHip;
    protected Node leftKnee;
    protected Node rightKnee;
    protected Node leftShoulder;
    protected Node rightShoulder;
    protected Node leftArm;
    protected Node rightArm;
    protected Node neck;
    protected Node head;
    protected float alpha = 0.0f;
    protected float leftAngle = 0.0f;
    protected float rightAngle = 0.0f;
    protected float leftDir = -1.0f;
    protected float rightDir = 1.0f;
 
    @Override
    public void simpleInitApp() {
        Box b;
        Geometry g;
        Material mat;
        
        body  = new Node("body");
        leftUpLeg  = new Node("left");
        rightUpLeg  = new Node("right");
        leftDownLeg = new Node("leftDownLeg");
        rightDownLeg = new Node("rightDownLeg");
        leftHip  = new Node("leftHip");
        rightHip  = new Node("rightHip");        
        leftKnee = new Node("leftKnee");
        rightKnee = new Node("rightKnee");
        leftShoulder = new Node("leftShoulder");
        rightShoulder = new Node("rightShoulder");
        leftArm = new Node("leftArm");
        rightArm = new Node("rightArm");
        neck = new Node("neck");
        head = new Node("head");
        
 
        b = new Box(new Vector3f(0, 0.85f, 0), 1.0f, 1.1f, 0.2f);
        g = new Geometry("body geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        g.setMaterial(mat);
        body.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.6f, 0.1f);
        g = new Geometry("left up leg geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        g.setMaterial(mat);
        leftUpLeg.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.6f, 0.1f);
        g = new Geometry("right up leg geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        g.setMaterial(mat);
        rightUpLeg.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.6f, 0.1f);
        g = new Geometry("left down leg geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        g.setMaterial(mat);
        leftDownLeg.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.6f, 0.1f);
        g = new Geometry("right down leg geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        g.setMaterial(mat);
        rightDownLeg.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.8f, 0.1f);
        g = new Geometry("left arm geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Cyan);
        g.setMaterial(mat);
        leftArm.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.1f, 0.8f, 0.1f);
        g = new Geometry("right arm geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Cyan);
        g.setMaterial(mat);
        rightArm.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.2f, 0.2f, 0.2f);
        g = new Geometry("neck geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        g.setMaterial(mat);
        neck.attachChild(g);
        
        b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        g = new Geometry("head geom", b);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Orange);
        g.setMaterial(mat);
        head.attachChild(g);
        
        leftKnee.attachChild(leftDownLeg);
        rightKnee.attachChild(rightDownLeg);
        
        leftUpLeg.attachChild(leftKnee);
        rightUpLeg.attachChild(rightKnee);

        leftHip.attachChild(leftUpLeg);
        rightHip.attachChild(rightUpLeg);
        
        leftShoulder.attachChild(leftArm);
        rightShoulder.attachChild(rightArm);
        
        neck.attachChild(head);
        
        body.attachChild(leftShoulder);
        body.attachChild(rightShoulder);
        body.attachChild(neck);
        body.attachChild(rightHip);
        body.attachChild(leftHip);
        rootNode.attachChild(body);
        
        leftKnee.move(0, -0.4f, 0);
        rightKnee.move(0, -0.4f, 0);
        
        leftUpLeg.move(-1f, -0.8f, 0);
        rightUpLeg.move(1f, -0.8f, 0);
        
        leftDownLeg.move(0, -0.8f, 0);
        rightDownLeg.move(0, -0.8f, 0);
        
        leftShoulder.move(-1.1f, 1.8f, 0);
        rightShoulder.move(1.1f, 1.8f, 0);
        
        leftArm.move(0, -0.65f, 0);
        rightArm.move(0, -0.65f, 0);
        
        neck.move(0, 2.15f, 0);
        head.move(0, 0.65f, 0);
    }
 
    protected float leftKneeAngle = 0, rightKneeAngle = 0;
    
    /* This is the update loop */
    @Override
    public void simpleUpdate(float tpf) {
        if(prevTime < 0) {
            prevTime = System.currentTimeMillis();
            return;
        }
        
        long time = System.currentTimeMillis();
        float dt = (float)(time - prevTime)/1000.0f;
        float r = 2.0f;
        float w = 1.0f;
        float v = r*w;
        float leg = 4.0f;
        
        alpha += w*dt;
        
        body.setLocalTranslation(r*(float)Math.cos(alpha), 0, r*(float)Math.sin(alpha));
        body.rotate(0, -w*dt, 0);
        
        if(rightAngle <= -maxAngle || rightAngle >= maxAngle) {
            rightDir *= -1;
        }
        if(leftAngle <= -maxAngle || leftAngle >= maxAngle) {
            leftDir *= -1;
        }
        
        float kl = leftDir*2*v/leg*dt;
        float kr = rightDir*2*v/leg*dt;
        
        if(leftAngle+kl < -maxAngle) {
            kl = -maxAngle - leftAngle;
        }
        if(leftAngle+kl > maxAngle) {
            kl = maxAngle - leftAngle;
        }
        if(rightAngle+kr < -maxAngle) {
            kr = -maxAngle - rightAngle;
        }
        if(rightAngle+kr > maxAngle) {
            kr = maxAngle - rightAngle;
        }
        
        leftAngle += kl;
        rightAngle += kr;

        leftHip.rotate(kl, 0, 0);
        rightHip.rotate(kr, 0, 0);
        
        leftShoulder.rotate((-1) * kl * 2f, 0, 0);
        rightShoulder.rotate((-1) * kr * 2f, 0, 0);
        
        leftKneeAngle += kl;
        rightKneeAngle += kr;
        
        if (leftKneeAngle <= 0){
            kl -= leftKneeAngle;
            leftKneeAngle = 0;
        }
       
        if (rightKneeAngle <= 0){
            kr -= rightKneeAngle;
            rightKneeAngle = 0;
        }
        
        leftKnee.rotate(kl, 0, 0);
        rightKnee.rotate(kr, 0, 0);
       
        prevTime = time;
    }
}