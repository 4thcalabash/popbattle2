package bllservice;
import bll.individual.Player;
import po.*;
public interface BasicPlatform {
	
	public Player getPlayer1();
	public BattlePlatform createBattle(int missionID,int [] allSkills);

	/**
	 * staticsceneֻ��Ҫ��ʾ����ϵͳ���ɣ�
	 * ���Ա��ӿ�ֻ��Ҫ�����ṩ��ǰ�������������Ϣ���ɡ�
	 * �޸�װ���ȹ��ܽ���������stage��ɡ�
	 * ÿ���޸ĺ�����ݶ���Ҫ���¶�ȡ��������ʾ
	 **/
	
}
