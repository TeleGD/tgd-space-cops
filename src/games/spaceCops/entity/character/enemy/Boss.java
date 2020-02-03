package games.spaceCops.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.spaceCops.World;
import games.spaceCops.explosion.Explosion;
import games.spaceCops.util.Rectangle;

public abstract class Boss extends Enemy implements Rectangle{

	protected Image skin;
	protected Explosion explo;

	public Boss(World world, double x, double y, double width, double height, int time){
		super(world, x, y, width, height,time);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if(alive)
		g.drawImage(skin, (float)x, (float)y);
		if(explo != null){
			explo.render(container, game, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			{
		super.update(container, game, delta);
		if(explo != null){
			explo.update(container, game, delta);
			if(explo.finishTest()){
				super.destroy();
			}
		}

	}

	public void destroy() {}

	@Override
	void shoot() {}

}
