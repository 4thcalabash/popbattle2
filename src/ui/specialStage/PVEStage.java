package ui.specialStage;

import ui.Main;
import ui.abstractStage.BattleStage;
import util.MissionInfo;
import bll.individual.Player;
import bll.platform.*;
public class PVEStage extends BattleStage{
//��ҵ�������scene
	
	public PVEStage (int missionID,Player player1,Main main){
		//��missionPo��������Ӧ��platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//չʾ����+����
	}
}
