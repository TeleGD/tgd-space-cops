package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;

public class CircleEnemy extends Enemy{
	
	boolean a = false;

	public CircleEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		timeShoot = 10;
		timeLeft = timeShoot;
		accelX = 0.0005;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if(timeLeft <= 0){
			if(a){
				shoot(90);
				a = false;
			}else{
				shoot(90);
				a = true;
			}
			timeLeft = timeShoot;
		}else{
			timeLeft--;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillOval((float)x,(float)y,(float)width,(float)height);
	}
	
	public void shoot(int n){
		for(int i = 0; i<360; i+=360/n){
			new Projectile(x+(width/2)-8-Math.cos((Math.PI/2)+i*Math.PI/180)*30,y+(height/2)-8-Math.sin((Math.PI/2)+i*Math.PI/180)*30,i,0.3);
		}
	}

}
