package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Chooseable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import util.MissionInfo;

public class NormalChooser extends AnchorPane{
	public static final int LENGTH = 100;
	public static final int ROW = 5;
	public static final int LINE = 5;
	public NormalChooser (Chooseable basicPlatform,GameChooser gameChooser){
		this.setId("NormalChooser");
		this.setMaxSize(LENGTH*ROW, LENGTH*LINE);
		this.setMinSize(getMaxWidth(), getMaxHeight());
		final String basicPath = "Graphics/Other/MissionGraphics/Normal/";
		int total = basicPlatform.totalNormalNum();
		int now = basicPlatform.nowNormalNum();
		for (int i=0;i<LINE;i++){
			for (int j=0;j<ROW;j++){
				int temp = i*ROW+j;
				Image StaticImage,EnteredImage,PressedImage;
				if (temp<now){
					StaticImage = new Image (basicPath+"FinishedStatic.png");
					EnteredImage = new Image (basicPath +"FinishedEntered.png");
					PressedImage = new Image (basicPath+"FinishedPressed.png");
					
				}else if (temp<total){
					StaticImage = new Image (basicPath+"UnfinishedStatic.png");
					EnteredImage = new Image (basicPath+"UnfinishedEntered.png");
					PressedImage = new Image (basicPath+"UnfinishedPressed.png");
					
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
				a.setY(i*LENGTH);
				this.getChildren().add(a);
			}
		}
	}
}
