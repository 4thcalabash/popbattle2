package ui.abstractStage;
import bll.support.Bonus;
import bllservice.BattlePlatform;
import javafx.scene.layout.StackPane;
import ui.Main;
import ui.sceneInterface.DramaticScene;
public abstract class BattleParent extends StackPane{
	//�����battlescene���ڼ̳й�ϵ�ϣ���PVAScene��PVPScene��NormalScene�������������
	//���Ҫ����PVA��PVP��Normal֮�����ģʽ���ʹ����µ�BattleScene������
	//����һ���Ƿϻ�
	protected DramaticScene main;//������battlescene����main����main������Ȩ�ƽ���staticscene
	protected BattlePlatform platform; //��Ӧ�ĺ��֧�֡�
	public BattleParent(Main main){
		this.main=main;
	}
	public void returnStatic(Bonus bonus){
		main.battleEnd(bonus);
	}
}
