package rendering;

import core.Main;

public class AnimatedImageThread implements Runnable {
	
	private double count;
	private int length;
	
	public AnimatedImageThread(int length) {
		count = 0;
		this.length = length;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000 / Main.FPS_LIMIT);
			  } catch (InterruptedException ex) {
			  }
			count += ((double) length) / ((double) Main.FPS_LIMIT);
			if ((int) count == length) {
				count = 0;
			}
		}
	}
	
	public int frame() {
		return (int) Math.floor(count);
	}

}
