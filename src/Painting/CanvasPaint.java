package Painting;

import Primitives.Triangle;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CanvasPaint extends JPanel implements ActionListener {
    int width;
    int height;

    private BufferedImage paintImage;
    Triangle[] obj;
    List<Triangle> objects = new ArrayList<Triangle>();
    List<Float> bufforDepth = new ArrayList<Float>();
    float[] bufford;

    JButton button = new JButton("Save Image");

    public CanvasPaint(Triangle[] obj, int width, int height) {
        paintImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        super.setDoubleBuffered(true);


        this.obj = obj;
        button.addActionListener(this);
        super.add(button);





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


    public void paintComponent(Graphics g)
    {

        long lStartTime = System.currentTimeMillis();
        Graphics2D g2d = (Graphics2D) g;
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

                        g2d.setColor(obj[t].GetInterpolarColor(h, w));
                        g2.setColor(obj[t].GetInterpolarColor(h, w));

                        g2d.fillRect(h, w, 1, 1);
                        g2.fillRect(h, w, 1, 1);

                        bufford[h + width * w]= depth;

                    }


                }


            }
           ++w;

        }
        /*for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                for (int t=0;t<size;++t) {
                    if (obj[t].CheckQuad(i, j) && obj[t].SetPixel(i, j)) {
                        //System.out.println("check1");
                        float depth = obj[t].GetDepth(i, j);
                        if (depth > bufford[i + width * j]) {
                            //System.out.println("check2");

                            g2d.setColor(obj[t].GetInterpolarColor(i, j));
                            g2.setColor(obj[t].GetInterpolarColor(i, j));

                            g2d.fillRect(i, j, 1, 1);
                            g2.fillRect(i, j, 1, 1);

                            bufford[i + width * j]= depth;

                        }


                    }


                }

            }
        }*/


        long lEndTime = System.currentTimeMillis();
        long output = lEndTime - lStartTime;

        System.out.println("Time Canvas Paint: " + output + " ms");  }
    public void save() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        ImageIO.write(paintImage, "PNG", new File("savedfile-" + dateFormat.format(date) + ".png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            save();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        button.setBackground(Color.GREEN);
    }
    }

