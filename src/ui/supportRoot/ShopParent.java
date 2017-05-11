package ui.supportRoot;
import bll.platform.*;
import bllservice.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.sceneInterface.BasicScene;
public class ShopParent extends SupportParent{
	public static final int BOARDHEIGHT = 800;
	public static final int BOARDWIDTH = 1500;
	public static final int BOARDGAP = 15;
	public static final int BOARDMIDGAP = BOARDGAP*3/2;
	public static final int TABLEGAP = 5;
	public static final int TABLEMIDGAP =2*TABLEGAP;
	public static final int CARDWIDTH = (BOARDWIDTH-2*BOARDGAP-6*TABLEGAP-2*TABLEMIDGAP-2*BOARDMIDGAP)/5;
	public static final int CARDHEIGHT = (BOARDHEIGHT-2*BOARDGAP-2*TABLEGAP);
	public static final int TABLEHEIGHT = BOARDHEIGHT-2*BOARDGAP;
	public static final int LEFTTABLEWIDTH = 2*TABLEGAP+TABLEMIDGAP+2*CARDWIDTH;
	public static final int MIDTABLEWIDTH = CARDWIDTH+2*TABLEGAP;
	public static final int RIGHTTABLEWIDTH = LEFTTABLEWIDTH;
	
	private AnchorPane board = new AnchorPane ();
	private AnchorPane leftTable,rightTable,midTable;
	//PP=Potential Point;SP=Skill Point;US=Upgrade Stone;ES = Evlove Stone
	private ProductCard EXPCard,PPCard,SPCard,USCard,ESCard;
	public class ProductCard extends AnchorPane{
		private ImageView Icon,wordBackground,background;
		private String introduction,unit;
		private int price;
		private Label word,priceLabel;
		public static final int CARDGAP = 6;
		public static final int ICONLENGTH = (CARDWIDTH-2*CARDGAP)*2/3;
		public static final int CARDMIDGAP = ICONLENGTH/3;
		public static final int WORDWIDTH = CARDWIDTH-2*CARDGAP;
		public static final int WORDHEIGHT = WORDWIDTH*4/3;
		public static final int PRICEHEIGHT = CARDMIDGAP;
		public static final int PRICEWIDTH =PRICEHEIGHT*5/2;
		public ProductCard(Image background,Image Icon,Image wordBackground,String introduction,int price,String unit){
			this.Icon = new ImageView (Icon);
			this.background = new ImageView (background);
			this.wordBackground = new ImageView (wordBackground);
			this.introduction=introduction;
			this.price=price;
			this.unit=unit;
			init();
		}
		private void init(){
			background.setFitHeight(CARDHEIGHT);
			background.setFitWidth(CARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			Icon.setFitHeight(ICONLENGTH);
			Icon.setFitWidth(ICONLENGTH);
			Icon.setX(CARDGAP);
			Icon.setY(CARDGAP);
			this.getChildren().add(Icon);
			wordBackground.setFitHeight(WORDHEIGHT);
			wordBackground.setFitWidth(WORDWIDTH);
			wordBackground.setX(CARDGAP);
			wordBackground.setY(CARDGAP+ICONLENGTH+CARDMIDGAP);
			this.getChildren().add(wordBackground);
			word = new Label(introduction);
			word.setFont(Main.myFont);
			word.setTextFill(Main.fontColor);
			word.setWrapText(true);
			word.setTextAlignment(TextAlignment.LEFT);
			word.setMaxSize(WORDWIDTH, WORDHEIGHT);
			word.setMinSize(WORDWIDTH, WORDHEIGHT);
			word.setLayoutX(CARDGAP);
			word.setLayoutY(CARDGAP+ICONLENGTH+CARDMIDGAP);
			this.getChildren().add(word);
			ImageView priceBackground = new ImageView (new Image("Graphics/Static/Shop/priceBackground.png"));
			priceBackground.setFitHeight(PRICEHEIGHT);
			priceBackground.setFitWidth(PRICEWIDTH);
			priceBackground.setX(CARDWIDTH-CARDGAP-PRICEWIDTH);
			priceBackground.setY(CARDGAP+ICONLENGTH+CARDMIDGAP-PRICEHEIGHT);
			this.getChildren().add(priceBackground);
			priceLabel = new Label(price+unit);
			priceLabel.setFont(Main.myFont);
			priceLabel.setTextFill(Main.fontColor);
			priceLabel.setId("Price");
			priceLabel.setMaxSize(PRICEWIDTH, PRICEHEIGHT);
			priceLabel.setMinSize(PRICEWIDTH, PRICEHEIGHT);
			priceLabel.setWrapText(true);
			priceLabel.setAlignment(Pos.CENTER_RIGHT);
			priceLabel.setLayoutX(CARDWIDTH-CARDGAP-PRICEWIDTH);
			priceLabel.setLayoutY(CARDGAP+ICONLENGTH+CARDMIDGAP-PRICEHEIGHT);
			this.getChildren().add(priceLabel);
		}
	}
	public ShopParent(Shopable shopPlatform,BasicScene main){
		super(shopPlatform,main);
		ImageView background = new ImageView (new Image("Graphics/Static/Shop/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		init();
	}
	private void addLeft(){
		leftTable = new AnchorPane ();
		ImageView background = new ImageView (new Image("Graphics/Static/Shop/leftBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(LEFTTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		leftTable.getChildren().add(background);
		leftTable.setLayoutX(BOARDGAP);
		leftTable.setLayoutY(BOARDGAP);
		board.getChildren().add(leftTable);
		EXPCard = new ProductCard (new Image("Graphics/Static/Shop/EXPCardBackground.png"),new Image("Graphics/Static/Icon/EXP.png"),
				new Image("Graphics/Static/Shop/EXPWordBackground.png"),"经验\n对吧\n买了可以升级啊\n你懂的",
				this.platform.getPlayer1().getShop().getExpPrice(),"金币/百");
		EXPCard.setLayoutX(TABLEGAP);
		EXPCard.setLayoutY(TABLEGAP);
		leftTable.getChildren().add(EXPCard);
		PPCard = new ProductCard (new Image("Graphics/Static/Shop/PPCardBackground.png"),new Image("Graphics/Static/Icon/PP.png"),
				new Image("Graphics/Static/Shop/PPWordBackground.png"),"潜力点\n对吧\n买了可以加属性啊\n你懂啊",
				this.platform.getPlayer1().getShop().getPPPrice(),"金币/点");
		PPCard.setLayoutX(TABLEGAP+CARDWIDTH+TABLEMIDGAP);
		PPCard.setLayoutY(TABLEGAP);
		leftTable.getChildren().add(PPCard);
	}
	private void addMid(){
		midTable = new AnchorPane ();
		ImageView background = new ImageView (new Image("Graphics/Static/Shop/midBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(MIDTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		midTable.getChildren().add(background);
		midTable.setLayoutX(BOARDGAP+LEFTTABLEWIDTH+BOARDMIDGAP);
		midTable.setLayoutY(BOARDGAP);
		board.getChildren().add(midTable);
		SPCard = new ProductCard (new Image("Graphics/Static/Shop/SPCardBackground.png"),new Image("Graphics/Static/Icon/SP.png"),
				new Image("Graphics/Static/Shop/SPWordBackground.png"),"技能点\n对吧\n买了可以学技能啊\n你懂啦",
				this.platform.getPlayer1().getShop().getSkillPointPrice(),"金币/点");
		SPCard.setLayoutX(TABLEGAP);
		SPCard.setLayoutY(TABLEGAP);
		midTable.getChildren().add(SPCard);
	}
	private void addRight(){
		rightTable = new AnchorPane ();
		ImageView background = new ImageView (new Image("Graphics/Static/Shop/rightBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(RIGHTTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		rightTable.getChildren().add(background);
		rightTable.setLayoutX(BOARDGAP+LEFTTABLEWIDTH+BOARDMIDGAP+MIDTABLEWIDTH+BOARDMIDGAP);
		rightTable.setLayoutY(BOARDGAP);
		board.getChildren().add(rightTable);
		USCard = new ProductCard (new Image("Graphics/Static/Shop/USCardBackground.png"),new Image("Graphics/Static/Icon/US.png"),
				new Image("Graphics/Static/Shop/USWordBackground.png"),"升级石\n对吧\n买了可以升级装备啊\n你懂哈",
				this.platform.getPlayer1().getShop().getUpGradeStonePrice(),"金币/个");
		USCard.setLayoutX(TABLEGAP);
		USCard.setLayoutY(TABLEGAP);
		rightTable.getChildren().add(USCard);
		ESCard = new ProductCard (new Image("Graphics/Static/Shop/ESCardBackground.png"),new Image("Graphics/Static/Icon/ES.png"),
				new Image("Graphics/Static/Shop/ESWordBackground.png"),"升阶石\n对吧\n买了可以进化装备啊\n你懂了",
				this.platform.getPlayer1().getShop().getEvolveStonePrice(),"金币/个");
		ESCard.setLayoutX(TABLEGAP+CARDWIDTH+TABLEMIDGAP);
		ESCard.setLayoutY(TABLEGAP);
		rightTable.getChildren().add(ESCard);
	}
	private void init(){
		addLeft();
		addMid();
		addRight();
	}
}
