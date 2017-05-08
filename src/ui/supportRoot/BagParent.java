package ui.supportRoot;
import bll.platform.*;
import bll.support.Equip;
import bllservice.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.PropertyLine;
import ui.awt.ImageButton.PropertyRow;
import ui.sceneInterface.BasicScene;
public class BagParent extends SupportParent{
	public static final int BOARDHEIGHT = 950;
	public static final int BOARDWIDTH = 950;
	public static final int CENTERLENGTH = 230;
	
	private AnchorPane board = new AnchorPane ();
	public BagParent(Equipable equipPlatform,BasicScene main){
		super(equipPlatform,main);
		ImageView background = new ImageView (new Image("Graphics/Static/Equip/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		HBoard head = new HBoard(this.platform.getPlayer1().getHeadWearingID(),this.platform.getPlayer1().getHeadWearingLevel());
		head.setLayoutX((BOARDWIDTH-CENTERLENGTH)/2);
		head.setLayoutY(0);
		board.getChildren().add(head);
		HBoard wearing = new HBoard(this.platform.getPlayer1().getWearingID(),this.platform.getPlayer1().getWeaponLevel());
		wearing.setLayoutX(0);
		wearing.setLayoutY(CENTERLENGTH+(BOARDHEIGHT-CENTERLENGTH)/2);
		board.getChildren().add(wearing);
		VBoard wings = new VBoard(this.platform.getPlayer1().getWingsID(),this.platform.getPlayer1().getWingsLevel());
		wings.setLayoutX(0);
		wings.setLayoutY(0);
		board.getChildren().add(wings);
		VBoard weapon = new VBoard(this.platform.getPlayer1().getWeaponID(),this.platform.getPlayer1().getWeaponLevel());
		weapon.setLayoutX(CENTERLENGTH+(BOARDWIDTH-CENTERLENGTH)/2);
		weapon.setLayoutY((BOARDWIDTH-CENTERLENGTH)/2);
		board.getChildren().add(weapon);
		ImageView center = new ImageView (new Image("Graphics/Static/Equip/center.gif"));
		center.setFitHeight(CENTERLENGTH);
		center.setFitWidth(CENTERLENGTH);
		center.setX(BOARDWIDTH/2-CENTERLENGTH/2);
		center.setY(BOARDHEIGHT/2-CENTERLENGTH/2);
		board.getChildren().add(center);
	}
	public class VBoard extends AnchorPane{
		public static final int VLEFTGAP = 10;
		public static final int VRIGHTGAP=VLEFTGAP;
		public static final int VTOPGAP =VLEFTGAP;
		public static final int VBOTTOMGAP=VLEFTGAP;
		public static final int VBOARDHEIGHT = CENTERLENGTH+(BOARDHEIGHT-CENTERLENGTH)/2;
		public static final int VBOARDWIDTH = (BOARDHEIGHT-CENTERLENGTH)/2;
		public static final int ICONLENGTH =VBOARDWIDTH/10;
		public static final int VMIDGAP = ICONLENGTH/3;
		public static final int PHOTOHEIGHT = ICONLENGTH*3;
		public static final int PHOTOWIDTH=ICONLENGTH*3;
		public static final int VDELTALENGTH=ICONLENGTH*2;
		public static final int BUTTONHEIGHT=ICONLENGTH*3/2;
		public static final int BUTTONWIDTH=BUTTONHEIGHT*2;
		public static final int MAXHEIGHT = 7*ICONLENGTH;
		public static final int ROWWIDTH =ICONLENGTH;
		PropertyRow HPRow,ADRow,APRow,MRRow,DRRow,MTRow,DTRow;
		ImageView Photo;
		public VBoard(int equipID,int level){
			ImageView background = new ImageView (new Image("Graphics/Static/Equip/VBackground.png"));
			background.setFitHeight(VBOARDHEIGHT);
			background.setFitWidth(VBOARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			System.out.println(equipID);
			Photo = new ImageView (new Image("Graphics/Equip/"+equipID+".png"));
			Photo.setFitHeight(PHOTOHEIGHT);
			Photo.setFitWidth(PHOTOWIDTH);
			Photo.setX(VLEFTGAP);
			Photo.setY(VTOPGAP);
			this.getChildren().add(Photo);
			addIcon();
			addRow(equipID,level);
		}
		private void addRow(int equipID,int level){
			renewEquipMax(equipID,level);
			Equip temp = Equip.getEquipByID(equipID);
			HPRow = new PropertyRow (new Image("Graphics/Static/Icon/HPLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getHP(level));
			HPRow.setLayoutX(VLEFTGAP);
			HPRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(HPRow);
			ADRow = new PropertyRow (new Image("Graphics/Static/Icon/ADLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getAD(level));
			ADRow.setLayoutX(VLEFTGAP+ICONLENGTH+VMIDGAP);
			ADRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(ADRow);
			APRow = new PropertyRow (new Image("Graphics/Static/Icon/APLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getAP(level));
			APRow.setLayoutX(VLEFTGAP+2*ICONLENGTH+2*VMIDGAP);
			APRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(APRow);
			DRRow = new PropertyRow (new Image("Graphics/Static/Icon/DRLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getDR(level));
			DRRow.setLayoutX(VLEFTGAP+3*ICONLENGTH+3*VMIDGAP);
			DRRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(DRRow);
			MRRow = new PropertyRow (new Image("Graphics/Static/Icon/MRLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getMR(level));
			MRRow.setLayoutX(VLEFTGAP+4*ICONLENGTH+4*VMIDGAP);
			MRRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(MRRow);
			DTRow = new PropertyRow (new Image("Graphics/Static/Icon/DTLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getDR(level));
			DTRow.setLayoutX(VLEFTGAP+5*ICONLENGTH+5*VMIDGAP);
			DTRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(DTRow);
			MTRow = new PropertyRow (new Image("Graphics/Static/Icon/MTLine.png"),
					ROWWIDTH,MAXHEIGHT,max,temp.getMT(level));
			MTRow.setLayoutX(VLEFTGAP+6*ICONLENGTH+6*VMIDGAP);
			MTRow.setLayoutY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH+ICONLENGTH+ICONLENGTH);
			this.getChildren().add(MTRow);
		}
		private void addIcon(){
			ImageView HP = new ImageView (new Image("Graphics/Static/Icon/HPIcon.png"));
			ImageView AD = new ImageView (new Image("Graphics/Static/Icon/ADIcon.png"));
			ImageView AP = new ImageView (new Image("Graphics/Static/Icon/APIcon.png"));
			ImageView DR = new ImageView (new Image("Graphics/Static/Icon/DRIcon.png"));
			ImageView MR = new ImageView (new Image("Graphics/Static/Icon/MRIcon.png"));
			ImageView DT = new ImageView (new Image("Graphics/Static/Icon/DTIcon.png"));
			ImageView MT = new ImageView (new Image("Graphics/Static/Icon/MTIcon.png"));
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
			HP.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			AD.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			AP.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			DR.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			MR.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			DT.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			MT.setY(VTOPGAP+PHOTOHEIGHT+VDELTALENGTH);
			HP.setX(VLEFTGAP);
			AD.setX(VLEFTGAP+ICONLENGTH+VMIDGAP);
			AP.setX(VLEFTGAP+2*ICONLENGTH+2*VMIDGAP);
			DR.setX(VLEFTGAP+3*ICONLENGTH+3*VMIDGAP);
			MR.setX(VLEFTGAP+4*ICONLENGTH+4*VMIDGAP);
			DT.setX(VLEFTGAP+5*ICONLENGTH+5*VMIDGAP);
			MT.setX(VLEFTGAP+6*ICONLENGTH+6*VMIDGAP);
			this.getChildren().addAll(HP,AD,AP,DR,MR,DT,MT);
		}
	}
	public class HBoard extends AnchorPane{
		public static final int HLEFTGAP = 10;
		public static final int HTOPGAP =HLEFTGAP;
		public static final int HRIGHTGAP = HLEFTGAP;
		public static final int HBOTTOMGAP =HLEFTGAP;
		public static final int HBOARDHEIGHT=(BOARDHEIGHT-CENTERLENGTH)/2;
		public static final int HBOARDWIDTH =BOARDWIDTH-HBOARDHEIGHT;
		public static final int ICONLENGTH = HBOARDHEIGHT/10;
		public static final int HMIDGAP =ICONLENGTH/3;
		public static final int PHOTOHEIGHT = ICONLENGTH*3;
		public static final int PHOTOWIDTH=PHOTOHEIGHT;
		public static final int HDELTALENGTH = ICONLENGTH*2;
		public static final int BUTTONHEIGHT = ICONLENGTH*3/2;
		public static final int BUTTONWIDTH = BUTTONHEIGHT*2;
		public static final int MAXLENGTH = 7*ICONLENGTH;
		public static final int LINEHEIGHT=ICONLENGTH;
		PropertyLine HPLine,ADLine,APLine,MRLine,DRLine,MTLine,DTLine;
		ImageView Photo;
		public HBoard(int equipID,int level){
			ImageView background = new ImageView (new Image("Graphics/Static/Equip/HBackground.png"));
			background.setFitHeight(HBOARDHEIGHT);
			background.setFitWidth(HBOARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			Photo = new ImageView (new Image("Graphics/Equip/"+equipID+".png"));
			Photo.setFitHeight(PHOTOHEIGHT);
			Photo.setFitWidth(PHOTOWIDTH);
			Photo.setX(HLEFTGAP);
			Photo.setY(HTOPGAP);
			this.getChildren().add(Photo);
			addIcon();
			addLine(equipID,level);
		}
		private void addLine(int equipID,int level){
			renewEquipMax(equipID,level);
			Equip temp = Equip.getEquipByID(equipID);
			HPLine = new PropertyLine (new Image("Graphics/Static/Icon/HPLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getHP(level));
			HPLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			HPLine.setLayoutY(HTOPGAP);
			this.getChildren().add(HPLine);
			ADLine = new PropertyLine (new Image("Graphics/Static/Icon/ADLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getAD(level));
			ADLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			ADLine.setLayoutY(HTOPGAP+ICONLENGTH+HMIDGAP);
			this.getChildren().add(ADLine);
			APLine = new PropertyLine (new Image("Graphics/Static/Icon/APLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getAP(level));
			APLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			APLine.setLayoutY(HTOPGAP+2*ICONLENGTH+2*HMIDGAP);
			this.getChildren().add(APLine);
			DRLine = new PropertyLine (new Image("Graphics/Static/Icon/DRLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getDR(level));
			DRLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			DRLine.setLayoutY(HTOPGAP+3*ICONLENGTH+3*HMIDGAP);
			this.getChildren().add(DRLine);
			MRLine = new PropertyLine (new Image("Graphics/Static/Icon/MRLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getMR(level));
			MRLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			MRLine.setLayoutY(HTOPGAP+4*ICONLENGTH+4*HMIDGAP);
			this.getChildren().add(MRLine);
			DTLine = new PropertyLine (new Image("Graphics/Static/Icon/DTLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getDT(level));
			DTLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			DTLine.setLayoutY(HTOPGAP+5*ICONLENGTH+5*HMIDGAP);
			this.getChildren().add(DTLine);
			MTLine = new PropertyLine (new Image("Graphics/Static/Icon/MTLine.png"),
					LINEHEIGHT,MAXLENGTH,max,temp.getMT(level));
			MTLine.setLayoutX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH+ICONLENGTH+ICONLENGTH);
			MTLine.setLayoutY(HTOPGAP+6*ICONLENGTH+6*HMIDGAP);
			this.getChildren().add(MTLine);
		}
		private void addIcon(){
			ImageView HP = new ImageView (new Image("Graphics/Static/Icon/HPIcon.png"));
			ImageView AD = new ImageView (new Image("Graphics/Static/Icon/ADIcon.png"));
			ImageView AP = new ImageView (new Image("Graphics/Static/Icon/APIcon.png"));
			ImageView DR = new ImageView (new Image("Graphics/Static/Icon/DRIcon.png"));
			ImageView MR = new ImageView (new Image("Graphics/Static/Icon/MRIcon.png"));
			ImageView DT = new ImageView (new Image("Graphics/Static/Icon/DTIcon.png"));
			ImageView MT = new ImageView (new Image("Graphics/Static/Icon/MTIcon.png"));
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
			HP.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			AD.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			AP.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			DR.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			MR.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			DT.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			MT.setX(HLEFTGAP+PHOTOWIDTH+HDELTALENGTH);
			HP.setY(HTOPGAP);
			AD.setY(HTOPGAP+ICONLENGTH+HMIDGAP);
			AP.setY(HTOPGAP+2*ICONLENGTH+2*HMIDGAP);
			DR.setY(HTOPGAP+3*ICONLENGTH+3*HMIDGAP);
			MR.setY(HTOPGAP+4*ICONLENGTH+4*HMIDGAP);
			DT.setY(HTOPGAP+5*ICONLENGTH+5*HMIDGAP);
			MT.setY(HTOPGAP+6*ICONLENGTH+6*HMIDGAP);
			this.getChildren().addAll(HP,AD,AP,DR,MR,DT,MT);
			
		}
	}
}
