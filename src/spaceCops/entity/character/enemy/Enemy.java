package spaceCops.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spaceCops.World;
import spaceCops.util.Collisions;
import spaceCops.util.Movable;
import spaceCops.util.Rectangle;

public abstract class Enemy extends Movable implements Rectangle{

	long lastShoot;
	long time;
	protected World world;
	protected Image skin;
	protected int id;
	protected static int enemyCounter;
	protected boolean alive;
	int hp;

	public Enemy(World world, double x,double y,double width,double height,int time){
		this.world = world;
		isMoving = true;
		id = enemyCounter;
		enemyCounter++;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		world.getEnemies().add(this);
		lastShoot = System.currentTimeMillis();
		this.time = time;
		alive = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
		testShoot();
		if(!(this instanceof Boss)){
		for(int i = 0; i< world.getProjectiles().size();i++){
			if(world.getProjectiles().get(i).getAllied() && Collisions.isCollisionRectRect(this,world.getProjectiles().get(i))){
				if(hp > 1){
					hp--;
					world.getProjectiles().remove(i);
				}else{
					destroy();
					alive = false;

				}
			}
		}
		}
	}

	public void destroy(){
		int i =0;
		while((i<world.getEnemies().size())&&(world.getEnemies().get(i).id!=this.id)){
			i++;
		}
		if(i<world.getEnemies().size()){
			world.getEnemies().remove(i);
		}
	}

	public void move(int delta){
		speedX+=accelX;
		moveX(delta);
		if(x<0){
			x = 0;
			speedX = -speedX;
			accelX = -accelX;
		}
		if(x>800-width){
			x = 800 - width;
			speedX = -speedX;
			accelX = -accelX;
		}
		moveY(delta);
		if(y<0 && this instanceof BasicEnemy){
			y = 0;
			speedY = -speedY;
		}
		if(y>600-height && this instanceof BasicEnemy){
			y = 600-height;
			speedY = -speedY;
		}
	}

	abstract void shoot();

	protected void testShoot(){
		if(System.currentTimeMillis() - lastShoot > time){
			shoot();
			lastShoot = System.currentTimeMillis();
		}
	}

}
