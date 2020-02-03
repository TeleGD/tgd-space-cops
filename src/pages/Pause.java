package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;

import games.spaceCops.World;

public class Pause extends AppMenu {

	public Pause(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);
		this.setTitle("Pause");
		this.setSubtitle("Le temps de prendre un goûter");
		this.setMenu(Arrays.asList(new MenuItem[] {
			new MenuItem("Retour") {
				public void itemSelected() {
					((World) game.getState(0)).setState(2);
					game.enterState(0 /* World */, new FadeOutTransition(), new FadeInTransition());
				}
			},
			new MenuItem("Abandon") {
				public void itemSelected() {
					((World) game.getState(0)).setState(0);
					game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}));
		this.setHint("HAVE A SNACK");
	}

}
