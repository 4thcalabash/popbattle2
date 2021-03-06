package ui;
import ui.abstractStage.*;
import ui.sceneInterface.*;
import ui.specialParent.BonusParent;
import ui.specialParent.NormalParent;
import ui.specialParent.PVEParent;
import ui.specialParent.StaticParent;
import ui.supportRoot.SkillChooser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bll.platform.Battle;
import bll.support.Bonus;
import bllservice.Supportable;
import util.*;
public class Main extends Application implements BasicScene,DramaticScene{
	public static Font myFont;
	public static final Color fontColor = Color.rgb(255, 0, 0);
	private BattleParent battleParent;//每次使用时都需要重新实例化
	private StaticParent staticParent = new StaticParent(this);//一直沿用一个staticScene，在battle时隐藏，在非battle时显示。
	private Stage stage = new Stage();
	private Scene scene = new Scene (staticParent);
	public final static int SCREENWIDTH =(int)Screen.getPrimary().getVisualBounds().getWidth()-10;
	public final static int SCREENHEIGHT = (int)Screen.getPrimary().getVisualBounds().getHeight()-10;
	
	public StaticParent getStaticParent() {
		return staticParent;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		// TODO Auto-generated method stub
		/**
		 * 展示staticScene
		 * .
		 * 当玩家想要进入battle时，隐藏staticScene（但不销毁），显示battleScene，
		 * 当battle结束，销毁battleScene，恢复staticScene显示
		 * 并检查battlePo查看battle结果
		 */
		try {
			myFont = Font.loadFont(getClass().getClassLoader().getResourceAsStream("ui/MyFont.ttf"), 32);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("消消乐");
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreen(true);
//		Screen screen = Screen.getPrimary();
		
//		System.out.println("Height:"+SCREENHEIGHT);
//		System.out.println("Width:"+SCREENWIDTH);
		stage.setResizable(false);
		primaryStage= stage;
		primaryStage.show(); 
		
	}
	public void createDone(){
		Platform.runLater(()->{
		scene.getStylesheets().remove(0);
		scene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
		battleParent = new PVEParent (missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
		stage.getScene().setRoot(battleParent);
		scene.getRoot().setId("scene110"+(int)(8*Math.random()+1));
	});
	}
	MissionInfo missionInfo;
	@Override
	
	public void createNewBattle(MissionInfo missionInfo,AnchorPane father) {
		this.missionInfo=missionInfo;
		// TODO Auto-generated method stub
		//隐藏staticscene，切记不要销毁。
		//负责解析missionPo，并生成PVE、PVP、NORMAL之一的BattleScene
		//要将自身注册到battlescene里去，从而使得可以让battlescene调用battleEnd方法以返回staticscene
		
		if (missionInfo.getModel()==Battle.PVE){
//			Platform.runLater(()->{
//				
			
//			});
			this.setStage(new SkillChooser(this.staticParent.getBasicPlatform().getPlayer1(),this,father));
//			Platform.runLater(()->{
//				scene.getStylesheets().remove(0);
//				scene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
//				battleParent = new PVEParent (missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
//				stage.getScene().setRoot(battleParent);
//				scene.getRoot().setId("scene110"+(int)(8*Math.random()+1));
//			});
			 
		}else if (missionInfo.getModel()==Battle.NORMAL){
			Platform.runLater(()->{
				scene.getStylesheets().remove(0);
				scene.getStylesheets().add(getClass().getResource("Normal.css").toExternalForm());
		
				
				battleParent = new NormalParent (missionInfo.getID(),this);
				stage.getScene().setRoot(battleParent);
			});
		}
	}
	
	
	@Override
	public void battleEnd(Bonus bonus) {
		// TODO Auto-generated method stub-
		//battleScene自行销毁
		//此方法完成从battle返回static
		//显示关卡奖励等
		
		scene.getStylesheets().remove(0);
		scene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		
		if (bonus!=null){
			this.setStage(new BonusParent((Supportable)staticParent.getBasicPlatform(),bonus,(BasicScene)this));
		}else{
//			this.setStage(new BattleFailedParent((BasicScene)this));
			this.setStage(staticParent);
		}
	}
	
	
	
	public static void main(String[] args){
		launch(args);
		
	}
	@Override
	public void setStage(Parent root) {
		// TODO Auto-generated method stub
		//此方法完成Static各个子系统界面跳转
		Platform.runLater(()->{this.stage.getScene().setRoot(root);});
	}
	@Override
	public void returnStatic() {
		// TODO Auto-generated method stub
		//此方法从子系统返回Static
		Platform.runLater(()->{this.stage.getScene().setRoot(staticParent);});
		System.out.println("return to Static");
//		this.staticParent.show();
	}
	@Override
	public void exitGame() {
		// TODO Auto-generated method stub
		stage.close();
	}
	
}
