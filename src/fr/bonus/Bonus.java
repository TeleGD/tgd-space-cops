package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Bonus extends Movable {

	protected static final int TYPE_PLUS_DE_MISSILE=0;
	protected  static final int TYPE_PLUS_DE_DASH=1;
	protected  static final int TYPE_INVINCIBLE=2;
	protected static final int TYPE_PLUS_UNE_VIE=3;
	protected  static final int TYPE_BUTCHER=4;
	
	private Image image;
	private int type;
	public Bonus(double x, double y)//position de l'ennemi
	{
		this.type=(int)(Math.random()*5);
		this.x=x;
		this.y=y;
		this.speedY=(int)(Math.random()*0.2)+0.2;
		
		try {
			if(type==TYPE_BUTCHER)
			{
				image=new Image("sprites/.png");
			}else if(type==TYPE_PLUS_UNE_VIE)
			{
				image=new Image("sprites/.png");
			}else if(type==TYPE_BUTCHER)
			{
				image=new Image("sprites/.png");
			}else if(type==TYPE_PLUS_DE_DASH)
			{
				image=new Image("sprites/.png");
			}else if(type==TYPE_PLUS_DE_MISSILE)
			{
				image=new Image("sprites/.png");
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
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
