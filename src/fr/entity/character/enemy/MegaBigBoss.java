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
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tour1 = new Tourret(x+8,y+36,32,32,(int) (-y+50),180,time,0);
		tour2 = new Tourret(x+40,y+75,32,32,(int) (-y+50),180,time,0);
		tour3 = new Tourret(x+60,y+86,32,32,(int) (-y+50),180,time,0);

		tour4 = new Tourret(x+64*4-8-32,y+36,32,32,(int) (-y+50),180,time,0);
		tour5 = new Tourret(x+64*4-40-32,y+75,32,32,(int) (-y+50),180,time,0);
		tour6 = new Tourret(x+64*4-60-32,y+86,32,32,(int) (-y+50),180,time,0);


		tour7 = new Tourret(x+8,y+180,32,32,(int) (-y+50),0,time,0);
		tour8 = new Tourret(x+48,y+128,32,32,(int) (-y+50),0,time,0);

		tour9 = new Tourret(x+64*4-8-32,y+180,32,32,(int) (-y+50),0,time,0);
		tour10 = new Tourret(x+64*4-48-32,y+128,32,32,(int) (-y+50),0,time,0);

		tour11 = new Tourret(x+95,y+144,32,32,(int) (-y+50),0,time,2);
		tour12 = new Tourret(x+95,y+187,32,32,(int) (-y+50),0,time,2);
		tour13 = new Tourret(x+57,y+213,32,32,(int) (-y+50),0,time,2);

		tour14 = new Tourret(x+64*4-32-95,y+144,32,32,(int) (-y+50),0,time,2);
		tour15 = new Tourret(x+64*4-32-95,y+187,32,32,(int) (-y+50),0,time,2);
		tour16 = new Tourret(x+64*4-32-57,y+213,32,32,(int) (-y+50),0,time,2);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		if(y>50)
			speedY = 0;
		if(!tour1.alive && !tour2.alive && this.alive && !tour3.alive && !tour4.alive && !tour5.alive && !tour6.alive && !tour7.alive && !tour8.alive && !tour9.alive && !tour10.alive && !tour11.alive && !tour12.alive && !tour13.alive && !tour14.alive && !tour15.alive && !tour16.alive){
			explo = new Explosion(x-64,y-80,3);
			alive = false;
		}
	}

}
