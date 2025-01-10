package GUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import soundPacket.GameOverSound;
import soundPacket.GameWinSound;
import spaceshipsPacket.SpaceShip;

abstract class GameplayScreen extends JPanel implements KeyListener {

	private static final long serialVersionUID = -1576593624330445583L;
	
	SpaceShip player;
	SpaceShip enemy;
	
	liveBgImage backGroundImage;
	promptScreen StageScreen;
	promptScreen LoseScreen;
	
	Timer timer;
	
	int framesNum;
	String imagesPathAndName, fileExtension;
	int time;  /* Bg refresh time */
	
	GameplayScreen(int framesNum, String imagesPathAndName, String fileExtension, int time, SpaceShip enemy, promptScreen StageScreen) {
		addKeyListener(this);
		
		this.enemy = enemy;
		this.StageScreen = StageScreen;
		
		this.framesNum = framesNum - 1;
		this.imagesPathAndName = imagesPathAndName;
		this.fileExtension = fileExtension;
		this.time = time;
		this.backGroundImage = createBackGround();
		this.add(backGroundImage);
		
		return;
	}
	
	void startSceneAnimation() {
		this.player = SelectSpaceShipScreen.player;
		
		this.player.resetSpaceShip();
		this.enemy.Gun.Reset();
		
		timer = new Timer();
			
		TimerTask task = new AnimationTimeTask();
		timer.schedule(task, 50, 50);
	
		return;
	}
	
	class AnimationTimeTask extends TimerTask {
		public void run() {
			chasePlayerAlgorithm();
			backGroundImage.repaint();
			
			return;
		}
	}
	
	abstract void chasePlayerAlgorithm();
	
	private liveBgImage createBackGround() {
		liveBgImage backGroundImage = new liveBgImage(this.framesNum, this.time, this.imagesPathAndName, this.fileExtension) {
			
			private static final long serialVersionUID = 4538062136340482982L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				showLaserShootings(g);
				
				player.TextureIcon.paintIcon(this, g, player.pos.getCoordX(), player.pos.getCoordY());
				enemy.TextureIcon.paintIcon(this, g, enemy.pos.getCoordX(), enemy.pos.getCoordY());

				checkWinLoseStatus();
				
				player.getXPtexture().paintIcon(this, g, 20, 15);

				return;
			}
		};
		
		return backGroundImage;
	}
	
	private void showLaserShootings(Graphics g) {
		int moveLineStep = 15;
		
		player.showLaserShootings(g, enemy, -moveLineStep);
		enemy.showLaserShootings(g, player, moveLineStep);
		
		return;
	}
	
	@Override
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.moveUP();
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			player.moveLEFT();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.moveRIGHT();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			player.moveDOWN();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.Gun.fire(player.pos.getCoordX(), player.pos.getCoordY());
		}
		
		return;
	}
	
	@Override
	public void keyReleased (KeyEvent e) { return; }
	
	@Override
	public void keyTyped (KeyEvent e) { return; }	
	
	private void checkWinLoseStatus() {
		if(player.noHealth()) {
			new GameOverSound();
			
			LoseScreen = new loseScreen();
			
			mainGUIwindow.MainPanel.add(LoseScreen);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			LoseScreen.setFocusable(true);
			LoseScreen.requestFocus();
			
			this.timer.cancel();
			this.backGroundImage.stopAnimation();
		}
		else if(enemy.noHealth()) {
			new GameWinSound();
			
			nextScreenIfPlayerWon();
			
			this.timer.cancel();
			this.backGroundImage.stopAnimation();
		}
		
		return;
	}
	
	protected void nextScreenIfPlayerWon() {
		mainGUIwindow.MainPanel.add(StageScreen);
		mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
		
		try {
			((StageScreen) StageScreen).startTimerForNextScene();
		}
		catch (Exception e) {}
		StageScreen.setFocusable(true);
		StageScreen.requestFocus();
		
		return;
	}

}
