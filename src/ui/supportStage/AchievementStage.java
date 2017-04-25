package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class AchievementStage extends SupportStage{
	private Achievementable achievementPlatform;
	public AchievementStage(Achievementable achievementPlatform){
		this.achievementPlatform=achievementPlatform;
		//显示界面，加入监听，并修改后端信息
	}
}
