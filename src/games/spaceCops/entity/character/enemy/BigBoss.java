package games.spaceCops.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.World;
import games.spaceCops.explosion.Explosion;

public class BigBoss extends Boss{

	private Tourret tour1,tour2;

	public BigBoss(World world, double x, double y, double width, double height, int time) {
		super(world, x, y, width, height, time);
		speedY = 0.05;
		skin = AppLoader.loadPicture("/images/spaceCops/ennemi2.png");
		skin = skin.getScaledCopy((float) 2);
		tour1 = new Tourret(world,x+8,y+26,32,32,(int) (-y+50),0,time,0);
		tour2 = new Tourret(world,x+88,y+26,32,32,(int) (-y+50),0,time,0);
	}



	public void update(GameContainer container, StateBasedGame game, int delta)
			{
		super.update(container, game, delta);
		if(y>50)
			speedY = 0;
		if(!tour1.alive && !tour2.alive && this.alive){
			explo = new Explosion(x-64,y-80,3);
			alive = false;
		}
	}

}
