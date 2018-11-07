package Painting;

import Primitives.Object;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Painting extends JPanel implements ActionListener {
    int width;
    int height;

    private BufferedImage paintImage;
    Color col;
    List<Object> objects = new ArrayList<Object>();
    List<Float> bufforDepth = new  ArrayList<Float>();

    JButton button = new JButton("Save Image");

    public Painting(List<Object> objects, int width, int height, Color col) {
        paintImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        super.setDoubleBuffered(true);
        this.objects = objects;

        button.addActionListener(this);
        super.add(button);


        this.width = width;
        this.height = height;
        this.col = col;

        for(int i = 0; i < width * height; i++) {
            bufforDepth.add(-1f);
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2 = paintImage.createGraphics();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (Object object : objects) {

                    if (object.CheckQuad(i, j) && object.SetPixel(i, j)) {
                        float depth = object.GetDepth(i,j);
                        if(depth > bufforDepth.get(i+width*j)) {

                            g2d.setColor(object.GetInterpolarColor(i, j));
                            g2.setColor(object.GetInterpolarColor(i, j));

                            g2d.fillRect(i, j, 1, 1);
                            g2.fillRect(i, j, 1, 1);

                            bufforDepth.set(i+width*j, depth);
                        }
                    }
                }

            }
        }


    }


    public void save() throws IOException {
        ImageIO.write(paintImage, "PNG", new File("savedfile.png"));
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
