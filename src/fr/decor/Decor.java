package fr.decor;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;


public class Decor extends Movable{

	private Image fond;
	private int compteur;
	static ArrayList<Nuage> nuages=new ArrayList<Nuage>();
	
	public Decor()
	{
		try {
			fond=new Image("sprites/ocean2.png");
			fond=fond.getScaledCopy(800, 600);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		g.drawImage(fond,0,0+compteur%600);
		g.drawImage(fond,0,-600+compteur%600);
		for(int i=0;i<nuages.size();i++)
		{
			nuages.get(i).render(container, game, g);
			
		}
		
				
		

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		compteur+=1;
		if(compteur%30==0 && ((int)Math.random()*2)==0)// on genere aleatoirement les nuages 1chance sur 8 tous les 100 frames
		{
			double scale=Math.random()*10.5+1.4;
			nuages.add(new Nuage(Math.random()*800-32*scale,-400, (Math.random()*1.5+0.5),(int) (Math.random()*3),scale));
		}

		
		for(int i=0;i<nuages.size();i++)
		{
			nuages.get(i).update(container, game, delta);
		}
		
	}
	
	

}
