package ui.supportRoot;

import bll.platform.*;
import bll.support.Skill;
import bllservice.*;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
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

public class SkillParent extends SupportParent {
	public final static int BOARDHEIGHT = 1000;
	public final static int BOARDWIDTH = 1000;
	public final static int BOARDGAP = 10;
	public final static int INNERGAP = 7;
	public final static int NORMALHEIGHT = (BOARDHEIGHT - 2 * BOARDGAP) / 3;
	public final static int NORMALWIDTH = (BOARDWIDTH - 2 * BOARDGAP) / 2 + 3;
	public final static int GENERATEHEIGHT = (BOARDHEIGHT - 2 * BOARDGAP) * 2 / 3;
	public final static int GENERATEWIDTH = (BOARDHEIGHT - 2 * BOARDGAP) / 2 + 3;
	public final static int PROFESSIONHEIGHT = (BOARDHEIGHT - 2 * BOARDGAP);
	public final static int PROFESSIONWIDTH = (BOARDWIDTH - 2 * BOARDGAP) / 2 + 3;
	public final static int ICONLENGTH = PROFESSIONHEIGHT / 13;
	public final static int MIDGAP = ICONLENGTH / 3;
	public final static int DELTALENGTH = ICONLENGTH;
	public final static int WORDHEIGHT = 4 * ICONLENGTH;
	public final static int CARDHEIGHT = NORMALHEIGHT - 2;
	public final static int CARDWIDTH = NORMALWIDTH - 10;
	public final static int WORDWIDTH = PROFESSIONWIDTH - 2 * INNERGAP - DELTALENGTH - ICONLENGTH - 4;
	public final static int BUTTONHEIGHT = ICONLENGTH * 8 / 10;
	public final static int BUTTONWIDTH = BUTTONHEIGHT * 5 / 3;
	public final static int LEVELHEIGHT = ICONLENGTH * 6 / 10;
	public final static int LEVELWIDTH = LEVELHEIGHT * 2 / 3;
	public final static int NUMBERHEIGHT = BUTTONHEIGHT * 6 / 10;
	public final static int NUMBERWIDTH = NUMBERHEIGHT * 4 / 5;
//	private final String nnn = "\n\n\n\n\n\n\n\n\n\n\n\n";
	private AnchorPane board = new AnchorPane();
	private AnchorPane normal = new AnchorPane();
	private AnchorPane generate = new AnchorPane();
	private AnchorPane profession = new AnchorPane();
	private SkillCard skill0, skill1, skill2, skill3, skill4, skill5;
	private NumberImage skillPoint;

	public SkillParent(Skillable skillPlatform, BasicScene main) {
		super(skillPlatform, main);
		init();
	}

	public class SkillCard extends AnchorPane {
		private ImageView background, skill;
		private int nowlevel;
		private String[] introduction;
		private ImageView labelBackground;
		private Label words;
		private Skill mySkill;
		private ImageButton button;
		private NumberImage level;
		private Label name;
		private NumberImage number;

		public SkillCard(Image cardBackground, Image labelBackground, int skillID, int nowlevel) {
			background = new ImageView(cardBackground);
			background.setFitHeight(CARDHEIGHT);
			background.setFitWidth(CARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			Image skillIcon = new Image("Graphics/Skill/" + skillID + ".png");
			skill = new ImageView(skillIcon);
			skill.setFitHeight(ICONLENGTH);
			skill.setFitWidth(ICONLENGTH);
			skill.setX(INNERGAP);
			skill.setY(INNERGAP);
			this.getChildren().add(skill);
			mySkill = Skill.getSkillByID(skillID);
			this.nowlevel = nowlevel;
//			this.maxlevel = mySkill.getMAXLEVEL();
			this.introduction = mySkill.getSkillIntroduction();
			this.labelBackground = new ImageView(labelBackground);
			this.labelBackground.setFitHeight(WORDHEIGHT);
			this.labelBackground.setFitWidth(WORDWIDTH);
			this.labelBackground.setX(INNERGAP + ICONLENGTH + DELTALENGTH);
			this.labelBackground.setY(INNERGAP);
			this.getChildren().add(this.labelBackground);
			this.labelBackground.setY(0);
			words = new Label(introduction[nowlevel]);
			words.setFont(Main.myFont);
			words.setTextFill(Main.fontColor);
			words.setMaxSize(WORDWIDTH, WORDHEIGHT);
			words.setMinSize(WORDWIDTH, WORDHEIGHT);
			words.setWrapText(true);
			words.setId("SkillIntroduction"+skillID%100);
			words.setLayoutX(INNERGAP + ICONLENGTH + DELTALENGTH);
			words.setLayoutY(INNERGAP);
			words.setText(introduction[nowlevel]);
			this.getChildren().add(words);
			number = new NumberImage(mySkill.getLevelUpCost(platform.getPlayer1()));
			number.setSize(NUMBERHEIGHT, NUMBERWIDTH);
			number.setLayoutX(INNERGAP + NUMBERHEIGHT);
			number.setLayoutY(CARDHEIGHT-INNERGAP-BUTTONHEIGHT - NUMBERHEIGHT);
			this.getChildren().add(number);
			ImageView logo = new ImageView(new Image("Graphics/Static/Icon/SP.png"));
			logo.setFitHeight(NUMBERHEIGHT);
			logo.setFitWidth(NUMBERHEIGHT);
			logo.setX(INNERGAP);
			logo.setY(number.getLayoutY());
			this.getChildren().add(logo);
			button = new ImageButton(null, null, null, illegalWorker);
			button.setFitHeight(BUTTONHEIGHT);
			button.setFitWidth(BUTTONWIDTH);
			button.setX(INNERGAP);
			button.setY(CARDHEIGHT - INNERGAP - BUTTONHEIGHT);
			this.getChildren().add(button);
			level = new NumberImage(nowlevel);
			level.setSize(LEVELHEIGHT, LEVELWIDTH);
			level.setLayoutX(INNERGAP + LEVELWIDTH * 3);
			level.setLayoutY(INNERGAP + ICONLENGTH + ICONLENGTH);
			ImageView L = new ImageView(new Image("Graphics/Static/Icon/L.png"));
			L.setFitHeight(LEVELHEIGHT);
			L.setFitWidth(LEVELWIDTH);
			L.setX(INNERGAP);
			L.setY(level.getLayoutY());
			this.getChildren().add(L);
			ImageView V = new ImageView(new Image("Graphics/Static/Icon/V.png"));
			V.setFitHeight(LEVELHEIGHT);
			V.setFitWidth(LEVELWIDTH);
			V.setX(INNERGAP + LEVELWIDTH);
			V.setY(level.getLayoutY());
			this.getChildren().add(V);
			ImageView Point = new ImageView(new Image("Graphics/Static/Icon/Point.png"));
			Point.setFitHeight(LEVELHEIGHT);
			Point.setFitWidth(LEVELWIDTH);
			Point.setX(INNERGAP + LEVELWIDTH * 2);
			Point.setY(level.getLayoutY());
			this.getChildren().add(Point);
			this.getChildren().add(level);
			this.setMaxSize(CARDWIDTH, CARDHEIGHT);
			this.setMinSize(CARDWIDTH, CARDHEIGHT);
			name = new Label(Skill.getSkillNameByID(mySkill.getID()));
			name.setLayoutX(skill.getX());
			name.setLayoutY(skill.getY() + ICONLENGTH * 4 / 3);
			name.setId("SkillLabel");
			this.getChildren().add(name);

			checkButton();
		}

		private void checkOther() {
			level.refresh(nowlevel);
			words.setText(mySkill.getSkillIntroduction()[nowlevel]);
			name.setText(Skill.getSkillNameByID(mySkill.getID()));
			if (nowlevel != mySkill.getMAXLEVEL()) {
				number.refresh(mySkill.getLevelUpCost(platform.getPlayer1()));
			} else {
				number.refresh(0);
			}
		}

		private void checkButton() {
			if (nowlevel == mySkill.getMAXLEVEL()) {
				button.setStaticGraphics(levelupMax);
				button.setEnteredGraphics(levelupMax);
				button.setPressedGraphics(levelupMax);
				button.setMyWorker(illegalWorker);
				button.setPlayAudio(false);
			} else if ((mySkill.getLevelUpCost(platform.getPlayer1()) >= platform.getPlayer1().getSkillPointNum())) {
				if (nowlevel != 0) {
					button.setStaticGraphics(levelupIllegal);
					button.setEnteredGraphics(levelupIllegal);
					button.setPressedGraphics(levelupIllegal);
					button.setMyWorker(illegalWorker);
					button.setPlayAudio(false);
				} else {
					button.setStaticGraphics(studyIllegal);
					button.setEnteredGraphics(studyIllegal);
					button.setPressedGraphics(studyIllegal);
					button.setMyWorker(illegalWorker);
					button.setPlayAudio(false);
				}
			} else {
				if (nowlevel != 0) {
					button.setStaticGraphics(levelupStatic);
					button.setEnteredGraphics(levelupEntered);
					button.setPressedGraphics(levelupPressed);
					button.setPlayAudio(true);
				} else {
					button.setStaticGraphics(studyStatic);
					button.setEnteredGraphics(studyEntered);
					button.setPressedGraphics(studyPressed);
					button.setPlayAudio(true);
				}
				button.setMyWorker(new ButtonWorker() {

					@Override
					public void work() {
						// TODO Auto-generated method stub
						nowlevel++;
						platform.getPlayer1().skillLevelup(mySkill.getID());
						skill0.checkButton();
						skill1.checkButton();
						skill2.checkButton();
						skill3.checkButton();
						skill4.checkButton();
						skill5.checkButton();
						skillPoint.refresh(platform.getPlayer1().getSkillPointNum());
					}

				});
			}
			checkOther();
		}
	}

	private void init() {
		ImageView background = new ImageView(new Image("Graphics/Static/Skill/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH / 2 - BOARDWIDTH / 2);
		board.setLayoutY(Main.SCREENHEIGHT / 2 - BOARDHEIGHT / 2);
		this.getChildren().add(board);
		addNormal();
		addGenerate();
		addProfession();
		addSkillPoint();
	}

	private void addSkillPoint() {
		skillPoint = new NumberImage(platform.getPlayer1().getSkillPointNum());
		skillPoint.setSize(LEVELHEIGHT, LEVELWIDTH);
		skillPoint.setLayoutX(board.getLayoutX() + BOARDWIDTH+LEVELHEIGHT);
		skillPoint.setLayoutY(board.getLayoutY() + BOARDHEIGHT);
		this.getChildren().add(skillPoint);
		ImageView logo = new ImageView (new Image("Graphics/Static/Icon/SP.png"));
		logo.setFitHeight(LEVELHEIGHT);
		logo.setFitWidth(LEVELHEIGHT);
		logo.setX(skillPoint.getLayoutX()-LEVELHEIGHT);
		logo.setY(skillPoint.getLayoutY());
		this.getChildren().add(logo);
	}

	private final Image levelupStatic = new Image("Graphics/Static/Skill/levelupStatic.png");
	private final Image levelupEntered = new Image("Graphics/Static/Skill/levelupEntered.png");
	private final Image levelupPressed = new Image("Graphics/Static/Skill/levelupPressed.png");
	private final Image levelupIllegal = new Image("Graphics/Static/Skill/levelupIllegal.png");
	private final Image levelupMax = new Image("Graphics/Static/Icon/max.png");
	private final Image studyStatic = new Image("Graphics/Static/Skill/studyStatic.png");
	private final Image studyEntered = new Image("Graphics/Static/Skill/studyEntered.png");
	private final Image studyPressed = new Image("Graphics/Static/Skill/studyPressed.png");
	private final Image studyIllegal = new Image("Graphics/Static/Skill/studyIllegal.png");
	private final ButtonWorker illegalWorker = new ButtonWorker() {

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("Illegal Opt");
		}

	};

	private void addProfession() {
		ImageView background = new ImageView(new Image("Graphics/Static/Skill/professionBackground.png"));
		background.setFitWidth(PROFESSIONWIDTH);
		background.setFitHeight(PROFESSIONHEIGHT);
		background.setX(0);
		background.setY(0);
		profession.getChildren().add(background);
		profession.setLayoutX(BOARDWIDTH - PROFESSIONWIDTH);
		profession.setLayoutY(BOARDGAP);
		board.getChildren().add(profession);
		skill3 = new SkillCard(new Image("Graphics/Static/Skill/skill3Background.png"),
				new Image("Graphics/Static/Skill/skill3LabelBackground.png"), this.platform.getPlayer1().getPro() + 3,
				this.platform.getPlayer1().getSkillList()[3]);
		skill3.setLayoutX(INNERGAP);
		skill3.setLayoutY(INNERGAP);
		profession.getChildren().add(skill3);
		skill4 = new SkillCard(new Image("Graphics/Static/Skill/skill4Background.png"),
				new Image("Graphics/Static/Skill/skill4LabelBackground.png"), this.platform.getPlayer1().getPro() + 4,
				this.platform.getPlayer1().getSkillList()[4]);
		skill4.setLayoutX(INNERGAP);
		skill4.setLayoutY(INNERGAP + CARDHEIGHT);
		profession.getChildren().add(skill4);
		skill5 = new SkillCard(new Image("Graphics/Static/Skill/skill5Background.png"),
				new Image("Graphics/Static/Skill/skill5LabelBackground.png"), this.platform.getPlayer1().getPro() + 5,
				this.platform.getPlayer1().getSkillList()[5]);
		skill5.setLayoutX(INNERGAP);
		skill5.setLayoutY(INNERGAP + 2 * CARDHEIGHT);
		profession.getChildren().add(skill5);
	}

	private void addGenerate() {
		ImageView background = new ImageView(new Image("Graphics/Static/Skill/generateBackground.png"));
		background.setFitWidth(GENERATEWIDTH);
		background.setFitHeight(GENERATEHEIGHT);
		background.setX(0);
		background.setY(0);
		generate.getChildren().add(background);
		generate.setLayoutX(BOARDGAP);
		generate.setLayoutY(BOARDGAP + NORMALHEIGHT);
		board.getChildren().add(generate);
		skill1 = new SkillCard(new Image("Graphics/Static/Skill/skill1Background.png"),
				new Image("Graphics/Static/Skill/skill1LabelBackground.png"), this.platform.getPlayer1().getPro() + 1,
				this.platform.getPlayer1().getSkillList()[1]);
		skill1.setLayoutX(INNERGAP);
		skill1.setLayoutY(INNERGAP);
		generate.getChildren().add(skill1);
		skill2 = new SkillCard(new Image("Graphics/Static/Skill/skill2Background.png"),
				new Image("Graphics/Static/Skill/skill2LabelBackground.png"), this.platform.getPlayer1().getPro() + 2,
				this.platform.getPlayer1().getSkillList()[2]);
		skill2.setLayoutX(INNERGAP);
		skill2.setLayoutY(INNERGAP + CARDHEIGHT);
		generate.getChildren().add(skill2);
	}

	private void addNormal() {
		ImageView background = new ImageView(new Image("Graphics/Static/Skill/normalBackground.png"));
		background.setFitHeight(NORMALHEIGHT);
		background.setFitWidth(NORMALWIDTH);
		background.setX(0);
		background.setY(0);
		normal.getChildren().add(background);
		normal.setLayoutX(BOARDGAP);
		normal.setLayoutY(BOARDGAP);
		board.getChildren().add(normal);
		skill0 = new SkillCard(new Image("Graphics/Static/Skill/skill0Background.png"),
				new Image("Graphics/Static/Skill/skill0LabelBackground.png"), this.platform.getPlayer1().getPro() + 0,
				this.platform.getPlayer1().getSkillList()[0]);
		skill0.setLayoutX(INNERGAP);
		skill0.setLayoutY(INNERGAP);
		normal.getChildren().add(skill0);
	}

}
