package fr.entity.character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.entity.projectile.Projectile;
import fr.menus.GOMenu;
import fr.util.Collisions;
import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;

public class Player extends Movable implements Rectangle {

	
	private final static int FRAME_TO_WAIT=5;
	private static int TIME_DASH=10000;
	private static int NB_DE_VIE=5;
	private boolean droitegauche=false,hautbas=false;  /* hautbas= true si bas dernier mis, droitegauche= true si droite dernier mis*/
	private boolean upPress = false;
	private boolean downPress = false;
	private boolean leftPress = false;
	private boolean rightPress = false;
	private boolean dash=false,dashDispo=true;
	private boolean invincible=false;
	private long timeDashInit=0;
	private int compteur=0;
	private Image imagegauche,imagecentrale,imagedroite,image,fond;
	private Image[] imageexplosion =new Image[60];
	private long timeInvincible;
	private int explosion=0;
	
	public Player() {
		x = 400;
		y = 500;
		width = 64;
		height = 64;
		speedX = 0;
		speedY = 0;
		isMoving = true;
		compteur=0;
		
		try {
			imagedroite=new Image("sprites/ship2.png");
			imagegauche=new Image("sprites/ship0.png");
			imagecentrale=new Image("sprites/ship1.png");
			image=imagecentrale;

			chargerImageExplosion();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void chargerImageExplosion() throws SlickException
	{
		for(int i=2;i<62;i++)
		{
			if(i<10)imageexplosion[i-2]=new Image("sprites/explosion/000"+i+".png");
			else imageexplosion[i-2]=new Image("sprites/explosion/00"+i+".png");
		}
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		


		for(int i=0;i<NB_DE_VIE;i++)
		{
			g.drawImage(image.getScaledCopy((float) 0.5),i*40,10);
		}
		g.setColor(Color.black);
		g.fillRoundRect((float) 700, (float)10, 10, 30,1);
		g.setColor(Color.white);
		g.fillRoundRect((float) 702, (float)12, 6, 26,1);
		g.drawImage(image,(float)x,(float)y);

		if(explosion>=1)g.drawImage(imageexplosion[59-(int)explosion/3], (float)(x-width/2), (float)(y-height/2));
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		deplacement();

		if(explosion>1)explosion--;
		
		if(System.currentTimeMillis()-timeInvincible>3000)invincible=false;
		
		dash();
		
		moveX(delta);
		moveY(delta);
		
		if(compteur%FRAME_TO_WAIT==0){
			new Projectile(x+width/2-2,y,0,1,true);
			new Projectile(x+width/2-14,y,0,1,true);
		}
		compteur++;
		
		for(int i=0;i<World.getProjectiles().size();i++)
		{
			if(!invincible && !World.getProjectiles().get(i).getAllied() && Collisions.isCollisionRectRect(this,World.getProjectiles().get(i)))
			{
				World.getProjectiles().get(i).destroy();
				NB_DE_VIE--;
				invincible=true;
				timeInvincible=System.currentTimeMillis();
				explosion=179;
				if(NB_DE_VIE==0) //game over
				{
					game.enterState(GOMenu.ID, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}
	}

	private void dash()
	{
		if(System.currentTimeMillis()-timeDashInit>TIME_DASH+500){
			dashDispo=true;
		}
		else if(dash && System.currentTimeMillis()-timeDashInit<500){
			speedX*=4;
			speedY*=4;
		}else{
			dash=false;
		}
	}
	private void deplacement() {
		speedX = 0;
		speedY = 0;
		if((upPress && !downPress) || (upPress && downPress && !hautbas)) 
		{
			if(y>300){
				speedY=-0.5;
			}
			
		}
		if((downPress && !upPress) || (upPress && downPress && hautbas)){
			if(y< 600- height){
				speedY=0.5;
			}
		}
		if((leftPress && !rightPress)|| (leftPress && rightPress && !droitegauche))
		{
			if(x>0){
				speedX = -0.5;
				image=imagegauche;
			}
			
		}
		if((!leftPress && rightPress)|| (leftPress && rightPress && droitegauche))
		{
			if(x< 800 - width)
			{

				image=imagedroite;
				speedX = 0.5;
			}
		}
		if(!rightPress && !leftPress)
		{
			image=imagecentrale;
		}
		
	}

	public void keyReleased(int key, char c) {
		
		switch (key){
		
			case Input.KEY_UP:
				upPress=false;
			break;
			
			case Input.KEY_DOWN:
				downPress=false;
			break;
			
			case Input.KEY_LEFT:
				leftPress=false;
			break;
			
			case Input.KEY_RIGHT:
				rightPress=false;
			break;
			case Input.KEY_LSHIFT:
				dash=false;
			break;
			
		}
		
		
	}

	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
			upPress=true;
			hautbas=false;
		break;
		
		case Input.KEY_DOWN:
			downPress=true;
			hautbas=true;
		break;
		
		case Input.KEY_LEFT:
			leftPress=true;
			droitegauche=false;
		break;
		case Input.KEY_RIGHT:
			rightPress=true;
			droitegauche=true;
		break;
		case Input.KEY_LSHIFT:
			if(dashDispo)
			{
				dash=true;
				dashDispo=false;
				timeDashInit=System.currentTimeMillis();
			}
		break;

		}
		
	}
	
	



}
