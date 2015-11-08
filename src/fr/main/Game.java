package fr.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.world.World;
import fr.menus.*;

public class Game extends StateBasedGame {
	

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game(),800, 600, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}
	

	public Game() {
		super("GeneralGame");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new WelcomeMenu());
		addState(new MainMenu());
		addState(new ConfirmMenu());
		addState(new CreditsMenu());
		addState(new GOMenu());
		//addState(new HelpMenu());
		addState(new PauseMenu());
		addState(new ScoresMenu());
		addState(new MissionMenu());
		addState(new World());
	}


}
