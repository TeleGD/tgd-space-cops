package games.spaceCops.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.util.Movable;

public class Island extends Movable{

	private Image image;

	public Island(double x,double y) {
		this.x=x;
		this.y=y;
		image=AppLoader.loadPicture("/images/spaceCops/island"+((int)(Math.random()*3)+1)+".png");

		image=image.getScaledCopy((int) (Math.random()*250)+150,(int) (Math.random()*250)+150);
		image.rotate((float) (Math.random()*360));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image,(float) x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		y+=Decor.defilement;
		if(y>600)Decor.islands.remove(this);
	}

}
