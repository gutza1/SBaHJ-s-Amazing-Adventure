package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.*;

import javax.imageio.ImageIO;

import rendering.AnimatedImage;
import utils.AssetMap;
import utils.ResizingArrayQueue;

public class PreLoader implements Runnable {
	
	private Main main;
	private final String[] allowedFileExtensions = {"gif", "txt", "json"};
	
	//Queues used to load the assets.
	private ResizingArrayQueue<File> fileQueue;
	
	//Variables used to store the assets;
	private AssetMap<BufferedImage> backgroundList;
	private AssetMap<BufferedImage> terrainList;
	private AssetMap<BufferedImage> spriteList;
	private AssetMap<AnimatedImage> animatedSpriteList;
	private String assetBase;
	private String codeBase;
	private Thread thread;
	
	public PreLoader(Main main) throws IOException {
		this.main = main;
		codeBase = (this.main.getCodeBase().toString()).substring(6);
		assetBase = codeBase + "assets/";
		backgroundList = new AssetMap<BufferedImage>();
		terrainList = new AssetMap<BufferedImage>();
		spriteList = new AssetMap<BufferedImage>();
		animatedSpriteList = new AssetMap<AnimatedImage>();
		this.fileQueue = new ResizingArrayQueue<File>();
		AnimatedImage loadingScreen = new AnimatedImage(new File(assetBase + "ui/sonic_loading_screen.gif"), this.main);
		this.main.assets.setLoadingScreen(loadingScreen);
		recursiveFileLoader(new File(this.assetBase));
		this.thread = new Thread(null, this, "Preloader");
		this.thread.start();
	}
	
	private void recursiveFileLoader(File dir) {
		File[] dirListing = dir.listFiles();
		if (!dir.isDirectory()) {
			String extension = dir.toString().substring(dir.toString().lastIndexOf('.') + 1);
			boolean valid = false;
			for (String ext : allowedFileExtensions) {
				if (extension.equals(ext)) {
					valid = true;
					break;
				}
			}
			if (!dir.getName().equals("sonic_loading_screen.gif") && valid) {
			  fileQueue.enqueue(dir);
			}
			return;
		}
		else {
			for (File child : dirListing) {
				recursiveFileLoader(child);
			}
			return;
		}
	}
	
	private static String getParentName(File file) {
	    if(file == null || file.isDirectory()) {
	            return null;
	    }
	    String parent = file.getParent();
	    parent = parent.substring(parent.lastIndexOf("/") + 1, parent.length());
	    return parent;      
	}
	
	private void loadAsset() {
		File asset = fileQueue.dequeue();
		String parent = getParentName(asset);
		BufferedImage image = null;
		try {
	      String extension = asset.toString().substring(asset.toString().lastIndexOf('.') + 1);
		  if (extension.equals("gif")) {
		    animatedSpriteList.put(asset.getName(), new AnimatedImage(asset, main));
		  }
		    else {
		      URL url = new URL(main.getCodeBase(), asset.getPath());
			  image = ImageIO.read(url);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		  if (image != null) {
		    if (parent == "background") {
		      backgroundList.put(asset.getName(), image);
		    }
		    else if (parent == "terrain") {
		      terrainList.put(asset.getName(), image);
		    }
		    else if (parent == "sprites") {
		      spriteList.put(asset.getName(), image);
		    }
		  }
	}

	public void run() {
		
		while (!fileQueue.isEmpty()) {
		  try {
			Thread.sleep(50);
		  } catch (InterruptedException ex) {
		  }
		  loadAsset();
		}
		
		this.main.assets.setBackgrounds(backgroundList);
		this.main.assets.setTerrain(terrainList);
		this.main.assets.setSprites(spriteList);
		this.thread.interrupt();
		this.thread = null;
		this.main.setState(States.MAIN_MENU);
	}

}
