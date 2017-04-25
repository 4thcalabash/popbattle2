package bll.support;

import bll.individual.Player;

public class Shop {
	//与player绑定的Shop
	private Player player;
	//可出售的PP和Exp数量，随等级更新
	private int PPNum,ExpNum;
	//买潜力点数，买卖经验值
	private int PPPrice;
	//正常的价格区间90-110G/Point
	public static final int PPNORMALCHEAPEST = 80;
	public static final int PPNORMALMOSTEXPENSIVE = 100;
	//高物价时，130-150G/Point
	public static final int PPHIGHCHEAPEST = 120;
	public static final int PPHIGHMOSTEXPENSIVE = 140;
	//低物价时，60-80G/Point
	public static final int PPLOWCHEAPEST = 50;
	public static final int PPLOWMOSTEXPENSIVE = 70;
	private int ExpPrice;
	public static final int EXPNORMALCHEAPEST = 50;
	public static final int EXPNORMALMOSTEXPENSIVE = 60;
	public static final int EXPLOWCHEAPEST = 30;
	public static final int EXPLOWMOSTEXPENSIVE = 40;
	public static final int EXPHIGHCHEAPEST = 70;
	public static final int EXPHIGHMOSTEXPENSIVE = 80;
	public Shop(Player player){
		this.player=player;
	}
	public void buyPP(int num){
		this.PPNum-=num;
		player.increaseGold(-num*this.PPPrice);
		player.increasePotentialPoint(num);
	}
	public void buyExp(int num){
		this.ExpNum-=num;
		player.increaseGold(-num*this.ExpPrice);
		player.increaseExp(num*100);
	}
	public void soldExp(int num){
		player.increaseExp(-num*100);
		player.increaseGold(num*this.ExpPrice);
	}
	public void renewNum(){
		//仅当升级时调用
		int level = player.getLevel();
		renewPPNum(level);
		renewExpNum(level);
		renewPrice();
	}
	private void renewPPNum(int level){
		int num =0;
		if (level<5){
			num=1;
		}else if (level<10){
			num=2;
		}else if (level<15){
			num=3;
		}else{
			num=4;
		}
		double temp = Math.random();
		if (temp<0.03){
			num++;
		}
		this.PPNum=num;
	}
	private void renewExpNum(int level){
		int num=0;
		if (level<3){
			num=0;
		}else if (level<5){
			num=1;
		}else if (level<10){
			num=2;
		}else if (level<14){
			num=3;
		}else if (level<20){
			num=4;
		}
		double temp = Math.random();
		if (temp<0.05){
			num++;
		}
		this.ExpNum=num;
	}
	public void renewPrice (){
		int level = player.getLevel();
		renewPPPrice(level);
		renewExpPrice(level);
	}
	private void renewPPPrice(int level){
		double temp = Math.random();
		int price =0;
		if (temp<0.1){
			//低物价
			price = (int)(Math.random()*(Shop.PPLOWMOSTEXPENSIVE-Shop.PPLOWCHEAPEST)+Shop.PPLOWCHEAPEST);
		}else if (temp<0.2){
			//高物价
			price = (int)(Math.random()*(Shop.PPHIGHMOSTEXPENSIVE-Shop.PPHIGHCHEAPEST)+Shop.PPHIGHCHEAPEST);
		}else{
			//正常物价
			price = (int)(Math.random()*(Shop.PPNORMALMOSTEXPENSIVE-Shop.PPNORMALCHEAPEST)+Shop.PPNORMALCHEAPEST);
		}
		price+=(int)(Math.random()*5+5)*level;
		this.PPPrice=price;
	}
	private void renewExpPrice(int level){
		double temp = Math.random();
		int price =0;
		if (temp<0.2){
			//低物价
			price = (int)(Math.random()*(Shop.EXPLOWMOSTEXPENSIVE-Shop.EXPLOWCHEAPEST)+Shop.EXPLOWCHEAPEST);
		}else if (temp<0.4){
			//高物价
			price = (int)(Math.random()*(Shop.EXPHIGHMOSTEXPENSIVE-Shop.EXPHIGHCHEAPEST)+Shop.EXPHIGHCHEAPEST);
		}else{
			//正常物价
			price = (int)(Math.random()*(Shop.EXPNORMALMOSTEXPENSIVE-Shop.EXPNORMALCHEAPEST)+Shop.EXPNORMALCHEAPEST);
		}
		price+=(int)(Math.random()*5+5)*level;
		this.ExpPrice=price;
	}
	
	public int getPPNum() {
		return PPNum;
	}
	public void setPPNum(int pPNum) {
		PPNum = pPNum;
	}
	public int getExpNum() {
		return ExpNum;
	}
	public void setExpNum(int expNum) {
		ExpNum = expNum;
	}
	public int getPPPrice() {
		return PPPrice;
	}
	public void setPPPrice(int pPPrice) {
		PPPrice = pPPrice;
	}
	public int getExpPrice() {
		return ExpPrice;
	}
	public void setExpPrice(int expPrice) {
		ExpPrice = expPrice;
	}
	
}
