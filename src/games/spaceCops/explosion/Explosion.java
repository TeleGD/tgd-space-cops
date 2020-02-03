package games.spaceCops.explosion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceCops.util.Movable;

public class Explosion extends Movable {

	private int explosion;
	private Image[] image=new Image[60];
	private double scale;

	public Explosion(double x,double y,double scale)
	{
		this.x = x;
		this.y = y;
		this.scale=scale;
		loadExplosion();
		explosion = 179;
		bruit();
	}

	public void loadExplosion(){
		for(int i = 0; i<60; i++){
			if(i<8){
				image[i] = AppLoader.loadPicture("/images/spaceCops/explosion/000"+(i+2)+".png");
			}
			else{
				image[i] = AppLoader.loadPicture("/images/spaceCops/explosion/00"+(i+2)+".png");
			}
			image[i]=image[i].getScaledCopy((float) scale);
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if(explosion>=0){
			g.drawImage(image[59-(explosion/3)],(float) (x-64), (float)(y-16));
		}

	}
	public boolean finishTest(){
		return  (explosion == 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(explosion>0){
			explosion--;
		}
	}

	public void bruit() {
		Audio sound= AppLoader.loadAudio("/sounds/spaceCops/explosion.ogg");
		sound.playAsSoundEffect(1, .3f, false);
	}

}
