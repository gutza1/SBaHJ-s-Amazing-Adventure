package rendering;

import java.awt.Graphics;
import java.awt.Image;

import core.Main;
import core.States;
import entities.Playable;

public class Renderer implements Runnable{
	
	private Main main;
	private Image dbImage;
	private Graphics dbg;
	private int viewPortX;
	private int viewPortY;
	private int animatedImageNum;
	
	public Renderer(Main currentMain) {
		main = currentMain;
		viewPortX = this.main.getSize().width / 2;
		viewPortY = this.main.getSize().height / 2;
		animatedImageNum = 0;
	}

	public void run() {

		// run a long while (true) this means in our case "always"
		while (true) {
		  try {
			Thread.sleep(1000 / Main.FPS_LIMIT);
		  } catch (InterruptedException ex) {
		  }
		  // do nothing
		  // repaint the applet
		  this.main.repaint();
		}
		
	}
	
  public void update (Graphics g) {

    dbImage = this.main.createImage(this.main.getWidth(), this.main.getHeight());
	dbg = dbImage.getGraphics();
	// initialize buffer
	if (dbImage != null)  {
	  // clear screen in background
	  dbg.setColor (this.main.getBackground ());
	  dbg.fillRect (0, 0, this.main.getSize().width, this.main.getSize().height);

	  // draw elements in background
	  dbg.setColor (this.main.getForeground());
	  this.paint(dbg);

	  // draw image on the screen
	  g.drawImage(dbImage, 0, 0, this.main.getWidth(), this.main.getHeight(), this.main);
	}
  }
  
  public void paint(Graphics g) {
	  if (this.main.assets != null) {
		  if (this.main.assets.getLoadingScreen() != null) {
		    g.drawImage(this.main.assets.getLoadingScreen().getCurrentFrame(), 0, 0, this.main.getWidth(), this.main.getHeight(), this.main);
		  }
		  if (this.main.getState() == States.LEVEL && this.main.getMainEntities().getPlayer() != null) {
			  Playable player = this.main.getMainEntities().getPlayer();
			  viewPortX = (int) Math.max(this.main.getSize().width, player.getPosX());
			  viewPortY = (int) Math.max(this.main.getSize().height, player.getPosY());
			  g.translate(viewPortX, viewPortY);
		  }
	  }
  }
  
  public int getAnimatedImageNum() {
	  return animatedImageNum;
  }
}
