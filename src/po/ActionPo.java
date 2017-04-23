package po;
public class ActionPo {
	//F
	private int actionPlayerID;//动作发出方
	private int targetPlayerID;//动作承受方
	private int effectValue;//效果值 正：回复 负：扣血
	private int skillID;//使用的技能ID
	public int getActionPlayerID() {
		return actionPlayerID;
	}
	public void setActionPlayerID(int actionPlayerID) {
		this.actionPlayerID = actionPlayerID;
	}
	public int getTargetPlayerID() {
		return targetPlayerID;
	}
	public void setTargetPlayerID(int targetPlayerID) {
		this.targetPlayerID = targetPlayerID;
	}
	public int getEffectValue() {
		return effectValue;
	}
	public void setEffectValue(int effectValue) {
		this.effectValue = effectValue;
	}
	public int getSkillID() {
		return skillID;
	}
	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}
}
