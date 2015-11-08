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


public class ScoresMenu extends BasicGameState {

	public static int ID = 5;
	
	static TrueTypeFont font5;
	static TrueTypeFont font6;

	private String nom = "Scores :";
	private String[] items = {
			"1. ",
			"2. ",
			"3. ",
			"4. ",
			"5. ",
			"",
			" Retour Menu" };

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
		background = new Image("sprites/0003.png");
		
    	Font titre5Font = new Font("Courant", Font.BOLD, 18);
    	font5 = new TrueTypeFont(titre5Font, false);
		
		Font titre6Font = new Font("Courant", Font.BOLD, 13);
    	font6 = new TrueTypeFont(titre6Font, false);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font5);

		g.drawString(this.nom, 190, 370);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 175, 420 + 13 * i);
		}
		
		g.drawString(">>", 165, 420 + 13 * selection);
		
		
		
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
