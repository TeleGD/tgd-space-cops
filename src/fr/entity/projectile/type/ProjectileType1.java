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
	private int amplitude;// Amplitude du sinus.
	private int period = 128;// Periode du sinus.
	
	private double speedXAlt;// speedX dans le repere alternatif (qui est tourne de angle)
	private double speedYAlt;// speedY dans le repere alternatif
		
	public ProjectileType1(double x, double y, double angle, double speed, int period, boolean allied){
		super(x, y, angle, speed, allied);
		spawnY = y;
		amplitude = 64;
		this.period = period;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
	}
	
	public void goAlt() {
	// Calcul des coordonnees dans le repere alternatif, application du sinus.
		speedXAlt = speedX*Math.cos((angle-90)*2*Math.PI/360.0)+speedY*Math.sin((angle-90)*2*Math.PI/360.0); 
		speedYAlt = amplitude*Math.sin(speedXAlt*2*Math.PI/period);
	}
	
	public void goNormal() {
	// Retour aux coordonnees dans le repere normal
		speedX = speedXAlt*Math.cos((angle-90)*2*Math.PI/360.0)+speedYAlt*Math.sin((angle-90)*2*Math.PI/360.0);
		speedY = speedYAlt*Math.cos((angle-90)*2*Math.PI/360.0)-speedXAlt*Math.sin((angle-90)*2*Math.PI/360.0);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	// On passe dans le repere alternatif tourne de angle degres, on applique la transformation,
	// puis on repasse dans le repere normal
		goAlt();
		goNormal();
		moveY(delta);
		moveX(delta);
	}
}
