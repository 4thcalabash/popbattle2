package ui.awt.ImageButton;

import java.util.concurrent.CountDownLatch;

import bll.individual.PaperPlayer;
import bllservice.BattlePlatform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	public static final int TEXTGAP = ICONLENGTH*3/2;
	private PropertyLine P1HP,P2HP,P1AD,P2AD,P1AP,P2AP,P1DR,P2DR,P1MR,P2MR;
	private Text HP1,HP2,AD1,AD2,AP1,AP2,DR1,DR2,MR1,MR2;
	private Font myFont = new Font("ºÚÌå", ICONLENGTH*2/3);
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
		HP1 = new Text();
		HP2 = new Text();
		HP1.setText(P1HP.getNow()+"/"+P1HP.getMax());
		HP2.setText(P2HP.getNow()+"/"+P2HP.getMax());
		HP1.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2-PlayerBoard.TEXTGAP*3/2);
		HP1.setY(HP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		HP2.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2+PlayerBoard.TEXTGAP);
		HP2.setY(HP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().addAll(HP1,HP2);
		ImageView AD = new ImageView (new Image("Graphics/PlayerBoard/AD.png"));
		AD.setFitHeight(ICONLENGTH);
		AD.setFitWidth(ICONLENGTH);
		AD.setX(HP.getX());
		AD.setY(HP.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(AD);
		int maxAD = this.platform.getPlayer1().getPlayer().getAd();
		if (this.platform.getPlayer2().getPlayer().getAd()>maxAD){
			maxAD = this.platform.getPlayer2().getPlayer().getAd();
		}
		if (maxAD<10){
			maxAD=10;
		}else if (maxAD<100){
			maxAD=(maxAD/10+1)*10;
		}else{
			maxAD = (maxAD/100+1)*100;
		}
		P1AD = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxAD,PropertyLine.TOWARDS_LEFT);
		P1AD.setLayoutX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH-2*PropertyLine.MAXLENGTH)/2);
		P1AD.setLayoutY(AD.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P1AD);
		P2AD = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxAD,PropertyLine.TOWARDS_RIGHT);
		P2AD.setLayoutX(AD.getX()+ICONLENGTH);
		P2AD.setLayoutY(AD.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P2AD);
		P1AD.refresh(this.platform.getPlayer1().getPlayer().getAd());
		P2AD.refresh(this.platform.getPlayer2().getPlayer().getAd());
		AD1 = new Text();
		AD2 = new Text();
		AD1.setText(P1AD.getNow()+"");
		AD2.setText(P2AD.getNow()+"");
		AD1.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2-PlayerBoard.TEXTGAP/2);
		AD1.setY(P1AD.getLayoutY());
		AD2.setX((PlayerBoard.BOARDWIDTH+PlayerBoard.ICONLENGTH)/2+PlayerBoard.TEXTGAP/2);
		AD2.setY(P2AD.getLayoutY());
		this.getChildren().addAll(AD1,AD2);
		
		ImageView AP = new ImageView (new Image ("Graphics/PlayerBoard/AP.png"));
		AP.setFitHeight(ICONLENGTH);
		AP.setFitWidth(ICONLENGTH);
		AP.setX(HP.getX());
		AP.setY(AD.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(AP);
		int maxAP = this.platform.getPlayer1().getPlayer().getAp();
		if (this.platform.getPlayer2().getPlayer().getAp()>maxAP){
			maxAP = this.platform.getPlayer2().getPlayer().getAp();
		}
		if (maxAP<10){
			maxAP=10;
		}else if (maxAP<100){
			maxAP=(maxAP/10+1)*10;
		}else{
			maxAP = (maxAP/100+1)*100;
		}
		P1AP = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxAP,PropertyLine.TOWARDS_LEFT);
		P1AP.setLayoutX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH-2*PropertyLine.MAXLENGTH)/2);
		P1AP.setLayoutY(AP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P1AP);
		P2AP = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxAP,PropertyLine.TOWARDS_RIGHT);
		P2AP.setLayoutX(AP.getX()+ICONLENGTH);
		P2AP.setLayoutY(AP.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P2AP);
		P1AP.refresh(this.platform.getPlayer1().getPlayer().getAp());
		P2AP.refresh(this.platform.getPlayer2().getPlayer().getAp());
		AP1 = new Text();
		AP2 = new Text();
		AP1.setText(P1AP.getNow()+"");
		AP2.setText(P2AP.getNow()+"");
		AP1.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2-PlayerBoard.TEXTGAP/2);
		AP1.setY(P1AP.getLayoutY());
		AP2.setX((PlayerBoard.BOARDWIDTH+PlayerBoard.ICONLENGTH)/2+PlayerBoard.TEXTGAP/2);
		AP2.setY(P2AP.getLayoutY());
		this.getChildren().addAll(AP1,AP2);
		
		ImageView DR = new ImageView (new Image("Graphics/PlayerBoard/DR.png"));
		DR.setFitHeight(ICONLENGTH);
		DR.setFitWidth(ICONLENGTH);
		DR.setX(HP.getX());
		DR.setY(AP.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(DR);
		int maxDR = this.platform.getPlayer1().getPlayer().getDR();
		if (this.platform.getPlayer2().getPlayer().getDR()>maxDR){
			maxDR = this.platform.getPlayer2().getPlayer().getDR();
		}
		if (maxDR<10){
			maxDR=10;
		}else if (maxDR<100){
			maxDR=(maxDR/10+1)*10;
		}else{
			maxDR = (maxDR/100+1)*100;
		}
		P1DR = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxDR,PropertyLine.TOWARDS_LEFT);
		P1DR.setLayoutX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH-2*PropertyLine.MAXLENGTH)/2);
		P1DR.setLayoutY(DR.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P1DR);
		P2DR = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxDR,PropertyLine.TOWARDS_RIGHT);
		P2DR.setLayoutX(DR.getX()+ICONLENGTH);
		P2DR.setLayoutY(DR.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P2DR);
		P1DR.refresh(this.platform.getPlayer1().getPlayer().getDR());
		P2DR.refresh(this.platform.getPlayer2().getPlayer().getDR());
		DR1 = new Text();
		DR2 = new Text();
		DR1.setText(P1DR.getNow()+"");
		DR2.setText(P2DR.getNow()+"");
		DR1.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2-PlayerBoard.TEXTGAP/2);
		DR1.setY(P1DR.getLayoutY());
		DR2.setX((PlayerBoard.BOARDWIDTH+PlayerBoard.ICONLENGTH)/2+PlayerBoard.TEXTGAP/2);
		DR2.setY(P2DR.getLayoutY());
		this.getChildren().addAll(DR1,DR2);
		
		ImageView MR = new ImageView (new Image("Graphics/PlayerBoard/MR.png"));
		MR.setFitHeight(ICONLENGTH);
		MR.setFitWidth(ICONLENGTH);
		MR.setX(HP.getX());
		MR.setY(DR.getY()+ICONLENGTH+BOARDMIDGAP);
		this.getChildren().add(MR);
		int maxMR = this.platform.getPlayer1().getPlayer().getMR();
		if (this.platform.getPlayer2().getPlayer().getMR()>maxMR){
			maxMR = this.platform.getPlayer2().getPlayer().getMR();
		}
		if (maxMR<10){
			maxMR=10;
		}else if (maxMR<100){
			maxMR=(maxMR/10+1)*10;
		}else{
			maxMR = (maxMR/100+1)*100;
		}
		P1MR = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxMR,PropertyLine.TOWARDS_LEFT);
		P1MR.setLayoutX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH-2*PropertyLine.MAXLENGTH)/2);
		P1MR.setLayoutY(MR.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P1MR);
		P2MR = new PropertyLine (new Image("Graphics/PlayerBoard/HPLine.png"),maxMR,PropertyLine.TOWARDS_RIGHT);
		P2MR.setLayoutX(MR.getX()+ICONLENGTH);
		P2MR.setLayoutY(MR.getY()+(ICONLENGTH-LINEHEIGHT)/3);
		this.getChildren().add(P2MR);
		P1MR.refresh(this.platform.getPlayer1().getPlayer().getMR());
		P2MR.refresh(this.platform.getPlayer2().getPlayer().getMR());
		MR1 = new Text();
		MR2 = new Text();
		MR1.setText(P1MR.getNow()+"");
		MR2.setText(P2MR.getNow()+"");
		MR1.setX((PlayerBoard.BOARDWIDTH-PlayerBoard.ICONLENGTH)/2-PlayerBoard.TEXTGAP/2);
		MR1.setY(P1MR.getLayoutY());
		MR2.setX((PlayerBoard.BOARDWIDTH+PlayerBoard.ICONLENGTH)/2+PlayerBoard.TEXTGAP/2);
		MR2.setY(P2MR.getLayoutY());
		this.getChildren().addAll(MR1,MR2);
		
		
	}
	public void refreshData(){
		P1HP.refresh(this.platform.getPlayer1().getHp());
		P2HP.refresh(this.platform.getPlayer2().getHp());
		refreshHP();
	}
	public void refreshData(CountDownLatch c){
		P1HP.refresh(this.platform.getPlayer1().getHp(),c);
		P2HP.refresh(this.platform.getPlayer2().getHp(),c);

		refreshHP();
	}
	private void refreshHP(){
		HP1.setText(P1HP.getNow()+"/"+P1HP.getMax());
		HP2.setText(P2HP.getNow()+"/"+P2HP.getMax());
	}
}
