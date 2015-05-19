package core;

import java.awt.Image;

public class Assets {
  private final Image[] backgroundAssets;
  private final Image[] terrainAssets;
  private final Image[] sprites;
  
  public Assets(Image[] backgroundAssets, Image[] terrainAssets, Image[] sprites) {
	  this.backgroundAssets = backgroundAssets;
	  this.terrainAssets = terrainAssets;
	  this.sprites = sprites;
  }
  
  public Image[] getBackground() {
	  return this.backgroundAssets;
  }
  
  public Image[] getTerrain() {
	  return this.terrainAssets;
  }
  
  public Image[] sprites() {
	  return this.sprites;
  }
}
