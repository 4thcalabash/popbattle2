package ui.abstractStage;
import bll.support.Bonus;
import bllservice.BattlePlatform;
import javafx.scene.layout.StackPane;
import ui.Main;
import ui.sceneInterface.DramaticScene;
public abstract class BattleParent extends StackPane{
	//抽象的battlescene，在继承关系上，有PVAScene、PVPScene、NormalScene三个具体的子类
	//如果要增加PVA、PVP、Normal之外的新模式，就创建新的BattleScene的子类
	//上面一句是废话
	protected DramaticScene main;//用来从battlescene返回main，由main将控制权移交给staticscene
	protected BattlePlatform platform; //相应的后端支持。
	public BattleParent(Main main){
		this.main=main;
	}
	public void returnStatic(Bonus bonus){
		main.battleEnd(bonus);
	}
}
