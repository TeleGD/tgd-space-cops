package fr.entity.character;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

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
import fr.explosion.Explosion;
import fr.menus.GOMenu;
import fr.menus.MissionMenu;
import fr.menus.ScoresMenu;
import fr.util.Collisions;
import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;

public class Player extends Movable implements Rectangle {

	
	private final static int FRAME_TO_WAIT=5;
	private static int TIME_DASH=10000; // à ré-initialiser
	private static int NB_DE_VIE=5; // à ré-initialiser
	private boolean droitegauche=false,hautbas=false;  /* hautbas= true si bas dernier mis, droitegauche= true si droite dernier mis*/
	private boolean upPress = false;
	private boolean downPress = false;
	private boolean leftPress = false;
	private boolean rightPress = false;
	private boolean dash=false,dashDispo=true;
	private boolean invincible=false;
	private long timeDashInit=0; // à ré-initialiser
	private static int compteur=0; // à ré-initialiser
	private Image imagegauche,imagecentrale,imagedroite,image,fond;
	private long timeInvincible;
	private int width2; // largeur image
	public Player() {
		x = 408;
		y = 500;
		width = 16;
		width2 = 64;
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

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		


		for(int i=0;i<NB_DE_VIE;i++)
		{
			g.drawImage(image.getScaledCopy((float) 0.5),i*40,10);
		}
		g.setColor(Color.black);
		g.fillRoundRect((float) 750, (float)10, 16, 28,1);
		g.setColor(Color.white);
		g.fillRoundRect((float) 752, (float)12, 12, 24,1);
		g.setColor(Color.yellow);
		
		if(System.currentTimeMillis()-timeDashInit<TIME_DASH+500)g.fillRoundRect((float) 752, (float)36, 12, -24*(System.currentTimeMillis()-timeDashInit)/TIME_DASH,1);
		else g.fillRoundRect((float) 752, (float)12, 12, 24,1);

		
		g.drawImage(image,(float)(x-24),(float)y);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		deplacement();
		
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
				World.getProjectiles().get(i).contact(true);
				NB_DE_VIE--;
				invincible=true;
				timeInvincible=System.currentTimeMillis();
				if(NB_DE_VIE==0) //game over
				{
					NB_DE_VIE=5;
					MissionMenu.textList = MissionMenu.generateText(40);
					ScoresMenu.addScoreToList();
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
			if(x>24){
				speedX = -0.5;
				image=imagegauche;
			}
			
		}
		if((!leftPress && rightPress)|| (leftPress && rightPress && droitegauche))
		{
			if(x< 800+24 - width2)
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
	
	public static void reset(){
		NB_DE_VIE=5;
		compteur=0;
		
	}



}
