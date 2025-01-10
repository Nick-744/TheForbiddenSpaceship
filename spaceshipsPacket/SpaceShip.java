package spaceshipsPacket;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import laserGun.Laser;
import laserGun.LaserGun;
import mainPacket.MainClass;

abstract public class SpaceShip implements Navigation{
	
	public final static int spaceShipYheight = 100, spaceShipXwidth = 100;
	
	private String SpaceshipTexturePath = MainClass.imagesPath + "SpaceShipsTexture/";
	
	public LaserGun Gun;
	public Position pos;
	public final String name;
	public final ImageIcon TextureIcon;
	public int health = 30;
	
	private int move;
	
	SpaceShip(int move, String SpaceshipTexture, String name, Color LaserColor) {
		this.pos = new Position(spaceShipYheight, spaceShipXwidth);
		this.move = move;
		
		this.SpaceshipTexturePath += SpaceshipTexture;
		this.TextureIcon = new ImageIcon(SpaceShip.class.getResource(this.SpaceshipTexturePath));
		
		this.name = name;
		
		this.Gun = new LaserGun(LaserColor);
		
		return;
	}
	
	public void resetSpaceShip() {
		this.pos = new Position(spaceShipYheight, spaceShipXwidth);
		this.Gun.Reset();
		
		return;
	}
	
	public ImageIcon getXPtexture() {
		String XpTexturePath = MainClass.imagesPath + "xp_texture/xp_";
		int index;
		
		if(health == 30) { index = 4; }
		else if(20 < health && health < 30) { index = 3; }
		else if(10 < health && health <= 20) { index = 2; }
		else if(2 < health && health <= 10) { index = 1; }
		else if(0 <= health && health <= 2) { index = 0; }
		else { index = 0; }
		
		ImageIcon XPtexture = new ImageIcon(SpaceShip.class.getResource(XpTexturePath + index + ".png"));
		
		return XPtexture;
	}
	
	public void showLaserShootings(Graphics g, SpaceShip SS, int moveLineStep) {
		int LinkedListSize = this.Gun.laserShotsLinkedList.size();
		
		for(int i = 0; i < LinkedListSize; i++) {
			Laser temp = this.Gun.laserShotsLinkedList.get(i);
			
			g.setColor(this.Gun.laserColor);
			g.drawLine(temp.x, temp.y, temp.x, temp.y + moveLineStep);
			temp.y = temp.y + moveLineStep;
			
			if(SS.isInArea(temp.x, temp.y)) {
				SS.gotHit(); /* Το αντίπαλο σκάφος έχασε 1 ζωή */
				this.Gun.laserShotsLinkedList.remove(i);
				LinkedListSize--;
			}
			else if(!Position.isInStage(temp.x, temp.y)) {
				this.Gun.laserShotsLinkedList.remove(i);
				LinkedListSize--;
			}
		}
		
		return;
	}
	
	public boolean isInArea(int x, int y) {
		boolean In = false;
		
		boolean xInCondition = this.pos.getCoordX() < x && x  < this.pos.getCoordX() + SpaceShip.spaceShipXwidth;
		boolean yInCondition = this.pos.getCoordY() < y && y  < this.pos.getCoordY() + SpaceShip.spaceShipYheight;
		
		if(xInCondition && yInCondition) {
			In = true;
		}
		
		return In;
	}
	
	public void gotHit() {
		this.health -= 1;
		
		return;
	}
	
	public boolean noHealth() {
		boolean noHealth = false;
		
		if(this.health < 1) {
			noHealth = true;
		}
		
		return noHealth;
	}
	
	public void setMove(int newMove) {
		this.move = newMove;
		
		return;
	}
	
	public static LinkedList<SpaceShip> createAllPlayableSpaceShips() {
		LinkedList<SpaceShip> spaceShipsList = new LinkedList<SpaceShip>();
		
		SpaceShip zero = new SpaceShipZERO();
		SpaceShip a = new SpaceShipALPHA();
		SpaceShip b = new SpaceShipBETA();
		SpaceShip c = new SpaceShipGAMA();
		SpaceShip d = new SpaceShipDELTA();
		
		spaceShipsList.add(zero);
		spaceShipsList.add(a);
		spaceShipsList.add(b);
		spaceShipsList.add(c);
		spaceShipsList.add(d);
		
		return spaceShipsList;
	}
	
	public int moveUP() {
		pos.setCoordY(-move);
		
		return pos.getCoordY();
	}
	
	public int moveDOWN() {
		pos.setCoordY(+move);
		
		return pos.getCoordY();
	}
	
	public int moveLEFT() {
		pos.setCoordX(-move);
		
		return pos.getCoordX();
	}
	
	public int moveRIGHT() {
		pos.setCoordX(+move);
		
		return pos.getCoordX();
	}
	
}
