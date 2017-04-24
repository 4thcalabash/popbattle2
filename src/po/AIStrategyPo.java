package po;

public class AIStrategyPo {
	private boolean moveStrategy;
	private DotPo dot1,dot2;
	private int actionPlayerID,targetPlayerID;
	private int skillID;
	private int skillValue;
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
	public int getSkillValue() {
		return skillValue;
	}
	public void setSkillValue(int skillValue) {
		this.skillValue = skillValue;
	}
	public boolean isMoveStrategy() {
		return moveStrategy;
	}
	public void setMoveStrategy(boolean moveStrategy) {
		this.moveStrategy = moveStrategy;
	}
	public DotPo getDot1() {
		return dot1;
	}
	public void setDot1(DotPo dot1) {
		this.dot1 = dot1;
	}
	public DotPo getDot2() {
		return dot2;
	}
	public void setDot2(DotPo dot2) {
		this.dot2 = dot2;
	}
	public int getSkillID() {
		return skillID;
	}
	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}
	
}
