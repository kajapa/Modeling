package ObjectLoader;

import Primitives.Triangle;
import Utilities.Matrix4x4;
import Utilities.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public List<Triangle> LoadTriangles(String file, Vector col1, Vector col2, Vector col3, int width, int height) {
        List<Triangle> result = new ArrayList<Triangle>();

       Model3D m = new Model3D();
        try {
            m = OBJLoader.loadModel(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        for (Face face : m.faces) {



          Vector  v1 = m.verticies.get(face.v1 - 1);
            //System.out.println("Wektor1!!"+v1.toString());




            Vector v2 = m.verticies.get(face.v2 - 1);
            // System.out.println("Wektor2!!"+v2.toString());
            // System.out.println("Wektor2!! PO "+v2.Transform(obj2proj).toString());

            Vector v3 = m.verticies.get(face.v3 - 1);
            // System.out.println("Wektor3!!"+v3.toString());
            //  System.out.println("Wektor3 PO "+v3.Transform(obj2proj).toString());


            Vector n = m.normals.get(face.n - 1);

            result.add(new Triangle(v1, v2, v3, col1, col2, col3, width, height));
           // System.out.println("v1: "+v1.toString() +" v2: "+ v2.toString() +" v3: "+ v3.toString() + " normal: " + n.toString());


        }
        return result;

    }

}
