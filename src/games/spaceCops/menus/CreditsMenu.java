package games.spaceCops.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

public class CreditsMenu extends BasicGameState {

	private int ID;

	private Font font5;
	private Font font6;

	private String nom = "Credits :";
	private String[] items = {
			"saliwan",
			"Medoc",
			"Jérôme",
			"BusyAnt",
			"Arthur",
			"Lala",
			"",
			"Club TeleGame Design,",
			"      Telecom Nancy",
			"",
			" Retour Menu" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	private Image background;

	int selection = nbrOption - 1;

	public CreditsMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) {
		container.setShowFPS(false);
		background = AppLoader.loadPicture("/images/spaceCops/0004.png");

		font5 = AppLoader.loadFont("Courant", AppFont.BOLD, 18); // TODO: trouver une fonte équivalente

		font6 = AppLoader.loadFont("Courant", AppFont.BOLD, 13); // TODO: trouver une fonte équivalente
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font5);

		g.drawString(this.nom, 255, 365);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 238, 390 + 13 * i);
		}

		g.drawString(">>", 225, 390 + 13 * selection);
	}

}
