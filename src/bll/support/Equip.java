package bll.support;


import util.*;

//只需要用ID和Level就可以生成一件装备
//本身不记录等级，只提供计算方法
public class Equip{
	public static final int ID_NULL = -1;
	public static final int ID_IRONWEARING = 1;
	public static final int ID_IRONHEADWEARING =2;
	public static final int ID_IRONSWORD =3;
	public static final int ID_IRONWINGS =4;
	public static final int ID_GOLDWEARING=5;
	public static final int ID_GOLDHEADWEARING = 6;
	public static final int ID_GOLDSWORD = 7;
	public static final int ID_GOLDWINGS = 8;
	
	public static final String[] INTRODUCTION_IRONWEARING = { "IRON", "IROND", "IRONDD" };
	public static final String[] INTRODUCTION_IRONHEADWEARING = {"IRON","IROND","IRONDD"};
	public static final String[] INTRODUCTION_IRONSWORD = {"IRON","IROND","IRONDD"};
	public static final String[] INTRODUCTION_IRONWINGS ={"IRON","IROND","IRONDD"};
	public static final String[] INTRODUCTION_GOLDWEARING ={"GOLD","GOLD","Gold"};
	public static final String[] INTRODUCTION_GOLDHEADWEARING={"GOLD","GOLD","Gold"};
	public static final String[] INTRODUCTION_GOLDSWORD={"GOLD","GOLD","Gold"};
	public static final String[] INTRODUCTION_GOLDWINGS={"GOLD","GOLD","Gold"};
//ID,MAXLEVEL,ADCalcMethod,APCalcMethod,DRCalcMethod,MRCalcMethod,DTCalcMethod,MTCalcMethod,levelUpCostCalcMethod,equipIntroduction,evolveEquipID
	public static final Equip IRONWEARING = new Equip(Equip.ID_IRONWEARING, 3, new Calcer(0,5,10),new Calcer(0,0,0),new Calcer(0,0,0), 
			new Calcer(5,10,15), new Calcer(5,10,15), new Calcer(0,0,0), new Calcer(0,0,0),new Calcer(3,5,5), Equip.INTRODUCTION_IRONWEARING,Equip.ID_GOLDWEARING);
	public static final Equip IRONHEADWEARING = new Equip(Equip.ID_IRONHEADWEARING, 3,new Calcer(12,17,22),new Calcer(0,0,0),new Calcer(0,0,0),
			new Calcer(4,7,10), new Calcer(4,7,10), new Calcer(0,0,0), new Calcer(0,0,0),new Calcer(3,4,5), Equip.INTRODUCTION_IRONHEADWEARING,Equip.ID_GOLDHEADWEARING);
	public static final Equip IRONSWORD = new Equip (Equip.ID_IRONSWORD,3,new Calcer(0,0,0),new Calcer(5,10,15),new Calcer(0,0,5),
			new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(0,5,10),new Calcer(0,0,10),new Calcer(3,6,7),Equip.INTRODUCTION_IRONSWORD,Equip.ID_GOLDSWORD);
	public static final Equip IRONSWINGS = new Equip (Equip.ID_IRONWINGS,3,new Calcer(5,8,11),new Calcer(2,4,7),new Calcer(3,6,9),
			new Calcer(3,5,8),new Calcer(3,5,8),new Calcer(5,7,9),new Calcer(5,7,10),new Calcer(5,6,10),Equip.INTRODUCTION_IRONWINGS,Equip.ID_GOLDWINGS);
	public static final Equip GOLDWEARING = new Equip (Equip.ID_GOLDWEARING,3,new Calcer(17,25,32),new Calcer(0,0,0),new Calcer(0,0,0),
			new Calcer(22,28,35),new Calcer(22,28,35),new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(9,13,-1),Equip.INTRODUCTION_GOLDWEARING,Equip.ID_NULL);
	public static final Equip GOLDHEADWEARING = new Equip(Equip.ID_GOLDHEADWEARING,3,new Calcer(28,35,45),new Calcer(0,0,0),new Calcer(0,0,0),
			new Calcer(13,17,21),new Calcer(13,17,21),new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(6,8,-1),Equip.INTRODUCTION_GOLDHEADWEARING,Equip.ID_NULL);
	public static final Equip GOLDSWORD = new Equip(Equip.ID_GOLDSWORD,3,new Calcer(10,15,20),new Calcer(25,35,50),new Calcer(10,20,30),
			new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(15,25,35),new Calcer(15,28,40),new Calcer(10,15,-1),Equip.INTRODUCTION_GOLDSWORD,Equip.ID_NULL);
	public static final Equip GOLDSWINGS = new Equip(Equip.ID_GOLDWINGS,3,new Calcer(23,26,29),new Calcer(12,15,18),new Calcer(18,25,32),
			new Calcer(12,14,16),new Calcer(12,14,16),new Calcer(10,13,17),new Calcer(15,17,22),new Calcer(10,15,-1),Equip.INTRODUCTION_GOLDWINGS,Equip.ID_NULL);


	public static Equip getEquipByID(int i) {
		switch (i) {
		case Equip.ID_IRONWEARING:
			return Equip.IRONWEARING;
		case Equip.ID_IRONHEADWEARING:
			return Equip.IRONHEADWEARING;
		case Equip.ID_IRONSWORD:
			return Equip.IRONSWORD;
		case Equip.ID_IRONWINGS:
			return Equip.IRONSWINGS;
		case Equip.ID_GOLDHEADWEARING:
			return Equip.GOLDHEADWEARING;
		case Equip.ID_GOLDSWORD:
			return Equip.GOLDSWORD;
		case Equip.ID_GOLDWEARING:
			return Equip.GOLDHEADWEARING;
		case Equip.ID_GOLDWINGS:
			return Equip.GOLDSWINGS;
		default:
			return null;
		}
	}

	public int getMAXLEVEL() {
		return MAXLEVEL;
	}

	public static String getEquipNameByID(int i) {
		switch (i) {
		case Equip.ID_IRONWEARING:
			return "锁子甲";
		case Equip.ID_IRONHEADWEARING:
			return "旧铁盔";
		case Equip.ID_IRONSWORD:
			return "精钢剑";
		case Equip.ID_IRONWINGS:
			return "夜明戒";
		case Equip.ID_GOLDHEADWEARING:
			return "霸王盔";
		case Equip.ID_GOLDSWORD:
			return "玄冰剑";
		case Equip.ID_GOLDWEARING:
			return "将军胄";
		case Equip.ID_GOLDWINGS:
			return "圣光符";
		default:
			return null;
		}
	}

	private int ID;
	private String[] equipIntroduction;
	private final int MAXLEVEL;// 允许的最高等级
	private CalcMethod HPCalcMethod,ADCalcMethod, APCalcMethod, DRCalcMethod, MRCalcMethod, DTCalcMethod, MTCalcMethod,levelUpCostCalcMethod;
	private int evolveEquipID;
//需要加一个costCalcer ，一个int EvoluteCost 。Equip的通货是升级石和进阶石，升级石用来升级装备，进阶石用来进阶装备。
	public Equip(int ID, int MAXLEVEL,CalcMethod HPCalcMethod, CalcMethod ADCalcMethod, CalcMethod APCalcMethod,
			CalcMethod DRCalcMethod, CalcMethod MRCalcMethod, CalcMethod DTCalcMethod,
			CalcMethod MTCalcMethod,CalcMethod levelUpCostCalcMethod, String[] equipIntroduction,int evolveEquipID) {
		this.ID = ID;
		this.MAXLEVEL = MAXLEVEL;
		this.HPCalcMethod=HPCalcMethod;
		this.ADCalcMethod = ADCalcMethod;
		this.APCalcMethod = APCalcMethod;
		this.DRCalcMethod = DRCalcMethod;
		this.MRCalcMethod = MRCalcMethod;
		this.DTCalcMethod = DTCalcMethod;
		this.MTCalcMethod = MTCalcMethod;
		this.levelUpCostCalcMethod=levelUpCostCalcMethod;
		this.equipIntroduction = equipIntroduction;
		this.evolveEquipID=evolveEquipID;
		// this.MAXLEVEL=MAXLEVEL;
	}
	public int getEvolveEquipID() {
		return evolveEquipID;
	}
	public String[] getEquipIntroduction() {
		return equipIntroduction;
	}
	public int getHP(int level){
		return this.HPCalcMethod.calc(level);
	}
	public int getAD(int level) {
		return this.ADCalcMethod.calc(level);
	}

	public int getAP(int level) {
		return this.APCalcMethod.calc(level);
	}

	public int getDR(int level) {
		return this.DRCalcMethod.calc(level);
	}

	public int getMR(int level) {
		return this.MRCalcMethod.calc(level);
	}

	public int getMT(int level) {
		return this.MTCalcMethod.calc(level);
	}

	public int getDT(int level) {
		return this.DTCalcMethod.calc(level);
	}
	
	public int getLevelUpCost(int level){
		return this.levelUpCostCalcMethod.calc(level);
	}
	public int getID() {
		return ID;
	}

}
