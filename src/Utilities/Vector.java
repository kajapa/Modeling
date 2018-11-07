package Utilities;

import java.awt.*;

public class Vector {
    public float x;
    public float y;
    public float z;
    static float epsilon = 0.0001f;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector negate(){
        return new Vector(this.x*-1,this.y*-1,this.z*-1);
    }

    public Vector addVector(Vector b) {
        return new Vector(this.x + b.x, this.y + b.y, this.z + b.z);
    }
    public Vector subtract(Vector b) {
        return new Vector(this.x - b.x, this.y - b.y, this.z - b.z);
    }

    public Vector multiplyby(float a) {
        return new Vector(this.x * a, this.y * a, this.z * a);
    }

    public Vector divideby(float a) {
        return new Vector(this.x / a, this.y / a, this.z / a);
    }

    public Vector Interpolar(int w, int h) {
        return new Vector((this.x + 1) * w * 0.5f, (this.y + 1) * h * 0.5f, this.z);
    }
    public int getlength(){

        return(int) (Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2)));
    }

    public void VectortoString() {
        System.out.println("\n" + " x:" + x + " y:" + y + " z:" + z);
    }

    public Color VectortoColor() {
        return new Color((int) this.x, (int) this.y, (int) this.z);
    }

    public Vector multi(Vector a) {
        return new Vector(this.x * a.x, this.y * a.y, this.z * a.z);
    }

    public Vector Normalize()
    {
        float len=(float)Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
        if(len>epsilon){
            return  new Vector(this.x/=len,this.y/=len,this.z/=len);
        }
        else
        {
            return new Vector(0,0,0);
        }
    }

    public  Vector cross(Vector v1) {
        return new Vector(this.y * v1.z - this.z * v1.y, this.z * v1.x - this.x * v1.z, this.x * v1.y - this.y * v1.x);
    }
    public float dot(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

public Vector reflect(Vector n)
{
    n=n.Normalize();
    return this.subtract(n.multiplyby(2*n.dot(this)));

}
public Vector saturate(){
        if(this.x<0){
            this.x=0;
        }
        else if(this.x>1){
            this.x=1;
        }
    if(this.y<0){
        this.y=0;
    }
    else if(this.y>1){
        this.y=1;
    }
    if(this.z<0){
        this.z=0;
    }
    else if(this.z>1){
        this.z=1;
    }
    return this;
}
}

