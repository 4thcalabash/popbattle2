package ui.supportRoot;
import bll.support.Equip;
import bllservice.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.NumberImage;
import ui.awt.ImageButton.PropertyLine;
import ui.awt.ImageButton.PropertyRow;
import ui.sceneInterface.BasicScene;

public class BagParent extends SupportParent {
	public static final int BOARDHEIGHT = 950;
	public static final int BOARDWIDTH = 950;
	public static final int CENTERLENGTH = 230;
	public static final int PAUSETIME = 1000;
	public static final int NUMBERHEIGHT = 80;
	public static final int NUMBERWIDTH=NUMBERHEIGHT*2/3;
	private HBoard wearing, head;
	private VBoard wings, weapon;
	private AnchorPane board = new AnchorPane();
	private NumberImage US;
	private NumberImage ES;
	public BagParent(Equipable equipPlatform, BasicScene main) {
		super(equipPlatform, main);
		ImageView background = new ImageView(new Image("Graphics/Static/Equip/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		this.getChildren().add(board);
		head = new HBoard(this.platform.getPlayer1().getHeadWearingID(),
				this.platform.getPlayer1().getHeadWearingLevel());
		head.setLayoutX((BOARDWIDTH - CENTERLENGTH) / 2);
		head.setLayoutY(0);
		board.getChildren().add(head);
		wearing = new HBoard(this.platform.getPlayer1().getWearingID(), this.platform.getPlayer1().getWearingLevel());
		wearing.setLayoutX(0);
		wearing.setLayoutY(CENTERLENGTH + (BOARDHEIGHT - CENTERLENGTH) / 2);
		board.getChildren().add(wearing);
		wings = new VBoard(this.platform.getPlayer1().getWingsID(), this.platform.getPlayer1().getWingsLevel());
		wings.setLayoutX(0);
		wings.setLayoutY(0);
		board.getChildren().add(wings);
		weapon = new VBoard(this.platform.getPlayer1().getWeaponID(), this.platform.getPlayer1().getWeaponLevel());
		weapon.setLayoutX(CENTERLENGTH + (BOARDWIDTH - CENTERLENGTH) / 2);
		weapon.setLayoutY((BOARDWIDTH - CENTERLENGTH) / 2);
		board.getChildren().add(weapon);
		ImageView center = new ImageView(new Image("Graphics/Static/Equip/center.gif"));
		center.setFitHeight(CENTERLENGTH);
		center.setFitWidth(CENTERLENGTH);
		center.setX(BOARDWIDTH / 2 - CENTERLENGTH / 2);
		center.setY(BOARDHEIGHT / 2 - CENTERLENGTH / 2);
		addNum();
		board.getChildren().add(center);
		
	}
	private void addNum(){
		US = new NumberImage(platform.getPlayer1().getUpGradeStoneNum(),NUMBERHEIGHT,NUMBERWIDTH);
		US.setLayoutX(board.getLayoutX()+BOARDWIDTH+NUMBERHEIGHT);
		US.setLayoutY(board.getLayoutY()+BOARDHEIGHT-NUMBERHEIGHT);
		this.getChildren().add(US);
		ES = new NumberImage(platform.getPlayer1().getEvolveStoneNum(),NUMBERHEIGHT,NUMBERWIDTH);
		ES.setLayoutX(US.getLayoutX());
		ES.setLayoutY(US.getLayoutY()+NUMBERHEIGHT);
		this.getChildren().add(ES);
		ImageView logo1 = new ImageView (new Image("Graphics/Static/Icon/US.png"));
		logo1.setFitHeight(NUMBERHEIGHT);
		logo1.setFitWidth(NUMBERHEIGHT);
		logo1.setX(US.getLayoutX()-NUMBERHEIGHT);
		logo1.setY(US.getLayoutY());
		ImageView logo2 = new ImageView (new Image("Graphics/Static/Icon/ES.png"));
		logo2.setFitHeight(NUMBERHEIGHT);
		logo2.setFitWidth(NUMBERHEIGHT);
		logo2.setX(ES.getLayoutX()-NUMBERHEIGHT);
		logo2.setY(ES.getLayoutY());
		this.getChildren().addAll(logo1,logo2);
	}
	public String getVString(String input){
		String output = "";
		for (int i=0;i<input.length();i++){
			output+=input.charAt(i);
			if (i!=input.length()-1){
				output+="\n";
			}
		}
		return output;
	}
	public class VBoard extends AnchorPane {
		public static final int VLEFTGAP = 10;
		public static final int VRIGHTGAP = VLEFTGAP;
		public static final int VTOPGAP = VLEFTGAP;
		public static final int VBOTTOMGAP = VLEFTGAP;
		public static final int VBOARDHEIGHT = CENTERLENGTH + (BOARDHEIGHT - CENTERLENGTH) / 2;
		public static final int VBOARDWIDTH = (BOARDHEIGHT - CENTERLENGTH) / 2;
		public static final int ICONLENGTH = VBOARDWIDTH / 10;
		public static final int VMIDGAP = ICONLENGTH / 3;
		public static final int PHOTOHEIGHT = ICONLENGTH * 3;
		public static final int PHOTOWIDTH = ICONLENGTH * 3;
		public static final int VDELTALENGTH = ICONLENGTH * 2;
		public static final int BUTTONHEIGHT = ICONLENGTH * 3 / 2;
		public static final int BUTTONWIDTH = BUTTONHEIGHT * 2;
		public static final int MAXHEIGHT = 7 * ICONLENGTH;
		public static final int ROWWIDTH = ICONLENGTH;
		public static final int NUMBERHEIGHT = ICONLENGTH / 2;
		public static final int NUMBERWIDTH = NUMBERHEIGHT * 2 / 3;
		public static final int LEVELHEIGHT = ICONLENGTH * 2 / 3;
		public static final int LEVELWIDTH = LEVELHEIGHT * 2 / 3;
//		public static final int LABELWIDTH = 
		private PropertyRow HPRow, ADRow, APRow, MRRow, DRRow, MTRow, DTRow;
		private ImageView Photo;
		private NumberImage hp, ad, ap, dr, mr, dt, mt;
		private Equip myEquip;
		private int level;
		private NumberImage Level;
		private ImageButton levelup, evolve;
		private NumberImage USnum, ESnum;
		private Label name;
		
		public VBoard(int equipID, int level) {
			ImageView background = new ImageView(new Image("Graphics/Static/Equip/VBackground.png"));
			background.setFitHeight(VBOARDHEIGHT);
			background.setFitWidth(VBOARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			System.out.println(equipID);
			Photo = new ImageView(new Image("Graphics/Equip/" + equipID + ".png"));
			Photo.setFitHeight(PHOTOHEIGHT);
			Photo.setFitWidth(PHOTOWIDTH);
			Photo.setX(VLEFTGAP);
			Photo.setY(VTOPGAP);
			this.myEquip = Equip.getEquipByID(equipID);
			this.level = level;
			this.getChildren().add(Photo);
			addIcon();
			addRow(equipID, level);
			addData();
			addOther();
			addButton();
			addLabel();
		}
		private void addLabel(){
			name = new Label (getVString(Equip.getEquipNameByID(myEquip.getID())));
			name.setLayoutX(Photo.getX()+PHOTOWIDTH);
			name.setLayoutY(Photo.getY());
			name.setId("SkillLabel");
			this.getChildren().add(name);
		}
		private void addOther() {
			ImageView US = new ImageView(new Image("Graphics/Static/Icon/US.png"));
			US.setFitHeight(ICONLENGTH);
			US.setFitWidth(ICONLENGTH);
			US.setX(VLEFTGAP + PHOTOWIDTH + ICONLENGTH);
			US.setY(VTOPGAP + ICONLENGTH - ICONLENGTH);
			this.getChildren().add(US);
			ImageView ES = new ImageView(new Image("Graphics/Static/Icon/ES.png"));
			ES.setFitHeight(ICONLENGTH);
			ES.setFitWidth(ICONLENGTH);
			ES.setX(VLEFTGAP + PHOTOWIDTH + ICONLENGTH);
			ES.setY(VTOPGAP + ICONLENGTH + BUTTONHEIGHT + ICONLENGTH - ICONLENGTH);
			this.getChildren().add(ES);
			USnum = new NumberImage(myEquip.getLevelUpCost(level), ICONLENGTH, ICONLENGTH * 2 / 3, "a");
			USnum.setLayoutX(US.getX() + ICONLENGTH * 3 / 2+ICONLENGTH);
			USnum.setLayoutY(US.getY());
			this.getChildren().add(USnum);
			ESnum = new NumberImage(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()), ICONLENGTH, ICONLENGTH * 2 / 3, "a");
			ESnum.setLayoutX(USnum.getLayoutX());
			ESnum.setLayoutY(USnum.getLayoutY() + ICONLENGTH + BUTTONHEIGHT);
			this.getChildren().add(ESnum);
//			ImageView logo1 = new ImageView (new Image("Graphics/Static/Icon/us.png"));
//			logo1.setFitHeight(ICONLENGTH);
//			logo1.setFitWidth(ICONLENGTH);
//			logo1.setX(USnum.getLayoutX()-ICONLENGTH);
//			logo1.setY(USnum.getLayoutY());
//			this.getChildren().add(logo1);
//			ImageView logo2 = new ImageView (new Image ("Graphics/Static/Icon/es.png"));
//			logo2.setFitHeight(ICONLENGTH);
//			logo2.setFitWidth(ICONLENGTH);
//			logo2.setX(ESnum.getLayoutX()-ICONLENGTH);
//			logo2.setY(ESnum.getLayoutY());
//			this.getChildren().add(logo2);
		}

		public void setNullWorker() {
			Platform.runLater(()->{
				levelup.setMyWorker(illegalWorker);
				evolve.setMyWorker(illegalWorker);
				//levelup.setPlayAudio(false);
				//evolve.setPlayAudio(false);
			});

		}

		public void resetWorker() {
			Platform.runLater(()->{
				checkButton();
			});

		}

		private final ButtonWorker levelupWorker = new ButtonWorker() {

			@Override
			public void work() {
				// TODO Auto-generated method stub
				weapon.setNullWorker();
				wings.setNullWorker();
				wearing.setNullWorker();
				head.setNullWorker();
				platform.getPlayer1().eqiupLevelup(myEquip.getID());
				level++;
				renewProperty();

				new SetNull().start();
				checkButton();
				wearing.checkButton();
				head.checkButton();
				weapon.checkButton();
				wings.checkButton();
				
			}

		};
		private final ButtonWorker evolveWorker = new ButtonWorker() {

			@Override
			public void work() {
				// TODO Auto-generated method stub
				weapon.setNullWorker();
				wings.setNullWorker();
				wearing.setNullWorker();
				head.setNullWorker();
				platform.getPlayer1().equipEvolve(myEquip.getID());
				myEquip = Equip.getEquipByID(myEquip.getEvolveEquipID());
				Photo.setImage(new Image("Graphics/Equip/" + myEquip.getID() + ".png"));
				name.setText(getVString(Equip.getEquipNameByID(myEquip.getID())));
				level = 1;
				renewProperty();
				new SetNull().start();
				checkButton();
				wearing.checkButton();
				head.checkButton();
				weapon.checkButton();
				wings.checkButton();
				
			}

		};

		private void renewProperty() {
			HPRow.refresh(myEquip.getHP(level));
			ADRow.refresh(myEquip.getAD(level));
			APRow.refresh(myEquip.getAP(level));
			DRRow.refresh(myEquip.getDR(level));
			MRRow.refresh(myEquip.getMR(level));
			DTRow.refresh(myEquip.getDT(level));
			MTRow.refresh(myEquip.getMT(level));
			hp.refresh(myEquip.getHP(level));
			ad.refresh(myEquip.getAD(level));
			ap.refresh(myEquip.getAP(level));
			dr.refresh(myEquip.getDR(level));
			mr.refresh(myEquip.getMR(level));
			dt.refresh(myEquip.getDT(level));
			mt.refresh(myEquip.getMT(level));
			Level.refresh(level);
			US.refresh(platform.getPlayer1().getUpGradeStoneNum());
			ES.refresh(platform.getPlayer1().getEvolveStoneNum());
			// USnum.refresh(myEquip.getLevelUpCost(level));
			// ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
		}

		private void checkButton() {
			if (level == myEquip.getMAXLEVEL()) {
				levelup.setStaticGraphics(maxImage);
				levelup.setEnteredGraphics(maxImage);
				levelup.setPressedGraphics(maxImage);
				levelup.setMyWorker(illegalWorker);
				levelup.setPlayAudio(false);
				USnum.refresh(0);
				if (myEquip.getEvolveEquipID() == Equip.ID_NULL) {
					evolve.setStaticGraphics(maxImage);
					evolve.setEnteredGraphics(maxImage);
					evolve.setPressedGraphics(maxImage);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(0);
				} else if (platform.getPlayer1().getEvolveStoneNum() >= myEquip.getLevelUpCost(level)) {
					evolve.setStaticGraphics(evolveStatic);
					evolve.setEnteredGraphics(evolveStatic);
					evolve.setPressedGraphics(evolvePressed);
					evolve.setMyWorker(evolveWorker);
					evolve.setPlayAudio(true);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				} else {
					evolve.setStaticGraphics(evolveIllegal);
					evolve.setEnteredGraphics(evolveIllegal);
					evolve.setPressedGraphics(evolveIllegal);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				}
			} else {
				USnum.refresh(myEquip.getLevelUpCost(level));
				if (myEquip.getEvolveEquipID() == Equip.ID_NULL) {
					evolve.setStaticGraphics(maxImage);
					evolve.setEnteredGraphics(maxImage);
					evolve.setPressedGraphics(maxImage);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(0);
				} else {
					evolve.setStaticGraphics(evolveIllegal);
					evolve.setEnteredGraphics(evolveIllegal);
					evolve.setPressedGraphics(evolveIllegal);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				}
				if (platform.getPlayer1().getUpGradeStoneNum() >= myEquip.getLevelUpCost(level)) {
					levelup.setStaticGraphics(levelupStatic);
					levelup.setEnteredGraphics(levelupEntered);
					levelup.setPressedGraphics(levelupPressed);
					levelup.setMyWorker(levelupWorker);
					levelup.setPlayAudio(true);
				} else {
					levelup.setStaticGraphics(levelupIllegal);
					levelup.setEnteredGraphics(levelupIllegal);
					levelup.setPressedGraphics(levelupIllegal);
					levelup.setMyWorker(illegalWorker);
					levelup.setPlayAudio(false);
				}
			}
		}

		private void addButton() {
			levelup = new ImageButton(levelupStatic, levelupEntered, levelupPressed, levelupWorker);
			levelup.setFitHeight(BUTTONHEIGHT);
			levelup.setFitWidth(BUTTONWIDTH);
			levelup.setX(VLEFTGAP + PHOTOWIDTH + ICONLENGTH);
			levelup.setY(VTOPGAP + ICONLENGTH);
			evolve = new ImageButton(evolveStatic, evolveEntered, evolvePressed, evolveWorker);
			evolve.setFitHeight(BUTTONHEIGHT);
			evolve.setFitWidth(BUTTONWIDTH);
			evolve.setX(levelup.getX());
			evolve.setY(levelup.getY() + BUTTONHEIGHT + ICONLENGTH);
			checkButton();
			this.getChildren().addAll(levelup, evolve);
		}

		private void addData() {
			hp = new NumberImage(myEquip.getHP(level), NUMBERHEIGHT, NUMBERWIDTH);
			ad = new NumberImage(myEquip.getAD(level), NUMBERHEIGHT, NUMBERWIDTH);
			ap = new NumberImage(myEquip.getAP(level), NUMBERHEIGHT, NUMBERWIDTH);
			dr = new NumberImage(myEquip.getDR(level), NUMBERHEIGHT, NUMBERWIDTH);
			mr = new NumberImage(myEquip.getMR(level), NUMBERHEIGHT, NUMBERWIDTH);
			dt = new NumberImage(myEquip.getDT(level), NUMBERHEIGHT, NUMBERWIDTH);
			mt = new NumberImage(myEquip.getMT(level), NUMBERHEIGHT, NUMBERWIDTH);
			hp.setLayoutX(HPRow.getLayoutX());
			hp.setLayoutY(HPRow.getLayoutY() + MAXHEIGHT);
			ad.setLayoutX(ADRow.getLayoutX());
			ad.setLayoutY(ADRow.getLayoutY() + MAXHEIGHT);
			ap.setLayoutX(APRow.getLayoutX());
			ap.setLayoutY(APRow.getLayoutY() + MAXHEIGHT);
			mr.setLayoutX(MRRow.getLayoutX());
			mr.setLayoutY(MRRow.getLayoutY() + MAXHEIGHT);
			dr.setLayoutX(DRRow.getLayoutX());
			dr.setLayoutY(DRRow.getLayoutY() + MAXHEIGHT);
			dt.setLayoutX(DTRow.getLayoutX());
			dt.setLayoutY(DTRow.getLayoutY() + MAXHEIGHT);
			mt.setLayoutX(MTRow.getLayoutX());
			mt.setLayoutY(MTRow.getLayoutY() + MAXHEIGHT);
			this.getChildren().addAll(hp, ad, ap, dr, mr, dt, mt);
			ImageView L = new ImageView(new Image("Graphics/Static/Icon/L.png"));
			L.setFitHeight(LEVELHEIGHT);
			L.setFitWidth(LEVELWIDTH);
			L.setX(VLEFTGAP);
			L.setY(VTOPGAP + PHOTOHEIGHT);
			ImageView V = new ImageView(new Image("Graphics/Static/Icon/V.png"));
			V.setFitHeight(LEVELHEIGHT);
			V.setFitWidth(LEVELWIDTH);
			V.setX(L.getX() + LEVELWIDTH);
			V.setY(L.getY());
			ImageView Point = new ImageView(new Image("Graphics/Static/Icon/Point.png"));
			Point.setFitHeight(LEVELHEIGHT);
			Point.setFitWidth(LEVELWIDTH);
			Point.setX(V.getX() + LEVELWIDTH);
			Point.setY(L.getY());
			Level = new NumberImage(level);
			Level.setSize(LEVELHEIGHT, LEVELWIDTH);
			Level.setLayoutX(Point.getX() + LEVELWIDTH);
			Level.setLayoutY(L.getY());
			this.getChildren().addAll(L, V, Point, Level);
		}

		private void addRow(int equipID, int level) {
			renewEquipMax(equipID, level);
			Equip temp = Equip.getEquipByID(equipID);
			HPRow = new PropertyRow(new Image("Graphics/Static/Icon/HPLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getHP(level));
			HPRow.setLayoutX(VLEFTGAP);
			HPRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(HPRow);
			ADRow = new PropertyRow(new Image("Graphics/Static/Icon/ADLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getAD(level));
			ADRow.setLayoutX(VLEFTGAP + ICONLENGTH + VMIDGAP);
			ADRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(ADRow);
			APRow = new PropertyRow(new Image("Graphics/Static/Icon/APLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getAP(level));
			APRow.setLayoutX(VLEFTGAP + 2 * ICONLENGTH + 2 * VMIDGAP);
			APRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(APRow);
			DRRow = new PropertyRow(new Image("Graphics/Static/Icon/DRLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getDR(level));
			DRRow.setLayoutX(VLEFTGAP + 3 * ICONLENGTH + 3 * VMIDGAP);
			DRRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(DRRow);
			MRRow = new PropertyRow(new Image("Graphics/Static/Icon/MRLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getMR(level));
			MRRow.setLayoutX(VLEFTGAP + 4 * ICONLENGTH + 4 * VMIDGAP);
			MRRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(MRRow);
			DTRow = new PropertyRow(new Image("Graphics/Static/Icon/DTLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getDR(level));
			DTRow.setLayoutX(VLEFTGAP + 5 * ICONLENGTH + 5 * VMIDGAP);
			DTRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(DTRow);
			MTRow = new PropertyRow(new Image("Graphics/Static/Icon/MTLine.png"), ROWWIDTH, MAXHEIGHT, max,
					temp.getMT(level));
			MTRow.setLayoutX(VLEFTGAP + 6 * ICONLENGTH + 6 * VMIDGAP);
			MTRow.setLayoutY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH + ICONLENGTH + ICONLENGTH);
			this.getChildren().add(MTRow);
		}

		private void addIcon() {
			ImageView HP = new ImageView(new Image("Graphics/Static/Icon/HPIcon.png"));
			ImageView AD = new ImageView(new Image("Graphics/Static/Icon/ADIcon.png"));
			ImageView AP = new ImageView(new Image("Graphics/Static/Icon/APIcon.png"));
			ImageView DR = new ImageView(new Image("Graphics/Static/Icon/DRIcon.png"));
			ImageView MR = new ImageView(new Image("Graphics/Static/Icon/MRIcon.png"));
			ImageView DT = new ImageView(new Image("Graphics/Static/Icon/DTIcon.png"));
			ImageView MT = new ImageView(new Image("Graphics/Static/Icon/MTIcon.png"));
			HP.setFitHeight(ICONLENGTH);
			HP.setFitWidth(ICONLENGTH);
			AD.setFitHeight(ICONLENGTH);
			AD.setFitWidth(ICONLENGTH);
			AP.setFitHeight(ICONLENGTH);
			AP.setFitWidth(ICONLENGTH);
			DR.setFitHeight(ICONLENGTH);
			DR.setFitWidth(ICONLENGTH);
			MR.setFitHeight(ICONLENGTH);
			MR.setFitWidth(ICONLENGTH);
			DT.setFitHeight(ICONLENGTH);
			DT.setFitWidth(ICONLENGTH);
			MT.setFitHeight(ICONLENGTH);
			MT.setFitWidth(ICONLENGTH);
			HP.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			AD.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			AP.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			DR.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			MR.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			DT.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			MT.setY(VTOPGAP + PHOTOHEIGHT + VDELTALENGTH);
			HP.setX(VLEFTGAP);
			AD.setX(VLEFTGAP + ICONLENGTH + VMIDGAP);
			AP.setX(VLEFTGAP + 2 * ICONLENGTH + 2 * VMIDGAP);
			DR.setX(VLEFTGAP + 3 * ICONLENGTH + 3 * VMIDGAP);
			MR.setX(VLEFTGAP + 4 * ICONLENGTH + 4 * VMIDGAP);
			DT.setX(VLEFTGAP + 5 * ICONLENGTH + 5 * VMIDGAP);
			MT.setX(VLEFTGAP + 6 * ICONLENGTH + 6 * VMIDGAP);
			this.getChildren().addAll(HP, AD, AP, DR, MR, DT, MT);
		}
	}

	private final Image levelupStatic = new Image("Graphics/Static/Icon/levelupStatic.png");
	private final Image levelupEntered = new Image("Graphics/Static/Icon/levelupEntered.png");
	private final Image levelupPressed = new Image("Graphics/Static/Icon/levelupPressed.png");
	private final Image levelupIllegal = new Image("Graphics/Static/Icon/levelupIllegal.png");
	private final Image evolveStatic = new Image("Graphics/Static/Icon/evolveStatic.png");
	private final Image evolveEntered = new Image("Graphics/Static/Icon/evolveEntered.png");
	private final Image evolvePressed = new Image("Graphics/Static/Icon/evolvePressed.png");
	private final Image evolveIllegal = new Image("Graphics/Static/Icon/evolveIllegal.png");
	private final Image maxImage = new Image("Graphics/Static/Icon/max.png");
	private final ButtonWorker illegalWorker = new ButtonWorker() {

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("illegal Click!!");
		}

	};

	public class HBoard extends AnchorPane {
		public static final int HLEFTGAP = 10;
		public static final int HTOPGAP = HLEFTGAP;
		public static final int HRIGHTGAP = HLEFTGAP;
		public static final int HBOTTOMGAP = HLEFTGAP;
		public static final int HBOARDHEIGHT = (BOARDHEIGHT - CENTERLENGTH) / 2;
		public static final int HBOARDWIDTH = BOARDWIDTH - HBOARDHEIGHT;
		public static final int ICONLENGTH = HBOARDHEIGHT / 10;
		public static final int HMIDGAP = ICONLENGTH / 3;
		public static final int PHOTOHEIGHT = ICONLENGTH * 3;
		public static final int PHOTOWIDTH = PHOTOHEIGHT;
		public static final int HDELTALENGTH = ICONLENGTH * 2;
		public static final int BUTTONHEIGHT = ICONLENGTH * 13 / 10;
		public static final int BUTTONWIDTH = BUTTONHEIGHT * 2;
		public static final int MAXLENGTH = 7 * ICONLENGTH;
		public static final int LINEHEIGHT = ICONLENGTH;
		public static final int NUMBERHEIGHT = ICONLENGTH * 2 / 3;
		public static final int NUMBERWIDTH = NUMBERHEIGHT * 2 / 3;
		public static final int LEVELHEIGHT = ICONLENGTH * 8 / 9;
		public static final int LEVELWIDTH = LEVELHEIGHT * 2 / 3;
		private PropertyLine HPLine, ADLine, APLine, MRLine, DRLine, MTLine, DTLine;
		private ImageView Photo;
		private Equip myEquip;
		private int level;
		private NumberImage Level;
		private NumberImage hp, ad, ap, dr, mr, dt, mt;
		private ImageButton levelup, evolve;
		private NumberImage USnum, ESnum;
		private Label name;
		public HBoard(int equipID, int level) {
			ImageView background = new ImageView(new Image("Graphics/Static/Equip/HBackground.png"));
			background.setFitHeight(HBOARDHEIGHT);
			background.setFitWidth(HBOARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			Photo = new ImageView(new Image("Graphics/Equip/" + equipID + ".png"));
			Photo.setFitHeight(PHOTOHEIGHT);
			Photo.setFitWidth(PHOTOWIDTH);
			Photo.setX(HLEFTGAP);
			Photo.setY(HTOPGAP);
			this.myEquip = Equip.getEquipByID(equipID);
			this.level = level;
			this.getChildren().add(Photo);
			addIcon();
			addLine(equipID, level);
			addData();
			addOther();
			addButton();
			addLabel();
		}
		private void addLabel(){
			name = new Label(getVString(Equip.getEquipNameByID(myEquip.getID())));
			name.setLayoutX(Photo.getX()+PHOTOWIDTH);
			name.setLayoutY(Photo.getY());
			name.setId("SkillLabel");
			this.getChildren().add(name);
		}
		public void setNullWorker() {
			Platform.runLater(()->{
				levelup.setMyWorker(illegalWorker);
				evolve.setMyWorker(illegalWorker);
			//	levelup.setPlayAudio(false);
			//	evolve.setPlayAudio(false);
			});
		}

		public void resetWorker() {
			Platform.runLater(()->{
				checkButton();
			});
		}

		private void addOther() {
			USnum = new NumberImage(myEquip.getLevelUpCost(level), ICONLENGTH, ICONLENGTH * 2 / 3, "a");
			USnum.setLayoutX(HLEFTGAP + ICONLENGTH * 3 / 2);
			USnum.setLayoutY(HTOPGAP + PHOTOHEIGHT + LEVELHEIGHT + LEVELHEIGHT * 2 - ICONLENGTH);
			this.getChildren().add(USnum);
			ESnum = new NumberImage(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()), ICONLENGTH, ICONLENGTH * 2 / 3, "a");
			ESnum.setLayoutX(HLEFTGAP + ICONLENGTH * 3 / 2);
			ESnum.setLayoutY(USnum.getLayoutY() + ICONLENGTH + BUTTONHEIGHT);
			this.getChildren().add(ESnum);
		}

		private final ButtonWorker levelupWorker = new ButtonWorker() {

			@Override
			public void work() {
				// TODO Auto-generated method stub
				weapon.setNullWorker();
				wings.setNullWorker();
				wearing.setNullWorker();
				head.setNullWorker();
				platform.getPlayer1().eqiupLevelup(myEquip.getID());
				level++;
				renewProperty();
				new SetNull().start();
				checkButton();
				wearing.checkButton();
				head.checkButton();
				weapon.checkButton();
				wings.checkButton();
			}

		};
		private final ButtonWorker evolveWorker = new ButtonWorker() {

			@Override
			public void work() {
				// TODO Auto-generated method stub
				weapon.setNullWorker();
				wings.setNullWorker();
				wearing.setNullWorker();
				head.setNullWorker();
				platform.getPlayer1().equipEvolve(myEquip.getID());
				myEquip = Equip.getEquipByID(myEquip.getEvolveEquipID());
				Photo.setImage(new Image("Graphics/Equip/" + myEquip.getID() + ".png"));
				name.setText(getVString(Equip.getEquipNameByID(myEquip.getID())));
				level = 1;
				renewProperty();
				new SetNull().start();
				checkButton();
				wearing.checkButton();
				head.checkButton();
				weapon.checkButton();
				wings.checkButton();
			}

		};

		private void renewProperty() {
			HPLine.refresh(myEquip.getHP(level));
			ADLine.refresh(myEquip.getAD(level));
			APLine.refresh(myEquip.getAP(level));
			DRLine.refresh(myEquip.getDR(level));
			MRLine.refresh(myEquip.getMR(level));
			DTLine.refresh(myEquip.getDT(level));
			MTLine.refresh(myEquip.getMT(level));
			hp.refresh(myEquip.getHP(level));
			ad.refresh(myEquip.getAD(level));
			ap.refresh(myEquip.getAP(level));
			dr.refresh(myEquip.getDR(level));
			mr.refresh(myEquip.getMR(level));
			dt.refresh(myEquip.getDT(level));
			mt.refresh(myEquip.getMT(level));
			Level.refresh(level);
			US.refresh(platform.getPlayer1().getUpGradeStoneNum());
			ES.refresh(platform.getPlayer1().getEvolveStoneNum());
			// USnum.refresh(myEquip.getLevelUpCost(level));
			// ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
		}

		private void checkButton() {
			if (level == myEquip.getMAXLEVEL()) {
				levelup.setStaticGraphics(maxImage);
				levelup.setEnteredGraphics(maxImage);
				levelup.setPressedGraphics(maxImage);
				levelup.setMyWorker(illegalWorker);
				levelup.setPlayAudio(false);
				USnum.refresh(0);
				if (myEquip.getEvolveEquipID() == Equip.ID_NULL) {
					evolve.setStaticGraphics(maxImage);
					evolve.setEnteredGraphics(maxImage);
					evolve.setPressedGraphics(maxImage);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(0);
				} else if (platform.getPlayer1().getEvolveStoneNum() >= myEquip.getLevelUpCost(level)) {
					evolve.setStaticGraphics(evolveStatic);
					evolve.setEnteredGraphics(evolveStatic);
					evolve.setPressedGraphics(evolvePressed);
					evolve.setMyWorker(evolveWorker);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				} else {
					evolve.setStaticGraphics(evolveIllegal);
					evolve.setEnteredGraphics(evolveIllegal);
					evolve.setPressedGraphics(evolveIllegal);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				}
			} else {
				USnum.refresh(myEquip.getLevelUpCost(level));
				if (myEquip.getEvolveEquipID() == Equip.ID_NULL) {
					evolve.setStaticGraphics(maxImage);
					evolve.setEnteredGraphics(maxImage);
					evolve.setPressedGraphics(maxImage);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(0);
				} else {
					evolve.setStaticGraphics(evolveIllegal);
					evolve.setEnteredGraphics(evolveIllegal);
					evolve.setPressedGraphics(evolveIllegal);
					evolve.setMyWorker(illegalWorker);
					evolve.setPlayAudio(false);
					ESnum.refresh(myEquip.getLevelUpCost(myEquip.getMAXLEVEL()));
				}
				if (platform.getPlayer1().getUpGradeStoneNum() >= myEquip.getLevelUpCost(level)) {
					levelup.setStaticGraphics(levelupStatic);
					levelup.setEnteredGraphics(levelupEntered);
					levelup.setPressedGraphics(levelupPressed);
					levelup.setMyWorker(levelupWorker);
					levelup.setPlayAudio(true);
				} else {
					levelup.setStaticGraphics(levelupIllegal);
					levelup.setEnteredGraphics(levelupIllegal);
					levelup.setPressedGraphics(levelupIllegal);
					levelup.setMyWorker(illegalWorker);
					levelup.setPlayAudio(false);
				}
			}
		}

		private void addButton() {
			levelup = new ImageButton(levelupStatic, levelupEntered, levelupPressed, levelupWorker);
			levelup.setFitHeight(BUTTONHEIGHT);
			levelup.setFitWidth(BUTTONWIDTH);
			levelup.setX(HLEFTGAP);
			levelup.setY(HTOPGAP + PHOTOHEIGHT + LEVELHEIGHT + LEVELHEIGHT * 2);
			evolve = new ImageButton(evolveStatic, evolveEntered, evolvePressed, evolveWorker);
			evolve.setFitHeight(BUTTONHEIGHT);
			evolve.setFitWidth(BUTTONWIDTH);
			evolve.setX(levelup.getX());
			evolve.setY(levelup.getY() + BUTTONHEIGHT + ICONLENGTH);
			checkButton();
			this.getChildren().addAll(levelup, evolve);
		}

		private void addData() {
			hp = new NumberImage(myEquip.getHP(level), NUMBERHEIGHT, NUMBERWIDTH);
			ad = new NumberImage(myEquip.getAD(level), NUMBERHEIGHT, NUMBERWIDTH);
			ap = new NumberImage(myEquip.getAP(level), NUMBERHEIGHT, NUMBERWIDTH);
			mr = new NumberImage(myEquip.getMR(level), NUMBERHEIGHT, NUMBERWIDTH);
			dr = new NumberImage(myEquip.getDR(level), NUMBERHEIGHT, NUMBERWIDTH);
			mt = new NumberImage(myEquip.getMT(level), NUMBERHEIGHT, NUMBERWIDTH);
			dt = new NumberImage(myEquip.getDT(level), NUMBERHEIGHT, NUMBERWIDTH);
			hp.setLayoutX(HPLine.getLayoutX() + MAXLENGTH);
			hp.setLayoutY(HPLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			ad.setLayoutX(ADLine.getLayoutX() + MAXLENGTH);
			ad.setLayoutY(ADLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			ap.setLayoutX(APLine.getLayoutX() + MAXLENGTH);
			ap.setLayoutY(APLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			dr.setLayoutX(DRLine.getLayoutX() + MAXLENGTH);
			dr.setLayoutY(DRLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			mr.setLayoutX(MRLine.getLayoutX() + MAXLENGTH);
			mr.setLayoutY(MRLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			dt.setLayoutX(DTLine.getLayoutX() + MAXLENGTH);
			dt.setLayoutY(DTLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			mt.setLayoutX(MTLine.getLayoutX() + MAXLENGTH);
			mt.setLayoutY(MTLine.getLayoutY() + (ICONLENGTH - NUMBERHEIGHT) / 2);
			this.getChildren().addAll(hp, ad, ap, dr, mr, dt, mt);
			ImageView L = new ImageView(new Image("Graphics/Static/Icon/L.png"));
			L.setFitHeight(LEVELHEIGHT);
			L.setFitWidth(LEVELWIDTH);
			L.setX(HLEFTGAP);
			L.setY(HTOPGAP + PHOTOHEIGHT);
			ImageView V = new ImageView(new Image("Graphics/Static/Icon/V.png"));
			V.setFitHeight(LEVELHEIGHT);
			V.setFitWidth(LEVELWIDTH);
			V.setX(L.getX() + LEVELWIDTH);
			V.setY(L.getY());
			ImageView Point = new ImageView(new Image("Graphics/Static/Icon/Point.png"));
			Point.setFitHeight(LEVELHEIGHT);
			Point.setFitWidth(LEVELWIDTH);
			Point.setX(V.getX() + LEVELWIDTH);
			Point.setY(L.getY());
			Level = new NumberImage(level);
			Level.setSize(LEVELHEIGHT, LEVELWIDTH);
			Level.setLayoutX(Point.getX() + LEVELWIDTH);
			Level.setLayoutY(L.getY());
			this.getChildren().addAll(L, V, Point, Level);
		}

		private void addLine(int equipID, int level) {
			renewEquipMax(equipID, level);
			Equip temp = Equip.getEquipByID(equipID);
			HPLine = new PropertyLine(new Image("Graphics/Static/Icon/HPLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getHP(level));
			HPLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			HPLine.setLayoutY(HTOPGAP);
			this.getChildren().add(HPLine);
			ADLine = new PropertyLine(new Image("Graphics/Static/Icon/ADLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getAD(level));
			ADLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			ADLine.setLayoutY(HTOPGAP + ICONLENGTH + HMIDGAP);
			this.getChildren().add(ADLine);
			APLine = new PropertyLine(new Image("Graphics/Static/Icon/APLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getAP(level));
			APLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			APLine.setLayoutY(HTOPGAP + 2 * ICONLENGTH + 2 * HMIDGAP);
			this.getChildren().add(APLine);
			DRLine = new PropertyLine(new Image("Graphics/Static/Icon/DRLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getDR(level));
			DRLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			DRLine.setLayoutY(HTOPGAP + 3 * ICONLENGTH + 3 * HMIDGAP);
			this.getChildren().add(DRLine);
			MRLine = new PropertyLine(new Image("Graphics/Static/Icon/MRLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getMR(level));
			MRLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			MRLine.setLayoutY(HTOPGAP + 4 * ICONLENGTH + 4 * HMIDGAP);
			this.getChildren().add(MRLine);
			DTLine = new PropertyLine(new Image("Graphics/Static/Icon/DTLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getDT(level));
			DTLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			DTLine.setLayoutY(HTOPGAP + 5 * ICONLENGTH + 5 * HMIDGAP);
			this.getChildren().add(DTLine);
			MTLine = new PropertyLine(new Image("Graphics/Static/Icon/MTLine.png"), LINEHEIGHT, MAXLENGTH, max,
					temp.getMT(level));
			MTLine.setLayoutX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH + ICONLENGTH + ICONLENGTH);
			MTLine.setLayoutY(HTOPGAP + 6 * ICONLENGTH + 6 * HMIDGAP);
			this.getChildren().add(MTLine);
		}

		private void addIcon() {
			ImageView HP = new ImageView(new Image("Graphics/Static/Icon/HPIcon.png"));
			ImageView AD = new ImageView(new Image("Graphics/Static/Icon/ADIcon.png"));
			ImageView AP = new ImageView(new Image("Graphics/Static/Icon/APIcon.png"));
			ImageView DR = new ImageView(new Image("Graphics/Static/Icon/DRIcon.png"));
			ImageView MR = new ImageView(new Image("Graphics/Static/Icon/MRIcon.png"));
			ImageView DT = new ImageView(new Image("Graphics/Static/Icon/DTIcon.png"));
			ImageView MT = new ImageView(new Image("Graphics/Static/Icon/MTIcon.png"));
			HP.setFitHeight(ICONLENGTH);
			HP.setFitWidth(ICONLENGTH);
			AD.setFitHeight(ICONLENGTH);
			AD.setFitWidth(ICONLENGTH);
			AP.setFitHeight(ICONLENGTH);
			AP.setFitWidth(ICONLENGTH);
			DR.setFitHeight(ICONLENGTH);
			DR.setFitWidth(ICONLENGTH);
			MR.setFitHeight(ICONLENGTH);
			MR.setFitWidth(ICONLENGTH);
			DT.setFitHeight(ICONLENGTH);
			DT.setFitWidth(ICONLENGTH);
			MT.setFitHeight(ICONLENGTH);
			MT.setFitWidth(ICONLENGTH);
			HP.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			AD.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			AP.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			DR.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			MR.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			DT.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			MT.setX(HLEFTGAP + PHOTOWIDTH + HDELTALENGTH);
			HP.setY(HTOPGAP);
			AD.setY(HTOPGAP + ICONLENGTH + HMIDGAP);
			AP.setY(HTOPGAP + 2 * ICONLENGTH + 2 * HMIDGAP);
			DR.setY(HTOPGAP + 3 * ICONLENGTH + 3 * HMIDGAP);
			MR.setY(HTOPGAP + 4 * ICONLENGTH + 4 * HMIDGAP);
			DT.setY(HTOPGAP + 5 * ICONLENGTH + 5 * HMIDGAP);
			MT.setY(HTOPGAP + 6 * ICONLENGTH + 6 * HMIDGAP);
			this.getChildren().addAll(HP, AD, AP, DR, MR, DT, MT);
			ImageView US = new ImageView(new Image("Graphics/Static/Icon/US.png"));
			US.setFitHeight(ICONLENGTH);
			US.setFitWidth(ICONLENGTH);
			US.setX(HLEFTGAP);
			US.setY(HTOPGAP + PHOTOHEIGHT + LEVELHEIGHT + LEVELHEIGHT * 2 - ICONLENGTH);
			this.getChildren().add(US);
			ImageView ES = new ImageView(new Image("Graphics/Static/Icon/ES.png"));
			ES.setFitHeight(ICONLENGTH);
			ES.setFitWidth(ICONLENGTH);
			ES.setX(US.getX());
			ES.setY(US.getY() + BUTTONHEIGHT + ICONLENGTH);
			this.getChildren().add(ES);
		}
	}

	public class SetNull {
		public void start() {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(PAUSETIME);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("RESET!!");
					weapon.resetWorker();
					wearing.resetWorker();
					head.resetWorker();
					wings.resetWorker();
				}

			}).start();
		}
	}
}
