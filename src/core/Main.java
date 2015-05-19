package core;
import java.applet.Applet;
import java.awt.Graphics;

import rendering.Renderer;
import entities.MainPhysics;
import entities.StatTracker;

public class Main extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
    
	//global variables. These are universal throughout the program.
	//state determines what state the game is in (main menu, level selection, etc.)
	private int state;
	private int score;
	
	// these variables are the threads that handle rendering, physics, and statistics tracking respectively
	private Thread render;
	private Thread physics;
	private Thread stats;
	
	//these variables are the classes that control the code for the aftforementioned threads
	private MainPhysics mainPhysics;
	private Renderer renderer;
	private StatTracker statTracker;
	
	//this variable stores the background, terrain, sprites, menus, cutscenes, and sound
	private Assets assets;
	
	// init - method is called the first time you enter the HTML site with the applet
	public void init() {
		this.mainPhysics = new MainPhysics();
		this.renderer = new Renderer(this);
		this.statTracker = new StatTracker();
		
		this.start();
	}

	// start - method is called every time you enter the HTML - site with the applet
	public void start() {
		//initialize the threads
		this.render = new Thread(null, this.renderer, "Rendering");
		this.render.start();
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
		if (this.physics != null) {
		  this.physics.interrupt();
		}
		if (this.physics != null) {
		  this.stats.interrupt();
		}
		this.render = null;
		this.physics = null;
		this.stats = null;
		this.renderer = null;
		this.mainPhysics = null;
		this.statTracker = null;
	}

	/** paint - method allows you to paint into your applet. This method is called e.g. if you move your browser window or if you call repaint() */
	public void paint (Graphics g) {
		this.renderer.paint(g);
	}
	
	public void update (Graphics g) {
		this.renderer.update(g);
	}
	
	public void run() {
        // Stop thread for 20 milliseconds
		try {
			Thread.sleep(20);
		} catch (InterruptedException ex) {
			// TODO Auto-generated catch block
		}
		
		while (true) {
					
		}
		
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
	
	public Thread getRender() {
		return this.render;
	}
	
	public void setRender(Thread newRender) {
		this.render = newRender;
	}
	
	public Thread getPhysics() {
		return this.physics;
	}
	
	public void setPhysics(Thread newPhysics) {
		this.physics = newPhysics;
	}
	
	public Thread getStats() {
		return this.stats;
	}
	
	public void setStats(Thread newStats) {
		this.stats = newStats;
	}
}
