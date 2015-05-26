package utils;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
  //public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){

      //@Override
      //public int compare(final Point o1, final Point o2) {
        //if (slopeTo(o1) == slopeTo(o2)) {
            //return 0;
        //}
        //else if (slopeTo(o1) > slopeTo(o2)) {
          //return 1;
        //}
        //else {
         // return -1;
        //}
      //}
    //};

  private final double x;                              // x coordinate
  private final double y;                              // y coordinate

    // create the point (x, y)
  public Point(double x, double y) {
        /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

    // slope between this point and that point
  public double slopeTo(Point that) {
    if (that.x == x) {
      if (that.y == y) {
        return Double.NEGATIVE_INFINITY;
      }
      return Double.POSITIVE_INFINITY;
    }
    else if (that.y == y) {
      return 0.0;
    }
    else {
      return (((double) that.y) - ((double) y)) / (((double) that.x) - (double) x);
    }
  }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (this.y < that.y) {
      return -1;
    }
    else if (this.y == that.y){
      if (this.x < that.x){
        return -1;
      }
      else if (this.x == that.x){
        return 0;
      }
      else{
        return 1;
      }
    }
    else{
      return 1;
    }
  }
  
  public double getX() {
	  return this.x;
  }
  
  public double getY() {
	  return this.y;
  }

    // return string representation of this point
  public String toString() {
        /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }
  
  @Override
  public int hashCode() {
	  double hash = 7;
	  hash = 71 * hash + this.x;
	  hash = 71 * hash + this.y;
	  return (int) hash;
  }
}
