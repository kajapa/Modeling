package Utilities;

public class Vector4 {
   public float x;
    public float y;
    public  float z;
    public  float w;

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Vector4(){

        this.x =0;
        this.y =0;
        this.z =0;
        this.w =1;
    }
    public Vector4(Vector v,float w){
        this.x =v.x;
        this.y =v.y;
        this.z =v.z;
        this.y = w;
    }

    public float dot(Vector4 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z +this.w *v.w;
    }
    public String VectortoString(){
        return this.x +"\n"+this.y +"\n"+this.z +"\n"+this.w;
    }
}

