package fr.entity.projectile.type;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType0 extends Projectile implements Rectangle {
// Ce projectile va tout droit selon l'angle donne.
	public ProjectileType0(double x, double y, double angle) {
		super(x, y, 0);
		this.shotType = 0;
		speedY = -Math.sin(0.5*Math.PI-angle*(2*Math.PI)/360)*0.5; // Sens horaire pour l'angle
		speedX = Math.cos(0.5*Math.PI-angle*(2*Math.PI)/360)*0.5;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
	}
}
