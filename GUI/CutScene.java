package GUI;

import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import mainPacket.MainClass;
import soundPacket.spaceShipSelectionAudio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

class CutScene extends JPanel {
	
	private static final long serialVersionUID = 4006966799550660761L;
	
	private int page = 0;
	private final int pagesNum;
	private final String pagePathAndName, fileExtension;
	private JPanel nextButtonPanel;
	
	private Timer timer;
	
	CutScene(int pagesNum, String pagePathAndName, String fileExtension, List<Long> dt) {
		// timeToChangepage's unit of measurement == msec
		this.pagesNum = pagesNum - 1;
		this.pagePathAndName = pagePathAndName;
		this.fileExtension = fileExtension;
		
		dt.forEach((temp) -> {
			startCutScene(temp);
		});
		
		this.setLayout(new BorderLayout());
		nextButtonPanel = createNextButtonPanel();
		this.add(nextButtonPanel, BorderLayout.PAGE_END);
		
		return;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		page++;
		
		if(page > pagesNum) {
			page = pagesNum + 1;
			this.nextButtonPanel.setVisible(true);
		}

		try {
			g.drawImage(ImageIO.read(MainClass.class.getResource(pagePathAndName + page + fileExtension)), 0, 0, null);
		} 
		catch(IOException e) {
			System.out.println(e + " - page " + pagePathAndName + page + fileExtension + " doesn't exist!\n");
		}
		
		return;
	}
	
	void stopAnimation() {
		timer.cancel();
		
		return;
	}
	
	private void startCutScene(long time) {
		timer = new Timer();
		TimerTask task = new Animate();
		timer.schedule(task, time);
		
		return;
	}
	
	class Animate extends TimerTask {
		public void run() {
			repaint();
			timer.cancel();
			
			return;
		}
	}
	
	private JPanel createNextButtonPanel() {
		JPanel tempPanel = new JPanel();
		{
			tempPanel.setOpaque(false);
			tempPanel.setVisible(false);
			tempPanel.setLayout(new BorderLayout());
		}
		
		JButton Button = new JButton("=>");
		{	
			Button.setForeground(Color.ORANGE);
			Button.setFont(new Font("Arcade Classic", Font.PLAIN, 100));
			
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
		
		tempPanel.add(Button, BorderLayout.LINE_END);
		
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
			
			SelectSpaceShipScreen SelectSpaceShipScreen = new SelectSpaceShipScreen();
			
			mainGUIwindow.MainPanel.add(SelectSpaceShipScreen);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			SelectSpaceShipScreen.setFocusable(true);
			SelectSpaceShipScreen.requestFocus();
			
			return;
		}
	}

}
