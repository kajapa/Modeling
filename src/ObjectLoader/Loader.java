package ObjectLoader;

import Light.DirectionalLight;
import Light.Light;
import Primitives.Triangle;
import Utilities.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Loader {

    public List<Triangle> LoadTriangles(String file,  int width, int height) {
        //long startTime = System.nanoTime();
        long lStartTime = System.currentTimeMillis();


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


            Vector v1 = m.verticies.get(face.v1 - 1);


            Vector v2 = m.verticies.get(face.v2 - 1);


            Vector v3 = m.verticies.get(face.v3 - 1);


            Vector n1 = m.normals.get(face.n1 - 1);
            Vector n2 = m.normals.get(face.n2 - 1);
            Vector n3 = m.normals.get(face.n3 - 1);



            result.add(new Triangle(v1, v2, v3, n1, n2, n3,  width, height));


        }
       long lEndTime = System.currentTimeMillis();
        long output = lEndTime - lStartTime;

        System.out.println("Time: " + output + " ms");
        return result;

    }

}
