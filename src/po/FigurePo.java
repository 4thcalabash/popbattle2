 package po;

public class FigurePo {
	//F
	private int hp;
	private int ad;
	private int ap;
	private int level;
	private int MR,DR;
	
	private EquipPo headWearingPo;
	private EquipPo weaponPo;
	private EquipPo wearingPo;
	private EquipPo swingsPo;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public EquipPo getHeadWearingPo() {
		return headWearingPo;
	}
	public void setHeadWearingPo(EquipPo headWearingPo) {
		this.headWearingPo = headWearingPo;
	}
	public EquipPo getWeaponPo() {
		return weaponPo;
	}
	public void setWeaponPo(EquipPo weaponPo) {
		this.weaponPo = weaponPo;
	}
	public EquipPo getWearingPo() {
		return wearingPo;
	}
	public void setWearingPo(EquipPo wearingPo) {
		this.wearingPo = wearingPo;
	}
	public EquipPo getSwingsPo() {
		return swingsPo;
	}
	public void setSwingsPo(EquipPo swingsPo) {
		this.swingsPo = swingsPo;
	}
	

}
