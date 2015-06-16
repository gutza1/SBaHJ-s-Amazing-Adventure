package entities;

import java.awt.image.BufferedImage;

import rendering.AnimatedImage;
import levels.Level;
import core.Main;
import utils.AABB;
import utils.HashMask;
import utils.Point;

public class Entity implements Comparable<Entity> {
  private AABB movementAABB;
  private boolean terrainClip = false;
  private boolean entityClip = false;
  private Main main;
  private BufferedImage image;
  private AnimatedImage aniImage;

  private HashMask collisionMask;
  private int posX;
  private int posY;
  private int moveSpeedX = 0;
  private int moveSpeedY = 0;
  private int isMovingX = 0;
  private int isMovingY = 0;

  private final boolean accelMove = false;
  private double accelX;
  private double accelY;
  private double deltaX;
  private double deltaY;
  private HashMask topCollide;
  private HashMask bottomCollide;
  private String name;
  
  public Entity(BufferedImage image, int spawnX, int spawnY) {
	  this.posX = spawnX;
	  this.posY = spawnY;
	  this.collisionMask = new HashMask(image.getHeight() * image.getWidth());
	  this.topCollide = new HashMask(image.getWidth());
	  this.bottomCollide = new HashMask(image.getWidth());
	  this.image = image;
	  for (int i = 0; i < image.getWidth(); i++) {
		for (int j = 0; j < image.getHeight(); j++) {
		  if ((image.getRGB(i, j) >> 24 & 0xff) != 0) {
			collisionMask.add(new Point(i, j));
		  }
		}
	  }
	  for (int i = 0; i < image.getWidth(); i++) {
	    for (int j = 0; j < image.getHeight(); j++) {
	      if ((image.getRGB(i, j) >> 24 & 0xff) != 0) {
			topCollide.add(new Point(i, j));
			break;
		  }
	    }
	  }
	  for (int i = 0; i < image.getWidth(); i++) {
		for (int j = image.getHeight() - 1; j > -1; j--) {
		  if ((image.getRGB(i, j) >> 24 & 0xff) != 0) {
			bottomCollide.add(new Point(i, j));
			break;
		  }
	    }
	  }
  }
  
  public Entity(AnimatedImage image, int spawnX, int spawnY) {
	  this.posX = spawnX;
	  this.posY = spawnY;
	  this.collisionMask = new HashMask(image.getHeight(null) * image.getWidth(null));
	  this.topCollide = new HashMask(image.getWidth(null));
	  this.bottomCollide = new HashMask(image.getWidth(null));
	  this.aniImage = image;
	  for (int i = 0; i < image.getWidth(null); i++) {
		for (int j = 0; j < image.getHeight(null); j++) {
		  if ((image.getFrame(0).getRGB(i, j) >> 24 & 0xff) != 0) {
			collisionMask.add(new Point(i, j));
		  }
		}
	  }
	  for (int i = 0; i < image.getWidth(null); i++) {
	    for (int j = 0; j < image.getHeight(null); j++) {
	      if ((image.getFrame(0).getRGB(i, j) >> 24 & 0xff) != 0) {
			topCollide.add(new Point(i, j));
			break;
		  }
	    }
	  }
	  for (int i = 0; i < image.getWidth(null); i++) {
		for (int j = image.getHeight(null) - 1; j > -1; j--) {
		  if ((image.getFrame(0).getRGB(i, j) >> 24 & 0xff) != 0) {
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
  
  public double getPosX() {
	  return this.posX;
  }
  
  public double getPosY() {
	  return this.posY;
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
  
  public int getIsMovingX() {
		return isMovingX;
	  }

  public void setIsMovingX(int isMovingX) {
	this.isMovingX = isMovingX;
  }
  
  public int getIsMovingY() {
	return isMovingY;
  }

  public void setIsMovingY(int isMovingY) {
	this.isMovingY = isMovingY;
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
	  if (isMovingX != 0) {
		  if (accelMove) {
			  this.accelX += this.moveSpeedX * isMovingX;
		  }
		  else {
			  this.deltaX += this.moveSpeedX * isMovingX;
		  }
	  }
	  if (isMovingY != 0) {
		  if (accelMove) {
			  this.accelY += this.moveSpeedY * isMovingX;
		  }
		  else {
			  this.deltaY += this.moveSpeedY * isMovingX;
		  }
	  }
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
  
  public BufferedImage getImage() {
		if (image == null) {
			return aniImage.getFrame();
		}
		else {
			return image;
		}
  }
  
  public String getName() {
	  return name;
  }

  @Override
  public int compareTo(Entity o) {
	// TODO Auto-generated method stub
	return 0;
  }
}
