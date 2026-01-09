package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class promptScreen extends JPanel {

	private static final long serialVersionUID = 8377029317964462696L;
	public final static boolean ADD_BUTTON = true;
	
	JLabel Label;

	promptScreen(String str, Color color){
		sameCodeForCons(str, color);
		
		return;
	}
	
	promptScreen(String str, Color color, boolean addButton){
		sameCodeForCons(str, color);
		
		this.add(createReturnButtonPanel(), BorderLayout.SOUTH);

		{	// Key listener for interaction - Press ENTER!

			javax.swing.InputMap inputMap   = this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
			javax.swing.ActionMap actionMap = this.getActionMap();

			inputMap.put(javax.swing.KeyStroke.getKeyStroke("ENTER"), "returnAction");
			
			actionMap.put("returnAction", new javax.swing.AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setButtonActionPerformed();
				}
			});

		}
		
		return;
	}
	
	private void sameCodeForCons(String str, Color color) {
		{
			this.setBackground(Color.BLACK);
			this.setLayout(new BorderLayout());
		}
		
		this.add(createLabel(str, color), BorderLayout.CENTER);
		
		return;
	}
	
	private JLabel createLabel(String str, Color color) {
		Label = new JLabel(str);
		{
			Label.setFont(new Font("Arcade Classic", Font.BOLD, 50));
			Label.setForeground(color);
			
			Label.setHorizontalAlignment(JLabel.CENTER);
			Label.setVerticalAlignment(JLabel.CENTER);
		}
		
		return Label;
	}
	
	JPanel createReturnButtonPanel() {
		JPanel tempPanel = new JPanel(); { tempPanel.setOpaque(false); }
		
		JButton Button = new JButton("Return");
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
			    	Button.setBorderPainted(true);
			        
			        return;
			    }
	
			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	Button.setForeground(Color.ORANGE);
			    	Button.setBorderPainted(false);
			    	
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
			setButtonActionPerformed();
			
			return;
		}
	}
	
	void setButtonActionPerformed() {
		mainGUIwindow.MainPanel.removeAll();
		
		mainGUIwindow.MainPanel.add(new SelectSpaceShipScreen());
		mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
		
		return;
	}

}
