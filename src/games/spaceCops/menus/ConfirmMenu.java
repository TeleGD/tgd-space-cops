package games.spaceCops.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

public class ConfirmMenu extends BasicGameState {

	private Font font9;
	private Font font6;

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

	public void init(GameContainer container, StateBasedGame game) {
		container.setShowFPS(false);

		//background = AppLoader.loadPicture("/images/spaceCops/background2.png");

		font9 = AppLoader.loadFont("Courant", AppFont.BOLD, 22); // TODO: trouver une fonte équivalente

		font6 = AppLoader.loadFont("Courant", AppFont.BOLD, 16); // TODO: trouver une fonte équivalente
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
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

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
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
