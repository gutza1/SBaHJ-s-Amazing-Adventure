package entities;

import java.awt.image.BufferedImage;

import levels.Level;
import core.Main;
import utils.AABB;
import utils.HashMask;
import utils.Point;

public class Entity {
  private AABB movementAABB;
  private boolean terrainClip = false;
  private boolean entityClip = false;
  private Main main;
  private BufferedImage image;
  private BufferedImage collisionImage;
  private HashMask collisionMask;
  private int posX;
  private int posY;
  private double accelX;
  private double accelY;
  private double deltaX;
  private double deltaY;
  private HashMask topCollide;
  private HashMask bottomCollide;
  
  public Entity(BufferedImage image, BufferedImage collisionImage, int spawnX, int spawnY) {
	  this.posX = spawnX;
	  this.posY = spawnY;
	  this.collisionImage = collisionImage;
	  this.collisionMask = new HashMask(collisionImage.getHeight() * collisionImage.getWidth());
	  this.topCollide = new HashMask(collisionImage.getWidth());
	  this.bottomCollide = new HashMask(collisionImage.getWidth());
	  for (int i = 0; i < collisionImage.getWidth(); i++) {
		for (int j = 0; j < collisionImage.getHeight(); j++) {
		  if ((collisionImage.getRGB(i, j) >> 24 & 0xff) != 0) {
			collisionMask.add(new Point(i, j));
		  }
		}
	  }
	  for (int i = 0; i < collisionImage.getWidth(); i++) {
	    for (int j = 0; j < collisionImage.getHeight(); j++) {
	      if ((collisionImage.getRGB(i, j) >> 24 & 0xff) != 0) {
			topCollide.add(new Point(i, j));
			break;
		  }
	    }
	  }
	  for (int i = 0; i < collisionImage.getWidth(); i++) {
		for (int j = collisionImage.getHeight() - 1; j > -1; j--) {
		  if ((collisionImage.getRGB(i, j) >> 24 & 0xff) != 0) {
			bottomCollide.add(new Point(i, j));
			break;
		  }
	    }
	  }
  }
  
  public AABB getMovementAABB() {
	  return this.movementAABB;
  }
  
  public boolean canClipThroughTerrain() {
	  return this.terrainClip;
  }
  
  public int[] rgb(int x, int y, BufferedImage image) {
	  int argb = image.getRGB(x, y);
	  int rgb[] = new int[] {
			    (argb >> 16) & 0xff, //red
			    (argb >>  8) & 0xff, //green
			    (argb      ) & 0xff  //blue
			};
	  return rgb;
  }
  
  public double deltaX() {
	  return deltaX;
  }
  
  public void setDeltaX(double deltaX) {
	  this.deltaX = deltaX;
  }
  
  public void setDeltaY(double deltaY) {
	  this.deltaY = deltaY;
  }
  
  public double getAccelX() {
    return accelX;
  }

  public void setAccelX(double accelX) {
	this.accelX = accelX;
  }

  public double getAccelY() {
	return accelY;
  }

  public void setAccelY(double accelY) {
	this.accelY = accelY;
  }
  
  public double deltaY() {
	  return deltaY;
  }
  
  public double speed() {
	  return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
  
  public double angle() {
	  return Math.atan2(deltaY, deltaX);
  }
  
  public void update() {
	  this.deltaX += this.accelX;
	  this.deltaY += this.accelY - MainEntities.GRAVITY_CONSTANT;
	  if (this.terrainClip) {
		    terrainCollide(this.main.getCurrentLevel());
	  }
	  this.posX += deltaX;
	  this.posY -= deltaY;
	  this.movementAABB = new AABB(deltaX, deltaY, movementAABB);
  }
  
  public void terrainCollide(Level level) {
	  Point currentPixel;
	  int yRaise = 0;
	  if (deltaX < 0) {
		  currentPixel = movementAABB.getUpperLeft();
		  for (int y = (int) currentPixel.getY(); y < movementAABB.getLowerLeft().getY(); y++) {
			  if (level.getCollisionMask().contains(currentPixel)) {
				  yRaise = -((int) movementAABB.getLowerLeft().getY() - y);
				  break;
			  }
			  currentPixel = new Point(currentPixel.getX(), y);
		  }
	  }
	  else if (deltaX >= 0) {
		  currentPixel = movementAABB.getUpperRight();
		  for (int y = (int) currentPixel.getY(); y < movementAABB.getLowerRight().getY(); y++) {
			  if (level.getCollisionMask().contains(currentPixel)) {
				  yRaise = -((int) movementAABB.getLowerRight().getY() - y);
				  break;
			  }
			  currentPixel = new Point(currentPixel.getX(), y);
		  }
	  }
	  if (yRaise / movementAABB.getHeight() <= MainEntities.SLOPE_RATIO) {
		    deltaX += (2 * deltaX + Math.sqrt(4 * (deltaX * deltaX) - 8 * yRaise * (2 * deltaY + yRaise))) / 2;
		    deltaY += yRaise;
	  }
	  for (Point topPoint : topCollide) {
		  if (level.getCollisionMask().contains(new Point(topPoint.getX() + deltaX, topPoint.getY() + deltaY))) {
			  deltaY = 0;
			  break;
		  }
	  }
	  for (Point bottomPoint : topCollide) {
		  if (level.getCollisionMask().contains(new Point(bottomPoint.getX() + deltaX, bottomPoint.getY() + deltaY))) {
			  deltaY = 0;
			  break;
		  }
	  }
	  
  }
}
