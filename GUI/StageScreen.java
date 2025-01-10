package GUI;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

abstract class StageScreen extends promptScreen {

	private static final long serialVersionUID = -5653982525187677271L;
	
	GameplayScreen gameStage;
	Timer timer;
	
	StageScreen(String LabelText, Color color, GameplayScreen gameStage) {
		super(LabelText, color);
		
		this.gameStage = gameStage;
		
		return;
	}
	
	public void startTimerForNextScene() {
		setTimerForNextScene(3000);
		
		return;
	}
	
	public void setTimerForNextScene(int time) {
		timer = new Timer();
		
		TimerTask task = new nextScreenAfterTimer();
		timer.schedule(task, time);
		
		return;
	}
	
	class nextScreenAfterTimer extends TimerTask {
		public void run(){
			mainGUIwindow.MainPanel.add(gameStage);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			gameStage.setFocusable(true);
			gameStage.requestFocus();
			gameStage.startSceneAnimation();
			
			timer.cancel();
			
			return;
		}
	}

}
