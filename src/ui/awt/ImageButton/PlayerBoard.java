package ui.awt.ImageButton;

import bll.individual.PaperPlayer;
import bllservice.BattlePlatform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.specialParent.GenerateParent;

public class PlayerBoard extends AnchorPane {
	private BattlePlatform platform;
	public static final int LEFTRIGHTGAP = 0;//(int)(Main.SCREENWIDTH*0.02);
	public static final int BOARDWIDTH = (Main.SCREENWIDTH-GenerateParent.POOLWIDTH*2-LEFTRIGHTGAP*2);
	public static final int BOARDHEIGHT = GenerateParent.POOLHEIGHT;
	public static final int BOARDTOPGAP = (int)(BOARDHEIGHT*0.15);
	public static final int BOARDBOTTOMGAP = (int)(BOARDHEIGHT*0.05);
	public static final int BOARDMIDGAP = (int)(BOARDHEIGHT*0.03);
	public static final int ICONLENGTH = (int)(BOARDHEIGHT-BOARDTOPGAP-BOARDBOTTOMGAP-4*BOARDMIDGAP)/5;
	public static final int LINEHEIGHT=(int)(ICONLENGTH*0.5);
	private PropertyLine P1HP,P2HP,P1AD,P2AD,P1AP,P2AP,P1DR,P2DR,P1MR,P2MR;
	public PlayerBoard(BattlePlatform platform){
		this.platform=platform;
		init();
		refreshData();
	}
	public void init(){
		ImageView boardBackground = new ImageView (new Image("Graphics/PlayerBoard/boardBackground.png"));
		boardBackground.setFitHeight(BOARDHEIGHT);
		boardBackground.setFitWidth(BOARDWIDTH);
		boardBackground.setX(0);
		boardBackground.setY(0);
		this.getChildren().add(boardBackground);
		ImageView HP = new ImageView (new Image("Graphics/PlayerBoard/HP.png"));
		HP.setFitHeight(ICONLENGTH);
		HP.setFitWidth(ICONLENGTH);
		HP.setX((BOARDWIDTH-ICONLENGTH)/2);
		HP.setY(BOARDTOPGAP);
		this.getChildren().add(HP);
		P1HP = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),this.platform.getPlayer1().getPlayer().getHp(),PropertyLine.TOWARDS_LEFT);
		P1HP.setLayoutX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH-2*PropertyLine.MAXLENGTH)/2);
		P1HP.setLayoutY(HP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P1HP);
		P2HP = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),this.platform.getPlayer2().getPlayer().getHp(),PropertyLine.TOWARDS_RIGHT);
		P2HP.setLayoutX(HP.getX()+ICONLENGTH);
		P2HP.setLayoutY(HP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P2HP);
		ImageView AD = new ImageView (new Image("Graphics/PlayerBoard/AD.png"));
		AD.setFitHeight(ICONLENGTH);
		AD.setFitWidth(ICONLENGTH);
		AD.setX(HP.getX());
		AD.setY(HP.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(AD);
		
		ImageView AP = new ImageView (new Image ("Graphics/PlayerBoard/AP.png"));
		AP.setFitHeight(ICONLENGTH);
		AP.setFitWidth(ICONLENGTH);
		AP.setX(HP.getX());
		AP.setY(AD.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(AP);
		
		ImageView DR = new ImageView (new Image("Graphics/PlayerBoard/DR.png"));
		DR.setFitHeight(ICONLENGTH);
		DR.setFitWidth(ICONLENGTH);
		DR.setX(HP.getX());
		DR.setY(AP.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(DR);
		
		ImageView MR = new ImageView (new Image("Graphics/PlayerBoard/MR.png"));
		MR.setFitHeight(ICONLENGTH);
		MR.setFitWidth(ICONLENGTH);
		MR.setX(HP.getX());
		MR.setY(DR.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(MR);
	}
	public void refreshData(){
		P1HP.refresh(this.platform.getPlayer1().getHp());
		P2HP.refresh(this.platform.getPlayer2().getHp());
	}
}
