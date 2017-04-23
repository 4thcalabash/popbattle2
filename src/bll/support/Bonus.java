package bll.support;

import java.util.ArrayList;
//Ω±¿¯–≈œ¢
public class Bonus {
	private ArrayList <Item> itemBouns;
	private ArrayList <Equip> equipBonus;
	private int exp,gold;
	private int hp,ad,ap,MR,DR,DT,MT;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
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
	public int getMR() {
		return MR;
	}
	public void setMR(int mR) {
		MR = mR;
	}
	public int getDR() {
		return DR;
	}
	public void setDR(int dR) {
		DR = dR;
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
	public ArrayList<Item> getItemBouns() {
		return itemBouns;
	}
	public void setItemBouns(ArrayList<Item> itemBouns) {
		this.itemBouns = itemBouns;
	}
	public ArrayList<Equip> getEquipBonus() {
		return equipBonus;
	}
	public void setEquipBonus(ArrayList<Equip> equipBonus) {
		this.equipBonus = equipBonus;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
}
