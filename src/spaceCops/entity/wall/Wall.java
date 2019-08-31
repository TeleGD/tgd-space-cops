package spaceCops.entity.wall;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spaceCops.entity.Entity;
import spaceCops.util.Rectangle;

public class Wall extends Entity implements Rectangle{

	public Wall(double x,double y,double width,double height){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException { }

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException { }

}
