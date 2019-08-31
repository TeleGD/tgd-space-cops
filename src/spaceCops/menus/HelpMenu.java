package spaceCops.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HelpMenu extends BasicGameState {

	private int ID;

	public HelpMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) {}


	public void update(GameContainer container, StateBasedGame game, int delta) {}


	public void render(GameContainer container, StateBasedGame game, Graphics context) {}

}
