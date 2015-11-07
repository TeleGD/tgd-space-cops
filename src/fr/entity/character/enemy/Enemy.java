package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.world.World;

public abstract class Enemy extends Movable {
	
	int timeShoot;
	int timeLeft;
	
	public Enemy(double x,double y,double width,double height){
		isMoving = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		World.getEnemies().add(this);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
	}
	
	public void move(int delta){
		moveX(delta);
		if(x<0){
			x = 0;
			speedX = -speedX;
		}
		if(x>800-width){
			x = 800 - width;
			speedX = -speedX;
		}
		moveY(delta);
		if(y<0)
			y = 0;
			speedY = -speedY;
		if(y>600-height){
			y = 600-height;
			speedY = -speedY;
		}
	}
	
	

}
