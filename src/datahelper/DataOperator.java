package datahelper;

import bll.individual.PaperPlayer;
import vo.MissionVo;
import vo.PlayerVo;

public interface DataOperator {
	public PlayerVo loadData(int index);//index�Ǵ浵�ı��
	public void saveData(PlayerVo playerVo,int index);//ͬ��
	public MissionVo loadMission(int index);
	public PaperPlayer loadAI(int ID);
}
