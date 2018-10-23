package Utilities;

import java.awt.*;

public class Vector {
    public float x;
    public float y;
    public float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector addVector(Vector b)
    {
        return new Vector(this.x+b.x,this.y+b.y,this.z+b.z);
    }
    public Vector multiplyby(float a)
    {
        return new Vector(this.x*a,this.y*a,this.z*a);
    }
    public  Vector divideby(float a)
    {
        return new Vector(this.x/a,this.y/a,this.z/a);
    }

    public Vector Interpolar(int w, int h) {
        return new Vector((this.x + 1) * w * 0.5f, (this.y + 1) * h * 0.5f, this.z);
    }

    public void VectortoString() {
        System.out.println("\n" + " x:" + x + " y:" + y + " z:" + z);
    }

    public Color VectortoColor()
    {
        return new Color((int)this.x,(int)this.y,(int)this.z);
    }
    public Vector multi(Vector a)
    {
        return new Vector(this.x*a.x,this.y*a.y,this.z*a.z);
    }
}
