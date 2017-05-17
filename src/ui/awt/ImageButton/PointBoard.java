package ui.awt.ImageButton;

import bll.matrix.Matrix;
import bllservice.BattlePlatform;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PointBoard extends AnchorPane{
	public static final int BOARDHEIGHT = PlayerBoard.BOARDHEIGHT;
	public static final int BOARDWIDTH = PlayerBoard.BOARDWIDTH;
	public static final int ICONLENGTH = BOARDHEIGHT/6;
	public static final int GAP = ICONLENGTH;
	public static final int LINEWIDTH = (BOARDWIDTH-ICONLENGTH-2*GAP)/2;
	public static final int LINEHEIGHT = ICONLENGTH*2/5;
	private static final int DELTA = (ICONLENGTH-LINEHEIGHT)/2;
	private BattlePlatform platform;
	private AnchorPane board;
	private final String path="Graphics/PlayerBoard/";
	private PropertyLine right0,right1,right2,right3,right4,right5,left0,left1,left2,left3,left4,left5;
	private Label r0,r1,r2,r3,r4,r5,l0,l1,l2,l3,l4,l5;
	public void refresh(){
		int [] temp1=platform.getPlayer1().getElementPool();
		for (int i=0;i<Matrix.KIND;i++){
			left0.refresh(temp1[0]);
			left1.refresh(temp1[1]);
			left2.refresh(temp1[2]);
			left3.refresh(temp1[3]);
			left4.refresh(temp1[4]);
			left5.refresh(temp1[5]);
			Platform.runLater(()->{
				l0.setText("完成度:"+temp1[0]+"/"+platform.getTarget()[0]);
				l1.setText("完成度:"+temp1[1]+"/"+platform.getTarget()[1]);
				l2.setText("完成度:"+temp1[2]+"/"+platform.getTarget()[2]);
				l3.setText("完成度:"+temp1[3]+"/"+platform.getTarget()[3]);
				l4.setText("完成度:"+temp1[4]+"/"+platform.getTarget()[4]);
				l5.setText("完成度:"+temp1[5]+"/"+platform.getTarget()[5]);
			});
		}
		int[] temp2 = platform.getPlayer2().getElementPool();
		for (int i=0;i<Matrix.KIND;i++){
			right0.refresh(temp2[0]);
			right1.refresh(temp2[1]);
			right2.refresh(temp2[2]);
			right3.refresh(temp2[3]);
			right4.refresh(temp2[4]);
			right5.refresh(temp2[5]);
			Platform.runLater(()->{
				r0.setText("完成度:"+temp2[0]+"/"+platform.getTarget()[0]);
				r1.setText("完成度:"+temp2[1]+"/"+platform.getTarget()[1]);
				r2.setText("完成度:"+temp2[2]+"/"+platform.getTarget()[2]);
				r3.setText("完成度:"+temp2[3]+"/"+platform.getTarget()[3]);
				r4.setText("完成度:"+temp2[4]+"/"+platform.getTarget()[4]);
				r5.setText("完成度:"+temp2[5]+"/"+platform.getTarget()[5]);
			});
		}
	}
	public PointBoard(BattlePlatform platform){
		this.platform=platform;
		board = this;
		ImageView background = new ImageView (new Image (path+"boardBackground.png"));
		background.setFitHeight(BOARDHEIGHT);
		background.setFitWidth(BOARDWIDTH);
		background.setX(0);
		background.setY(0);
		board.getChildren().add(background);
		init();
	}
	private void init(){
		addIcon();
		addRightLine();
		addLeftLine();
		addRightLabel();
		addLeftLabel();
	}
	private void addLeftLabel(){
		l0 = new Label("完成度:0/"+platform.getTarget()[0]);
		l1 = new Label("完成度:0/"+platform.getTarget()[1]);
		l2 = new Label("完成度:0/"+platform.getTarget()[2]);
		l3 = new Label("完成度:0/"+platform.getTarget()[3]);
		l4 = new Label("完成度:0/"+platform.getTarget()[4]);
		l5 = new Label("完成度:0/"+platform.getTarget()[5]);
		l0.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l0.setLayoutY(0);
		l1.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l1.setLayoutY(ICONLENGTH);
		l2.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l2.setLayoutY(ICONLENGTH*2);
		l3.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l3.setLayoutY(ICONLENGTH*3);
		l4.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l4.setLayoutY(ICONLENGTH*4);
		l5.setLayoutX(BOARDWIDTH/2-ICONLENGTH*2-ICONLENGTH);
		l5.setLayoutY(ICONLENGTH*5);
		l0.setId("lineNumber");
		l1.setId("lineNumber");
		l2.setId("lineNumber");
		l3.setId("lineNumber");
		l4.setId("lineNumber");
		l5.setId("lineNumber");
		board.getChildren().addAll(l0,l1,l2,l3,l4,l5);
	}
	private void addRightLabel(){
		r0=new Label("完成度:0/"+platform.getTarget()[0]);
		r1=new Label("完成度:0/"+platform.getTarget()[1]);
		r2=new Label("完成度:0/"+platform.getTarget()[2]);
		r3=new Label("完成度:0/"+platform.getTarget()[3]);
		r4=new Label("完成度:0/"+platform.getTarget()[4]);
		r5=new Label("完成度:0/"+platform.getTarget()[5]);
		r0.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r0.setLayoutY(0);
		r1.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r1.setLayoutY(ICONLENGTH);
		r2.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r2.setLayoutY(ICONLENGTH*2);
		r3.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r3.setLayoutY(ICONLENGTH*3);
		r4.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r4.setLayoutY(ICONLENGTH*4);
		r5.setLayoutX(BOARDWIDTH/2+ICONLENGTH);
		r5.setLayoutY(ICONLENGTH*5);
		r0.setId("lineNumber");
		r1.setId("lineNumber");
		r2.setId("lineNumber");
		r3.setId("lineNumber");
		r4.setId("lineNumber");
		r5.setId("lineNumber");
		board.getChildren().addAll(r0,r1,r2,r3,r4,r5);
	}
	private void addRightLine(){
		right0 = new PropertyLine (new Image (path+"0Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[0],0,PropertyLine.TOWARDS_RIGHT);
		right1 = new PropertyLine (new Image (path+"1Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[1],0,PropertyLine.TOWARDS_RIGHT);
		right2 = new PropertyLine (new Image (path+"2Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[2],0,PropertyLine.TOWARDS_RIGHT);
		right3 = new PropertyLine (new Image (path+"3Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[3],0,PropertyLine.TOWARDS_RIGHT);
		right4 = new PropertyLine (new Image (path+"4Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[4],0,PropertyLine.TOWARDS_RIGHT);
		right5 = new PropertyLine (new Image (path+"5Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[5],0,PropertyLine.TOWARDS_RIGHT);
		right0.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right0.setLayoutY(DELTA);
		right1.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right1.setLayoutY(ICONLENGTH+DELTA);
		right2.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right2.setLayoutY(ICONLENGTH*2+DELTA);
		right3.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right3.setLayoutY(ICONLENGTH*3+DELTA);
		right4.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right4.setLayoutY(ICONLENGTH*4+DELTA);
		right5.setLayoutX(BOARDWIDTH/2+ICONLENGTH/2);
		right5.setLayoutY(ICONLENGTH*5+DELTA);
		board.getChildren().addAll(right0,right1,right2,right3,right4,right5);
	}
	private void addLeftLine(){
		left0 = new PropertyLine (new Image (path+"0Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[0],0,PropertyLine.TOWARDS_LEFT);
		left1 = new PropertyLine (new Image (path+"1Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[1],0,PropertyLine.TOWARDS_LEFT);
		left2 = new PropertyLine (new Image (path+"2Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[2],0,PropertyLine.TOWARDS_LEFT);
		left3 = new PropertyLine (new Image (path+"3Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[3],0,PropertyLine.TOWARDS_LEFT);
		left4 = new PropertyLine (new Image (path+"4Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[4],0,PropertyLine.TOWARDS_LEFT);
		left5 = new PropertyLine (new Image (path+"5Line.png"),LINEHEIGHT,LINEWIDTH,platform.getTarget()[5],0,PropertyLine.TOWARDS_LEFT);
		left0.setLayoutX(GAP);
		left0.setLayoutY(DELTA);
		left1.setLayoutX(GAP);
		left1.setLayoutY(ICONLENGTH+DELTA);
		left2.setLayoutX(GAP);
		left2.setLayoutY(ICONLENGTH*2+DELTA);
		left3.setLayoutX(GAP);
		left3.setLayoutY(ICONLENGTH*3+DELTA);
		left4.setLayoutX(GAP);
		left4.setLayoutY(ICONLENGTH*4+DELTA);
		left5.setLayoutX(GAP);
		left5.setLayoutY(ICONLENGTH*5+DELTA);
		board.getChildren().addAll(left0,left1,left2,left3,left4,left5);
	}
	private void addIcon(){
		ImageView icon0 = new ImageView (new Image ("Graphics/Matrix/0_100.png"));
		ImageView icon1 = new ImageView (new Image ("Graphics/Matrix/1_100.png"));
		ImageView icon2 = new ImageView (new Image ("Graphics/Matrix/2_100.png"));
		ImageView icon3 = new ImageView (new Image ("Graphics/Matrix/3_100.png"));
		ImageView icon4 = new ImageView (new Image ("Graphics/Matrix/4_100.png"));
		ImageView icon5 = new ImageView (new Image ("Graphics/Matrix/5_100.png"));
		icon0.setFitHeight(ICONLENGTH);
		icon0.setFitWidth(ICONLENGTH);
		icon1.setFitHeight(ICONLENGTH);
		icon1.setFitWidth(ICONLENGTH);
		icon2.setFitHeight(ICONLENGTH);
		icon2.setFitWidth(ICONLENGTH);
		icon3.setFitHeight(ICONLENGTH);
		icon3.setFitWidth(ICONLENGTH);
		icon4.setFitHeight(ICONLENGTH);
		icon4.setFitWidth(ICONLENGTH);
		icon5.setFitHeight(ICONLENGTH);
		icon5.setFitWidth(ICONLENGTH);
		icon0.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon0.setY(0);
		icon1.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon1.setY(ICONLENGTH);
		icon2.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon2.setY(2*ICONLENGTH);
		icon3.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon3.setY(3*ICONLENGTH);
		icon4.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon4.setY(4*ICONLENGTH);
		icon5.setX(BOARDWIDTH/2-ICONLENGTH/2);
		icon5.setY(5*ICONLENGTH);
		board.getChildren().addAll(icon0,icon1,icon2,icon3,icon4,icon5);
	}
}
