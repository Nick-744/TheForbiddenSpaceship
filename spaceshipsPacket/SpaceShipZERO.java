package spaceshipsPacket;

import java.awt.Color;

public class SpaceShipZERO extends SpaceShip {
	
	private final static int move = 5;
	private final static String texturePath = "ZERO.png";
	private final static String name = "ZERO";
	private final static Color LaserColor = Color.YELLOW;
	
	public SpaceShipZERO() {
		super(move, texturePath, name, LaserColor);
		
		return;
	}
	
}
