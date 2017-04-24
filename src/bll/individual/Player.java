package bll.individual;
import java.util.ArrayList;
import bllservice.*;
import bll.support.*;
import vo.*;
import bll.platform.*;
import po.*;
public class Player {
	/**********
	 *人物属性分为基础属性和当前属性
	 *基础属性是裸的人物属性
	 *当前属性是基础属性加上装备的增益
	 *
	 * 人物
	 */
	
	//基础属性
	private int ad,ap,hp,DR,MR,DT,MT;//DR物理抗性 MR魔法抗性
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
	private int basicad,basicap,basichp,basicDR,basicMR,level;
//	private int playerID;
//	private int skillPoint;
	private int nowExp;
	private int potentialPoint;
	public int getPotentialPoint() {
		return potentialPoint;
	}
	public void setPotentialPoint(int potentialPoint) {
		this.potentialPoint = potentialPoint;
	}
	//唯一通货gold
	private int gold;
	//记录技能等级，0：未学习
	private int [] skillList;
	//记录装备等级，0：未获得
	private Equip headWearing;
	private Equip weapon;
	private Equip wearing;
	private Equip wings;
	//读取档案生成Player,包括AI在内的Player都得读档来生成
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
		this.basicad=playerVo.getBasicad();
		this.basicap=playerVo.getBasicap();
		this.basichp=playerVo.getBasichp();
		this.basicDR=playerVo.getBasicDR();
		this.basicMR=playerVo.getBasicMR();
		this.level=playerVo.getLevel();
		this.nowExp=playerVo.getNowExp();
		this.skillList=playerVo.getSkillList();
		this.headWearing=Equip.getEquipByID(playerVo.getHeadWearingID());
		this.headWearing.setLevel(playerVo.getHeadWearingLevel());
		this.weapon=Equip.getEquipByID(playerVo.getWeaponID());
		this.weapon.setLevel(playerVo.getWeaponLevel());
		this.wearing=Equip.getEquipByID(playerVo.getWearingID());
		this.wearing.setLevel(playerVo.getWearingLevel());
		this.wings=Equip.getEquipByID(playerVo.getWingsID());
		this.wings.setLevel(playerVo.getWingsLevel());
		this.gold=playerVo.getGold();
		this.potentialPoint= playerVo.getPotentialPoint();
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
	public PaperPlayer createPaper(){
		PaperPlayer paper = new PaperPlayer (this);
		paper.setHp(this.hp);
		return paper;
	}
	public int getDR() {
		return DR;
	}
	public int getMR() {
		return MR;
	}

//	public int getSkillPoint() {
//		return skillPoint;
//	}
//	public void setSkillPoint(int skillPoint) {
//		this.skillPoint = skillPoint;
//	}
//	public int getPlayerID() {
//		return playerID;
//	}
//	public void setPlayerID(int playerID) {
//		this.playerID = playerID;
//	}
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
	public void increasePotentialPoint(int delta){
		this.potentialPoint+=delta;
	}
	public void increaseGold(int delta){
		this.gold+=delta;
	}
	public void increaseAd(int delta){
		this.basicad+=delta*Player.BASICAD;
		this.potentialPoint-=delta;
	}
	public void increaseAp(int delta){
		this.basicap+=delta*Player.BASICAP;
		this.potentialPoint-=delta;
	}
	public void increaseHp(int delta){
		this.basichp+=delta*Player.BASICHP;
		this.potentialPoint-=delta;
	}
	public void increaseDR(int delta){
		this.basicDR+=delta*Player.BASICDR;
		this.potentialPoint-=delta;
	}
	public void increaseMR(int delta){
		this.basicMR+=delta*Player.BASICMR;
		this.potentialPoint-=delta;
	}
	public void increaseExp(int delta){
		this.nowExp+=delta;
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
}
