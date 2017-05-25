package ui.supportRoot;

import bllservice.Supportable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.PropertyLine;
import ui.sceneInterface.BasicScene;
import util.Audio;
public class AudioSettingParent extends SupportParent{
	private SettingParent back;
	public static final int GAP = 10;
	public static final int LABELHEIGHT = Main.SCREENHEIGHT/18;
	public static final int LABELWIDTH = LABELHEIGHT*3;
	public static final int MIDGAP = 10;
	public static final int BUTTONHEIGHT = LABELHEIGHT;
	public static final int BUTTONWIDTH = BUTTONHEIGHT*2;
	public static final int LINEHEIGHT = BUTTONHEIGHT/2;
	public static final int LINEWIDTH = BUTTONWIDTH*3;
	public static final int ITEMHEIGHT = LABELHEIGHT;
	public static final int INNERMIDGAP = BUTTONHEIGHT/3;
	public static final int ITEMWIDTH = LABELWIDTH+BUTTONHEIGHT+LINEWIDTH+BUTTONHEIGHT+2*BUTTONWIDTH+INNERMIDGAP;
	public static final int BOARDWIDTH = ITEMWIDTH+GAP*2;
	public static final int BOARDHEIGHT = ITEMHEIGHT*5+MIDGAP*4+2*GAP;
	private AnchorPane board = new AnchorPane ();
	private SettingItem enteredSetter,pressedSetter,battleSetter,skillSetter,popSetter;
	public AudioSettingParent(Supportable supportPlatform, BasicScene main,SettingParent back) {
		super(supportPlatform, main);
		this.back=back;
		ImageView background = new ImageView (new Image ("Graphics/Other/AudioSetting/background.png"));
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
	private void init(){
		enteredSetter = new SettingItem("按钮悬停",new Image("Graphics/Static/Icon/HPLine.png"),Audio.entered);
		AudioClip []audios={Audio.pressed,Audio.illegal};
		pressedSetter = new SettingItem("按钮点击",new Image("Graphics/Static/Icon/ADLine.png"),audios);
		battleSetter = new SettingItem("战斗背景",new Image("Graphics/Static/Icon/DRLine.png"),Audio.battle);
		skillSetter = new SettingItem("技能特效",new Image("Graphics/Static/Icon/EXPLine.png"),Audio.skillAudio);
		AudioClip []audios2 = {Audio.bombPop,Audio.chickPop,Audio.lineOrRowPop,Audio.normalPop};
		popSetter = new SettingItem("消除特效",new Image("Graphics/Static/Icon/MTLine.png"),audios2);
		enteredSetter.setLayoutX(GAP);
		enteredSetter.setLayoutY(GAP);
		pressedSetter.setLayoutX(GAP);
		pressedSetter.setLayoutY(enteredSetter.getLayoutY()+ITEMHEIGHT+MIDGAP);
		battleSetter.setLayoutX(GAP);
		battleSetter.setLayoutY(pressedSetter.getLayoutY()+ITEMHEIGHT+MIDGAP);
		skillSetter.setLayoutX(GAP);
		skillSetter.setLayoutY(battleSetter.getLayoutY()+ITEMHEIGHT+MIDGAP);
		popSetter.setLayoutX(GAP);
		popSetter.setLayoutY(skillSetter.getLayoutY()+ITEMHEIGHT+MIDGAP);
		board.getChildren().addAll(enteredSetter,pressedSetter,battleSetter,skillSetter,popSetter);
	}
	@Override
	public void returnStatic(){
		main.setStage(back);
	}
	private final Image reduceStatic = new Image ("Graphics/Static/Icon/reduceStatic.png");
	private final Image reduceEntered = new Image ("Graphics/Static/Icon/reduceEntered.png");
	private final Image reducePressed = new Image ("Graphics/Static/Icon/reducePressed.png");
	private final Image reduceIllegal = new Image ("Graphics/Static/Icon/reduceIllegal.png");
	private final Image addStatic = new Image ("Graphics/Static/Icon/addStatic.png");
	private final Image addEntered = new Image ("Graphics/Static/Icon/addEntered.png");
	private final Image addPressed = new Image ("Graphics/Static/Icon/addPressed.png");
	private final Image addIllegal = new Image ("Graphics/Static/Icon/addIllegal.png");
	private final Image openStatic = new Image ("Graphics/Static/Icon/openStatic.png");
	private final Image openEntered = new Image ("Graphics/Static/Icon/openEntered.png");
	private final Image openPressed = new Image ("Graphics/Static/Icon/openPressed.png");
	private final Image openIllegal = new Image ("Graphics/Static/Icon/openIllegal.png");
	private final Image closeStatic = new Image ("Graphics/Static/Icon/closeStatic.png");
	private final Image closeEntered = new Image ("Graphics/Static/Icon/closeEntered.png");
	private final Image closePressed = new Image ("Graphics/Static/Icon/closePressed.png");
	private final Image closeIllegal = new Image ("Graphics/Static/Icon/closeIllegal.png");
//	private final Image illegalImage = new Image ("Graphics/Static/Icon/blank.png");
	private final ButtonWorker illegalWorker = new ButtonWorker (){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("illegal click");
		}
		
	};
	public class SettingItem extends AnchorPane{
		private String name;
//		private AudioClip audio;
		private AudioClip[] audio;
		private Label nameLabel;
		private ImageButton reduce,add;
		private ImageButton open,close;
		private PropertyLine line;
		private int now=5;
		private Image fill;
		public SettingItem(String name,Image fill,AudioClip audio){
			this.name=name;
			this.fill=fill;
			this.audio=new AudioClip[1];
			this.audio[0]=audio;
			init();
		}
		public SettingItem (String name,Image fill,AudioClip[] audio){
			this.name=name;
			this.fill=fill;
			this.audio=audio;
			init();
		}
		private void init(){
			for (AudioClip audio :audio){
				if (audio!=null){
					this.now=(int)((audio.getVolume()+0.01)*10);
					break;
				}
			}

			nameLabel = new Label (name);
			nameLabel.setMaxSize(LABELWIDTH, LABELHEIGHT);
			nameLabel.setLayoutX(0);
			nameLabel.setLayoutY(0);
			reduce = new ImageButton(reduceStatic,reduceEntered,reducePressed,reduceWorker);
			reduce.setFitHeight(LABELHEIGHT);
			reduce.setFitWidth(LABELHEIGHT);
			reduce.setX(nameLabel.getLayoutX()+LABELWIDTH);
			reduce.setY(0);
			line = new PropertyLine (fill,LINEHEIGHT,LINEWIDTH,10,now);
			line.setLayoutX(reduce.getX()+LABELHEIGHT);
			line.setLayoutY((ITEMHEIGHT/2-LINEHEIGHT/2));
			add = new ImageButton (addStatic,addEntered,addPressed,addWorker);
			add.setFitHeight(LABELHEIGHT);
			add.setFitWidth(LABELHEIGHT);
			add.setX(line.getLayoutX()+LINEWIDTH);
			add.setY(0);
			open = new ImageButton (openIllegal,openIllegal,openIllegal,illegalWorker);
			open.setPlayAudio(false);
			open.setFitHeight(BUTTONHEIGHT);
			open.setFitWidth(BUTTONWIDTH);
			open.setX(add.getX()+LABELHEIGHT);
			open.setY(0);
			close = new ImageButton (closeStatic,closeEntered,closePressed,closeWorker);
			close.setFitHeight(BUTTONHEIGHT);
			close.setFitWidth(BUTTONWIDTH);
			close.setX(open.getX()+BUTTONWIDTH+INNERMIDGAP);
			close.setY(0);
			if (now==0){
				now=5;
				close.setStaticGraphics(closeIllegal);
				close.setEnteredGraphics(closeIllegal);
				close.setPressedGraphics(closeIllegal);
				close.setMyWorker(illegalWorker);
				close.setPlayAudio(false);
				open.setStaticGraphics(openStatic);
				open.setEnteredGraphics(openEntered);
				open.setPressedGraphics(openPressed);
				open.setMyWorker(openWorker);
				open.setPlayAudio(true);
				add.setStaticGraphics(addIllegal);
				add.setEnteredGraphics(addIllegal);
				add.setPressedGraphics(addIllegal);
				add.setMyWorker(illegalWorker);
				add.setPlayAudio(false);
				reduce.setStaticGraphics(reduceIllegal);
				reduce.setEnteredGraphics(reduceIllegal);
				reduce.setPressedGraphics(reduceIllegal);
				reduce.setMyWorker(illegalWorker);
				reduce.setPlayAudio(false);
			}
			this.getChildren().addAll(nameLabel,reduce,line,add,open,close);
		}
		private final ButtonWorker openWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				for (AudioClip audio:audio){
					if (audio!=null)
					audio.setVolume(now*0.1);
				}
				//set open illegal
				line.refresh(now);
				open.setStaticGraphics(openIllegal);
				open.setEnteredGraphics(openIllegal);
				open.setPressedGraphics(openIllegal);
				open.setMyWorker(illegalWorker);
				open.setPlayAudio(false);
				//set close legal
				close.setStaticGraphics(closeStatic);
				close.setEnteredGraphics(closeEntered);
				close.setPressedGraphics(closePressed);
				close.setMyWorker(closeWorker);
				close.setPlayAudio(true);
				//set add reduce illegal
				if (now!=0){
					add.setStaticGraphics(addStatic);
					add.setEnteredGraphics(addEntered);
					add.setPressedGraphics(addPressed);
					add.setMyWorker(addWorker);
					add.setPlayAudio(true);
				}
				if (now!=10){
					reduce.setStaticGraphics(reduceStatic);
					reduce.setEnteredGraphics(reduceEntered);
					reduce.setPressedGraphics(reducePressed);
					reduce.setMyWorker(reduceWorker);
					reduce.setPlayAudio(true);
				}
			}
			
		};
		private final ButtonWorker closeWorker = new ButtonWorker (){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				for (AudioClip audio:audio){
					if (audio!=null)
					audio.setVolume(0);
				}
				line.refresh(0);
				//set close illegal
				close.setStaticGraphics(closeIllegal);
				close.setEnteredGraphics(closeIllegal);
				close.setPressedGraphics(closeIllegal);
				close.setMyWorker(illegalWorker);
				close.setPlayAudio(false);
				//set open legal
				open.setStaticGraphics(openStatic);
				open.setEnteredGraphics(openEntered);
				open.setPressedGraphics(openPressed);
				open.setMyWorker(openWorker);
				open.setPlayAudio(true);
				//set add reduce illegal
				add.setStaticGraphics(addIllegal);
				add.setEnteredGraphics(addIllegal);
				add.setPressedGraphics(addIllegal);
				add.setMyWorker(illegalWorker);
				add.setPlayAudio(false);
				reduce.setStaticGraphics(reduceIllegal);
				reduce.setEnteredGraphics(reduceIllegal);
				reduce.setPressedGraphics(reduceIllegal);
				reduce.setMyWorker(illegalWorker);
				reduce.setPlayAudio(false);
			}
			
		};
		private final ButtonWorker reduceWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				now--;
				if(audio[0]==null){
					return ;
				}
				for (AudioClip audio:audio){
					audio.setVolume(now*0.1);
				}
				line.refresh(now);
				if(now==1){
					//make this illegal
					reduce.setMyWorker(illegalWorker);
					reduce.setStaticGraphics(reduceIllegal);
					reduce.setEnteredGraphics(reduceIllegal);
					reduce.setPressedGraphics(reduceIllegal);
					reduce.setPlayAudio(false);
				}
				else if (now==9){
					add.setMyWorker(addWorker);
					add.setStaticGraphics(addStatic);
					add.setEnteredGraphics(addEntered);
					add.setPressedGraphics(addPressed);
					add.setPlayAudio(true);
				}
			}
			
		};
		private final ButtonWorker addWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				now++;
				for (AudioClip audio :audio){
					if (audio!=null){
						audio.setVolume(now*0.1);
					}
				}
				line.refresh(now);
				for (AudioClip audio:audio){
					audio.setVolume(now*0.1);
				}
				if (now==10){
					add.setMyWorker(illegalWorker);
					add.setStaticGraphics(addIllegal);
					add.setEnteredGraphics(addIllegal);
					add.setPressedGraphics(addIllegal);
					add.setPlayAudio(false);
				}
//				else if (now==1){
//					reduce.setMyWorker(reduceWorker);
//					reduce.setStaticGraphics(reduceStatic);
//					reduce.setEnteredGraphics(reduceEntered);
//					reduce.setPressedGraphics(reducePressed);
//					reduce.setPlayAudio(true);
//				}
			}
			
		};
	}
}
