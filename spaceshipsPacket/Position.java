package spaceshipsPacket;

import mainPacket.MainClass;

public class Position {
	
	private int stageYheight = MainClass.stageYheight;
	private int stageXwidth = MainClass.stageXwidth;
	
	private int CoordX;
	private int CoordY;

	Position(int spaceShipYheight, int spaceShipXwidth) {
		stageYheight -= spaceShipYheight;
		stageXwidth -= spaceShipXwidth;
		
		this.CoordX = (stageXwidth / 2);
		this.CoordY = stageYheight;
		
		return;
	}
	
	public void getCoords() {
		System.out.println("(x, y) == " + "(" + CoordX + ", " + CoordY + ")\n");
		
		return;
	}
	
	public int getCoordX() { return CoordX; }
	
	public int getCoordY() { return CoordY; }
	
	void setCoords(int x, int y) {
		boolean invalidX = (0 <= (this.CoordX + x) && (this.CoordX + x) <= stageXwidth);
		boolean invalidY = (0 <= (this.CoordY + y) && (this.CoordY + y) <= stageYheight);
		
		if(invalidX && invalidY) {
			this.CoordX = x;
			this.CoordY = y;
		}
		
		return;
	}
	
	void setCoordX(int x) {
		boolean invalidX = (0 <= (this.CoordX + x) && (this.CoordX + x) <= stageXwidth);
		
		if(invalidX) {
			this.CoordX += x;
		}
		
		return;
	}
	
	void setCoordY(int y) {
		boolean invalidY = (0 <= (this.CoordY + y) && (this.CoordY + y) <= stageYheight);
		
		if(invalidY) {
			this.CoordY += y;
		}
		
		return;
	}
	
	public static boolean isInStage(int x, int y) {
		boolean In = false;
		
		boolean xInCondition = 0 < x && x  < MainClass.stageXwidth;
		boolean yInCondition = 0 < y && y  < MainClass.stageYheight;
		
		if(xInCondition && yInCondition) {
			In = true;
		}
		
		return In;
	}

}
