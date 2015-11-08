package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.main.Game;
import fr.world.World;


public class Tourret extends Enemy{
	
	float rotation;
	Image skinCannon;
	int type;
	

	public Tourret(double x, double y, double width, double height, int time,int type) {
		super(x, y, width, height,time);
		this.type = type;
		hp = 100;
		speedY = 0.05;
		try {
			skinCannon = new Image("sprites/cannon1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		skinCannon.rotate(1);
		rotation++;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(skinCannon, (float)x, (float)y);
		g.setColor(Color.green);
		g.fillRect((float)x-12, (float)y-20, (float)hp/2, (float)10);
	}
	
	public void destroy(){
		World.setScore(World.getScore()+15);
		super.destroy();
	}
	
	public void move(int delta){
		moveX(delta);
		if(x<0){
			x = 0;
			speedX = -speedX;
		}
		if(x>800-width){
			x = 800 - width;
			speedX = -speedX;
		}
		moveY(delta);
		if(y>76)
			speedY = 0;
	}
	
	void shoot(){
		switch(type){
		case 0:
			new Projectile(x+(width/2)-8+Math.cos((Math.PI/2)+rotation*Math.PI/180)*15,y+(height/2)-8+Math.sin((Math.PI/2)+rotation*Math.PI/180)*15,rotation-180,0.3,false);
			break;
		}
	}


}
