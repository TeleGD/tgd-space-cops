package fr.entity.projectile.type;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType2 extends Projectile implements Rectangle{
// Ces tirs partent en spirale, dans le sens trigo si trigo est vrai.
	private double radius;
	private int modifier; // Sert juste a gerer le changement trigo/horaire
	private double X0; // Position initiale (et donc centre de la spirale
	private double Y0; // idem


	public ProjectileType2(double x, double y, double angle, double speed, boolean trigo, boolean allied) {
		super(x, y, angle, speed, allied);
		radius = 0;
		X0 = x;
		Y0 = y;
		angle -= 90;
		if(trigo){
			modifier = 1;
		}
		else{
			modifier = -1;
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float)x,(float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		radius += Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		angle += modifier*Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		y =Y0 + radius*Math.sin(angle*2*Math.PI/360);
		x =X0 + radius*Math.cos(angle*2*Math.PI/360);
		//System.out.println("Rayon : "+radius+" angle : "+angle+" trigo : "+modifier+"---------");
		if(x>800||y>600||x<0||y<0){
			destroy();
		}
	}

}
