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
	protected static int defilement=3;
	static ArrayList<Nuage> nuages=new ArrayList<Nuage>();
	static ArrayList<Island> islands=new ArrayList<Island>();
	
	public Decor()
	{
		try {
			fond=new Image("sprites/OCEAN4.png");
			fond=fond.getScaledCopy(256,256);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		for(int i=0;i<7;i++)
		{
			for(int j=-1;j<6;j++)
			{
				g.drawImage(fond,i*256,j*256+(defilement*compteur)%256);
			}
		}
		for(int i=0;i<islands.size();i++)
		{
			islands.get(i).render(container, game, g);
			
		}
		for(int i=0;i<nuages.size();i++)
		{
			nuages.get(i).render(container, game, g);
			
		}
		

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		compteur+=1;
		if(compteur%10==0 && ((int)Math.random()*2)==0)// on genere aleatoirement les nuages 1chance sur 8 tous les 100 frames
		{
			double scale=Math.random()*13+0.5;
			nuages.add(new Nuage(Math.random()*800-32*scale,-800, (Math.random()*0.3+0.2),(int) (Math.random()*3),scale));
		}
		
		if(compteur%100==0 && ((int)Math.random()*3)==0)// on genere aleatoirement les nuages 1chance sur 8 tous les 100 frames
		{
			islands.add(new Island(Math.random()*800-32,-800));
		}

		
		for(int i=0;i<nuages.size();i++)
		{
			nuages.get(i).update(container, game, delta);
		}
		for(int i=0;i<islands.size();i++)
		{
			islands.get(i).update(container, game, delta);
		}
		
	}
	
	

}
