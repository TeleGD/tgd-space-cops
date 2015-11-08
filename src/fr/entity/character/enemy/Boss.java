package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;

public class Boss extends Enemy implements Rectangle{
	
	Image skin,skinCannon;
	Tourret tour1,tour2;
	
	public Boss(double x, double y, double width, double height, int time){
		super(x, y, width, height,time);
		try {
			skin = new Image("sprites/ennemi2.png");
			skin = skin.getScaledCopy((float) 2); 
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tour1 = new Tourret(x+8,y+26,32,32,time);
		tour2 = new Tourret(x+88,y+26,32,32,time);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(skin, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(!tour1.alive && !tour2.alive)
			destroy();
	}

	@Override
	void shoot() {
		
	}

}
