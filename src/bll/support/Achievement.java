package bll.support;

public class Achievement {
	public final int ITEMID;//成就统计的项目，可以是方块，等级，所有东西。根据常量来匹配
	public final int TARGETNUM;//目标数量
	private int finishedNum;//已完成数量
	private boolean isFinished;//是否已经完成
	public boolean isFinished() {
		return isFinished;
	}
	public Achievement(int ITEMID,int TARGETNUM){
		this.ITEMID=ITEMID;
		this.TARGETNUM=TARGETNUM;
	}
	public void renewprogress(int nowFinishedNum){
		this.finishedNum=nowFinishedNum;
		if (this.finishedNum>=TARGETNUM){
			isFinished=true;
		}
	}
	//aaaaa
	public int getFinishedNum() {
		return finishedNum;
	}
	public void setFinishedNum(int finishedNum) {
		this.finishedNum = finishedNum;
	}
	public int getITEMID() {
		return ITEMID;
	}
	public int getTARGETNUM() {
		return TARGETNUM;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
