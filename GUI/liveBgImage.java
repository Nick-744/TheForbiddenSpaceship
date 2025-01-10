package GUI;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import spaceshipsPacket.SpaceShip;

class liveBgImage extends JLabel {
	
	private static final long serialVersionUID = 4006966799550660761L;
	
	private int frame = 0;
	private final int framesNum;
	private final String imagesPathAndName, fileExtension;
	
	private Timer timer;
	
	liveBgImage(int framesNum, int timeToChangeFrame, String imagesPathAndName, String fileExtension) {
		// timeToChangeFrame's unit of measurement == msec
		this.framesNum = framesNum - 1;
		this.imagesPathAndName = imagesPathAndName;
		this.fileExtension = fileExtension;
		
		setFPS(timeToChangeFrame);
		
		return;
	}
	
	private void reloadFrameBackGroundImage() {
		if(frame > framesNum) { frame = 0; }
		else frame++;
		try {
			this.setIcon(new ImageIcon(SpaceShip.class.getResource(imagesPathAndName + frame + fileExtension)));
		} catch(NullPointerException e) {
			System.out.println(e + " - Frame " + imagesPathAndName + frame + fileExtension + " doesn't exist!\n");
		}
		
		return;
	}
	
	void stopAnimation() {
		timer.cancel();
		
		return;
	}
	
	private void setFPS(int time) {
		timer = new Timer();
		TimerTask task = new Animate();
		timer.schedule(task, time, time);
		
		return;
	}
	
	class Animate extends TimerTask {
		public void run() {
			reloadFrameBackGroundImage();
			
			return;
		}
	}

}
