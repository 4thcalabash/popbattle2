package bll.support;
import java.util.ArrayList;

import vo.MissionVo;
public class Mission {
	//每个关卡都需要播放片头和片尾，
	//如果不需要的话，就播放一个1帧的黑屏就好了
	//startVideo和endVideo通过FileHelper来获取。
	//关卡ID
	private int ID;
	//关卡介绍信息
	private String Introduction;
	//每一个AI的ID号
	private ArrayList <Integer> AIID;//从AI常量库提取
	public Mission(MissionVo missionVo){
		this.ID=missionVo.getID();
		this.Introduction= missionVo.getIntroduction();
		this.AIID=missionVo.getAIID();
	}
	
	public int getID() {
		return ID;
	}
	public String getIntroduction() {
		return Introduction;
	}
	public ArrayList<Integer> getAIID() {
		return AIID;
	}

	
}
