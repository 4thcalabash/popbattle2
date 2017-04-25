package util;

import bll.individual.PaperPlayer;
import bll.support.Skill;

public class DamageCalcer {
	private static final int DAMAGEBASIC = 20;
	private static final int MAGICBASIC = 20;
	public static int calc(PaperPlayer action,Skill skill,PaperPlayer target){
		//计算技能全额伤害
		int skillValue = skill.calcVaue(action);
		//计算最终伤害
		if (skill.getSkillType()==Skill.DAMAGETYPE){
			//物理类型
			int ansDR = target.getPlayer().getDR()-action.getPlayer().getDT();
			ansDR=ansDR<0?0:ansDR;
			double resistence = ansDR/(DamageCalcer.DAMAGEBASIC+ansDR);
			return (int)((double)skillValue*(1-resistence));
		}else{
			//魔法类型
			int ansMR = target.getPlayer().getMR()-action.getPlayer().getMT();
			ansMR=ansMR<0?0:ansMR;
			double resistence = ansMR/(DamageCalcer.MAGICBASIC+ansMR);
			return (int)((double)skillValue*(1-resistence));
		}
	}
}
