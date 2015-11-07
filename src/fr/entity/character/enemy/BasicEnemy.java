package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BasicEnemy extends Enemy{

	public BasicEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		speedX = 1;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.rotate((float)x, (float)y, 45);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
		
	}


}
