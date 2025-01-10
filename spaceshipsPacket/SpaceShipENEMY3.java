package spaceshipsPacket;

import java.awt.Color;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceShipENEMY3 extends SpaceShip {
	
	private final static int move = 25;
	private final static String texturePath = "ENEMY3.png";
	private final static String name = "ENEMY3";
	private final static int chaseDistance = 40;
	private final static Color LaserColor = Color.CYAN;
	final static int enemyHealth = 100;
	
	public SpaceShipENEMY3(int yCoordStart) {
		super(move, texturePath, name, LaserColor);
		
		this.pos.setCoords(this.pos.getCoordX(), yCoordStart);
		this.health = enemyHealth;
		this.startFiring();
		
		return;
	}
	
	public void chasePlayer(int xPlayer, int yPlayer) {
		int distancePEx = this.pos.getCoordX() - xPlayer;
		int distancePEy = yPlayer - this.pos.getCoordY();
		
		if(distancePEx < -chaseDistance) {
			this.moveRIGHT();
		}
		else if(distancePEx > chaseDistance) {
			this.moveLEFT();
		}
		
		if(Math.sqrt(distancePEx * distancePEx) > (5 * chaseDistance)) {
			this.setMove(2 * SpaceShipENEMY3.move);
		}
		else {
			this.setMove(SpaceShipENEMY3.move);
		}
		
		if(Math.sqrt(distancePEy * distancePEy) > (7 * chaseDistance)) {
			this.moveDOWN();
		}
		else if (Math.sqrt(distancePEy * distancePEy) < (5 * chaseDistance)) {
			this.moveUP();
		}
		
		return;
	}
	
	public void startFiring() {
		Timer timer = new Timer();
		
		TimerTask task = new FireGunTimeTask();
		timer.schedule(task, 250, 250);
		
		return;
	}
	
	class FireGunTimeTask extends TimerTask {
		public void run() {
			Gun.fire(pos.getCoordX(), pos.getCoordY() + SpaceShip.spaceShipYheight);
			
			return;
		}
	}
	
}
