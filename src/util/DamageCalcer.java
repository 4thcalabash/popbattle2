package util;

import bll.individual.PaperPlayer;
import bll.support.Skill;

public class DamageCalcer {
	private static final int DAMAGEBASIC = 20;
	private static final int MAGICBASIC = 20;
	public static int calc(PaperPlayer action,Skill skill,PaperPlayer target){
		//���㼼��ȫ���˺�
		int skillValue = skill.calcVaue(action);
		//���������˺�
		if (skill.getSkillType()==Skill.DAMAGETYPE){
			//��������
			int ansDR = target.getPlayer().getDR()-action.getPlayer().getDT();
			ansDR=ansDR<0?0:ansDR;
			double resistence = ansDR/(DamageCalcer.DAMAGEBASIC+ansDR);
			return (int)((double)skillValue*(1-resistence));
		}else{
			//ħ������
			int ansMR = target.getPlayer().getMR()-action.getPlayer().getMT();
			ansMR=ansMR<0?0:ansMR;
			double resistence = ansMR/(DamageCalcer.MAGICBASIC+ansMR);
			return (int)((double)skillValue*(1-resistence));
		}
	}
}
