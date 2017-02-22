package ConvexHull;

/**
 *
 * @author AnthonyMikhail
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ConvexHull {
    int[] hull;
    
    public ArrayList<Point> getHull(Point[] pts){
        Point[] points = defensiveCopy(pts);
        int n = points.length;
        //array of hull points,
        //max size n points,
        //-1 value means not on hull
         hull = new int[n];
        Arrays.fill(hull, -1);
        
        // find leftmost point
        int l = 0;
        for(int i = 1; i < n; i += 1){
            if(points[i].x < points[l].x){
                l = i;
            }else if (points[i].x == points[l].x){
                if(points[i].y < points [l].y){
                    l = i;
                }
            }
        }   
        int p = l;
        do{
            int q = (p + 1) % n;
            for(int i = 0; i < n; i += 1)
                if(counterClockwise(points[p],points[i],points[q]))
                    q = i;
            hull[p] = q;
            p = q;
        }while (p != l);
        display(points, hull);
        return toPointArray(points, hull);
        
        
    }
    //ensure Points have been instantiated
    public Point[] defensiveCopy(Point[] pts){
        int n = pts.length;
        Point[] points = new Point[n];
        for(int i = 0; i < n; i += 1){
            if(pts[i] == null)
                throw new IllegalArgumentException("Points in the array are null");
            points[i] = pts[i];
        }
        return points;
    }
    
    //counterclockwise helper method
    private boolean counterClockwise(Point a, Point b, Point c){
        int det = (b.y - a.y)*(c.x - b.x)-(b.x - a.x)*(c.y - b.y);
        return det < 0;
    }

    private void display(Point[] points, int[] hull) {
        System.out.println("Hull Points: ");
        for(int i = 0; i < hull.length; i+= 1){
            if(hull[i] != -1)
                System.out.print("("+ points[i].x + "," + points[i].y + ")\n");
        }
    }
    public ArrayList<Point> toPointArray(Point[] points, int[] hull){
        int count = 0;
        while(hull[count] != -1){
            count++;
        }
        ArrayList<Point> hullPoints = new ArrayList<Point>();
        for(int i = 0; i < hull.length; i+= 1){
            if(hull[i] != -1)
                hullPoints.add(new Point(points[i].x, points[i].y));            
        }
        return hullPoints;
    }
    public boolean contains(ArrayList<Point> points,Point test){
        int i,j;
        boolean result = false;
        for(i = 0, j = points.size() - 1; i < points.size(); j = i++){
            if((points.get(i).y > test.y) != (points.get(j).y > test.y)&&
                    (test.x < (points.get(j).x - points.get(i).x)*(test.y - points.get(i).y)/(points.get(j).y - points.get(i).y) + points.get(i).x)){
                result = !result;
            }
        }
        return result;
                
    }
        
}
