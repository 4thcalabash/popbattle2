package ui.specialParent;

import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.sceneInterface.BasicScene;

public class BattleFailedParent extends AnchorPane{
	public static final int BOARDHEIGHT = Main.SCREENHEIGHT/6;
	public static final int BOARDWIDTH = BOARDHEIGHT*2;
	public static final int TOPGAP = BOARDHEIGHT/10;
	public static final int BUTTOMGAP = BOARDHEIGHT/5;
	public static final int LEFTGAP = BOARDWIDTH/20;
	public static final int RIGHTGAP = LEFTGAP;
	public static final int IMAGEHEIGHT = 0;
	private BasicScene main;
	public BattleFailedParent(BasicScene main){
		this.main=main;
		init();
	}
	private void init(){
		
	}
}
