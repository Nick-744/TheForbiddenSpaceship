package laserGun;

import java.awt.Color;
import java.util.LinkedList;

public class LaserGun {
	public final Color laserColor;
	public LinkedList<Laser> laserShotsLinkedList = new LinkedList<Laser>();
	
	public LaserGun(Color laserColor) {
		this.laserColor = laserColor;
		
		return;
	}
	
	public void fire(int x, int y) {
		if(laserShotsLinkedList.size() <= 10) {
			laserShotsLinkedList.add(new Laser(x, y));
		}
		
		return;
	}
	
	public void Reset() {
		this.laserShotsLinkedList.clear();
		
		return;
	}
	
}
