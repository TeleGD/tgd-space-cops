package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyGenerator {
	
	public void init(GameContainer container,StateBasedGame game){
		new CircleEnemy(600,200,32,32,1000,12);
		new CircleEnemy(200,200,32,32,1000,12);
		new BasicEnemy(200,200,32,32,100);
		new BasicEnemy(200,200,32,32,100);
		new BasicEnemy(200,200,32,32,100);
	}

}
