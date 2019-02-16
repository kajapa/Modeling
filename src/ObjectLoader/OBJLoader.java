package ObjectLoader;

import Utilities.Matrix4x4;
import Utilities.Vector;

import java.io.*;

public class OBJLoader {

    public OBJLoader() {
    }

    public static Model3D loadModel(File f) throws FileNotFoundException, IOException {
       //
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model3D m = new Model3D();
        String line;


        while ((line = reader.readLine()) != null) {

            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);

                    m.verticies.add(new Vector(x, y, z));

            }
            else if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);


                  m.normals.add(new Vector(x, y, z));

            }


            else if (line.startsWith("f ")) {



                int v1 = Integer.valueOf(line.split(" ")[1].split("/")[0]);
                int n1 = Integer.valueOf(line.split(" ")[1].split("/")[2]);


                int v2 = Integer.valueOf(line.split(" ")[2].split("/")[0]);
                int n2 = Integer.valueOf(line.split(" ")[2].split("/")[2]);


                int v3 = Integer.valueOf(line.split(" ")[3].split("/")[0]);
                int n3 = Integer.valueOf(line.split(" ")[3].split("/")[2]);



                m.faces.add(new Face(v1,v2,v3,n1,n2,n3));

            }


        }

       // long lEndTime = System.currentTimeMillis();
      //  long output = lEndTime - lStartTime;

       // System.out.println("Time OBJLOADER: " + output + " ms");
        reader.close();

        return m;
    }
}
