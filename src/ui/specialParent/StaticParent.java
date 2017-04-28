package ui.specialParent;
import bll.platform.Static;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
//import bllservice.Shopable;
import bllservice.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.Main;
import ui.sceneInterface.BasicScene;
import ui.supportRoot.*;
public class StaticParent extends BorderPane{
	//��Ҫ����û���ѯ��ϵͳ��̬��Ϣ�Ĺ���
	private BasicScene main;
	private BasicPlatform basicPlatform;//���о�̬ϵͳ����Ϣ�����ڴˣ���Ҫ��ʾ����Ϣͨ��������ȡ
	public StaticParent (Main main){
		this.main=main;
		basicPlatform = new Static (0);//ʵ����Staticʱ�����Ѿ������Ĭ�ϴ浵�ļ���
		/**��Ҫ��ɽ������ʾ
		*������ɼ��������統���������ʱ�������������stage��
		*���磬�û���������񡱣�ֻ��Ҫnew TaskStage(basicPlatform).show();
		*ÿ��supportPlatform������Լ�����Ҫ
		*ͨ��basicPlatform����ȡ��Ҫ����Ϣ
		*���û����supportStage�ϵĹرհ�ťʱ��supportStage����������
		**/
		HBox hbox = addHBox();
		VBox vbox = addVBox();
		this.setBottom(hbox);
		this.setCenter(vbox);
		vbox.setAlignment(Pos.BOTTOM_CENTER); 
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setMaxWidth(hbox.getPrefWidth());
		hbox.setMaxHeight(hbox.getPrefHeight());
//		Scene staticScene = new Scene(border);
//		staticScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		System.out.println(getClass().getResource(""));
	}
	private HBox addHBox() {
		// TODO Auto-generated method stub
		HBox hbox = new HBox();
		
		hbox.setPrefHeight(210);
		hbox.setPadding(new Insets(45,0,0,0));
		hbox.setSpacing(10);
		
		hbox.setId("ButtonBox");
		ImageButton playerButton = new ImageButton(new Image ("Graphics/Button/playerStatic.png"),new Image("Graphics/Button/playerEntered.png"),new Image("Graphics/Button/playerPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new PlayerParent((Playerable)basicPlatform,main));;
				System.out.println("change to PlayerStage");
			}
			
		});
		ImageButton skillButton = new ImageButton(new Image ("Graphics/Button/skillStatic.png"),new Image("Graphics/Button/skillEntered.png"),new Image("Graphics/Button/skillPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new SkillParent((Skillable)basicPlatform,main));
			}
			
		});
		ImageButton bagButton = new ImageButton(new Image ("Graphics/Button/bagStatic.png"),new Image("Graphics/Button/bagEntered.png"),new Image("Graphics/Button/bagPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new BagParent((Equipable)basicPlatform,main));
			}
			
		});
		ImageButton achievementButton = new ImageButton(new Image ("Graphics/Button/achievementStatic.png"),new Image("Graphics/Button/achievementEntered.png"),new Image("Graphics/Button/achievementPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new AchievementParent((Achievementable)basicPlatform,main));
			}
			
		});
		ImageButton shopButton = new ImageButton(new Image ("Graphics/Button/shopStatic.png"),new Image("Graphics/Button/shopEntered.png"),new Image("Graphics/Button/shopPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new ShopParent((Shopable)basicPlatform,main));
			}
			
		});
        hbox.getChildren().addAll(playerButton,skillButton,bagButton,achievementButton,shopButton);
       // hbox.setMaxWidth(playerButton.getFitWidth()+skillButton.getFitWidth()+bagButton.getFitWidth()+shopButton.getFitWidth()+achievementButton.getFitWidth());
        return hbox;
        
    
	}
	private VBox addVBox() {
        
        VBox vbox = new VBox();
        vbox.setMaxHeight(200);
        vbox.setPadding(new Insets(150,0,0,0)); // Set all sides to 10
        vbox.setSpacing(80);              // Gap between nodes
		ImageButton startBattleButton = new ImageButton(new Image ("Graphics/Button/startBattleStatic.png"),new Image("Graphics/Button/startBattleEntered.png"),new Image("Graphics/Button/startBattlePressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new BattleChooser((Battleable)basicPlatform,main));
//				System.out.println("Graphics/Battle Chooser");
				
			}
			
		});
		ImageButton systemSetting = new ImageButton (new Image ("Graphics/Button/systemSettingStatic.png"),new Image ("Graphics/Button/systemSettingEntered.png"),new Image("Graphics/Button/systemSettingPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				main.setStage(new SettingParent((Supportable)basicPlatform,main));
//				System.out.println("Setting Work!");
			}
			
		});
        vbox.getChildren().addAll(startBattleButton,systemSetting);
        
        return vbox;
    }
	//һ��battle����֮���bonus��Main����
	//���û�ѡ��ĳһ��ģʽ ��ʼbattleʱ������basicPlatform��createNewBattle(MissionPo)����Ҫ���û�ѡ���ģʽ��������Ϣ�洢��MissionPo�м��Դ��ݡ�
	public BasicPlatform getBasicPlatform() {
		return basicPlatform;
	}

}
