package fr.util;

import fr.entity.Entity;

public abstract class Movable extends Entity{

	protected double oldX,oldY;
	protected double speedX,speedY;
	protected double accelX,accelY;
	protected boolean isMoving;
	protected boolean jump;
	protected int jumpLeft;
	protected int jumpMax;
	protected double jumpPower;
	protected boolean collision;

	public void moveX(int delta){
		if(isMoving){
			x+=speedX*delta;
		}
	}

	public void moveY(int delta){
		if(isMoving){
			y+=speedY*delta;
		}
	}

	public void jump(int delta){
		if(jumpLeft > 0){
		speedY = -jumpPower*delta;
		jumpLeft--;
		}
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public double getOldX() {
		return oldX;
	}

	public void setOldX(double oldX) {
		this.oldX = oldX;
	}

	public double getOldY() {
		return oldY;
	}

	public void setOldY(double oldY) {
		this.oldY = oldY;
	}

	public int getJumpLeft () {
		return jumpLeft;
	}

	public void setJump(int jumpLeft) {
		this.jumpLeft = jumpLeft;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getAccelX() {
		return accelX;
	}

	public void setAccelX(double accelX) {
		this.accelX = accelX;
	}

	public double getAccelY() {
		return accelY;
	}

	public void setAccelY(double accelY) {
		this.accelY = accelY;
	}

}
