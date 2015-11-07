package fr.entity.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;

public class Projectile extends Movable implements Rectangle {

	public Projectile(double x, double y) { //(x,y) Sont les coordonnees d'apparition du tir
		this.x = x;
		this.y = y;
		width = 16;
		height = 16;
		setMoving(true);
		World.getProjectiles().add(this);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
	}
	
	public void keyReleased(int key, char c) {
		
	}

	public void keyPressed(int key, char c) {
		
	}

}
