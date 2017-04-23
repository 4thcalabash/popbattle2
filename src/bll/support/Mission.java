package bll.support;
import java.util.ArrayList;
public class Mission {
	private int ID;
	private String Introduction;
	private int AINum;
	private ArrayList <Integer> AIID;//从常量库提取
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
	public int getAINum() {
		return AINum;
	}
	public void setAINum(int aINum) {
		AINum = aINum;
	}
	public ArrayList<Integer> getAIID() {
		return AIID;
	}
	public void setAIID(ArrayList<Integer> aIID) {
		AIID = aIID;
	}
	
}
