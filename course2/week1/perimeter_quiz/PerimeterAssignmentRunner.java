import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num = 0;
        for (Point p :s.getPoints()){
            num++;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double res;
        double length = getPerimeter(s);
        int sides = getNumPoints(s);
        res = length/sides;
        return res;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            largestSide=Math.max(currDist,largestSide);
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double x = currPt.getX();
            // Update totalPerim by currDist
            largestX=Math.max(x,largestX);
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            largestPerimeter = Math.max(largestPerimeter,perimeter);
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        String file = "";
        double curPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter>curPeri){
                file = f.toString();
                curPeri = perimeter;
            }
        }
        return file;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int pointNumber = getNumPoints(s);
        System.out.println("points number " + pointNumber);
        double aveLength = getAverageLength(s);
        System.out.println("average length " + aveLength);
        double largestSide = getLargestSide(s);
        System.out.println("LargestSide " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("LargestX " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("LargestX " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPerimeter = getFileWithLargestPerimeter();
        System.out.println("largestPerimeter file" + largestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
