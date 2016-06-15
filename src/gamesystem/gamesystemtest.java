package gamesystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class gamesystemtest {

	@Test
	public void test() {
		Sprite sprtest= SpriteManager.res.getSprite("test");
		System.out.println(sprtest.getImages());
	}

}
