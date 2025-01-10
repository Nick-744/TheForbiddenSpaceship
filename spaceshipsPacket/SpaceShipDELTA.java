package spaceshipsPacket;

import java.awt.Color;

public class SpaceShipDELTA extends SpaceShip {
	
	private final static int move = 40;
	private final static String texturePath = "DELTA.png";
	private final static String name = "DELTA";
	private final static Color LaserColor = Color.ORANGE;

	public SpaceShipDELTA() {
		super(move, texturePath, name, LaserColor);
		
		return;
	}
	
	
}
