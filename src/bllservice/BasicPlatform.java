package bllservice;
import bll.individual.Player;
import po.*;
public interface BasicPlatform {
	
	public Player getPlayer1();
	public BattlePlatform createBattle(int missionID,int [] allSkills);

	/**
	 * staticscene只需要显示人物系统即可，
	 * 所以本接口只需要负责提供给前端人物的所有信息即可。
	 * 修改装备等功能交给各个子stage完成。
	 * 每次修改后端数据都需要重新读取来更新显示
	 **/
	
}
