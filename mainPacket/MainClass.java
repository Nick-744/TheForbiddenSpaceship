package mainPacket;

import GUI.mainGUIwindow;

import spaceshipsPacket.SpaceShip;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.LinkedList;
import soundPacket.GameBgAudio;


public class MainClass {
	
	public final static int stageYheight = 720;
	public final static int stageXwidth = 1280;
	public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public final static LinkedList<SpaceShip> spaceShipsList = SpaceShip.createAllPlayableSpaceShips();
	
	public final static String imagesPath = "../images/";
	public final static String audioPath = "../audio/";
	
	public final static GameBgAudio audio = new GameBgAudio();
	
	public static mainGUIwindow mainWindow;

	public static void main(String[] args) {
		mainWindow = mainGUIwindow.getInstance();
		
		return;
	}
	
}
