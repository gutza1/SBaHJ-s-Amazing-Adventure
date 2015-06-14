package rendering;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Assets;
import core.Main;
import core.States;
import entities.Entity;
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
		    g.drawImage(this.main.assets.getLoadingScreen().getFrame(), 0, 0, this.main.getWidth(), this.main.getHeight(), this.main);
		  }
		  if (main.getState() == States.LEVEL && this.main.getMainEntities().getPlayer() != null) {
			  Playable player = this.main.getMainEntities().getPlayer();
			  viewPortX = (int) (Math.min(this.main.getSize().width, player.getPosX()) + player.deltaX());
			  viewPortY = (int) (Math.min(this.main.getSize().height, player.getPosY()) + player.deltaY());
			  g.translate(viewPortX, viewPortY);
			  g.drawImage(main.getCurrentLevel().getBackground().getSubimage(viewPortX - this.main.getSize().width / 2, viewPortX - this.main.getSize().height / 2, this.main.getSize().width, this.main.getSize().height), 0, 0, null);
			  for (Entity entity : main.getMainEntities().getEntities()) {
				  BufferedImage entityImage = entity.getImage();
				  boolean inRender = (entity.getPosX() - entityImage.getWidth() / 2 >= viewPortX) && (entity.getPosX() + entityImage.getWidth() / 2 <= viewPortX + this.main.getSize().width) && (entity.getPosY() - entityImage.getHeight() / 2 >= viewPortY) && (entity.getPosY() + entityImage.getHeight() / 2 <= viewPortY + this.main.getSize().height);
				  if (inRender) {
					  g.drawImage(entityImage, (int) entity.getPosX() - entityImage.getWidth() / 2, (int) entity.getPosY() - entityImage.getHeight() / 2, null);
				  }
			  }
			  g.drawImage(main.getCurrentLevel().getTerrain().getSubimage(viewPortX - this.main.getSize().width / 2, viewPortX - this.main.getSize().height / 2, this.main.getSize().width, this.main.getSize().height), 0, 0, null);
		  }
	  }
  }
  
  public int getAnimatedImageNum() {
	  return animatedImageNum;
  }
}
