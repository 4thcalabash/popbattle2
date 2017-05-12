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
	
	
	public static final int TOTALNUMOFGENERATESKILL = 3;
	public static final int TOTALNUMOFSPECIALSKILL = 3;
	public static final int ID_NORMALATTACK = 100;
	public static final int ID_FIREONGRASS = 101;
	public static final int ID_WATERFLOW = 102;
	public static final int ID_HURRICANE = 103;
	public static final int ID_PRO100_1 = 104;
	public static final int ID_PRO100_2 = 105;
	public static final String[] INTRODUCTION_NORMAL = { "a", "b", "c","d" };
	public static final String[] INTRODUCTION_FIREONGRASS = { "aa", "bb", "cc","dd" };
	public static final String[] INTRODUCTION_WATERFLOW = { "aaa", "bbb", "ccc","ddd" };
	public static final String[] INTRODUCTION_HURRICANE = { "aaaa", "bbbb", "cccc","dddd" };
	public static final String[] INTRODUCTION_PRO100_1 ={"aaaaa","bbbbb","ccccc","ddddd"};
	public static final String[] INTRODUCTION_PRO100_2 = {"aaaaaa","bbbbbb","cccccc","dddddd"};
	public static final Skill PRO100_2 = new Skill(Skill.ID_PRO100_2,3,Player.ENEMY,
			new SkillValueCalcMethod(){

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
					if (skillLevel == 1) {
						return (int) (40 + (double) paperplayer.getPlayer().getAd() * 0.3);
					} else if (skillLevel == 2) {
						return (int) (60 + (double) paperplayer.getPlayer().getAd() * 0.4);
					} else {
						return (int) (80 + (double) paperplayer.getPlayer().getAd() * 0.5
								+ (double) paperplayer.getPlayer().getAp() * 0.2);
					}
				}
		
	},new SkillCostCalcMethod(){

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
			if (skillLevel == 0) {
				cost[Matrix.FIREELEMENT] = 5;
				cost[Matrix.WATERELEMENT]=5;
				cost[Matrix.SPIRITELEMENT]=10;
			} else if (skillLevel == 1) {
				cost[Matrix.FIREELEMENT] = 7;
				cost[Matrix.WATERELEMENT]=7;
				cost[Matrix.SPIRITELEMENT]=12;
			} else {
				cost[Matrix.FIREELEMENT] = 9;
				cost[Matrix.WATERELEMENT]=9;
				cost[Matrix.SPIRITELEMENT]=14;
			}
			return cost;
		}
		
	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_NORMALATTACK%100];
			if (level ==1){
				return 10;
			}else {
				return 20;
			}
		}
		
	},Skill.INTRODUCTION_PRO100_2,Skill.DAMAGETYPE);
	public static final Skill PRO100_1 = new Skill(Skill.ID_PRO100_1,3,Player.ENEMY,
			new SkillValueCalcMethod(){

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
					if (skillLevel == 1) {
						return (int) (20 + (double) paperplayer.getPlayer().getAd() * 0.3);
					} else if (skillLevel == 2) {
						return (int) (30 + (double) paperplayer.getPlayer().getAd() * 0.45);
					} else {
						return (int) (45 + (double) paperplayer.getPlayer().getAd() * 0.5
								+ (double) paperplayer.getPlayer().getAp() * 0.15);
					}
				}
		
	},new SkillCostCalcMethod(){

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
			if (skillLevel == 0) {
				cost[Matrix.FIREELEMENT] = 8;
				cost[Matrix.WATERELEMENT]=3;
			} else if (skillLevel == 1) {
				cost[Matrix.FIREELEMENT] = 12;
				cost[Matrix.WATERELEMENT]=4;
			} else {
				cost[Matrix.FIREELEMENT] = 15;
				cost[Matrix.WATERELEMENT]=5;
			}
			return cost;
		}
		
	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_NORMALATTACK%100];
			if (level ==1){
				return 5;
			}else {
				return 8;
			}
		}
		
	},Skill.INTRODUCTION_PRO100_1,Skill.DAMAGETYPE);
	public static final Skill NORMALATTACK = new Skill(Skill.ID_NORMALATTACK, 3, Player.ENEMY,
			new SkillValueCalcMethod() {

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
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
					int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
					int num;
					if (skillLevel==1){
						num=3;
					}else if (skillLevel==2){
						num=4;
					}else {
						num=5;
					}
					int sum = 0;
					for (int i=0;i<Matrix.KIND;i++){
						sum+=paperPlayer.getElementPool()[i];
					}
					if (num>sum){
						cost[0]=num;
					}else{
						while (num>0){
							int temp = (int)(Math.random()*Matrix.KIND);
							if (paperPlayer.getElementPool()[temp]>cost[temp]){
								cost[temp]++;
								num--;
							}
						}
					}
					return cost;

				}

			},new SkillLevelUpCalcMethod(){

				@Override
				public int getCost(Player player) {
					// TODO Auto-generated method stub
					int level = player.getSkillList()[Skill.ID_NORMALATTACK%100];
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
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
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
					int[] cost = new int[Matrix.KIND];
					int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
					if (skillLevel == 0) {
						cost[Matrix.FIREELEMENT] = 6;
					} else if (skillLevel == 1) {
						cost[Matrix.FIREELEMENT] = 8;
					} else {
						cost[Matrix.FIREELEMENT] = 10;
					}
					return cost;
				}

			},new SkillLevelUpCalcMethod(){

				@Override
				public int getCost(Player player) {
					// TODO Auto-generated method stub
					int level = player.getSkillList()[Skill.ID_FIREONGRASS%100];
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
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_WATERFLOW%100];
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
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_WATERFLOW%100];
			if (skillLevel == 1) {
				cost[Matrix.WATERELEMENT] = 5;
			} else if (skillLevel == 2) {
				cost[Matrix.WATERELEMENT] = 7;
			} else {
				cost[Matrix.WATERELEMENT] = 9;
			}
			return cost;
		}

	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_WATERFLOW%100];
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
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_HURRICANE%100];
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
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_HURRICANE%100];
			if (skillLevel == 1) {
				cost[Matrix.AIRELEMENT] = 6;
			} else if (skillLevel == 2) {
				cost[Matrix.AIRELEMENT] = 8;
			} else {
				cost[Matrix.AIRELEMENT] = 10;
			}
			return cost;
		}

	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_HURRICANE%100];
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
		case Skill.ID_PRO100_1:
			return Skill.PRO100_1;
		case Skill.ID_PRO100_2:
			return Skill.PRO100_2;
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
		case Skill.ID_PRO100_1:
			return "��������1";
		case Skill.ID_PRO100_2:
			return "��������2";
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
	public String[] getSkillIntroduction() {
		return skillIntroduction;
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
	public int getMAXLEVEL() {
		return MAXLEVEL;
	}

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
		
		return (int)((0.8+Math.random()*0.4)*this.valueCalcMethod.calc(paperplayer));
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
		System.out.println("Cost:");
		for (int i=0;i<Matrix.KIND;i++){
			System.out.print(cost[i]+" ");
		}
		System.out.println("Has:");
		for (int i=0;i<Matrix.KIND;i++){
			System.out.print(paperplayer.getElementPool()[i]+" ");
		}
		for (int i=0;i<Matrix.KIND;i++){
			if (cost[i]>paperplayer.getElementPool()[i]){
				ans=false;
				break;
			}
		}
		return ans;
	}
}
