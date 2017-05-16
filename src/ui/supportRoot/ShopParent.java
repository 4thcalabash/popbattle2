package ui.supportRoot;

import bll.support.Shop;
import bllservice.Shopable;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.NumberImage;
import ui.sceneInterface.BasicScene;

public class ShopParent extends SupportParent {
	public static final int EXPFLAG = 1000;
	public static final int PPFLAG = 1001;
	public static final int SPFLAG = 1002;
	public static final int USFLAG = 1003;
	public static final int ESFLAG = 1004;
	public static final int BOARDHEIGHT = 800;
	public static final int BOARDWIDTH = 1500;
	public static final int BOARDGAP = 15;
	public static final int BOARDMIDGAP = BOARDGAP * 3 / 2;
	public static final int TABLEGAP = 5;
	public static final int TABLEMIDGAP = 2 * TABLEGAP;
	public static final int CARDWIDTH = (BOARDWIDTH - 2 * BOARDGAP - 6 * TABLEGAP - 2 * TABLEMIDGAP - 2 * BOARDMIDGAP)
			/ 5;
	public static final int CARDHEIGHT = (BOARDHEIGHT - 2 * BOARDGAP - 2 * TABLEGAP);
	public static final int TABLEHEIGHT = BOARDHEIGHT - 2 * BOARDGAP;
	public static final int LEFTTABLEWIDTH = 2 * TABLEGAP + TABLEMIDGAP + 2 * CARDWIDTH;
	public static final int MIDTABLEWIDTH = CARDWIDTH + 2 * TABLEGAP;
	public static final int RIGHTTABLEWIDTH = LEFTTABLEWIDTH;
	public static final int NUMBERHEIGHT = (CARDWIDTH - 2 * ProductCard.CARDGAP) / 6;
	public static final int NUMBERWIDTH = NUMBERHEIGHT * 2 / 3;
	private AnchorPane board = new AnchorPane();
	private AnchorPane leftTable, rightTable, midTable;
	// PP=Potential Point;SP=Skill Point;US=Upgrade Stone;ES = Evlove Stone
	private ProductCard EXPCard, PPCard, SPCard, USCard, ESCard;
	private NumberImage Gold;
	private int gold;

	public class ProductCard extends AnchorPane {
		private ImageView Icon, wordBackground, background;
		private String introduction;
		private int price;
		private Label word;
		private ImageButton buy;
		private int flag;
		private NumberImage Price;
		public static final int CARDGAP = 6;
		public static final int ICONLENGTH = (CARDWIDTH - 2 * CARDGAP) / 2;
		public static final int CARDMIDGAP = ICONLENGTH / 3;
		public static final int WORDWIDTH = CARDWIDTH - 2 * CARDGAP;
		public static final int WORDHEIGHT = WORDWIDTH * 4 / 3;
		public static final int PRICEHEIGHT = CARDMIDGAP;
		public static final int PRICEWIDTH = PRICEHEIGHT * 5 / 2;
		public static final int BUTTONHEIGHT = (CARDWIDTH - 2 * CARDGAP) / 2;
		public static final int BUTTONWIDTH = BUTTONHEIGHT * 5 / 3;

		public ProductCard(Image background, Image Icon, Image wordBackground, String introduction, int price,
				String unit, int flag) {
			this.Icon = new ImageView(Icon);
			this.background = new ImageView(background);
			this.wordBackground = new ImageView(wordBackground);
			this.introduction = introduction;
			this.price = price;
//			this.unit = unit;
			this.flag = flag;
			init();
		}

		private void init() {
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
			wordBackground.setY(CARDGAP + ICONLENGTH + CARDMIDGAP);
			this.getChildren().add(wordBackground);
			word = new Label(introduction);
			word.setFont(Main.myFont);
			word.setTextFill(Main.fontColor);
			word.setWrapText(true);
			word.setTextAlignment(TextAlignment.LEFT);
			word.setMaxSize(WORDWIDTH, WORDHEIGHT);
			word.setMinSize(WORDWIDTH, WORDHEIGHT);
			word.setLayoutX(CARDGAP);
			word.setLayoutY(CARDGAP + ICONLENGTH + CARDMIDGAP);
			this.getChildren().add(word);
			Price = new NumberImage(price, NUMBERHEIGHT, NUMBERWIDTH);
			Price.setLayoutX(CARDWIDTH - 3 * NUMBERWIDTH - CARDGAP - NUMBERWIDTH / 2);
			Price.setLayoutY(CARDGAP + ICONLENGTH + CARDMIDGAP - NUMBERHEIGHT);
			this.getChildren().add(Price);
			ImageView Icon = new ImageView(new Image("Graphics/Static/Icon/gold.png"));
			Icon.setX(Price.getLayoutX() - NUMBERHEIGHT);
			Icon.setY(Price.getLayoutY());
			Icon.setFitHeight(NUMBERHEIGHT);
			Icon.setFitWidth(NUMBERHEIGHT);
			this.getChildren().add(Icon);
			addButton();
		}

		public void checkButton() {
			Shop shop = platform.getPlayer1().getShop();
			if (flag == EXPFLAG && gold >= shop.getExpPrice() || flag == PPFLAG && gold >= shop.getPPPrice()
					|| flag == SPFLAG && gold >= shop.getSkillPointPrice()
					|| flag == USFLAG && gold >= shop.getUpGradeStonePrice()
					|| flag == ESFLAG && gold >= shop.getEvolveStonePrice()) {

				buy.setStaticGraphics(buyStatic);
				buy.setEnteredGraphics(buyEntered);
				buy.setPressedGraphics(buyPressed);
				buy.setMyWorker(buyWorker);
				buy.setPlayAudio(true);
			} else {
				buy.setStaticGraphics(buyIllegal);
				buy.setEnteredGraphics(buyIllegal);
				buy.setPressedGraphics(buyIllegal);
				buy.setMyWorker(illegalWorker);
				buy.setPlayAudio(false);
			}
		}
		public void setNull(){
			Platform.runLater(()->{
				buy.setMyWorker(illegalWorker);
				//buy.setPlayAudio(false);
			});

		}
		public void reset(){
			Platform.runLater(()->{
//				buy.setMyWorker(buyWorker);
				checkButton();
			});
		}
		private final ButtonWorker buyWorker = new ButtonWorker() {
			@Override
			public void work() {
				// TODO Auto-generated method stub
				Shop shop = platform.getPlayer1().getShop();
				EXPCard.setNull();
				PPCard.setNull();
				SPCard.setNull();
				USCard.setNull();
				ESCard.setNull();
				if (flag == EXPFLAG) {
					shop.buyExp(1);
					Price.refresh(shop.getExpPrice());
				} else if (flag == PPFLAG) {
					shop.buyPP(1);
					Price.refresh(shop.getPPPrice());
				} else if (flag == SPFLAG) {
					shop.buySkillPoint(1);
					Price.refresh(shop.getSkillPointPrice());
				} else if (flag == USFLAG) {
					shop.buyUpGradeStone(1);
					Price.refresh(shop.getUpGradeStonePrice());
				} else if (flag == ESFLAG) {
					shop.buyEvolveStone(1);
					Price.refresh(shop.getEvolveStonePrice());
				} else {
					System.out.println("FLAG解析错误！");
				}
				gold = platform.getPlayer1().getGold();
				Gold.refresh(gold);
				EXPCard.checkButton();
				PPCard.checkButton();
				SPCard.checkButton();
				USCard.checkButton();
				ESCard.checkButton();
				new MyThread().start();
			}

		};

		private void addButton() {
			buy = new ImageButton(buyStatic, buyEntered, buyPressed, buyWorker);
			buy.setFitHeight(BUTTONHEIGHT);
			buy.setFitWidth(BUTTONWIDTH);
			buy.setX(CARDWIDTH / 2 - BUTTONWIDTH / 2);
			buy.setY(CARDHEIGHT - CARDGAP - BUTTONHEIGHT);
			checkButton();
			this.getChildren().add(buy);
		}
	}

	private final Image buyStatic = new Image("Graphics/Static/Shop/buyStatic.png");
	private final Image buyEntered = new Image("Graphics/Static/Shop/buyEntered.png");
	private final Image buyPressed = new Image("Graphics/Static/Shop/buyPressed.png");
	private final Image buyIllegal = new Image("Graphics/Static/Shop/buyIllegal.png");
	private final ButtonWorker illegalWorker = new ButtonWorker() {

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("Illegal Ckick!");
		}

	};

	public ShopParent(Shopable shopPlatform, BasicScene main) {
		super(shopPlatform, main);
		ImageView background = new ImageView(new Image("Graphics/Static/Shop/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		this.getChildren().add(board);
		init();
	}

	private void addGold() {
		this.gold = platform.getPlayer1().getGold();
		Gold = new NumberImage(gold, NUMBERHEIGHT, NUMBERWIDTH);
		Gold.setLayoutX(board.getLayoutX() + BOARDWIDTH+NUMBERHEIGHT);
		Gold.setLayoutY(board.getLayoutY() + BOARDHEIGHT);
		this.getChildren().add(Gold);
		ImageView logo =new ImageView (new Image("Graphics/Static/Icon/gold.png"));
		logo.setFitHeight(NUMBERHEIGHT);
		logo.setFitWidth(NUMBERHEIGHT);
		logo.setX(Gold.getLayoutX()-NUMBERHEIGHT);
		logo.setY(Gold.getLayoutY());
		this.getChildren().add(logo);
	}

	private void addLeft() {
		leftTable = new AnchorPane();

		ImageView background = new ImageView(new Image("Graphics/Static/Shop/leftBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(LEFTTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		leftTable.getChildren().add(background);
		leftTable.setLayoutX(BOARDGAP);
		leftTable.setLayoutY(BOARDGAP);
		board.getChildren().add(leftTable);
		EXPCard = new ProductCard(new Image("Graphics/Static/Shop/EXPCardBackground.png"),
				new Image("Graphics/Static/Icon/EXP.png"), new Image("Graphics/Static/Shop/EXPWordBackground.png"),
				"经验值\n可以提升人物等级，进而获得升级潜力点奖励，提升自身能力", this.platform.getPlayer1().getShop().getExpPrice(), "金币/百", EXPFLAG);
		EXPCard.setLayoutX(TABLEGAP);
		EXPCard.setLayoutY(TABLEGAP);
		leftTable.getChildren().add(EXPCard);
		PPCard = new ProductCard(new Image("Graphics/Static/Shop/PPCardBackground.png"),
				new Image("Graphics/Static/Icon/PP.png"), new Image("Graphics/Static/Shop/PPWordBackground.png"),
				"潜力点\n可以转化为人物的物攻、法强、物抗、法抗中的某一项属性\n进而增加攻击伤害或者防御能力", this.platform.getPlayer1().getShop().getPPPrice(), "金币/点", PPFLAG);
		PPCard.setLayoutX(TABLEGAP + CARDWIDTH + TABLEMIDGAP);
		PPCard.setLayoutY(TABLEGAP);
		leftTable.getChildren().add(PPCard);
	}

	private void addMid() {
		midTable = new AnchorPane();
		ImageView background = new ImageView(new Image("Graphics/Static/Shop/midBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(MIDTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		midTable.getChildren().add(background);
		midTable.setLayoutX(BOARDGAP + LEFTTABLEWIDTH + BOARDMIDGAP);
		midTable.setLayoutY(BOARDGAP);
		board.getChildren().add(midTable);
		SPCard = new ProductCard(new Image("Graphics/Static/Shop/SPCardBackground.png"),
				new Image("Graphics/Static/Icon/SP.png"), new Image("Graphics/Static/Shop/SPWordBackground.png"),
				"技能点\n技能点可用于人物技能的学习或者升级\n从而获得更强大的技能", this.platform.getPlayer1().getShop().getSkillPointPrice(), "金币/点", SPFLAG);
		SPCard.setLayoutX(TABLEGAP);
		SPCard.setLayoutY(TABLEGAP);
		midTable.getChildren().add(SPCard);
	}

	private void addRight() {
		rightTable = new AnchorPane();
		ImageView background = new ImageView(new Image("Graphics/Static/Shop/rightBackground.png"));
		background.setFitHeight(TABLEHEIGHT);
		background.setFitWidth(RIGHTTABLEWIDTH);
		background.setX(0);
		background.setY(0);
		rightTable.getChildren().add(background);
		rightTable.setLayoutX(BOARDGAP + LEFTTABLEWIDTH + BOARDMIDGAP + MIDTABLEWIDTH + BOARDMIDGAP);
		rightTable.setLayoutY(BOARDGAP);
		board.getChildren().add(rightTable);
		USCard = new ProductCard(new Image("Graphics/Static/Shop/USCardBackground.png"),
				new Image("Graphics/Static/Icon/US.png"), new Image("Graphics/Static/Shop/USWordBackground.png"),
				"升级石\n升级石用于装备等级的提升\n装备等级提升可以小幅度提高装备的属性加成", this.platform.getPlayer1().getShop().getUpGradeStonePrice(), "金币/个", USFLAG);
		USCard.setLayoutX(TABLEGAP);
		USCard.setLayoutY(TABLEGAP);
		rightTable.getChildren().add(USCard);
		ESCard = new ProductCard(new Image("Graphics/Static/Shop/ESCardBackground.png"),
				new Image("Graphics/Static/Icon/ES.png"), new Image("Graphics/Static/Shop/ESWordBackground.png"),
				"升阶石\n升阶石用于装备阶级的提升\n装备进阶可以全方面提升装备的属性值", this.platform.getPlayer1().getShop().getEvolveStonePrice(), "金币/个", ESFLAG);
		ESCard.setLayoutX(TABLEGAP + CARDWIDTH + TABLEMIDGAP);
		ESCard.setLayoutY(TABLEGAP);
		rightTable.getChildren().add(ESCard);
	}

	private void init() {
		addGold();
		addLeft();
		addMid();
		addRight();
	}
	public class MyThread{
		public void start(){
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						Thread.sleep(900);
					}catch(Exception e){
						e.printStackTrace();
					}
					EXPCard.checkButton();
					PPCard.checkButton();
					SPCard.checkButton();
					USCard.checkButton();
					ESCard.checkButton();
				}
				
			}).start();
		}
	}
}
