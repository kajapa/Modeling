package Utilities;

public class Matrix4x4 {
    public Vector4[] matrix = new Vector4[4];

    public Matrix4x4(Vector4 x, Vector4 y, Vector4 z, Vector4 w) {
        this.matrix[0] = x;
        this.matrix[1] = y;
        this.matrix[2] = z;
        this.matrix[3] = w;
    }

    public Matrix4x4() {
    }

    public float[][] multimatrix(float m[][], float n[][]) {
        float mn[][] = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    mn[i][k] += m[i][j] * n[j][k];
                }
            }
        }
        return mn;

    }

    public Matrix4x4 multiplaymatrix(Matrix4x4 m) {
        Matrix4x4 res = new Matrix4x4();
        Vector4 a;
        Vector4 b;
        Vector4 c;
        Vector4 d;
        Vector4 temp = null;
        float x = 0, y = 0, z = 0, w = 0;


        for (int i = 0; i < 4; i++) {

            for (int k = 0; k < 4; k++) {

                switch (i) {
                    case 0:
                        temp = new Vector4(this.matrix[0].x, this.matrix[1].x, this.matrix[2].x, this.matrix[3].x);
                        break;
                    case 1:
                        temp = new Vector4(this.matrix[0].y, this.matrix[1].y, this.matrix[2].y, this.matrix[3].y);
                        break;
                    case 2:
                        temp = new Vector4(this.matrix[0].z, this.matrix[1].z, this.matrix[2].z, this.matrix[3].z);
                        break;
                    case 3:
                        temp = new Vector4(this.matrix[0].w, this.matrix[1].w, this.matrix[2].w, this.matrix[3].w);
                        break;
                    default:

                }

                switch (k) {
                    case 0:
                        x = temp.dot(m.matrix[k]);
                        break;
                    case 1:
                        y = temp.dot(m.matrix[k]);
                        break;
                    case 2:
                        z = temp.dot(m.matrix[k]);
                        break;
                    case 3:
                        w = temp.dot(m.matrix[k]);
                        break;


                }


            }
            res.matrix[i] = new Vector4(x, y, z, w);
        }

        return res.transmatrix();
    }

    public Vector4 multiplyMatrix4(float[][] a, Vector4 v) {
        int m = a.length;
        int n = a[0].length;
        float[] x = {v.x, v.y, v.z, v.w};
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        float[] y = new float[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                y[i] += a[i][j] * x[j];
            }
        }
        return new Vector4(y[0], y[1], y[2], y[3]);
    }

    public Vector4 multiplyMatrix3(float[][] a, Vector v) {
        int m = a.length;
        int n = a[0].length;
        float[] x = {v.x, v.y, v.z, 1};
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        float[] y = new float[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                y[i] += a[i][j] * x[j];
            }
        }

        return new Vector4(y[0], y[1], y[2], y[3]);
    }


    public Matrix4x4 transmatrix() {

        Vector4 a = new Vector4(this.matrix[0].x, this.matrix[1].x, this.matrix[2].x, this.matrix[3].x);
        Vector4 b = new Vector4(this.matrix[0].y, this.matrix[1].y, this.matrix[2].y, this.matrix[3].y);
        Vector4 c = new Vector4(this.matrix[0].z, this.matrix[1].z, this.matrix[2].z, this.matrix[3].z);
        Vector4 d = new Vector4(this.matrix[0].w, this.matrix[1].w, this.matrix[2].w, this.matrix[3].w);

        return new Matrix4x4(a, b, c, d);
    }

    public void MatrixtoString() {
        System.out.println(this.matrix[0].x + "," + this.matrix[1].x + "," + this.matrix[2].x + "," + this.matrix[3].x);
        System.out.println(this.matrix[0].y + "," + this.matrix[1].y + "," + this.matrix[2].y + "," + this.matrix[3].y);
        System.out.println(this.matrix[0].z + "," + this.matrix[1].z + "," + this.matrix[2].z + "," + this.matrix[3].z);
        System.out.println(this.matrix[0].w + "," + this.matrix[1].w + "," + this.matrix[2].w + "," + this.matrix[3].w);
    }

    public Vector4 multimatrixbyV3(Matrix4x4 m, Vector v) {


        return new Vector4(m.matrix[0].x * v.x + m.matrix[1].x * v.y + m.matrix[2].x + m.matrix[3].x,
                m.matrix[0].y * v.x + m.matrix[1].y * v.y + m.matrix[2].y + m.matrix[3].y,
                m.matrix[0].z * v.x + m.matrix[1].z * v.y + m.matrix[2].z + m.matrix[3].z,
                m.matrix[0].w * v.x + m.matrix[1].w * v.y + m.matrix[2].w + m.matrix[3].w);
    }

}
