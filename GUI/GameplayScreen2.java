package GUI;

import mainPacket.MainClass;
import spaceshipsPacket.SpaceShip;
import spaceshipsPacket.SpaceShipENEMY2;

class GameplayScreen2 extends GameplayScreen {

	private static final long serialVersionUID = 6668849529849260882L;
	
	private final static int framesNum = 8;
	private final static String imagesPathAndName = MainClass.imagesPath + "Gameplay2_bg/gameplay_bg-", fileExtension = ".png";
	private final static int time = 100;
	
	private GameplayScreen2(SpaceShip enemy, promptScreen nextStageScreen) {
		super(framesNum, imagesPathAndName, fileExtension, time, enemy, nextStageScreen);
		
		return;
	}
	
	static GameplayScreen getGPS2() {
		SpaceShip enemy =  new SpaceShipENEMY2(0);
		StageScreen nextStageScreen = new Stage3();
		
		GameplayScreen2 GPS2 = new GameplayScreen2(enemy, nextStageScreen);
		
		return GPS2;
	}

	@Override
	void chasePlayerAlgorithm() {
		((SpaceShipENEMY2) enemy).chasePlayer(player.pos.getCoordX(), player.pos.getCoordY());
		
		return;
	}
	
}
