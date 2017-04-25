package ui;
import po.*;
import ui.abstractStage.*;
import ui.sceneInterface.*;
import ui.specialStage.StaticStage;
import bllservice.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import bll.individual.*;
import util.*;
public class Main extends Application implements BasicScene,DramaticScene{
	
	private BattleStage battleStage;//每次使用时都需要重新实例化
	private StaticStage staticStage = new StaticStage(this);//一直沿用一个staticScene，在battle时隐藏，在非battle时显示。
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 展示staticScene
		 * 当玩家想要进入battle时，隐藏staticScene（但不销毁），显示battleScene，
		 * 当battle结束，销毁battleScene，恢复staticScene显示
		 * 并检查battlePo查看battle结果
		 */
		primaryStage = staticStage;
		primaryStage.show();

	}
	@Override
	public void createNewBattle(MissionInfo missionInfo) {
		// TODO Auto-generated method stub
		//隐藏staticscene，切记不要销毁。
		//负责解析missionPo，并生成PVE、PVP、NORMAL之一的BattleScene
		//要将自身注册到battlescene里去，从而使得可以让battlescene调用battleEnd方法以返回staticscene
		
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

}
