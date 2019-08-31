package spaceCops.menus;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ConfirmMenu extends BasicGameState {

	static TrueTypeFont font9;
	static TrueTypeFont font6;

	private int ID;

	private String nom = "Etes-vous sûr(e)? Tous les scores seront perdus";
	private String[] items = { "Non", "Oui" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	int selection = 0;

	public ConfirmMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setShowFPS(false);

		//background = new Image("Images/background2.png");

		Font titre9Font = new Font("Courant", Font.BOLD, 22);
		font9 = new TrueTypeFont(titre9Font, false);

		Font titre6Font = new Font("Courant", Font.BOLD, 16);
		font6 = new TrueTypeFont(titre6Font, false);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) {
			if (selection < nbrOption - 1)
				selection++;
			else
				selection = 0;
		}
		if (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_Z)) {
			if (selection > 0)
				selection--;
			else
				selection = nbrOption - 1;
		}
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE)) {
			switch (selection) {
				case 0: {
					game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
				case 1: {
					container.exit();
					break;
				}
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.drawImage(background, 0, 0);

		g.setColor(Color.white);
		g.setFont(font9);

		g.drawString(this.nom, 100, 200);

		g.setFont(font6);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 300, 280 + 50 * i);
		}

		g.drawString(">>", 230, 280 + 50 * selection);
	}

}
