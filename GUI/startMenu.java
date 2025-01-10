package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import mainPacket.MainClass;
import soundPacket.spaceShipSelectionAudio;

class startMenu extends promptScreen {
	
	private static final long serialVersionUID = -4176185593361029656L;
	
	private int frame = 0;
	private final int framesNum = 9 - 1;
	private final String imagesPathAndName = MainClass.imagesPath + "SelectShip_bg/selectShip_bg-", fileExtension = ".jpg";
	
	Timer timer;

	startMenu() {
		super("The Forbidden Spaceship", Color.RED, ADD_BUTTON);
		
		setFPSbgImage(100);
		
		return;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(frame > framesNum) { frame = 0; }
		else frame++;
		try {
			g.drawImage(ImageIO.read(MainClass.class.getResource(imagesPathAndName + frame + fileExtension)), 0, 0, null);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	private void setFPSbgImage(int time) {
		timer = new Timer();
		TimerTask task = new AnimateBG();
		timer.schedule(task, 0, time);
		
		return;
	}
	
	class AnimateBG extends TimerTask {
		public void run() {
			repaint();
		}
	}
	
	@Override
	JPanel createReturnButtonPanel() {
		JPanel tempPanel = new JPanel(); { tempPanel.setOpaque(false); }
		
		JButton Button = new JButton("START");
		{	
			Button.setHorizontalAlignment(JButton.CENTER);
			Button.setVerticalAlignment(JButton.CENTER);
			
			Button.setForeground(Color.ORANGE);
			Button.setFont(new Font("Arcade Classic", Font.BOLD, 20));
			
			Button.setOpaque(false);
			Button.setContentAreaFilled(false);
			Button.setBorderPainted(false);
			
			Button.addActionListener(new SelectedButtonHandler(Button.getName()));
			Button.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	Button.setForeground(Color.GREEN);
			        
			        return;
			    }
	
			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	Button.setForeground(Color.ORANGE);
			    	
			    	return;
			    }
			});
		}    /* Button */
		
		tempPanel.add(Button);
		
		return tempPanel;
	}
	
	class SelectedButtonHandler implements ActionListener {
		String name;
		
		public SelectedButtonHandler(String name) {
			this.name = name;
			
			return;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new spaceShipSelectionAudio();
			
			startCutScene startCutScene = new startCutScene();
			
			mainGUIwindow.MainPanel.add(startCutScene);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			startCutScene.setFocusable(true);
			startCutScene.requestFocus();
			
			return;
		}
	}
	
}
