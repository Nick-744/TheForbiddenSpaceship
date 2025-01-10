package spaceshipsPacket;

import java.awt.Color;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceShipENEMY extends SpaceShip {
	
	private final static int move = 15;
	private final static String texturePath = "ENEMY.png";
	private final static String name = "ENEMY";
	private final static int chaseDistance = 40;
	private final static Color LaserColor = Color.RED;
	
	public SpaceShipENEMY(int yCoordStart) {
		super(move, texturePath, name, LaserColor);
		
		this.pos.setCoords(this.pos.getCoordX(), yCoordStart);
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
			this.setMove(2 * SpaceShipENEMY.move);
		}
		else {
			this.setMove(SpaceShipENEMY.move);
		}
		
		return;
	}
	
	public void startFiring() {
		Timer timer = new Timer();
		
		TimerTask task = new FireGunTimeTask();
		timer.schedule(task, 450, 450);
		
		return;
	}
	
	class FireGunTimeTask extends TimerTask {
		public void run() {
			Gun.fire(pos.getCoordX(), pos.getCoordY() + SpaceShip.spaceShipYheight);
			
			return;
		}
	}
	
}
