import java.awt.Graphics;
import java.awt.Image;


public class Renderer implements Runnable{
	
	private Main main;
	private Image dbImage;
	private Graphics dbg;
	
	public Renderer(Main currentMain) {
		main = currentMain;
	}

	public void run() {
        // Stop thread for 20 milliseconds
		try {
			Thread.sleep(20);
		} catch (InterruptedException ex) {
			
		}
		// do nothing
		// repaint the applet
		main.repaint();

		// run a long while (true) this means in our case "always"
		while (true) {
					
		}
		
	}
	
  public void update (Graphics g) {

	 
    dbImage = main.createImage(main.getSize().width, main.getSize().height);
	dbg = dbImage.getGraphics();
	// initialize buffer
	if (dbImage == null)  {

	  // clear screen in background
	  dbg.setColor (main.getBackground ());
	  dbg.fillRect (0, 0, main.getSize().width, main.getSize().height);

	  // draw elements in background
	  dbg.setColor (main.getForeground());
	  paint(dbg);

	  // draw image on the screen
	  g.drawImage(dbImage, 0, 0, main);
	}
  }
  
  public void paint(Graphics g) {
	  
  }
}
