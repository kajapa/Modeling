package ObjectLoader;

public class Face {
    public int v1, v2, v3;
    public int n1, n2, n3;


    public Face() {
    }

    public Face(int v1, int v2, int v3, int n1, int n2, int n3) {

        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;

        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;

    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }
}
