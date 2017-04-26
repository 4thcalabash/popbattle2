package bll.support;

import java.util.ArrayList;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Matrix;
import util.*;

public class Skill {
	/**
	 * ǰ��˴��ݼ���ֻ��Ҫ����Player��skilllist���飬���м�¼��ÿ�����ܵĵ�ǰ�ȼ���0Ϊδϰ�á�
	 * ǰ�˿���ͨ��getSkillNameByID(SKILLID)����ü��ܵ����֡�
	 * ǰ��Ҳ����ͨ��getSkillByID(SKILLID).getSkillIntroduction[SkillLevel]����ȡ����ÿ���ȼ���˵����Ϣ
	 * ���ս����ʼ�ĳ�ʼ�������У�ǰ�˴��͸���˼�������ID��
	 * ��˵�battleƽ̨������getSkillByID(SKILLID).calcVaue(paperplayer)����֪paperplayer�ͷ�SKILLID������ܵ�Ч��ֵ
	 * ���Ҳ����ͨ��getSkillByID(SKILLID).getTargetPlayer()����֪���ö���ΪMyself����Enemy
	 * skillType ָ���˼����������˺����ͻ���ħ���˺�����
	 */
	//�мǱ���ȫ���Ǿ�̬�ģ�������¼�ȼ���ֻ�ṩ���㷽��
	public static final int NULLSKILL = -10000;
	
	
	public static final int TOTALNUMOFGENERATESKILL = 4;
	public static final int TOTALNUMOFSPECIALSKILL = 3;
	public static final int ID_NORMALATTACK = 0;
	public static final int ID_FIREONGRASS = 1;
	public static final int ID_WATERFLOW = 2;
	public static final int ID_HURRICANE = 3;
	public static final String[] INTRODUCTION_NORMAL = { "a", "b", "c" };
	public static final String[] INTRODUCTION_FIREONGRASS = { "aa", "bb", "cc" };
	public static final String[] INTRODUCTION_WATERFLOW = { "aaa", "bbb", "ccc" };
	public static final String[] INTRODUCTION_HURRICANE = { "aaaa", "bbbb", "cccc" };
	public static final Skill NORMALATTACK = new Skill(Skill.ID_NORMALATTACK, 3, Player.ENEMY,
			new SkillValueCalcMethod() {

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK];
					if (skillLevel == 1) {
						return (int) (5 + (double) paperplayer.getPlayer().getAd() * 0.2);
					} else if (skillLevel == 2) {
						return (int) (10 + (double) paperplayer.getPlayer().getAd() * 0.25);
					} else {
						return (int) (15 + (double) paperplayer.getPlayer().getAd() * 0.25
								+ (double) paperplayer.getPlayer().getAp() * 0.1);
					}
				}

			}, new SkillCostCalcMethod() {

				@Override
				public int[] calc(PaperPlayer paperPlayer) {
					// TODO Auto-generated method stub
					int[] cost = new int[Matrix.KIND];
					int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK];
					int temp[] = new int[Matrix.KIND];
					int num = 0;
					for (int i = 0; i < Matrix.KIND; i++) {
						if (paperPlayer.getElementPool()[i] != 0) {
							temp[num] = i;
							num++;
						}
					}

					int ans = (int) (Math.random() * num);
					cost[ans] = 1;
					return cost;

				}

			},new SkillLevelUpCalcMethod(){

				@Override
				public int getCost(Player player) {
					// TODO Auto-generated method stub
					int level = player.getSkillList()[Skill.ID_NORMALATTACK];
					if (level ==1){
						return 1;
					}else {
						return 2;
					}
				}
				
			}, Skill.INTRODUCTION_NORMAL,Skill.DAMAGETYPE);
	public static final Skill FIREONGRASS = new Skill(Skill.ID_FIREONGRASS, 3, Player.ENEMY,
			new SkillValueCalcMethod() {

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS];
					if (skillLevel == 0) {
						return -1;
					} else if (skillLevel == 1) {
						return (int) (10 + (double) paperplayer.getPlayer().getAd() * 0.5
								+ (double) paperplayer.getPlayer().getAp() * 0.7);
					} else if (skillLevel == 2) {
						return (int) (15 + (double) paperplayer.getPlayer().getAd() * 0.6
								+ (double) paperplayer.getPlayer().getAp() * 0.7);
					} else {
						return (int) (25 + (double) paperplayer.getPlayer().getAd() * 0.6
								+ (double) paperplayer.getPlayer().getAp() * 0.8);
					}
				}

			}, new SkillCostCalcMethod() {

				@Override
				public int[] calc(PaperPlayer paperPlayer) {
					// TODO Auto-generated method stub
					int[] cost = new int[5];
					int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS];
					if (skillLevel == 0) {
						cost[Matrix.FIREELEMENT] = 3;
					} else if (skillLevel == 1) {
						cost[Matrix.FIREELEMENT] = 4;
					} else {
						cost[Matrix.FIREELEMENT] = 5;
					}
					return cost;
				}

			},new SkillLevelUpCalcMethod(){

				@Override
				public int getCost(Player player) {
					// TODO Auto-generated method stub
					int level = player.getSkillList()[Skill.ID_FIREONGRASS];
					if (level ==0){
						return 3;
					}else if (level == 1){
						return 4;
					}else{
						return 5;
					}
				}
				
			}, Skill.INTRODUCTION_FIREONGRASS,Skill.DAMAGETYPE);
	public static final Skill WATERFLOW = new Skill(Skill.ID_WATERFLOW, 3, Player.ENEMY, new SkillValueCalcMethod() {

		@Override
		public int calc(PaperPlayer paperplayer) {
			// TODO Auto-generated method stub
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_WATERFLOW];
			if (skillLevel == 1) {
				return (int) (15 + (double) paperplayer.getPlayer().getAd() * 0.45
						+ (double) paperplayer.getPlayer().getAp() * 0.3);
			} else if (skillLevel == 2) {
				return (int) (25 + (double) paperplayer.getPlayer().getAd() * 0.5
						+ (double) paperplayer.getPlayer().getAp() * 0.35);
			} else {
				return (int) (40 + (double) paperplayer.getPlayer().getAd() * 0.55
						+ (double) paperplayer.getPlayer().getAp() * 0.4);
			}
		}

	}, new SkillCostCalcMethod() {

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_WATERFLOW];
			if (skillLevel == 1) {
				cost[Matrix.WATERELEMENT] = 3;
			} else if (skillLevel == 2) {
				cost[Matrix.WATERELEMENT] = 5;
			} else {
				cost[Matrix.WATERELEMENT] = 7;
			}
			return cost;
		}

	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_WATERFLOW];
			if (level ==0){
				return 3;
			}else if (level ==1){
				return 4;
			}else{
				return 5;
			}
		}
		
	}, Skill.INTRODUCTION_WATERFLOW,Skill.MAGICTYPE);
	public static final Skill HURRICANE = new Skill(Skill.ID_HURRICANE, 3, Player.ENEMY, new SkillValueCalcMethod() {

		@Override
		public int calc(PaperPlayer paperplayer) {
			// TODO Auto-generated method stub
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_HURRICANE];
			if (skillLevel == 1) {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.4
						+ (double) paperplayer.getPlayer().getAp() * 0.5
						+ (double) paperplayer.getPlayer().getLevel() * 0.1);
			} else if (skillLevel == 2) {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.5
						+ (double) paperplayer.getPlayer().getAp() * 0.6
						+ (double) paperplayer.getPlayer().getLevel() * 0.15);
			} else {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.6
						+ (double) paperplayer.getPlayer().getAp() * 0.7
						+ (double) paperplayer.getPlayer().getLevel() * 0.2);
			}
		}

	}, new SkillCostCalcMethod() {

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_HURRICANE];
			if (skillLevel == 1) {
				cost[Matrix.AIRELEMENT] = 4;
			} else if (skillLevel == 2) {
				cost[Matrix.AIRELEMENT] = 6;
			} else {
				cost[Matrix.AIRELEMENT] = 8;
			}
			return cost;
		}

	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_HURRICANE];
			if (level ==0){
				return 4;
			}else if (level ==1){
				return 6;
			}else {
				return 8;
			}
		}
		
	}, Skill.INTRODUCTION_HURRICANE,Skill.DAMAGETYPE);

	public static Skill getSkillByID(int i) {
		switch (i) {
		case Skill.ID_NORMALATTACK:
			return Skill.NORMALATTACK;
		case Skill.ID_FIREONGRASS:
			return Skill.FIREONGRASS;
		case Skill.ID_WATERFLOW:
			return Skill.WATERFLOW;
		case Skill.ID_HURRICANE:
			return Skill.HURRICANE;
		default:
			return null;
		}
	}

	public static String getSkillNameByID(int i) {
		switch (i) {
		case Skill.ID_NORMALATTACK:
			return "��ͨ����";
		case Skill.ID_FIREONGRASS:
			return "�һ���ԭ";
		case Skill.ID_WATERFLOW:
			return "ˮ����ɽ";
		case Skill.ID_HURRICANE:
			return "쫷�֮��";
		default:
			return null;
		}
	}

	private int ID;
	private int skillType;
	public static final int DAMAGETYPE= -5;
	public static final int MAGICTYPE = -10;
	public int getSkillType() {
		return skillType;
	}
	// private int level;//���ܵ�ǰ�ȼ�
	public int getID() {
		return ID;
	}

	private String[] skillIntroduction;
	// public int getLevel() {
	// return level;
	// }
	// ������ߵȼ���Ҫ�ڳ������м�¼
	private int effectValue;
	private final int MAXLEVEL;
	// �� ��Ѫ ���� �ظ�
	private int targetPlayer;
	private SkillValueCalcMethod valueCalcMethod;
	//����ʩ�����ĵ�Ԫ��
	
	private SkillCostCalcMethod costCalcMethod;
	//��������
	private SkillLevelUpCalcMethod levelUpCostCalcMethod;
	public Skill(int SKILLID, int MAXLEVEL, int targetPlayer, SkillValueCalcMethod valueCalcMethod,
			SkillCostCalcMethod costCalcMethod,SkillLevelUpCalcMethod levelUpCostCalcMethod, String[] skillIntroduction,int skillType) {
		this.ID = SKILLID;

		this.MAXLEVEL = MAXLEVEL;
		this.targetPlayer = targetPlayer;
		this.valueCalcMethod = valueCalcMethod;
		this.costCalcMethod = costCalcMethod;
		this.levelUpCostCalcMethod=levelUpCostCalcMethod;
		this.skillIntroduction = skillIntroduction;
		this.skillType=skillType;
	}

	public int getTargetPlayer() {
		return targetPlayer;
	}

	public int calcVaue(PaperPlayer paperplayer) {
		return this.valueCalcMethod.calc(paperplayer);
	}

	public int[] calcCost(PaperPlayer paperplayer) {
		return this.costCalcMethod.calc(paperplayer);
	}
	public int getLevelUpCost(Player player){
		return this.levelUpCostCalcMethod.getCost(player);
	}
	public boolean canAction(PaperPlayer paperplayer){
		boolean ans =true;
		int [] cost = this.calcCost(paperplayer);
		for (int i=0;i<Matrix.KIND;i++){
			if (cost[i]>paperplayer.getElementPool()[i]){
				ans=false;
				break;
			}
		}
		return ans;
	}
}
