package fr.entity.projectile.type;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType1 extends Projectile implements Rectangle {
// Ce projectile avance selon l'angle donne
// en oscillant (avec une periode de p).
	
	private int amplitude;// Amplitude du sinus. (1 marche bien)
	private int period;// Periode du sinus. (16 marche bien)
	private double altX;// X alternatif (dans le repere tourne de angle)
	private double altY;// Y alternatif
		
	public ProjectileType1(double x, double y, double angle, double speed, int period, boolean allied){
		super(x, y, angle, speed, allied);
		altX=0;
		altY=0;
		this.period = period;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//On avance selon l'axe :
		altX += Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		//On applique la sinusoide :
		altY = amplitude*Math.sin(altX*2*Math.PI/period);
		//On repercute les changements aux vitesses dans le vrai repere :
		speedX = altY*Math.sin((angle-90)*(2*Math.PI)/360.0)+Math.cos((angle-90)*(2*Math.PI)/360.0);
		speedY = altY*Math.cos((angle-90)*(2*Math.PI)/360.0)-Math.sin((angle-90)*(2*Math.PI)/360.0);
		// On bouge
		moveY(delta);
		moveX(delta);
		
		if(x>800||y>600||x<0||y<0){
			destroy();
		}
		
	}
}
