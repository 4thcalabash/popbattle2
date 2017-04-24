package datahelper;

import vo.MissionVo;
import vo.PlayerVo;

public interface DataOperator {
	public PlayerVo loadData(int index);//index是存档的编号
	public void saveData(PlayerVo playerVo,int index);//同上
	public MissionVo loadMission(int index);
}
