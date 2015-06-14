package core;

import java.awt.image.BufferedImage;

import rendering.AnimatedImage;
import utils.AssetMap;

public class Assets {
  private AssetMap<BufferedImage> backgroundAssets;
  private AssetMap<BufferedImage> terrainAssets;
  private AssetMap<BufferedImage> sprites;
  private AssetMap<AnimatedImage> animatedSprites;
  private AnimatedImage loadingScreen;
  
  public AssetMap<BufferedImage> getBackgrounds() {
	  return backgroundAssets;
  }
  
  public void setBackgrounds(AssetMap<BufferedImage> backgroundList) {
	  backgroundAssets = backgroundList;
  }
  
  public AssetMap<BufferedImage> getTerrain() {
	  return terrainAssets;
  }
  
  public void setTerrain(AssetMap<BufferedImage> terrain) {
	  terrainAssets = terrain;
  }
  
  public AssetMap<BufferedImage> getSprites() {
	  return sprites;
  }
  
  public void setSprites(AssetMap<BufferedImage> Sprites) {
	  sprites = Sprites;
  }
  
  public AssetMap<AnimatedImage> getAnimatedSprites() {
	  return animatedSprites;
  }
  
  public void setAnimatedSprites(AssetMap<AnimatedImage> aniSprites) {
	  animatedSprites = aniSprites;
  }
  
  public AnimatedImage getLoadingScreen() {
	  return loadingScreen;
  }
  
  public void setLoadingScreen(AnimatedImage temploadingScreen) {
	  loadingScreen = temploadingScreen;
  }
}
