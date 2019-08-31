package spaceCops.menus;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

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

public class MissionMenu extends BasicGameState {

	private int ID;

	static TrueTypeFont font5;
	static TrueTypeFont font6;

	public String[] txt1 = new String[2];
	public String[] txt2 = new String[2];
	public String[] txt3 = new String[2];
	public String[] planetName = { "Kepler-770-C", "Utopia", "Balmoran", "Sulituan", "Naeco", "Nihpuad" };
	public String[] postName = { "général 6 étoiles en chef", "capitaine de section", "grand administrateur", "chef artilleur" };
	public ArrayList<String> textList;
	public int numMessage;

	private String nom = "Mission :";

	private Image background;

	public MissionMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public ArrayList<String> generateText(int lineSize) {
		ArrayList<String> res = new ArrayList<String>();
		Random r = new Random();

		int numMission = r.nextInt(2);
		numMessage = numMission;
		int numPlanet = r.nextInt(planetName.length);
		int numPost = r.nextInt(postName.length);

		String text = txt1[numMission] + planetName[numPlanet]
				+ txt2[numMission] + postName[numPost] + txt3[numMission];
		int lastSpaceIndex = 0;
		int chunkStart = 0;
		String tmp = "";

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c == ' ') {
				lastSpaceIndex = i;
			}

			if (i - chunkStart == lineSize) {
				tmp = text.substring(chunkStart, lastSpaceIndex);
				res.add(tmp);
				chunkStart = lastSpaceIndex;
			}

		}

		tmp = text.substring(chunkStart, text.length());
		res.add(tmp);

		return res;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setShowFPS(false);
		background = new Image("images/0005.png");

		Font titre5Font = new Font("Courant", Font.BOLD, 18);
		font5 = new TrueTypeFont(titre5Font, false);

		Font titre6Font = new Font("Courant", Font.BOLD, 13);
		font6 = new TrueTypeFont(titre6Font, false);

		txt1[0] = "Depuis plusieurs années, la planète ";
		txt2[0] = " vit dans la peur de la menace spatio-terroriste. Sur une des dernières planètes de la galaxie possédant encore des arbres naturels fournissant l'oxygène nécessaire à la vie, l'économie inter-stellaire est gangrènée par le trafic de végétaux. Ces pirates de l'espace possédent leur propre armée et agissent en bande organisée, se livrant à toutes sortes d'activités anti-écologiques de grande ampleur. Vous venez d'être nommé au poste de ";
		txt3[0] = " à la tête de la brigade d'intervention et de prévision des attaques. Prenez les devants de votre escadron et faites le ménage dans cette armée de cosmobrigands !";

		txt1[1] = "Des idéalistes soviétiques tentent de mettre en place une nouvelle révolution communiste ! Après la migration de l'humanité vers la planète ";
		txt2[1] = ", des groupuscules armés ont tenté de prendre le pouvoir par la force. Grâce à la puissance du grand chef suprême, les camarades tentent d'envahir les bastions de l'ennemi capitaliste depuis leurs vaisseaux spatiaux. Si rien n'est fait pour les arréter, la face de ce nouveau monde pourrait changer à jamais ! En tant que ";
		txt3[1] = " de la contre-révolte capitaliste, vous avez la charge de tuer dans l'oeuf ce soulèvement prolétaire. Soyez fort, le sort de la planète est entre vos mains.";

		textList = generateText(40);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(0 /* World */, new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font5);

		g.drawString(this.nom, 90, 235);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int j = 0; j < textList.size(); j++) {
			g.drawString(textList.get(j), 75, 258 + 13 * j);
		}

		g.drawString(">>  Continuer", 75, 255 + 25 + textList.size() * 13);
	}

}
