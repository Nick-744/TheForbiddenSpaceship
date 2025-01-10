package GUI;

import java.awt.Color;

class winScreen extends promptScreen {
  
	private static final long serialVersionUID = 8377029317964462696L;
	static String labelText = "You Won?";

	winScreen(){
		super(labelText, Color.CYAN, ADD_BUTTON);
		
		return;
	}
	
	@Override
	void setButtonActionPerformed() {
		mainGUIwindow.MainPanel.removeAll();
		
		if(endCutScene.seen) {
			SelectSpaceShipScreen SelectSpaceShipScreen = new SelectSpaceShipScreen();
			
			mainGUIwindow.MainPanel.add(SelectSpaceShipScreen);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			SelectSpaceShipScreen.setFocusable(true);
			SelectSpaceShipScreen.requestFocus();
		}
		else {
			endCutScene endCutScene = new endCutScene();
			mainGUIwindow.MainPanel.add(endCutScene);
			mainGUIwindow.cardLayout.next(mainGUIwindow.MainPanel);
			
			endCutScene.setFocusable(true);
			endCutScene.requestFocus();
			
			GUI.endCutScene.seen = true;
			labelText = "You Won!";
		}
		
		return;
	}
	
}
