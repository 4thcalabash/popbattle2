package bll.support;
import java.util.ArrayList;

import dal.FileHelper;
import vo.MissionVo;
public class Mission {
	//ÿ���ؿ�����Ҫ����Ƭͷ��Ƭβ��
	//�������Ҫ�Ļ����Ͳ���һ��1֡�ĺ����ͺ���
	//startVideo��endVideoͨ��FileHelper����ȡ��
	//�ؿ�ID
	private int ID;
	//�ؿ�������Ϣ
	private String Introduction;
	private Object startVideo,endVideo;
	//ÿһ��AI��ID��
	private ArrayList <Integer> AIID;//��AI��������ȡ
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
