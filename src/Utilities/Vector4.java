package Utilities;

public class Vector4 {
   public float a;
    public float b;
    public  float c;
    public  float d;

    public Vector4(float a, float b, float c, float d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public Vector4(){

        this.a=0;
        this.b=0;
        this.c=0;
        this.d=1;
    }
    public Vector4(Vector v,float d){
        this.a=v.x;
        this.b=v.y;
        this.c=v.z;
        this.b=d;
    }

    public float dot(Vector4 v) {
        return this.a * v.a + this.b * v.b + this.c * v.c+this.d*v.d;
    }
    public String VectortoString(){
        return this.a+"\n"+this.b+"\n"+this.c+"\n"+this.d;
    }
}

