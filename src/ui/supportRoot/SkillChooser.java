package ui.supportRoot;

import bll.individual.Player;
import bll.support.Skill;
import bllservice.Chooseable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.DialogableImageView;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;
import util.Audio;public class SkillChooser extends AnchorPane {
	public static final int BOARDHEIGHT = Main.SCREENHEIGHT/2;
	public static final int BOARDWIDTH = BOARDHEIGHT;
	public static final int GAP = BOARDHEIGHT/10;
	public static final int MIDGAP = GAP;
	public static final int CARDHEIGHT = (BOARDHEIGHT-GAP*2-MIDGAP*2)/3;
	public static final int CARDWIDTH = (BOARDWIDTH-GAP*2-MIDGAP)/2;
	public static final int INNERGAP = CARDHEIGHT/10;
	public static final int ICONLENGTH =CARDHEIGHT-INNERGAP*2;
	public static final int BUTTONHEIGHT = ICONLENGTH;
	public static final int BUTTONWIDTH =BUTTONHEIGHT*2;
	public static final int G=BUTTONHEIGHT/3;
	private Player player;
	private boolean [] flag = new boolean [6];
	private Main main;
	private AnchorPane board=new AnchorPane();
	private SkillCard s0,s1,s2,s3,s4,s5;
	private ImageButton confirm,back;
	private AnchorPane father;
	public SkillChooser(Player player,Main main,AnchorPane father2){
		this.player=player;
//		this.father=father2;
		father = this;
		this.main=main;
		init();
	}
	private void init(){
		ImageView background = new ImageView (new Image("Graphics/Other/SkillChooser/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		this.getChildren().add(board);
		addSkill();
		addButton();
	}
	private void addButton(){
		confirm = new ImageButton(confirmIllegal,confirmIllegal,confirmIllegal,illegalWorker);
		confirm.setPlayAudio(false);
		confirm.setFitHeight(BUTTONHEIGHT);
		confirm.setFitWidth(BUTTONWIDTH);
		confirm.setX(Main.SCREENWIDTH/2-BUTTONWIDTH-G);
		confirm.setY(board.getLayoutY()+BOARDHEIGHT);
		this.getChildren().addAll(confirm);
		back = new ImageButton (new Image("Graphics/Static/Icon/backStatic.png"),new Image("Graphics/Static/Icon/backEntered.png"),
				new Image("Graphics/Static/Icon/backPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						main.setStage(new GameChooser((Chooseable)main.getStaticParent().getBasicPlatform(),(BasicScene)main,"a"));
					}
			
		});
		back.setFitHeight(BUTTONHEIGHT);
		back.setFitWidth(BUTTONWIDTH);
		back.setX(Main.SCREENWIDTH/2+G);
		back.setY(confirm.getY());
		this.getChildren().add(back);
	}
	private void addSkill(){
		s0 = new SkillCard (0);
		s1 = new SkillCard (1);
		s2 = new SkillCard (2);
		s3 = new SkillCard (3);
		s4 = new SkillCard (4);
		s5 = new SkillCard (5);
		s0.setLayoutX(GAP);
		s0.setLayoutY(GAP);
		s1.setLayoutX(GAP);
		s1.setLayoutY(GAP+CARDHEIGHT+MIDGAP);
		s2.setLayoutX(GAP);
		s2.setLayoutY(GAP+CARDHEIGHT*2+MIDGAP*2);
		s3.setLayoutX(GAP+CARDWIDTH+MIDGAP);
		s3.setLayoutY(GAP);
		s4.setLayoutX(s3.getLayoutX());
		s4.setLayoutY(s3.getLayoutY()+CARDHEIGHT+MIDGAP);
		s5.setLayoutX(s3.getLayoutX());
		s5.setLayoutY(s4.getLayoutY()+CARDHEIGHT+MIDGAP);
		board.getChildren().addAll(s0,s1,s2,s3,s4,s5);
	}
	private final ButtonWorker confirmWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			//edit player
			int temp=0;
			for (int i=0;i<6;i++){
				if (flag[i]){
					System.out.println("Choosed "+i);
					player.getSkillChoosed()[temp]=player.getPro()+i;
					temp++;
				}
			}
			for (int i=temp;i<3;i++){
				player.getSkillChoosed()[i]=Skill.NULLSKILL;
			}
			main.createDone();
		}
		
	};
	private void checkButton(){
		int temp = 0;
		for (int i=0;i<6;i++){
			if (flag[i]){
				temp++;
			}
		}
		if (temp==0){
			confirm.setStaticGraphics(confirmIllegal);
			confirm.setEnteredGraphics(confirmIllegal);
			confirm.setPressedGraphics(confirmIllegal);
			confirm.setMyWorker(illegalWorker);
			confirm.setPlayAudio(false);
		}else{
			confirm.setStaticGraphics(confirmStatic);
			confirm.setEnteredGraphics(confirmEntered);
			confirm.setPressedGraphics(confirmPressed);
			confirm.setMyWorker(confirmWorker);
			confirm.setPlayAudio(true);
		}
	}
	private final Image confirmStatic = new Image("Graphics/Static/Icon/confirmStatic.png");
	private final Image confirmEntered = new Image("Graphics/Static/Icon/confirmEntered.png");
	private final Image confirmPressed = new Image("Graphics/Static/Icon/confirmPressed.png");
	private final Image confirmIllegal = new Image("Graphics/Static/Icon/confirmIllegal.png");
	private final Image skillStatic = new Image("Graphics/Other/Pool/skillBackgroundStatic.png");
	private final Image skillEntered = new Image ("Graphics/Other/Pool/skillBackgroundEntered.png");
	private final Image skillPressed = new Image ("Graphics/Other/Pool/skillBackgroundPressed.png");
	private final Image skillIllegal = new Image ("Graphics/Other/Pool/skillBackgroundIllegal.png");
	private final ButtonWorker illegalWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("illegal Click!");
		}
		
	};
	public class SkillCard extends AnchorPane{
		private int index;
		private boolean choosed =false;
		private ImageView background;
		private Image staticImage,enteredImage,pressedImage;
		private ButtonWorker worker;
		private boolean enter=false,press=false,playAudio=true;
		private final ButtonWorker myWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				if (!choosed){
					choosed=true;
					flag[index]=true;
					staticImage = skillEntered;
					enteredImage =skillEntered;
					pressedImage = skillPressed;
					//check
					s0.check();
					s1.check();
					s2.check();
					s3.check();
					s4.check();
					s5.check();
					checkButton();
					for (int i=0;i<6;i++){
						System.out.print(flag[i]+" ");
					}
					System.out.println();
				}else{
					choosed=false;
					flag[index]=false;
					staticImage=skillStatic;
					enteredImage =skillEntered;
					pressedImage = skillPressed;
					//check
					s0.check();
					s1.check();
					s2.check();
					s3.check();
					s4.check();
					s5.check();
					checkButton();
					for (int i=0;i<6;i++){
						System.out.print(flag[i]+" ");
					}
					System.out.println();
				}
			}
			
		};
		public void check(){
			int temp =0;
			for (int i=0;i<6;i++){
				if (flag[i]){
					temp++;
				}
			}
			if (temp==3){
				if (!choosed){
					staticImage=enteredImage=pressedImage=skillIllegal;
					worker=illegalWorker;
					background.setImage(skillIllegal);
					playAudio=false;
				}
			}else{
				if (!choosed){
				if (player.getSkillList()[index]>0){
					staticImage = skillStatic;
					enteredImage =skillEntered;
					pressedImage = skillPressed;
					worker=myWorker;
					playAudio=true;
					background.setImage(skillStatic);
				}else{
					playAudio=false;
					staticImage=enteredImage=pressedImage=skillIllegal;
					worker = illegalWorker;
				}
				}
			}
		}
		public SkillCard (int index){
			this.index=index;
			if (player.getSkillList()[index]>0){
				staticImage = skillStatic;
				enteredImage =skillEntered;
				pressedImage = skillPressed;
				worker=myWorker;
				playAudio=true;
			}else{
				staticImage=enteredImage=pressedImage=skillIllegal;
				worker = illegalWorker;
				playAudio=false;
			}
			background = new ImageView (staticImage);
			background.setFitHeight(CARDHEIGHT);
			background.setFitWidth(CARDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
//			ImageView icon = new ImageView (new Image("Graphics/Skill/"+(index+player.getPro())+".png"));
			ImageView icon = new DialogableImageView (Skill.getSkillByID(index+player.getPro()).getSkillIntroduction()[player.getSkillList()[index]]+"!",father,320,400," ");
//			ImageView icon = new ImageView ();
			icon.setImage(new Image ("Graphics/Skill/"+(index+player.getPro())+".png"));
			icon.setFitHeight(ICONLENGTH);
			icon.setFitWidth(ICONLENGTH);
			icon.setX(INNERGAP);
			icon.setY(INNERGAP);
			this.getChildren().add(icon);
			this.setOnMouseEntered(e->{
				enter=true;
				if (!press){
					if (playAudio){
						Audio.entered.play();
					}
					background.setImage(enteredImage);
				}
			});
			this.setOnMouseExited(e->{
				enter=false;
				if (!press){
					background.setImage(staticImage);
				}
			});
			this.setOnMousePressed(e->{
				press=true;
				if (!playAudio){
					Audio.illegal.play();
				}else{
					Audio.pressed.play();
				}
				background.setImage(pressedImage);
			});
			this.setOnMouseReleased(e->{
				press=false;
				if (enter){
					worker.work();
				}
				background.setImage(staticImage);
			});
		}
	}
}
