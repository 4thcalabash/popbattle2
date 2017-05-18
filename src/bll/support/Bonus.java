package bll.support;

public class Bonus {
	private int exp,gold,sp,us,es;
	public static final Bonus mission0 = new Bonus(10,20,1,1,1);
	public static final Bonus mission1 = new Bonus (20,20);
	public static final Bonus mission2 = new Bonus (35,40,1,1,0);
	public static final Bonus mission3 = new Bonus (40,45,1,0,1);
	public static final Bonus mission4 = new Bonus (70,65,2,1,1);
	public static final Bonus mission5 = new Bonus (100,115,2,2,2);
	public static final Bonus mission6 = new Bonus (150,200,3,3,4);
	public static final Bonus normal0 = new Bonus(20,20);
	public static final Bonus normal1 = new Bonus(25,25);
	public static final Bonus normal2 = new Bonus (30,30,1,0,0);
	public static final Bonus normal3 = new Bonus (35,35,2,0,0);
	public static final Bonus normal4 = new Bonus (30,30,2,1,1);
	public static final Bonus normal5 = new Bonus (100,0);
	public static final Bonus normal6 = new Bonus (0,0,3,2,2);
	public static final Bonus normal7 = new Bonus (80,80,1,1,1);
	public static final Bonus normal8 = new Bonus (100,100,2,1,1);
	public static final Bonus normal9 = new Bonus (110,110,2,2,2);
	public static Bonus getBonusByID(int missionID){
		switch(missionID){
		case 0:return Bonus.mission0;
		case 1:return Bonus.mission1;
		case 2:return Bonus.mission2;
		case 3:return Bonus.mission3;
		case 4:return Bonus.mission4;
		case 5:return Bonus.mission5;
		case 6:return Bonus.mission6;
		case 100:return Bonus.normal0;
		case 101:return Bonus.normal1;
		case 102:return Bonus.normal2;
		case 103:return Bonus.normal3;
		case 104:return Bonus.normal4;
		case 105:return Bonus.normal5;
		case 106:return Bonus.normal6;
		case 107:return Bonus.normal7;
		case 108:return Bonus.normal8;
		case 109:return Bonus.normal9;
		default:return null;
		}
	}
	
	
	
	public Bonus(int exp,int gold,int sp,int us,int es){
		this.exp=exp;
		this.gold=gold;
		this.sp=sp;
		this.us=us;
		this.es=es;
	}
	public Bonus (int exp,int gold){
		this.exp=exp;
		this.gold=gold;
		this.sp=
		this.us=
		this.es=0;
	}
	public int getExp() {
		return exp;
	}

	public int getGold() {
		return gold;
	}

	public int getSp() {
		return sp;
	}

	public int getUs() {
		return us;
	}

	public int getEs() {
		return es;
	}
	
}
