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

public class SettingParent extends SupportParent {

	public static final int TOPBOTTOMGAP = 40;
	public static final int LEFTRIGHTGAP = 30;
	public static final int MIDGAP = 20;
	public static final int BUTTONHEIGHT = 120;
	public static final int BUTTONWIDTH = BUTTONHEIGHT*2;
	public static final int BOARDHEIGHT = BUTTONHEIGHT*2+TOPBOTTOMGAP*2+MIDGAP;
	public static final int BOARDWIDTH = BUTTONWIDTH+2*LEFTRIGHTGAP;
	public SettingParent(Supportable support, BasicScene main) {
		super(support, main);
		AnchorPane board = new AnchorPane();
		ImageView background = new ImageView(new Image("Graphics/Static/Setting/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		this.getChildren().add(board);
		String basicPath = "Graphics/Other/SettingGraphics/";
		ImageButton exitGame = new ImageButton(new Image(basicPath + "exitStatic.png"),
				new Image(basicPath + "exitEntered.png"), new Image(basicPath + "exitPressed.png"), new ButtonWorker() {

					@Override
					public void work() {
						// TODO Auto-generated method stub
						main.exitGame();

					}

				});
		exitGame.setFitHeight(BUTTONHEIGHT);
		exitGame.setFitWidth(BUTTONWIDTH);
		exitGame.setX(LEFTRIGHTGAP);
		exitGame.setY(TOPBOTTOMGAP+BUTTONHEIGHT+MIDGAP);
		board.getChildren().add(exitGame);
		ImageButton record = new ImageButton(new Image(basicPath + "recordStatic.png"),
				new Image(basicPath + "recordEntered.png"), new Image(basicPath + "recordPressed.png"),
				new ButtonWorker() {

					@Override
					public void work() {
						// TODO Auto-generated method stub
						main.setStage(new RecordParent(support, main));
					}

				});
		record.setFitHeight(BUTTONHEIGHT);
		record.setFitWidth(BUTTONWIDTH);
		record.setX(LEFTRIGHTGAP);
		record.setY(TOPBOTTOMGAP);
		board.getChildren().add(record);
	}
}
