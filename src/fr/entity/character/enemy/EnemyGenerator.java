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
	
	public void init(GameContainer container,StateBasedGame game){
		time = 1000;
		lastSpawn = System.currentTimeMillis();
		rand = new Random();	
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!started){
			timeStart = System.currentTimeMillis();
			started = true;
		}
		if(System.currentTimeMillis() - lastSpawn > time){
			lastSpawn = System.currentTimeMillis();
			new BasicEnemy(genDouble(0,800),genDouble(0,300),32,32,500);
		}
		
		System.out.println((long)System.currentTimeMillis() - timeStart);

		if(!event[0] && (long)System.currentTimeMillis() - timeStart>5000){
			new Boss(400-64,50,128,128,200);
			event[0]=true;
		}
	}
	
	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}
	
	public boolean condEvent(int i, long time){
		return (!event[i] && (long)System.currentTimeMillis() - timeStart>time);
	}
	

}
