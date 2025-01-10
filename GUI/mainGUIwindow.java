package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainPacket.MainClass;

public class mainGUIwindow extends JFrame {

	private static final long serialVersionUID = -5655286018058315268L;
	
	private static mainGUIwindow instance;
	
	public final static int windowYheight = MainClass.stageYheight + 37; // 37 == border size.
	public final static int windowXwidth = MainClass.stageXwidth;
	
	static CardLayout cardLayout = new CardLayout();
	static JPanel MainPanel = new JPanel();
	
	private mainGUIwindow() {
		setMainPanelPreferences();
		this.setPreferences();
		
		this.add(MainPanel);
		
		return;
	}
	
	private void setPreferences() {
		this.setTitle("The Forbidden Spaceship");
		this.setSize(windowXwidth, windowYheight);
		
		this.setVisible(true);
		this.setFocusable(true);
		this.setResizable(false);
		
		{
			int x = (MainClass.screenSize.width / 2) - (windowXwidth / 2);
			int y = (MainClass.screenSize.height / 2) - (windowYheight / 2);
			this.setLocation(x, y);
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		return;
	}
	
	private void setMainPanelPreferences() {
		MainPanel.setSize(MainClass.stageXwidth, MainClass.stageYheight);
		
		MainPanel.setVisible(true);
		MainPanel.setFocusable(true);
		
		{
			MainPanel.setLayout(cardLayout);
			
			startMenu startMenu = new startMenu();
			MainPanel.add(startMenu);
			startMenu.setFocusable(true);
			startMenu.requestFocus();
		}
		
		return;
	}
	
	public static mainGUIwindow getInstance() {
		if(instance == null) {
			instance = new mainGUIwindow();
		}
		
		return instance;
	}

}
