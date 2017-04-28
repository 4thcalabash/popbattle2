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
	
	private BattleParent battleParent;//ÿ��ʹ��ʱ����Ҫ����ʵ����
	private StaticParent staticParent = new StaticParent(this);//һֱ����һ��staticScene����battleʱ���أ��ڷ�battleʱ��ʾ��
	private Stage stage = new Stage();
	private Scene staticScene = new Scene (staticParent);
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * չʾstaticScene
		 * �������Ҫ����battleʱ������staticScene���������٣�����ʾbattleScene��
		 * ��battle����������battleScene���ָ�staticScene��ʾ
		 * �����battlePo�鿴battle���
		 */
		staticScene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		stage.setScene(staticScene);
		stage.setTitle("������");
		stage.setFullScreen(true);
		stage.setResizable(false);
		primaryStage = stage;
		primaryStage.show();

	}
	@Override
	public void createNewBattle(MissionInfo missionInfo) {
		// TODO Auto-generated method stub
		//����staticscene���мǲ�Ҫ���١�
		//�������missionPo��������PVE��PVP��NORMAL֮һ��BattleScene
		//Ҫ������ע�ᵽbattlescene��ȥ���Ӷ�ʹ�ÿ�����battlescene����battleEnd�����Է���staticscene
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
		//����battlescene
		//�ָ���ʾstaticscene
		//��ʾ�ؿ�������
	}
	
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void setStage(Parent root) {
		// TODO Auto-generated method stub
		//�˷������Static������ϵͳ������ת
//		this.stage=stage;
		Platform.runLater(()->{this.stage.getScene().setRoot(root);});
//		this.staticParent.hide();
	}
	@Override
	public void returnStatic() {
		// TODO Auto-generated method stub
		//�˷�������ϵͳ����Static
		Platform.runLater(()->{this.stage.getScene().setRoot(staticParent);});
		System.out.println("return to Static");
//		this.staticParent.show();
	}

}
