package Primitives;

import Utilities.Vector;

import java.awt.*;


public class Triangle extends Object {
    Vector a;
    Vector b;
    Vector c;
    Vector col1;
    Vector col2;
    Vector col3;
    int width;
    int height;
    int bar = 50;
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


    public Triangle(Vector a, Vector b, Vector c, Vector col1, Vector col2, Vector col3, int width, int height) {
        super(col1, col2, col3);
        this.a = a;
        this.b = b;
        this.c = c;

        this.a = this.a.Interpolar(width, height - bar);
        this.b = this.b.Interpolar(width, height - bar);
        this.c = this.c.Interpolar(width, height - bar);

        this.dx12 = this.a.x - this.b.x;
        this.dx23 = this.b.x - this.c.x;
        this.dx31 = this.c.x - this.a.x;
        this.dy12 = this.a.y - this.b.y;
        this.dy23 = this.b.y - this.c.y;
        this.dy31 = this.c.y - this.a.y;

        this.LX32 = this.c.x - this.b.x;
        this.LY13 = this.a.y - this.c.y;
        this.LX13 = this.a.x - this.c.x;

        this.width = width;
        this.height = height;


    }

    @Override
    public boolean SetPixel(int x, int y) {

        float t1 = (dx12) * (y - a.y) - (dy12) * (x - a.x);
        float t2 = (dx23) * (y - b.y) - (dy23) * (x - b.x);
        float t3 = (dx31) * (y - c.y) - (dy31) * (x - c.x);
        if (t1 > 0 && t2 > 0 && t3 > 0) {

            return true;
        } else {
            //System.out.println("Nope");
            return false;
        }
    }

    @Override
    public boolean CheckQuad(int x, int y) {
        float minx = getMin(a.x, b.x, c.x);
        float maxx = getMax(a.x, b.x, c.x);
        float miny = getMin(a.y, b.y, c.y);
        float maxy = getMax(a.y, b.y, c.y);
        minx = Math.max(minx, 0);
        maxx = Math.min(maxx, width - 1);
        miny = Math.max(miny, 0);
        maxy = Math.min(maxy, height - 1);
        if (x > minx && x < maxx && y > miny && y < maxy) {

            return true;
        } else
            return false;
    }

    @Override
    public Color GetInterpolarColor(int x, int y) {
        Vector Lambda = GetLambda(x, y);

        Vector aL = super.col1.multiplyby(Lambda.x);

        Vector bL = super.col2.multiplyby(Lambda.y);
        Vector cL = super.col3.multiplyby(Lambda.z);
        Vector res = aL.addVector(bL).addVector(cL);
        res.VectortoString();
        return res.VectortoColor();
    }


    public Vector GetLambda(int x, int y) {
        Vector res;
        L1 = ((dy23) * (x - c.x) + (LX32) * (y - c.y)) / ((dy23) * (a.x - c.x) + (LX32) * (a.y - c.y));
        L2 = ((dy31) * (x - c.x) + (LX13) * (y - c.y)) / ((dy31) * (c.x - c.x) + (LX13) * (b.y - c.y));
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


}
