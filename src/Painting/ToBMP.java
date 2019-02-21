package Painting;

import Primitives.Triangle;
import ij.ImageJ;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import net.sf.image4j.codec.bmp.BMPEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToBMP {

    int width;
    int height;

    private BufferedImage paintImage;
    Triangle[] obj;
    List<Triangle> objects = new ArrayList<Triangle>();
    List<Float> bufforDepth = new ArrayList<Float>();
    float[] bufford;



    public ToBMP(Triangle[] obj, int width, int height) {
        paintImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);



        this.obj = obj;






        this.width = width;
        this.height = height;
        this.bufford=new float[width * height];
        for(int i = 0; i < width * height; i++)
        {
            bufford[i]=-1f;
        }

       /* for (int i = 0; i < width * height; i++) {
            bufforDepth.add(-1f);
        }*/

    }


    public void Draw() throws IOException {


        long lStartTime = System.currentTimeMillis();

        Graphics2D g2 = paintImage.createGraphics();
        int size= obj.length;
        int combo=width*height;
        int w=0;
        int h=0;
        int it=0;
        int t=0;
        for(it=0;it<combo;++it)
        {
            if(w==height){w=0;++h;}
            for ( t=0;t<size;++t) {
                if (obj[t].CheckQuad(h, w) && obj[t].SetPixel(h, w)) {
                    //System.out.println("check1");

                    float depth = obj[t].GetDepth(h, w);
                    if (depth > bufford[h + width * w]) {
                        //System.out.println("check2");


                        g2.setColor(obj[t].GetInterpolarColor(h, w));


                        g2.fillRect(h, w, 1, 1);

                        bufford[h + width * w]= depth;

                    }


                }


            }
            ++w;

        }
        save();



        long lEndTime = System.currentTimeMillis();
        long output = lEndTime - lStartTime;

        System.out.println("Time Canvas Paint: " + output + " ms");  }
    public void save() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
         //BMPEncoder.write(paintImage,  new File("render/savedfile-" + dateFormat.format(date) + ".bmp"));
        MarvinImageIO.saveImage(new MarvinImage(paintImage), "render/savedfile-" + dateFormat.format(date) + ".bmp");
    }


}
