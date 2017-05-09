package ui.supportRoot;
import bll.platform.*;
import bllservice.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.sceneInterface.BasicScene;
public class AchievementParent extends SupportParent{
	public static final int BOARDHEIGHT=900;
	public static final int BOARDWIDTH = 1200;
	AnchorPane board = new AnchorPane ();
	public AchievementParent(Achievementable achievementPlatform,BasicScene main){
		super(achievementPlatform,main);
		ImageView background = new ImageView (new Image("Graphics/Static/Achievement/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
	}
}
