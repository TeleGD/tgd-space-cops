package games.spaceCops.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.util.Movable;

public class Nuage extends Movable{

	private Image image;

	public Nuage(double x, double y, double vit,int i, double taille) {
		speedY=vit;
		this.x=x;
		this.y=y;

		image=AppLoader.loadPicture("/images/spaceCops/cloud"+(i+1)+".png");

		image=image.getScaledCopy((float) taille);
		image.rotate((float) (Math.random()*360));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image,(float) x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		y+=speedY*delta+Decor.defilement;
		if(y>600)Decor.nuages.remove(this);
	}

}
