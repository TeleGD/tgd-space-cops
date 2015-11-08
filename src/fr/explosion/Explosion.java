package fr.explosion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Explosion extends Movable {
	
	private int explosion;
	private Image[] image=new Image[60];
	private double scale;
	public Explosion(double x,double y,double scale)
	{
		this.x = x;
		this.y = y;
		this.scale=scale;
		loadExplosion();
		explosion = 179;
		bruit();
	}
	
	public void loadExplosion(){
		for(int i = 0; i<60; i++){
			if(i<8){
				try {
					image[i] = new Image("sprites/explosion/000"+(i+2)+".png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					image[i] = new Image("sprites/explosion/00"+(i+2)+".png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			image[i]=image[i].getScaledCopy((float) scale);
		}

	}

	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(explosion>=0){
			g.drawImage(image[59-(explosion/3)],(float) (x-64), (float)(y-16));
		}

	}
	public boolean finishTest(){
		return  (explosion == 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(explosion>0){
			explosion--;
		}	
	}
	
	public void bruit()
	{
		try {
			Sound sound= new Sound("sons/explosion.ogg");
			sound.play();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
