package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import util.MissionInfo;
import bll.individual.Player;
import bll.platform.*;
public class PVEParent extends BattleParent{
//��ҵ�������scene
	
	public PVEParent (int missionID,Player player1,Main main){
		//��missionPo��������Ӧ��platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//չʾ����+����
	}
}
