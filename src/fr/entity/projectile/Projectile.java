package fr.entity.projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;

public class Projectile extends Movable implements Rectangle {
// Ce projectile va tout droit selon l'angle donne, a la vitesse speed,
// a partir des coordonees (x,y).
// L'angle est en degres, et est compte en sens horaire.
	
	protected double angle;
	protected Image image;
	protected boolean alliedShot;
	protected int id;
	protected static int projCounter;
	protected Image[] explosion;
	protected int explosionCounter;
	
	
	public Projectile(double x, double y, double angle, double speed, boolean allied) { 
		alliedShot = allied;
		id = projCounter;
		projCounter++;
		this.x = x;
		this.y = y;
		width = 16;
		height = 16;
		setMoving(true);
		explosion = new Image[60];
		loadExplosion();
		explosionCounter = 0;
		this.angle = angle;
		speedY = -speed*Math.sin(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		speedX = speed*Math.cos(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		World.getProjectiles().add(this);
		try {
			if(alliedShot){
				image=new Image("sprites/proj1.png");
				image.rotate((float) angle);
			}
			else{
				image=new Image("sprites/proj2.png");
				image.rotate((float)(angle+180));
			}
		} catch (SlickException e){
			e.printStackTrace();
		}
	}
	
	public boolean getAllied(){
		return alliedShot;
	}
	
	public void destroy(){
		int i =0;
		while((i<World.getProjectiles().size())&&(World.getProjectiles().get(i).id!=this.id)){
			i++;
		}
		if(i<World.getProjectiles().size()){
			World.getProjectiles().remove(i);
		}
	}
	public void destroy(boolean contact){
		explosionCounter = 179;
		System.out.println("Explo");
	}

	public void loadExplosion(){
		for(int i = 0; i<60; i++){
			if(i<8){
				try {
					explosion[i] = new Image("sprites/explosion/000"+(i+2)+".png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					explosion[i] = new Image("sprites/explosion/00"+(i+2)+".png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(explosionCounter>1){
			g.drawImage(explosion[59-(explosionCounter/3)],(float) x,(float) y);
		}
		else if(explosionCounter == 1){
			this.destroy();
		}
		else{
			g.drawImage(image,(float)x,(float)y);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(explosionCounter>1){
			explosionCounter --;
		}
		moveY(delta);
		moveX(delta);
		
		
		if(x>800||y>600||x<0||y<0){
			destroy();
		}
	}
	
}
