package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class SkillStage extends SupportStage{
	private Skillable skillStage;
	public SkillStage(Skillable skillStage){
		this.skillStage=skillStage;
		//显示界面，加入监听，并修改后端信息
	}
}
