package core;

import java.awt.Image;

public class Assets {
  private Image[] backgroundAssets;
  private Image[] terrainAssets;
  private Image[] sprites;
  private Image loadingScreen;
  
  public Image[] getBackgrounds() {
	  return this.backgroundAssets;
  }
  
  public void setBackgrounds(Image[] backgrounds) {
	  this.backgroundAssets = backgrounds;
  }
  
  public Image[] getTerrain() {
	  return this.terrainAssets;
  }
  
  public void setTerrain(Image[] terrain) {
	  this.terrainAssets = terrain;
  }
  
  public Image[] getSprites() {
	  return this.sprites;
  }
  
  public void setSprites(Image[] sprites) {
	  this.sprites = sprites;
  }
  
  public Image getLoadingScreen() {
	  return this.loadingScreen;
  }
  
  public void setLoadingScreen(Image loadingScreen) {
	  this.loadingScreen = loadingScreen;
  }
}
