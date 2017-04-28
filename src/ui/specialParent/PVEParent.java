package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import util.MissionInfo;
import bll.individual.Player;
import bll.platform.*;
public class PVEParent extends BattleParent{
//玩家单机闯关scene
	
	public PVEParent (int missionID,Player player1,Main main){
		//用missionPo来生成相应的platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//展示界面+监听
	}
}
