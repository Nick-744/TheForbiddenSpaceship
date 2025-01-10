package spaceshipsPacket;

import java.awt.Color;

public class SpaceShipALPHA extends SpaceShip {
	
	private final static int move = 10;
	private final static String texturePath = "ALPHA.png";
	private final static String name = "ALPHA";
	private final static Color LaserColor = Color.BLUE;
	
	public SpaceShipALPHA() {
		super(move, texturePath, name, LaserColor);
		
		return;
	}
	
}
