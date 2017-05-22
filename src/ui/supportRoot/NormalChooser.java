package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Chooseable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import util.MissionInfo;

public class NormalChooser extends AnchorPane{
	public static final int LENGTH = 120;
	public static final int ROW = 5;
	public static final int LINE = 3;
	public static final int TOPGAP= 12;
	public static final int BOARDWIDTH= ROW*LENGTH;
	public static final int BOARDHEIGHT  = LINE*LENGTH+2*TOPGAP;
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
				a.setX(j*LENGTH);
				a.setY(i*LENGTH+TOPGAP);
				if (i==1){
					a.setY(2*LENGTH+TOPGAP);
				}
				board.getChildren().add(a);
			}
		}
	}
}
