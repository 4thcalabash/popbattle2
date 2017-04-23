package ui.specialStage;

import ui.Main;
import ui.abstractStage.BattleStage;
import bll.platform.*;
public class PVEStage extends BattleStage{
//玩家单机闯关scene
	
	public PVEStage (int missionID,Main main){
		//用missionPo来生成相应的platform
		super(main);
	}
}
