package ui.awt.ImageButton;

import java.util.concurrent.CountDownLatch;

import bll.matrix.Matrix;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.specialParent.GenerateParent;

public class Pool extends AnchorPane{
	private NumberImage[] poolNumber = new NumberImage [Matrix.KIND];
	private GenerateParent parent;
	private ImageButton[]skill= new ImageButton[3];
	private final Image skillStatic = new Image ("Graphics/Other/Pool/skillBackgroundStatic.png");
	private final Image skillEntered = new Image ("Graphics/Other/Pool/skillBackgroundEntered.png");
	private final Image skillPressed = new Image ("Graphics/Other/Pool/skillBackgroundPressed.png");
	private final Image skillIllegal = new Image ("Graphics/Other/Pool/skillBackgroundIllegal.png");
	private final ButtonWorker illegalWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("Illegal Ckick On Skill");
		}
		
	};
	private final ButtonWorker []skillWorker = new ButtonWorker[3];
	public void refreshElementNum(int[]elementNum){
		for (int i=0;i<3;i++){
			for (int j=0;j<2;j++){
				int temp = i*2+j;
				poolNumber[temp].refresh(elementNum[temp]);
			}
		}
		
	}
	public class Worker implements ButtonWorker{
		int ID;
		Worker(int i){
			this.ID=i;
		}
		@Override
		public void work() {
			// TODO Auto-generated method stub
			parent.setSkill(ID);
		}
		
	}
	public void renewSkill(boolean[]flag,CountDownLatch c){
		Platform.runLater(()->{
			for (int i=0;i<3;i++){
				if (flag[i]){
					skill[i].setStaticGraphics(skillStatic);
					skill[i].setEnteredGraphics(skillEntered);
					skill[i].setPressedGraphics(skillPressed);
					skill[i].setMyWorker(skillWorker[i]);
				}else{
					skill[i].setStaticGraphics(skillIllegal);
					skill[i].setEnteredGraphics(skillIllegal);
					skill[i].setPressedGraphics(skillIllegal);
					skill[i].setMyWorker(illegalWorker);
				}
			}
			System.out.println("RESET THE SKILL BUTTON SUCCEED");
			c.countDown();
		});
	}
	public Pool (int[]skillList,int[]elementNum,GenerateParent parent){
		this.parent=parent;
		ImageView background = new ImageView (new Image("Graphics/Other/Pool/poolBackground.png"));
		background.setFitHeight(GenerateParent.POOLHEIGHT);
		background.setFitWidth(GenerateParent.POOLWIDTH);
		background.setX(0);
		background.setY(0);
		this.getChildren().add(background);
		this.setMaxSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
		this.setMinSize(getMaxWidth(), getMaxHeight());
		for (int i=0;i<3;i++){
			skillWorker[i]=new Worker(skillList[i]);
//			ImageView skill = new ImageView (new Image("Graphics/Other/Pool/skillBackground.png"));
			skill[i] = new ImageButton(skillIllegal,skillIllegal,skillIllegal,illegalWorker);
			skill[i].setFitWidth(GenerateParent.POOLITEMWIDTH);
			skill[i].setFitHeight(GenerateParent.POOLITEMHEIGHT);
			skill[i].setLayoutX(GenerateParent.POOLLEFTGAP+2*GenerateParent.POOLWIDTHGAP);
			skill[i].setLayoutY(GenerateParent.POOLTOPGAP+(i+1)*GenerateParent.POOLHEIGHTGAP+i*GenerateParent.POOLITEMHEIGHT);
			this.getChildren().add(skill[i]);
			System.out.println(i);
//			System.out.println("Graphics/Skill/"+this.platform.getPlayer1().getAllSkills()[i].getID()+".png");
			ImageView skillIcon = new ImageView (new Image ("Graphics/Skill/"+skillList[i]+".png"));
			int iconLength = (int)(GenerateParent.POOLITEMHEIGHT*0.8);
			skillIcon.setFitHeight(iconLength);
			skillIcon.setFitWidth(iconLength);
			skillIcon.setX(1+skill[i].getLayoutX()+(GenerateParent.POOLITEMHEIGHT-iconLength)/2);
			skillIcon.setY(skill[i].getLayoutY()+(GenerateParent.POOLITEMHEIGHT-iconLength)/2);
			this.getChildren().add(skillIcon);
			//¼ÓÎÄ×Ö
		}
		for (int i=0;i<3;i++){
			for (int j=0;j<2;j++){
				int temp = i*2+j;
				ImageView element = new ImageView (new Image("Graphics/Other/Pool/elementBackground.png"));
				element.setFitHeight(GenerateParent.POOLITEMHEIGHT);
				element.setFitWidth(GenerateParent.POOLITEMWIDTH);
				element.setLayoutX(GenerateParent.POOLLEFTGAP+GenerateParent.POOLMIDGAP+(j+3)*GenerateParent.POOLWIDTHGAP+(j+1)*GenerateParent.POOLITEMWIDTH);
				element.setLayoutY(GenerateParent.POOLTOPGAP+(i+1)*GenerateParent.POOLHEIGHTGAP+i*GenerateParent.POOLITEMHEIGHT);
				this.getChildren().add(element);
				ImageView elementIcon = new ImageView (new Image("Graphics/Matrix/"+temp+"_100.png"));
				elementIcon.setFitHeight(GenerateParent.ELEMENTLENGTH);
				elementIcon.setFitWidth(GenerateParent.ELEMENTLENGTH);
				elementIcon.setX(element.getLayoutX()+GenerateParent.ELEMENTLENGTH*2/5);
				elementIcon.setY(element.getLayoutY()+(GenerateParent.POOLITEMHEIGHT-GenerateParent.ELEMENTLENGTH)/2);
				this.getChildren().add(elementIcon);
				poolNumber[temp] = new NumberImage(0);
				poolNumber[temp].setLayoutX(element.getLayoutX()+GenerateParent.POOLITEMWIDTH-NumberImage.WIDTH*2-GenerateParent.ELEMENTLENGTH/2);
				poolNumber[temp].setLayoutY(element.getLayoutY()+(GenerateParent.POOLITEMHEIGHT-GenerateParent.ELEMENTLENGTH)/2);
				this.getChildren().add(poolNumber[temp]);
			}
		}
	}
}
