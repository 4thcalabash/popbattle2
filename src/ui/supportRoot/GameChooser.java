package ui.supportRoot;

import bllservice.Chooseable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.Main;
import ui.abstractStage.SupportParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.ImageButton;
import ui.sceneInterface.BasicScene;
import util.MissionInfo;

public class GameChooser extends SupportParent {
	private final GameChooser myself = this;
	public static final int BUTTONLENGTH=200;
	public static final int BOARDHEIGHT = 500;
	public static final int BOARDWIDTH = 550;
	public static final int LEFTRIGHTGAP = 20;
	public static final int TOPBOTTOMGAP = 50;
	public static final int MIDGAP = 30;
	public static final int ITEMHEIGHT = (BOARDHEIGHT -TOPBOTTOMGAP*2-MIDGAP)/2*2/3;
	public static final int ITEMWIDTH = (BOARDWIDTH-LEFTRIGHTGAP*2)*2/3;
	public static final int INFOWIDTH = 200;
	public static final int INFOHEIGHT = 200;
	private AnchorPane board = new AnchorPane ();
	private boolean inSon = false;
	private BattleChooser battleChooser;
	public GameChooser(Chooseable basicPlatform,BasicScene main,String flag){
		this(basicPlatform,main);
//		inSon=true;
//		getChildren().remove(board);
		battleChooser=new BattleChooser(basicPlatform,myself);
//		getChildren().add(battleChooser);
//		getChildren().add(board);
		System.out.println(this);
	}
	public GameChooser(Chooseable basicPlatform,BasicScene main){
		super(basicPlatform,main);
		
		ImageView background = new ImageView (new Image("Graphics/Static/GameChooser/background.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
//		board.setMaxHeight(BOARDHEIGHT);
//		board.setMaxWidth(BOARDWIDTH);
		board.setLayoutX(Main.SCREENWIDTH/2-BOARDWIDTH/2);
		board.setLayoutY(Main.SCREENHEIGHT/2-BOARDHEIGHT/2);
		final String basicPath = "Graphics/Other/MissionGraphics/";
		ImageButton PVE= new ImageButton(new Image (basicPath+"PVEStatic.png"),new Image(basicPath+"PVEEntered.png"),
				new Image(basicPath+"PVEPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						inSon = true;
						getChildren().remove(board);
						getChildren().add(new BattleChooser(basicPlatform,myself));
					}
			
		});
		PVE.setFitHeight(ITEMHEIGHT);
		PVE.setFitWidth(ITEMWIDTH);
		PVE.setX(BOARDWIDTH/2-ITEMWIDTH/2);
		PVE.setY(TOPBOTTOMGAP+ITEMHEIGHT/2);
		
		ImageButton Normal = new ImageButton (new Image(basicPath+"normalStatic.png"),new Image(basicPath+"normalEntered.png"),
				new Image(basicPath+"normalPressed.png"),new ButtonWorker(){

					@Override
					public void work() {
						// TODO Auto-generated method stub
						inSon = true;
						getChildren().remove(board);
						getChildren().add(new NormalChooser(basicPlatform,myself));
					}
			
		});
		Normal.setFitHeight(ITEMHEIGHT);
		Normal.setFitWidth(ITEMWIDTH);
		Normal.setX(BOARDWIDTH/2-ITEMWIDTH/2);
		Normal.setY(TOPBOTTOMGAP+ITEMHEIGHT+MIDGAP+ITEMHEIGHT/2);
		board.getChildren().addAll(PVE,Normal);
		this.getChildren().add(board);
//		board.setAlignment(Pos.CENTER);

	}
	public void back(){
		getChildren().add(board);
	}
	public void createNewBattle(MissionInfo missionInfo){
		main.createNewBattle(missionInfo,battleChooser);
	}
	@Override
	public void returnStatic(){
		if (!inSon){
			super.returnStatic();
		}else{
//			super.returnStatic();
			getChildren().remove(1);
			getChildren().add(board);
			inSon=false;
		}
	}
}
