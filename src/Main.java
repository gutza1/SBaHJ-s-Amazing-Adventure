import java.applet.Applet;
import java.awt.Graphics;

public class Main extends Applet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	//global variables. These are universal throughout the program.
	//state determines what state the game is in (main menu, level selection, etc.)
	private int state;
	//score is the current player's core. That's pretty obvious.
	private int score;
	
	private Thread render;
	private Thread physics;
	private Thread stats;
	
	private MainPhysics mainPhysics;
	private Renderer renderer;
	private StatTracker statTracker;
	
	// init - method is called the first time you enter the HTML site with the applet
	public void init() {
		
	}

	// start - method is called every time you enter the HTML - site with the applet
	public void start() {
		mainPhysics = new MainPhysics();
		renderer = new Renderer(this);
		statTracker = new StatTracker();
		render = new Thread(null, renderer, "Rendering");
		physics = new Thread(null, mainPhysics, "Physics");
		stats = new Thread(null, statTracker, "Statistics");
	}

	// stop - method is called if you leave the site with the applet
	public void stop() {
		
	}

	// destroy method is called if you leave the page finally (e. g. closing browser)
	public void destroy() {
		
	}

	/** paint - method allows you to paint into your applet. This method is called e.g. if you move your browser window or if you call repaint() */
	public void paint (Graphics g) {
		renderer.paint(g);
		
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
		return state;
	}
	
	public void setState(int newState) {
		state = newState;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}

}
