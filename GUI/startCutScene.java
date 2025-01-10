package GUI;

import java.util.Arrays;
import java.util.List;

import mainPacket.MainClass;
import soundPacket.startCutSceneAudio;

class startCutScene extends CutScene {
	
	private static final long serialVersionUID = -3556157970149995494L;
	
	private final static int pagesNum = 6;
	private final static String pagePathAndName = MainClass.imagesPath + "startCutScene/ComicStory", fileExtension = ".png";
	private final static List<Long> dt = Arrays.asList((long) 6500, (long) 10000, (long) 15000, (long) 19000, (long) 21000, (long) 21000);

	startCutScene() {
		super(pagesNum, pagePathAndName, fileExtension, dt);
		new startCutSceneAudio();
		
		return;
	}

}
