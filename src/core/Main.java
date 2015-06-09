package core;
import java.applet.Applet;
import java.awt.Graphics;
import java.io.IOException;

import levels.Level;
import rendering.Renderer;
import entities.MainEntities;
import entities.StatTracker;

public class Main extends Applet {
	/**
	 * 
	 */
	public static final long serialVersionUID = 7L;
	public static final int FPS_LIMIT = 60;
    
	//global variables. These are universal throughout the program.
	//state determines what state the game is in (main menu, level selection, etc.)
	private int state;
	private int score;
	private Level currentLevel;
	private int screenX;
	private int screenY;
	
	// these variables are the threads that handle rendering, physics, and statistics tracking respectively
	private Thread render;
	private Thread entities;
	private Thread stats;
	private Thread preloader;
	
	//these variables are the classes that control the code for the aftforementioned threads
	private MainEntities mainEntities;
	private Renderer renderer;
	private StatTracker statTracker;
	private PreLoader preLoader;
	public boolean preloadingComplete = false;
	
	//this variable stores the background, terrain, sprites, menus, cutscenes, and sound
	public Assets assets;
	
	// init - method is called the first time you enter the HTML site with the applet
	public void init() {
		this.assets = new Assets();
		this.mainEntities = new MainEntities();
		this.renderer = new Renderer(this);
		this.statTracker = new StatTracker(this);
		try {
			this.preLoader = new PreLoader(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setState(States.PRELOADER);
		this.setPreloader(new Thread(null, this.preLoader, "Preloader"));
		this.getPreloader().start();
		this.render = new Thread(null, this.renderer, "Rendering");
		this.render.start();
	}

	// start - method is called every time you enter the HTML - site with the applet
	public void start() {
		//initialize the threads
		//this.render = new Thread(null, this.renderer, "Rendering");
		//this.render.start();
		
	}

	// stop - method is called if you leave the site with the applet
	public void stop() {
		//try {
			//this.render.wait();
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//}
		//if (this.physics != null) {
			//try {
				//this.physics.wait();
			//} catch (InterruptedException e) {
				
			//}
		//}
		//try {
			//this.stats.wait();
		//} catch (InterruptedException e) {
			
		//}
		this.destroy();
	}

	// destroy method is called if you leave the page finally (e. g. closing browser)
	public void destroy() {
		if (this.render != null) {
		  this.render.interrupt();
		}
		if (this.entities != null) {
		  this.entities.interrupt();
		}
		if (this.entities != null) {
		  this.stats.interrupt();
		}
		this.render = null;
		this.entities = null;
		this.stats = null;
		this.renderer = null;
		this.mainEntities = null;
		this.statTracker = null;
	}

	/** paint - method allows you to paint into your applet. This method is called e.g. if you move your browser window or if you call repaint() */
	public void paint (Graphics g) {
		if (this.renderer != null) {
		  this.renderer.paint(g);
		}
	}
	
	public void update (Graphics g) {
		if (this.renderer != null) {
		  this.renderer.update(g);
		}
	}
	
	public void run() {
        // Stop thread for 20 milliseconds
	}
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int newState) {
		this.state = newState;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
	
	public Thread getPreloader() {
		return preloader;
	}

	public void setPreloader(Thread preloader) {
		this.preloader = preloader;
		if (this.preloader == null) {
			this.preLoader = null;
		}
	}

	public Thread getRender() {
		return this.render;
	}
	
	public void setRender(Thread newRender) {
		this.render = newRender;
		if (this.render == null) {
			this.renderer = null;
		}
	}
	
	public Renderer getRenderer() {
		return this.renderer;
	}
	
	public Thread getEntities() {
		return this.entities;
	}
	
	public void setEntities(Thread newEntities) {
		this.entities = newEntities;
		if (this.entities == null) {
			this.mainEntities = null;
		}
	}
	
	public MainEntities getMainEntities() {
		return this.mainEntities;
	}
	
	public Thread getStats() {
		return this.stats;
	}
	
	public void setStats(Thread newStats) {
		this.stats = newStats;
		if (this.stats == null) {
			this.statTracker = null;
		}
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
}
