package Primitives;

import Utilities.Vector;

import java.awt.*;

public abstract class Object {
    public Vector col1;
    public Vector col2;
    public Vector col3;

    public Object(Vector col1, Vector col2, Vector col3) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }

    public abstract boolean SetPixel(int x, int y);
    public abstract boolean CheckQuad(int x,int y);

    public abstract Color GetInterpolarColor(int x, int y);
}
