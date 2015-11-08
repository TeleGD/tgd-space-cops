package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;
import fr.entity.projectile.type.ProjectileType1;
import fr.entity.projectile.type.ProjectileType3;
import fr.main.Game;
import fr.world.World;


public class Tourret extends Enemy{
	
	float rotation;
	Image skinCannon;
	int type;
	int decal;
	int posYfinale;
	float decalR;
	

	public Tourret(double x, double y, double width, double height,int decal,float decalR, int time,int type) {
		super(x, y, width, height,time);
		this.type = type;
		hp = 100;
		speedY = 0.05;
		this.decalR = decalR;
		this.decal = decal;
		posYfinale = (int) (y + decal);
		try {
			skinCannon = new Image("sprites/cannon1.png");
			skinCannon.rotate(decalR);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		skinCannon.rotate(1);
		rotation++;
		System.out.println(posYfinale);
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
		if(y>posYfinale)
			speedY = 0;
	}
	
	void shoot(){
		switch(type){
		case 0:
			new Projectile(x+(width/2)-8+Math.cos((Math.PI/2)+rotation*Math.PI/180)*15,y+(height/2)-8+Math.sin((Math.PI/2)+rotation*Math.PI/180)*15,rotation+decalR-180,0.3,false);
			break;
		case 1:
			new ProjectileType0(x+(width/2)-8+Math.cos((Math.PI/2)+rotation*Math.PI/180)*15,y+(height/2)-8+Math.sin((Math.PI/2)+rotation*Math.PI/180)*15, rotation+decalR-180, 0.3, 50, 3, false);
			break;
		case 2:
			new ProjectileType3(x+(width/2)-8+Math.cos((Math.PI/2)+rotation*Math.PI/180)*15,y+(height/2)-8+Math.sin((Math.PI/2)+rotation*Math.PI/180)*15, rotation+decalR-180, 0.3, 8, false);
			break;
		case 3:
			
		}
	}


}
