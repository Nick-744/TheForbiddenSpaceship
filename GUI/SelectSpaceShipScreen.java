package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import mainPacket.MainClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import spaceshipsPacket.SpaceShip;

import soundPacket.spaceShipSelectionAudio;

class SelectSpaceShipScreen extends JPanel {

	private static final long serialVersionUID = -8859789748573653103L;
	
	private int frame = 0;
	private final int framesNum = 9 - 1;
	private final String imagesPathAndName = MainClass.imagesPath + "SelectShip_bg/selectShip_bg-", fileExtension = ".jpg";
	
	public static SpaceShip player;
	
	private Timer timer;

	// Keyboard Navigation Helpers
	private int selectedIndex = 0;
	private java.util.List<JLabel> shipLabels   = new java.util.ArrayList<>();
	private java.util.List<JButton> shipButtons = new java.util.ArrayList<>();
	
	SelectSpaceShipScreen() {
		{
			this.setFocusable(true);
			this.setLayout(new BorderLayout(10, 200));
		}
		
		JPanel SpaceShipsGrid = createSpaceShipGridPanel(); // IMPORTANT - Must run first to fill the lists
		JPanel selectLabel = createSelectLabelPanel();
		setFPSbgImage(100);
		
		{
			this.add(selectLabel, BorderLayout.NORTH);
			this.add(SpaceShipsGrid, BorderLayout.CENTER);
		}

		setupKeyBindings();      // Initialize keyboard controls
		updateVisualHighlight(); // Initial highlight for first ship
		
		return;
	}

	private void setupKeyBindings() {
		InputMap inputMap   = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = this.getActionMap();

		// Map Keys
		inputMap.put(KeyStroke.getKeyStroke("LEFT"),   "moveLeft");
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"),  "moveRight");
		inputMap.put(KeyStroke.getKeyStroke("ENTER"),  "selectShip");
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "exitGame");

		actionMap.put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shipLabels.isEmpty()) return;

				selectedIndex = (selectedIndex - 1 + shipLabels.size()) % shipLabels.size();
				updateVisualHighlight();
			}
		});

		actionMap.put("moveRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shipLabels.isEmpty()) return;

				selectedIndex = (selectedIndex + 1) % shipLabels.size();
				updateVisualHighlight();
			}
		});

		actionMap.put("selectShip", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shipLabels.isEmpty()) return;

				String shipName = shipLabels.get(selectedIndex).getText();
				new SelectedButtonHandler(shipName).actionPerformed(null);
			}
		});

		actionMap.put("exitGame", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void updateVisualHighlight() {
		for (int i = 0; i < shipLabels.size(); i++) {
			if (i == selectedIndex) {
				shipLabels.get(i).setForeground(Color.GREEN); // Highlight
				shipButtons.get(i).setBorderPainted(true);
			}
			else {
				shipLabels.get(i).setForeground(Color.RED);   // Normal
				shipButtons.get(i).setBorderPainted(false);
			}
		}
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
	
	private JPanel createSpaceShipGridPanel() {
		LinkedList<SpaceShip> spaceShipsList = MainClass.spaceShipsList;
		
		JPanel SpaceShipsGrid = new JPanel();
		{
			SpaceShipsGrid.setLayout(new FlowLayout());
			SpaceShipsGrid.setOpaque(false);
		}
			
		for(int i = 0; i < spaceShipsList.size(); i++) {
			SpaceShip tempSS = spaceShipsList.get(i);
			
			JPanel tempPanel = new JPanel();
			{
				tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
				tempPanel.setOpaque(false);
			}   /* tempPanel */
			
			JLabel tempLabel = new JLabel(tempSS.name);
			{
				tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				tempLabel.setFont(new Font("Arcade Classic", Font.BOLD, 30));
				tempLabel.setForeground(Color.RED);
			}   /* tempLabel */ 

			shipLabels.add(tempLabel);
			
			JButton tempButton = new JButton(tempSS.TextureIcon);
			{	
				tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				tempButton.setOpaque(false);
				tempButton.setContentAreaFilled(false);
				tempButton.setBorderPainted(false);
				
				tempButton.addActionListener(new SelectedButtonHandler(tempSS.name));
				
				// Sync mouse hover with keyboard index
				final int index = i;
				tempButton.addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	selectedIndex = index;
				    	updateVisualHighlight();
				        
				        return;
				    }
				});
			}    /* tempButton */

			shipButtons.add(tempButton);
			
			{
				tempPanel.add(tempLabel);
				tempPanel.add(tempButton);
				
				SpaceShipsGrid.add(tempPanel);
			}
		}
		
		return SpaceShipsGrid;
	}
	
	private JPanel createSelectLabelPanel() {
		JPanel selectLabelPanel = new JPanel();
		{
			selectLabelPanel.setOpaque(false);
		}
		
		JLabel selectLabel = new JLabel("Choose your spaceship:");
		{
			selectLabel.setFont(new Font("Arcade Classic", Font.BOLD, 50));
			selectLabel.setForeground(Color.ORANGE);
		}
		
		selectLabelPanel.add(selectLabel);
		
		return selectLabelPanel;
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
			
			LinkedList<SpaceShip> spaceShipsList = SpaceShip.createAllPlayableSpaceShips();
			
			for(int i = 0; i < spaceShipsList.size(); i++) {
				SpaceShip tempSS = spaceShipsList.get(i);
				if(name.equalsIgnoreCase(tempSS.name)) {
					player = tempSS;
				}
			}
			
			Stage1 Stage1 = new Stage1();
			
			mainGUIwindow.MainPanel.add(Stage1);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			Stage1.startTimerForNextScene();
			Stage1.setFocusable(true);
			Stage1.requestFocus();
			
			timer.cancel();
			
			return;
		}
	}

}
