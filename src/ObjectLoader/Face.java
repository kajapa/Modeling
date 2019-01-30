package ObjectLoader;

public class Face {
    public int v1,v2,v3;
    public int n;


    public Face(){}

    public Face(int v1, int v2, int v3, int n) {

        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;

        this.n = n;

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

    public void setN(int n) {
        this.n = n;
    }
}
