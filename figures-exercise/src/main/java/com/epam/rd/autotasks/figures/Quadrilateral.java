package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        return triangleArea(a, b, c) + triangleArea(a, c, d);
    }

    private double triangleArea(Point p1, Point p2, Point p3) {
        return 0.5 * Math.abs(
                p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY())
        );
    }

    @Override
    public String pointsToString() {
        return a.toString() + b.toString() + c.toString() + d.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point leftmost = a;
        if (b.getX() < leftmost.getX()) {
            leftmost = b;
        }
        if (c.getX() < leftmost.getX()) {
            leftmost = c;
        }
        if (d.getX() < leftmost.getX()) {
            leftmost = d;
        }
        return leftmost;
    }
}
