package core;

import java.awt.image.BufferedImage;

import rendering.AnimatedImage;
import utils.AssetMap;

public class Assets {
  private static AssetMap<BufferedImage> backgroundAssets;
  private static AssetMap<BufferedImage> terrainAssets;
  private static AssetMap<BufferedImage> sprites;
  private static AssetMap<AnimatedImage> animatedSprites;
  private static AnimatedImage loadingScreen;
  
  public static AssetMap<BufferedImage> getBackgrounds() {
	  return backgroundAssets;
  }
  
  public void setBackgrounds(AssetMap<BufferedImage> backgroundList) {
	  backgroundAssets = backgroundList;
  }
  
  public static AssetMap<BufferedImage> getTerrain() {
	  return terrainAssets;
  }
  
  public void setTerrain(AssetMap<BufferedImage> terrain) {
	  terrainAssets = terrain;
  }
  
  public static AssetMap<BufferedImage> getSprites() {
	  return sprites;
  }
  
  public void setSprites(AssetMap<BufferedImage> Sprites) {
	  sprites = Sprites;
  }
  
  public static AssetMap<AnimatedImage> getAnimatedSprites() {
	  return animatedSprites;
  }
  
  public static void setAnimatedSprites(AssetMap<AnimatedImage> aniSprites) {
	  animatedSprites = aniSprites;
  }
  
  public static AnimatedImage getLoadingScreen() {
	  return loadingScreen;
  }
  
  public void setLoadingScreen(AnimatedImage temploadingScreen) {
	  loadingScreen = temploadingScreen;
  }
}
