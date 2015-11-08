package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.explosion.Explosion;
import fr.util.Rectangle;

public class Boss extends Enemy implements Rectangle{
	
	Image skin,skinCannon;
	Tourret tour1,tour2;
	private Explosion explo;
	
	
	public Boss(double x, double y, double width, double height, int time){
		super(x, y, width, height,time);
		speedY = 0.05;
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
		if(y>50)
			speedY = 0;
		if(!tour1.alive && !tour2.alive && this.alive){
			explo = new Explosion(x-64,y-80,3);
			alive = false;
		}
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
