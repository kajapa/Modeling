package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ObjectLoader.Loader;
import Painting.Painting;
import Primitives.Object;
import Primitives.Triangle;
import Utilities.Matrix4x4;
import Utilities.Vector;
import Utilities.Vector4;
import Utilities.VertexProcessor;


public class Main extends JFrame {

    int width = 800;
    int height = 600;
    Vector red = new Vector(255, 0, 0);
    Vector blue = new Vector(0, 0, 255);
    Vector green = new Vector(0, 255, 0);
    Vector orange = new Vector(255, 136, 0);
    Vector pink = new Vector(251, 4, 120);
    Vector yellow = new Vector(255, 255, 0);

    List<Triangle> objects = new ArrayList<Triangle>();
    Triangle t = new Triangle(new Vector(-1, 1, 0.5f), new Vector(1, 1, 0.5f), new Vector(1, -1, -1), red, green, blue, width, height);
    Triangle t2 = new Triangle(new Vector(-1, -1, 0), new Vector(1, 1, 0), new Vector(1, -1, 0), orange, pink, yellow, width, height);
    Loader load = new Loader();
    List<Triangle> triangles = new ArrayList<Triangle>();

    public Main() throws IOException {
       // objects.add(t);
        //objects.add(t2);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

        VertexProcessor vt = new VertexProcessor(orange, pink, yellow, width, height);
        vt.setPerspective(45, 1, 1, 100);
        vt.setLookat(new Vector(0,0,-100000), new Vector(0,0,0),new Vector(0,1,0));
        vt.Obj2world();
       //vt.multiByTranslation(new Vector(100,-30000,0));
        vt.multiByRototation(90,new Vector(1,0,0));
        //vt.multiByScale(new Vector(0.5f,0.5f,0.5f));
        vt.PrepareTransformation();


     objects=  load.LoadTriangles("czajnik.obj", orange, pink, yellow, width, height);

        Painting paint = new Painting(vt.TransformTriangles(objects), width, height, Color.YELLOW);

        super.setLayout(new BorderLayout());

        super.setTitle("Modeling");


        super.setLocation(0, 0);
        super.setResizable(false);
        super.add(paint);
        super.setVisible(true);
        Vector4 a = new Vector4(1, 2, 3, 4);
        Vector4 b = new Vector4(12, 43, 54, 121);
        Vector4 c = new Vector4(21, 65, 532, 54);
        Vector4 d = new Vector4(53, 26, 9, 90);
        Matrix4x4 m = new Matrix4x4(a, b, c, d);
        m.MatrixtoString();
        System.out.println("=============================");
        Vector4 e = new Vector4(789, 651, 54, 13);
        Vector4 f = new Vector4(64, 18, 16, 48);
        Vector4 g = new Vector4(156, 15, 85, 185);
        Vector4 h = new Vector4(53, 26, 9, 90);
        Matrix4x4 n = new Matrix4x4(e, f, g, h);
        m.multiplaymatrix(n).MatrixtoString();


    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}
