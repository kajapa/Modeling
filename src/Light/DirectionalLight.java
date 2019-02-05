package Light;

import Utilities.Vector;

public class DirectionalLight extends Light {
    public DirectionalLight(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess) {
        super(position, ambient, diffuse, specular, shininess);
    }
}
