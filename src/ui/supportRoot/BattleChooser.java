package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Battleable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;
import util.MissionInfo;

public class BattleChooser extends SupportParent{
	public BattleChooser(Battleable basicPlatform,BasicScene main){
		super(basicPlatform,main);
		AnchorPane map = new AnchorPane();
		map.setId("BattleChooserMap");
//		map.setMaxSize(maxWidth, maxHeight);
		ImageButton mission0 = new ImageButton(new Image("Graphics/Other/mission0Static.png"),new Image("Graphics/Other/mission0Entered.png"),
				new Image("Graphics/Other/mission0Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo= new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(0);
						main.createNewBattle(missionInfo);
					}
			
		});
		map.getChildren().add(mission0);
		mission0.setX(10);
		mission0.setY(20);
		map.setMaxHeight(800);
		map.setMaxWidth(1100);
		this.getStylesheets().add(getClass().getResource("support.css").toExternalForm());
		this.setCenter(map);
	}
}
