package fr.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.character.Player;
import fr.entity.character.enemy.Enemy;
import fr.entity.character.enemy.EnemyGenerator;
import fr.entity.projectile.Projectile;
import fr.entity.projectile.type.ProjectileType0;
import fr.main.Game;
import fr.menus.*;
import fr.decor.Decor;


public class World extends BasicGameState{
	
	public enum direction {HAUT,DROITE,BAS,GAUCHE};
	private static Player player;
	public static int ID=0;
	private static ArrayList<Projectile> projectiles;
	private static ArrayList<Enemy> enemies;
	private static EnemyGenerator enemyGen;
	private GameContainer container;
	private StateBasedGame game;
	private Decor decor;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		player = new Player();
		enemies = new ArrayList<Enemy>();
		enemyGen = new EnemyGenerator();
		enemyGen.init(container,game);
		projectiles = new ArrayList<Projectile>();
		this.container = container;
		this.game = game;
		decor = new Decor();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		decor.render(container, game,g);
		player.render(container, game, g);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).render(container, game, g);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).render(container, game, g);
		}
		g.setColor(Color.white);
		g.drawString(""+Game.getScore(), 10, 50);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, game, delta);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).update(container, game, delta);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).update(container, game, delta);
		}
		decor.update(container,game,delta);
		enemyGen.update(container, game, delta);
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
		if(key == Input.KEY_ESCAPE){
			game.enterState(PauseMenu.ID);
		}
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
