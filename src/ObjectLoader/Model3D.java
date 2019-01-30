package ObjectLoader;

import Utilities.Vector;

import java.util.ArrayList;
import java.util.List;

public class Model3D {
    public List<Vector> verticies = new ArrayList<Vector>();
    public List<Vector> normals = new ArrayList<Vector>();
    public List<Face> faces = new ArrayList<Face>();
    public Model3D() {

    }
}

