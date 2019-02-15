package Light;

import Utilities.Vector;
import Utilities.VertexProcessor;

public abstract class Light {
    public Vector position;
    public Vector ambient;
    public Vector diffuse;
    public Vector specular;
    public float shininess;
    VertexProcessor vp;

    public Light(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess,VertexProcessor vp) {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
        this.vp=vp;
    }
    public abstract Vector Calculate(Vector pos,Vector normal);

}
