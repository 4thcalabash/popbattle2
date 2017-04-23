package ui.supportStage;
import bll.platform.*;
import bllservice.*;
public class ShopStage extends SupportStage{
	private Shopable shopPlatform;
	public ShopStage (Shopable shopPlatform){
		this.shopPlatform=shopPlatform;
		//显示界面，加入监听，并修改后端信息1
	}
}
