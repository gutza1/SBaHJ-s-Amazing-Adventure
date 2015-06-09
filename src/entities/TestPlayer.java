package entities;

import java.awt.image.BufferedImage;

import core.Assets;
import core.Main;

public class TestPlayer extends Playable {
	
	private Main main;

	public TestPlayer(Main main) {
		super(Assets.getSprites().get("test_player_sprite.gif"), main.getCurrentLevel().getSpawnPointX(), main.getCurrentLevel().getSpawnPointX());
		// TODO Auto-generated constructor stub
	}

}
