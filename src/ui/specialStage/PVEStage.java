package ui.specialStage;

import ui.Main;
import ui.abstractStage.BattleStage;
import util.MissionInfo;
import bll.individual.Player;
import bll.platform.*;
public class PVEStage extends BattleStage{
//玩家单机闯关scene
	
	public PVEStage (int missionID,Player player1,Main main){
		//用missionPo来生成相应的platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//展示界面+监听
	}
}
