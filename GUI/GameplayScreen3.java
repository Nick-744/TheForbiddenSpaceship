package GUI;

import mainPacket.MainClass;
import spaceshipsPacket.SpaceShip;
import spaceshipsPacket.SpaceShipENEMY3;

class GameplayScreen3 extends GameplayScreen {

	private static final long serialVersionUID = 6668849529849260882L;
	
	private final static int framesNum = 12;
	private final static String imagesPathAndName = MainClass.imagesPath + "Gameplay3_bg/gameplay_bg-", fileExtension = ".png";
	private final static int time = 150;
	
	GameplayScreen3(SpaceShip enemy, promptScreen nextScreen) {
		super(framesNum, imagesPathAndName, fileExtension, time, enemy, nextScreen);
		
		return;
	}
	
	static GameplayScreen getGPS3() {
		SpaceShip enemy =  new SpaceShipENEMY3(0);
		winScreen nextScreen = new winScreen();
		
		GameplayScreen3 GPS3 = new GameplayScreen3(enemy, nextScreen);

		return GPS3;
	}

	@Override
	void chasePlayerAlgorithm() {
		((SpaceShipENEMY3) enemy).chasePlayer(player.pos.getCoordX(), player.pos.getCoordY());
		
		return;
	}

}
