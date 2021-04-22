import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) { // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() { // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) { // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() { // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) { // compare two points by y-coordinates, breaking ties by x-coordinates
        if (that.y > y) {
            return 1;
        } else if (y > that.y) {
            return -1;
        } else {
            if (that.x > x) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public double slopeTo(Point that) { // the slope between this point and that point
        if (x = that.x) {
            if (y = that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (y = that.y) {
            return 0;
        }
        return (that.y - y) / (that.x - x);
    }

    public Comparator<Point> slopeOrder() { // compare two points by slopes they make with this point
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point> {
        public int Compare(Point p1, Point p2) {
            double slopeP1 = slopeTo(p1);
            double slopeP2 = slopeTo(p2);
            if (slopeP1 > slopeP2) {
                return 1;
            }
            if (slopeP1 < slopeP2) {
                return -1;
            }
            return 0;
        }
    }
}