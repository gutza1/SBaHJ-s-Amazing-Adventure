package levels;

  public class LevelList {
    public final String[][] LEVEL_LIST;
    public final int[][][] SPAWN_POINTS;
    
    private class LevelInfo {
    	public final String name;
    	public final int[] SPAWN_POINT;
    	public final int[][] ENTITY_SPAWNS;
    }
    
    public LevelList(int[][][] spawnPoints) {
    	LEVEL_LIST = new String[spawnPoints.length][0];
    	SPAWN_POINTS = spawnPoints;
    	for (int i = 0; i < spawnPoints.length; i++) {
    		LEVEL_LIST[i] = new String[spawnPoints[i].length];
    		for (int j = 1; j <= spawnPoints[i].length; j++) {
    			LEVEL_LIST[i][j - 1] = i + "-" + j;
    		}
    	}
    }
  }
