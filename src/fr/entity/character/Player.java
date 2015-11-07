package fr.entity.character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World.direction;

public class Player extends Movable implements Rectangle {

	private direction dir=direction.GAUCHE;
	private boolean ispressedHaut,ispressedBas,ispressedDroite,ispressedGauche;
	
	private boolean upPress = false;
	private boolean downPress = false;
	private boolean leftPress = false;
	private boolean rightPress = false;
	
	private Image imagegauche,imagecentrale,imagedroite,image;
	
	public Player() {
		x = 400;
		y = 500;
		width = 64;
		height = 64;
		speedX = 0;
		speedY = 0;
		isMoving = false;
		try {
			image=new Image("sprites/ship1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		
		
		g.drawImage(image,(float)x,(float)y);
		g.setColor(Color.green);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		speedX = 0;
		speedY = 0;
		
		if(upPress)
		{
			y -= 5;
			System.out.println("haut");
		}
		if(downPress)
		{
			//speedY = 0.5;
			y += 5;
		}
		if(leftPress)
		{
			//speedX = -0.5;
			x -= 5;
		}
		if(rightPress)
		{
			//speedX = 0.5;
			x += 5;
		}
		/*
		moveX(1);
		moveY(1);*/
		
	}

	public void keyReleased(int key, char c) {
		
		switch (key){
		
			case Input.KEY_UP:
				upPress=false;
			break;
			
			case Input.KEY_DOWN:
				downPress=false;
			break;
			
			case Input.KEY_LEFT:
				leftPress=false;
			break;
			
			case Input.KEY_RIGHT:
				rightPress=false;
			break;
			
		}
		
		
	}

	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
			upPress=true;
		break;
		
		case Input.KEY_DOWN:
			downPress=true;
		break;
		
		case Input.KEY_LEFT:
			leftPress=true;
		break;
		
		case Input.KEY_RIGHT:
			rightPress=true;
		break;
		
		}
		
	}

}
