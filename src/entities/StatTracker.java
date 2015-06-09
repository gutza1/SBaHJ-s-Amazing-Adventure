package entities;



import java.awt.event.ActionEvent;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import core.Main;

public class StatTracker implements Runnable{
	private Main main;
	private KeyboardBindings keyTracker;
	

	public StatTracker(Main main) {
		this.main = main;
		keyTracker = new KeyboardBindings(this.main);
	}
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
	      try {
			Thread.sleep(1000 / Main.FPS_LIMIT);
			} catch (InterruptedException ex) {
			}
	        Playable player = this.main.getMainEntities().getPlayer();
	        if (player != null) {
			TreeSet<Integer> currentKeys = keyTracker.getCurrentKeys();
			for (Integer key : currentKeys) {
			  if (key == KeyEvent.VK_RIGHT) {
				player.setIsMovingX(player.getIsMovingX() + 1);
			  }
			  else if (key == KeyEvent.VK_LEFT) {
				player.setIsMovingX(player.getIsMovingX() - 1);
			  }
			  else if (key == KeyEvent.VK_UP) {
				player.setIsMovingY(player.getIsMovingY() + 1);
			  }
			  else if (key == KeyEvent.VK_DOWN) {
				player.setIsMovingY(player.getIsMovingY() - 1);
			  }
			}
	      }
		}
	}

}
