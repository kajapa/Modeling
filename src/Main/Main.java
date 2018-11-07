package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Painting.Painting;
import Primitives.Object;
import Primitives.Triangle;
import Utilities.Matrix4x4;
import Utilities.Vector;
import Utilities.Vector4;


public class Main extends JFrame {

    int width = 800;
    int height = 600;
    Vector red= new Vector(255,0,0);
    Vector blue= new Vector(0,0,255);
    Vector green= new Vector(0,255,0);
    Vector orange= new Vector(255, 136, 0);
    Vector pink= new Vector(251, 4, 120);
    Vector yellow= new Vector(255, 255, 0);

    List<Object> objects = new ArrayList<Object>();
    Triangle t = new Triangle(new Vector(-1, 1, 0.5f),new Vector(1, 1, 0.5f), new Vector(1, -1, -1) , red,green,blue, width, height);
    Triangle t2= new Triangle(new Vector(-1, -1, 0),new Vector(1, 1, 0), new Vector(1, -1, 0) , orange,pink,yellow, width, height);
    public Main() throws IOException {
      objects.add(t);
      objects.add(t2);
        super.setSize(width, height);

        Painting paint = new Painting(objects, width, height, Color.YELLOW);


        super.setLayout(new BorderLayout());

        super.setTitle("Modeling");

        super.setLocation(0, 0);
        super.setResizable(false);
        super.add(paint);
        Vector4 a= new Vector4(1,2,3,4);
        Vector4 b= new Vector4(12,43,54,121);
        Vector4 c= new Vector4(21,65,532,54);
        Vector4 d= new Vector4(53,26,9,90);
        Matrix4x4 m = new Matrix4x4(a,b,c,d);
        m.MatrixtoString();
        System.out.println("=============================");
        Vector4 e= new Vector4(789,651,54,13);
        Vector4 f= new Vector4(64,18,16,48);
        Vector4 g= new Vector4(156,15,85,185);
        Vector4 h= new Vector4(53,26,9,90);
        Matrix4x4 n = new Matrix4x4(e,f,g,h);
        m.multiplaymatrix(n).MatrixtoString();




        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}
