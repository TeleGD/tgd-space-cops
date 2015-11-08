package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.explosion.Explosion;

public class MegaBigBoss extends Boss{
	
	Tourret tour1,tour2,tour3,tour4,tour5,tour6,tour7,tour8,tour9,tour10,tour11,tour12,tour13,tour14,tour15,tour16;

	public MegaBigBoss(double x, double y, double width, double height, int time) {
		super(x, y, width, height, time);
		speedY = 0.05;
		try {
			skin = new Image("sprites/bIGbOSS.png");
			skin = skin.getScaledCopy((float) 2); 
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tour1 = new Tourret(x+8,y+26,32,32,time,0);
		tour2 = new Tourret(x+8,y+26,32,32,time,0);
		tour3 = new Tourret(x+8,y+26,32,32,time,0);
		tour4 = new Tourret(x+8,y+26,32,32,time,0);
		tour5 = new Tourret(x+8,y+26,32,32,time,0);
		tour6 = new Tourret(x+8,y+26,32,32,time,0);
		tour7 = new Tourret(x+8,y+26,32,32,time,0);
		tour8 = new Tourret(x+8,y+26,32,32,time,0);
		tour9 = new Tourret(x+8,y+26,32,32,time,0);
		tour10 = new Tourret(x+8,y+26,32,32,time,0);
		tour11 = new Tourret(x+8,y+26,32,32,time,0);
		tour12 = new Tourret(x+8,y+26,32,32,time,0);
		tour13 = new Tourret(x+8,y+26,32,32,time,0);
		tour14 = new Tourret(x+8,y+26,32,32,time,0);
		tour15 = new Tourret(x+8,y+26,32,32,time,0);
		tour16 = new Tourret(x+8,y+26,32,32,time,0);
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
