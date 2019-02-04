package Utilities;

import Primitives.Triangle;
import net.jafama.FastMath;

import java.util.ArrayList;
import java.util.List;

public class VertexProcessor {
    public Vector col1;
    public Vector col2;
    public Vector col3;
    public int width;
    public int height;

    public VertexProcessor(Vector col1, Vector col2, Vector col3, int width, int height) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.width = width;
        this.height = height;
    }

    float PI = 3.14f;
    public Matrix4x4 obj2world = new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(0, 0, 0, 1));
    public Matrix4x4 world2view = new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(0, 0, 0, 1));
    public Matrix4x4 view2proj = new Matrix4x4();
    public Matrix4x4 obj2view = new Matrix4x4();
    public Matrix4x4 obj2proj = new Matrix4x4();

    public void setLookat(Vector eye, Vector center, Vector up) {
        Vector f = center.subtract(eye);
        f.Normalize();
        Vector s = f.cross(up);
        Vector u = s.cross(f);
        world2view = new Matrix4x4(new Vector4(s.x, u.x, -f.x, 0), new Vector4(s.y, u.y, -f.y, 0), new Vector4(s.z, u.z, -f.z, 0), new Vector4(eye.negate(), 1));
    }

    public void multiByTranslation(Vector v) {

        Matrix4x4 m = new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(v, 1));
        obj2world=obj2world.multiplaymatrix(m);
    }

    public void setPerspective(float fovy, float aspect, float near, float far) {

        fovy *= PI / 360;
        float f = (float) (Math.cos(fovy) / Math.sin(fovy));
        view2proj = new Matrix4x4(new Vector4(f / aspect, 0, 0, 0), new Vector4(0, f, 0, 0),
                new Vector4(0, 0, (far + near) / (near - far), -1),
                new Vector4(0, 0, (2 * far * near) / (near - far), 0));
    }

    public void multiByScale(Vector v) {
        Matrix4x4 m = new Matrix4x4(new Vector4(v.x, 0, 0, 0), new Vector4(0, v.y, 0, 0), new Vector4(0, 0, v.z, 0), new Vector4(0, 0, 0, 1));
        obj2world=  obj2world.multiplaymatrix(m);

    }

    public void multiByRototation(float a, Vector v) {
        float s = (float) Math.sin(a * PI / 180);
        float b =(float)FastMath.sin(a * PI / 180);

        float c = (float) Math.cos(a * PI / 180);
        float d=(float) FastMath.cos(a * PI / 180);
      v= v.Normalize();
        Matrix4x4 m = new Matrix4x4(new Vector4(v.x * v.x * (1 - c) + c, v.y * v.x * (1 - c) + v.z * s, v.x * v.z * (1 - c) - v.y * s, 0),
                new Vector4(v.x * v.y * (1 - c) - v.z * s, v.y * v.y * (1 - c) + c, v.y * v.z * (1 - c) + v.x * s, 0),
                new Vector4(v.x * v.z * (1 - c) + v.y * s, v.y * v.z * (1 - c) - v.x * s, v.z * v.z * (1 - c) + c, 0),
                new Vector4(0, 0, 0, 1));
        obj2world= obj2world.multiplaymatrix(m);

    }

    public Matrix4x4 ResetMatrix() {
        return new Matrix4x4(new Vector4(1, 0, 0, 0), new Vector4(0, 1, 0, 0), new Vector4(0, 0, 1, 0), new Vector4(0, 0, 0, 1));
    }
    public void Obj2world(){
        obj2world.matrix[0]= new Vector4(1, 0, 0, 0);
        obj2world.matrix[1]=new Vector4(0, 1, 0, 0);
        obj2world.matrix[2]=new Vector4(0, 0, 1, 0);
        obj2world.matrix[3]=new Vector4(0, 0, 0, 1);

    }

    public void PrepareTransformation() {

        obj2view = world2view.multiplaymatrix(obj2world);
        obj2proj = view2proj.multiplaymatrix(obj2view);
    }

    public Vector Transform(Vector v) {
        Vector4 temp = Matrix4x4.multimatrixbyV3(obj2proj, v);
        return new Vector(temp.x / temp.w, temp.y / temp.w, temp.z / temp.w);
    }

    public List<Triangle> TransformTriangles(List<Triangle> objects) {
        List<Triangle> result = new ArrayList<Triangle>();
        for (Triangle tr : objects) {

            result.add(new Triangle(Transform(tr.a), Transform(tr.b), Transform(tr.c), col1, col2, col3, width, height));
        }
        return result;
    }


}
