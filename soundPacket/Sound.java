package soundPacket;

import javax.sound.sampled.*; 

import mainPacket.MainClass;

public class Sound {
	public final static boolean LOOP_CONTINUOUSLY = true;
	
	private AudioInputStream audioInputStream;
	private String audioPath = MainClass.audioPath;
	Clip clip;
	
	public Sound(String audioPath){
		try {
			sameCodeForCons(audioPath);
		}
		catch (Exception ex) { System.out.println(ex); } 
		
		return;
	}
	
	public Sound(String audioPath, boolean loopContinuously){
		try {
			sameCodeForCons(audioPath);
			
			clip.loop(Clip.LOOP_CONTINUOUSLY) ; 
			
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f);
		}
		catch (Exception ex) { System.out.println(ex); } 
		
		return;
	}
	
	private void sameCodeForCons(String audioPath) {
		this.audioPath += audioPath;
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(MainClass.class.getResource(this.audioPath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
			clip.start();
		}
		catch (Exception ex) { System.out.println(ex); } 
		
		return;
	}
	
	public void stopSound() {
		this.clip.stop();
		
		return;
	}
	
}
