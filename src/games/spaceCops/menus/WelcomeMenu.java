package games.spaceCops.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

public class WelcomeMenu extends BasicGameState {

	private int ID;

	private String[] items = {};
	private Image background, textcops, textspace;
	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	public Audio MMenu;
	private int selection = 0;

	private int cpt = 0;
	private boolean disp = true;
	private int xs, ys, xc, yc; // coordonnees des deux textes
	private int dir_h_s, dir_v_s, dir_h_c, dir_v_c;

	public WelcomeMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) {
		background = AppLoader.loadPicture("/images/spaceCops/vaisseaux0003.png");
		textcops = AppLoader.loadPicture("/images/spaceCops/vaisseauxcops0003.png");
		textspace = AppLoader.loadPicture("/images/spaceCops/vaisseauxspace0003.png");
		MMenu = AppLoader.loadAudio("/musics/spaceCops/menu_theme.ogg");
		container.setShowFPS(false);

		xs = 5; ys = 0; xc = 0; yc = 5;
		dir_h_s = 1; dir_v_s = 1; dir_h_c = -1; dir_v_c = -1;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
		}
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

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(background, 0, 0);
		g.drawImage(textspace, xs, ys);
		g.drawImage(textcops, xc, yc);
		g.setColor(Color.white);
		if (disp)
			g.drawString(">        Press Enter        <", 240, 552);
	}

}
