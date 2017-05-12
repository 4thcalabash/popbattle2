package bll.support;

public class Bonus {
	private int exp,gold,sp,us,es;
	public static final Bonus mission0 = new Bonus(10,20,1,1,1);
	public static final Bonus mission1 = new Bonus (15,20);
	
	public static Bonus getBonusByID(int missionID){
		switch(missionID){
		case 0:return Bonus.mission0;
		case 1:return Bonus.mission1;
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
