package ui.supportRoot;

import bllservice.Chooseable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;
import util.MissionInfo;

public class GameChooser extends SupportParent {
	private final GameChooser myself = this;
	public static final int BUTTONLENGTH=200;
	VBox vbox = new VBox ();
	public GameChooser(Chooseable basicPlatform,BasicScene main){
//		VBox vbox = new VBox ();
		super(basicPlatform,main);
		this.getStylesheets().add(getClass().getResource("support.css").toExternalForm());
		vbox.setId("GameChooserMap");
		final String basicPath = "Graphics/Other/MissionGraphics/";
		ImageButton PVE= new ImageButton(new Image (basicPath+"PVEStatic.png"),new Image(basicPath+"PVEEntered.png"),
				new Image(basicPath+"PVEPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						setCenter(new BattleChooser(basicPlatform,myself));
					}
			
		});
		PVE.setFitHeight(BUTTONLENGTH);
		PVE.setFitWidth(BUTTONLENGTH);
		
		ImageButton Normal = new ImageButton (new Image(basicPath+"normalStatic.png"),new Image(basicPath+"normalEntered.png"),
				new Image(basicPath+"normalPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						setCenter(new NormalChooser(basicPlatform,myself));
					}
			
		});
		Normal.setFitHeight(BUTTONLENGTH);
		Normal.setFitWidth(BUTTONLENGTH);
		vbox.getChildren().addAll(PVE,Normal);
		this.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);

	}
	public void back(){
		setCenter(vbox);
	}
	public void createNewBattle(MissionInfo missionInfo){
		main.createNewBattle(missionInfo);
	}
}
