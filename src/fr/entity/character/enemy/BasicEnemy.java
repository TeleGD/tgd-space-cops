package fr.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;


public class BasicEnemy extends Enemy{
	
	Random rand;
	
	double targetX; //Coordonnées de la prochaine position
	double targetY;
	
	boolean xOk,yOk; //Si les coordonées x et y sont atteintes
	
	int marge = 10; //Marge d'erreur

	public BasicEnemy(double x, double y, double width, double height, int time) {
		super(x, y, width, height, time);
		speedX = 0.3;
		speedY = 0.3;
		rand = new Random();
		moveArea(0, 0, 200, 200);
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
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
			moveArea(100 ,100 ,600 ,300);
			speedX = 0.3;
			speedY = 0.3;
		}
	}
	
	public void moveArea(double x, double y, double width, double height){
		targetX = genDouble(x,x+width);
		targetY = genDouble(y,y+height);
	}
	
	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}
	
	public void shoot(){
		new Projectile(x,y,180,0.3, false);
	}


}
