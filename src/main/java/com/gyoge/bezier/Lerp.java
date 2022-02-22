package com.gyoge.bezier;

import java.util.Arrays;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class Lerp extends Point2D.Double {

    private final Point2D start;
    private final Point2D end;

    public Lerp(Point2D... points) throws SinglePointException {
        if (points.length < 2) {
            throw new SinglePointException();
        } else if (points.length == 2) {
            this.start = points[0];
            this.end = points[1];
        } else {
            this.start = points[0];
            this.end = new Lerp(Arrays.copyOfRange(points, 1, points.length));
        }
    }

    public Point2D getLoc(double t) {
        Point2D trueStart = this.start instanceof Lerp lerp ? lerp.getLoc(t) : this.start;
        Point2D trueEnd = this.end instanceof Lerp lerp ? lerp.getLoc(t) : this.end;

        double dist = trueStart.distance(trueEnd) * t;
        double angle = Math.atan2(trueEnd.getY() - trueStart.getY(), trueEnd.getX() - start.getX());

        return new Point2D.Double(
            (trueStart.getX() + Math.cos(angle) * dist),
            (trueStart.getY() + Math.sin(angle) * dist)
        );
    }

    public void drawCurve(Graphics g, int steps, double radius) {
        for (double t = 0.0; t <= 1; t += 1.0 / steps) {
            Point2D loc = this.getLoc(t);
            g.drawOval((int) loc.getX(), (int) loc.getY(), (int) radius * 2, (int) radius * 2);
        }
    }

    private static class SinglePointException extends Exception {

        SinglePointException() {
            super("Lerp requires at least two points");
        }
    }

}
