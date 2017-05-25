package ui.supportRoot;

import bll.individual.Player;
import bllservice.Supportable;
import dal.FileHelper;
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
import ui.sceneInterface.BasicScene;
import vo.PlayerVo;

public class RecordParent extends SupportParent{
	public RecordParent(Supportable supportPlatform, BasicScene main) {
		super(supportPlatform, main);
		// TODO Auto-generated constructor stub
		ImageView background = new ImageView (new Image("Graphics/Record/background.png"));
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
	public static final int NUMBERHEIGHT = Main.SCREENHEIGHT/20;
	public static final int NUMBERWIDTH = NUMBERHEIGHT/2;
	public static final int ICONLENGTH = NUMBERHEIGHT;
	public static final int MIDGAP = ICONLENGTH/3;
	public static final int DELTA = MIDGAP;
	public static final int INNERGAP = MIDGAP/4;
	public static final int PHOTOLENGTH = (ICONLENGTH*3+2*MIDGAP);
	public static final int BUTTONHEIGHT = ICONLENGTH*3/2;
	public static final int BUTTONWIDTH = BUTTONHEIGHT*2;
	public static final int RECORDWIDTH = INNERGAP*2+PHOTOLENGTH+2*DELTA+10*ICONLENGTH+BUTTONWIDTH;
	public static final int RECORDHEIGHT = PHOTOLENGTH+2*INNERGAP;
	public static final int GAP = 10;
	public static final int BOARDHEIGHT = 4*RECORDHEIGHT+5*GAP;
	public static final int BOARDWIDTH = RECORDWIDTH+2*GAP;
	private AnchorPane board = new AnchorPane();
	private Record record0,record1,record2,record3;
	private final Image loadStatic = new Image ("Graphics/Static/Icon/loadStatic.png");
	private final Image loadEntered = new Image ("Graphics/Static/Icon/loadEntered.png");
	private final Image loadPressed = new Image ("Graphics/Static/Icon/loadPressed.png");
	private final Image loadIllegal = new Image ("Graphics/Static/Icon/loadIllegal.png");
	private final Image saveStatic = new Image ("Graphics/Static/Icon/saveStatic.png");
	private final Image saveEntered = new Image ("Graphics/Static/Icon/saveEntered.png");
	private final Image savePressed = new Image ("Graphics/Static/Icon/savePressed.png");
	private final ButtonWorker illegalWorker = new ButtonWorker(){

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println("Cannot Save!");
		}
		
	};
	private void init(){
		record0 = new Record(0);
		record1 = new Record(1);
		record2 = new Record(2);
		record3 = new Record(3);
		record0.setLayoutX(GAP);
		record0.setLayoutY(GAP);
		record1.setLayoutX(GAP);
		record1.setLayoutY(record0.getLayoutY()+GAP+RECORDHEIGHT);
		record2.setLayoutX(GAP);
		record2.setLayoutY(record1.getLayoutY()+GAP+RECORDHEIGHT);
		record3.setLayoutX(GAP);
		record3.setLayoutY(record2.getLayoutY()+GAP+RECORDHEIGHT);
		board.getChildren().addAll(record0,record1,record2,record3);
	}
	private void renew (int index){
		Platform.runLater(()->{
			if (index==0){
				this.getChildren().remove(record0);
				record0 = new Record(0);
				record0.setLayoutX(GAP);
				record0.setLayoutY(GAP);
				board.getChildren().add(record0);
			}else if (index==1){
				this.getChildren().remove(record1);
				record1=new Record(1);
				record1.setLayoutX(GAP);
				record1.setLayoutY(GAP*2+RECORDHEIGHT);
				board.getChildren().add(record1);
			}else if (index==2){
				this.getChildren().remove(record2);
				record2=new Record(2);
				record2.setLayoutX(GAP);
				record2.setLayoutY(GAP*3+RECORDHEIGHT*2);
				board.getChildren().add(record2);
			}else if (index==3){
				this.getChildren().remove(record3);
				record3 = new Record(3);
				record3.setLayoutX(GAP);
				record3.setLayoutY(GAP*4+RECORDHEIGHT*3);
				board.getChildren().add(record3);
			}else{
				System.out.println("index½âÎö´íÎó");
			}
		});
	
	}
	public class Record extends AnchorPane{
		private int index;
		private NumberImage level,hp,pp,ad,ap,dr,mr,dt,mt,sp,us,es;
		private ImageButton load,save;
		private Player player;
		public Record(int index){
			this.index=index;
			PlayerVo temp= new FileHelper().loadData(index);
			if (temp==null){
				player=null;
			}else{
				player=new Player(temp);
			}
			ImageView background = new ImageView (new Image("Graphics/Record/recordBackground.png"));
			background.setFitHeight(RECORDHEIGHT);
			background.setFitWidth(RECORDWIDTH);
			background.setX(0);
			background.setY(0);
			this.getChildren().add(background);
			this.init();
		}
		public void setNull(){
			save.setMyWorker(illegalWorker);
			load.setMyWorker(illegalWorker);
			save.setPlayAudio(false);
			load.setPlayAudio(false);
		}
		public void checkButton(){
//			save.setStaticGraphics(saveStatic);
//			save.setEnteredGraphics(saveEntered);
//			save.setPressedGraphics(savePressed);
			save.setMyWorker(saveWorker);
			load.setMyWorker(loadWorker);
			save.setPlayAudio(true);
			load.setPlayAudio(true);
		}
		private void initBlank(){
			Label blank = new Label("¿Õ");
			blank.setLayoutX(RECORDWIDTH/2-10);
			blank.setLayoutY(RECORDHEIGHT/2-5);
			this.getChildren().add(blank);
		}
		private void addBoard1(){
			ImageView L = new ImageView (new Image("Graphics/Static/Icon/L.png"));
			ImageView V = new ImageView (new Image("Graphics/Static/Icon/V.png"));
			ImageView Point = new ImageView (new Image("Graphics/Static/Icon/Point.png"));
			L.setFitHeight(NUMBERHEIGHT);
			L.setFitWidth(NUMBERWIDTH);
			V.setFitHeight(NUMBERHEIGHT);
			V.setFitWidth(NUMBERWIDTH);
			Point.setFitHeight(NUMBERHEIGHT);
			Point.setFitWidth(NUMBERWIDTH);
			L.setX(INNERGAP+PHOTOLENGTH+DELTA);
			L.setY(INNERGAP);
			V.setX(L.getX()+NUMBERWIDTH);
			V.setY(L.getY());
			Point.setX(V.getX()+NUMBERWIDTH);
			Point.setY(L.getY());
			this.getChildren().addAll(L,V,Point);
			ImageView HPIcon = new ImageView (new Image("Graphics/Static/Icon/HPIcon.png"));
			ImageView PPIcon = new ImageView (new Image("Graphics/Static/Icon/PPIcon.png"));
			HPIcon.setFitHeight(ICONLENGTH);
			HPIcon.setFitWidth(ICONLENGTH);
			PPIcon.setFitHeight(ICONLENGTH);
			PPIcon.setFitWidth(ICONLENGTH);
			HPIcon.setX(L.getX());
			HPIcon.setY(L.getY()+NUMBERHEIGHT+MIDGAP);
			PPIcon.setX(L.getX());
			PPIcon.setY(HPIcon.getY()+ICONLENGTH+MIDGAP);
			this.getChildren().addAll(HPIcon,PPIcon);
			level = new NumberImage (player.getLevel());
			hp = new NumberImage (player.getHp(),NUMBERHEIGHT,NUMBERWIDTH);
			pp = new NumberImage(player.getPotentialPoint(),NUMBERHEIGHT,NUMBERWIDTH);
			level.setSize(NUMBERHEIGHT, NUMBERWIDTH);
			level.setLayoutX(Point.getX()+NUMBERWIDTH);
			level.setLayoutY(INNERGAP);
			hp.setLayoutX(level.getLayoutX()-NUMBERWIDTH);
			hp.setLayoutY(level.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			pp.setLayoutX(level.getLayoutX()-NUMBERWIDTH);
			pp.setLayoutY(hp.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(level,hp,pp);	
		}
		private void addBoard2(){
			ImageView ADIcon = new ImageView (new Image ("Graphics/Static/Icon/ADIcon.png"));
			ImageView DRIcon = new ImageView (new Image ("Graphics/Static/Icon/DRIcon.png"));
			ImageView DTIcon = new ImageView (new Image ("Graphics/Static/Icon/DTIcon.png"));
			ADIcon.setFitHeight(ICONLENGTH);
			ADIcon.setFitWidth(ICONLENGTH);
			DRIcon.setFitHeight(ICONLENGTH);
			DRIcon.setFitWidth(ICONLENGTH);
			DTIcon.setFitHeight(ICONLENGTH);
			DTIcon.setFitWidth(ICONLENGTH);
			ADIcon.setX(level.getLayoutX()+2*NUMBERWIDTH);
			ADIcon.setY(INNERGAP);
			DRIcon.setX(ADIcon.getX());
			DRIcon.setY(ADIcon.getY()+NUMBERHEIGHT+MIDGAP);
			DTIcon.setX(ADIcon.getX());
			DTIcon.setY(DRIcon.getY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(ADIcon,DRIcon,DTIcon);
			ad = new NumberImage (player.getAd(),NUMBERHEIGHT,NUMBERWIDTH);
			dr = new NumberImage (player.getDR(),NUMBERHEIGHT,NUMBERWIDTH);
			dt = new NumberImage (player.getDT(),NUMBERHEIGHT,NUMBERWIDTH);
			ad.setLayoutX(ADIcon.getX()+ICONLENGTH);
			ad.setLayoutY(ADIcon.getY());
			dr.setLayoutX(ad.getLayoutX());
			dr.setLayoutY(ad.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			dt.setLayoutX(ad.getLayoutX());
			dt.setLayoutY(dr.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(ad,dr,dt);
		}
		private void addBoard3(){
			
			ImageView APIcon = new ImageView (new Image ("Graphics/Static/Icon/APIcon.png"));
			ImageView MRIcon = new ImageView (new Image ("Graphics/Static/Icon/MRIcon.png"));
			ImageView MTIcon = new ImageView (new Image ("Graphics/Static/Icon/MTIcon.png"));
			APIcon.setFitHeight(ICONLENGTH);
			APIcon.setFitWidth(ICONLENGTH);
			MRIcon.setFitHeight(ICONLENGTH);
			MRIcon.setFitWidth(ICONLENGTH);
			MTIcon.setFitHeight(ICONLENGTH);
			MTIcon.setFitWidth(ICONLENGTH);
			APIcon.setX(ad.getLayoutX()+3*NUMBERWIDTH);
			APIcon.setY(INNERGAP);
			MRIcon.setX(APIcon.getX());
			MRIcon.setY(APIcon.getY()+NUMBERHEIGHT+MIDGAP);
			MTIcon.setX(APIcon.getX());
			MTIcon.setY(MRIcon.getY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(APIcon,MRIcon,MTIcon);
			ap = new NumberImage (player.getAp(),NUMBERHEIGHT,NUMBERWIDTH);
			mr = new NumberImage (player.getMR(),NUMBERHEIGHT,NUMBERWIDTH);
			mt = new NumberImage (player.getMT(),NUMBERHEIGHT,NUMBERWIDTH);
			ap.setLayoutX(APIcon.getX()+ICONLENGTH);
			ap.setLayoutY(APIcon.getY());
			mr.setLayoutX(ap.getLayoutX());
			mr.setLayoutY(ap.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			mt.setLayoutX(ap.getLayoutX());
			mt.setLayoutY(mr.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(ap,mr,mt);
		}
		private void addBoard4(){
			ImageView SPIcon = new ImageView (new Image ("Graphics/Static/Icon/SPIcon.png"));
			ImageView USIcon = new ImageView (new Image ("Graphics/Static/Icon/USIcon.png"));
			ImageView ESIcon = new ImageView (new Image ("Graphics/Static/Icon/ESIcon.png"));
			SPIcon.setFitHeight(ICONLENGTH);
			SPIcon.setFitWidth(ICONLENGTH);
			USIcon.setFitHeight(ICONLENGTH);
			USIcon.setFitWidth(ICONLENGTH);
			ESIcon.setFitHeight(ICONLENGTH);
			ESIcon.setFitWidth(ICONLENGTH);
			SPIcon.setX(ap.getLayoutX()+3*NUMBERWIDTH);
			SPIcon.setY(INNERGAP);
			USIcon.setX(SPIcon.getX());
			USIcon.setY(SPIcon.getY()+NUMBERHEIGHT+MIDGAP);
			ESIcon.setX(SPIcon.getX());
			ESIcon.setY(USIcon.getY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(SPIcon,USIcon,ESIcon);
			sp = new NumberImage (player.getSkillPointNum(),NUMBERHEIGHT,NUMBERWIDTH);
			us = new NumberImage (player.getUpGradeStoneNum(),NUMBERHEIGHT,NUMBERWIDTH);
			es = new NumberImage (player.getEvolveStoneNum(),NUMBERHEIGHT,NUMBERWIDTH);
			sp.setLayoutX(SPIcon.getX()+ICONLENGTH);
			sp.setLayoutY(SPIcon.getY());
			us.setLayoutX(sp.getLayoutX());
			us.setLayoutY(sp.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			es.setLayoutX(sp.getLayoutX());
			es.setLayoutY(us.getLayoutY()+NUMBERHEIGHT+MIDGAP);
			this.getChildren().addAll(sp,us,es);
		}
		private void initRecord(){
			System.out.println("Graphics/Player/Photo"+player.getPro()+".png");
			ImageView Photo = new ImageView ("Graphics/Player/Photo"+player.getPro()+".png");
			Photo.setFitHeight(PHOTOLENGTH);
			Photo.setFitWidth(PHOTOLENGTH);
			Photo.setX(INNERGAP);
			Photo.setY(INNERGAP);
			this.getChildren().add(Photo);
			addBoard1();
			addBoard2();
			addBoard3();
			addBoard4();
		}
		private final ButtonWorker loadWorker = new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				platform.loadData(index);
			}
			
		};
		private final ButtonWorker saveWorker=new ButtonWorker(){

			@Override
			public void work() {
				// TODO Auto-generated method stub
				platform.saveData(index);
				renew(index);
			}
			
		};
		private void addButton(){
			save = new ImageButton(saveStatic,saveEntered,savePressed,saveWorker);
			save.setFitHeight(BUTTONHEIGHT);
			save.setFitWidth(BUTTONWIDTH);
			save.setX(RECORDWIDTH-INNERGAP-BUTTONWIDTH);
			save.setY(INNERGAP);
			if (player!=null){
			load = new ImageButton(loadStatic,loadEntered,loadPressed,loadWorker);
			}else{
				load = new ImageButton(loadIllegal,loadIllegal,loadIllegal,illegalWorker);
				load.setPlayAudio(false);
			}
			load.setX(save.getX());
			load.setY(RECORDHEIGHT-INNERGAP-BUTTONHEIGHT);
			load.setFitHeight(BUTTONHEIGHT);
			load.setFitWidth(BUTTONWIDTH);
			this.getChildren().addAll(save,load);
		}
		private void init(){
			if (player==null){
				initBlank();
			}else{
				initRecord();
			}
			addButton();
		}
	}
}
