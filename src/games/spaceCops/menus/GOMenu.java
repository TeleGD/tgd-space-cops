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

import games.spaceCops.World;


public class GOMenu extends BasicGameState {

	private int ID;

	private Font font3;
	private Font font4;

	private String nom = "GAME OVER";
	private String[] items = { "Rejouer", "Retour au menu", "", "Score : "};

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	int selection = 0;
	private boolean firstTime;

	public GOMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) {
		container.setShowFPS(false);

		//background = new Image("Images/background2.png");

		font3 = AppLoader.loadFont("Goudy Stout", AppFont.BOLD, 30); // TODO: trouver une fonte équivalente

		font4 = AppLoader.loadFont("Kristen ITC", AppFont.BOLD, 20); // TODO: trouver une fonte équivalente

		firstTime = true;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_UP)) {
			selection = 1-selection;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selection) {
				case 0: {
					game.enterState(9 /* MissionMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
				case 1: {
					game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_C)) {
			game.enterState(8 /* CreditsMenu */, new FadeOutTransition(), new FadeInTransition());
		}
		if (firstTime) {
			items[3] = "Score : "+((World) game.getState(0 /* World */)).getScore();
			firstTime = false;
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		//g.drawImage(background, 0, 0);


		g.setColor(Color.red);
		g.setFont(font3);

		g.drawString(this.nom, 200, 200);

		g.setColor(Color.white);
		g.setFont(font4);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 300, 280 + 50 * i);
		}

		g.drawString(">>", 230, 280 + 50 * selection);
	}

	public void reset() {
		firstTime = true;
	}

}
