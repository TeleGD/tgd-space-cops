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

import fr.world.World;

public class MainMenu extends BasicGameState{
	
	public static int ID = 2;
	
	static TrueTypeFont font1;

	private String nom = "Menu Principal";
	private String[] items = { "Jouer", "Scores (Prochaine M‡J)", "Aide (DLC)", "Quitter" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}
	
	private Image background;

	static GameContainer container;
	static StateBasedGame game;
	
	
	int selection = 0;

	 public void init(GameContainer container, StateBasedGame game) throws SlickException {
	        this.container = container;
	        //container.setShowFPS(false);
	        this.game=game;
	        
	        background = new Image("sprites/0001.png");
	        
	    	Font titre1Font = new Font("Kalinga", Font.BOLD, 26);
	    	font1 = new TrueTypeFont(titre1Font, false);
	    	
	 }

	 public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	    	//let this empty
	 }

	 public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		 	g.drawImage(background, 0, 0);
		 
		 	g.setColor(Color.red);
		 	g.setFont(font1);
			g.drawString(this.nom, 200, 220);

			g.setColor(Color.white);

			for (int i = 0; i < nbrOption; i++) {
				g.drawString(this.items[i], 300, 280 + 50 * i);
			}
			g.drawString(">>", 230, 280 + 50 * selection);
	 }

	 @Override
	public void keyPressed(int key,char c) {
		switch (key) {
		case Input.KEY_DOWN: 
			if (selection < nbrOption - 1)
				selection++;
			else
				selection = 0;
			break;
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = nbrOption - 1;
			break;
		case Input.KEY_ENTER:
			execOption();
			break;
			
		case Input.KEY_ESCAPE:
			game.enterState(ConfirmMenu.ID);
			break;
		case Input.KEY_C:
			game.enterState(CreditsMenu.ID);
			break;
		case Input.KEY_M:
			game.enterState(MissionMenu.ID);
			break;
		}
	}
	
	public void execOption() {
		switch (selection) {
		case 0:
			game.enterState(World.ID);
			break;
			/*
		case 1:
			game.enterState(ScoresMenu.ID);
			break;
		case 2:
			game.enterState(HelpMenu.ID);
			break;
			*/
		case 3:
			game.enterState(ConfirmMenu.ID);
			break;
		}
	}
	
	public int getID(){
		return ID;
	}

}