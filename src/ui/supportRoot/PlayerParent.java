package ui.supportRoot;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

import bll.individual.Player;
import bll.platform.*;
import bllservice.*;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.NumberImage;
import ui.awt.ImageButton.PropertyLine;
import ui.sceneInterface.BasicScene;

public class PlayerParent extends SupportParent {
	public static final int BOARDHEIGHT = 800;
	public static final int BOARDWIDTH = 1200;
	public static final int TOPGAP = 15;
	public static final int LEFTGAP = TOPGAP;
	public static final int RIGHTGAP = TOPGAP;
	public static final int BOTTOMGAP = TOPGAP;
	public static final int PHOTOHEIGHT = (BOARDHEIGHT - TOPGAP - BOTTOMGAP) / 2;
	public static final int PHOTOWIDTH = PHOTOHEIGHT;
	public static final int TEXTHEIGHT = BOARDHEIGHT - TOPGAP - BOTTOMGAP - PHOTOHEIGHT;
	public static final int TEXTWIDTH = PHOTOWIDTH;
	public static final int DATAWIDTH = (int) ((BOARDWIDTH - LEFTGAP - RIGHTGAP - PHOTOWIDTH) * 0.9);
	public static final int DATAHEIGHT = BOARDHEIGHT - TOPGAP - BOTTOMGAP;
	public static final int DATAFOURGAP = 15;
	public static final int ICONLENGTH = (DATAHEIGHT - DATAFOURGAP * 2) * 3 / 34;
	public static final int BUTTONHEIGHT = ICONLENGTH * 2;
	public static final int BUTTONWIDTH = BUTTONHEIGHT * 2;
	public static final int DATAMIDGAP = ICONLENGTH / 3;
	public static final int DELTALENGTH = ICONLENGTH * 2;
	public static final int DATAMAXLENGTH = DATAWIDTH - ICONLENGTH - DELTALENGTH * 2;
	public static final int DATALINEHEIGHT = ICONLENGTH * 5 / 10;
	public static final int PHOTOGAP = 60;
	public static final int NUMBERHEIGHT = ICONLENGTH / 2;
	public static final int NUMBERWIDTH = NUMBERHEIGHT * 7 / 10;
	public static final int FUNCBUTTONHEIGHT = ICONLENGTH*9/10;
	public static final int FUNCBUTTONWIDTH = FUNCBUTTONHEIGHT*5/3;
	public static final int FUNCBUTTONGAP = ICONLENGTH/3;
	public static final int LEVELHEIGHT = ICONLENGTH*9/10;
	public static final int LEVELWIDTH = LEVELHEIGHT*2/3;
	public static final int INTRODUCTIONLEFTGAP = ICONLENGTH*2;
	public static final int INTRODUCTIONTOPGAP = ICONLENGTH/10;
	private int restPP = -1;
	private int hp = -1, ad = -1, ap = -1, dr = -1, mr = -1, dt = -1, mt = -1, exp = -1;
	private AnchorPane board = new AnchorPane();
	private AnchorPane data = new AnchorPane();
	private Playerable platform;
	private NumberImage HP, AD, AP, DR, MR, DT, MT, EXPnow, EXPfull, PP;
	private ImageView D;
	private int tempHPP=0,tempADP=0,tempAPP=0,tempDRP=0,tempMRP=0;
	private PropertyLine HPLine, ADLine, APLine, DRLine, MRLine, DTLine, MTLine, EXPLine;
	private final Label HPL, ADL, APL, DRL, MRL, DTL, MTL, EXPL, PPL;
	private ImageButton HPPlus, ADPlus, APPlus, DRPlus, MRPlus;
	private ImageButton confirm,reset,levelup;
	private NumberImage level;

	public PlayerParent(Playerable playerPlatform, BasicScene main) {
		super(playerPlatform, main);
		
		this.platform = playerPlatform;
		restPP = platform.getPlayer1().getPotentialPoint();
		hp = platform.getPlayer1().getHp();
		ad = platform.getPlayer1().getAd();
		ap = platform.getPlayer1().getAp();
		dr = platform.getPlayer1().getDR();
		mr = platform.getPlayer1().getMR();
		dt = platform.getPlayer1().getDT();
		mt = platform.getPlayer1().getMT();
		exp = platform.getPlayer1().getNowExp();
		ImageView background = new ImageView(new Image("Graphics/Static/Player/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		this.getChildren().add(board);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		ImageView photoBackground = new ImageView(new Image("Graphics/Static/Player/photoBackground.png"));
		photoBackground.setFitHeight(PHOTOHEIGHT);
		photoBackground.setFitWidth(PHOTOWIDTH);
		photoBackground.setX(LEFTGAP);
		photoBackground.setY(TOPGAP);
		board.getChildren().add(photoBackground);
		ImageView photo = new ImageView(
				new Image("Graphics/Player/Photo" + playerPlatform.getPlayer1().getPro() + ".png"));
		photo.setFitHeight(PHOTOHEIGHT - PHOTOGAP);
		photo.setFitWidth(PHOTOWIDTH - PHOTOGAP);
		photo.setX(LEFTGAP + PHOTOGAP / 2);
		photo.setY(TOPGAP + PHOTOGAP / 2);
		board.getChildren().add(photo);
		ImageView introduction = new ImageView(
				new Image("Graphics/Static/Player/Introduction" + playerPlatform.getPlayer1().getPro() + ".png"));
		introduction.setFitHeight(TEXTHEIGHT);
		introduction.setFitWidth(TEXTWIDTH);
		introduction.setX(LEFTGAP);
		introduction.setY(TOPGAP + PHOTOHEIGHT);
		board.getChildren().add(introduction);
		
		HPL = new Label("生命");
		HPL.setFont(Main.myFont);
		HPL.setTextFill(Main.fontColor);
		ADL = new Label("物攻");
		ADL.setFont(Main.myFont);
		ADL.setTextFill(Main.fontColor);
		APL = new Label("法强");
		APL.setFont(Main.myFont);
		APL.setTextFill(Main.fontColor);
		DRL = new Label("物抗");
		DRL.setFont(Main.myFont);
		DRL.setTextFill(Main.fontColor);
		MRL = new Label("法抗");
		MRL.setFont(Main.myFont);
		MRL.setTextFill(Main.fontColor);
		DTL = new Label("物穿");
		DTL.setFont(Main.myFont);
		DTL.setTextFill(Main.fontColor);
		MTL = new Label("法穿");
		MTL.setFont(Main.myFont);
		MTL.setTextFill(Main.fontColor);
		EXPL = new Label("经验");
		EXPL.setFont(Main.myFont);
		EXPL.setTextFill(Main.fontColor);
		PPL = new Label("潜力");
		PPL.setFont(Main.myFont);
		PPL.setTextFill(Main.fontColor);
		
		addIcon();
		addLine();
		addLabel();
		addData();
		addPlusButton();
		addFunctionButton();
		addLevel();
	}
	private void addLevel(){
		ImageView L = new ImageView (new Image("Graphics/Static/Icon/L.png"));
		L.setFitHeight(LEVELHEIGHT);
		L.setFitWidth(LEVELWIDTH);
		L.setX(INTRODUCTIONLEFTGAP);
		L.setY(TOPGAP+PHOTOHEIGHT+INTRODUCTIONTOPGAP);
		ImageView V = new ImageView (new Image("Graphics/Static/Icon/V.png"));
		V.setFitHeight(LEVELHEIGHT);
		V.setFitWidth(LEVELWIDTH);
		V.setX(L.getX()+LEVELWIDTH);
		V.setY(L.getY());
		ImageView Point = new ImageView (new Image("Graphics/Static/Icon/Point.png"));
		Point.setFitHeight(LEVELHEIGHT);
		Point.setFitWidth(LEVELWIDTH);
		Point.setX(V.getX()+LEVELWIDTH);
		Point.setY(L.getY());
		level = new NumberImage(platform.getPlayer1().getLevel());
		level.setSize(LEVELHEIGHT, LEVELWIDTH);
		level.setLayoutX(Point.getX()+LEVELWIDTH);
		level.setLayoutY(L.getY());
		board.getChildren().addAll(L,V,Point,level);
	}
	private final ButtonWorker illegalFuncWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("Cannot Confirm or Reset!!!");
		}
		
	};
	private final ButtonWorker confirmWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			platform.getPlayer1().increaseHp(tempHPP);
			platform.getPlayer1().increaseAd(tempADP);
			platform.getPlayer1().increaseAp(tempAPP);
			platform.getPlayer1().increaseDR(tempDRP);
			platform.getPlayer1().increaseMR(tempMRP);
			tempHPP=0;
			tempADP=0;
			tempAPP=0;
			tempDRP=0;
			tempMRP=0;
			checkFuncButton();
//			new Thread (Sub).start();
		}
		
	};
	private final ButtonWorker resetWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			hp = platform.getPlayer1().getHp();
			ad = platform.getPlayer1().getAd();
			ap = platform.getPlayer1().getAp();
			dr = platform.getPlayer1().getDR();
			mr = platform.getPlayer1().getMR();
			restPP = platform.getPlayer1().getPotentialPoint();
			tempHPP=tempADP=tempAPP=tempDRP=tempMRP=0;
			HPLine.refresh(hp);
			ADLine.refresh(ad);
			APLine.refresh(ap);
			DRLine.refresh(dr);
			MRLine.refresh(mr);
			HP.refresh(hp);
			AD.refresh(ad);
			AP.refresh(ap);
			DR.refresh(dr);
			MR.refresh(mr);
			PP.refresh(restPP);
			checkFuncButton();
//			new Thread (Sub).start();
		}
		
	};
	private final ButtonWorker levelupWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			platform.getPlayer1().levelUp();
			exp=platform.getPlayer1().getNowExp();
//			restPP = platform.getPlayer1().getPotentialPoint();
			restPP+=Player.BASICPOTENTIALPOINT;
			EXPLine.setMax(Player.getExpNumberToLevelUp(platform.getPlayer1().getLevel()));
			EXPLine.setNow(-1);
			EXPLine.refresh(exp);
			EXPnow.refresh(exp);
			EXPfull.refresh(Player.getExpNumberToLevelUp(platform.getPlayer1().getLevel()));
			PP.refresh(restPP);
			checkFuncButton();
//			new Thread (Sub).start();
		}
		
	};
	private final Image confirmStatic = new Image ("Graphics/Static/Player/confirmStatic.png");
	private final Image confirmEntered = new Image ("Graphics/Static/Player/confirmEntered.png");
	private final Image confirmPressed = new Image ("Graphics/Static/Player/confirmPressed.png");
	private final Image confirmIllegal = new Image ("Graphics/Static/Player/confirmIllegal.png");
	private final Image resetStatic = new Image ("Graphics/Static/Player/resetStatic.png");
	private final Image resetEntered = new Image ("Graphics/Static/Player/resetEntered.png");
	private final Image resetPressed = new Image ("Graphics/Static/Player/resetPressed.png");
	private final Image resetIllegal = new Image ("Graphics/Static/Player/resetIllegal.png");
	private final Image levelupStatic = new Image ("Graphics/Static/Player/levelupStatic.png");
	private final Image levelupEntered = new Image ("Graphics/Static/Player/levelupEntered.png");
	private final Image levelupPressed = new Image ("Graphics/Static/Player/levelupPressed.png");
	private final Image levelupIllegal = new Image ("Graphics/Static/Player/levelupIllegal.png");
	private void addFunctionButton(){
		confirm = new ImageButton (confirmStatic,confirmEntered,confirmPressed,confirmWorker);
		reset = new ImageButton(resetStatic,resetEntered,resetPressed,resetWorker);
		levelup = new ImageButton(levelupStatic,levelupEntered,levelupPressed,levelupWorker);
		confirm.setFitHeight(FUNCBUTTONHEIGHT);
		confirm.setFitWidth(FUNCBUTTONWIDTH);
		confirm.setX(DATAFOURGAP+ICONLENGTH+3*NUMBERWIDTH+ICONLENGTH+ICONLENGTH/2);
		confirm.setY(DATAFOURGAP + ICONLENGTH * 8 + DATAMIDGAP * 8);
		reset.setFitHeight(FUNCBUTTONHEIGHT);
		reset.setFitWidth(FUNCBUTTONWIDTH);
		reset.setX(confirm.getX()+FUNCBUTTONGAP+FUNCBUTTONWIDTH);
		reset.setY(confirm.getY());
		levelup.setFitHeight(FUNCBUTTONHEIGHT);
		levelup.setFitWidth(FUNCBUTTONWIDTH);
		levelup.setX(DATAWIDTH-DATAFOURGAP-FUNCBUTTONWIDTH-ICONLENGTH/5);
		levelup.setY(confirm.getY());
		checkFuncButton();
		data.getChildren().addAll(confirm,reset,levelup);
	}
	private void checkFuncButton(){
		if (restPP==platform.getPlayer1().getPotentialPoint()||platform.getPlayer1().getPotentialPoint()==0){
			//Confirm Illegal Reset Illegal
			confirm.setStaticGraphics(confirmIllegal);
			confirm.setEnteredGraphics(confirmIllegal);
			confirm.setPressedGraphics(confirmIllegal);
			confirm.setMyWorker(illegalFuncWorker);
			reset.setStaticGraphics(resetIllegal);
			reset.setEnteredGraphics(resetIllegal);
			reset.setPressedGraphics(resetIllegal);
			reset.setMyWorker(illegalFuncWorker);
		}else{
			//Confirm legal Reset legal
			confirm.setStaticGraphics(confirmStatic);
			confirm.setEnteredGraphics(confirmEntered);
			confirm.setPressedGraphics(confirmPressed);
			confirm.setMyWorker(confirmWorker);
			reset.setStaticGraphics(resetStatic);
			reset.setEnteredGraphics(resetEntered);
			reset.setPressedGraphics(resetPressed);
			reset.setMyWorker(resetWorker);
		}
		if (exp>=Player.getExpNumberToLevelUp(this.platform.getPlayer1().getLevel())){
			levelup.setStaticGraphics(levelupStatic);
			levelup.setEnteredGraphics(levelupEntered);
			levelup.setPressedGraphics(levelupPressed);
			levelup.setMyWorker(levelupWorker);
		}else{
			levelup.setStaticGraphics(levelupIllegal);
			levelup.setEnteredGraphics(levelupIllegal);
			levelup.setPressedGraphics(levelupIllegal);
			levelup.setMyWorker(illegalFuncWorker);
		}
		checkZero();
	}
	private final Runnable Sub = new Runnable (){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(PropertyLine.DELTATIME*2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("功能复位");
			checkFuncButton();
			
		}
		
	};
	private void setnull(){
		HPPlus.setMyWorker(illegalWorker);
		ADPlus.setMyWorker(illegalWorker);
		APPlus.setMyWorker(illegalWorker);
		DRPlus.setMyWorker(illegalWorker);
		MRPlus.setMyWorker(illegalWorker);
		confirm.setMyWorker(illegalFuncWorker);
		reset.setMyWorker(illegalFuncWorker);
		levelup.setMyWorker(illegalFuncWorker);
	}
	private final Image plusStatic = new Image("Graphics/Static/Player/plusStatic.png");
	private final Image plusEntered = new Image("Graphics/Static/Player/plusEntered.png");
	private final Image plusPressed = new Image("Graphics/Static/Player/plusPressed.png");
	private final Image plusIllegal = new Image("Graphics/Static/Player/plusIllegal.png");
	private final ButtonWorker HPPlusWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			setnull();
			restPP--;
			tempHPP++;
			hp+=Player.BASICHP;
			HP.refresh(hp);
			HPLine.refresh(hp);
			PP.refresh(restPP);
			new Thread (Sub).start();
			System.out.println("Have Changed Data");
			
//			checkFuncButton();
		}
		
	};
	private final ButtonWorker ADPlusWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			setnull();
			restPP--;
			tempADP++;
			ad+=Player.BASICAD;
			AD.refresh(ad);
			ADLine.refresh(ad);
			PP.refresh(restPP);
			System.out.println("Have Changed Data");
			new Thread (Sub).start();
//			checkFuncButton();
		}
		
	};
	private final ButtonWorker APPlusWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			setnull();
			restPP--;
			tempAPP++;
			ap+=Player.BASICAP;
			AP.refresh(ap);
			APLine.refresh(ap);
			PP.refresh(restPP);
			System.out.println("Have Changed Data");
			new Thread (Sub).start();
//			checkFuncButton();
		}
		
	};
	private final ButtonWorker DRPlusWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			setnull();
			restPP--;
			tempDRP++;
			dr+=Player.BASICDR;
			DR.refresh(dr);
			DRLine.refresh(dr);
			PP.refresh(restPP);
			System.out.println("Have Changed Data");
			new Thread (Sub).start();
//			checkFuncButton();
		}
		
	};
	private final ButtonWorker MRPlusWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			setnull();
			restPP--;
			tempMRP++;
			mr+=Player.BASICMR;
			MR.refresh(mr);
			MRLine.refresh(mr);
			PP.refresh(restPP);
			System.out.println("Have Changed Data");
			new Thread (Sub).start();
//			checkFuncButton();
		}
		
	};
	private void addPlusButton() {

		HPPlus = new ImageButton(plusStatic, plusEntered, plusPressed, HPPlusWorker);
		HPPlus.setFitHeight(ICONLENGTH);
		HPPlus.setFitWidth(ICONLENGTH);
		HPPlus.setX(DATAFOURGAP + ICONLENGTH * 2 + DATAMAXLENGTH + NUMBERWIDTH * 3);
		HPPlus.setY(DATAFOURGAP);
		ADPlus = new ImageButton(plusStatic, plusEntered, plusPressed,ADPlusWorker);
		ADPlus.setFitHeight(ICONLENGTH);
		ADPlus.setFitWidth(ICONLENGTH);
		ADPlus.setX(DATAFOURGAP + ICONLENGTH * 2 + DATAMAXLENGTH + NUMBERWIDTH * 3);
		ADPlus.setY(DATAFOURGAP+ICONLENGTH+DATAMIDGAP);
		APPlus = new ImageButton(plusStatic, plusEntered, plusPressed,APPlusWorker);
		APPlus.setFitHeight(ICONLENGTH);
		APPlus.setFitWidth(ICONLENGTH);
		APPlus.setX(DATAFOURGAP + ICONLENGTH * 2 + DATAMAXLENGTH + NUMBERWIDTH * 3);
		APPlus.setY(DATAFOURGAP+2*ICONLENGTH+2*DATAMIDGAP);
		DRPlus = new ImageButton(plusStatic, plusEntered, plusPressed,DRPlusWorker);
		DRPlus.setFitHeight(ICONLENGTH);
		DRPlus.setFitWidth(ICONLENGTH);
		DRPlus.setX(DATAFOURGAP + ICONLENGTH * 2 + DATAMAXLENGTH + NUMBERWIDTH * 3);
		DRPlus.setY(DATAFOURGAP+3*ICONLENGTH+3*DATAMIDGAP);
		MRPlus = new ImageButton(plusStatic, plusEntered, plusPressed,MRPlusWorker);
		MRPlus.setFitHeight(ICONLENGTH);
		MRPlus.setFitWidth(ICONLENGTH);
		MRPlus.setX(DATAFOURGAP + ICONLENGTH * 2 + DATAMAXLENGTH + NUMBERWIDTH * 3);
		MRPlus.setY(DATAFOURGAP+4*ICONLENGTH+4*DATAMIDGAP);		
		checkZero();
		data.getChildren().addAll(HPPlus,ADPlus,APPlus,DRPlus,MRPlus);
	}
	ButtonWorker illegalWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("There is no PP to Use!!!");
		}
		
	};
	private void checkZero() {
		if (restPP == 0) {
			System.out.println("Make Button Illegal");
			HPPlus.setStaticGraphics(plusIllegal);
			HPPlus.setEnteredGraphics(plusIllegal);
			HPPlus.setPressedGraphics(plusIllegal);
			HPPlus.setMyWorker(illegalWorker);
			ADPlus.setStaticGraphics(plusIllegal);
			ADPlus.setEnteredGraphics(plusIllegal);
			ADPlus.setPressedGraphics(plusIllegal);
			ADPlus.setMyWorker(illegalWorker);
			APPlus.setStaticGraphics(plusIllegal);
			APPlus.setEnteredGraphics(plusIllegal);
			APPlus.setPressedGraphics(plusIllegal);
			APPlus.setMyWorker(illegalWorker);
			DRPlus.setStaticGraphics(plusIllegal);
			DRPlus.setEnteredGraphics(plusIllegal);
			DRPlus.setPressedGraphics(plusIllegal);
			DRPlus.setMyWorker(illegalWorker);
			MRPlus.setStaticGraphics(plusIllegal);
			MRPlus.setEnteredGraphics(plusIllegal);
			MRPlus.setPressedGraphics(plusIllegal);
			MRPlus.setMyWorker(illegalWorker);
		}else{
			HPPlus.setStaticGraphics(plusStatic);
			HPPlus.setEnteredGraphics(plusEntered);
			HPPlus.setPressedGraphics(plusPressed);
			HPPlus.setMyWorker(HPPlusWorker);
			ADPlus.setStaticGraphics(plusStatic);
			ADPlus.setEnteredGraphics(plusEntered);
			ADPlus.setPressedGraphics(plusPressed);
			ADPlus.setMyWorker(ADPlusWorker);
			APPlus.setStaticGraphics(plusStatic);
			APPlus.setEnteredGraphics(plusEntered);
			APPlus.setPressedGraphics(plusPressed);
			APPlus.setMyWorker(APPlusWorker);
			DRPlus.setStaticGraphics(plusStatic);
			DRPlus.setEnteredGraphics(plusEntered);
			DRPlus.setPressedGraphics(plusPressed);
			DRPlus.setMyWorker(DRPlusWorker);
			MRPlus.setStaticGraphics(plusStatic);
			MRPlus.setEnteredGraphics(plusEntered);
			MRPlus.setPressedGraphics(plusPressed);
			MRPlus.setMyWorker(MRPlusWorker);
		}
	}

	private void addData() {
		HP = new NumberImage(this.platform.getPlayer1().getHp(), NUMBERHEIGHT, NUMBERWIDTH);
		HP.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		HP.setLayoutY(DATAFOURGAP + (ICONLENGTH - NUMBERHEIGHT) / 2);
		AD = new NumberImage(this.platform.getPlayer1().getAd(), NUMBERHEIGHT, NUMBERWIDTH);
		AD.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		AD.setLayoutY(DATAFOURGAP + ICONLENGTH + DATAMIDGAP + (ICONLENGTH - NUMBERHEIGHT) / 2);
		AP = new NumberImage(this.platform.getPlayer1().getAp(), NUMBERHEIGHT, NUMBERWIDTH);
		AP.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		AP.setLayoutY(DATAFOURGAP + ICONLENGTH * 2 + DATAMIDGAP * 2 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		DR = new NumberImage(this.platform.getPlayer1().getDR(), NUMBERHEIGHT, NUMBERWIDTH);
		DR.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		DR.setLayoutY(DATAFOURGAP + ICONLENGTH * 3 + DATAMIDGAP * 3 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		MR = new NumberImage(this.platform.getPlayer1().getMR(), NUMBERHEIGHT, NUMBERWIDTH);
		MR.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		MR.setLayoutY(DATAFOURGAP + ICONLENGTH * 4 + DATAMIDGAP * 4 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		DT = new NumberImage(this.platform.getPlayer1().getDT(), NUMBERHEIGHT, NUMBERWIDTH);
		DT.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		DT.setLayoutY(DATAFOURGAP + ICONLENGTH * 5 + DATAMIDGAP * 5 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		MT = new NumberImage(this.platform.getPlayer1().getMT(), NUMBERHEIGHT, NUMBERWIDTH);
		MT.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		MT.setLayoutY(DATAFOURGAP + ICONLENGTH * 6 + DATAMIDGAP * 6 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		EXPnow = new NumberImage(this.platform.getPlayer1().getNowExp(), NUMBERHEIGHT, NUMBERWIDTH);
		EXPnow.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH);
		EXPnow.setLayoutY(DATAFOURGAP + ICONLENGTH * 7 + DATAMIDGAP * 7 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		EXPfull = new NumberImage(Player.getExpNumberToLevelUp(this.platform.getPlayer1().getLevel()), NUMBERHEIGHT,
				NUMBERWIDTH);
		EXPfull.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH + DATAMAXLENGTH + NUMBERWIDTH * 4);
		EXPfull.setLayoutY(DATAFOURGAP + ICONLENGTH * 7 + DATAMIDGAP * 7 + (ICONLENGTH - NUMBERHEIGHT) / 2);
		D = new ImageView(new Image("Graphics/Other/Numbers/D.png"));
		D.setFitHeight(NUMBERHEIGHT);
		D.setFitWidth(NUMBERWIDTH);
		D.setX(EXPnow.getLayoutX() + 3 * NUMBERWIDTH);
		D.setY(EXPnow.getLayoutY());
		PP = new NumberImage(restPP, NUMBERHEIGHT, NUMBERWIDTH);
		PP.setLayoutX(DATAFOURGAP + ICONLENGTH + ICONLENGTH);
		PP.setLayoutY(EXPnow.getLayoutY() + ICONLENGTH + DATAMIDGAP);
		data.getChildren().addAll(HP, AD, AP, DR, MR, DT, MT, EXPnow, EXPfull, D, PP);
	}

	private void addLabel() {
		HPL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		ADL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		APL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		DRL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		MRL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		DTL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		MTL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		EXPL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		PPL.setLayoutX(DATAFOURGAP + ICONLENGTH);
		HPL.setLayoutY(DATAFOURGAP);
		ADL.setLayoutY(DATAFOURGAP + ICONLENGTH + DATAMIDGAP);
		APL.setLayoutY(DATAFOURGAP + 2 * ICONLENGTH + 2 * DATAMIDGAP);
		DRL.setLayoutY(DATAFOURGAP + 3 * ICONLENGTH + 3 * DATAMIDGAP);
		MRL.setLayoutY(DATAFOURGAP + 4 * ICONLENGTH + 4 * DATAMIDGAP);
		DTL.setLayoutY(DATAFOURGAP + 5 * ICONLENGTH + 5 * DATAMIDGAP);
		MTL.setLayoutY(DATAFOURGAP + 6 * ICONLENGTH + 6 * DATAMIDGAP);
		EXPL.setLayoutY(DATAFOURGAP + 7 * ICONLENGTH + 7 * DATAMIDGAP);
		PPL.setLayoutY(DATAFOURGAP + 8 * ICONLENGTH + 8 * DATAMIDGAP);
		HPL.setMaxSize(ICONLENGTH, ICONLENGTH);
		HPL.setMinSize(ICONLENGTH, ICONLENGTH);
		ADL.setMaxSize(ICONLENGTH, ICONLENGTH);
		ADL.setMinSize(ICONLENGTH, ICONLENGTH);
		APL.setMaxSize(ICONLENGTH, ICONLENGTH);
		APL.setMinSize(ICONLENGTH, ICONLENGTH);
		DRL.setMaxSize(ICONLENGTH, ICONLENGTH);
		DRL.setMinSize(ICONLENGTH, ICONLENGTH);
		MRL.setMaxSize(ICONLENGTH, ICONLENGTH);
		MRL.setMinSize(ICONLENGTH, ICONLENGTH);
		DTL.setMaxSize(ICONLENGTH, ICONLENGTH);
		DTL.setMinSize(ICONLENGTH, ICONLENGTH);
		MTL.setMaxSize(ICONLENGTH, ICONLENGTH);
		MTL.setMinSize(ICONLENGTH, ICONLENGTH);
		EXPL.setMaxSize(ICONLENGTH, ICONLENGTH);
		EXPL.setMinSize(ICONLENGTH, ICONLENGTH);
		PPL.setMinSize(ICONLENGTH, ICONLENGTH);
		PPL.setMaxSize(ICONLENGTH, ICONLENGTH);
		data.getChildren().addAll(HPL, ADL, APL, DRL, MRL, DTL, MTL, EXPL, PPL);

	}

	private void addLine() {
		int temp = platform.getPlayer1().getHp();
		if (temp < 10) {
			temp = 10;
		} else if (temp < 100) {
			temp = (temp / 10 + 1) * 10;
		} else {
			temp = (temp / 100 + 1) * 100;
		}
		renewMax();
		HPLine = new PropertyLine(new Image("Graphics/Static/Player/HPLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, temp,
				this.platform.getPlayer1().getHp());
		HPLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		HPLine.setLayoutY(DATAFOURGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(HPLine);
		ADLine = new PropertyLine(new Image("Graphics/Static/Player/ADLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getAd());
		ADLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		ADLine.setLayoutY(DATAFOURGAP + ICONLENGTH + DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(ADLine);
		APLine = new PropertyLine(new Image("Graphics/Static/Player/APLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getAp());
		APLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		APLine.setLayoutY(DATAFOURGAP + 2 * ICONLENGTH + 2 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(APLine);
		DRLine = new PropertyLine(new Image("Graphics/Static/Player/DRLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getDR());
		DRLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		DRLine.setLayoutY(DATAFOURGAP + 3 * ICONLENGTH + 3 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(DRLine);
		MRLine = new PropertyLine(new Image("Graphics/Static/Player/MRLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getMR());
		MRLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		MRLine.setLayoutY(DATAFOURGAP + 4 * ICONLENGTH + 4 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(MRLine);
		DTLine = new PropertyLine(new Image("Graphics/Static/Player/DTLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getDT());
		DTLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		DTLine.setLayoutY(DATAFOURGAP + 5 * ICONLENGTH + 5 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(DTLine);
		MTLine = new PropertyLine(new Image("Graphics/Static/Player/MTLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, max,
				this.platform.getPlayer1().getMT());
		MTLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		MTLine.setLayoutY(DATAFOURGAP + 6 * ICONLENGTH + 6 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(MTLine);
		platform.getPlayer1();
		EXPLine = new PropertyLine(new Image("Graphics/Static/Player/EXPLine.png"), DATALINEHEIGHT, DATAMAXLENGTH, Player.getExpNumberToLevelUp(platform.getPlayer1().getLevel()),
				this.platform.getPlayer1().getNowExp());
		EXPLine.setLayoutX(DELTALENGTH / 2 + ICONLENGTH + DATAFOURGAP);
		EXPLine.setLayoutY(DATAFOURGAP + 7 * ICONLENGTH + 7 * DATAMIDGAP + (ICONLENGTH - DATALINEHEIGHT) / 2);
		data.getChildren().add(EXPLine);
	}

	private void addIcon() {
		data.setLayoutX(BOARDWIDTH - RIGHTGAP - DATAWIDTH);
		data.setLayoutY(TOPGAP);
		board.getChildren().add(data);
		ImageView dataBackground = new ImageView(new Image("Graphics/Static/Player/dataBackground.png"));
		dataBackground.setFitHeight(DATAHEIGHT);
		dataBackground.setFitWidth(DATAWIDTH);
		dataBackground.setX(0);
		dataBackground.setY(0);
		data.getChildren().add(dataBackground);
		ImageView HPIcon = new ImageView(new Image("Graphics/Static/Icon/HPIcon.png"));
		HPIcon.setFitHeight(ICONLENGTH);
		HPIcon.setFitWidth(ICONLENGTH);
		HPIcon.setX(DATAFOURGAP);
		HPIcon.setY(DATAFOURGAP);
		data.getChildren().add(HPIcon);
		ImageView ADIcon = new ImageView(new Image("Graphics/Static/Icon/ADIcon.png"));
		ADIcon.setFitHeight(ICONLENGTH);
		ADIcon.setFitWidth(ICONLENGTH);
		ADIcon.setX(DATAFOURGAP);
		ADIcon.setY(DATAFOURGAP + ICONLENGTH + DATAMIDGAP);
		data.getChildren().add(ADIcon);
		ImageView APIcon = new ImageView(new Image("Graphics/Static/Icon/APIcon.png"));
		APIcon.setFitHeight(ICONLENGTH);
		APIcon.setFitWidth(ICONLENGTH);
		APIcon.setX(DATAFOURGAP);
		APIcon.setY(DATAFOURGAP + ICONLENGTH * 2 + DATAMIDGAP * 2);
		data.getChildren().add(APIcon);
		ImageView DRIcon = new ImageView(new Image("Graphics/Static/Icon/DRIcon.png"));
		DRIcon.setFitHeight(ICONLENGTH);
		DRIcon.setFitWidth(ICONLENGTH);
		DRIcon.setX(DATAFOURGAP);
		DRIcon.setY(DATAFOURGAP + ICONLENGTH * 3 + DATAMIDGAP * 3);
		data.getChildren().add(DRIcon);
		ImageView MRIcon = new ImageView(new Image("Graphics/Static/Icon/MRIcon.png"));
		MRIcon.setFitHeight(ICONLENGTH);
		MRIcon.setFitWidth(ICONLENGTH);
		MRIcon.setX(DATAFOURGAP);
		MRIcon.setY(DATAFOURGAP + ICONLENGTH * 4 + DATAMIDGAP * 4);
		data.getChildren().add(MRIcon);
		ImageView DTIcon = new ImageView(new Image("Graphics/Static/Icon/DTIcon.png"));
		DTIcon.setFitHeight(ICONLENGTH);
		DTIcon.setFitWidth(ICONLENGTH);
		DTIcon.setX(DATAFOURGAP);
		DTIcon.setY(DATAFOURGAP + ICONLENGTH * 5 + DATAMIDGAP * 5);
		data.getChildren().add(DTIcon);
		ImageView MTIcon = new ImageView(new Image("Graphics/Static/Icon/MTIcon.png"));
		MTIcon.setFitHeight(ICONLENGTH);
		MTIcon.setFitWidth(ICONLENGTH);
		MTIcon.setX(DATAFOURGAP);
		MTIcon.setY(DATAFOURGAP + ICONLENGTH * 6 + DATAMIDGAP * 6);
		data.getChildren().add(MTIcon);
		ImageView EXPIcon = new ImageView(new Image("Graphics/Static/Icon/EXPIcon.png"));
		EXPIcon.setFitHeight(ICONLENGTH);
		EXPIcon.setFitWidth(ICONLENGTH);
		EXPIcon.setX(DATAFOURGAP);
		EXPIcon.setY(DATAFOURGAP + ICONLENGTH * 7 + DATAMIDGAP * 7);
		data.getChildren().add(EXPIcon);
		ImageView PPIcon = new ImageView(new Image("Graphics/Static/Icon/PPIcon.png"));
		PPIcon.setFitHeight(ICONLENGTH);
		PPIcon.setFitWidth(ICONLENGTH);
		PPIcon.setX(DATAFOURGAP);
		PPIcon.setY(DATAFOURGAP + ICONLENGTH * 8 + DATAMIDGAP * 8);
		data.getChildren().add(PPIcon);
	}
}
