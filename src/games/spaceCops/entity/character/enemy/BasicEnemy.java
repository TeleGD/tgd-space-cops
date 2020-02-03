package games.spaceCops.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.entity.projectile.Projectile;
import games.spaceCops.explosion.Explosion;
import games.spaceCops.World;

public class BasicEnemy extends Enemy{

	private Random rand;

	private double targetX; //Coordonnees de la prochaine position
	private double targetY;

	private boolean explosed = false;

	private boolean xOk,yOk; //Si les coordonees x et y sont atteintes

	private int marge = 10; //Marge d'erreur
	private Explosion explo;

	public BasicEnemy(World world, double x, double y, double width, double height, int time) {
		super(world, x, y, width, height, time);
		speedX = 0.3;
		speedY = 0.3;
		hp = 1;
		rand = new Random();
		moveArea(0, 0, 800, 400);
		skin = AppLoader.loadPicture("/images/spaceCops/ennemi1.png");
		skin = skin.getScaledCopy((float) 1);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		if(!explosed && !this.alive){
			explo = new Explosion(x,y,0.75);
			explosed = true;
		}
		if(explo!=null)
		{
			explo.update(container,game,delta);
			if(explo.finishTest()){
				super.destroy();
			}
		}

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if(alive)
		g.drawImage(skin,(float)x,(float)y);
		if(explo!=null)
			explo.render(container,game,g);
	}

	public void move(int delta){
		moveX(delta);
		if(x<targetX-marge){
			speedX = Math.abs(speedX);
			xOk = false;
		}else if(x > targetX+marge){
			speedX = -Math.abs(speedX);
			xOk = false;
		}else{
			speedX = 0;
			xOk = true;
		}
		moveY(delta);
		if(y<targetY-marge){
			speedY = Math.abs(speedY);
			yOk = false;
		}else if(y > targetY+marge){
			speedY = -Math.abs(speedY);
			yOk = false;
		}else{
			speedY = 0;
			yOk = true;
		}
		if(xOk && yOk){
			moveArea(0 ,0 ,800 ,300);
			speedX = 0.3;
			speedY = 0.3;
		}
	}

	public void destroy(){
		if(alive)
		world.setScore(world.getScore()+1);
	}

	public void moveArea(double x, double y, double width, double height){
		targetX = genDouble(x,x+width);
		targetY = genDouble(y,y+height);
	}

	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}

	public void shoot(){
		if(alive)
		new Projectile(world, x,y,180,0.8, false);
	}

}
