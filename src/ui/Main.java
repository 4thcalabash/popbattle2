package ui;
import po.*;
import ui.abstractStage.*;
import ui.sceneInterface.*;
import ui.specialParent.PVEParent;
import ui.specialParent.StaticParent;
import bllservice.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import bll.individual.*;
import bll.platform.Battle;
import util.*;
public class Main extends Application implements BasicScene,DramaticScene{
	
	private BattleParent battleParent;//每次使用时都需要重新实例化
	private StaticParent staticParent = new StaticParent(this);//一直沿用一个staticScene，在battle时隐藏，在非battle时显示。
	private Stage stage = new Stage();
	private Scene staticScene = new Scene (staticParent);
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 展示staticScene
		 * 当玩家想要进入battle时，隐藏staticScene（但不销毁），显示battleScene，
		 * 当battle结束，销毁battleScene，恢复staticScene显示
		 * 并检查battlePo查看battle结果
		 */
		staticScene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		stage.setScene(staticScene);
		stage.setTitle("消消乐");
		stage.setFullScreen(true);
		stage.setResizable(false);
		primaryStage = stage;
		primaryStage.show();

	}
	@Override
	public void createNewBattle(MissionInfo missionInfo) {
		// TODO Auto-generated method stub
		//隐藏staticscene，切记不要销毁。
		//负责解析missionPo，并生成PVE、PVP、NORMAL之一的BattleScene
		//要将自身注册到battlescene里去，从而使得可以让battlescene调用battleEnd方法以返回staticscene
		if (missionInfo.getModel()==Battle.PVE){
			Platform.runLater(()->{
				battleParent = new PVEParent(missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
				Scene battleScene = new Scene (battleParent);
				battleScene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
				System.out.println(getClass().getResource("PVE.css"));
				stage.setScene(battleScene);
				stage.setFullScreen(true);
				stage.setResizable(false);
				stage.show();
			});
//			battleParent = new PVEParent(missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
//			Scene battleScene = new Scene (battleParent);
//			battleScene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
//			System.out.println(getClass().getResource("PVE.css"));
//			stage.setScene(battleScene);
//			stage.setFullScreen(true);
//			stage.setResizable(false);
//			stage.show();
		}
	}
	
	@Override
	public void battleEnd() {
		// TODO Auto-generated method stub
		//销毁battlescene
		//恢复显示staticscene
		//显示关卡奖励等
	}
	
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void setStage(Parent root) {
		// TODO Auto-generated method stub
		//此方法完成Static各个子系统界面跳转
//		this.stage=stage;
		Platform.runLater(()->{this.stage.getScene().setRoot(root);});
//		this.staticParent.hide();
	}
	@Override
	public void returnStatic() {
		// TODO Auto-generated method stub
		//此方法从子系统返回Static
		Platform.runLater(()->{this.stage.getScene().setRoot(staticParent);});
		System.out.println("return to Static");
//		this.staticParent.show();
	}

}
