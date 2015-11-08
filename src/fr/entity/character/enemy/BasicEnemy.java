package fr.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;
import fr.explosion.Explosion;
import fr.main.Game;
import fr.util.Collisions;
import fr.world.World;

public class BasicEnemy extends Enemy{
	
	Random rand;
	
	double targetX; //Coordonnées de la prochaine position
	double targetY;
	
	boolean explosed = false;
	
	boolean xOk,yOk; //Si les coordonées x et y sont atteintes
	
	int marge = 10; //Marge d'erreur
	private Explosion explo;
	public BasicEnemy(double x, double y, double width, double height, int time) {
		super(x, y, width, height, time);
		speedX = 0.3;
		speedY = 0.3;
		hp = 1;
		rand = new Random();
		moveArea(0, 0, 800, 400);
		try {
			skin = new Image("sprites/ennemi1.png");
			skin = skin.getScaledCopy((float) 1);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
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
		World.setScore(World.getScore()+1);
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
		new Projectile(x,y,180,0.8, false);
	}


}
