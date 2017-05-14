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
	public static Bonus getBonusByID(int missionID){
		switch(missionID){
		case 0:return Bonus.mission0;
		case 1:return Bonus.mission1;
		case 2:return Bonus.mission2;
		case 3:return Bonus.mission3;
		case 4:return Bonus.mission4;
		case 5:return Bonus.mission5;
		case 6:return Bonus.mission6;
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
