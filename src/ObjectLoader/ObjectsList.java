package ObjectLoader;

import Primitives.Triangle;

import java.util.ArrayList;
import java.util.List;

public class ObjectsList {
   public List<Triangle> result= new ArrayList<Triangle>();
   public Triangle[] res;
    public List<Triangle> TransferObject(List<Triangle> in)
    {


        for(Triangle tr:in)
        {
            result.add(tr);
        }
        return result;
    }
    public void TransfertoArray()
    {
        res=new Triangle[result.size()];
        res=result.toArray(res);



    }


}
