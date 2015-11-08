package fr.entity.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;

public class Projectile extends Movable implements Rectangle {
// Ce projectile va tout droit selon l'angle donne, a la vitesse speed,
// a partir des coordonees (x,y)
// L'angle est en degres, et est compte en sens horaire.
	
	protected double angle;
	protected Image image;
	public Projectile(double x, double y, double angle, double speed) { 

		this.x = x;
		this.y = y;
		width = 16;
		height = 16;
		setMoving(true);
		this.angle = angle;
		speedY = -speed*Math.sin(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		speedX = speed*Math.cos(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		World.getProjectiles().add(this);
		try {
			image=new Image("sprites/proj1.png");
		} catch (SlickException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.setColor(Color.green);
		g.drawImage(image,(float)x,(float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
	}
	
}
