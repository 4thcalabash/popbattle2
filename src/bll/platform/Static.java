package bll.platform;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.support.Equip;
import bll.support.Skill;
import bllservice.*;
import dal.FileHelper;
import po.EquipPo;
import po.FigurePo;

public class Static implements BasicPlatform, Shopable, Achievementable, Equipable, Playerable, Skillable {
	private Player player;
	private FileHelper helper;
	public Static (int index){
		//indexµµ°¸ºÅ
		helper = new FileHelper ();
		player = new Player(helper.loadData(index));
	}
	@Override
	public Player getPlayer1() {
		// TODO Auto-generated method stub
		return player;
	}
	@Override
	public BattlePlatform createBattle(int missionID,int [] allSkills) {
		// TODO Auto-generated method stub
		PaperPlayer paperPlayer1 = player.createPaper();
		for (int i=0;i<3;i++){
			paperPlayer1.getAllSkills()[i]=Skill.getSkillByID(allSkills[i]);
		}
		Battle battle = new Battle (missionID,paperPlayer1);
		
		
		return (BattlePlatform)battle;
	}


}
