package po;

public class AIStrategyPo {
	private boolean moveStrategy;
	private DotPo dot1,dot2;
	private int skillID;
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
