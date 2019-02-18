package Light;

import Utilities.Matrix4x4;
import Utilities.Vector;
import Utilities.Vector4;
import Utilities.VertexProcessor;

public  class DirectionalLight extends  Light {

    public DirectionalLight(Vector position, Vector ambient, Vector diffuse, Vector specular, float shininess,VertexProcessor vp) {
        super(position, ambient, diffuse, specular, shininess, vp);
    }

    public Vector Calculate(Vector pos,Vector normal) {

        Vector N= vp.TransormNormal(normal);
       Vector4 temp =Matrix4x4.multimatrixbyV3(vp.obj2view,pos.negate());
       Vector V=new Vector(temp.x,temp.y,temp.z).Normalize();
       /// N relfect light pos
        Vector R=N.reflect(pos).Normalize();
        Vector localdiff=diffuse.multiplyby(Clamp(position.dot(N))) ;
        Vector localspec=specular.multiplyby((float) Math.pow(Clamp(R.dot(V)),shininess));
        //r normal


        //light pos skalr N normal
        //pomnoz przez diff light

        //skalar R i V potega shin razy specular


        return ambient.addVector(localdiff).addVector(localspec).CheckVector();
    }

    public float Clamp(float in)
    {
        if(in <0)
            in=0;
        else if(in>1)
            in=1;
        return in;
    }
}
