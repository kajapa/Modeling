package Light;

import Utilities.Vector;

public class PointLight extends Light {

    public PointLight(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess) {
        super(position, ambient, diffuse, specular, shininess);
    }
}
