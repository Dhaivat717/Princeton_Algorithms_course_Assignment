import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class LineSegment {
    private Point p;
    private Point q;

    public LineSegment(Point p, Point q) { // constructs the line segment between points p and q
        if (p == null || q == null) {
            throw new NullPointerException("point is null");
        }
        this.p = p;
        this.q = q;
    }

    public void draw() { // draws this line segment
        p.drawTo(q);
    }

    public String toString(){                    // string representation
        return p.toString() + "->" + q.toString()
    }
}
