package spaceCops.entity.character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import spaceCops.World;
import spaceCops.bonus.Bonus;
import spaceCops.entity.projectile.Projectile;
import spaceCops.entity.projectile.type.ProjectileType0;
import spaceCops.menus.ScoresMenu;
import spaceCops.util.Collisions;
import spaceCops.util.Movable;
import spaceCops.util.Rectangle;

public class Player extends Movable implements Rectangle {

	private final static int FRAME_TO_WAIT=5;
	private static int TIME_DASH=10000; //  re-initialiser
	private static int NB_DE_VIE=5; //  re-initialiser
	private World world;
	private ScoresMenu scoresMenu;
	private boolean droitegauche=false,hautbas=false;  /* hautbas= true si bas dernier mis, droitegauche= true si droite dernier mis*/
	private boolean upPress = false;
	private boolean downPress = false;
	private boolean leftPress = false;
	private boolean rightPress = false;
	private boolean dash=false,dashDispo=true;
	private boolean invincible=false;
	private long timeDashInit=0; // re-initialiser
	private int compteur=0; // re-initialiser
	private Image imagegauche,imagecentrale,imagedroite,image;
	private long timeInvincible;
	private int width2; // largeur image
	private Bonus bonus;
	private int MISSILE=2;

	public Player(World world, ScoresMenu scoresMenu) {
		this.world = world;
		this.scoresMenu = scoresMenu;
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
			imagedroite=new Image("images/ship2.png");
			imagegauche=new Image("images/ship0.png");
			imagecentrale=new Image("images/ship1.png");
			image=imagecentrale;

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for(int i=0;i<NB_DE_VIE;i++) {
			g.drawImage(image.getScaledCopy((float) 0.5),i*40,10);
		}
		g.setColor(Color.black);
		g.fillRoundRect((float) 780, (float)10, 16, 28,1);
		g.setColor(Color.white);
		g.fillRoundRect((float) 782, (float)12, 12, 24,1);
		g.setColor(Color.yellow);

		if(System.currentTimeMillis()-timeDashInit<TIME_DASH+500)g.fillRoundRect((float) 782, (float)36, 12, -24*(System.currentTimeMillis()-timeDashInit)/TIME_DASH,1);
		else g.fillRoundRect((float) 782, (float)12, 12, 24,1);

		if(bonus!=null)bonus.render(container, game, g);
		if((invincible && compteur%64>35 )|| !invincible)g.drawImage(image,(float)(x-24),(float)y);

	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			upPress=true;
			hautbas=false;
		} else {
			upPress=false;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			downPress=true;
			hautbas=true;
		} else {
			downPress=false;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			leftPress=true;
			droitegauche=false;
		} else {
			leftPress=false;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			rightPress=true;
			droitegauche=true;
		} else {
			rightPress=false;
		}
		if (input.isKeyDown(Input.KEY_LSHIFT)) {
			if (dashDispo) {
				dash=true;
				dashDispo=false;
				timeDashInit=System.currentTimeMillis();
			}
		} else {
			dash=false;
		}
		deplacement();

		if(System.currentTimeMillis()-timeInvincible>3000)invincible=false;

		dash();

		moveX(delta);
		moveY(delta);

		if(compteur%FRAME_TO_WAIT==0){
			if(MISSILE==2)
			{
				new Projectile(world, x+width/2-2,y,0,1,true);
				new Projectile(world, x+width/2-14,y,0,1,true);
			}else{
				if(MISSILE==4)
				{
					new ProjectileType0(world,x+width/2-2,y,0,0.5,0,4,true);
				}
			}
		}
		compteur++;

		for(int i=0;i<world.getProjectiles().size();i++)
		{
			if(!invincible && !world.getProjectiles().get(i).getAllied() && Collisions.isCollisionRectRect(this,world.getProjectiles().get(i)))
			{
				world.getProjectiles().get(i).contact(true);
				NB_DE_VIE--;
				invincible=true;
				MISSILE=2;
				timeInvincible=System.currentTimeMillis();
				TIME_DASH=5500;
				if(NB_DE_VIE==0) //game over
				{
					NB_DE_VIE=5;
					scoresMenu.addScoreToList(world);
					game.enterState(3 /* GOMenu */, new FadeOutTransition(), new FadeInTransition());
				}
			}


		}

		if(bonus==null &&(int)(Math.random()*1000)==0)
		{
			bonus=new Bonus(Math.random()*760,-400);
		}

		if(bonus!=null)
		{
			bonus.update(container, game, delta);
			if(Collisions.isCollisionRectRect(this,bonus))
			{
				if(bonus.type==Bonus.TYPE_PLUS_DE_MISSILE)
				{
					MISSILE=4;
				}else if(bonus.type==Bonus.TYPE_PLUS_DE_DASH)
				{
					TIME_DASH=5500;
				}else if(bonus.type==Bonus.TYPE_PLUS_UNE_VIE)
				{
					if(NB_DE_VIE<5)NB_DE_VIE++;
				}
				bonus=null;
			}
		}
	}

	private void dash() {
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

	public void reset(){
		NB_DE_VIE=5;
		compteur=0;
	}

}
