package Utilities;

public class VertexProcessor {
    float PI = 3.14f;
    Matrix4x4 obj2world = new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(0, 0, 0, 1));

    public Matrix4x4 setLookat(Vector eye, Vector center, Vector up) {
        Vector f = center.subtract(eye);
        f.Normalize();
        Vector s = f.cross(up);
        Vector u = s.cross(f);
        return new Matrix4x4(new Vector4(s.x, u.x, -f.x, 0), new Vector4(s.y, u.y, -f.y, 0), new Vector4(s.z, u.z, -f.z, 0), new Vector4(eye.negate(), 1));
    }

    public Matrix4x4 multiByTranslation(Vector v) {

        Matrix4x4 m = new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(v, 1));
        return obj2world.multiplaymatrix(m);
    }

    public Matrix4x4 setPerspective(float fovy, float aspect, float near, float far) {

        fovy *= PI / 360;
        float f = (float) (Math.cos(fovy) / Math.sin(fovy));
        return new Matrix4x4(new Vector4(f / aspect, 0, 0, 0), new Vector4(0, f, 0, 0), new Vector4(0, 0, (far + near) / (near - far), -1), new Vector4(0, 0, (2 * far * near) / (near - far), 0));
    }

    public Matrix4x4 multiByScale(Vector v) {
        Matrix4x4 m = new Matrix4x4(new Vector4(v.x, 0, 0, 0), new Vector4(0, v.y, 0, 0), new Vector4(0, 0, v.z, 0), new Vector4(0, 0, 0, 1));
        return obj2world.multiplaymatrix(m);

    }

    public Matrix4x4 multiByRototation(float a, Vector v) {
        float s = (float) Math.sin(a * PI / 180);
        float c= (float)Math.cos(a*PI/180);
        Matrix4x4 m= new Matrix4x4(new Vector4(v.x*v.x*(1-c)+c,v.y*v.x*(1-c)+v.z*s,v.x*v.z*(1-c)-v.y*s,0),
                                    new Vector4(v.x*v.y*(1-c)-v.z*s,v.y*v.y*(1-c)+c,v.y*v.z*(1-c)+v.x*s,0),
                                    new Vector4(v.x*v.z*(1-c)+v.y*s,v.y*v.z*(1-c)-v.x*s,v.z*v.z*(1-c)+c,0),
                                    new Vector4(0,0,0,1));
        return obj2world.multiplaymatrix(m);

    }
}
