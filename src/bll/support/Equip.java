package bll.support;

import util.*;

//只需要用ID和Level就可以生成一件装备
public class Equip implements Calcable{

	public static final int ID_GOLDWEARING = 1;
	public static final int ID_GOLDHEADWEARING =2;
	public static final int ID_GOLDSWORD =3;
	public static final int ID_GOLDWINGS =4;
	
	
	public static final String[] INTRODUCTION_GOLDWEARING = { "GOLD", "GOLDD", "GOLDDD" };
	public static final String[] INTRODUCTION_GOLDHEADWEARING = {"GOLD","GOLDD","GOLDDD"};
	public static final String[] INTRODUCTION_GOLDSWORD = {"GOLD","GOLDD","GOLDDD"};
	public static final String[] INTRODUCTION_GOLDWINGS ={"GOLD","GOLDD","GOLDDD"};
	
	
	public static final Equip GOLDWEARING = new Equip(Equip.ID_GOLDWEARING, 3, new Calcer(0,0,0),new Calcer(0,0,0), 
			new Calcer(5,10,15), new Calcer(5,10,15), new Calcer(0,0,0), new Calcer(0,0,0), Equip.INTRODUCTION_GOLDWEARING);
	public static final Equip GOLDHEADWEARING = new Equip(Equip.ID_GOLDHEADWEARING, 3,new Calcer(0,0,0),new Calcer(0,0,0),
			new Calcer(5,10,15), new Calcer(5,10,15), new Calcer(0,0,0), new Calcer(0,0,0), Equip.INTRODUCTION_GOLDHEADWEARING);
	public static final Equip GOLDSWORD = new Equip (Equip.ID_GOLDSWORD,3,new Calcer(5,10,15),new Calcer(0,0,10),
			new Calcer(0,0,0),new Calcer(0,0,0),new Calcer(0,5,10),new Calcer(0,5,10),Equip.INTRODUCTION_GOLDSWORD);
	public static final Equip GOLDSWINGS = new Equip (Equip.ID_GOLDWINGS,3,new Calcer(3,6,9),new Calcer(3,6,9),
			new Calcer(3,6,9),new Calcer(3,6,9),new Calcer(3,6,9),new Calcer(3,6,9),Equip.INTRODUCTION_GOLDWINGS);
	
	
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

	public String[] getEquipIntroduction() {
		return equipIntroduction;
	}

	private String[] equipIntroduction;
	private int level;
	private final int MAXLEVEL;// 允许的最高等级
	private CalcMethod ADCalcMethod, APCalcMethod, DRCalcMethod, MRCalcMethod, DTCalcMethod, MTCalcMethod;

	public Equip(int ID, int MAXLEVEL, CalcMethod ADCalcMethod, CalcMethod APCalcMethod,
			CalcMethod DRCalcMethod, CalcMethod MRCalcMethod, CalcMethod DTCalcMethod,
			CalcMethod MTCalcMethod, String[] equipIntroduction) {
		this.ID = ID;
		this.MAXLEVEL = MAXLEVEL;
		this.ADCalcMethod = ADCalcMethod;
		this.APCalcMethod = APCalcMethod;
		this.DRCalcMethod = DRCalcMethod;
		this.MRCalcMethod = MRCalcMethod;
		this.DTCalcMethod = DTCalcMethod;
		this.MTCalcMethod = MTCalcMethod;
		this.equipIntroduction = equipIntroduction;
		// this.MAXLEVEL=MAXLEVEL;
	}

	public int getAD() {
		return this.ADCalcMethod.calc(this);
	}

	public int getAP() {
		return this.APCalcMethod.calc(this);
	}

	public int getDR() {
		return this.DRCalcMethod.calc(this);
	}

	public int getMR() {
		return this.MRCalcMethod.calc(this);
	}

	public int getMT() {
		return this.MTCalcMethod.calc(this);
	}

	public int getDT() {
		return this.DTCalcMethod.calc(this);
	}

	public int getID() {
		return ID;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
