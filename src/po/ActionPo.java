package po;
public class ActionPo {
	//F
	private int actionPlayerID;//����������
	private int targetPlayerID;//�������ܷ�
	private int effectValue;//Ч��ֵ �����ظ� ������Ѫ
	private int skillID;//ʹ�õļ���ID
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
