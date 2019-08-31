import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public final class Main {

	public static final void main (String [] arguments) throws SlickException {
		String title = "Space Cops";
		int width = 800;
		int height = 600;
		boolean fullscreen = false;
		String request = "Voulez-vous jouer en plein écran ?";
		String [] options = {
			"Oui",
			"Non"
		};
		int returnValue = JOptionPane.showOptionDialog (
			null,
			request,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options [0]
		);
		if (returnValue == -1) {
			return;
		}
		if (returnValue == 0) {
			DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ().getDisplayMode ();
			width = display.getWidth ();
			height = display.getHeight ();
			fullscreen = true;
		}
		StateBasedGame game = new StateBasedGame (title) {

			@Override
			public void initStatesList (GameContainer container) {
				this.addState(new spaceCops.menus.WelcomeMenu (7));
				this.addState(new spaceCops.menus.MainMenu (2));
				this.addState(new spaceCops.menus.ConfirmMenu (1));
				this.addState(new spaceCops.menus.CreditsMenu (8));
				this.addState(new spaceCops.menus.GOMenu (3));
				// this.addState(new spaceCops.menus.HelpMenu (6));
				this.addState(new pages.Pause (4));
				this.addState(new spaceCops.menus.ScoresMenu (5));
				this.addState(new spaceCops.menus.MissionMenu (9));
				this.addState(new spaceCops.World (0));
			}

		};
		AppGameContainer container = new AppGameContainer (game, width, height, fullscreen);
		container.setTargetFrameRate (60);
		container.setVSync (true);
		container.setShowFPS (false);
		container.start ();
	}

}
