package ui.abstractStage;
import bllservice.BattlePlatform;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import po.*;
import ui.Main;
import ui.sceneInterface.DramaticScene;
public abstract class BattleParent extends BorderPane{
	//�����battlescene���ڼ̳й�ϵ�ϣ���PVAScene��PVPScene��NormalScene�������������
	//���Ҫ����PVA��PVP��Normal֮�����ģʽ���ʹ����µ�BattleScene������
	//����һ���Ƿϻ�
	protected DramaticScene main;//������battlescene����main����main������Ȩ�ƽ���staticscene
	protected BattlePlatform platform; //��Ӧ�ĺ��֧�֡�
	public BattleParent(Main main){
		this.main=main;
	}
	public void returnStatic(){
		main.battleEnd();
	}
}