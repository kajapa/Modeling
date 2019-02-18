package Light;

import Utilities.Matrix4x4;
import Utilities.Vector;
import Utilities.Vector4;
import Utilities.VertexProcessor;

public class PointLight extends Light {

    public PointLight(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess, VertexProcessor vp) {
        super(position, ambient, diffuse, specular, shininess, vp);
    }

    @Override
    public Vector Calculate(Vector pos, Vector normal) {
        position = position.Normalize();
        Vector N = vp.TransormNormal(normal).Normalize();
        Matrix4x4 test = vp.obj2view;
        Vector4 temp = Matrix4x4.multimatrixbyV3(vp.obj2view, pos.negate());
        Vector V = new Vector(temp.x, temp.y, temp.z).Normalize();
        Vector L = position.subtract(V).Normalize();
        Vector R = N.reflect(L).Normalize();
        Vector localdiff = diffuse.multiplyby(Clamp(L.dot(N)));
        Vector localspec = specular.multiplyby((float) Math.pow(Clamp(R.dot(V)), shininess));



        return ambient.addVector(localdiff).addVector(localspec).CheckVector();
    }

    public float Clamp(float in) {
        if (in < 0)

            in = 0;


        else if (in > 1)
            in = 1;


        return in;


    }
}
