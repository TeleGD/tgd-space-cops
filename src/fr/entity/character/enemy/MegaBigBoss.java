package fr.entity.character.enemy;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MegaBigBoss extends Boss{
	
	Tourret tour1,tour2,tour3,tour4,tour5,tour6,tour7,tour8,tour9,tour10,tour11,tour12,tour13,tour14,tour15,tour16;

	public MegaBigBoss(double x, double y, double width, double height, int time) {
		super(x, y, width, height, time);
		speedY = 0.05;
		try {
			skin = new Image("sprites/ennemi2.png");
			skin = skin.getScaledCopy((float) 2); 
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tour1 = new Tourret(x+8,y+26,32,32,time);
		tour2 = new Tourret(x+88,y+26,32,32,time);
		tour3 = new Tourret(x+8,y+26,32,32,time);
		tour4 = new Tourret(x+88,y+26,32,32,time);
		tour5 = new Tourret(x+8,y+26,32,32,time);
		tour6 = new Tourret(x+88,y+26,32,32,time);
		tour7 = new Tourret(x+8,y+26,32,32,time);
		tour8 = new Tourret(x+88,y+26,32,32,time);
		tour9 = new Tourret(x+8,y+26,32,32,time);
		tour10 = new Tourret(x+88,y+26,32,32,time);
		tour11 = new Tourret(x+8,y+26,32,32,time);
		tour12 = new Tourret(x+88,y+26,32,32,time);
		tour13 = new Tourret(x+8,y+26,32,32,time);
		tour14 = new Tourret(x+88,y+26,32,32,time);
		tour15 = new Tourret(x+8,y+26,32,32,time);
		tour16 = new Tourret(x+88,y+26,32,32,time);
	}

}
