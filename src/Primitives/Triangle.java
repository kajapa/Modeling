package Primitives;

import Light.DirectionalLight;
import Light.Light;
import Utilities.Vector;

import java.awt.*;


public class Triangle  {
    public Vector a;
    public Vector b;
    public Vector c;
    public Vector n1;
    public Vector n2;
    public Vector n3;
    public Vector col1;
    public Vector col2;
    public Vector col3;
    public Vector tempA;
    public Vector tempB;
   public Vector tempC;
    public  int width;
    public int height;
    int bar = 20;
    float dx12;
    float dx23;
    float dx31;
    float dy12;
    float dy23;
    float dy31;
    float L1;
    float L2;
    float L3;

    float LX32;
    float LY13;
    float LX13;
   public Light DL;


    public Triangle(Vector a, Vector b, Vector c, Vector col1, Vector col2, Vector col3, Vector n1, Vector n2, Vector n3, Light DL, int width, int height) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.n1=n1;
        this.n2=n2;
        this.n3=n3;
        this.DL=DL;

        this.tempA = this.a.Interpolar(width, height );
        this.tempB = this.b.Interpolar(width, height );
        this.tempC = this.c.Interpolar(width, height );
        this.col1=col1;
        this.col2=col2;
        this.col3=col3;





        this.dx12 = this.tempA.x - this.tempB.x;
        this.dx23 = this.tempB.x - this.tempC.x;
        this.dx31 = this.tempC.x - this.tempA.x;
        this.dy12 = this.tempA.y - this.tempB.y;
        this.dy23 = this.tempB.y - this.tempC.y;
        this.dy31 = this.tempC.y - this.tempA.y;

        this.LX32 = this.tempC.x - this.tempB.x;
        this.LY13 = this.tempA.y - this.tempC.y;
        this.LX13 = this.tempA.x - this.tempC.x;

        this.width = width;
        this.height = height;


    }

    public Vector getCol1() {
        return col1;
    }

    public Vector getCol2() {
        return col2;
    }

    public Vector getCol3() {
        return col3;
    }


    public boolean SetPixel(int x, int y) {
        boolean tl1 = false;
        boolean tl2 = false;
        boolean tl3 = false;
        if (dy12 < 0 || (dy12 == 0 && dx12 > 0)) {
            tl1 = true;
            // System.out.println("Check1");
        }

        if (dy23 < 0 || (dy23 == 0 && dx23 > 0)) {
            tl2 = true;
            // System.out.println("Check2");
        }

        if (dy31 < 0 || (dy31 == 0 && dx31 > 0)) {
            tl3 = true;

        }
        float t1 = (dx12) * (y - tempA.y) - (dy12) * (x - tempA.x);
        float t2 = (dx23) * (y - tempB.y) - (dy23) * (x - tempB.x);
        float t3 = (dx31) * (y - tempC.y) - (dy31) * (x - tempC.x);


        if (((t1 > 0 && tl1 == false) || (t1 >= 0 && tl1 == true)) &&
                ((t2 > 0 && tl2 == false) || (t2 >= 0 && tl2)) &&
                ((t3 > 0 && tl3 == false) || (t3 >= 0 && tl3))) {

            return true;
        } else {
            //System.out.println("Nope");
            return false;
        }

    }


    public boolean CheckQuad(int x, int y) {
        float minx = getMin(tempA.x, tempB.x, tempC.x);
        float maxx = getMax(tempA.x, tempB.x, tempC.x);
        float miny = getMin(tempA.y, tempB.y, tempC.y);
        float maxy = getMax(tempA.y, tempB.y, tempC.y);
        minx = Math.max(minx, 0);
        maxx = Math.min(maxx, width - 1);
        miny = Math.max(miny, 0);
        maxy = Math.min(maxy, height - 1);
        if (x > minx && x < maxx && y > miny && y < maxy) {

            return true;
        } else
            return false;
    }


    public Color GetInterpolarColor(int x, int y) {

        Vector Lambda = GetLambda(x, y);
        col1=DL.Calculate(a,n1);
        col2=DL.Calculate(b,n2);
        col3=DL.Calculate(c,n3);

        float red = col1.x * Lambda.x + col2.x * Lambda.y + col3.x * Lambda.z;
        float green = col1.y * Lambda.x + col2.y * Lambda.y + col3.y * Lambda.z;
        float blue = col1.z * Lambda.x + col2.z * Lambda.y + col3.z * Lambda.z;

        Vector result = new Vector(red, green, blue).CheckVector();


        return result.VectortoColor();
    }


    public Vector GetLambda(int x, int y) {
        Vector res;
        L1 = ((dy23 * (x - tempC.x)) + (LX32 * (y - tempC.y))) / ((dy23 * (tempA.x - tempC.x)) + (LX32 * (tempA.y - tempC.y)));

        L2 = ((dy31) * (x - tempC.x) + (LX13) * (y - tempC.y)) / ((dy31) * (tempB.x - tempC.x) + (LX13) * (tempB.y - tempC.y));
        L3 = 1 - L1 - L2;
        res = new Vector(L1, L2, L3);
        return res;

    }

    public float getMax(float v1, float v2, float v3) {
        float[] numbers = {v1, v2, v3};
        float maxValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public float getMin(float v1, float v2, float v3) {
        float[] numbers = {v1, v2, v3};
        float minValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    public float GetDepth(int x, int y) {
        Vector Lambda = GetLambda(x, y);

        return Lambda.x * tempA.z + Lambda.y * tempB.z + Lambda.z * tempC.z;
    }




}
