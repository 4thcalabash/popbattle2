package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import bllservice.*;
import po.*;
public class NormalParent extends BattleParent{
//经典和休闲scene
	public NormalParent(int missionID,Main main){

		super(main);
		//用missionPo来申请一个Normalplatform
	}
	
}
