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
	private int ad,ap,hp,DR,MR;//DR物理抗性 MR魔法抗性
	private int basicad,basicap,basichp,basicDR,basicMR,level;
//	private int playerID;
//	private int skillPoint;
	private int nowExp;
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
	}
	public int getBasicad() {
		return basicad;
	}
	public void setBasicad(int basicad) {
		this.basicad = basicad;
	}
	public int getBasicap() {
		return basicap;
	}
	public void setBasicap(int basicap) {
		this.basicap = basicap;
	}
	public int getBasichp() {
		return basichp;
	}
	public void setBasichp(int basichp) {
		this.basichp = basichp;
	}
	public int getBasicDR() {
		return basicDR;
	}
	public void setBasicDR(int basicDR) {
		this.basicDR = basicDR;
	}
	public int getBasicMR() {
		return basicMR;
	}
	public void setBasicMR(int basicMR) {
		this.basicMR = basicMR;
	}
	public PaperPlayer createPaper(){
		PaperPlayer paper = new PaperPlayer (this);
		return paper;
	}
	public int getDR() {
		return DR;
	}
	public void setDR(int dR) {
		DR = dR;
	}
	public int getMR() {
		return MR;
	}
	public void setMR(int mR) {
		MR = mR;
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
	public void setAd(int ad) {
		this.ad = ad;
	}
	public int getAp() {
		return ap;
	}
	public void setAp(int ap) {
		this.ap = ap;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public int getNowExp() {
		return nowExp;
	}
	public void setNowExp(int nowExp) {
		this.nowExp = nowExp;
	}
	public int[] getSkillList() {
		return skillList;
	}
	public void setSkillList(int[] skillList) {
		this.skillList = skillList;
	}
	public void increaseAd(int delta){
		this.ad+=delta;
	}
	public void increaseAp(int delta){
		this.ap+=delta;
	}
	public void increaseHp(int delta){
		this.hp+=delta;
	}
	public void levelUp(){
		this.level++;
	}
}
