package fr.menus;

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


public class MissionMenu extends BasicGameState {

	public static int ID = 9;
	
	static TrueTypeFont font5;
	static TrueTypeFont font6;

	private String nom = "Mission :";
	private String[] items = {
			"texte mission",
			"à",
			"définir",
			"plus",
			"tard",
			"",
			"fbjvnsk,bledsfpnvsfnvsnv nsvo sfnibo",
			"",
			"Retour Menu" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	private Image background;
	static GameContainer container;
	static StateBasedGame game;
	int selection = nbrOption - 1;

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		container.setShowFPS(false);
		this.game = game;
		background = new Image("sprites/0005.png");
		
    	Font titre5Font = new Font("Courant", Font.BOLD, 26);
    	font5 = new TrueTypeFont(titre5Font, false);
		
		Font titre6Font = new Font("Courant", Font.BOLD, 16);
    	font6 = new TrueTypeFont(titre6Font, false);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(background, 0, 0);

		g.setColor(Color.blue);
		g.setFont(font5);

		g.drawString(this.nom, 200, 80);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 150, 180 + 30 * i);
		}
		
		g.drawString(">>", 80, 180 + 30 * selection);
		
		
		
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER: case Input.KEY_SPACE:
			game.enterState(MainMenu.ID);
			break;
		case Input.KEY_ESCAPE:
			game.enterState(MainMenu.ID);
			break;
		}
	}


	public int getID() {
		return ID;
	}
}
