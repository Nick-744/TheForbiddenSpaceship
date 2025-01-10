package spaceshipsPacket;

import java.awt.Color;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceShipENEMY2 extends SpaceShip {
	
	private final static int move = 20;
	private final static String texturePath = "ΕΝΕΜΥ2.png";
	private final static String name = "ENEMY2";
	private final static int chaseDistance = 30;
	private final static Color LaserColor = Color.MAGENTA;
	final static int enemyHealth = 50;
	
	public SpaceShipENEMY2(int yCoordStart) {
		super(move, texturePath, name, LaserColor);
		
		this.pos.setCoords(this.pos.getCoordX(), yCoordStart);
		this.health = enemyHealth;
		this.startFiring();
		
		return;
	}
	
	public void chasePlayer(int xPlayer, int yPlayer) {
		int distancePE = this.pos.getCoordX() - xPlayer;
		
		if(distancePE < -chaseDistance) {
			this.moveRIGHT();
		}
		else if(distancePE > chaseDistance) {
			this.moveLEFT();
		}
		
		if(Math.sqrt(distancePE * distancePE) > (5 * chaseDistance)) {
			this.setMove(2 * SpaceShipENEMY2.move);
		}
		else {
			this.setMove(SpaceShipENEMY2.move);
		}
		
		return;
	}
	
	public void startFiring() {
		Timer timer = new Timer();
		
		TimerTask task = new FireGunTimeTask();
		timer.schedule(task, 300, 300);
		
		return;
	}
	
	class FireGunTimeTask extends TimerTask {
		public void run() {
			Gun.fire(pos.getCoordX(), pos.getCoordY() + SpaceShip.spaceShipYheight);
			
			return;
		}
	}
	
}
