package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Chooseable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;
import util.MissionInfo;

public class BattleChooser extends AnchorPane{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	public static final int LENGTH = 50;
	public BattleChooser(Chooseable basicPlatform,GameChooser gameChooser){
//		AnchorPane this = new AnchorPane();
		this.setId("BattleChooser");
		this.setMaxSize(WIDTH,HEIGHT);
		this.setMinSize(getMaxWidth(), getMaxHeight());
		final String basicPath = "Graphics/Other/MissionGraphics/Battle/";
		System.out.println(new Image(basicPath+"mission0Static.png").impl_getUrl());
		ImageButton mission0 = new ImageButton(new Image(basicPath+"mission0Static.png"),new Image(basicPath+"mission0Entered.png"),
				new Image(basicPath+"mission0Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo= new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(0);
						gameChooser.createNewBattle(missionInfo);
					}
			
		});
		ImageButton mission1 = new ImageButton(new Image(basicPath+"mission1Static.png"),new Image(basicPath+"mission1Entered.png"),
				new Image(basicPath+"mission1Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(1);
						gameChooser.createNewBattle(missionInfo);
					}
				
		});
		
		this.getChildren().add(mission0);
		this.getChildren().add(mission1);
		mission1.setX(200);
		mission1.setY(250);
		mission1.setFitHeight(LENGTH);
		mission1.setFitWidth(LENGTH);
		
		mission0.setX(10);
		mission0.setY(20);
		mission0.setFitHeight(LENGTH);
		mission0.setFitWidth(LENGTH);
	}
}
