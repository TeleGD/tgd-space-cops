package fr.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Nuage extends Movable{

	private Image image;

	public Nuage(double x, double y, double vit,int i, double taille) {

		speedY=vit;
		this.x=x;
		this.y=y;

		try {
			image=new Image("sprites/cloud"+(i+1)+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		image=image.getScaledCopy((float) taille);
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float) x, (float)y);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		System.out.println("passe par la :"+speedY+"  "+y);
		y+=speedY*delta;
		if(y>600)Decor.nuages.remove(this);
	}

}
