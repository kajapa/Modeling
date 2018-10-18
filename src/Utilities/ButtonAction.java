package Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonAction extends JPanel implements ActionListener {

    public static final int HEIGHT = 50;
    public static final int WIDTH = 150;
    private JButton save= new JButton("Save image");;

    public ButtonAction()throws IOException {

        save.addActionListener(this);
        save.setText("Save image");
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        super.add(save);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
