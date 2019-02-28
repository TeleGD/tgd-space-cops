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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.main.Game;
import fr.world.World;

public class WelcomeMenu extends BasicGameState {

	public static int ID = 7;

	private String nom = "Menu Accueil";
	private String[] items = {};
	private Image background, textcops, textspace;
	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	static GameContainer container;
	static StateBasedGame game;
	int selection = 0;

	int cpt = 0;
	boolean disp = true;
	int xs, ys, xc, yc; // coordonnees des deux textes
	int dir_h_s, dir_v_s, dir_h_c, dir_v_c;

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("sprites/vaisseaux0003.png");
		textcops = new Image("sprites/vaisseauxcops0003.png");
		textspace = new Image("sprites/vaisseauxspace0003.png");
		this.container = container;
		container.setShowFPS(false);
		this.game = game;

		xs = 5; ys = 0; xc = 0; yc = 5;
		dir_h_s = 1; dir_v_s = 1; dir_h_c = -1; dir_v_c = -1;

	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (cpt > 30) {
			cpt = 0;
			disp = !disp;
		}
		cpt++;


		int floatingSize = 10;

		if (Math.abs(xs) == floatingSize)
			dir_h_s = -dir_h_s;
		if (Math.abs(ys) == floatingSize)
			dir_v_s = -dir_v_s;
		if (Math.abs(xc) == floatingSize)
			dir_h_c = -dir_h_c;
		if (Math.abs(yc) == floatingSize)
			dir_v_c = -dir_v_c;

		xs += dir_h_s;
		ys += dir_v_s;
		xc += dir_h_c;
		yc += dir_v_c;


	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.drawImage(background, 0, 0);
		g.drawImage(textspace, xs, ys);
		g.drawImage(textcops, xc, yc);
		g.setColor(Color.white);
		if (disp)
			g.drawString(">        Press Enter        <", 240, 552);

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(MainMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		}
	}

	public int getID() {
		return ID;
	}

}
