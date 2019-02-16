package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Light.DirectionalLight;
import Light.PointLight;
import ObjectLoader.Loader;
import ObjectLoader.ObjectsList;
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



    Loader load = new Loader();
    List<Triangle> triangles = new ArrayList<Triangle>();
    ObjectsList OL= new ObjectsList();

    public Main() throws IOException {
       // objects.add(t);
        //objects.add(t2);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

        VertexProcessor vt = new VertexProcessor( width, height);
        vt.setPerspective(45, 1, 1, 100);
        vt.setLookat(new Vector(0,0,-1000), new Vector(0,0,0),new Vector(0,1,0));
        DirectionalLight DR = new DirectionalLight(new Vector(0,1,1),new Vector(0,0,0),new Vector(255,0,0),new Vector(100,100,100),50,vt);
        PointLight PL=new PointLight(new Vector(300,0,0),new Vector(0,0,0),new Vector(255,0,0),new Vector(100,100,100),50,vt);
        super.setBackground(Color.BLACK);


        vt.Obj2world();
       vt.multiByTranslation(new Vector(-300,300,0));
     vt.multiByRototation(60,new Vector(0,1,0));
        vt.multiByScale(new Vector(0.5f,0.5f,0.5f));
        vt.PrepareTransformation();






        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("czajnik.obj", width, height,PL)));
        /*
        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(0,300,0));
       // vt.multiByRototation(90,new Vector(1,0,0));
         vt.multiByScale(new Vector(50,50,50));
        vt.PrepareTransformation();
         OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("monkey.obj", width, height,PL)));

        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(200,250,0));
        // vt.multiByRototation(90,new Vector(1,0,0));
        vt.multiByScale(new Vector(50,50,50));
        vt.PrepareTransformation();
        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("cone.obj",  width, height,PL)));

        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(325,50,0));
        // vt.multiByRototation(90,new Vector(1,0,0));
        vt.multiByScale(new Vector(50,50,50));
        vt.PrepareTransformation();
        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("sphere.obj",  width, height,PL)));


        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(325,-300,0));
        // vt.multiByRototation(90,new Vector(1,0,0));
        vt.multiByScale(new Vector(50,50,50));
        vt.PrepareTransformation();
        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("torus.obj", width, height,PL)));



        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(0,-250,0));
        // vt.multiByRototation(90,new Vector(1,0,0));
        vt.multiByScale(new Vector(50,50,50));
        vt.PrepareTransformation();
        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("cube.obj", width, height,PL)));


        //-------------------------------------------------------------------------------------------------------------------------------------------
        vt.Obj2world();
        vt.multiByTranslation(new Vector(-300,-300,0));
        // vt.multiByRototation(90,new Vector(1,0,0));
        vt.multiByScale(new Vector(0.5f,0.5f,0.5f));
        vt.PrepareTransformation();
        OL.TransferObject(vt.TransformTriangles(load.LoadTriangles("czajnik.obj",  width, height,PL)));*/

        Painting paint = new Painting(OL.result, width, height);


        super.setVisible(true);

        super.setLayout(new BorderLayout());

        super.setTitle("Modeling");


        super.setLocation(0, 0);
        super.setResizable(false);
        super.add(paint);
        super.setVisible(true);



    }
    public float getMax(float v1, float v2, float v3) {

// ... the code being measured ...
        float[] numbers = {v1, v2, v3};
        float maxValue = numbers[0];
        for (int i = 0; i < 3; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
      //float maxValue=Math.max(v1,Math.max(v2,v3));

        return maxValue;
    }

    public float getMin(float v1, float v2, float v3) {

        float[] numbers = {v1, v2, v3};
        float minValue = numbers[0];
        for (int i = 0; i < 3; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
            }
        }

        return minValue;
    }


    public static void main(String[] args) throws IOException {
        new Main();

    }
}
