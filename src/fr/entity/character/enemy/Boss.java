package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.explosion.Explosion;
import fr.util.Rectangle;

public abstract class Boss extends Enemy implements Rectangle{
	
	Image skin;
	protected Explosion explo;
	
	
	public Boss(double x, double y, double width, double height, int time){
		super(x, y, width, height,time);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if(alive)
		g.drawImage(skin, (float)x, (float)y);
		if(explo != null){
			explo.render(container, game, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		if(explo != null){
			explo.update(container, game, delta);
			if(explo.finishTest()){
				super.destroy();
			}
		}
		
	}
	
	public void destroy(){
		
	}

	@Override
	void shoot() {
		
	}

}
