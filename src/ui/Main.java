package ui;
import po.*;
import ui.abstractStage.*;
import ui.sceneInterface.*;
import ui.specialStage.PVEStage;
import ui.specialStage.StaticStage;
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
	
	private BattleStage battleStage;//ÿ��ʹ��ʱ����Ҫ����ʵ����
	private StaticStage staticStage = new StaticStage(this);//һֱ����һ��staticScene����battleʱ���أ��ڷ�battleʱ��ʾ��
	private Parent staticRoot = staticStage.getScene().getRoot();
	private Stage stageOnShow = staticStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * չʾstaticScene
		 * �������Ҫ����battleʱ������staticScene���������٣�����ʾbattleScene��
		 * ��battle����������battleScene���ָ�staticScene��ʾ
		 * �����battlePo�鿴battle���
		 */
		primaryStage = stageOnShow;
		primaryStage.show();

	}
	@Override
	public void createNewBattle(MissionInfo missionInfo) {
		// TODO Auto-generated method stub
		//����staticscene���мǲ�Ҫ���١�
		//�������missionPo��������PVE��PVP��NORMAL֮һ��BattleScene
		//Ҫ������ע�ᵽbattlescene��ȥ���Ӷ�ʹ�ÿ�����battlescene����battleEnd�����Է���staticscene
		if (missionInfo.getModel()==Battle.PVE){
			battleStage = new PVEStage(missionInfo.getID(),staticStage.getBasicPlatform().getPlayer1(),this);
			
		}
		//������ת
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
//		this.stageOnShow=stage;
		Platform.runLater(()->{this.stageOnShow.getScene().setRoot(root);});
//		this.staticStage.hide();
	}
	@Override
	public void returnStatic() {
		// TODO Auto-generated method stub
		//�˷�������ϵͳ����Static
		Platform.runLater(()->{this.stageOnShow.getScene().setRoot(this.staticRoot);});
		System.out.println("return to Static");
//		this.staticStage.show();
	}

}
