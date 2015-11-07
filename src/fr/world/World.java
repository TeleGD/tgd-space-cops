package fr.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.character.Player;
import fr.entity.character.enemy.Enemy;
import fr.entity.character.enemy.EnemyGenerator;
import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;


public class World extends BasicGameState{
	
	public enum direction {HAUT,DROITE,BAS,GAUCHE};
	private static Player player;
	public static int ID=0;
	private static Projectile p1;
	private static ArrayList<Projectile> projectiles;
	private static ArrayList<Enemy> enemies;
	private static EnemyGenerator enemyGen;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player = new Player();
		p1 = new ProjectileType0(400,400,45);
		enemies = new ArrayList<Enemy>();
		enemyGen = new EnemyGenerator();
		enemyGen.init(container,game);
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		player.render(container, game, g);
		p1.render(container,game,g);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).render(container, game, g);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).render(container, game, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, game, delta);
		p1.update(container, game,delta);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).update(container, game, delta);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).update(container, game, delta);
		}
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
	}
	
	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public static ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}


	@Override
	public int getID() {
		return ID;
	}
	
}
