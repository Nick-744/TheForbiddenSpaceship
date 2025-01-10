package spaceshipsPacket;

import java.awt.Color;

public class SpaceShipBETA extends SpaceShip {
	
	private final static int move = 20;
	private final static String texturePath = "BETA.png";
	private final static String name = "BETA";
	private final static Color LaserColor = Color.GREEN;
	
	public SpaceShipBETA() {
		super(move, texturePath, name, LaserColor);
		
		return;
	}
	
}
