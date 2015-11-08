package fr.entity.character.enemy;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyGenerator {
	
	long lastSpawn;
	long time;
	Random rand;
	
	public void init(GameContainer container,StateBasedGame game){
		time = 250;
		lastSpawn = System.currentTimeMillis();
		rand = new Random();
		new RotateEnemy(200, 200, 32, 32, 200);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(System.currentTimeMillis() - lastSpawn > time){
			lastSpawn = System.currentTimeMillis();
			new BasicEnemy(genDouble(0,800),genDouble(0,300),32,32,500);
		}
	}
	
	public double genDouble(double min, double max){
		return (min+rand.nextInt((int)(max - min)));
	}

}
