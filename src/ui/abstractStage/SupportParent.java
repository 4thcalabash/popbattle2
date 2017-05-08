package ui.abstractStage;

import bllservice.Playerable;
import bllservice.Supportable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import ui.Main;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;

public abstract class SupportParent extends AnchorPane {
	private Supportable supportPlatform;
	protected BasicScene main;
	public static final int BACKLENGTH = 100;
	public SupportParent(Supportable supportPlatform, BasicScene main) {
		this.supportPlatform = supportPlatform;
		this.main = main;
		ImageButton backButton = new ImageButton(new Image("Graphics/Button/returnStatic.png"),
				new Image("Graphics/Button/returnEntered.png"), new Image("Graphics/Button/returnPressed.png"),
				new ButtonWorker() {

					@Override
					public void work() {
						// TODO Auto-generated method stub
						returnStatic();

					}

				});
		backButton.setFitHeight(BACKLENGTH);
		backButton.setFitWidth(BACKLENGTH);
		backButton.setX(0);
		backButton.setY(Main.SCREENHEIGHT-BACKLENGTH);
		this.getChildren().add(backButton);
	}

	public void returnStatic() {
		this.main.returnStatic();
	}
}
