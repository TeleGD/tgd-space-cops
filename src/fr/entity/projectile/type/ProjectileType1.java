package fr.entity.projectile.type;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Movable;
import fr.util.Rectangle;

public class ProjectileType1 extends Projectile implements Rectangle {
	private double spawnX;// X d'apparition du projectile
	private double spawnY;// Y d'apparition du projectile
	private int amplitude;// Amplitude du sinus.
	
	public ProjectileType1(double x, double y) {
		super(x, y, 1);
		this.shotType = 0;
		speedY = -0.5;
		spawnY = y;
		amplitude = 64;
	}
	
	public void move(int delta) { // Ce projectile la oscille de gauche a droite (avec une periode de 128).
		y += speedY*delta;
		x = spawnX + amplitude*Math.sin((spawnY-y)*(2*Math.PI)/128.0);
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
	}
}
