package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BasicEnemy extends Enemy{
	
	float rotation;

	public BasicEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		speedX = 0.3;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		rotation++;
		y += Math.sin(x/30)*10;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.rotate((float)x, (float)y, rotation);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
		
	}
	
	public void move(int delta){
		moveX(delta);
		if(x<0){
			x = 0;
			speedX = -speedX;
			speedY = -0.05;
		}
		if(x>800-width){
			x = 800 - width;
			speedX = -speedX;
			speedY = 0.05;
		}
		moveY(delta);
		if(y<0){
			y = 0;
			speedY = -speedY;
		}
		if(y>600-height){
			y = 600-height;
			speedY = -speedY;
		}
	}


}
