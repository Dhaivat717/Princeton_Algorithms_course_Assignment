import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    private LineSegment[] ls;
    public FastCollinearPoints(Point[] points){     // finds all line segments containing 4 or more points
        if(points == null){throw new java.lang.NullPointerException("No points provided");}
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("One of the point is null");
            }
        }
        ArrayList<LineSegment> lsList = new ArrayList<LineSegment>();
        Point[] pointsCpyS = Arrays.copyOf(points);
        Point[] pointsCpySS = Arrays.copyOf(points);
        Arrays.sort(pointsCpyS);
        for(int i = 0; i < pointsCpy.length - 1; i++){
            if(pointsCpy[i].compareTo(pointsCpy[i+1]) == 0){
                throw new IllegalArgumentException("Duplicate points are spotted");
            }
        }
        for(int i = 0;i < pointsCpyS.length; ++i){
            Point origin = pointsCpyS[i];
            pointsCpySS.sort(pointsCpySS);
            pointsCpySS.sort(pointsCpySS,origin.slopeOrder());
            int count = 1;
            Point lineStart = null;
            for(int j = 0; j < pointsCpySS.length - 1; ++j){
                if(origin.slopeTo(pointsCpySS[j]) == origin.slopeTo(pointsCpySS[j + 1])){
                    count++;
                    if(count == 2){
                        lineStart = pointsCpySS[j];
                        count++;
                    }
                    else if(count >= 4){
                        if(lineStart.compareTo(origin) > 0){
                            lsList.add(new LineSegment(origin, pointsCpySS[j+1]));
                        }
                        count = 1;
                    }
                }
                else if(count >= 4){
                    if(lineStart.compareTo(origin) > 0){
                        lsList.add(new LineSegment(origin, pointsCpySS[j+1]))
                    }
                    count = 1;
                }
                else{
                    count = 1;
                } 
            }
        }
        ls = lsList.toArray(new LineSegment[lsList.size()]);
        
    }
    
    public int numberOfSegments(){        // the number of line segments
        return ls.length;
    }

    public LineSegment[] segments(){                // the line segments
        return ls;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

