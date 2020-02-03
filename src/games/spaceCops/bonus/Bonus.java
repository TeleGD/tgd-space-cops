package games.spaceCops.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.util.Movable;
import games.spaceCops.util.Rectangle;

public class Bonus extends Movable implements Rectangle {

	public static final int TYPE_PLUS_DE_MISSILE=0;
	public  static final int TYPE_PLUS_DE_DASH=1;
	public  static final int TYPE_PLUS_UNE_VIE=2;

	private Image image;
	public int type;
	public Bonus(double x, double y)//position de l'ennemi
	{
		this.type=(int)(Math.random()*3);
		this.x=x;
		this.y=y;
		this.width=32;
		this.height=32;
		this.speedY=(int)(Math.random()*0.2)+0.2;
		if(type==TYPE_PLUS_UNE_VIE)
		{
			image=AppLoader.loadPicture("/images/spaceCops/InvincibleBonus.png");
		}else if(type==TYPE_PLUS_DE_DASH)
		{
			image=AppLoader.loadPicture("/images/spaceCops/DashBonus.png");
		}else if(type==TYPE_PLUS_DE_MISSILE)
		{
			image=AppLoader.loadPicture("/images/spaceCops/MissileBonus.png");
		}

		image=image.getScaledCopy((float)2);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		g.drawImage(image, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		y+=speedY*delta;
	}

}
