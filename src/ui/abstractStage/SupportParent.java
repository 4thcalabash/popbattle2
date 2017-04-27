package ui.abstractStage;

import bllservice.Playerable;
import bllservice.Supportable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;

public abstract class SupportParent extends BorderPane{
	private Supportable supportPlatform;
	private BasicScene main;
	public SupportParent(Supportable supportPlatform,BasicScene main){
		this.supportPlatform=supportPlatform;
		this.main=main;
		this.setBottom(new ImageButton(new Image("Graphics/Button/returnStatic.png"), new Image("Graphics/Button/returnEntered.png"),
				new Image("Graphics/Button/returnPressed.png"), new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						returnStatic();
						
					}
			
		}));
	}
	public void returnStatic(){
		this.main.returnStatic();
	}
}
