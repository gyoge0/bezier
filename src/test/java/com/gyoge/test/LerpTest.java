package com.gyoge.test;

import com.gyoge.bezier.Lerp;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LerpTest extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(new LerpTest());
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            new Lerp(
                new Point2D.Double(100, 100),
                new Point2D.Double(100, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 100)
            ).drawCurve(g, 100000, 0.5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

