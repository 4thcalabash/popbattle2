package ui.supportRoot;

import bllservice.Supportable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;

public class SettingParent extends SupportParent{
	public static final int BOARDHEIGHT = 600;
	public static final int BOARDWIDTH = 500;
	public static final int TOPBOTTOMGAP = 100;
	public static final int LEFTRIGHTGAP = 50;
	public static final int MIDGAP = 20;
	public static final int ITEMLENGTH = 200;
	public SettingParent (Supportable support,BasicScene main){
		super(support,main);
		AnchorPane board = new AnchorPane ();
		ImageView background = new ImageView(new Image("Graphics/Static/Setting/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
//		board.setId("SettingBox");
//		board.setMaxHeight(BOARDHEIGHT);
//		board.setMaxWidth(BOARDWIDTH);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		String basicPath = "Graphics/Other/SettingGraphics/";
		ImageButton exitGame = new ImageButton(new Image(basicPath+"exitStatic.png"),
				new Image(basicPath+"exitEntered.png"),new Image(basicPath+"exitPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						main.exitGame();
					}
			
		});
		exitGame.setFitHeight(ITEMLENGTH);
		exitGame.setFitWidth(ITEMLENGTH);
		exitGame.setX(BOARDWIDTH/2-ITEMLENGTH/2);
		exitGame.setY(BOARDHEIGHT/2-ITEMLENGTH/2);
		board.getChildren().add(exitGame);
	}
}
