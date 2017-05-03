package bll.support;


import util.*;

//只需要用ID和Level就可以生成一件装备
//本身不记录等级，只提供计算方法
public class Equip{
	public static final int ID_NULL = -1;
	public static final int ID_GOLDWEARING = 1;
	public static final int ID_GOLDHEADWEARING =2;
	public static final int ID_GOLDSWORD =3;
	public static final int ID_GOLDWINGS =4;
	
	
	public static final String[] INTRODUCTION_GOLDWEARING = { "GOLD", "GOLDD", "GOLDDD" };
	public static final String[] INTRODUCTION_GOLDHEADWEARING = {"GOLD","GOLDD","GOLDDD"};
	public static final String[] INTRODUCTION_GOLDSWORD = {"GOLD","GOLDD","GOLDDD"};
	public static final String[] INTRODUCTION_GOLDWINGS ={"GOLD","GOLDD","GOLDDD"};
	
//ID,MAXLEVEL,ADCalcMethod,APCalcMethod,DRCalcMethod,MRCalcMethod,DTCalcMethod,MTCalcMethod,levelUpCostCalcMethod,equipIntroduction,evolveEquipID
	public static final Equip GOLDWEARING = new Equip(Equip.ID_GOLDWEARING, 3, new Calcer(5,10,15),new Calcer(0,0,0),new Calcer(0,0,0), 
			new Calcer(5,10,15), new Calcer(5,10,15), new Calcer(0,0,0), new Calcer(0,0,0),new Calcer(1,2,Equip.ID_NULL), Equip.INTRODUCTION_GOLDWEARING,Equip.ID_NULL);
	public static final Equip GOLDHEADWEARING = new Equip(Equip.ID_GOLDHEADWEARING, 3,new Calcer(10,20,30),new Calcer(0,0,0),new Calcer(0,0,0),
			new Calcer(5,10,15), new Calcer(5,10,15), new Calcer(0,0,0), new Calcer(0,0,0),new Calcer(1,2,Equip.ID_NULL), Equip.INTRODUCTION_GOLDHEADWEARING,Equip.ID_NULL);
	public static final Equip GOLDSWORD = new Equip (Equip.ID_GOLDSWORD,3,new Calcer(0,0,0),new Calcer(5,10,15),new Calcer(0,0,10),
			new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(0,5,10),new Calcer(0,5,10),new Calcer(1,2,Equip.ID_NULL),Equip.INTRODUCTION_GOLDSWORD,Equip.ID_NULL);
	public static final Equip GOLDSWINGS = new Equip (Equip.ID_GOLDWINGS,3,new Calcer(5,15,25),new Calcer(3,6,9),new Calcer(3,6,9),
			new Calcer(3,6,9),new Calcer(3,6,9),new Calcer(3,6,9),new Calcer(3,6,9),new Calcer(1,2,Equip.ID_NULL),Equip.INTRODUCTION_GOLDWINGS,Equip.ID_NULL);
	
	


	public static Equip getEquipByID(int i) {
		switch (i) {
		case Equip.ID_GOLDWEARING:
			return Equip.GOLDWEARING;
		case Equip.ID_GOLDHEADWEARING:
			return Equip.GOLDHEADWEARING;
		case Equip.ID_GOLDSWORD:
			return Equip.GOLDSWORD;
		case Equip.ID_GOLDWINGS:
			return Equip.GOLDSWINGS;
		default:
			return null;
		}
	}

	public static String getEquipNameByID(int i) {
		switch (i) {
		case Equip.ID_GOLDWEARING:
			return "黄金甲";
		case Equip.ID_GOLDHEADWEARING:
			return "黄金头盔";
		case Equip.ID_GOLDSWORD:
			return "黄金剑";
		case Equip.ID_GOLDWINGS:
			return "黄金翅膀";
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
