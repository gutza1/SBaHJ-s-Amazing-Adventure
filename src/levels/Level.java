package levels;

import java.awt.image.BufferedImage;

import utils.HashMask;
import utils.Point;

public class Level implements Comparable<Level> {
	private BufferedImage background;
	private BufferedImage terrain;
	private BufferedImage collisionImage;
	private HashMask collisionMask;
	private int spawnPointX;
	private int spawnPointY;
	private final int number;
	private final int world;
	private final String name;
	
	public Level(BufferedImage background, BufferedImage terrain, BufferedImage collisionImage, String name) {
		this.number = name.charAt(2);
		this.world = name.charAt(0);
		this.name = name;
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
		spawnPointX = 0;
		spawnPointY = 0;
	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public BufferedImage getTerrain() {
		return terrain;
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
	
	public int getSpawnPointX() {
		return spawnPointX;
	}
	
	public int getSpawnPointY() {
		return spawnPointY;
	}

	@Override
	public int compareTo(Level arg0) {
		// TODO Auto-generated method stub
		if (arg0 == null) {
			return -1;
		}
		else {
			if (arg0.getName().charAt(0) > world) {
				return -1;
			}
			else if (arg0.getName().charAt(0) == world) {
				if (arg0.getName().charAt(2) > number) {
					return -1;
				}
				else if (arg0.getName().charAt(2) == number) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
	}
	public String getName() {
		return name;
	}
}
