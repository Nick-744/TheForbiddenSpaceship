package GUI;

import java.util.Arrays;
import java.util.List;

import mainPacket.MainClass;

class endCutScene extends CutScene {
	
	private static final long serialVersionUID = -3556157970149995494L;
	
	private final static int pagesNum = 4;
	private final static String pagePathAndName = MainClass.imagesPath + "endCutScene/ComicStory", fileExtension = ".png";
	private final static List<Long> dt = Arrays.asList((long) 5000, (long) 10000, (long) 15000, (long) 15000);
	static boolean seen = false;

	endCutScene() {
		super(pagesNum, pagePathAndName, fileExtension, dt);
		
		return;
	}

}
