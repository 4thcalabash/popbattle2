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
	public static final int INFOWIDTH = 250;
	public static final int INFOHEIGHT = 200;
	ImageButton [] mission = new ImageButton [8];

	private final static String text0 = "��0��,������һֻ�����ڵ�Ы�ӹ�,�����ǹ��������ԭ���ж������ƺ��ֵֹġ�";
	private final static String text1 = "��1��,Ы�ӹֽ����˺û�������,ʤ���β���������ô���ɡ�";
	private final static String text2 = "��2��,���𾫵ĸ���!�Է���������,����Ҫͬ����������ս��";
	private final static String text3 = "��3��,����!\nЫ�Ӻ������������������˵�������,��Ѱ����!";
	private final static String text4 = "��4��,����Խ��Խǿ����,��ħ�����ˡ���ף����ˡ���";
	private final static String text5 = "��5��,��������!\n��ħû����!����Ҳû����!������Խ��Խ����!";
	private final static String text6 = "��6��,����ʦ����!\n��ʿ,����׼��,��ˮһս��!\n��ȥ��ֹ����ʦ����ı!";
	private final static String [] text = {text0,text1,text2,text3,text4,text5,text6};
	public BattleChooser(Chooseable basicPlatform,GameChooser gameChooser){
		info.setWrapText(true);
		info.setId("info");
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
		mission[0] = new ImageButton(new Image(basicPath+"mission0Static.png"),new Image(basicPath+"mission0Entered.png"),
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
					info.setText(text0);
//					new InfoDialog(gameChooser, basicPath, BOTTOM_INVALID, BOTTOM_INVALID, BOTTOM_INVALID, BOTTOM_INVALID, basicPath);
				});
			}
			
		});
		mission[1] = new ImageButton(new Image(basicPath+"mission1Static.png"),new Image(basicPath+"mission1Entered.png"),
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
					info.setText(text1);
				});
			}
			
		});
		mission[2] = new ImageButton(new Image(basicPath+"mission2Static.png"),new Image(basicPath+"mission2Entered.png"),
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
					info.setText(text2);
				});
			}
			
		});
		mission[3] = new ImageButton(new Image(basicPath+"mission3Static.png"),new Image(basicPath+"mission3Entered.png"),
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
					info.setText(text3);
				});
			}
			
		});
		mission[4] = new ImageButton(new Image(basicPath+"mission4Static.png"),new Image(basicPath+"mission4Entered.png"),
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
					info.setText(text4);
				});
			}
			
		});
		mission[5] = new ImageButton(new Image(basicPath+"mission5Static.png"),new Image(basicPath+"mission5Entered.png"),
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
					info.setText(text5);
				});
			}
			
		});
		mission[6] = new ImageButton(new Image(basicPath+"mission6Static.png"),new Image(basicPath+"mission6Entered.png"),
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
					info.setText(text6);
				});
			}
			
		});
		info.setMaxSize(INFOWIDTH, INFOHEIGHT);
		info.setLayoutX(WIDTH-INFOWIDTH);
		info.setLayoutY(HEIGHT-INFOHEIGHT);
		this.getChildren().add(info);
		this.getChildren().add(mission[0]);
		this.getChildren().add(mission[1]);
		this.getChildren().add(mission[2]);
		this.getChildren().add(mission[3]);
		this.getChildren().add(mission[4]);
		this.getChildren().add(mission[5]);
		this.getChildren().add(mission[6]);
		mission[6].setX(780);
		mission[6].setY(140);
		mission[6].setFitHeight(LENGTH);
		mission[6].setFitWidth(LENGTH);
		mission[5].setX(680);
		mission[5].setY(370);
		mission[5].setFitHeight(LENGTH);
		mission[5].setFitWidth(LENGTH);
		mission[4].setX(380);
		mission[4].setY(220);
		mission[4].setFitHeight(LENGTH);
		mission[4].setFitWidth(LENGTH);
		mission[3].setX(310);
		mission[3].setY(340);
		mission[3].setFitHeight(LENGTH);
		mission[3].setFitWidth(LENGTH);
		mission[2].setX(210);
		mission[2].setY(410);
		mission[2].setFitHeight(LENGTH);
		mission[2].setFitWidth(LENGTH);
		mission[1].setX(200);
		mission[1].setY(250);
		mission[1].setFitHeight(LENGTH);
		mission[1].setFitWidth(LENGTH);
		mission[0].setX(150);
		mission[0].setY(150);
		mission[0].setFitHeight(LENGTH);
		mission[0].setFitWidth(LENGTH);
		ButtonWorker illegalWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				info.setText("����������һ��Ŷ���Ȱ�ǰ��Ĺؿ����˰�\n    qwq");
			}
			
		};
		ButtonWorker illegalButtonWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				System.out.println("illegal click!");
			}
			
		};
		for (int i=basicPlatform.getPlayer1().getNowMission()+1;i<=6;i++){
			mission[i].setEnteredGraphics(mission[i].getStaticGraphics());
			mission[i].setPressedGraphics(mission[i].getEnteredGraphics());
			mission[i].setPlayAudio(false);
			mission[i].setEnteredWorker(illegalWorker);
			mission[i].setMyWorker(illegalButtonWorker);
		}
		for (int i=0;i<basicPlatform.getPlayer1().getNowMission();i++){
			mission[i].setEnteredWorker(new Workerr(i));
		}
	}
	public class Workerr implements ButtonWorker{
		private int index;
		public Workerr(int index){
			this.index=index;
		}
		@Override
		public void work() {
			// TODO Auto-generated method stub
			info.setText("��ϲ�����Ѿ�ͨ���˱��أ�\n"+text[index]);
		}
	}
}
