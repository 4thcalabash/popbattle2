package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class PlayerStage extends SupportStage{
	private Playerable playerPlatform;
	public PlayerStage(Playerable playerPlatform){
		this.playerPlatform=playerPlatform;
		//显示界面，加入监听，并修改后端信息
	}
}
