package ui.supportRoot;

import bll.platform.Battle;
import bllservice.Chooseable;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.InfoDialog;
import util.MissionInfo;

public class BattleChooser extends AnchorPane{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 650;
	public static final int LENGTH = 80;
	private Label info = new Label();
	public static final int INFOWIDTH = 200;
	public static final int INFOHEIGHT = 200;
	public BattleChooser(Chooseable basicPlatform,GameChooser gameChooser){
		ImageView background = new ImageView (new Image("Graphics/Static/BattleChooser/background.jpg"));
		background.setFitHeight(HEIGHT);
		background.setFitWidth(WIDTH);
		background.setX(0);
		background.setY(0);
		this.getChildren().add(background);
//		this.setMaxSize(WIDTH,HEIGHT);
//		this.setMinSize(getMaxWidth(), getMaxHeight());
		this.setLayoutX(Main.SCREENWIDTH/2-WIDTH/2);
		this.setLayoutY(Main.SCREENHEIGHT/2-HEIGHT/2);
		final String basicPath = "Graphics/Other/MissionGraphics/Battle/";
		ImageButton mission0 = new ImageButton(new Image(basicPath+"mission0Static.png"),new Image(basicPath+"mission0Entered.png"),
				new Image(basicPath+"mission0Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo= new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(0);
						gameChooser.createNewBattle(missionInfo);
					}
			
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第0关,对手是一只筑基期的蝎子怪，可能是功力不足的原因，行动起来似乎怪怪的。");
//					new InfoDialog(gameChooser, basicPath, BOTTOM_INVALID, BOTTOM_INVALID, BOTTOM_INVALID, BOTTOM_INVALID, basicPath);
				});
			}
			
		});
		ImageButton mission1 = new ImageButton(new Image(basicPath+"mission1Static.png"),new Image(basicPath+"mission1Entered.png"),
				new Image(basicPath+"mission1Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(1);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第1关");
				});
			}
			
		});
		ImageButton mission2 = new ImageButton(new Image(basicPath+"mission2Static.png"),new Image(basicPath+"mission2Entered.png"),
				new Image(basicPath+"mission2Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(2);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第2关");
				});
			}
			
		});
		ImageButton mission3 = new ImageButton(new Image(basicPath+"mission3Static.png"),new Image(basicPath+"mission3Entered.png"),
				new Image(basicPath+"mission3Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(3);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第3关");
				});
			}
			
		});
		ImageButton mission4 = new ImageButton(new Image(basicPath+"mission4Static.png"),new Image(basicPath+"mission4Entered.png"),
				new Image(basicPath+"mission4Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(4);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第4关");
				});
			}
			
		});
		ImageButton mission5 = new ImageButton(new Image(basicPath+"mission5Static.png"),new Image(basicPath+"mission5Entered.png"),
				new Image(basicPath+"mission5Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(5);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第5关");
				});
			}
			
		});
		ImageButton mission6 = new ImageButton(new Image(basicPath+"mission6Static.png"),new Image(basicPath+"mission6Entered.png"),
				new Image(basicPath+"mission6Pressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						MissionInfo missionInfo = new MissionInfo();
						missionInfo.setModel(Battle.PVE);
						missionInfo.setID(6);
						gameChooser.createNewBattle(missionInfo);
					}
				
		},new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				Platform.runLater(()->{
					info.setText("第6关");
				});
			}
			
		});
		info.setMaxSize(INFOWIDTH, INFOHEIGHT);
		info.setLayoutX(WIDTH-INFOWIDTH);
		info.setLayoutY(HEIGHT-INFOHEIGHT);
		this.getChildren().add(info);
		this.getChildren().add(mission0);
		this.getChildren().add(mission1);
		this.getChildren().add(mission2);
		this.getChildren().add(mission3);
		this.getChildren().add(mission4);
		this.getChildren().add(mission5);
		this.getChildren().add(mission6);
		mission6.setX(780);
		mission6.setY(140);
		mission6.setFitHeight(LENGTH);
		mission6.setFitWidth(LENGTH);
		mission5.setX(680);
		mission5.setY(370);
		mission5.setFitHeight(LENGTH);
		mission5.setFitWidth(LENGTH);
		mission4.setX(380);
		mission4.setY(220);
		mission4.setFitHeight(LENGTH);
		mission4.setFitWidth(LENGTH);
		mission3.setX(310);
		mission3.setY(340);
		mission3.setFitHeight(LENGTH);
		mission3.setFitWidth(LENGTH);
		mission2.setX(210);
		mission2.setY(410);
		mission2.setFitHeight(LENGTH);
		mission2.setFitWidth(LENGTH);
		mission1.setX(200);
		mission1.setY(250);
		mission1.setFitHeight(LENGTH);
		mission1.setFitWidth(LENGTH);
		mission0.setX(150);
		mission0.setY(150);
		mission0.setFitHeight(LENGTH);
		mission0.setFitWidth(LENGTH);
	}
}
