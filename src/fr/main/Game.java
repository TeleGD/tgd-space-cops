package fr.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.world.World;

public class Game extends StateBasedGame {
	

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game(),800, 600, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.start();
	}

	public Game() {
		super("GeneralGame");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new World());
	}

}
