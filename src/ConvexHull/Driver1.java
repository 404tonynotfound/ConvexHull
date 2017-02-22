
package ConvexHull;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author AnthonyMikhail
 */
public class Driver1 {
    public static void main(String[] args) throws FileNotFoundException{
        int n = 0;
        Scanner scan = new Scanner(new File("input.txt")); 
        while(scan.hasNextLine()){
            n++;
            scan.nextLine();
        }
        ConvexHull hull = new ConvexHull();
        //ensure starting at beginning file
        scan = new Scanner(new File("input.txt")); 
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            points[i] = new Point(scan.nextInt(),scan.nextInt());
        }
        System.out.println("Welcome To Anthony Mikhail's Computational Geometry Exhibit");
        System.out.println("This program is an implementation of the Jarvis Scan");
        System.out.println("Also known as the \"Gift Wrapping\" Algorithm");
        
        ArrayList<Point> pointArray = hull.getHull(points);
        scan = new Scanner(System.in);
        Point testPoint;
        boolean test = true;
        
        while(test){
            System.out.println("To test if a point lies in or out of the hull, enter 1");
            if(scan.nextInt() == 1){
            System.out.println("Test point:");
            System.out.print("> ");
             testPoint = new Point(scan.nextInt(),scan.nextInt());
             if(hull.contains(pointArray, testPoint)){
                 System.out.println("Inside");
             }else{
                  System.out.println("Outside");
             }
             
            }else{
                test = false;
                System.out.println("Thank you for your time");
            }
        }
   
    }
}
