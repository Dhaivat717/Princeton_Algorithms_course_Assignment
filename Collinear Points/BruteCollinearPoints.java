import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private LineSegment[] ls; 
    public BruteCollinearPoints(Point[] points){    // finds all line segments containing 4 points
        if(points == null){throw new java.lang.NullPointerException("No points provided");}
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("One of the point is null");
            }
        }
        ArrayList<LineSegment> lsList = new ArrayList<LineSegment>();
        Point[] pointsCpy = Arrays.copyOf(points,points.length);
        Arrays.sort(pointsCpy); 
        for(int i = 0; i < pointsCpy.length - 1; i++){
            if(pointsCpy[i].compareTo(pointsCpy[i+1]) == 0){
                throw new IllegalArgumentException("Duplicate points are spotted");
            }
        }
        for(int i = 0; i < pointsCpy.length - 3; ++i){
            for(int j = i + 1;j < pointsCpy.length - 2; ++j){
                for(int k = j + 1;k < pointsCpy.length - 1; ++k){
                    for(int l = l + 1;l < pointsCpy.length; ++l){
                        if(pointsCpy[i].slopeTo(pointsCpy[j]) == pointsCpy[i].slopeTo(pointsCpy[k])  && pointsCpy[i].slopeTo(pointsCpy[j]) == pointsCpy[i].slopeTo(pointsCpy[l])){
                            LineSegment temp = new LineSegment(pointsCpy[i],pointsCpy[l]);
                        }
                        if(!lsList.contains(temp)){
                            lsList.add(temp);
                        }
                    }
                }
            }
        }
        ls = lsList.toArray(new LineSegment[lsList.size()]);
    }

    public int numberOfSegments(){       // the number of line segments
        return ls.length;
    }
    public LineSegment[] segments(){                // the line segments
        return ls;
    }
}

