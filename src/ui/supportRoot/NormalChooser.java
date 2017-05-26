package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Chooseable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import util.MissionInfo;

public class NormalChooser extends AnchorPane{
	public static final int LENGTH = 115;
	public static final int ROW = 5;
	public static final int LINE = 3;
	public static final int TOPGAP= 45;
	public static final int LEFTGAP = 70;
	public static final int BOARDWIDTH= ROW*LENGTH+2*LEFTGAP;
	public static final int BOARDHEIGHT  = LINE*LENGTH-LENGTH/2+2*TOPGAP;
	public NormalChooser (Chooseable basicPlatform,GameChooser gameChooser){
		AnchorPane board = new AnchorPane ();
		ImageView background = new ImageView (new Image ("Graphics/Static/NormalChooser/background.png"));
		background.setFitWidth(BOARDWIDTH);
		background.setFitHeight(BOARDHEIGHT);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		final String basicPath = "Graphics/Other/MissionGraphics/Normal/";
		int total = basicPlatform.totalNormalNum();
		int now = basicPlatform.nowNormalNum();
		for (int i=0;i<LINE;i++){
			for (int j=0;j<ROW;j++){
				int temp = i*ROW+j;
				Image StaticImage,EnteredImage,PressedImage;
				if (temp<=now+1){
					StaticImage = new Image (basicPath+temp+"Static.png");
					EnteredImage = new Image (basicPath +temp+"Entered.png");
					PressedImage = new Image (basicPath+temp+"Pressed.png");
					
				}else if (temp<total){
					StaticImage = new Image (basicPath+temp+"Static.png");
					EnteredImage = new Image (basicPath+temp+"Entered.png");
					PressedImage = new Image (basicPath+temp+"Pressed.png");
					
				}else{
					StaticImage = EnteredImage=PressedImage=null;
				}
				ImageButton a = new ImageButton (StaticImage,EnteredImage,PressedImage,new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo ();
						missionInfo.setModel(Battle.NORMAL);
						missionInfo.setID(temp);
						gameChooser.createNewBattle(missionInfo);
					}
					
				});
				a.setFitHeight(LENGTH);
				a.setFitWidth(LENGTH);
				a.setX(j*LENGTH+LEFTGAP);
				a.setY(i*LENGTH+TOPGAP);
				if (i==1){
					a.setY(BOARDHEIGHT-LENGTH-TOPGAP);
				}
				board.getChildren().add(a);
			}
		}
	AnchorPane word = new AnchorPane ();
	word.setLayoutX(board.getLayoutX()+BOARDWIDTH);
	word.setLayoutY(board.getLayoutY()+BOARDHEIGHT/8);
	ImageView background2 = new ImageView (new Image("Graphics/Other/MissionGraphics/Normal/textBackground.png"));
	background2.setFitHeight(BOARDHEIGHT-BOARDHEIGHT/4);
	background2.setFitWidth(BOARDHEIGHT*2/3);
	background2.setX(0);
	background2.setY(0);
	word.getChildren().add(background2);
	Label text = new Label("在挑战模式中，你将挑战最强AI，先达到目标分数者获胜，虽然AI很强，但还是可以依靠套路取胜");
	text.setWrapText(true);
	text.setMaxWidth(BOARDHEIGHT*2/3);
	text.setLayoutX(0);
	text.setLayoutY(0);
	word.getChildren().add(text);
	this.getChildren().add(word);
	}
}
