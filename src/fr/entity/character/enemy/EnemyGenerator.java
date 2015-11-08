package fr.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyGenerator {
	
	long lastSpawn;
	long time;
	Random rand;
	boolean event[] = new boolean[10];
	long timeStart;
	boolean started = false;
	boolean spawn = false;
	
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

		
		
		if(condEvent(0,3000+4000)){
			spawn = true;
			event[0]=true;
		}
		if(condEvent(1,5000+4000)){
			new Boss(400-64,-100,128,128,350);
			spawn = false;
			event[1]=true;
		}
		if(condEvent(2,12000+4000)){
			spawn = true;
			event[2]=true;
		}
		if(condEvent(3,25000+4000)){
			new Boss(400-64,-100,128,128,350);
			event[3]=true;
		}
		if(condEvent(4,45000+4000)){
			new Boss(200-64,-100,128,128,350);
			new Boss(600-64,-100,128,128,350);
			spawn = false;
			event[4]=true;
		}
		if(condEvent(5,65000+4000)){
			spawn = true;
			event[5]=true;
		}
		if(condEvent(6,75000+4000)){
			new Boss(200-64,-100,128,128,350);
			new Boss(600-64,-100,128,128,350);
			event[6]=true;
		}
		if(condEvent(7,100000+4000)){
			time = 300;
			event[7]=true;
		}
		
		
		
		
	}
	
	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}
	
	public boolean condEvent(int i, long time){
		return (!event[i] && (long)System.currentTimeMillis() - timeStart>time);
	}
	

}
