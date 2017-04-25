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
	
	private BattleStage battleStage;//ÿ��ʹ��ʱ����Ҫ����ʵ����
	private StaticStage staticStage = new StaticStage(this);//һֱ����һ��staticScene����battleʱ���أ��ڷ�battleʱ��ʾ��
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * չʾstaticScene
		 * �������Ҫ����battleʱ������staticScene���������٣�����ʾbattleScene��
		 * ��battle����������battleScene���ָ�staticScene��ʾ
		 * �����battlePo�鿴battle���
		 */
		primaryStage = staticStage;
		primaryStage.show();

	}
	@Override
	public void createNewBattle(MissionInfo missionInfo) {
		// TODO Auto-generated method stub
		//����staticscene���мǲ�Ҫ���١�
		//�������missionPo��������PVE��PVP��NORMAL֮һ��BattleScene
		//Ҫ������ע�ᵽbattlescene��ȥ���Ӷ�ʹ�ÿ�����battlescene����battleEnd�����Է���staticscene
		
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

}
