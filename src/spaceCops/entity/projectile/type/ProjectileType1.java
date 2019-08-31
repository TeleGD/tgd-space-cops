package spaceCops.entity.projectile.type;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spaceCops.World;
import spaceCops.entity.projectile.Projectile;
import spaceCops.util.Rectangle;

public class ProjectileType1 extends Projectile implements Rectangle {
// Ce projectile avance selon l'angle donne
// en oscillant (avec une periode de p).

	private int amplitude;// Amplitude du sinus. (1 marche bien)
	private int period;// Periode du sinus. (16 marche bien)
	private double altX;// X alternatif (dans le repere tourne de angle)
	private double altY;// Y alternatif
	private double distance;// Distance par rapport au point de tir.

	public ProjectileType1(World world, double x, double y, double angle, double speed, int period, boolean allied){
		super(world, x, y, angle, speed, allied);
		altX=0;
		altY=0;
		this.period = period;
		amplitude = 1;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		distance += speed*delta;
		//En dessous, c'est les projections du sinus du repere tourne de l'angle voulu dans le repere d'origine.
		//Je sais que c'est moche, mais ca marche, et c'est mathematiquement correct, en plus. (les deux points etant probablement lies)
		speedX = speed*(Math.cos((angle-90)*2*Math.PI/360.0)-amplitude*Math.sin(distance*2*Math.PI/period)*Math.sin((angle-90)*2*Math.PI/360.0));
		speedY = speed*(Math.sin((angle-90)*2*Math.PI/360.0)-amplitude*Math.sin(distance*2*Math.PI/period)*Math.cos((angle-90)*2*Math.PI/360.0));
		moveY(delta);
		moveX(delta);

		if(x>800||y>600||x<0||y<0){
			destroy();
		}
	}

}
