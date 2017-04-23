package vo;
import bll.individual.*;
import bll.support.Equip;
public class PlayerVo {
	private int basicad,basicap,basichp,basicDR,basicMR;
	private int level,nowExp;
	private int [] skillList;
	private int headWearingID,weaponID,wearingID,wingsID;
	private int headWearingLevel,weaponLevel,wearingLevel,wingsLevel;
	private int gold;
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
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
	
}
