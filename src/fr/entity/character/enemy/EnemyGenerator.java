package fr.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.menus.MissionMenu;
import fr.world.World;

public class EnemyGenerator {

	long lastSpawn;
	long time;
	Random rand;
	boolean event[] = new boolean[10];
	long timeStart;
	boolean started = false;
	boolean spawn = false;

	String message0 = "Vous avez eu beau brûler mes sbires, vous ne m'attraperez pas !";
	String message02 = "La forêt est à moi, je vous détruirai !";
	String message1 = "Mes camarades sont morts en défendant la mère patrie,";
	String message12 = "mais je vous brûlerai ! Les rouges vaincront !";

	boolean m1,m2;

	public void reset(){
		for(int i = 0; i < event.length;i++){
			event[i] = false;
			started = false;
			spawn = false;
		}
	}


	public void init(GameContainer container,StateBasedGame game){
		time = 1000;
		rand = new Random();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(m1){
		g.drawString(message0, 50, 500);
		g.drawString(message02, 50, 550);
		}
		if(m2){
		g.drawString(message1, 50, 500);
		g.drawString(message12, 50, 550);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!started){
			lastSpawn = System.currentTimeMillis();
			timeStart = System.currentTimeMillis();
			started = true;
		}

		if(spawn){
			if(System.currentTimeMillis() - lastSpawn > time){
				lastSpawn = System.currentTimeMillis();
				new BasicEnemy(genDouble(0,800),genDouble(0,300),32,32,500);
			}
		}



		if(condEvent(0,2000)){
			spawn = true;
			event[0]=true;
		}
		if(condEvent(1,2000)){
			new BigBoss(400-64,-100,128,128,350);
			spawn = false;
			event[1]=true;
		}
		if(condEvent(2,12000+4000)){
			spawn = true;
			event[2]=true;
		}
		if(condEvent(3,25000+4000)){
			new BigBoss(400-64,-100,128,128,350);
			event[3]=true;
		}
		if(condEvent(4,45000+4000)){
			new BigBoss(200-64,-100,128,128,350);
			new BigBoss(600-64,-100,128,128,350);
			spawn = false;
			event[4]=true;
		}
		if(condEvent(5,65000+4000)){
			spawn = true;
			event[5]=true;
		}
		if(condEvent(6,75000+4000)){
			new BigBoss(200-64,-100,128,128,350);
			new BigBoss(600-64,-100,128,128,350);
			event[6]=true;
		}
		if(condEvent(7,105000+4000+10000)){
			if(MissionMenu.numMessage == 0)
				m1 = true;
			else
				m2 = true;
			event[7]=true;
		}if(condEvent(8,105000+4000+20000)){
			new MegaBigBoss(290,-100,128,128,350);
			event[8]=true;
		}if(condEvent(9,105000+8000+20000)){
				m1 = false;
				m2 = false;
			event[9]=true;
		}




	}

	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}

	public boolean condEvent(int i, long time){
		return (!event[i] && (long)System.currentTimeMillis() - timeStart>time);
	}


}
