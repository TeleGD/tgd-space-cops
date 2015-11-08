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

import fr.main.Game;
import fr.world.World;

public class WelcomeMenu extends BasicGameState{
	
	public static int ID = 7;

	private String nom = "Menu Accueil";
	private String[] items = {};
	private Image welcome;
	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	static GameContainer container;
	static StateBasedGame game;
	int selection = 0;

	 public void init(GameContainer container, StateBasedGame game) throws SlickException {
		 	//welcome = new Image("Images/welcome.png");
	        this.container = container;
	        container.setShowFPS(false);
	        this.game=game;
	    	
	 }
	 
	 int cpt = 0;
	 boolean disp = true;
	 
	 public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	    	if (cpt>30) {
	    		cpt = 0;
	    		disp = !disp;
	    	}
	    	cpt++;
	 }


	 public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		 //g.drawImage(welcome, 0, 0);
		 g.setColor(Color.white);
		 if (disp)
			 g.drawString(">                                <", 240, 552);
		 
	 }

	 @Override
	public void keyPressed(int key,char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(MainMenu.ID);
			break;
		}
	}
	
	
	public int getID(){
		return ID;
	}

}