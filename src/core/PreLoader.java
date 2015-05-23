package core;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.net.*;

import javax.imageio.ImageIO;

import utils.ResizingArrayQueue;

public class PreLoader implements Runnable {
	
	private Main main;
	
	//Queues used to load the assets.
	private ResizingArrayQueue<File> fileQueue;
	
	//Variables used to store the assets;
	private ArrayList<Image> backgroundList;
	private ArrayList<Image> terrainList;
	private ArrayList<Image> spriteList;
	private String codeBase;
	
	public PreLoader(Main main) {
		this.main = main;
		codeBase = (this.main.getCodeBase().toString()).substring(6) + "assets/";
		backgroundList = new ArrayList<Image>();
		terrainList = new ArrayList<Image>();
		spriteList = new ArrayList<Image>();
		this.fileQueue = new ResizingArrayQueue<File>();
		this.main.assets.setLoadingScreen(Toolkit.getDefaultToolkit().getImage(codeBase + "ui/sonic_loading_screen.gif"));
		recursiveFileLoader(new File(this.codeBase));
	}
	
	private void recursiveFileLoader(File dir) {
		File[] dirListing = dir.listFiles();
		if (!dir.isDirectory()) {
			if (!dir.getName().equals("sonic_loading_screen.gif") && !dir.toString().substring(dir.toString().lastIndexOf('.') + 1).equals("db")) {
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
	
	public void run() {
		
		while (!fileQueue.isEmpty()) {
		  try {
			Thread.sleep(20);
		  } catch (InterruptedException ex) {
			ex.printStackTrace();
		  }
		  File asset = fileQueue.dequeue();
		  String parent = getParentName(asset);
		  Image image = null;
		  try {
		    URL url = new URL(main.getCodeBase(), asset.getPath());
			image = ImageIO.read(url);
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		  if (parent == "background") {
		    backgroundList.add(image);
		  }
		  else if (parent == "terrain") {
		    terrainList.add(image);
		  }
		  else if (parent == "sprites") {
		    spriteList.add(image);
		  }
		}
		
		this.main.assets.setBackgrounds(backgroundList.toArray(new Image[backgroundList.size()]));
		this.main.assets.setTerrain(terrainList.toArray(new Image[terrainList.size()]));
		this.main.assets.setSprites(spriteList.toArray(new Image[spriteList.size()]));
		this.main.preloadingComplete = true;
	}

}
