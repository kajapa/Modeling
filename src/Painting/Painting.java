package Painting;


import Primitives.Triangle;
import Utilities.Vector;

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

public class Painting extends JPanel implements ActionListener {
    int width;
    int height;

    private BufferedImage paintImage;

    Triangle[] objects ;
    float[] bufforDepth ;
    int size;

    JButton button = new JButton("Save Image");

    public Painting(Triangle []objects, int width, int height) {
        paintImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        super.setDoubleBuffered(true);
        this.objects = objects;

        button.addActionListener(this);
        super.add(button);
        this.size=objects.length;

        this.width = width;
        this.height = height;

        this.bufforDepth= new float[width * height];
        for (int i = 0; i < width * height; i++) {
            bufforDepth[i]=1f;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        long lStartTime = System.currentTimeMillis();
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2 = paintImage.createGraphics();

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                for (int t=0;t<size;++t) {
                    if (objects[t].CheckQuad(i, j) && objects[t].SetPixel(i, j)) {
                        //System.out.println("check1");
                        float depth = objects[t].GetDepth(i, j);
                        if (depth > bufforDepth[i + width * j]) {
                            //System.out.println("check2");

                            g2d.setColor(objects[t].GetInterpolarColor(i, j));
                            g2.setColor(objects[t].GetInterpolarColor(i, j));

                            g2d.fillRect(i, j, 1, 1);
                            g2.fillRect(i, j, 1, 1);

                            bufforDepth[i + width * j]= depth;

                        }


                    }


                }

            }
        }


        long lEndTime = System.currentTimeMillis();
        long output = lEndTime - lStartTime;

        System.out.println("Time Paint: " + output + " ms"); }


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
