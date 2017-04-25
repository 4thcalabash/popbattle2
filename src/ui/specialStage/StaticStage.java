package ui.specialStage;
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
import ui.supportStage.*;
public class StaticStage extends Stage{
	//��Ҫ����û���ѯ��ϵͳ��̬��Ϣ�Ĺ���
	private BasicScene main;
	private BasicPlatform basicPlatform;//���о�̬ϵͳ����Ϣ�����ڴˣ���Ҫ��ʾ����Ϣͨ��������ȡ
	public StaticStage (Main main){
		this.main=main;
		basicPlatform = new Static ();//ʵ����Staticʱ�����Ѿ������Ĭ�ϴ浵�ļ���
		/**��Ҫ��ɽ������ʾ
		*������ɼ��������統���������ʱ�������������stage��
		*���磬�û���������񡱣�ֻ��Ҫnew TaskStage(basicPlatform).show();
		*ÿ��supportPlatform������Լ�����Ҫ
		*ͨ��basicPlatform����ȡ��Ҫ����Ϣ
		*���û����supportStage�ϵĹرհ�ťʱ��supportStage����������
		**/
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		VBox vbox = addVBox();
		border.setBottom(hbox);
		border.setCenter(vbox);
	
		vbox.setAlignment(Pos.BOTTOM_CENTER); 
		hbox.setAlignment(Pos.TOP_CENTER);
		
		hbox.setMaxWidth(hbox.getPrefWidth());
		hbox.setMaxHeight(hbox.getPrefHeight());
		Scene staticScene = new Scene(border);
		staticScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		System.out.println(getClass().getResource(""));
		this.setScene(staticScene);
		this.setTitle("������");
		this.setFullScreen(true);
		this.setResizable(false);
		this.show();
	}
	private HBox addHBox() {
		// TODO Auto-generated method stub
		HBox hbox = new HBox();
		
		hbox.setPrefHeight(210);
		hbox.setPadding(new Insets(45,0,0,0));
		hbox.setSpacing(10);
		//hbox.setMaxSize(hbox.getPrefWidth(), hbox.getPrefHeight());
		
		hbox.setId("ButtonBox");
		ImageButton playerButton = new ImageButton(new Image ("Graphics/Button/playerStatic.png"),new Image("Graphics/Button/playerEntered.png"),new Image("Graphics/Button/playerPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				new PlayerStage((Playerable)basicPlatform).show();
			}
			
		});
		ImageButton skillButton = new ImageButton(new Image ("Graphics/Button/skillStatic.png"),new Image("Graphics/Button/skillEntered.png"),new Image("Graphics/Button/skillPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				new SkillStage((Skillable)basicPlatform).show();
			}
			
		});
		ImageButton bagButton = new ImageButton(new Image ("Graphics/Button/bagStatic.png"),new Image("Graphics/Button/bagEntered.png"),new Image("Graphics/Button/bagPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				new BagStage((Equipable)basicPlatform).show();
			}
			
		});
		ImageButton achievementButton = new ImageButton(new Image ("Graphics/Button/achievementStatic.png"),new Image("Graphics/Button/achievementEntered.png"),new Image("Graphics/Button/achievementPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				new AchievementStage((Achievementable)basicPlatform).show();
			}
			
		});
		ImageButton shopButton = new ImageButton(new Image ("Graphics/Button/shopStatic.png"),new Image("Graphics/Button/shopEntered.png"),new Image("Graphics/Button/shopPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				new ShopStage((Shopable)basicPlatform).show();
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
				System.out.println("Graphics/Battle Chooser");
				
			}
			
		});
		ImageButton systemSetting = new ImageButton (new Image ("Graphics/Button/systemSettingStatic.png"),new Image ("Graphics/Button/systemSettingEntered.png"),new Image("Graphics/Button/systemSettingPressed.png"),new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				System.out.println("Setting Work!");
			}
			
		});
        vbox.getChildren().addAll(startBattleButton,systemSetting);
        
        return vbox;
    }
	//һ��battle����֮���bonus��Main����
	//���û�ѡ��ĳһ��ģʽ ��ʼbattleʱ������basicPlatform��createNewBattle(MissionPo)����Ҫ���û�ѡ���ģʽ��������Ϣ�洢��MissionPo�м��Դ��ݡ�

}
