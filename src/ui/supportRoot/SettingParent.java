package ui.supportRoot;

import bllservice.Supportable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;

public class SettingParent extends SupportParent{
	public SettingParent (Supportable support,BasicScene main){
		super(support,main);
		this.getStylesheets().add(getClass().getResource("support.css").toExternalForm());
		HBox hbox = new HBox ();
		hbox.setId("SettingBox");
		hbox.setMaxHeight(600);
		hbox.setMaxWidth(400);
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		this.setCenter(hbox);
		String basicPath = "Graphics/Other/SettingGraphics/";
		ImageButton exitGame = new ImageButton(new Image(basicPath+"exitStatic.png"),
				new Image(basicPath+"exitEntered.png"),new Image(basicPath+"exitPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						main.exitGame();
					}
			
		});
		hbox.getChildren().add(exitGame);
	}
}
