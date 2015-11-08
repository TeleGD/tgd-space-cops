package fr.entity.projectile.type;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType0 extends Projectile implements Rectangle {
// Ce projectile va tout droit selon l'angle donne,
// puis se separe apres une distance d en n sous-projectiles qui font un eventail 
// (avec un angle de 90/n entre chaque sous projectile).
// L'angle est en degres, et est compte en sens horaire.
	
	private double maxDistance;
	private double distance;
	private double numberOfChildren;
	private boolean forked;
	private ArrayList<Projectile> children;
	private boolean even;
	
	public ProjectileType0(double x, double y, double angle, double speed, double d, double n) {
		super(x, y, angle, speed);
		maxDistance = d;
		distance = 0;
		numberOfChildren = n;
		children = new ArrayList<Projectile>();
		forked = false;
	}
	
	public void cut(double d, double n) {
	// Verifie si le projectile s'est deja scinde, et le scinde si ce n'est pas le cas
	// et qu'on est alles assez loin
		if((!forked)&&(d>maxDistance)){
			for(int i = 0; i<n; i++){
				children.add(new Projectile(x,y,angle+45-i*(90/(n-1)),Math.sqrt(Math.pow(speedY, 2)+Math.pow(speedX, 2))));
			}
			forked = true;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.setColor(Color.green);
		g.drawImage(image,(float)x,(float)y);
		for(int i = 0; i<children.size(); i++){
			children.get(i).render(container, game, g);
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
		distance += delta*Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		cut(distance,numberOfChildren);
		for(int i = 0; i<children.size(); i++){
			children.get(i).update(container, game, delta);
		}
	}
}
