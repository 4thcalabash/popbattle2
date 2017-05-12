package ui.specialParent;

import bll.support.Bonus;
import bllservice.Supportable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.NumberImage;
import ui.sceneInterface.BasicScene;

public class BonusParent extends AnchorPane{
	public static final int BOARDHEIGHT = Main.SCREENHEIGHT*4/10;
	public static final int BOARDWIDTH = BOARDHEIGHT*2;
	public static final int TOPGAP = BOARDHEIGHT/15;
	public static final int BUTTOMGAP =TOPGAP*2;
	public static final int LEFTGAP = BOARDWIDTH/10;
	public static final int RIGHTGAP=LEFTGAP;
	public static final int ICONLENGTH = (BOARDHEIGHT-TOPGAP-BUTTOMGAP)/6;
	public static final int MIDGAP = ICONLENGTH/3;
	public static final int BONUSHEIGHT =ICONLENGTH*2;
	public static final int BONUSWIDTH = BONUSHEIGHT*10/3;
	public static final int DELTALENGTH = ICONLENGTH/2;
	public static final int NUMBERHEIGHT =ICONLENGTH;
	public static final int NUMBERWIDTH = NUMBERHEIGHT*4/5;
	public static final int BUTTONWIDTH = (BOARDWIDTH -LEFTGAP-RIGHTGAP-2*(ICONLENGTH+DELTALENGTH+3*NUMBERWIDTH))*8/10;
	public static final int BUTTONHEIGHT = BUTTONWIDTH/2;
	private AnchorPane board;
	private BasicScene main;
	private Bonus bonus;
	private Supportable platform;
	public BonusParent(Supportable platform,Bonus bonus,BasicScene main){
		this.bonus=bonus;
		this.main=main;
		this.platform=platform;
		init();
	}
	private void init(){
		board = new AnchorPane ();
		ImageView background = new ImageView (new Image("Graphics/Other/Bonus/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		ImageView bonusImage = new ImageView (new Image ("Graphics/Other/Bonus/bonusImage.png"));
		bonusImage.setFitHeight(BONUSHEIGHT);
		bonusImage.setFitWidth(BONUSWIDTH);
		bonusImage.setX(BOARDWIDTH/2-BONUSWIDTH/2);
		bonusImage.setY(TOPGAP);
		board.getChildren().add(bonusImage);
		addLeftItem();
		addRightItem();
		addButton();
	}
	private void addButton(){
		ImageButton button = new ImageButton (new Image("Graphics/Static/Icon/confirmStatic.png"),
				new Image ("Graphics/Static/Icon/confirmEntered.png"),new Image("Graphics/Static/Icon/confirmPressed.png"),
				new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						platform.getPlayer1().increaseExp(bonus.getExp());
						platform.getPlayer1().increaseGold(bonus.getGold());
						platform.getPlayer1().increaseSkillPoint(bonus.getSp());
						platform.getPlayer1().increaseUpGradeStoneNum(bonus.getUs());
						platform.getPlayer1().increaseEvolveStoneNum(bonus.getEs());
						main.returnStatic();
					}
			
		});
		button.setFitHeight(BUTTONHEIGHT);
		button.setFitWidth(BUTTONWIDTH);
		button.setX(BOARDWIDTH/2-BUTTONWIDTH/2);
		button.setY(BOARDHEIGHT-BUTTONHEIGHT);
		board.getChildren().add(button);
	}
	private void addRightItem(){
		int tempy = TOPGAP+BONUSHEIGHT;
		int tempx = BOARDWIDTH-RIGHTGAP-3*NUMBERWIDTH-DELTALENGTH-ICONLENGTH;
		if (bonus.getSp()!=0){
			ImageView spIcon = new ImageView (new Image ("Graphics/Static/Icon/sp.png"));
			spIcon.setFitHeight(ICONLENGTH);
			spIcon.setFitWidth(ICONLENGTH);
			spIcon.setX(tempx);
			spIcon.setY(tempy);
			NumberImage spNum = new NumberImage (bonus.getSp(),NUMBERHEIGHT,NUMBERWIDTH);
			spNum.setLayoutX(tempx+ICONLENGTH+DELTALENGTH);
			spNum.setLayoutY(spIcon.getY());
			board.getChildren().addAll(spIcon,spNum);
			tempy+=ICONLENGTH+MIDGAP;
		}
		if (bonus.getUs()!=0){
			ImageView usIcon = new ImageView (new Image("Graphics/Static/Icon/us.png"));
			usIcon.setFitHeight(ICONLENGTH);
			usIcon.setFitWidth(ICONLENGTH);
			usIcon.setX(tempx);
			usIcon.setY(tempy);
			NumberImage usNum = new NumberImage (bonus.getUs(),NUMBERHEIGHT,NUMBERWIDTH);
			usNum.setLayoutX(usIcon.getX()+ICONLENGTH+DELTALENGTH);
			usNum.setLayoutY(usIcon.getY());
			board.getChildren().addAll(usIcon,usNum);
			tempy+=ICONLENGTH+MIDGAP;
		}
		if (bonus.getEs()!=0){
			ImageView esIcon = new ImageView (new Image ("Graphics/Static/Icon/es.png"));
			esIcon.setFitHeight(ICONLENGTH);
			esIcon.setFitWidth(ICONLENGTH);
			esIcon.setX(tempx);
			esIcon.setY(tempy);
			NumberImage esNum = new NumberImage (bonus.getEs(),NUMBERHEIGHT,NUMBERWIDTH);
			esNum.setLayoutX(esIcon.getX()+ICONLENGTH+DELTALENGTH);
			esNum.setLayoutY(esIcon.getY());
			board.getChildren().addAll(esIcon,esNum);
		}
	}
	private void addLeftItem(){
		ImageView expIcon = new ImageView (new Image ("Graphics/Static/Icon/exp.png"));
		expIcon.setFitHeight(ICONLENGTH);
		expIcon.setFitWidth(ICONLENGTH);
		expIcon.setX(LEFTGAP);
		expIcon.setY(TOPGAP+BONUSHEIGHT);
		ImageView goldIcon = new ImageView (new Image("Graphics/Static/Icon/gold.png"));
		goldIcon.setFitHeight(ICONLENGTH);
		goldIcon.setFitWidth(ICONLENGTH);
		goldIcon.setX(LEFTGAP);
		goldIcon.setY(TOPGAP+BONUSHEIGHT+ICONLENGTH+MIDGAP);
		board.getChildren().addAll(expIcon,goldIcon);
		NumberImage expNum = new NumberImage(this.bonus.getExp(),NUMBERHEIGHT,NUMBERWIDTH);
		expNum.setLayoutX(expIcon.getX()+ICONLENGTH+DELTALENGTH);
		expNum.setLayoutY(expIcon.getY());
		NumberImage goldNum = new NumberImage(this.bonus.getGold(),NUMBERHEIGHT,NUMBERWIDTH);
		goldNum.setLayoutX(expNum.getLayoutX());
		goldNum.setLayoutY(expNum.getLayoutY()+NUMBERHEIGHT+MIDGAP);
		board.getChildren().addAll(expNum,goldNum);
	}
}
