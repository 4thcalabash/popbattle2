package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class BagStage extends SupportStage{
	private Bagable bagPlatform;
	public BagStage(Bagable bagPlatform){
		this.bagPlatform=bagPlatform;
		//显示界面，加入监听，并修改后端信息
	}

}
