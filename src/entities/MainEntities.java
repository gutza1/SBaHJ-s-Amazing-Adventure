package entities;

import java.util.TreeSet;

import core.Main;

public class MainEntities implements Runnable {
	
	private TreeSet<Entity> entitySet;
	public final static double GRAVITY_CONSTANT = 5.0;
	public final static double SLOPE_RATIO = 0.25;
	private Playable player;
	
	public MainEntities() {
		entitySet = new TreeSet<Entity>();
	}
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000 / Main.FPS_LIMIT);
			} catch (InterruptedException ex) {
			}
			for (Entity entity : entitySet) {
				entity.update();
			}
		}
	}
	
	public void addEntity(Entity entity) {
		entitySet.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entitySet.remove(entity);
	}
	
	public Playable getPlayer() {
		return this.player;
	}

}
