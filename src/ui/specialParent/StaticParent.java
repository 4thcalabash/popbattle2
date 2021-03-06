package ui.specialParent;
import bll.platform.Static;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
//import bllservice.Shopable;
import bllservice.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.sceneInterface.BasicScene;
import ui.supportRoot.*;
public class StaticParent extends BorderPane{
	//需要完成用户查询各系统静态信息的功能
	private BasicScene main;
	private BasicPlatform basicPlatform;//所有静态系统和信息存在于此，需要显示的信息通过它来获取

	public StaticParent (BasicScene main){
		this.main=main;
		basicPlatform = new Static (0);//实例化Static时，便已经完成了默认存档的加载
		/**需要完成界面的显示
		*添加若干监听，比如当点击“任务”时，弹出任务面板stage。
		*例如，用户点击“任务”，只需要new TaskStage(basicPlatform).show();
		*每个supportPlatform会根据自己的需要
		*通过basicPlatform来获取需要的信息
		*当用户点击supportStage上的关闭按钮时，supportStage会自行销毁
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
//				main.setStage(new BonusParent((Supportable)basicPlatform,Bonus.getBonusByID(0),main));
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
				main.setStage(new GameChooser((Chooseable)basicPlatform,main,"a"));
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
	//一次battle结束之后的bonus由Main负责。
	//在用户选定某一个模式 开始battle时，调用basicPlatform的createNewBattle(MissionPo)，需要将用户选择的模式的所有信息存储在MissionPo中加以传递。
	public BasicPlatform getBasicPlatform() {
		return basicPlatform;
	}

}
