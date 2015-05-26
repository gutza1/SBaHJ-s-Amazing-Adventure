package utils;

public class AABB {
  private Point upperRight;
  private Point upperLeft;
  private Point lowerLeft;
  private Point lowerRight;
  private double deltaX;
  private double deltaY;
  private int width;
  private int height;
  
  public AABB (Point upperRight, Point upperLeft, Point lowerLeft, Point lowerRight) {
	  this.upperRight = upperRight;
	  this.upperLeft = upperLeft;
	  this.lowerLeft = lowerLeft;
	  this.lowerRight = lowerRight;
	  this.deltaX = 0;
	  this.deltaY = 0;
	  this.width = (int) (lowerRight.getX() - lowerLeft.getX());
	  this.height = (int) (lowerRight.getY() - upperRight.getY());
  }
  
  public AABB (double deltaX, double deltaY, AABB original) {
	  this.deltaX = deltaX;
	  this.deltaY = deltaY;
	  this.upperRight = new Point(original.getUpperRight().getX() + deltaX, original.getUpperRight().getY() - deltaY);
	  this.upperLeft = new Point(original.getUpperLeft().getX() + deltaX, original.getUpperLeft().getY() - deltaY);
	  this.lowerRight = new Point(original.getLowerRight().getX() + deltaX, original.getLowerRight().getY() - deltaY);
	  this.lowerLeft = new Point(original.getLowerLeft().getX() + deltaX, original.getLowerLeft().getY() - deltaY);
  }
  
  public Point getUpperRight() {
	  return this.upperRight;
  }
  
  public Point getUpperLeft() {
	  return this.upperLeft;
  }
  
  public Point getLowerRight() {
	  return this.lowerRight;
  }
  
  public Point getLowerLeft() {
	  return this.lowerLeft;
  }
  
  public double getDeltaX() {
	  return this.deltaX;
  }
  
  public double getDeltaY() {
	  return this.deltaY;
  }
  
  public int getWidth() {
	  return this.width;
  }
  
  public int getHeight() {
	  return this.height;
  }
}
