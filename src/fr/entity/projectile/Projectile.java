package fr.entity.projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;

public class Projectile extends Movable implements Rectangle {

	protected int shotType; // Pour avoir plusieurs types de tirs
	
	public void setShotType(int typeNumber){
		if(typeNumber>=0){
			shotType = typeNumber;
		}
		else {
			shotType = 0;
		}
	}
	
	public int getShotType() {
		 return shotType;
	}
	
	public void moveType(int delta){
		switch(this.getShotType()){
		case 0: // Type de base, tout droit vers le haut.
			this.setMoving(true);
			this.setSpeedY(-10);
			this.moveY(delta);
			break;
		
		default:
			break;
		}
	}
	
	public Projectile(double x, double y, int shotType) { // (x,y) Sont les coordonnees d'apparition du tir
		this.x = x;
		this.y = y;
		this.shotType = shotType;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveType(0);
	}
	
	public void keyReleased(int key, char c) {
		
	}

	public void keyPressed(int key, char c) {
		
	}

}
