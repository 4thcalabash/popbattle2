package datahelper;

import vo.PlayerVo;

public interface DataOperator {
	public PlayerVo loadData(int index);//index�Ǵ浵�ı��
	public void saveData(PlayerVo playerVo,int index);//ͬ��
}
