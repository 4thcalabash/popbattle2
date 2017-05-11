package bll.individual;
import java.util.ArrayList;
import bllservice.*;
import bll.support.*;
import vo.*;
import bll.platform.*;
import po.*;
import util.Calcable;
public class Player implements Calcable{
	public void setPro(int pro) {
		this.pro = pro;
	}
	/**********
	 *人物属性分为基础属性和当前属性
	 *基础属性是裸的人物属性
	 *当前属性是基础属性加上装备的增益
	 *
	 * 人物
	 */
	public static final String INTRODUCTION100 = "你是一个剑客，特别特别的贱你是一个剑客，特别特别的贱你是一个剑客，特别特别的贱你是一个剑客，特别特别的贱你是一个剑客，特别特别的贱你是一个剑客，特别特别的贱";
	public static final String INTRODUCTION200 = "你是一个女忍者，特别特别能忍";
	public static final int USER_PLAYERID=1;
	//设定用户永远为Player1
	public static final int AI_PLAYERID=2;
	//设定AI永远为Player2
	public static final int INITSAVEPRO_1=100;
	public static final int INITSAVEPRO_2=200;
	public static final int PRO_1=100;
	public static final int PRO_2=200;
	//指定了两个职业的初始档案编号，以及SpecialSkill的偏移量
	//职业的SpecialSkill的SkillID分别为
	//104、105、106
	//204、205、206
	
	//基础属性
	private int pro;
	private int ad,ap,hp,DR,MR,DT,MT;//DR物理抗性 MR魔法抗性

	private Shop shop;
	public int getSkillPointNum() {
		return skillPointNum;
	}
	public void setSkillPointNum(int skillPointNum) {
		this.skillPointNum = skillPointNum;
	}
	public int getUpGradeStoneNum() {
		return upGradeStoneNum;
	}
	public void setUpGradeStoneNum(int upGradeStoneNum) {
		this.upGradeStoneNum = upGradeStoneNum;
	}
	public int getEvolveStoneNum() {
		return evolveStoneNum;
	}
	public void setEvolveStoneNum(int evolveStoneNum) {
		this.evolveStoneNum = evolveStoneNum;
	}
	public int getHeadWearingID() {
		return headWearingID;
	}
	public void setHeadWearingID(int headWearingID) {
		this.headWearingID = headWearingID;
	}
	public int getWeaponID() {
		return weaponID;
	}
	public void setWeaponID(int weaponID) {
		this.weaponID = weaponID;
	}
	public int getWearingID() {
		return wearingID;
	}
	public void setWearingID(int wearingID) {
		this.wearingID = wearingID;
	}
	public int getWingsID() {
		return wingsID;
	}
	public void setWingsID(int wingsID) {
		this.wingsID = wingsID;
	}
	public int getHeadWearingLevel() {
		return headWearingLevel;
	}
	public void setHeadWearingLevel(int headWearingLevel) {
		this.headWearingLevel = headWearingLevel;
	}
	public int getWeaponLevel() {
		return weaponLevel;
	}
	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}
	public int getWearingLevel() {
		return wearingLevel;
	}
	public void setWearingLevel(int wearingLevel) {
		this.wearingLevel = wearingLevel;
	}
	public int getWingsLevel() {
		return wingsLevel;
	}
	public void setWingsLevel(int wingsLevel) {
		this.wingsLevel = wingsLevel;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public void setSkillChoosed(int[] skillChoosed) {
		this.skillChoosed = skillChoosed;
	}
	private int AILevel;

	private int skillPointNum,upGradeStoneNum,evolveStoneNum;


	private int basicad,basicap,basichp,basicDR,basicMR,level;
//	private int playerID;
//	private int skillPoint;
	private int nowExp;
	private int potentialPoint;


	private int gold;

	private int [] skillList;
	private int [] skillChoosed = new int [3];
	private int headWearingID;
	private int weaponID;
	private int wearingID;
	private int wingsID;
	private int headWearingLevel,weaponLevel,wearingLevel,wingsLevel;
	public static final int BASICAD=2;
	public static final int BASICAP=2;
	public static final int BASICHP = 10;
	public static final int BASICDR = 3;
	public static final int BASICMR = 3;
	public static final int BASICPOTENTIALPOINT = 5;

	public static final int INFINITEEXP = -3;
	public static final int MYSELF = 233;
	public static final int ENEMY = 666;
	public Player (PlayerVo playerVo){

		
		
		
		System.out.println("pro:"+playerVo.getPro());
		this.pro=playerVo.getPro();
		this.basicad=playerVo.getBasicad();
		this.basicap=playerVo.getBasicap();
		this.basichp=playerVo.getBasichp();
		this.basicDR=playerVo.getBasicDR();
		this.basicMR=playerVo.getBasicMR();
		System.out.println(basicad+" "+basicap+" "+basichp+" "+basicDR+" "+basicMR);
		this.level=playerVo.getLevel();
		this.nowExp=playerVo.getNowExp();
		this.gold=playerVo.getGold();
		this.skillPointNum=playerVo.getSkillPointNum();
		this.upGradeStoneNum=playerVo.getUpGradeStoneNum();
		this.evolveStoneNum=playerVo.getEvolveStoneNum();
		this.skillList=playerVo.getSkillList();
		this.skillChoosed=playerVo.getSkillChoosed();
		this.headWearingID=playerVo.getHeadWearingID();
		this.weaponID=playerVo.getWeaponID();
		this.wearingID=playerVo.getWearingID();
		this.wingsID=playerVo.getWingsID();
		this.headWearingLevel=playerVo.getHeadWearingLevel();
		this.weaponLevel=playerVo.getWeaponLevel();
		this.wearingLevel=playerVo.getWearingLevel();
		this.wingsLevel=playerVo.getWingsLevel();

		this.potentialPoint= playerVo.getPotentialPoint();
		this.shop = new Shop(this);
		this.shop.setPPPrice(playerVo.getShopVo().getPPPrice());
		this.shop.setPPNum(playerVo.getShopVo().getPPNum());
		this.shop.setExpPrice(playerVo.getShopVo().getExpPrice());
		this.shop.setExpNum(playerVo.getShopVo().getExpNum());
		this.shop.setSkillPointPrice(playerVo.getSkillPointNum());
		this.shop.setUpGradeStonePrice(playerVo.getUpGradeStoneNum());
		this.shop.setEvolveStonePrice(playerVo.getEvolveStoneNum());
		this.hp=basichp+Equip.getEquipByID(headWearingID).getHP(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getHP(this.weaponLevel)+Equip.getEquipByID(wearingID).getHP(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getHP(this.wingsLevel);
		this.ad=basicad+Equip.getEquipByID(headWearingID).getAD(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getAD(this.weaponLevel)+Equip.getEquipByID(wearingID).getAD(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getAD(this.wingsLevel);
		this.ap=basicap+Equip.getEquipByID(headWearingID).getAP(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getAP(this.weaponLevel)+Equip.getEquipByID(wearingID).getAP(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getAP(this.wingsLevel);
		this.MR=basicMR+Equip.getEquipByID(headWearingID).getMR(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getMR(this.weaponLevel)+Equip.getEquipByID(wearingID).getMR(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getMR(this.wingsLevel);
		this.DR=basicDR+Equip.getEquipByID(headWearingID).getDR(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getDR(this.weaponLevel)+Equip.getEquipByID(wearingID).getDR(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getDR(this.wingsLevel);
		this.DT=Equip.getEquipByID(headWearingID).getDT(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getDT(this.weaponLevel)+Equip.getEquipByID(wearingID).getDT(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getDT(this.wingsLevel);
		this.MT=Equip.getEquipByID(headWearingID).getMT(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getMT(this.weaponLevel)+Equip.getEquipByID(wearingID).getMT(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getMT(this.wingsLevel);
		System.out.println(ad+" "+ap+" "+hp+" "+DR+" "+MR);
		
		
		
		/*************
		 * For Debug *
		 *************/
		this.skillChoosed[0]=pro+0;
		this.skillChoosed[1]=pro+1;
		this.skillChoosed[2]=pro+2;
	}
	public void setAd(int ad) {
		this.ad = ad;
	}
	public void setAp(int ap) {
		this.ap = ap;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setDR(int dR) {
		DR = dR;
	}
	public void setMR(int mR) {
		MR = mR;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDT() {
		return DT;
	}
	public void setDT(int dT) {
		DT = dT;
	}
	public int getMT() {
		return MT;
	}
	public void setMT(int mT) {
		MT = mT;
	}
	public int getAILevel() {
		return AILevel;
	}
	public void setAILevel(int aILevel) {
		AILevel = aILevel;
	}
	public int getBasicad() {
		return basicad;
	}
	public int getBasicap() {
		return basicap;
	}
	public int getBasichp() {
		return basichp;
	}
	public int getBasicDR() {
		return basicDR;
	}
	public int getBasicMR() {
		return basicMR;
	}
	public int getPotentialPoint() {
		return potentialPoint;
	}
	public void setPotentialPoint(int potentialPoint) {
		this.potentialPoint = potentialPoint;
	}
	public PaperPlayer createPaper(){
		PaperPlayer paper = new PaperPlayer (this);
		System.out.println("myHp="+hp);
//		paper.setHp(this.hp);
		return paper;
	}
	public int getDR() {
		return DR;
	}
	public int getMR() {
		return MR;
	}


	public int getPro() {
		return pro;
	}
	public int[] getSkillChoosed() {
		return skillChoosed;
	}
	public int getAd() {
		return ad;
	}
	public int getAp() {
		return ap;
	}
	public int getHp() {
		return hp;
	}
	public int getLevel() {
		return level;
	}
	public int getNowExp() {
		return nowExp;
	}
	public int[] getSkillList() {
		return skillList;
	}
	public Shop getShop() {
		return shop;
	}
//	public Equip getHeadWearing() {
//		return headWearing;
//	}
//	public Equip getWeapon() {
//		return weapon;
//	}
//	public Equip getWearing() {
//		return wearing;
//	}
//	public Equip getWings() {
//		return wings;
//	}
	public int getGold() {
		return gold;
	}
	public void increasePotentialPoint(int delta){
		this.potentialPoint+=delta;
	}
	public void increaseGold(int delta){
		this.gold+=delta;
	}
	public void increaseAd(int delta){
		this.basicad+=delta*Player.BASICAD;
		this.ad+=delta*Player.BASICAD;
		this.potentialPoint-=delta;
	}
	public void increaseAp(int delta){
		this.basicap+=delta*Player.BASICAP;
		this.ap+=delta*Player.BASICAP;
		this.potentialPoint-=delta;
	}
	public void increaseHp(int delta){
		this.basichp+=delta*Player.BASICHP;
		this.hp+=delta*Player.BASICHP;
		this.potentialPoint-=delta;
	}
	public void increaseDR(int delta){
		this.basicDR+=delta*Player.BASICDR;
		this.DR+=delta*Player.BASICDR;
		this.potentialPoint-=delta;
	}
	public void increaseMR(int delta){
		this.basicMR+=delta*Player.BASICMR;
		this.MR+=delta*Player.BASICMR;
		this.potentialPoint-=delta;
	}
	public void increaseExp(int delta){
		this.nowExp+=delta;
	}
	public void increaseSkillPoint(int delta){
		this.skillPointNum+=delta;
	}
	public void increaseUpGradeStoneNum(int delta){
		this.upGradeStoneNum+=delta;
	}
	public void increaseEvolveStoneNum(int delta){
		this.evolveStoneNum+=delta;
	}
	public static int getExpNumberToLevelUp(int index){
		if (index<5){
			return 100+index*50;
		}else if (index<10){
			return Player.getExpNumberToLevelUp(4)+100*(index-4);
		}else if (index<15){
			return Player.getExpNumberToLevelUp(9)+150*(index-9);
		}else if (index<20){
			return Player.getExpNumberToLevelUp(14)+200*(index-14);
		}else{
			return Player.INFINITEEXP;
		}
	}
	public void levelUp(){
		this.nowExp-=Player.getExpNumberToLevelUp(this.level);
		this.level++;
		this.potentialPoint+=Player.BASICPOTENTIALPOINT;
	}
	public void skillLevelup(int skillID){
		int index = skillID%100;
		this.skillPointNum-=Skill.getSkillByID(skillID).getLevelUpCost(this);
		this.skillList[index]++;
		System.out.println("Still Have "+skillPointNum+" Skill Point After Level UP");
	}
	public void eqiupLevelup(int equipID){
		int ans =-1;
		if (equipID==this.headWearingID){
			ans=0;
		}else if (equipID==this.weaponID){
			ans=1;
		}else if (equipID==this.wingsID){
			ans=2;
		}else if (equipID==this.wearingID){
			ans=3;
		}
		switch(ans){
		case 0:this.upGradeStoneNum-=Equip.getEquipByID(this.headWearingID).getLevelUpCost(this.headWearingLevel);this.headWearingLevel++;break;
		case 1:this.upGradeStoneNum-=Equip.getEquipByID(this.weaponID).getLevelUpCost(this.weaponLevel);this.weaponLevel++;break;
		case 2:this.upGradeStoneNum-=Equip.getEquipByID(this.wingsID).getLevelUpCost(this.wingsLevel);this.wingsLevel++;break;
		case 3:this.upGradeStoneNum-=Equip.getEquipByID(wearingID).getLevelUpCost(this.wearingLevel);this.wearingLevel++;break;
		default:System.out.println("装备升级ID解析错误");break;

		}
		System.out.println(headWearingLevel+"/"+weaponLevel+"/"+wingsLevel+"/"+wearingLevel);
		renewProperty();
	}
	public void equipEvolve(int equipID){
		int ans =-1;
		if (equipID==this.headWearingID){
			ans=0;
		}else if (equipID==this.weaponID){
			ans=1;
		}else if (equipID==this.wingsID){
			ans=2;
		}else if (equipID==this.wearingID){
			ans=3;
		}
		switch(ans){
		case 0:this.evolveStoneNum-=Equip.getEquipByID(this.headWearingID).getLevelUpCost(this.headWearingLevel);this.headWearingLevel=1;this.headWearingID=Equip.getEquipByID(this.headWearingID).getEvolveEquipID();break;
		case 1:this.evolveStoneNum-=Equip.getEquipByID(this.weaponID).getLevelUpCost(this.weaponLevel);this.weaponLevel=1;this.weaponID=Equip.getEquipByID(this.weaponID).getEvolveEquipID();break;
		case 2:this.evolveStoneNum-=Equip.getEquipByID(this.wingsID).getLevelUpCost(this.wingsLevel);this.wingsLevel=1;this.wingsID=Equip.getEquipByID(this.wingsID).getEvolveEquipID();break;
		case 3:this.evolveStoneNum-=Equip.getEquipByID(this.wearingID).getLevelUpCost(this.wearingLevel);this.wearingLevel=1;this.wearingID=Equip.getEquipByID(this.wearingID).getEvolveEquipID();break;
		default: System.out.println("装备ID解析错误！");break;
		}
		renewProperty();
	}
	private void renewProperty(){
		this.hp=basichp+Equip.getEquipByID(headWearingID).getHP(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getHP(this.weaponLevel)+Equip.getEquipByID(wearingID).getHP(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getHP(this.wingsLevel);
		this.ad=basicad+Equip.getEquipByID(headWearingID).getAD(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getAD(this.weaponLevel)+Equip.getEquipByID(wearingID).getAD(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getAD(this.wingsLevel);
		this.ap=basicap+Equip.getEquipByID(headWearingID).getAP(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getAP(this.weaponLevel)+Equip.getEquipByID(wearingID).getAP(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getAP(this.wingsLevel);
		this.MR=basicMR+Equip.getEquipByID(headWearingID).getMR(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getMR(this.weaponLevel)+Equip.getEquipByID(wearingID).getMR(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getMR(this.wingsLevel);
		this.DR=basicDR+Equip.getEquipByID(headWearingID).getDR(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getDR(this.weaponLevel)+Equip.getEquipByID(wearingID).getDR(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getDR(this.wingsLevel);
		this.DT=Equip.getEquipByID(headWearingID).getDT(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getDT(this.weaponLevel)+Equip.getEquipByID(wearingID).getDT(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getDT(this.wingsLevel);
		this.MT=Equip.getEquipByID(headWearingID).getMT(this.headWearingLevel)+
				Equip.getEquipByID(weaponID).getMT(this.weaponLevel)+Equip.getEquipByID(wearingID).getMT(this.wearingLevel)+
				Equip.getEquipByID(wingsID).getMT(this.wingsLevel);
		System.out.println(ad+" "+ap+" "+hp+" "+DR+" "+MR);
	}
}
