package laserGun;

import spaceshipsPacket.SpaceShip;

public class Laser {
	public int x;
	public int y;
	
	Laser(int x, int y) {
		this.x = x + SpaceShip.spaceShipXwidth / 2;
		this.y = y;
		
		return;
	}
}
