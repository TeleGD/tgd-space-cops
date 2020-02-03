package games.spaceCops.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.spaceCops.entity.projectile.Projectile;
import games.spaceCops.World;

public class CircleEnemy extends Enemy{

	private int nbrShoot;

	public CircleEnemy(World world, double x, double y, double width, double height, int time,int nbrShoot) {
		super(world, x, y, width, height,time);
		this.nbrShoot = nbrShoot;
		speedY = 0.05;
	}
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container,game,delta);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.fillOval((float)x,(float)y,(float)width,(float)height);
	}

	public void shoot(){
		for(int i = 0; i<360; i+=360/nbrShoot){
			new Projectile(world,x+(width/2)-8-Math.cos((Math.PI/2)+i*Math.PI/180)*30,y+(height/2)-8-Math.sin((Math.PI/2)+i*Math.PI/180)*30,i,0.3,false);
		}
	}
}
