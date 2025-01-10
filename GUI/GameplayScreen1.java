package GUI;

import mainPacket.MainClass;
import spaceshipsPacket.SpaceShip;
import spaceshipsPacket.SpaceShipENEMY;

class GameplayScreen1 extends GameplayScreen {

	private static final long serialVersionUID = 6668849529849260882L;
	
	private final static int framesNum = 48;
	private final static String imagesPathAndName = MainClass.imagesPath + "Gameplay1_bg/gameplay_bg-", fileExtension = ".jpg";
	private final static int time = 250;
	
	private GameplayScreen1(SpaceShip enemy, promptScreen nextStageScreen) {
		super(framesNum, imagesPathAndName, fileExtension, time, enemy, nextStageScreen);
		
		return;
	}
	
	static GameplayScreen getGPS1() {
		SpaceShip enemy =  new SpaceShipENEMY(0);
		StageScreen nextStageScreen = new Stage2();
		
		GameplayScreen1 GPS1 = new GameplayScreen1(enemy, nextStageScreen);
		
		return GPS1;
	}

	@Override
	void chasePlayerAlgorithm() {
		((SpaceShipENEMY) enemy).chasePlayer(player.pos.getCoordX(), player.pos.getCoordY());
		
		return;
	}

}
