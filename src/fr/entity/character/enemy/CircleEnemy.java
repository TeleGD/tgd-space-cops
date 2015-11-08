package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;
import fr.entity.projectile.type.ProjectileType1;

public class CircleEnemy extends Enemy{
	
	int nbrShoot;

	public CircleEnemy(double x, double y, double width, double height, int time,int nbrShoot) {
		super(x, y, width, height,time);
		this.nbrShoot = nbrShoot;
		speedX = 0.3;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillOval((float)x,(float)y,(float)width,(float)height);
	}
	
	public void shoot(){
		for(int i = 0; i<360; i+=360/nbrShoot){
			new Projectile(x+(width/2)-8-Math.cos((Math.PI/2)+i*Math.PI/180)*30,y+(height/2)-8-Math.sin((Math.PI/2)+i*Math.PI/180)*30,i,0.3,false);
		}
	}

}
