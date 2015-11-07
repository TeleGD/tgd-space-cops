package fr.entity.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World.direction;

public class Player extends Movable implements Rectangle {

	private direction dir = direction.GAUCHE;
	private float hauteur = 64;
	private float largeur = 64;

	public Player() {

		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.fillRect((float) x, (float) y, largeur, hauteur);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (isMoving()) {
			switch (dir) {
			case HAUT:
				speedY=-0.0005;
				moveY(delta);
				break;
			case BAS:
				speedY=0.0005;
				moveY(delta);
				break;
			case DROITE:
				speedX=0.0005;
				moveX(delta);
				break;
			case GAUCHE:
				speedX=-0.0005;
				moveX(delta);
				break;
			}
		}
	}

	public void keyReleased(int key, char c) {

	}

	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
		if(y-speedY>300){
			setMoving(true);
				dir=direction.HAUT;
		} else {
			setMoving(false);
		}
		break;
		case Input.KEY_DOWN:
			if(y+hauteur + speedY <600){
				setMoving(true);
					dir=direction.BAS;
			} else {
				setMoving(false);
			}
					
			break;
	case Input.KEY_LEFT:
		if(x-speedX>0){
			setMoving(true);
				dir=direction.GAUCHE;
		} else {
			setMoving(false);
		}
		break;
			
		case Input.KEY_RIGHT:
			if(x+speedX+largeur<800){
				setMoving(true);
					dir=direction.DROITE;
			} else {
				setMoving(false);
			}
			break;
		}
		
	}

}
