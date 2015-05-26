package levels;

import java.awt.image.BufferedImage;

import utils.HashMask;
import utils.Point;

public class Level {
	private BufferedImage background;
	private BufferedImage terrain;
	private BufferedImage collisionImage;
	private HashMask collisionMask;
	
	public Level(BufferedImage background, BufferedImage terrain, BufferedImage collisionImage) {
		this.background = background;
		this.terrain = terrain;
		this.collisionImage = collisionImage;
		this.collisionMask = new HashMask(collisionImage.getHeight() * collisionImage.getWidth());
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
	public HashMask getCollisionMask() {
		return this.collisionMask;
	}

}
