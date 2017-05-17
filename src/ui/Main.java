package ui;
import ui.abstractStage.*;
import ui.sceneInterface.*;
import ui.specialParent.BonusParent;
import ui.specialParent.NormalParent;
import ui.specialParent.PVEParent;
import ui.specialParent.StaticParent;
import ui.supportRoot.SkillChooser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bll.platform.Battle;
import bll.support.Bonus;
import bllservice.Supportable;
import util.*;
public class Main extends Application implements BasicScene,DramaticScene{
	public static Font myFont;
	public static final Color fontColor = Color.rgb(255, 0, 0);
	private BattleParent battleParent;//ÿ��ʹ��ʱ����Ҫ����ʵ����
	private StaticParent staticParent = new StaticParent(this);//һֱ����һ��staticScene����battleʱ���أ��ڷ�battleʱ��ʾ��
	private Stage stage = new Stage();
	private Scene scene = new Scene (staticParent);
	public final static int SCREENWIDTH =(int)Screen.getPrimary().getVisualBounds().getWidth()-10;
	public final static int SCREENHEIGHT = (int)Screen.getPrimary().getVisualBounds().getHeight()-10;
	
	public StaticParent getStaticParent() {
		return staticParent;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		// TODO Auto-generated method stub
		/**
		 * չʾstaticScene
		 * .
		 * �������Ҫ����battleʱ������staticScene���������٣�����ʾbattleScene��
		 * ��battle����������battleScene���ָ�staticScene��ʾ
		 * �����battlePo�鿴battle���
		 */
		try {
			myFont = Font.loadFont(getClass().getClassLoader().getResourceAsStream("ui/MyFont.ttf"), 32);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("������");
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreen(true);
//		Screen screen = Screen.getPrimary();
		
//		System.out.println("Height:"+SCREENHEIGHT);
//		System.out.println("Width:"+SCREENWIDTH);
		stage.setResizable(false);
		primaryStage= stage;
		primaryStage.show(); 
		
	}
	public void createDone(){
		Platform.runLater(()->{
		scene.getStylesheets().remove(0);
		scene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
		battleParent = new PVEParent (missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
		stage.getScene().setRoot(battleParent);
		scene.getRoot().setId("scene110"+(int)(8*Math.random()+1));
	});
	}
	MissionInfo missionInfo;
	@Override
	
	public void createNewBattle(MissionInfo missionInfo,AnchorPane father) {
		this.missionInfo=missionInfo;
		// TODO Auto-generated method stub
		//����staticscene���мǲ�Ҫ���١�
		//�������missionPo��������PVE��PVP��NORMAL֮һ��BattleScene
		//Ҫ������ע�ᵽbattlescene��ȥ���Ӷ�ʹ�ÿ�����battlescene����battleEnd�����Է���staticscene
		
		if (missionInfo.getModel()==Battle.PVE){
//			Platform.runLater(()->{
//				
//			});
			this.setStage(new SkillChooser(this.staticParent.getBasicPlatform().getPlayer1(),this,father));
//			Platform.runLater(()->{
//				scene.getStylesheets().remove(0);
//				scene.getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
//				battleParent = new PVEParent (missionInfo.getID(),staticParent.getBasicPlatform().getPlayer1(),this);
//				stage.getScene().setRoot(battleParent);
//				scene.getRoot().setId("scene110"+(int)(8*Math.random()+1));
//			});
			 
		}else if (missionInfo.getModel()==Battle.NORMAL){
			Platform.runLater(()->{
				scene.getStylesheets().remove(0);
				scene.getStylesheets().add(getClass().getResource("Normal.css").toExternalForm());
		
				
				battleParent = new NormalParent (missionInfo.getID(),this);
				stage.getScene().setRoot(battleParent);
			});
		}
	}
	
	
	@Override
	public void battleEnd(Bonus bonus) {
		// TODO Auto-generated method stub-
		//battleScene��������
		//�˷�����ɴ�battle����static
		//��ʾ�ؿ�������
		
		scene.getStylesheets().remove(0);
		scene.getStylesheets().add(getClass().getResource("static.css").toExternalForm());
		
		if (bonus!=null){
			this.setStage(new BonusParent((Supportable)staticParent.getBasicPlatform(),bonus,(BasicScene)this));
		}else{
//			this.setStage(new BattleFailedParent((BasicScene)this));
			this.setStage(staticParent);
		}
	}
	
	
	
	public static void main(String[] args){
		launch(args);
		
	}
	@Override
	public void setStage(Parent root) {
		// TODO Auto-generated method stub
		//�˷������Static������ϵͳ������ת
		Platform.runLater(()->{this.stage.getScene().setRoot(root);});
	}
	@Override
	public void returnStatic() {
		// TODO Auto-generated method stub
		//�˷�������ϵͳ����Static
		Platform.runLater(()->{this.stage.getScene().setRoot(staticParent);});
		System.out.println("return to Static");
//		this.staticParent.show();
	}
	@Override
	public void exitGame() {
		// TODO Auto-generated method stub
		stage.close();
	}
	
}
