package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class BagStage extends SupportStage{
	private Equipable bagPlatform;
	public BagStage(Equipable bagPlatform){
		this.bagPlatform=bagPlatform;
		//显示界面，加入监听，并修改后端信息
	}

}
