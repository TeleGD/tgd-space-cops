package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;

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
		try {
			if(type==TYPE_PLUS_UNE_VIE)
			{
				image=new Image("sprites/InvincibleBonus.png");
			}else if(type==TYPE_PLUS_DE_DASH)
			{
				image=new Image("sprites/DashBonus.png");
			}else if(type==TYPE_PLUS_DE_MISSILE)
			{
				image=new Image("sprites/MissileBonus.png");
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image=image.getScaledCopy((float)2);


	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(image, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		y+=speedY*delta;

	}




}
