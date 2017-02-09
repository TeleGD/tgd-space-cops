package fr.entity.projectile.type;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType3 extends Projectile implements Rectangle{
// Ce tir fait une couronne autour du tireur, qui s'elargit au fil du temps.
	
	private ArrayList<Projectile> shot;
	private int shotCount;
	
	public ProjectileType3(double x, double y, double angle, double speed, int shotCount, boolean allied) {
		super(x, y, angle, speed, allied);
		shot = new ArrayList<Projectile>();
		this.shotCount = shotCount;
		
		for(int i = 1;i<shotCount;i++){
			shot.add(new Projectile(x, y, angle + i*360/(shotCount-1), speed, allied));
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
		for(Projectile p : shot){
			p.render(container, game, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
		for(Projectile p : shot){
			p.update(container, game, delta);
		}
		if(x>800||y>600||x<0||y<0){
			destroy();
		}
	}
}
