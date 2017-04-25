package bll.support;

import bll.individual.Player;

public class Shop {
	//skillPoint upGradeStone evolveStone
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
	//不限制购买数量
	private int skillPointPrice;
	public static final int BASICSKILLPOINTPRICE = 5;
	public static final int SKILLPOINTNORMALCHEAPEST = 110;
	public static final int SKILLPOINTNORMALMOSTEXPENSIVE = 130;
	public static final int SKILLPOINTLOWCHEAPEST = 70;
	public static final int SKILLPOINTLOWMOSTEXPENSIVE = 90;
	public static final int SKILLPOINTHIGHCHEAPEST = 140;
	public static final int SKILLPOINTHIGHMOSTEXPENSIVE = 160;
	//不限制购买数量
	private int upGradeStonePrice;
	public static final int BASICUPGRADESTONEPRICE = 5;
	public static final int UPGRADESTONENORMALCHEAPEST = 70;
	public static final int UPGRADESTONENORMALMOSTEXPENSIVE = 90;
	public static final int UPGRADESTONELOWCHEAPEST = 40;
	public static final int UPGRADESTONELOWMOSTEXPENSIVE = 60;
	public static final int UPGRADESTONEHIGHCHEAPEST = 100;
	public static final int UPGRADESTONEHIGHMOSTEXPENSIVE = 120;
	//不限制购买数量
	private int evolveStonePrice;
	public static final int BASICEVOLVESTONEPRICE = 10;
	public static final int EVOLVESTONENORMALCHEAPEST = 140;
	public static final int EVOLVESTONENORMALMOSTEXPENSIVE = 160;
	public static final int EVOLVESTONELOWCHEAPEST = 110;
	public static final int EVOLVESTONELOWMOSTEXPENSIVE = 130;
	public static final int EVOLVESTONEHIGHCHEAPEST = 170;
	public static final int EVOLVESTONEHIGHMOSTEXPENSIVE = 190;
	
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
	public void buySkillPoint(int num){
		player.increaseSkillPoint(num);
		player.increaseGold(-num*this.skillPointPrice);
	}
	public void buyUpGradeStone(int num){
		player.increaseUpGradeStoneNum(num);
		player.increaseGold(-num*this.upGradeStonePrice);
	}
	public void buyEvolveStone(int num){
		player.increaseEvolveStoneNum(num);
		player.increaseGold(-num*this.evolveStonePrice);
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
		renewSkillPointPrice(level);
		renewUpGradeStonePrice(level);
		renewEvolveStonePrice(level);
	}
	private void renewSkillPointPrice(int level){
		double temp = Math.random();
		int price =0;
		if (temp<0.05){
			price=(int)(Math.random()*(Shop.SKILLPOINTLOWMOSTEXPENSIVE-Shop.SKILLPOINTLOWMOSTEXPENSIVE)+Shop.SKILLPOINTLOWCHEAPEST);
		}else if (temp<0.1){
			price = (int)(Math.random()*(Shop.SKILLPOINTHIGHMOSTEXPENSIVE-Shop.SKILLPOINTHIGHCHEAPEST)+Shop.SKILLPOINTHIGHCHEAPEST);
		}else{
			price = (int)(Math.random()*(Shop.SKILLPOINTNORMALMOSTEXPENSIVE-Shop.SKILLPOINTNORMALCHEAPEST)+Shop.SKILLPOINTNORMALCHEAPEST);
		}
		price+=level*Shop.BASICSKILLPOINTPRICE;
		this.skillPointPrice=price;
	}
	private void renewUpGradeStonePrice(int level){
		double temp = Math.random();
		int price = 0;
		if (temp<0.08){
			price = (int)(Math.random()*(Shop.UPGRADESTONELOWMOSTEXPENSIVE-Shop.UPGRADESTONELOWCHEAPEST)+Shop.UPGRADESTONELOWCHEAPEST);
		}else if (temp<0.16){
			price = (int)(Math.random()*(Shop.UPGRADESTONEHIGHMOSTEXPENSIVE-Shop.UPGRADESTONEHIGHCHEAPEST)+Shop.UPGRADESTONEHIGHCHEAPEST);
		}else{
			price = (int)(Math.random()*(Shop.UPGRADESTONENORMALMOSTEXPENSIVE-Shop.UPGRADESTONENORMALCHEAPEST)+Shop.UPGRADESTONENORMALCHEAPEST);
		}
		price+=Shop.BASICUPGRADESTONEPRICE*level;
		this.upGradeStonePrice=price;
	}
	private void renewEvolveStonePrice(int level){
		double temp = Math.random();
		int price =0;
		if (temp<0.12){
			price = (int)(Math.random()*(Shop.EVOLVESTONELOWMOSTEXPENSIVE-Shop.EVOLVESTONELOWCHEAPEST)+Shop.EVOLVESTONELOWCHEAPEST);
		}else if (temp<0.24){
			price = (int)(Math.random()*(Shop.EVOLVESTONEHIGHMOSTEXPENSIVE-Shop.EVOLVESTONEHIGHCHEAPEST)+Shop.EVOLVESTONEHIGHCHEAPEST);
		}else{
			price = (int)(Math.random()*(Shop.EVOLVESTONENORMALMOSTEXPENSIVE-Shop.EVOLVESTONENORMALCHEAPEST)+Shop.EVOLVESTONENORMALCHEAPEST);
		}
		price+=Shop.BASICEVOLVESTONEPRICE*level;
		this.evolveStonePrice=price;
	}
	public void setSkillPointPrice(int skillPointPrice) {
		this.skillPointPrice = skillPointPrice;
	}
	public void setUpGradeStonePrice(int upGradeStonePrice) {
		this.upGradeStonePrice = upGradeStonePrice;
	}
	public void setEvolveStonePrice(int evolveStonePrice) {
		this.evolveStonePrice = evolveStonePrice;
	}
	public int getSkillPointPrice() {
		return skillPointPrice;
	}
	public int getUpGradeStonePrice() {
		return upGradeStonePrice;
	}
	public int getEvolveStonePrice() {
		return evolveStonePrice;
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
