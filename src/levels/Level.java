package levels;

import java.awt.image.BufferedImage;
import java.util.HashSet;

import utils.Point;

public class Level {
	private BufferedImage background;
	private BufferedImage terrain;
	private BufferedImage collisionImage;
	private HashSet<Point> collisionMask;
	
	public Level(BufferedImage background, BufferedImage terrain, BufferedImage collisionImage) {
		this.background = background;
		this.terrain = terrain;
		this.collisionImage = collisionImage;
		this.collisionMask = new HashSet<Point>(collisionImage.getHeight() * collisionImage.getWidth());
		for (int i = 0; i < collisionImage.getWidth(); i++) {
		  for (int j = 0; j < collisionImage.getHeight(); j++) {
			if ((collisionImage.getRGB(i, j) >> 24 & 0xff) != 0) {
				collisionMask.add(new Point(i, j));
			}
		  }
		}
	}
	
	public BufferedImage getCollisionImage() {
		return collisionImage;
	}
	public void setCollisionImage(BufferedImage collisionImage) {
		this.collisionImage = collisionImage;
	}
	public HashSet<Point> getCollisionMask() {
		return this.collisionMask;
	}

}
