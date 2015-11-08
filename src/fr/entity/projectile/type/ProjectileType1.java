package fr.entity.projectile.type;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType1 extends Projectile implements Rectangle {
// Ce projectile avance selon l'angle donne
// en oscillant de gauche a droite (avec une periode de p ou 128 par defaut).
	
	private double spawnX;// X d'apparition du projectile.
	private double spawnY;// Y d'apparition du projectile.
	private int amplitude = 64;// Amplitude du sinus.
	private int period = 128;// Periode du sinus.
	private double distance;// Distance parcourue sur l'axe de l'angle.
	private double modifier;// Modificateur pour generer le mouvement sinusoidal.
		
	public ProjectileType1(double x, double y, double angle, double speed, int period, int amplitude, boolean allied){
		super(x, y, angle, speed, allied);
		spawnY = y;
		spawnX = x;
		distance = 0;
		this.amplitude = amplitude;
		this.period = period;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	// On passe dans le repere alternatif tourne de angle degres, on applique la transformation,
	// puis on repasse dans le repere normal
		
		//On avance selon l'axe :
		speedY = -Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2))*Math.sin(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		speedX = Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2))*Math.cos(0.5*Math.PI-(angle*(2*Math.PI)/360.0))*0.5;
		moveY(delta);
		moveX(delta);
		
		//On met a jour la distance parcourue sur l'axe
		distance += delta*Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		
		//On applique le modificateur :
		modifier = amplitude*Math.sin(distance*2*Math.PI/period);
		speedY = modifier*Math.cos((angle-90)*2*Math.PI/360.0);
		speedX = modifier*Math.sin((angle-90)*2*Math.PI/360.0);
		moveY(delta);
		moveX(delta);
	}
}
