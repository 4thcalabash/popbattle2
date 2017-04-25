package vo;
import java.util.ArrayList;
public class MissionVo {
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	private int ID;
	private String Introduction;
	private ArrayList <Integer> AIID;
	private int startVideoID,endVideoID;
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
}
