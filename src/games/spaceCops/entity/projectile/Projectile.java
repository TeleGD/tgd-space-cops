package games.spaceCops.entity.projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.explosion.Explosion;
import games.spaceCops.util.Movable;
import games.spaceCops.util.Rectangle;
import games.spaceCops.World;

public class Projectile extends Movable implements Rectangle {
// Ce projectile va tout droit selon l'angle donne, a la vitesse speed,
// a partir des coordonees (x,y).
// L'angle est en degres, et est compte en sens horaire.

	protected World world;
	protected double angle;
	protected Image image;
	protected boolean alliedShot;
	protected int id;
	protected int projCounter;
	protected Explosion explo;
	protected boolean isExploding;
	protected double speed;// Norme de la vitesse, pour eviter les calculs chiants a repetition

	public Projectile(World world, double x, double y, double angle, double speed, boolean allied) {
		this.world = world;
		alliedShot = allied;
		id = projCounter;
		projCounter++;
		this.x = x;
		this.y = y;
		width = 16;
		height = 16;
		setMoving(true);
		this.angle = angle;
		this.speed = speed;
		speedY = -speed*Math.sin(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		speedX = speed*Math.cos(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		world.getProjectiles().add(this);
		if(alliedShot){
			image=AppLoader.loadPicture("/images/spaceCops/proj1.png");
			image.rotate((float) angle);
		}
		else{
			image=AppLoader.loadPicture("/images/spaceCops/proj2.png");
			image.rotate((float)(angle+180));
		}
	}

	public boolean getAllied(){
		return alliedShot;
	}

	public void destroy(){
		int i =0;
		while((i<world.getProjectiles().size())&&(world.getProjectiles().get(i).id!=this.id)){
			i++;
		}
		if(i<world.getProjectiles().size()){
			world.getProjectiles().remove(i);
		}
	}

	public void contact(boolean contact){
		isExploding = true;
		explo = new Explosion(x,y,1);
		explo.bruit();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if(isExploding){
			explo.render(container,game,g);
		}
		else{
			g.drawImage(image,(float)x,(float)y);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		moveY(delta);
		moveX(delta);
		if(isExploding){
			explo.update(container,game,delta);
			if(explo.finishTest()){
				destroy();
			}
		}
		else if(x>800||y>600||x<0||y<0){
			destroy();
		}
	}

}
