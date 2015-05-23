package rendering;
import java.awt.Graphics;
import java.awt.Image;

import core.Main;
import core.States;

public class Renderer implements Runnable{
	
	private Main main;
	private Image dbImage;
	private Graphics dbg;
	
	public Renderer(Main currentMain) {
		main = currentMain;
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

    dbImage = this.main.createImage(this.main.getSize().width, this.main.getSize().height);
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
	  g.drawImage(dbImage, 0, 0, this.main);
	}
  }
  
  public void paint(Graphics g) {
	  if (this.main.getState() == States.PRELOADER && this.main.assets != null && this.main.assets.getLoadingScreen() != null) {
		  g.drawImage(this.main.assets.getLoadingScreen(), 0, 0, this.main);
	  }
  }
}
