package spaceCops.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spaceCops.util.Movable;

public class Nuage extends Movable{

	private Image image;

	public Nuage(double x, double y, double vit,int i, double taille) {
		speedY=vit;
		this.x=x;
		this.y=y;

		try {
			image=new Image("images/cloud"+(i+1)+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image=image.getScaledCopy((float) taille);
		image.rotate((float) (Math.random()*360));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float) x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		y+=speedY*delta+Decor.defilement;
		if(y>600)Decor.nuages.remove(this);
	}

}
