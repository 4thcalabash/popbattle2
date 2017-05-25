package bll.support;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Matrix;
import util.SkillCostCalcMethod;
import util.SkillLevelUpCalcMethod;
import util.SkillValueCalcMethod;

public class Skill {
	/**
	 * 前后端传递技能只需要传递Player的skilllist数组，其中记录了每个技能的当前等级，0为未习得。
	 * 前端可以通过getSkillNameByID(SKILLID)来获得技能的名字。
	 * 前端也可以通过getSkillByID(SKILLID).getSkillIntroduction[SkillLevel]来获取技能每个等级的说明信息
	 * 因此战斗开始的初始化过程中，前端传送给后端几个技能ID，
	 * 后端的battle平台来调用getSkillByID(SKILLID).calcVaue(paperplayer)来得知paperplayer释放SKILLID这个技能的效果值
	 * 后端也可以通过getSkillByID(SKILLID).getTargetPlayer()来得知作用对象为Myself或是Enemy
	 * skillType 指明了技能是物理伤害类型或是魔法伤害类型
	 */
	//切记本身全都是静态的，本身不记录等级，只提供计算方法
	public static final int NULLSKILL = -10000;
	
	
	public static final int TOTALNUMOFGENERATESKILL = 3;
	public static final int TOTALNUMOFSPECIALSKILL = 3;
	public static final int ID_NORMALATTACK = 100;
	public static final int ID_FIREONGRASS = 101;
	public static final int ID_WATERFLOW = 102;
	public static final int ID_HURRICANE = 103;
	public static final int ID_PRO100_1 = 104;
	public static final int ID_PRO100_2 = 105;
	public static final String[] INTRODUCTION_NORMAL = { "消耗：随机三个宝石\n出招时，用蛮力向敌人直刺，并产生一定的物理伤害\n增加物攻可以提高技能伤害", 
			"消耗：随机3颗元素石\n\n最基本的物理攻击招式，增加物攻可以提高技能伤害", 
			"消耗：随机5颗元素石\n\n最基本的物理攻击招式，增加物攻可以提高技能伤害",
			"消耗：随机6颗元素石\n\n最基本的物理攻击招式，增加物攻可以提高技能伤害" };
	public static final String[] INTRODUCTION_FIREONGRASS = { "\n加成不俗的双修技能，增加物攻和法强都可以显著提升伤害", 
			"消耗：6颗火元素石\n\n加成不俗的双修物理系技能，增加物攻和法强都可以显著提升伤害",
			"消耗：8颗火元素石\n\n加成不俗的双修物理系技能，增加物攻和法强都可以显著提升伤害",
			"消耗：10颗火元素石\n\n加成不俗的双修物理系技能，增加物攻和法强都可以显著提升伤害" };
	public static final String[] INTRODUCTION_WATERFLOW = { "\n基础伤害很高的双修法系技能，升级技能性价比较高", 
			"消耗：5颗水元素石\n\n基础伤害很高的双修法系技能，升级技能性价比较高", 
			"消耗：7颗水元素石\n\n基础伤害很高的双修法系技能，升级技能性价比较高",
			"消耗：9颗水元素石\n\n基础伤害很高的双修法系技能，升级技能性价比较高" };
	public static final String[] INTRODUCTION_HURRICANE = { "\n无基础伤害，但有全面加成的物理系技能，物攻、法强、等级都可以转化为伤害值",
			"消耗：6颗风元素石\n\n无基础伤害，但有全面加成的物理系技能，物攻、法强、等级都可以转化为伤害值", 
			"消耗：8颗风元素石\n\n无基础伤害，但有全面加成的物理系技能，物攻、法强、等级都可以转化为伤害值",
			"消耗：10颗风元素石\n\n无基础伤害，但有全面加成的物理系技能，物攻、法强、等级都可以转化为伤害值" };
	public static final String[] INTRODUCTION_PRO100_1 ={"\n超高AD加成的物理系爆发技能",
			"消耗：3颗火元素石+3颗水元素石+6颗地元素石\n\n超高AD加成的物理系爆发技能",
			"消耗：4颗火元素石+4颗水元素石+8颗地元素石\n\n超高AD加成的物理系爆发技能",
			"消耗：5颗火元素石+5颗水元素石+10颗地元素石\n\n超高AD加成的物理系爆发技能"};
	public static final String[] INTRODUCTION_PRO100_2 = {"\n超高基础伤害值的法系爆发技能",
			"消耗：10颗草元素石+10颗魄元素石\n\n超高基础伤害值的法系爆发技能",
			"消耗：12颗草元素石+12颗魄元素石\n\n超高基础伤害值的法系爆发技能",
			"消耗：14颗草元素石+14颗魄元素石\n\n超高基础伤害值的法系爆发技能"};
	public static final Skill PRO100_2 = new Skill(Skill.ID_PRO100_2,3,Player.ENEMY,
			new SkillValueCalcMethod(){

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
					if (skillLevel == 1) {
						return (int) (55 + (double) paperplayer.getPlayer().getAp() * 0.35);
					} else if (skillLevel == 2) {
						return (int) (70 + (double) paperplayer.getPlayer().getAp() * 0.45);
					} else {
						return (int) (105 + (double) paperplayer.getPlayer().getAp() * 0.65);
					}
				}
		
	},new SkillCostCalcMethod(){

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
			if (skillLevel == 1) {
				cost[Matrix.GREENELEMENT]=10;
				cost[Matrix.SPIRITELEMENT]=10;
			} else if (skillLevel == 2) {
				cost[Matrix.GREENELEMENT]=12;
				cost[Matrix.SPIRITELEMENT]=12;
			} else {
				cost[Matrix.GREENELEMENT]=14;
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
		
	},Skill.INTRODUCTION_PRO100_2,Skill.MAGICTYPE);
	public static final Skill PRO100_1 = new Skill(Skill.ID_PRO100_1,3,Player.ENEMY,
			new SkillValueCalcMethod(){

				@Override
				public int calc(PaperPlayer paperplayer) {
					// TODO Auto-generated method stub
					int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_NORMALATTACK%100];
					if (skillLevel == 1) {
						return (int) (20 + (double) paperplayer.getPlayer().getAd() * 0.9);
					} else if (skillLevel == 2) {
						return (int) (30 + (double) paperplayer.getPlayer().getAd() * 1.1);
					} else {
						return (int) (45 + (double) paperplayer.getPlayer().getAd() * 1.4);
					}
				}
		
	},new SkillCostCalcMethod(){

		@Override
		public int[] calc(PaperPlayer paperPlayer) {
			// TODO Auto-generated method stub
			int[] cost = new int[Matrix.KIND];
			int skillLevel = paperPlayer.getPlayer().getSkillList()[Skill.ID_FIREONGRASS%100];
			if (skillLevel == 1) {
				cost[Matrix.FIREELEMENT] = 3;
				cost[Matrix.WATERELEMENT]=3;
				cost[Matrix.EARTHELEMENT]=6;
			} else if (skillLevel == 2) {
				cost[Matrix.FIREELEMENT] = 4;
				cost[Matrix.WATERELEMENT]=4;
				cost[Matrix.EARTHELEMENT]=8;
			} else {
				cost[Matrix.FIREELEMENT] = 5;
				cost[Matrix.WATERELEMENT]=5;
				cost[Matrix.EARTHELEMENT]=10;
			}
			return cost;
		}
		
	},new SkillLevelUpCalcMethod(){

		@Override
		public int getCost(Player player) {
			// TODO Auto-generated method stub
			int level = player.getSkillList()[Skill.ID_NORMALATTACK%100];
			if (level ==1){
				return 6;
			}else {
				return 9;
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
						return (int) (5 + (double) paperplayer.getPlayer().getAd() * 0.6);
					} else if (skillLevel == 2) {
						return (int) (10 + (double) paperplayer.getPlayer().getAd() * 0.75);
					} else {
						return (int) (15 + (double) paperplayer.getPlayer().getAd() * 1.0
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
						num=5;
					}else {
						num=6;
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
						return 3;
					}else {
						return 7;
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
					if (skillLevel == 1) {
						cost[Matrix.FIREELEMENT] = 6;
					} else if (skillLevel == 2) {
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
						return 5;
					}else if (level == 1){
						return 7;
					}else{
						return 9;
					}
				}
				
			}, Skill.INTRODUCTION_FIREONGRASS,Skill.DAMAGETYPE);
	public static final Skill WATERFLOW = new Skill(Skill.ID_WATERFLOW, 3, Player.ENEMY, new SkillValueCalcMethod() {

		@Override
		public int calc(PaperPlayer paperplayer) {
			// TODO Auto-generated method stub
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_WATERFLOW%100];
			if (skillLevel == 1) {
				return (int) (25 + (double) paperplayer.getPlayer().getAd() * 0.35
						+ (double) paperplayer.getPlayer().getAp() * 0.3);
			} else if (skillLevel == 2) {
				return (int) (40 + (double) paperplayer.getPlayer().getAd() * 0.40
						+ (double) paperplayer.getPlayer().getAp() * 0.35);
			} else {
				return (int) (55 + (double) paperplayer.getPlayer().getAd() * 0.5
						+ (double) paperplayer.getPlayer().getAp() * 0.35);
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
				return 5;
			}else if (level ==1){
				return 8;
			}else{
				return 10;
			}
		}
		
	}, Skill.INTRODUCTION_WATERFLOW,Skill.MAGICTYPE);
	public static final Skill HURRICANE = new Skill(Skill.ID_HURRICANE, 3, Player.ENEMY, new SkillValueCalcMethod() {

		@Override
		public int calc(PaperPlayer paperplayer) {
			// TODO Auto-generated method stub
			int skillLevel = paperplayer.getPlayer().getSkillList()[Skill.ID_HURRICANE%100];
			if (skillLevel == 1) {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.5
						+ (double) paperplayer.getPlayer().getAp() * 0.7
						+ (double) paperplayer.getPlayer().getLevel() * 0.2);
			} else if (skillLevel == 2) {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.55
						+ (double) paperplayer.getPlayer().getAp() * 0.75
						+ (double) paperplayer.getPlayer().getLevel() * 0.25);
			} else {
				return (int) (0 + (double) paperplayer.getPlayer().getAd() * 0.6
						+ (double) paperplayer.getPlayer().getAp() * 0.8
						+ (double) paperplayer.getPlayer().getLevel() * 0.3);
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
			return "普通攻击";
		case Skill.ID_FIREONGRASS:
			return "烈火燎原";
		case Skill.ID_WATERFLOW:
			return "水漫金山";
		case Skill.ID_HURRICANE:
			return "飓风之舞";
		case Skill.ID_PRO100_1:
			return "神秘力量1";
		case Skill.ID_PRO100_2:
			return "神秘力量2";
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
	// private int level;//技能当前等级
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
	// 技能最高等级需要在常量包中记录
	@SuppressWarnings("unused")
	private int effectValue;
	private final int MAXLEVEL;
	// 正 扣血 ，负 回复
	private int targetPlayer;
	private SkillValueCalcMethod valueCalcMethod;
	//技能施放消耗的元素
	
	private SkillCostCalcMethod costCalcMethod;
	public int getMAXLEVEL() {
		return MAXLEVEL;
	}

	//升级花费
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
