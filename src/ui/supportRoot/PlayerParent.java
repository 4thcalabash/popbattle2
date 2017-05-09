package ui.supportRoot;

import bll.individual.Player;
import bll.platform.*;
import bllservice.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
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
	public static final int DATALINEHEIGHT = ICONLENGTH*8/10;
	public static final int PHOTOGAP = 10;
	private AnchorPane board = new AnchorPane();
	private AnchorPane data = new AnchorPane();
	private Playerable platform;
	private PropertyLine HPLine,ADLine,APLine,DRLine,MRLine,DTLine,MTLine,EXPLine;
	private Text HP,AD,AP,DR,MR,DT,MT,EXP;
	public PlayerParent(Playerable playerPlatform, BasicScene main) {
		super(playerPlatform, main);
		this.platform=playerPlatform;
		ImageView background = new ImageView(new Image("Graphics/Static/Player/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		this.getChildren().add(board);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		ImageView photoBackground = new ImageView (new Image("Graphics/Static/Player/photoBackground.png"));
		photoBackground.setFitHeight(PHOTOHEIGHT);
		photoBackground.setFitWidth(PHOTOWIDTH);
		photoBackground.setX(LEFTGAP);
		photoBackground.setY(TOPGAP);
		board.getChildren().add(photoBackground);
		ImageView photo = new ImageView(
				new Image("Graphics/Player/Photo" + playerPlatform.getPlayer1().getPro() + ".png"));
		photo.setFitHeight(PHOTOHEIGHT-PHOTOGAP);
		photo.setFitWidth(PHOTOWIDTH-PHOTOGAP);
		photo.setX(LEFTGAP+PHOTOGAP/2);
		photo.setY(TOPGAP+PHOTOGAP/2);
		board.getChildren().add(photo);
		ImageView introduction = new ImageView(
				new Image("Graphics/Static/Player/Introduction" + playerPlatform.getPlayer1().getPro() + ".png"));
		introduction.setFitHeight(TEXTHEIGHT);
		introduction.setFitWidth(TEXTWIDTH);
		introduction.setX(LEFTGAP);
		introduction.setY(TOPGAP + PHOTOHEIGHT);
		board.getChildren().add(introduction);
		addIcon();
		addLine();
		addText();
	}
	private void addText(){
		HP = new Text("生命");
		AD = new Text("物攻");
		AP = new Text("法强");
		DR = new Text("物抗");
		MR = new Text("法抗");
		DT = new Text("物穿");
		MT = new Text("法穿");
		EXP = new Text("经验");
		HP.setX(DATAFOURGAP+ICONLENGTH);
		AD.setX(DATAFOURGAP+ICONLENGTH);
		AP.setX(DATAFOURGAP+ICONLENGTH);
		DR.setX(DATAFOURGAP+ICONLENGTH);
		MR.setX(DATAFOURGAP+ICONLENGTH);
		DT.setX(DATAFOURGAP+ICONLENGTH);
		MT.setX(DATAFOURGAP+ICONLENGTH);
		EXP.setX(DATAFOURGAP+ICONLENGTH);
		HP.setY(DATAFOURGAP+ICONLENGTH/2);
		AD.setY(DATAFOURGAP+ICONLENGTH+DATAMIDGAP+ICONLENGTH/2);
		AP.setY(DATAFOURGAP+2*ICONLENGTH+2*DATAMIDGAP+ICONLENGTH/2);
		DR.setY(DATAFOURGAP+3*ICONLENGTH+3*DATAMIDGAP+ICONLENGTH/2);
		MR.setY(DATAFOURGAP+4*ICONLENGTH+4*DATAMIDGAP+ICONLENGTH/2);
		DT.setY(DATAFOURGAP+5*ICONLENGTH+5*DATAMIDGAP+ICONLENGTH/2);
		MT.setY(DATAFOURGAP+6*ICONLENGTH+6*DATAMIDGAP+ICONLENGTH/2);
		EXP.setY(DATAFOURGAP+7*ICONLENGTH+7*DATAMIDGAP+ICONLENGTH/2);
		data.getChildren().addAll(HP,AD,AP,DR,MR,DT,MT,EXP);
	}
	private void addLine(){
		renewMax();
		HPLine = new PropertyLine (new Image("Graphics/Static/Player/HPLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getHp());
		HPLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		HPLine.setLayoutY(DATAFOURGAP);
		data.getChildren().add(HPLine);
		ADLine = new PropertyLine (new Image("Graphics/Static/Player/ADLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getAd());
		ADLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		ADLine.setLayoutY(DATAFOURGAP+ICONLENGTH+DATAMIDGAP);
		data.getChildren().add(ADLine);
		APLine = new PropertyLine (new Image("Graphics/Static/Player/APLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getAp());
		APLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		APLine.setLayoutY(DATAFOURGAP+2*ICONLENGTH+2*DATAMIDGAP);
		data.getChildren().add(APLine);
		DRLine = new PropertyLine (new Image("Graphics/Static/Player/DRLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getDR());
		DRLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		DRLine.setLayoutY(DATAFOURGAP+3*ICONLENGTH+3*DATAMIDGAP);
		data.getChildren().add(DRLine);
		MRLine = new PropertyLine (new Image("Graphics/Static/Player/MRLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getMR());
		MRLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		MRLine.setLayoutY(DATAFOURGAP+4*ICONLENGTH+4*DATAMIDGAP);
		data.getChildren().add(MRLine);
		DTLine = new PropertyLine (new Image("Graphics/Static/Player/DTLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getDT());
		DTLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		DTLine.setLayoutY(DATAFOURGAP+5*ICONLENGTH+5*DATAMIDGAP);
		data.getChildren().add(DTLine);
		MTLine = new PropertyLine (new Image("Graphics/Static/Player/MTLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH,max,this.platform.getPlayer1().getMT());
		MTLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		MTLine.setLayoutY(DATAFOURGAP+6*ICONLENGTH+6*DATAMIDGAP);
		data.getChildren().add(MTLine);
		EXPLine = new PropertyLine (new Image("Graphics/Static/Player/EXPLine.png"),
				DATALINEHEIGHT,DATAMAXLENGTH+DELTALENGTH,max,this.platform.getPlayer1().getNowExp());
		EXPLine.setLayoutX(DELTALENGTH/2+ICONLENGTH+DATAFOURGAP);
		EXPLine.setLayoutY(DATAFOURGAP+7*ICONLENGTH+7*DATAMIDGAP);
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
		ImageView HPIcon = new ImageView(new Image("Graphics/Static/Player/HPIcon.png"));
		HPIcon.setFitHeight(ICONLENGTH);
		HPIcon.setFitWidth(ICONLENGTH);
		HPIcon.setX(DATAFOURGAP);
		HPIcon.setY(DATAFOURGAP);
		data.getChildren().add(HPIcon);
		ImageView ADIcon = new ImageView(new Image("Graphics/Static/Player/ADIcon.png"));
		ADIcon.setFitHeight(ICONLENGTH);
		ADIcon.setFitWidth(ICONLENGTH);
		ADIcon.setX(DATAFOURGAP);
		ADIcon.setY(DATAFOURGAP + ICONLENGTH + DATAMIDGAP);
		data.getChildren().add(ADIcon);
		ImageView APIcon = new ImageView(new Image("Graphics/Static/Player/APIcon.png"));
		APIcon.setFitHeight(ICONLENGTH);
		APIcon.setFitWidth(ICONLENGTH);
		APIcon.setX(DATAFOURGAP);
		APIcon.setY(DATAFOURGAP + ICONLENGTH * 2 + DATAMIDGAP * 2);
		data.getChildren().add(APIcon);
		ImageView DRIcon = new ImageView(new Image("Graphics/Static/Player/DRIcon.png"));
		DRIcon.setFitHeight(ICONLENGTH);
		DRIcon.setFitWidth(ICONLENGTH);
		DRIcon.setX(DATAFOURGAP);
		DRIcon.setY(DATAFOURGAP + ICONLENGTH * 3 + DATAMIDGAP * 3);
		data.getChildren().add(DRIcon);
		ImageView MRIcon = new ImageView(new Image("Graphics/Static/Player/MRIcon.png"));
		MRIcon.setFitHeight(ICONLENGTH);
		MRIcon.setFitWidth(ICONLENGTH);
		MRIcon.setX(DATAFOURGAP);
		MRIcon.setY(DATAFOURGAP + ICONLENGTH * 4 + DATAMIDGAP * 4);
		data.getChildren().add(MRIcon);
		ImageView DTIcon = new ImageView(new Image("Graphics/Static/Player/DTIcon.png"));
		DTIcon.setFitHeight(ICONLENGTH);
		DTIcon.setFitWidth(ICONLENGTH);
		DTIcon.setX(DATAFOURGAP);
		DTIcon.setY(DATAFOURGAP + ICONLENGTH * 5 + DATAMIDGAP * 5);
		data.getChildren().add(DTIcon);
		ImageView MTIcon = new ImageView(new Image("Graphics/Static/Player/MTIcon.png"));
		MTIcon.setFitHeight(ICONLENGTH);
		MTIcon.setFitWidth(ICONLENGTH);
		MTIcon.setX(DATAFOURGAP);
		MTIcon.setY(DATAFOURGAP + ICONLENGTH * 6 + DATAMIDGAP * 6);
		data.getChildren().add(MTIcon);
		ImageView EXPIcon = new ImageView (new Image("Graphics/Static/Player/EXPIcon.png"));
		EXPIcon.setFitHeight(ICONLENGTH);
		EXPIcon.setFitWidth(ICONLENGTH);
		EXPIcon.setX(DATAFOURGAP);
		EXPIcon.setY(DATAFOURGAP+ICONLENGTH*7+DATAMIDGAP*7);
		data.getChildren().add(EXPIcon);
	}
}
