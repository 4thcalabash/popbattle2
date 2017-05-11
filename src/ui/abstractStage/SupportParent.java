package ui.abstractStage;

import bll.support.Equip;
import bllservice.Playerable;
import bllservice.Supportable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import ui.Main;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;

public abstract class SupportParent extends AnchorPane {
	protected Supportable platform;
	protected BasicScene main;
	public static final int BACKHEIGHT = 120;
	public static final int BACKWIDTH =BACKHEIGHT*4/3;
	protected int max=-1;
	public SupportParent(Supportable supportPlatform, BasicScene main) {
		this.platform = supportPlatform;
		this.main = main;
		ImageButton backButton = new ImageButton(new Image("Graphics/Button/returnStatic.png"),
				new Image("Graphics/Button/returnEntered.png"), new Image("Graphics/Button/returnPressed.png"),
				new ButtonWorker() {

					@Override
					public void work() {
						// TODO Auto-generated method stub
						returnStatic();

					}

				});
		backButton.setFitHeight(BACKHEIGHT);
		backButton.setFitWidth(BACKWIDTH);
		backButton.setX(0);
		backButton.setY(Main.SCREENHEIGHT-BACKHEIGHT);
		this.getChildren().add(backButton);
	}
	protected void renewEquipMax(int equipID,int level){
		max=-1;
		Equip temp =Equip.getEquipByID(equipID);
		if (temp.getHP(level)>max){
			max=temp.getHP(level);
		}
		if (temp.getAD(level)>max){
			max=temp.getAD(level);
		}
		if (temp.getAP(level)>max){
			max=temp.getAP(level);
		}
		if (temp.getDR(level)>max){
			max=temp.getDR(level);
		}
		if (temp.getMR(level)>max){
			max=temp.getMR(level);
		}
		if (temp.getDT(level)>max){
			max=temp.getDR(level);
		}
		if (temp.getMT(level)>max){
			max=temp.getMT(level);
		}
		max*=2;
		if (max<10){
			max=10;
		}else if (max<100){
			max=(max/10+1)*10;
		}else{
			max=(max/100+1)*100;
		}
	}
	protected void renewMax(){
		max=-1;
		if (this.platform.getPlayer1().getAd()>max){
			max=this.platform.getPlayer1().getAd();
		}
		if (this.platform.getPlayer1().getAp()>max){
			max=this.platform.getPlayer1().getAp();
		}
		if (this.platform.getPlayer1().getDR()>max){
			max = this.platform.getPlayer1().getDR();
		}
		if (this.platform.getPlayer1().getMR()>max){
			max = this.platform.getPlayer1().getMR();
		}
		if (this.platform.getPlayer1().getDT()>max){
			max = this.platform.getPlayer1().getDT();
		}
		if (this.platform.getPlayer1().getMT()>max){
			max = this.platform.getPlayer1().getMT();
		}
		if (max<10){
			max=10;
		}else if (max<100){
			max=(max/10+1)*10;
		}else if (max<1000){
			max=(max/100+1)*100;
		}
		System.out.println("The Max IS"+max);
	}
	public void returnStatic() {
		this.main.returnStatic();
	}
}
