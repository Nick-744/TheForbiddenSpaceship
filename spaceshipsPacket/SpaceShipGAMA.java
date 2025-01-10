package spaceshipsPacket;

import java.awt.Color;

public class SpaceShipGAMA extends SpaceShip {
	
	private final static int move = 30;
	private final static String texturePath = "GAMA.png";
	private final static String name = "GAMA";
	private final static Color LaserColor = Color.WHITE;

	public SpaceShipGAMA() {
		super(move, texturePath, name, LaserColor);
		
		return;
	}
	
	
}
