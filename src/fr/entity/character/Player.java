package fr.entity.character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World;
import fr.world.World.direction;

public class Player extends Movable implements Rectangle {

	private direction dir=direction.GAUCHE;
	private boolean ispressedHaut,ispressedBas,ispressedDroite,ispressedGauche;

	public Player() {
		x = 400;
		y = 500;
		width = 32;
		height = 32;
		speedX = 0;
		speedY = 0;
		isMoving = false;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		
		g.fillOval((float) x, (float) y, (float)width,(float)height);
		g.setColor(Color.green);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (isMoving()) {
			if(ispressedGauche  && ispressedHaut)
			{
				speedX=-0.5;
				speedY=-0.5;
				if(x+speedX>0)moveX(delta);
				
				if(y+speedY>300)moveY(delta);
				
				
			}else if(ispressedGauche  && ispressedBas)
			{
				speedX=-0.5;
				speedY=0.5;
				if(x+speedX>0)moveX(delta);
				
				if(y+height+speedY<600)moveY(delta);
			}else 
				if(ispressedDroite  && ispressedHaut)
				{
					speedX=0.5;
					speedY=-0.5;
					if(x+speedX+width<800)moveX(delta);
					
					if(y+speedY>300)moveY(delta);
				}
			else if(ispressedDroite  && ispressedBas)
			{
				speedX=0.5;
				speedY=0.5;
				if(x+speedX+width<800)moveX(delta);
				
				if(y+height+speedY<600)moveY(delta);
			}else 
				if(dir==direction.HAUT){
				speedY=-0.5;
				if(y+speedY>300)moveY(delta);
			}
			else if(dir==direction.BAS)
			{
				speedY=0.5;
				if(y+height+speedY<600)moveY(delta);
			}
				
			else if( dir==direction.DROITE)
			{
				speedX=0.5;
				if(x+speedX+width<800)moveX(delta);
			}
			else if(dir==direction.GAUCHE)
			{
				speedX=-0.5;
				if(x+speedX>0)moveX(delta);
			}
				
		}
		
	}

	public void keyReleased(int key, char c) {
		
		switch (key){
		
			case Input.KEY_UP:ispressedHaut=false;
			if (ispressedBas) dir = direction.BAS;
			else setMoving(false);
			break;
			case Input.KEY_DOWN:ispressedBas=false;
			if (ispressedHaut) dir = direction.HAUT;
			else setMoving(false);
			break;
			case Input.KEY_LEFT:ispressedGauche=false;
			if (ispressedDroite) dir = direction.DROITE;
			else setMoving(false);
			break;
			case Input.KEY_RIGHT:ispressedDroite=false;
			if (ispressedGauche) dir = direction.GAUCHE;
			else setMoving(false);
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
		if(y-speedY>300){
			setMoving(true);
			ispressedHaut=true;
			dir=direction.HAUT;
		} else {
			setMoving(false);
		}
		break;
		case Input.KEY_DOWN:
			if(y+height + speedY <600){
				setMoving(true);
					dir=direction.BAS;
					ispressedBas=true;
			} else {
				setMoving(false);
			}
					
			break;
	case Input.KEY_LEFT:
		if(x-speedX>0){
			setMoving(true);
			dir=direction.GAUCHE;
			ispressedGauche=true;
				
		} else {
			setMoving(false);
		}
		break;
			
		case Input.KEY_RIGHT:
			if(x+speedX+width<800){
				setMoving(true);
				ispressedDroite=true;
					dir=direction.DROITE;
			} else {
				setMoving(false);
			}
			break;
		}
		
	}

}
