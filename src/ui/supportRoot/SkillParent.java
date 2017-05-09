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
import ui.sceneInterface.BasicScene;
public class SkillParent extends SupportParent{
	public final static int BOARDHEIGHT = 1000;
	public final static int BOARDWIDTH = 1000;
	public final static int BOARDGAP = 10;
	public final static int INNERGAP = 7;
	public final static int NORMALHEIGHT = (BOARDHEIGHT-2*BOARDGAP)/3;
	public final static int NORMALWIDTH = (BOARDWIDTH-2*BOARDGAP)/2+3;
	public final static int GENERATEHEIGHT =(BOARDHEIGHT-2*BOARDGAP)*2/3;
	public final static int GENERATEWIDTH = (BOARDHEIGHT-2*BOARDGAP)/2+3;
	public final static int PROFESSIONHEIGHT = (BOARDHEIGHT-2*BOARDGAP);
	public final static int PROFESSIONWIDTH = (BOARDWIDTH-2*BOARDGAP)/2+3;
	public final static int ICONLENGTH = PROFESSIONHEIGHT/13;
	public final static int MIDGAP=ICONLENGTH/3;
	public final static int DELTALENGTH = ICONLENGTH;
	public final static int WORDHEIGHT = 4*ICONLENGTH;
	public final static int CARDHEIGHT=NORMALHEIGHT-2;
	public final static int CARDWIDTH = NORMALWIDTH-10;
	public final static int WORDWIDTH = PROFESSIONWIDTH-2*INNERGAP-DELTALENGTH-ICONLENGTH-4;
	private final String nnn="\n\n\n\n\n\n\n\n\n\n\n\n";
	AnchorPane board = new AnchorPane ();
	AnchorPane normal= new AnchorPane ();
	AnchorPane generate = new AnchorPane ();
	AnchorPane profession = new AnchorPane ();
	SkillCard skill0,skill1,skill2,skill3,skill4,skill5;
	public SkillParent(Skillable skillPlatform,BasicScene main){
		super(skillPlatform,main);
		init();
	}
	public class SkillCard extends AnchorPane{
		private ImageView background,skill;
		private int maxlevel,nowlevel;
		private String[] introduction;
		private ImageView labelBackground;
		private Label words;
		private Skill mySkill;
		public SkillCard (Image cardBackground,Image labelBackground,int skillID,int nowlevel){
			background = new ImageView (cardBackground);
			background.setFitHeight(CARDHEIGHT);
			background.setFitWidth(CARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			Image skillIcon = new Image("Graphics/Skill/"+skillID+".png");
			skill = new ImageView (skillIcon);
			skill.setFitHeight(ICONLENGTH);
			skill.setFitWidth(ICONLENGTH);
			skill.setX(INNERGAP);
			skill.setY(INNERGAP);
			this.getChildren().add(skill);
			mySkill = Skill.getSkillByID(skillID);
			this.nowlevel=nowlevel;
			this.maxlevel=mySkill.getMAXLEVEL();
			this.introduction = mySkill.getSkillIntroduction();
			this.labelBackground = new ImageView(labelBackground);
			this.labelBackground.setFitHeight(WORDHEIGHT);
			this.labelBackground.setFitWidth(WORDWIDTH);
			this.labelBackground.setX(INNERGAP+ICONLENGTH+DELTALENGTH);
			this.labelBackground.setY(INNERGAP);
			this.getChildren().add(this.labelBackground);
			this.labelBackground.setY(0);
			words = new Label (introduction[nowlevel]);
			words.setMaxSize(WORDWIDTH, WORDHEIGHT);
			words.setMinSize(WORDWIDTH, WORDHEIGHT);
			words.setLayoutX(INNERGAP+ICONLENGTH+DELTALENGTH);
			words.setLayoutY(INNERGAP);
//			words.setGraphic(this.labelBackground);
			words.setText(introduction[nowlevel]);
			this.getChildren().add(words);
			this.setMaxSize(CARDWIDTH, CARDHEIGHT);
			this.setMinSize(CARDWIDTH, CARDHEIGHT);
			
		}
	}
	private void init(){
		ImageView background = new ImageView (new Image("Graphics/Static/Skill/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		addNormal();
		addGenerate();
		addProfession();
	}
	private void addProfession(){
		ImageView background = new ImageView (new Image("Graphics/Static/Skill/professionBackground.png"));
		background.setFitWidth(PROFESSIONWIDTH);
		background.setFitHeight(PROFESSIONHEIGHT);
		background.setX(0);
		background.setY(0);
		profession.getChildren().add(background);
		profession.setLayoutX(BOARDWIDTH-PROFESSIONWIDTH);
		profession.setLayoutY(BOARDGAP);
		board.getChildren().add(profession);
		skill3 = new SkillCard (new Image("Graphics/Static/Skill/skill3Background.png"),new Image("Graphics/Static/Skill/skill3LabelBackground.png"),
				this.platform.getPlayer1().getPro()+3,this.platform.getPlayer1().getSkillList()[3]);
		skill3.setLayoutX(INNERGAP);
		skill3.setLayoutY(INNERGAP);
		profession.getChildren().add(skill3);
		skill4 = new SkillCard (new Image("Graphics/Static/Skill/skill4Background.png"),new Image("Graphics/Static/Skill/skill4LabelBackground.png"),
				this.platform.getPlayer1().getPro()+4,this.platform.getPlayer1().getSkillList()[4]);
		skill4.setLayoutX(INNERGAP);
		skill4.setLayoutY(INNERGAP+CARDHEIGHT);
		profession.getChildren().add(skill4);
		skill5 = new SkillCard (new Image("Graphics/Static/Skill/skill5Background.png"),new Image("Graphics/Static/Skill/skill5LabelBackground.png"),
				this.platform.getPlayer1().getPro()+5,this.platform.getPlayer1().getSkillList()[5]);
		skill5.setLayoutX(INNERGAP);
		skill5.setLayoutY(INNERGAP+2*CARDHEIGHT);
		profession.getChildren().add(skill5);
	}
	private void addGenerate(){
		ImageView background = new ImageView (new Image("Graphics/Static/Skill/generateBackground.png"));
		background.setFitWidth(GENERATEWIDTH);
		background.setFitHeight(GENERATEHEIGHT);
		background.setX(0);
		background.setY(0);
		generate.getChildren().add(background);
		generate.setLayoutX(BOARDGAP);
		generate.setLayoutY(BOARDGAP+NORMALHEIGHT);
		board.getChildren().add(generate);
		skill1 = new SkillCard (new Image("Graphics/Static/Skill/skill1Background.png"),new Image("Graphics/Static/Skill/skill1LabelBackground.png"),
				this.platform.getPlayer1().getPro()+1,this.platform.getPlayer1().getSkillList()[1]);
		skill1.setLayoutX(INNERGAP);
		skill1.setLayoutY(INNERGAP);
		generate.getChildren().add(skill1);
		skill2 = new SkillCard (new Image("Graphics/Static/Skill/skill2Background.png"),new Image("Graphics/Static/Skill/skill2LabelBackground.png"),
				this.platform.getPlayer1().getPro()+2,this.platform.getPlayer1().getSkillList()[2]);
		skill2.setLayoutX(INNERGAP);
		skill2.setLayoutY(INNERGAP+CARDHEIGHT);
		generate.getChildren().add(skill2);
	}
	private void addNormal(){
		ImageView background = new ImageView (new Image("Graphics/Static/Skill/normalBackground.png"));
		background.setFitHeight(NORMALHEIGHT);
		background.setFitWidth(NORMALWIDTH);
		background.setX(0);
		background.setY(0);
		normal.getChildren().add(background);
		normal.setLayoutX(BOARDGAP);
		normal.setLayoutY(BOARDGAP);
		board.getChildren().add(normal);
		skill0 = new SkillCard (new Image("Graphics/Static/Skill/skill0Background.png"),new Image("Graphics/Static/Skill/skill0LabelBackground.png"),
				this.platform.getPlayer1().getPro()+0,this.platform.getPlayer1().getSkillList()[0]);
		skill0.setLayoutX(INNERGAP);
		skill0.setLayoutY(INNERGAP);
		normal.getChildren().add(skill0);
	}

}
