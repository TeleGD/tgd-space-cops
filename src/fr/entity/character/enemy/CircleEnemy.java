package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.type.ProjectileType0;

public class CircleEnemy extends Enemy{

	public CircleEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		timeShoot = 10;
		timeLeft = timeShoot;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if(timeLeft <= 0){
			shoot();
			timeLeft = timeShoot;
		}else{
			timeLeft--;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillOval((float)x,(float)y,(float)width,(float)height);
	}
	
	public void shoot(){
		new ProjectileType0((double) x+(width/2)-8,(double)y - 16,0);
		new ProjectileType0((double) x+(width/2)-8,(double)y + height,180);
		new ProjectileType0((double) x+(width/2)-8,(double)y - 16,0);
		new ProjectileType0((double) x+(width/2)-8,(double)y + height,180);
		
	}

}
