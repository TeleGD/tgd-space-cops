package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.explosion.Explosion;

public class BigBoss extends Boss{
	
	Tourret tour1,tour2;

	public BigBoss(double x, double y, double width, double height, int time) {
		super(x, y, width, height, time);
		speedY = 0.05;
		try {
			skin = new Image("sprites/ennemi2.png");
			skin = skin.getScaledCopy((float) 2); 
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tour1 = new Tourret(x+8,y+26,32,32,time,0);
		tour2 = new Tourret(x+88,y+26,32,32,time,0);
	}
	

	
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		if(y>50)
			speedY = 0;
		if(!tour1.alive && !tour2.alive && this.alive){
			explo = new Explosion(x-64,y-80,3);
			alive = false;
		}
	}

}
