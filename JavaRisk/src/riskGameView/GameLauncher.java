package riskGameView;

import java.io.File;

public class GameLauncher {

	public static void main(String[] args) {

		GameLauncher gameLauncher = new GameLauncher();
		gameLauncher.editMap();
		
	}

	public void editMap() {
			MapRender mapRender = new MapRender();
			File selectedFile = mapRender.chooseFile();
			if(selectedFile!=null) {
				mapRender.readFile();
			}
	}

	

}
