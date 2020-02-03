package games.spaceCops.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

public class MainMenu extends BasicGameState {

	private int ID;

	private Font font1;

	private String nom = "Menu Principal";
	private String[] items = { "Jouer", "Scores", "Aide (DLC)", "Quitter" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	private Image background;
	private Audio MMenu;

	private int selection = 0;

	public MainMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void init(GameContainer container, StateBasedGame game) {
		background = AppLoader.loadPicture("/images/spaceCops/0001.png");
		MMenu = AppLoader.loadAudio("/musics/spaceCops/menu_theme.ogg");
		container.setShowFPS(false);

		font1 = AppLoader.loadFont("Kalinga", AppFont.BOLD, 12); // TODO: trouver une fonte équivalente

	}

	public void enter(GameContainer container, StateBasedGame game) {
		if (!MMenu.isPlaying()) {
			MMenu.playAsMusic(1, .3f, true);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (selection < nbrOption - 1)
				selection++;
			else
				selection = 0;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (selection > 0)
				selection--;
			else
				selection = nbrOption - 1;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selection) {
				case 0: {
					game.enterState(9 /* MissionMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
				case 1: {
					game.enterState(5 /* ScoresMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
				// case 2: {
				// 	game.enterState(6 /* HelpMenu */, new FadeOutTransition(), new FadeInTransition());
				// 	break;
				// }
				case 3: {
					game.enterState(1 /* ConfirmMenu */, new FadeOutTransition(), new FadeInTransition());
					break;
				}
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(1 /* ConfirmMenu */, new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_C)) {
			game.enterState(8 /* CreditsMenu */, new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_M)) {
			game.enterState(9 /* MissionMenu */, new FadeOutTransition(), new FadeInTransition());
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			{
		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font1);
		g.drawString(this.nom, 550, 320);

		g.setColor(Color.white);

		for (int i = 0; i < nbrOption; i++) {
			g.drawString(this.items[i], 560, 360 + 30 * i);
		}
		g.drawString(">>", 540, 360 + 30 * selection);
	}

}
