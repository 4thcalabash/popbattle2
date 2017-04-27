package ui.abstractStage;
import bllservice.BattlePlatform;
import javafx.stage.Stage;
import po.*;
import ui.Main;
import ui.sceneInterface.DramaticScene;
public abstract class BattleStage extends Stage{
	//抽象的battlescene，在继承关系上，有PVAScene、PVPScene、NormalScene三个具体的子类
	//如果要增加PVA、PVP、Normal之外的新模式，就创建新的BattleScene的子类
	//上面一句是废话
	protected DramaticScene main;//用来从battlescene返回main，由main将控制权移交给staticscene
	protected BattlePlatform platform; //相应的后端支持。
	public BattleStage(Main main){
		this.main=main;
	}
	public void returnStatic(){
		main.battleEnd();
	}
}
