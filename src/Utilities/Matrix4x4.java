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
                        temp = new Vector4(this.matrix[0].a, this.matrix[1].a, this.matrix[2].a, this.matrix[3].a);
                        break;
                    case 1:
                        temp = new Vector4(this.matrix[0].b, this.matrix[1].b, this.matrix[2].b, this.matrix[3].b);
                        break;
                    case 2:
                        temp = new Vector4(this.matrix[0].c, this.matrix[1].c, this.matrix[2].c, this.matrix[3].c);
                        break;
                    case 3:
                        temp = new Vector4(this.matrix[0].d, this.matrix[1].d, this.matrix[2].d, this.matrix[3].d);
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
        float[] x = {v.a, v.b, v.c, v.d};
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

        Vector4 a = new Vector4(this.matrix[0].a, this.matrix[1].a, this.matrix[2].a, this.matrix[3].a);
        Vector4 b = new Vector4(this.matrix[0].b, this.matrix[1].b, this.matrix[2].b, this.matrix[3].b);
        Vector4 c = new Vector4(this.matrix[0].c, this.matrix[1].c, this.matrix[2].c, this.matrix[3].c);
        Vector4 d = new Vector4(this.matrix[0].d, this.matrix[1].d, this.matrix[2].d, this.matrix[3].d);

        return new Matrix4x4(a, b, c, d);
    }

    public void MatrixtoString() {
        System.out.println(this.matrix[0].a + "," + this.matrix[1].a + "," + this.matrix[2].a + "," + this.matrix[3].a);
        System.out.println(this.matrix[0].b + "," + this.matrix[1].b + "," + this.matrix[2].b + "," + this.matrix[3].b);
        System.out.println(this.matrix[0].c + "," + this.matrix[1].c + "," + this.matrix[2].c + "," + this.matrix[3].c);
        System.out.println(this.matrix[0].d + "," + this.matrix[1].d + "," + this.matrix[2].d + "," + this.matrix[3].d);
    }

}
