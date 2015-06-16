package levels;

import core.Main;

public class LevelLoader implements Runnable{
  
	private Main main;
	private Thread thread;
	private String levelIdentity;
	private Level level;
	
	public LevelLoader(Main main, String levelIdentity) {
		this.main = main;
		this.levelIdentity = levelIdentity;
		this.thread = new Thread(null, this, "LevelLoader");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
