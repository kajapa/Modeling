package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Painting.Painting;
import Primitives.Object;
import Primitives.Triangle;
import Utilities.Vector;


public class Main extends JFrame {

    int width = 800;
    int height = 600;
    Vector red= new Vector(255,0,0);
    Vector blue= new Vector(0,0,255);
    Vector green= new Vector(0,255,0);
    List<Object> objects = new ArrayList<Object>();
    Triangle t = new Triangle(new Vector(-1, 1, 0),new Vector(1, 0, 0), new Vector(1, -1, 0) , red,green,blue, width, height);

    public Main() throws IOException {
        objects.add(t);
        super.setSize(width, height);

        Painting paint = new Painting(objects, width, height, Color.YELLOW);


        super.setLayout(new BorderLayout());

        super.setTitle("Modeling");

        super.setLocation(0, 0);
        super.setResizable(false);
        super.add(paint);


        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}
