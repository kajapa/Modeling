package Light;

import Utilities.Vector;
import Utilities.VertexProcessor;

public class Light {
    public Vector position;
    public Vector ambient;
    public Vector diffuse;
    public Vector specular;
    public float shininess;

    public Light(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess) {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }
    public void Calculate(Fragment f, VertexProcessor vp)
    {


    }
}
