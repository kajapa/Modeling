package ObjectLoader;

import Utilities.Matrix4x4;
import Utilities.Vector;

import java.io.*;

public class OBJLoader {


    public OBJLoader() {
    }

    public static Model3D loadModel(File f) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model3D m = new Model3D();
        String line;

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                //System.out.println(new Vector(x, y, z).toString());
                m.verticies.add(new Vector(x, y, z));
               // System.out.println(new Vector(x, y, z).toString());
            }
            else if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);

                m.normals.add(new Vector(x, y, z));

            }


            else if (line.startsWith("f ")) {



                int v1 = Integer.valueOf(line.split(" ")[1].split("/")[0]);


                //System.out.println(v1);
                int v2 = Integer.valueOf(line.split(" ")[2].split("/")[0]);


                int v3 = Integer.valueOf(line.split(" ")[3].split("/")[0]);



                int n = Integer.valueOf(line.split(" ")[1].split("/")[2]);



                //System.out.println(v1+" "+v2+" "+v3+" "+" "+n);
                // m.faces.add(new Face(v1, v2, v3, n));


                m.faces.add(new Face(v1,v2,v3,n));
            }


        }


        reader.close();

        return m;
    }
}
