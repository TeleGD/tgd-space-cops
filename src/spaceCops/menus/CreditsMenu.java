package spaceCops.menus;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class CreditsMenu extends BasicGameState {

	private int ID;

	static TrueTypeFont font5;
	static TrueTypeFont font6;

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

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setShowFPS(false);
		background = new Image("images/0004.png");

		Font titre5Font = new Font("Courant", Font.BOLD, 18);
		font5 = new TrueTypeFont(titre5Font, false);

		Font titre6Font = new Font("Courant", Font.BOLD, 13);
		font6 = new TrueTypeFont(titre6Font, false);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
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
