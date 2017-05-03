package vo;
import java.util.ArrayList;
public class MissionVo {

	private int ID;
	private String Introduction;
	private ArrayList <Integer> AIID;
	private int startVideoID,endVideoID;
	//普通模式需要
	private int [] targetElementNum;
	private int availTime;
//	private int targetMark;
	private int availOperateTimes;
	
	public int getAvailOperateTimes() {
		return availOperateTimes;
	}
	public void setAvailOperateTimes(int availOperateTimes) {
		this.availOperateTimes = availOperateTimes;
	}
	public int getStartVideoID() {
		return startVideoID;
	}
	public void setStartVideoID(int startVideoID) {
		this.startVideoID = startVideoID;
	}
	public int getEndVideoID() {
		return endVideoID;
	}
	public void setEndVideoID(int endVideoID) {
		this.endVideoID = endVideoID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int[] getTargetElementNum() {
		return targetElementNum;
	}
	public void setTargetElementNum(int[] targetElementNum) {
		this.targetElementNum = targetElementNum;
	}

	public int getAvailTime() {
		return availTime;
	}
	public void setAvailTime(int availTime) {
		this.availTime = availTime;
	}
	public String getIntroduction() {
		return Introduction;
	}
	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}
	public ArrayList<Integer> getAIID() {
		return AIID;
	}
	public void setAIID(ArrayList<Integer> aIID) {
		AIID = aIID;
	}
}
